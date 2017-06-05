package com.bw.web.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

/**
 * @author Byungwook, Lee
 */
public class HttpRequesTest {
	private String testDirectory = "./src/test/resources/";
	private static final String HTTP_GET_TEST_FILE_NAME = "Http_GET.txt";
	private static final String HTTP_POST_TEST_FILE_NAME = "http_POST.txt";

	@Test
	public void testGetMethod_GET() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_GET_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("get method test, return value is GET.", "GET", request.getMethod());
	}

	@Test
	public void testGetPath_GET() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_GET_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("get path test, return value is /user/create.", "/user/create", request.getPath());
	}

	@Test
	public void testGetHeader_GET() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_GET_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("get header test, return value is keep-alive.", "keep-alive", request.getHeader("Connection"));
	}

	@Test
	public void testGetParameter_GET() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_GET_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("get parameter test, return value is java.", "java", request.getParameter("userId"));
	}

	@Test
	public void testGetMethod_POST() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_POST_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("POST method test, return value is POST.", "POST", request.getMethod());
	}

	@Test
	public void testGetPath_POST() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_POST_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("POST path test, return value is /user/create.", "/user/create", request.getPath());
	}

	@Test
	public void testGetHeader_POST() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_POST_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("POST header test, return value is keep-alive.", "keep-alive", request.getHeader("Connection"));
	}

	@Test
	public void testGetParameter_POST() throws Exception {
		// Given
		InputStream in = new FileInputStream(new File(testDirectory + HTTP_POST_TEST_FILE_NAME));

		// When
		HttpRequest request = new HttpRequest(in);

		// Then
		assertEquals("POST parameter test, return value is java.", "java", request.getParameter("userId"));
	}
}
