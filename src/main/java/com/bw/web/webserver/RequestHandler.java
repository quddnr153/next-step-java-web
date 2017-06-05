/**
 *
 */
package com.bw.web.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.controller.Controller;
import com.bw.web.mapper.RequestMapping;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private static final String INDEX_GET_PATH = "/index.html";
	private static final String LOGIN_GET_PATH = "/user/login.html";
	private static final String CREATE_POST_PATH = "/user/create";
	private static final String LOGIN_POST_PATH = "/user/login";
	private static final String USER_LIST_POST_PATH = "/user/list";
	private static final String COOKIE_KEY_STRING = "Cookie";
	private static final String LOGIN_SUCCESS_COOKIE_VALUE = "logined=true";
	private static final String LOGIN_FAIL_COOKIE_VALUE = "logined=false";

	private Socket connection;

	public RequestHandler(final Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	@Override
	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
			connection.getPort());

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			HttpRequest request = new HttpRequest(in);
			HttpResponse response = new HttpResponse(out);

			String pagePath = request.getPath();

			Controller controller = RequestMapping.getController(pagePath);

			if (controller == null) {
				response.forward(pagePath);
			} else {
				controller.service(request, response);
			}

		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

}
