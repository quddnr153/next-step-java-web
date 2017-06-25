package com.bw.jwp.next.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class InsertJdbcTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(InsertJdbcTemplate.class);

	public void insert(final User user, final UserDao userDao) {
		try {
			userDao.insert(user);
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}
}
