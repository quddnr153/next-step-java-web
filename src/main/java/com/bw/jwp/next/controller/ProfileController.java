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

@WebServlet("/users/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final String userId = req.getParameter("userId");

		LOG.debug("Get Profile page : {}", userId);

		final User user = userService.getUser(userId);

		if (user == null) {
			throw new NullPointerException("사용자를 찾을 수 없습니다.");
		}

		req.setAttribute("user", user);

		final RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");

		rd.forward(req, res);
	}
}
