package com.bw.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class LoginController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Override
	public void doPost(HttpRequest request, HttpResponse response) {
		UserService userService = new UserService();
		User user = userService.makeUser(request);

		if (userService.login(user)) {
			response.addHeader("Set-Cookie", "logined=true");
			response.sendRedirect("/index.html");

		} else {
			log.debug("user login fails");

			response.addHeader("Set-Cookie", "logined=false");
			response.sendRedirect("/index.html");

		}
	}

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		response.forward("/user/login.html");
	}

}
