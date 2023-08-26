package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.services.data.MySqlDatabaseQueryWiki;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mySqlDatabaseWiki")
public class MySqlDatabaseWikiController {

	private static final String DEFAULT_VIEW = "mySqlDatabaseWiki";

	@GetMapping
	public String getMainPage(Model model) {
		fillModelWithQuestions(model);
		return DEFAULT_VIEW;
	}

	@GetMapping("/query1")
	public String getQuery1Answer(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("dbQuestion1Response", MySqlDatabaseQueryWiki.QUERY_1.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/query2")
	public String getQuery2Answer(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("dbQuestion2Response", MySqlDatabaseQueryWiki.QUERY_2.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/query3")
	public String getQuery3Answer(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("dbQuestion3Response", MySqlDatabaseQueryWiki.QUERY_3.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/query4")
	public String getQuery4Answer(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("dbQuestion4Response", MySqlDatabaseQueryWiki.QUERY_4.getAnswer());
		return DEFAULT_VIEW;
	}

	@GetMapping("/query5")
	public String getQuery5Answer(Model model) {
		fillModelWithQuestions(model);
		model.addAttribute("dbQuestion5Response", MySqlDatabaseQueryWiki.QUERY_5.getAnswer());
		return DEFAULT_VIEW;
	}

	private void fillModelWithQuestions(Model model) {
		final MySqlDatabaseQueryWiki[] queries = MySqlDatabaseQueryWiki.values();
		for(int i = 0; i < queries.length; i++) {
			model.addAttribute("dbQuestion" + (i + 1), queries[i].getQuestion());
		}
	}
}
