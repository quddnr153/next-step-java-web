package com.bw.jwp.next.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.core.db.DataBase;
import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Override
	public void create(final User user) {
		LOG.debug("create user: {}", user);
		DataBase.addUser(user);
	}

	@Override
	public boolean login(final User user) {
		LOG.debug("login user: {}", user);
		User userInDataBase = DataBase.findUserById(user.getUserId());

		if (userInDataBase == null) {
			return false;
		}

		if (!user.getPassword().equals(userInDataBase.getPassword())) {
			return false;
		}

		return true;
	}

	@Override
	public List<User> getUsers() {
		return new ArrayList<>(DataBase.findAll());
	}

	@Override
	public User getUser(final String userId) {
		return DataBase.findUserById(userId);
	}

	@Override
	public void update(final User user) {
		DataBase.addUser(user);
	}

	@Override
	public User make(final HttpServletRequest request) {
		User user = new User();

		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));

		return user;
	}
}
