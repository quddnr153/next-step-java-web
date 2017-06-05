package com.bw.web.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpResponse {
	private static final String CLASS_PATH = "src/main/webapp";

	private DataOutputStream dos;
	private Map<String, String> headerContents;

	public HttpResponse(final OutputStream out) {
		dos = new DataOutputStream(out);
		headerContents = Maps.newHashMap();
	}

	public void addHeader(final String key, final String value) throws IOException {
		headerContents.put(key, value);
	}

	public void forward(final String path) throws IOException {
		byte[] body = getPage(path);

		if (path.endsWith(".html")) {
			headerContents.put("Content-Type", "text/html;charset=utf-8");
		} else if (path.endsWith(".css")) {
			headerContents.put("Content-Type", "text/css");
		} else if (path.endsWith(".js")) {
			headerContents.put("Content-Type", "application/javascript");
		}

		headerContents.put("Content-Length", Integer.toString(body.length));

		response200Header(body.length);
		responseBody(body);
	}

	public void forwardBody(final String body) throws IOException {
		byte[] bodyContents = body.getBytes();

		headerContents.put("Content-Type", "text/html;charset=utf-8");
		headerContents.put("Content-Length", Integer.toString(bodyContents.length));

		response200Header(bodyContents.length);
		responseBody(bodyContents);
	}

	public void sendRedirect(final String path) throws IOException {
		dos.writeBytes("HTTP/1.1 302 Found \r\n");
		processHeaders();
		dos.writeBytes("Location: " + path + " \r\n");
		dos.writeBytes("\r\n");
	}

	private void response200Header(final int length) throws IOException {
		dos.writeBytes("HTTP/1.1 200 OK \r\n");
		processHeaders();
		dos.writeBytes("\r\n");
	}

	private void responseBody(final byte[] body) throws IOException {
		dos.write(body, 0, body.length);
		dos.writeBytes("\r\n");
		dos.flush();
	}

	private void processHeaders() throws IOException {
		for (String key : headerContents.keySet()) {
			dos.writeBytes(key + ": " + headerContents.get(key) + " \r\n");
		}
	}

	private byte[] getPage(final String pagePath) throws IOException {
		return Files.readAllBytes(new File(CLASS_PATH + pagePath).toPath());
	}
}
