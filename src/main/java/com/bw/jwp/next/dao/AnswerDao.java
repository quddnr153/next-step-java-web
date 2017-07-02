package com.bw.jwp.next.dao;

import java.util.List;

import com.bw.jwp.core.jdbc.JdbcTemplate;
import com.bw.jwp.next.model.Answer;

/**
 * @author Byungwook, Lee
 */
public class AnswerDao {
	public Answer insert(final Answer answer) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "INSERT INTO ANSWERS"
				+ " (writer, contents, createdDate, questionId)"
				+ " VALUES"
				+ " (?, ?, CURRENT_TIMESTAMP(), ?)";

		long id = jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, answer.getWriter());
			pstmt.setString(2, answer.getContents());
			pstmt.setLong(3, answer.getQuestionId());
		});

		return findByAnswerId(id);
	}

	public void updateContents(final Answer answer) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "UPDATE QUESTIONS"
				+ " SET contents = ?"
				+ " WHERE answerId = ?";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, answer.getContents());
			pstmt.setLong(2, answer.getAnswerId());
		});
	}

	public List<Answer> findAll() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT * FROM ANSWERS";

		return jdbcTemplate.query(sql, pstmt -> {
		}, rs -> (new Answer(rs.getLong("answerId"),
				rs.getString("writer"),
				rs.getString("contents"),
				rs.getString("createdDate"),
				rs.getLong("questionId"))));
	}

	public List<Answer> findByQuestionId(final long questionId) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

		return jdbcTemplate.query(sql, pstmt -> pstmt.setLong(1, questionId)
				, rs -> (new Answer(rs.getLong("answerId"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("createdDate"),
						rs.getLong("questionId"))));
	}

	public Answer findByAnswerId(final long answerId) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT * FROM ANSWERS WHERE answerId = ?";

		return jdbcTemplate.queryForObject(sql, pstmt -> pstmt.setLong(1, answerId)
				, rs -> new Answer(rs.getLong("answerId"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("createdDate"),
						rs.getLong("questionId")));
	}

	public long findLastInsertId() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT LAST_INSERT_ID()";

		return jdbcTemplate.queryForObject(sql, pstmt -> {
		}, rs -> rs.getLong(1));
	}
}
