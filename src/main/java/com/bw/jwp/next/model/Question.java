package com.bw.jwp.next.model;

/**
 * @author Byungwook, Lee
 */
public class Question {
	private long questionId;
	private String writer;
	private String title;
	private String contents;
	private String createdDate;
	private int countOfAnswer;

	public Question() {
	}

	public Question(final long questionId) {
		this.questionId = questionId;
	}

	public Question(final long questionId, final int countOfAnswer) {
		this.questionId = questionId;
		this.countOfAnswer = countOfAnswer;
	}

	public Question(final long questionId, final String title, final String contents) {
		this.questionId = questionId;
		this.title = title;
		this.contents = contents;
	}

	public Question(final String writer, final String title, final String contents,
					final int countOfAnswer) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.countOfAnswer = countOfAnswer;
	}

	public Question(final long questionId, final String writer, final String title, final String contents,
					final String createdDate, final int countOfAnswer) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCountOfAnswer() {
		return countOfAnswer;
	}

	public void setCountOfAnswer(int countOfAnswer) {
		this.countOfAnswer = countOfAnswer;
	}
}
