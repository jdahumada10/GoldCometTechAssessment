package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.services.data.JBossWiki;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jBossWiki")
public class JBossWikiController {

	private static final String DEFAULT_VIEW = "jBossWiki";

	@GetMapping
	public String getMainPage(Model model) {
		fillModelWithQuestions(model);
		return DEFAULT_VIEW;
	}

	@GetMapping("/question1Response")
	public String getQuestion1Response(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("jBossQuestion1Response", JBossWiki.QUESTION_1.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/question2Response")
	public String getQuestion2Response(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("jBossQuestion2Response", JBossWiki.QUESTION_2.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/question3Response")
	public String getQuestion3Response(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("jBossQuestion3Response", JBossWiki.QUESTION_3.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/question4Response")
	public String getQuestion4Response(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("jBossQuestion4Response", JBossWiki.QUESTION_4.getAnswer());
		return DEFAULT_VIEW;
	}

	private void fillModelWithQuestions(Model model) {
		final JBossWiki[] queries = JBossWiki.values();
		for(int i = 0; i < queries.length; i++) {
			model.addAttribute("jBossQuestion" + (i + 1), queries[i].getQuestion());
		}
	}
}
