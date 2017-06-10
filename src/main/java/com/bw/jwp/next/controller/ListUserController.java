package com.bw.jwp.next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

@WebServlet(value = {"/users", "/users/list"})
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ListUserController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("Get user list page");

		if (!UserSessionUtils.isLogin(req.getSession())) {
			res.sendRedirect("/users/loginForm");
			return;
		}

		req.setAttribute("users", userService.getUsers());

		final RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");

		rd.forward(req, res);
	}
}
