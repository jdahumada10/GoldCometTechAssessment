package com.juan.ahumada.techassessment.services.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.exceptions.IOCustomException;
import com.juan.ahumada.techassessment.services.IIOService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IOService implements IIOService {
	@Override
	public String streamTextFile(final MultipartFile file) throws IOCustomException {
		try {
			final InputStream inputStream = file.getInputStream();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			final StringBuilder contentBuilder = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				contentBuilder.append(line).append("\n");
			}
			reader.close();
			inputStream.close();
			return contentBuilder.toString();
		} catch (IOException e) {
			throw new IOCustomException("Error while reading the file");
		}
	}

	@Override
	public String readBinaryFile(MultipartFile file) throws IOCustomException {
		try {
			InputStream inputStream = file.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			return convertBinaryToText(new String(bytes, StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new IOCustomException("Error reading file: " + e.getMessage());
		}
	}

	private String convertBinaryToText(final String binaryText) {
		final String[] binaryCodes = binaryText.split(" ");
		final StringBuilder textBuilder = new StringBuilder();
		for(String binaryCode : binaryCodes) {
			final int asciiValue = Integer.parseInt(binaryCode, 2);
			textBuilder.append((char) asciiValue);
		}

		return textBuilder.toString();
	}

	@Override
	public byte[] convertTextToBinaryAndDownload(String text) throws IOCustomException {
		try {
			return convertTextToBinary(text).getBytes();
		} catch (Exception e) {
			throw new IOCustomException("Error while converting text to binary");
		}
	}

	private String convertTextToBinary(final String text) {
		final StringBuilder binaryText = new StringBuilder();
		for(char c : text.toCharArray()) {
			final StringBuilder binary = new StringBuilder(Integer.toBinaryString(c));
			while(binary.length() < 8) {
				binary.insert(0, "0");
			}
			binaryText.append(binary).append(" ");
		}
		return binaryText.toString().trim();
	}


	@Override
	public <T> List<T> readJsonCsvAsPojo(MultipartFile file,
	                                     Class<T> clazz) throws IOCustomException {
		try {
			final String fileName = file.getOriginalFilename();
			final InputStreamReader reader = new InputStreamReader(file.getInputStream());

			if(fileName != null && fileName.endsWith(".json")) {
				return mapJson(clazz, reader);
			} else if(fileName != null && fileName.endsWith(".csv")) {
				return mapCsv(clazz, reader);
			} else {
				throw new IOCustomException("Unsupported file format");
			}
		} catch (IOException e) {
			throw new IOCustomException("Error reading file: " + e.getMessage());
		}
	}

	private <T> List<T> mapCsv(Class<T> clazz,
	                           InputStreamReader reader) throws IOException {
		final CSVReader csvReader = new CSVReader(reader);
		final CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
				.withType(clazz)
				.withSkipLines(1)
				.build();
		final List<T> objects = csvToBean.parse();
		csvReader.close();
		return objects;
	}

	private <T> List<T> mapJson(Class<T> clazz,
	                            InputStreamReader reader) throws IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(reader, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}
}
