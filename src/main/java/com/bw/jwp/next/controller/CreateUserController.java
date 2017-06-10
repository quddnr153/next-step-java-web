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

import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;

@WebServlet(value = {"/users/create", "/users/form"})
public class CreateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CreateUserController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("Get user create form page");

		final RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");

		rd.forward(req, res);
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final User user = new User(req.getParameter("userId"), req.getParameter("password"),
				req.getParameter("name"), req.getParameter("email"));

		LOG.debug("User : {}", user);

		userService.create(user);

		res.sendRedirect("/");
	}
}
