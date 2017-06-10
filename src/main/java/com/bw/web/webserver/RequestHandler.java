/**
 *
 */
package com.bw.web.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.controller.Controller;
import com.bw.web.mapper.RequestMapping;
import com.bw.web.util.HttpRequest;
import com.bw.web.util.HttpRequestUtils;
import com.bw.web.util.HttpResponse;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

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

			if (getSessionId(request.getHeader("Cookie")) == null) {
				response.addHeader("Set-Cookie", "JSESSIONID=" + UUID.randomUUID());
			}

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

	private String getSessionId(final String cookieValue) {
		final Map<String, String> cookies = HttpRequestUtils.parseCookies(cookieValue);

		return cookies.get("JSESSIONID");
	}
}
