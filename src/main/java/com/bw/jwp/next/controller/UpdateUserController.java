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
import com.bw.jwp.next.util.UserSessionUtils;

@WebServlet(value = {"/users/update", "/users/updateForm"})
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UpdateUserController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final String userId = req.getParameter("userId");

		LOG.debug("Get user update page: {}", userId);

		final User user = userService.getUser(userId);

		if (!UserSessionUtils.isUserInSession(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		req.setAttribute("user", user);

		final RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");

		rd.forward(req, res);
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final User user = userService.getUser(req.getParameter("userId"));

		LOG.debug("User update : {}", user);

		if (!UserSessionUtils.isUserInSession(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		final User updateUser = new User(req.getParameter("userId"), req.getParameter("password"),
				req.getParameter("name"), req.getParameter("email"));

		LOG.debug("Update User : {}", updateUser);

		userService.update(updateUser);

		res.sendRedirect("/");
	}
}
