package com.bw.web.controller;

import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public abstract class AbstractController implements Controller {
	private static final String POST = "POST";
	private static final String GET = "GET";

	@Override
	public void service(final HttpRequest request, final HttpResponse response) {
		final String method = request.getMethod();

		if (POST.equals(method)) {
			doPost(request, response);

		} else if (GET.equals(method)) {
			doGet(request, response);

		}
	}

	public abstract void doPost(final HttpRequest request, final HttpResponse response);

	public abstract void doGet(final HttpRequest request, final HttpResponse response);
}
