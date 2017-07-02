package com.bw.jwp.next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;

public class ProfileController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final String userId = req.getParameter("userId");

		LOG.debug("Get Profile page : {}", userId);

		final User user = userService.getUser(userId);

		if (user == null) {
			throw new NullPointerException("사용자를 찾을 수 없습니다.");
		}

		req.setAttribute("user", user);

		return "/user/profile.jsp";
	}
}
