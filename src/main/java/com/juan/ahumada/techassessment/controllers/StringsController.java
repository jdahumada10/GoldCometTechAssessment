package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.StringsException;
import com.juan.ahumada.techassessment.services.IStringsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/strings")
public class StringsController {
	public static final String DEFAULT_VIEW = "strings";
	private final IStringsService stringsService;

	@Autowired
	public StringsController(final IStringsService stringsService) {
		this.stringsService = stringsService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/countsDuplicateCharacters")
	public String countsDuplicateCharacters(final @RequestParam String givenString,
	                                        final Model model) {
		try {
			model.addAttribute("duplicateCharactersResult", stringsService.countDuplicateCharacters(givenString));
		} catch (StringsException e) {
			model.addAttribute("duplicateCharactersResult", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/firstNonRepeatingCharacter")
	public String firstNonRepeatingCharacter(final @RequestParam String inputStringNonRepeat,
	                                         final Model model) {
		try {
			model.addAttribute("firstNonRepeating", stringsService.firstNonRepeatingCharacter(inputStringNonRepeat));
		} catch (StringsException e) {
			model.addAttribute("firstNonRepeating", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/checkPalindrome")
	public String checkPalindrome(final @RequestParam String inputStringPalindrome,
	                              final Model model) {
		try {
			model.addAttribute("isPalindrome", stringsService.checkPalindrome(inputStringPalindrome));
		} catch (StringsException e) {
			model.addAttribute("isPalindrome", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/joinWithDelimiter")
	public String joinWithDelimiter(final @RequestParam String inputStringsToJoin,
	                                final @RequestParam String delimiter,
	                                final Model model) {
		try {
			model.addAttribute("joinedString", stringsService.joinWithDelimiter(inputStringsToJoin, delimiter));
		} catch (StringsException e) {
			model.addAttribute("joinedString", e.getMessage());
		}
		return DEFAULT_VIEW;
	}
}

