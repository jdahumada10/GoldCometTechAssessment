package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.mappers.EmployeeMapper;
import com.juan.ahumada.techassessment.repositories.EmployeeRepository;
import com.juan.ahumada.techassessment.services.IEmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeRepository.findAll().stream().map(EmployeeMapper.INSTANCE::toDto).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		return employeeRepository.findById(id).map(EmployeeMapper.INSTANCE::toDto).orElse(null);
	}
}
