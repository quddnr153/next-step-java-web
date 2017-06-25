package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.util.UserSessionUtils;

public class LogoutController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) {
		LOG.debug("Logout");

		final HttpSession session = req.getSession();

		session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);

		return "redirect:/";
	}
}
