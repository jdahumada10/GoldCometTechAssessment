package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.NumbersException;
import com.juan.ahumada.techassessment.services.INumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/numbers")
public class NumbersController {

	public static final String DEFAULT_VIEW = "numbers";
	private final INumbersService numbersService;

	@Autowired
	public NumbersController(final INumbersService numbersService) {
		this.numbersService = numbersService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/findMinMax")
	public String conversionFromStringToDateTime(final @RequestParam Double firstNumber,
	                                             final @RequestParam Double secondNumber,
	                                             final Model model) {
		try {
			model.addAttribute("minNumber", numbersService.findMinNumber(firstNumber, secondNumber));
		} catch (NumbersException e) {
			model.addAttribute("minNumber", e.getMessage());
		}
		try {
			model.addAttribute("maxNumber", numbersService.findMaxNumber(firstNumber, secondNumber));
		} catch (NumbersException e) {
			model.addAttribute("maxNumber", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/multiply")
	public String multiply(final @RequestParam Long firstNumber,
	                       final @RequestParam Long secondNumber,
	                       final Model model) {
		try {
			model.addAttribute("multiplyResult", numbersService.multiplyNumbers(firstNumber, secondNumber));
		} catch (NumbersException exception) {
			model.addAttribute("multiplyResult", exception.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/computeValues")
	public String multiply(final @RequestParam Double numberA,
	                       final @RequestParam Double numberB,
	                       final @RequestParam Double numberC,
	                       final Model model) {
		try {
			model.addAttribute("computeValuesResult", numbersService.computeValues(numberA, numberB, numberC));
		} catch (NumbersException e) {
			model.addAttribute("computeValuesResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}
}
