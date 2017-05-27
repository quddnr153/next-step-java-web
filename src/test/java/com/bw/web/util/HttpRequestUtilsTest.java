package com.bw.web.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bw.web.util.HttpRequestUtils.Pair;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpRequestUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testParseRequestLine_Basic_Guidance() {
		// Given
		String requestLine = "GET /index.html HTTP/1.1";
		Map<String, String> expected = Maps.newHashMap();
		expected.put("method", "GET");
		expected.put("path", "/index.html");
		expected.put("version", "HTTP/1.1");

		// When
		Map<String, String> actual = HttpRequestUtils.parseRequestLine(requestLine);

		// Then
		assertEquals("GET /index.html HTTP/1.1", expected, actual);
		assertEquals("method = GET 이다.", expected.get("method"), actual.get("method"));
		assertEquals("path = /index.html 이다.", expected.get("path"), actual.get("path"));
		assertEquals("version = HTTP/1.1 이다.", expected.get("version"), actual.get("version"));
	}

	@Test
	public void testParseRequestLine_Query_String() {
		// Given
		String requestLine = "GET /user/create?userId=test&password=test HTTP/1.1";
		Map<String, String> expected = Maps.newHashMap();
		expected.put("method", "GET");
		expected.put("path", "/user/create");
		expected.put("version", "HTTP/1.1");
		expected.put("queryString", "userId=test&password=test");

		// When
		Map<String, String> actual = HttpRequestUtils.parseRequestLine(requestLine);

		// Then
		assertEquals("GET /index.html HTTP/1.1", expected, actual);
		assertEquals("method = GET 이다.", expected.get("method"), actual.get("method"));
		assertEquals("path = /index.html 이다.", expected.get("path"), actual.get("path"));
		assertEquals("version = HTTP/1.1 이다.", expected.get("version"), actual.get("version"));
		assertEquals("userId=test&password=test 이다.", expected.get("queryString"), actual.get("queryString"));
	}

	@Test
	public void testParseQueryString_Basic_Guidance() {
		// Given
		String queryString = "id=test&password=test";
		Map<String, String> expected = Maps.newHashMap();
		expected.put("id", "test");
		expected.put("password", "test");

		// When
		Map<String, String> actual = HttpRequestUtils.parseQueryString(queryString);

		// Then
		assertEquals("id=test, password=test 이다.", expected, actual);
	}

	@Test
	public void testParseQueryString_Null() {
		// Given
		String queryString = null;
		Map<String, String> expected = Maps.newHashMap();

		// When
		Map<String, String> actual = HttpRequestUtils.parseQueryString(queryString);

		// Then
		assertNotNull("queryString이 null이어도 map 객체를 넘긴다.", expected);
		assertEquals("null은 빈 map을 return 한다.", expected, actual);
	}

	@Test
	public void testParseQueryString_Empty_String() {
		// Given
		String queryString = "";
		Map<String, String> expected = Maps.newHashMap();

		// When
		Map<String, String> actual = HttpRequestUtils.parseQueryString(queryString);

		// Then
		assertNotNull("queryString이 empty string 이어도 map 객체를 넘긴다.", expected);
		assertEquals("null은 빈 map을 return 한다.", expected, actual);
	}

	@Test
	public void testParseCookies() {
		// Given
		String cookies = "name=value; name2=value2; name3=value3";
		Map<String, String> expected = Maps.newHashMap();
		expected.put("name", "value");
		expected.put("name2", "value2");
		expected.put("name3", "value3");

		// When
		Map<String, String> actual = HttpRequestUtils.parseCookies(cookies);

		// Then
		assertEquals("name=value; name2=value2; name3=value3 이다.", expected, actual);
	}

	@Test
	public void testParseCookies_Null() {
		// Given
		String cookies = null;
		Map<String, String> expected = Maps.newHashMap();

		// When
		Map<String, String> actual = HttpRequestUtils.parseCookies(cookies);

		// Then
		assertNotNull("cookies이 null이어도 map 객체를 넘긴다.", expected);
		assertEquals("null은 빈 map을 return 한다.", expected, actual);
	}

	@Test
	public void testParseCookies_Empty_String() {
		// Given
		String cookies = null;
		Map<String, String> expected = Maps.newHashMap();

		// When
		Map<String, String> actual = HttpRequestUtils.parseCookies(cookies);

		// Then
		assertNotNull("cookies이 empty string 이어도 map 객체를 넘긴다.", expected);
		assertEquals("null은 빈 map을 return 한다.", expected, actual);
	}

	@Test
	public void testGetKeyValue() {
		// Given
		String keyValue = "Host: localhost:8080";
		String regex = ": ";
		Pair expected = new Pair("Host", "localhost:8080");

		// When
		Pair actual = HttpRequestUtils.getKeyValue(keyValue, regex);

		// Then
		assertEquals("Host = localhost:8080 이다.", expected, actual);
	}

	@Test
	public void testParseHeader() {
		// Given
		String header = "Host: localhost:8080";
		Pair expected = new Pair("Host", "localhost:8080");

		// When
		Pair actual = HttpRequestUtils.parseHeader(header);

		// Then
		assertEquals("Host = localhost:8080 이다.", expected, actual);
	}

}
