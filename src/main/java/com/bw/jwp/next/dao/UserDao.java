package com.bw.jwp.next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bw.jwp.core.jdbc.ConnectionManager;
import com.bw.jwp.next.model.User;

public class UserDao {
	String createQueryForInsert() {
		return "INSERT INTO USERS (userId, password, name, email) VALUES (?, ?, ?, ?)";
	}

	void setValuesForInsert(final User user, final PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
	}

	String createQueryForUpdate() {
		return "UPDATE USERS SET name = ?, email = ? WHERE userId = ?";
	}

	void setValuesForUpdate(final User user, final PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getUserId());
	}

	public List<User> findAll() throws SQLException {
		final List<User> users = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionManager.getConnection();
			final String sql = "SELECT userId, password, name, email FROM USERS";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final User user = new User(rs.getString("userId"), rs.getString("password"),
						rs.getString("name"), rs.getString("email"));

				users.add(user);
			}

			return users;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public User findByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

			return user;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
