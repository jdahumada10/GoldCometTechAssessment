package com.juan.ahumada.techassessment.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.exceptions.ObjectsException;
import com.juan.ahumada.techassessment.services.IObjectsService;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService implements IObjectsService {
	public static final String CASES_DEFAULT_SEPARATOR = ",";

	@Override
	public String performNullCheck(final String jsonObject) throws ObjectsException {
		if(jsonObject == null || jsonObject.isEmpty()) {
			throw new ObjectsException("All the object is null.");
		}
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			final EmployeeDto employee = objectMapper.readValue(jsonObject, EmployeeDto.class);
			validateObject(employee);
			return "All values are valid";
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public String buildSwitchExample(final String cases) {
		final StringBuilder switchBuilder = new StringBuilder();
		switchBuilder.append("switch (expression) {\n");
		for(String caseValue : getUniqueCases(cases)) {
			if(caseValue != null && !caseValue.isEmpty()) {
				switchBuilder.append("    case \"").append(caseValue).append("\":\n");
				switchBuilder.append("        // Case ").append(caseValue).append(" code\n");
				switchBuilder.append("        break;\n");
			}
		}
		switchBuilder.append("    default:\n");
		switchBuilder.append("        // Default code\n");
		switchBuilder.append("}");
		return switchBuilder.toString();
	}

	private Set<String> getUniqueCases(final String cases) {
		return Arrays.stream(cases.split(CASES_DEFAULT_SEPARATOR)).collect(Collectors.toSet());
	}

	private <T> void validateObject(final T object) throws ObjectsException {
		Class<?> clazz = object.getClass();

		for(Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			Object fieldValue;
			try {
				fieldValue = field.get(object);
			} catch (IllegalAccessException e) {
				throw new IllegalAccessError("Error accessing field: " + e.getMessage());
			}
			checkNotNull(fieldValue, "NullPointerException found: " + field.getName() + " is null");
		}
	}

	private void checkNotNull(final Object reference,
	                          final String errorMessage) throws ObjectsException {
		if(reference == null) {
			throw new ObjectsException(errorMessage);
		}
	}
}
