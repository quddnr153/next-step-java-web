package com.bw.jwp.next.service;

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

		userDao.insert(user);
	}

	@Override
	public boolean login(final User user) {
		LOG.debug("login user: {}", user);
		boolean result = true;
		final UserDao userDao = new UserDao();

		User userInDataBase = userDao.findByUserId(user.getUserId());

		if (userInDataBase == null || !user.getPassword().equals(userInDataBase.getPassword())) {
			result = false;
		}

		return result;
	}

	@Override
	public List<User> getUsers() {
		final UserDao userDao = new UserDao();

		return userDao.findAll();
	}

	@Override
	public User getUser(final String userId) {
		final UserDao userDao = new UserDao();

		return userDao.findByUserId(userId);
	}

	@Override
	public void update(final User user) {
		final UserDao userDao = new UserDao();

		userDao.update(user);
	}
}
