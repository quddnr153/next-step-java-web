package com.bw.jwp.next.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.dao.AnswerDao;
import com.bw.jwp.next.model.Answer;

/**
 * @author Byungwook, Lee
 */
public class AnswerServiceImpl implements AnswerService {
	private static final Logger LOG = LoggerFactory.getLogger(AnswerServiceImpl.class);
	private AnswerDao answerDao = new AnswerDao();

	@Override
	public Answer insert(Answer answer) {
		LOG.info("Insert Answer {}", answer);

		return answerDao.insert(answer);
	}

	@Override
	public void updateContents(Answer answer) {
		LOG.info("Update contents {}", answer);

		answerDao.updateContents(answer);
	}

	@Override
	public List<Answer> getAnswers() {
		LOG.info("Get Answers");

		return answerDao.findAll();
	}

	@Override
	public List<Answer> getAnswersByQuestionId(long questionId) {
		LOG.info("Get answer by question id {}", questionId);

		return answerDao.findByQuestionId(questionId);
	}

	@Override
	public Answer getAnswer(long answerId) {
		LOG.info("Get answer by answer id {}", answerId);

		return answerDao.findByAnswerId(answerId);
	}
}
