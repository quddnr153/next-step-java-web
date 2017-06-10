package com.bw.web.util;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpSession {
	private Map<String, Object> session = Maps.newHashMap();
	private String id;

	public HttpSession(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAttributes(final String key, final Object value) {
		session.put(key, value);
	}

	public Object getAttrubute(final String key) {
		return session.get(key);
	}

	public void removeAttribute(final String key) {
		session.remove(key);
	}

	public void invalidate() {
		HttpSessions.remove(id);
	}
}
