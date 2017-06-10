package com.bw.web.util;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpSessions {
	private static Map<String, HttpSession> sessions = Maps.newHashMap();

	public static HttpSession getSession(final String id) {
		HttpSession session = sessions.get(id);

		if (session == null) {
			session = new HttpSession(id);
			sessions.put(id, session);
		}

		return session;
	}

	public static void remove(final String id) {
		sessions.remove(id);
	}
}
