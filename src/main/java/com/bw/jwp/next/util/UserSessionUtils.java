package com.bw.jwp.next.util;

import javax.servlet.http.HttpSession;

import com.bw.jwp.next.model.User;

public class UserSessionUtils {
	public static final String USER_SESSION_KEY = "user";

	public static boolean isLogin(final HttpSession session) {
		return !(getUserFromSession(session) == null);
	}

	public static boolean isUserInSession(final HttpSession session, final User user) {
		return !(!isLogin(session) && user == null) && user.isSameUser(getUserFromSession(session));
	}

	private static User getUserFromSession(final HttpSession session) {
		final User user = (User) session.getAttribute(USER_SESSION_KEY);

		if (user == null) {
			return null;
		}

		return user;
	}
}
