package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.DateTimeQuestionException;
import com.juan.ahumada.techassessment.services.IDateTimeService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dateTime")
public class DateTimeController {

	public static final String DEFAULT_VIEW = "dateTime";
	private final IDateTimeService dateTimeService;

	@Autowired
	public DateTimeController(final IDateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/conversionFromStringToDateTime")
	public String conversionFromStringToDateTime(final @RequestParam String dateString,
	                                             final @RequestParam String dateStringPattern,
	                                             final Model model) {
		try {
			final LocalDateTime result = dateTimeService.convertStringToLocalDateTime(dateString, dateStringPattern);
			model.addAttribute("conversionFromDateTimeToStringResult", result);
		} catch (DateTimeQuestionException exception) {
			model.addAttribute("conversionFromDateTimeToStringResult", exception.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/conversionFromDateTimeToString")
	public String conversionFromDateTimeToString(final @RequestParam(name = "dateTimeInput")
	                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
	                                             final Model model) {
		try {
			model.addAttribute("convertLocalDateTimeToStringResult", dateTimeService.convertLocalDateTimeToString(dateTime));
		} catch (DateTimeQuestionException e) {
			model.addAttribute("convertLocalDateTimeToStringResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/calculateFirstLastDayOfMonth")
	public String calculateFirstLastDayOfMonth(final @RequestParam(name = "dateTimeToCalculate")
	                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
	                                           final Model model) {
		try {
			model.addAttribute("firstDayOfMonthResult", dateTimeService.calculateFirstDayOfMonth(dateTime));
		} catch (DateTimeQuestionException e) {
			model.addAttribute("firstDayOfMonthResult", e.getMessage());
		}
		try {
			model.addAttribute("lastDayOfMonthResult", dateTimeService.calculateLastDayOfMonth(dateTime));
		} catch (DateTimeQuestionException e) {
			model.addAttribute("lastDayOfMonthResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/calculateDaysBetweenTwoDates")
	public String calculateDaysBetweenTwoDates(final @RequestParam(name = "firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
	                                           final @RequestParam(name = "secondDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime secondDate,
	                                           final Model model) {
		try {
			model.addAttribute("daysBetweenTwoDates", dateTimeService.calculateDaysBetweenTwoDates(firstDate, secondDate));
		} catch (DateTimeQuestionException e) {
			model.addAttribute("daysBetweenTwoDates", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/addOrSubtractTime")
	public String addOrSubtractTime(final @RequestParam(name = "dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
	                                final @RequestParam(name = "operation") String operation,
	                                final @RequestParam(name = "minutes", required = false) Integer minutes,
	                                final @RequestParam(name = "hours", required = false) Integer hours,
	                                final @RequestParam(name = "days", required = false) Integer days,
	                                final Model model) {
		model.addAttribute("initialDate", dateTime);
		try {
			model.addAttribute("dateWithOperation", dateTimeService.addOrSubtractTime(dateTime, operation, minutes, hours, days));
		} catch (DateTimeQuestionException e) {
			model.addAttribute("dateWithOperation", e.getMessage());
		}
		return DEFAULT_VIEW;
	}
}
