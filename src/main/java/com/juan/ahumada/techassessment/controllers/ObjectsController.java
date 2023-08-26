package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.ObjectsException;
import com.juan.ahumada.techassessment.services.IObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/objects")
public class ObjectsController {
	public static final String DEFAULT_VIEW = "objects";
	private final IObjectsService objectsService;

	@Autowired
	public ObjectsController(final IObjectsService objectsService) {
		this.objectsService = objectsService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/performNullCheck")
	public String multiply(final @RequestParam String jsonObject,
	                       final Model model) {
		try {
			model.addAttribute("nullCheckResult", objectsService.performNullCheck(jsonObject));
		} catch (ObjectsException e) {
			model.addAttribute("nullCheckResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/switchExample")
	public String buildSwitchExample(final @RequestParam(required = false) String cases,
	                                 final Model model) {
		model.addAttribute("switchResult", objectsService.buildSwitchExample(cases));
		return DEFAULT_VIEW;
	}
}
