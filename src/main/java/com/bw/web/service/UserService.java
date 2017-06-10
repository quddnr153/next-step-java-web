package com.bw.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;
import com.bw.web.util.HttpRequest;

/**
 * @author Byungwook, Lee
 */
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public void create(final User user) {
		log.debug("create user: {}", user.toString());
		DataBase.addUser(user);
	}

	public boolean login(final User user) {
		log.debug("login user: {}", user.toString());
		User userInDataBase = DataBase.findUserById(user.getUserId());

		if (userInDataBase == null) {
			return false;
		}

		if (!user.getPassword().equals(userInDataBase.getPassword())) {
			return false;
		}

		return true;
	}

	public User makeUser(final HttpRequest request) {
		User user = new User();

		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));

		return user;
	}
}