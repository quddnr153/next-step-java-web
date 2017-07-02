package com.bw.jwp.next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

public class LoginController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final String userId = req.getParameter("userId");
		final String password = req.getParameter("password");

		LOG.debug("Login post {}, {}", userId, password);

		final User loginUser = new User();

		loginUser.setUserId(userId);
		loginUser.setPassword(password);

		if (userService.login(loginUser)) {
			final HttpSession session = req.getSession();

			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, loginUser);

			return "redirect:/";
		}

		req.setAttribute("isLogIn", true);
		return "/user/login.jsp";
	}
}
