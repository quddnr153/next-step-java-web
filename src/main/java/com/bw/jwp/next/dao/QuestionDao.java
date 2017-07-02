package com.bw.jwp.next.dao;

import java.util.List;

import com.bw.jwp.core.jdbc.JdbcTemplate;
import com.bw.jwp.next.model.Question;

/**
 * @author Byungwook, Lee
 */
public class QuestionDao {
	public void insert(final Question question) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "INSERT INTO QUESTIONS"
				+ " (writer, title, contents, createdDate, countOfAnswer)"
				+ " VALUES"
				+ " (?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, question.getWriter());
			pstmt.setString(2, question.getTitle());
			pstmt.setString(3, question.getContents());
			pstmt.setString(4, question.getCreatedDate());
			pstmt.setInt(5, question.getCountOfAnswer());
		});
	}

	public void updteTitleAndContents(final Question question) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "UPDATE QUESTIONS"
				+ " SET title = ?, contents = ?"
				+ " WHERE questionId = ?";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, question.getTitle());
			pstmt.setString(2, question.getContents());
			pstmt.setLong(3, question.getQuestionId());
		});
	}

	public void updteCountOfAnswer(final Question question) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "UPDATE QUESTIONS"
				+ " SET countOfAnswer = countOfAnswer + 1"
				+ " WHERE questionId = ?";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setLong(1, question.getQuestionId());
		});
	}

	public List<Question> findAll() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT * FROM QUESTIONS";

		return jdbcTemplate.query(sql, pstmt -> {
				}, rs -> (new Question(rs.getLong("questionId"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getString("createdDate"),
						rs.getInt("countOfAnswer")))
		);
	}

	public Question findByQuestionId(final long questionId) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

		return jdbcTemplate.queryForObject(sql, pstmt -> pstmt.setLong(1, questionId)
				, rs -> (new Question(rs.getLong("questionId"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getString("createdDate"),
						rs.getInt("countOfAnswer")))
		);
	}
}
