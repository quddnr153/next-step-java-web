package com.bw.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @author Byungwook, Lee
 */
public class HttpResponseTest {
	private String testDirectory = "./src/test/resources/";

	@Test
	public void testForward() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream("Http_Forward.txt"));
		response.forward("/index.html");
		// Then
		// Http_Forward.txt 결과는 응답 body에 index.html이 포함되어 있어야 한다.
	}

	@Test
	public void testSendRedirect() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream("Http_Redirect.txt"));
		response.sendRedirect("/index.html");
		// Then
		// Http_Redirect.txt 결과는 응답 header에
		// Location 정보가 /index.html로 포함되어 있어야한다.
	}

	@Test
	public void testAddHeader() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream("Http_Cookie.txt"));
		response.addHeader("Set-Cookie", "logined=true");
		response.sendRedirect("/index.html");
		// Then
		// Http_Cookie.txt 결과는 응답 header에 Set-Cookie 값으로
		// logined=true 값이 포함되어 있어야 한다.
	}

	private OutputStream createOutputStream(final String file) throws FileNotFoundException {
		return new FileOutputStream(new File(testDirectory + file));
	}
}
