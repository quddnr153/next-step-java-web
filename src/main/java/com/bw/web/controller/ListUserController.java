package com.bw.web.controller;

import java.util.Collection;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;
import com.bw.web.util.HttpSession;

/**
 * @author Byungwook, Lee
 */
public class ListUserController extends AbstractController {

	@Override
	public void doPost(final HttpRequest request, final HttpResponse response) {
	}

	@Override
	public void doGet(final HttpRequest request, final HttpResponse response) {
		if (!isLogin(request.getSession())) {
			response.sendRedirect("/user/login.html");

			return;
		}

		response.forwardBody(getUsers());
	}

	private boolean isLogin(final HttpSession session) {
		return session.getAttrubute("user") != null;
	}

	private String getUsers() {
		final Collection<User> users = DataBase.findAll();

		final StringBuilder sb = new StringBuilder();

		sb.append("<table border='1'>");
		for (final User user : users) {
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
		return "true".equals(cookieValue);
	}
}
