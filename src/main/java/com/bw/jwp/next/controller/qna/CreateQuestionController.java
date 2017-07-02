package com.bw.jwp.next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.Question;
import com.bw.jwp.next.service.QuestionService;
import com.bw.jwp.next.service.QuestionServiceImpl;

/**
 * @author Byungwook, Lee
 */
public class CreateQuestionController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(CreateQuestionController.class);
	private static final int INIT_COUNT_OF_ANSWER = 0;

	private QuestionService questionService = new QuestionServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final Question question = new Question(req.getParameter("writer"), req.getParameter("title"),
				req.getParameter("contents"), INIT_COUNT_OF_ANSWER);

		LOG.info("Question : {}", question);

		questionService.create(question);

		return "redirect:/";
	}
}
