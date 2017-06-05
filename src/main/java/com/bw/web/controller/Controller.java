package com.bw.web.controller;

import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public interface Controller {
	void service(final HttpRequest request, final HttpResponse response);
}
