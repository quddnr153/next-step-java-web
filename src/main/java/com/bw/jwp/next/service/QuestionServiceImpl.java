package com.bw.jwp.next.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.dao.QuestionDao;
import com.bw.jwp.next.model.Question;

/**
 * @author Byungwook, Lee
 */
public class QuestionServiceImpl implements QuestionService {
	private static final Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public void create(Question question) {
		LOG.info("Create Question {}", question);

		questionDao.insert(question);
	}

	@Override
	public List<Question> getQuestions() {
		LOG.info("Get questions");

		return questionDao.findAll();
	}

	@Override
	public Question getQuestion(long questionId) {
		LOG.info("Get question by question id {}", questionId);

		return questionDao.findByQuestionId(questionId);
	}

	@Override
	public void updateTitleAndContents(Question question) {
		LOG.info("Update Title and Contents {}", question);

		questionDao.updateTitleAndContents(question);
	}

	@Override
	public void updateCountOfAnswer(Question question) {
		LOG.info("Update count of answer {}", question);

		questionDao.updateCountOfAnswer(question);
	}
}
