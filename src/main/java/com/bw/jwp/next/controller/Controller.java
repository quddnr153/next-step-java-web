package com.bw.jwp.next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Byungwook, Lee
 */
public interface Controller {
	String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception;
}
