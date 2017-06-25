package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.core.db.DataBase;

public class HomeController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		LOG.debug("Get home page");

		req.setAttribute("users", DataBase.findAll());

		return "index.jsp";
	}
}
