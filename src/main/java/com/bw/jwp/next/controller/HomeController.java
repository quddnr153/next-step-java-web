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

import com.bw.jwp.core.db.DataBase;

@WebServlet(value = {"", "/home", "/index"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("Get home page");

		req.setAttribute("users", DataBase.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");

		rd.forward(req, res);
	}
}
