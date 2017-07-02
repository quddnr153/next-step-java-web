package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Byungwook, Lee
 */
public class CreateQuestionController implements Controller {
	private static final Logger LOG = LoggerFactory.getLogger(CreateQuestionController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		LOG.info("Create Question Controller");
		return null;
	}
}
