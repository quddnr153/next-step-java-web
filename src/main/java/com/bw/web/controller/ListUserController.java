package com.bw.web.controller;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpRequestUtils;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class ListUserController extends AbstractController {

	@Override
	public void doPost(HttpRequest request, HttpResponse response) {}

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		String cookie = request.getHeader("Cookie");

		if (!isLogin(cookie)) {
			response.sendRedirect("/user/login.html");

			return;
		}

		response.forwardBody(getUsers());
	}

	private boolean isLogin(final String cookie) {
		boolean isLogined = false;

		if (StringUtils.isNotEmpty(cookie)) {
			Map<String, String> cookies = HttpRequestUtils.parseCookies(cookie);
			isLogined = getLoginCookieToBoolean(cookies.get("logined"));
		}

		return isLogined;
	}

	private String getUsers() {
		Collection<User> users = DataBase.findAll();

		StringBuilder sb = new StringBuilder();

		sb.append("<table border='1'>");
		for (User user : users) {
			sb.append("<tr>");
			sb.append("<td>" + user.getUserId() + "</td>");
			sb.append("<td>" + user.getName() + "</td>");
			sb.append("<td>" + user.getEmail() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		return sb.toString();
	}

	private boolean getLoginCookieToBoolean(final String cookieValue) {
		if ("true".equals(cookieValue)) {
			return true;
		} else {
			return false;
		}
	}

}
