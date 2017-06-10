package com.bw.jwp.next.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.util.UserSessionUtils;

@WebServlet("/users/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("Logout");

		final HttpSession session = req.getSession();

		session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);

		res.sendRedirect("/");
	}
}
