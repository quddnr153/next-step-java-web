package com.bw.jwp.next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.service.AnswerService;
import com.bw.jwp.next.service.AnswerServiceImpl;
import com.bw.jwp.next.service.QuestionService;
import com.bw.jwp.next.service.QuestionServiceImpl;

/**
 * @author Byungwook, Lee
 */
public class GetQuestionController implements Controller {
	private QuestionService questionService = new QuestionServiceImpl();
	private AnswerService answerService = new AnswerServiceImpl();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		final long questionId = Long.parseLong(req.getParameter("questionId"));

		req.setAttribute("question", questionService.getQuestion(questionId));
		req.setAttribute("answers", answerService.getAnswersByQuestionId(questionId));

		return "/qna/show.jsp";
	}
}
