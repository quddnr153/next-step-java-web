package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.model.User;
import com.bw.jwp.next.service.UserService;
import com.bw.jwp.next.service.UserServiceImpl;
import com.bw.jwp.next.util.UserSessionUtils;

/**
 * @author Byungwook, Lee
 */
public class UpdateFormController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateFormController.class);

	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final String userId = req.getParameter("userId");

		LOG.debug("Get user update page: {}", userId);

		final User user = userService.getUser(userId);

		if (!UserSessionUtils.isUserInSession(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		req.setAttribute("user", user);

		return "/user/updateForm.jsp";
	}
}
