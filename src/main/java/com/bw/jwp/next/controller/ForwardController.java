package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Byungwook, Lee
 */
public class ForwardController implements Controller {
	private String forwardUrl;

	public ForwardController(final String forwardUrl) {
		this.forwardUrl = forwardUrl;

		if (forwardUrl == null) {
			throw new NullPointerException("forward URL is null. Insert URL where you want to go.");
		}
	}

	@Override
	public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		return forwardUrl;
	}
}
