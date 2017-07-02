package com.bw.jwp.next.service;

import java.util.List;

import com.bw.jwp.next.model.Answer;

/**
 * @author Byungwook, Lee
 */
public interface AnswerService {
	Answer insert(final Answer answer);

	void updateContents(final Answer answer);

	List<Answer> getAnswers();

	List<Answer> getAnswersByQuestionId(final long questionId);

	Answer getAnswer(final long answerId);
}
