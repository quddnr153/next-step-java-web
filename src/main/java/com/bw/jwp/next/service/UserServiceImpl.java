package com.bw.jwp.next.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.dao.UserDao;
import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Override
	public void create(final User user) {
		LOG.debug("create user: {}", user);

		final UserDao userDao = new UserDao();

		try {
			userDao.insert(user);
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}

	@Override
	public boolean login(final User user) {
		LOG.debug("login user: {}", user);
		boolean result = true;

		final UserDao userDao = new UserDao();

		try {
			User userInDataBase = userDao.findByUserId(user.getUserId());

			if (userInDataBase == null || !user.getPassword().equals(userInDataBase.getPassword())) {
				result = false;
			}

		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());

			result = false;
		}

		return result;
	}

	@Override
	public List<User> getUsers() {
		final UserDao userDao = new UserDao();

		List<User> users = new ArrayList<>();

		try {
			users = userDao.findAll();
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}

		return users;
	}

	@Override
	public User getUser(final String userId) {
		User user = new User();
		final UserDao userDao = new UserDao();

		try {
			user = userDao.findByUserId(userId);
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}

		return user;
	}

	@Override
	public void update(final User user) {
		final UserDao userDao = new UserDao();

		try {
			userDao.update(user);
		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}
}
