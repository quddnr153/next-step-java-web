package com.bw.jwp.core.db;

import java.util.Collection;
import java.util.Map;

import com.bw.jwp.next.model.User;
import com.google.common.collect.Maps;

public class DataBase {
	private static Map<String, User> users = Maps.newHashMap();

	static {
		users.put("admin", new User("admin", "admin", "admin", "admin@admin.com"));
	}

	public static void addUser(User user) {
		users.put(user.getUserId(), user);
	}

	public static User findUserById(String userId) {
		return users.get(userId);
	}

	public static Collection<User> findAll() {
		return users.values();
	}

	public static void deleteUser(final String userId) {
		users.remove(userId);
	}
}
