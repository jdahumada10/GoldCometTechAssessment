package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.CollectionsException;
import com.juan.ahumada.techassessment.services.ICollectionsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/collections")
public class CollectionsController {
	public static final String DEFAULT_VIEW = "collections";
	public static final String DEFAULT_ARRAY_SEPARATOR = ",";
	private final ICollectionsService collectionsService;

	@Autowired
	public CollectionsController(final ICollectionsService collectionsService) {
		this.collectionsService = collectionsService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/sortArray")
	public String sortArray(@RequestParam("objectArrayInput") String objectArrayInput,
	                        Model model) {
		try {
			List<String> arrayList = new ArrayList<>(Arrays.asList(objectArrayInput.split(DEFAULT_ARRAY_SEPARATOR)));
			model.addAttribute("sortedArrayResult", collectionsService.sortArray(arrayList));
		} catch (CollectionsException e) {
			model.addAttribute("sortedArrayResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/checkArrays")
	public String checkArrays(@RequestParam("array1Input") String array1Input,
	                          @RequestParam("array2Input") String array2Input,
	                          Model model) {
		try {
			List<String> arrayList1 = new ArrayList<>(Arrays.asList(array1Input.split(DEFAULT_ARRAY_SEPARATOR)));
			List<String> arrayList2 = new ArrayList<>(Arrays.asList(array2Input.split(DEFAULT_ARRAY_SEPARATOR)));
			model.addAttribute("arrayComparisonResult", collectionsService.checkArrays(arrayList1, arrayList2));
		} catch (CollectionsException e) {
			model.addAttribute("arrayComparisonResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}
}