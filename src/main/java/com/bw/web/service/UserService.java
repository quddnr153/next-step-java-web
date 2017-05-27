package com.bw.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;

/**
 * @author Byungwook, Lee
 *
 */
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public void createUser(final User user) {
		log.info("create user: {}", user.toString());
		DataBase.addUser(user);
	}
}
