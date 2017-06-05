package com.bw.web.controller;

import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class CreateUserController extends AbstractController {

	@Override
	public void doPost(final HttpRequest request, final HttpResponse response) {
		UserService userService = new UserService();
		User user = userService.makeUser(request);

		userService.create(user);

		response.sendRedirect("/index.html");
	}

	@Override
	public void doGet(final HttpRequest request, final HttpResponse response) {
		response.forward("/user/form.html");
	}

}
