package com.bw.jwp.next.dao;

import java.util.List;

import com.bw.jwp.core.jdbc.JdbcTemplate;
import com.bw.jwp.next.model.User;

public class UserDao {
	public void insert(final User user) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "INSERT INTO USERS (userId, password, name, email) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		});
	}

	public void update(final User user) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "UPDATE USERS SET name = ?, email = ? WHERE userId = ?";

		jdbcTemplate.update(sql, pstmt -> {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUserId());
		});
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT userId, password, name, email FROM USERS";

		return jdbcTemplate.query(sql,
				pstmt -> {
				},
				rs -> (new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email")))
		);
	}

	public User findByUserId(final String userId) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		final String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";

		return jdbcTemplate.queryForObject(sql,
				pstmt -> {
					pstmt.setString(1, userId);
				},
				rs -> (new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email")))
		);
	}
}
