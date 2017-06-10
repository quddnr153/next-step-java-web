package com.bw.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.web.util.HttpRequestUtils.Pair;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpRequest {
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
	private static final String METHOD = "method";
	private static final String PATH = "path";
	private static final String VERSION = "version";
	private static final String SPACE_BAR = " ";
	private static final String QUESTION_MARK = "\\?";
	private static final String UTF_8 = "UTF-8";
	private static final String CONTENT_LENGTH_STRING = "Content-Length";
	private static final String COOKIE = "Cookie";

	private Map<String, String> request = Maps.newHashMap();

	public HttpRequest(final InputStream in) throws IOException {
		this.request = getParsedHeaderContents(in);
	}

	public String getMethod() {
		return request.get(METHOD);
	}

	public String getPath() {
		return request.get(PATH);
	}

	public Map<String, String> getCookies() {
		final String cookie = getHeader(COOKIE);

		return HttpRequestUtils.parseCookies(cookie);
	}

	public String getHeader(final String fieldKey) {
		return request.get(fieldKey);
	}

	public String getParameter(final String parameterKey) {
		return request.get(parameterKey);
	}

	private Map<String, String> getParsedHeaderContents(final InputStream in) throws IOException {
		Map<String, String> headerContents = Maps.newHashMap();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
		String url = br.readLine();

		if (StringUtils.isEmpty(url)) {
			return headerContents;
		}

		headerContents = parseRequestLine(url);

		String header;
		while (StringUtils.isNotEmpty(header = br.readLine())) {
			log.debug("header: {}", header);
			Pair headerPair = HttpRequestUtils.parseHeader(header);
			headerContents.put(headerPair.getKey(), headerPair.getValue());
		}

		String contentLength = headerContents.get(CONTENT_LENGTH_STRING);

		if (StringUtils.isNotEmpty(contentLength)) {
			String queryString = IOUtils.readData(br, Integer.parseInt(contentLength));
			Map<String, String> postBody = HttpRequestUtils.parseQueryString(queryString);

			headerContents.putAll(postBody);
		}

		return headerContents;
	}

	private Map<String, String> parseRequestLine(final String requestLine) {
		Map<String, String> result = Maps.newHashMap();

		if (StringUtils.isEmpty(requestLine)) {
			return result;
		}

		// {METHOD} {PATH?QUERY_STRING} {VERSION}
		List<String> splitedRequestLine = Arrays.asList(requestLine.split(SPACE_BAR));
		// {PATH} {QUERY_STRING}
		List<String> splitedPath = Arrays.asList(splitedRequestLine.get(1).split(QUESTION_MARK));

		result.put(METHOD, splitedRequestLine.get(0));
		result.put(PATH, splitedPath.get(0));
		result.put(VERSION, splitedRequestLine.get(2));

		if (splitedPath.size() == 2) {
			Map<String, String> queryString = HttpRequestUtils.parseQueryString(splitedPath.get(1));
			result.putAll(queryString);
		}

		return result;
	}

	public HttpSession getSession() {
		return HttpSessions.getSession(getCookies().get("JSESSIONID"));
	}
}
