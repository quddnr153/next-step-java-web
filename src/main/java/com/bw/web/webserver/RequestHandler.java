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

import com.bw.web.util.HttpRequestUtils;

/**
 * @author Byungwook, Lee
 */
public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	private static final String UTF_8 = "UTF-8";

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
			byte[] body = getPage(in);

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

	private byte[] getPage(final InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
		String requestLine = br.readLine();

		Map<String, String> parsedRequestLine = HttpRequestUtils.parseRequestLine(requestLine);

		return Files.readAllBytes(new File("src/main/webapp" + parsedRequestLine.get("path")).toPath());
	}
}
