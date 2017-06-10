package com.bw.web.db;

import java.util.Collection;
import java.util.Map;

import com.bw.web.model.User;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class DataBase {
	private static Map<String, User> users = Maps.newHashMap();

	static {
		User user = new User("admin", "admin", "admin", "admin@admin.com");
		addUser(user);
	}

	public static void addUser(final User user) {
		users.put(user.getUserId(), user);
	}

	public static void deleteUser(final String userId) {
		users.remove(userId);
	}

	public static User findUserById(final String userId) {
		return users.get(userId);
	}

	public static Collection<User> findAll() {
		return users.values();
	}
}