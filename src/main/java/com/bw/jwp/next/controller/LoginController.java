package com.bw.jwp.next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

@WebServlet(value = {"/users/login", "/users/loginForm"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("Get login page");

		final RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");

		rd.forward(req, res);
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final String userId = req.getParameter("userId");
		final String password = req.getParameter("password");

		LOG.debug("Login post {}, {}", userId, password);

		final User loginUser = new User();

		loginUser.setUserId(userId);
		loginUser.setPassword(password);

		if (userService.login(loginUser)) {
			final HttpSession session = req.getSession();

			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, loginUser);

			res.sendRedirect("/");
		} else {
			req.setAttribute("isLogIn", true);

			final RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");

			rd.forward(req, res);
		}
	}
}
