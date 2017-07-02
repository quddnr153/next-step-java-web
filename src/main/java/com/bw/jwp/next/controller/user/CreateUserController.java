package com.bw.jwp.next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;

public class CreateUserController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(CreateUserController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final User user = new User(req.getParameter("userId"), req.getParameter("password"),
				req.getParameter("name"), req.getParameter("email"));

		LOG.debug("User : {}", user);

		userService.create(user);

		return "redirect:/";
	}
}
