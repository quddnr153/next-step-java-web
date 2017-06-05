/**
 *
 */
package com.bw.web.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpRequestUtils;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private static final String INDEX_GET_PATH = "/index.html";
	private static final String LOGIN_GET_PATH = "/user/login.html";
	private static final String CREATE_POST_PATH = "/user/create";
	private static final String LOGIN_POST_PATH = "/user/login";
	private static final String USER_LIST_POST_PATH = "/user/list";
	private static final String COOKIE_KEY_STRING = "Cookie";
	private static final String LOGIN_SUCCESS_COOKIE_VALUE = "logined=true";
	private static final String LOGIN_FAIL_COOKIE_VALUE = "logined=false";

	private Socket connection;

	public RequestHandler(final Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	@Override
	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
			connection.getPort());

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			HttpRequest request = new HttpRequest(in);
			HttpResponse response = new HttpResponse(out);

			String pagePath = request.getPath();
			String cookieContent = request.getHeader(COOKIE_KEY_STRING);

			boolean isLogined = false;

			if (StringUtils.isNotEmpty(cookieContent)) {
				Map<String, String> cookies = HttpRequestUtils.parseCookies(cookieContent);
				isLogined = getLoginCookieToBoolean(cookies.get("logined"));
			}

			if (pagePath.equals(CREATE_POST_PATH)) {
				UserService userService = new UserService();

				User user = userService.makeUser(request);

				userService.create(user);

				response.sendRedirect(INDEX_GET_PATH);

			} else if (pagePath.equals(LOGIN_POST_PATH)) {
				UserService userService = new UserService();

				User user = userService.makeUser(request);

				if (userService.login(user)) {
					response.addHeader("Set-Cookie", LOGIN_SUCCESS_COOKIE_VALUE);
					response.sendRedirect(INDEX_GET_PATH);

				} else {
					log.debug("user login fails");

					response.addHeader("Set-Cookie", LOGIN_FAIL_COOKIE_VALUE);
					response.sendRedirect(INDEX_GET_PATH);

				}

			} else if (pagePath.equals(LOGIN_GET_PATH)) {
				response.forward(pagePath);

			} else if (pagePath.equals(USER_LIST_POST_PATH)) {
				if (!isLogined) {
					response.sendRedirect(LOGIN_GET_PATH);

					return;
				}

				response.forwardBody(getUsers());

			} else if (pagePath.endsWith(".css")) {
				response.forward(pagePath);

			} else {
				response.forward(pagePath);

			}
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private boolean getLoginCookieToBoolean(final String cookieValue) {
		if ("true".equals(cookieValue)) {
			return true;
		} else {
			return false;
		}
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
}
