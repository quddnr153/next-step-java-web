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
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;
import com.bw.web.service.UserService;
import com.bw.web.util.HttpRequestUtils;
import com.bw.web.util.HttpRequestUtils.Pair;
import com.bw.web.util.IOUtils;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private static final String UTF_8 = "UTF-8";
	private static final String CLASS_PATH = "src/main/webapp";
	private static final String INDEX_GET_PATH = "/index.html";
	private static final String LOGIN_GET_PATH = "/user/login.html";
	private static final String LIST_GET_PATH = "/user/list.html";
	private static final String CREATE_POST_PATH = "/user/create";
	private static final String LOGIN_POST_PATH = "/user/login";
	private static final String USER_LIST_POST_PATH = "/user/list";
	private static final String COOKIE_KEY_STRING = "logined";
	private static final String LOGIN_SUCCESS_COOKIE_VALUE = "logined=true";
	private static final String LOGIN_FAIL_COOKIE_VALUE = "logined=false";
	private static final String DOC_TYPE_HTTP = "html";
	private static final String DOC_TYPE_CSS = "css";
	private static final String METHOD_POST = "POST";

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
			Map<String, String> parsedHeaderContents = getParsedHeaderContents(in);

			if (parsedHeaderContents.isEmpty()) {
				return;
			}

			String pagePath = parsedHeaderContents.get(HttpRequestUtils.REQUEST_PATH_STRING);
			String httpContent = parsedHeaderContents.get(HttpRequestUtils.HTTP_CONTENT_STRING);
			String cookieContent = parsedHeaderContents.get(HttpRequestUtils.COOKIE_STRING);

			boolean isLogined = false;
			if (StringUtils.isNotEmpty(cookieContent)) {
				Map<String, String> cookies = HttpRequestUtils.parseCookies(cookieContent);
				isLogined = getLoginCookieToBoolean(cookies.get("logined"));
			}

			if (pagePath.equals(CREATE_POST_PATH)) {
				Map<String, String> parsedQueryString = HttpRequestUtils.parseQueryString(httpContent);
				UserService userService = new UserService();

				userService.create(makeUser(parsedQueryString));
				response302Header(dos, INDEX_GET_PATH);
			} else if (pagePath.equals(LOGIN_POST_PATH)) {
				Map<String, String> parsedQueryString = HttpRequestUtils.parseQueryString(httpContent);
				UserService userService = new UserService();

				if (userService.login(makeUser(parsedQueryString))) {
					response302HeaderIncludedCookie(dos, INDEX_GET_PATH, LOGIN_SUCCESS_COOKIE_VALUE);
				} else {
					log.debug("user login fails");
					response302HeaderIncludedCookie(dos, INDEX_GET_PATH, LOGIN_FAIL_COOKIE_VALUE);
				}
			} else if (pagePath.equals(LOGIN_GET_PATH)) {
				byte[] body = getPage(pagePath);
				response200Header(dos, DOC_TYPE_HTTP, body.length);
				responseBody(dos, body);
			} else if (pagePath.equals(USER_LIST_POST_PATH)) {
				if (!isLogined) {
					response302Header(dos, LOGIN_GET_PATH);
					return;
				}

				byte[] body = getUserListToByte();

				response200Header(dos, DOC_TYPE_HTTP, body.length);
				responseBody(dos, body);
			} else if (pagePath.endsWith(".css")) {
				byte[] body = getPage(pagePath);
				response200Header(dos, DOC_TYPE_CSS, body.length);
				responseBody(dos, body);
			} else {
				byte[] body = getPage(pagePath);
				response200Header(dos, DOC_TYPE_HTTP, body.length);
				responseBody(dos, body);
			}
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private void response200Header(final DataOutputStream dos, final String docType, final int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/" + docType + ";charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private void response302Header(final DataOutputStream dos, final String redirectPath) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: " + redirectPath + "\r\n");
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
		}
	}

	private void response302HeaderIncludedCookie(final DataOutputStream dos, final String redirectPath,
												 final String cookie) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: " + redirectPath + " \r\n");
			dos.writeBytes("Set-Cookie: " + cookie + "; Path=/ \r\n");
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

	private Map<String, String> getParsedHeaderContents(final InputStream in) throws IOException {
		Map<String, String> headerContents = Maps.newHashMap();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
		String url = br.readLine();

		if (StringUtils.isEmpty(url)) {
			return headerContents;
		}

		headerContents = HttpRequestUtils.parseRequestLine(url);

		String header;
		while (StringUtils.isNotEmpty(header = br.readLine())) {
			log.debug("header: {}", header);
			Pair headerPair = HttpRequestUtils.parseHeader(header);
			headerContents.put(headerPair.getKey(), headerPair.getValue());
		}

		String contentLength = headerContents.get(HttpRequestUtils.CONTENT_LENGTH_STRING);

		if (StringUtils.isNotEmpty(contentLength)) {
			headerContents.put(HttpRequestUtils.HTTP_CONTENT_STRING,
					IOUtils.readData(br, Integer.parseInt(contentLength)));
		}

		return headerContents;
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

	private boolean getLoginCookieToBoolean(final String cookieValue) {
		if ("true".equals(cookieValue)) {
			return true;
		} else {
			return false;
		}
	}

	private byte[] getUserListToByte() {
		Collection<User> users = DataBase.findAll();

		StringBuilder sb = new StringBuilder();

		sb.append("<table border='1'>");
		for (User user : users) {
			sb.append("<tr>");
			sb.append("<td>" + user.getUserId() + "</td>");
			sb.append("<td>" + user.getName() + "</td>");
			sb.append("<td>" + user.getEmail() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		return sb.toString().getBytes();
	}
}
