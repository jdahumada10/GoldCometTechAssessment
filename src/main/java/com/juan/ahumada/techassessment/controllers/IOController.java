package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.exceptions.IOCustomException;
import com.juan.ahumada.techassessment.services.IIOService;
import java.lang.reflect.Field;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/io")
public class IOController {
	public static final String DEFAULT_VIEW = "io";
	private final IIOService iOService;

	private final static Logger LOGGER = LoggerFactory.getLogger(IOController.class);

	@Autowired
	public IOController(IIOService iOService) {
		this.iOService = iOService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/streamTextFile")
	public String streamTextFile(final @RequestParam("file") MultipartFile file,
	                             final Model model) {
		try {
			model.addAttribute("streamedText", iOService.streamTextFile(file));
		} catch (IOCustomException e) {
			model.addAttribute("streamedText", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/readBinaryFile")
	public String readBinaryFile(final @RequestParam("file") MultipartFile file,
	                             final Model model) {
		try {
			model.addAttribute("binaryContent", iOService.readBinaryFile(file));
		} catch (IOCustomException e) {
			model.addAttribute("binaryContent", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/writeBinaryFileFromText")
	public ResponseEntity<byte[]> writeBinaryFileFromText(final @RequestParam("text") String text) {
		try {
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", "binaryFile.bin");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return ResponseEntity.ok()
					.headers(headers)
					.body(iOService.convertTextToBinaryAndDownload(text));
		} catch (IOCustomException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	@PostMapping("/readJsonCsvAsPojo")
	public String readJsonCsvAsPojo(final @RequestParam("file") MultipartFile file,
	                                final Model model) {
		try {
			model.addAttribute("pojoResult", convertObjectListToString(iOService.readJsonCsvAsPojo(file, EmployeeDto.class)));
		} catch (IOCustomException e) {
			model.addAttribute("pojoResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	private <T> String convertObjectListToString(List<T> objectList) {
		StringBuilder result = new StringBuilder();
		for(T object : objectList) {
			Class<?> clazz = object.getClass();
			result.append(clazz.getSimpleName()).append(":\n");
			for(Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					Object fieldValue = field.get(object);
					result.append("\t").append(field.getName()).append(": ").append(fieldValue).append("\n");
				} catch (IllegalAccessException e) {
					throw new IllegalAccessError("Error accessing field: " + e.getMessage());
				}
			}
			result.append("\n");
		}
		return result.toString();
	}
}
