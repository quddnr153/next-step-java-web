package com.bw.jwp.next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

public class UpdateUserController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateUserController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final User user = userService.getUser(req.getParameter("userId"));

		LOG.debug("User update : {}", user);

		if (!UserSessionUtils.isUserInSession(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		final User updateUser = new User(req.getParameter("userId"), req.getParameter("password"),
				req.getParameter("name"), req.getParameter("email"));

		LOG.debug("Update User : {}", updateUser);

		userService.update(updateUser);

		return "redirect:/";
	}
}
