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
public class UpdateQuestionCountOfAnswerController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateQuestionCountOfAnswerController.class);
	private QuestionService questionService = new QuestionServiceImpl();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Question question = new Question(Long.parseLong(req.getParameter("questionId")),
				Integer.parseInt(req.getParameter("countOfAnswer")));

		LOG.info("Update question count of answer {}", question);

		questionService.updateCountOfAnswer(question);

		return "redirect:/";
	}
}
