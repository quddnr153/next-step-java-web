package com.bw.jwp.next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.core.jdbc.ConnectionManager;
import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class InsertJdbcTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(InsertJdbcTemplate.class);

	public void insert(final User user, final UserDao userDao) {
		final String sql = userDao.createQueryForInsert();

		try (final Connection con = ConnectionManager.getConnection();
			 final PreparedStatement pstmt = con.prepareStatement(sql);) {
			userDao.setValuesForInsert(user, pstmt);
			pstmt.executeUpdate();

		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}
}
