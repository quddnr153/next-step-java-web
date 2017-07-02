package com.bw.jwp.next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

public class ListUserController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(ListUserController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		LOG.debug("Get user list page");

		if (!UserSessionUtils.isLogin(req.getSession())) {
			return "redirect:/users/loginForm";
		}

		req.setAttribute("users", userService.getUsers());
		return "/user/list.jsp";
	}
}
