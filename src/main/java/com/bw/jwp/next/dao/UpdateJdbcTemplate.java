package com.bw.jwp.next.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class UpdateJdbcTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateJdbcTemplate.class);

	public void update(final User user, final UserDao userDao) {
		try {
			userDao.update(user);
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}
}
