package com.bw.jwp.next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.Answer;
import com.bw.jwp.next.model.Question;
import com.bw.jwp.next.service.AnswerService;
import com.bw.jwp.next.service.AnswerServiceImpl;
import com.bw.jwp.next.service.QuestionService;
import com.bw.jwp.next.service.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Byungwook, Lee
 */
public class CreateAnswerController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(CreateAnswerController.class);

	private AnswerService answerService = new AnswerServiceImpl();
	private QuestionService questionService = new QuestionServiceImpl();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		final long questionId = Long.parseLong(req.getParameter("questionId"));
		final Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"), questionId);
		LOG.debug("answer : {}", answer);

		final Answer savedAnswer = answerService.insert(answer);
		questionService.updateCountOfAnswer(new Question(questionId));
		final ObjectMapper mapper = new ObjectMapper();

		res.setContentType("application/json;charset=UTF-8");
		final PrintWriter out = res.getWriter();
		out.print(mapper.writeValueAsString(savedAnswer));

		return null;
	}
}
