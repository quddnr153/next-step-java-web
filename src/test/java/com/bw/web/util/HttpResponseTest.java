package com.bw.web.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Byungwook, Lee
 */
public class HttpResponseTest {
	private static final String TEST_DIRECTORY = "./src/test/resources/";
	private static final String HTTP_FORWORD = "Http_Forward.txt";
	private static final String HTTP_FORWORD_RESULT = "Http_Forward_Result.txt";
	private static final String HTTP_REDIRECT = "Http_Redirect.txt";
	private static final String HTTP_REDIRECT_RESULT = "Http_Redirect_Result.txt";
	private static final String HTTP_COOKIE = "Http_Cookie.txt";
	private static final String HTTP_COOKIE_RESULT = "Http_Cookie_Result.txt";

	@Test
	public void testForward() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream(HTTP_FORWORD));
		response.forward("/index.html");
		// Then
		// Http_Forward.txt 결과는 응답 body에 index.html이 포함되어 있어야 한다.
		assertEquals("두 파일은 결과가 같아야 한다.",
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_FORWORD_RESULT), "utf-8"),
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_FORWORD), "utf-8"));
	}

	@Test
	public void testSendRedirect() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream(HTTP_REDIRECT));
		response.sendRedirect("/index.html");
		// Then
		// Http_Redirect.txt 결과는 응답 header에
		// Location 정보가 /index.html로 포함되어 있어야한다.
		assertEquals("두 파일은 결과가 같아야 한다.",
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_REDIRECT_RESULT), "utf-8"),
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_REDIRECT), "utf-8"));
	}

	@Test
	public void testAddHeader() throws Exception {
		// Given
		// When
		HttpResponse response = new HttpResponse(createOutputStream(HTTP_COOKIE));
		response.addHeader("Set-Cookie", "logined=true");
		response.sendRedirect("/index.html");
		// Then
		// Http_Cookie.txt 결과는 응답 header에 Set-Cookie 값으로
		// logined=true 값이 포함되어 있어야 한다.
		assertEquals("두 파일은 결과가 같아야 한다.",
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_COOKIE_RESULT), "utf-8"),
			FileUtils.readFileToString(new File(TEST_DIRECTORY + HTTP_COOKIE), "utf-8"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		fileDelete(HTTP_FORWORD);
		fileDelete(HTTP_REDIRECT);
		fileDelete(HTTP_COOKIE);
	}

	private static void fileDelete(final String fileName) {
		File file = new File(TEST_DIRECTORY + fileName);
		file.delete();
	}

	private OutputStream createOutputStream(final String file) throws FileNotFoundException {
		return new FileOutputStream(new File(TEST_DIRECTORY + file));
	}
}
