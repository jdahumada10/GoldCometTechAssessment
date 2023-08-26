package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import java.util.List;

public interface IEmployeeService {
	List<EmployeeDto> getAllEmployees();
	EmployeeDto getEmployeeById(Long id);
}
