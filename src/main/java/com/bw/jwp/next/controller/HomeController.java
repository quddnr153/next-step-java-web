package com.bw.jwp.next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.model.Question;
import com.bw.jwp.next.service.QuestionService;
import com.bw.jwp.next.service.QuestionServiceImpl;

public class HomeController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	private QuestionService questionService = new QuestionServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		LOG.debug("Get home page");
		List<Question> questions = questionService.getQuestions();

		for (Question q :
				questions) {
			System.out.println(q.getTitle());
		}

		req.setAttribute("questions", questions);

		return "/home.jsp";
	}
}
