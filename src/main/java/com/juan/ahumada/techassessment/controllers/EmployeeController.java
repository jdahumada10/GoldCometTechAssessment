package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.dtos.EmployeeDto;
import com.juan.ahumada.techassessment.services.IEmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private final IEmployeeService employeeService;

	@Autowired
	public EmployeeController(final IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public String getAllInvoices(Model model) {
		final List<EmployeeDto> employees = employeeService.getAllEmployees();
		model.addAttribute("list", employees);
		return "allEmployeesPage";
	}
}
