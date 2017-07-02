package com.bw.jwp.next.service;

import java.util.List;

import com.bw.jwp.next.model.Question;

/**
 * @author Byungwook, Lee
 */
public interface QuestionService {
	void create(final Question question);

	List<Question> getQuestions();

	Question getQuestion(final long questionId);

	void updateTitleAndContents(final Question question);

	void updateCountOfAnswer(final Question question);
}
