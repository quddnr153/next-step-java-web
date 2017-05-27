/**
 *
 */
package com.bw.web.webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequestUtils;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private static final String UTF_8 = "UTF-8";
	private static final String CLASS_PATH = "src/main/webapp";

	private Socket connection;

	public RequestHandler(final Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	@Override
	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
			connection.getPort());

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			DataOutputStream dos = new DataOutputStream(out);
			Map<String, String> parsedRequestLine = getParsedRequestLine(in);

			String pagePath = parsedRequestLine.get(HttpRequestUtils.REQUEST_PATH_STRING);
			String queryString = parsedRequestLine.get(HttpRequestUtils.QUERY_STRING);

			if (pagePath.equals("/user/create")) {
				Map<String, String> parsedQueryString = HttpRequestUtils.parseQueryString(queryString);
				UserService userService = new UserService();
				userService.createUser(makeUser(parsedQueryString));
			}

			byte[] body = getPage(pagePath);

			response200Header(dos, body.length);
			responseBody(dos, body);
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private void response200Header(final DataOutputStream dos, final int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private void responseBody(final DataOutputStream dos, final byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.flush();
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private Map<String, String> getParsedRequestLine(final InputStream in) throws IOException {
		Map<String, String> parsedRequestLine = Maps.newHashMap();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
		String requestLine = br.readLine();

		log.info(requestLine);

		parsedRequestLine = HttpRequestUtils.parseRequestLine(requestLine);

		return parsedRequestLine;
	}

	private byte[] getPage(final String pagePath) throws IOException {
		return Files.readAllBytes(new File(CLASS_PATH + pagePath).toPath());
	}

	private User makeUser(final Map<String, String> parsedQueryString) {
		User user = new User();
		user.setUserId(parsedQueryString.get("userId"));
		user.setPassword(parsedQueryString.get("password"));
		user.setName(parsedQueryString.get("name"));
		user.setEmail(parsedQueryString.get("email"));
		return user;
	}
}
