package com.bw.jwp.next.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public interface UserService {
	void create(final User user);

	boolean login(final User user);

	List<User> getUsers();

	User getUser(final String userId);

	void update(final User user);

	User make(final HttpServletRequest httpServletRequest);
}
