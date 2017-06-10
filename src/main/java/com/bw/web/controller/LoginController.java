package com.bw.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;
import com.bw.web.util.HttpSession;

/**
 * @author Byungwook, Lee
 */
public class LoginController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Override
	public void doPost(final HttpRequest request, final HttpResponse response) {
		final UserService userService = new UserService();
		final User user = userService.makeUser(request);

		if (!userService.login(user)) {
			log.debug("user login fails");

			response.addHeader("Set-Cookie", "logined=false");
			response.sendRedirect("/user/login_failed.html");

			return;
		}

		final HttpSession session = request.getSession();

		session.setAttributes("user", user);
		response.addHeader("Set-Cookie", "logined=true");
		response.sendRedirect("/index.html");
	}

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		response.forward("/user/login.html");
	}

}
