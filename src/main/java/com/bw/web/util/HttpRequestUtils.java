package com.bw.web.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class HttpRequestUtils {
	public static final String REQUEST_METHOD_STRING = "method";
	public static final String REQUEST_PATH_STRING = "path";
	public static final String REQUEST_VERSION_STRING = "version";
	public static final String QUERY_STRING = "queryString";
	public static final String HTTP_CONTENT_STRING = "httpContent";
	public static final String CONTENT_LENGTH_STRING = "Content-Length";

	private static final String SPACE_BAR = " ";
	private static final String AND = "&";
	private static final String SEMI_COLON = ";";
	private static final String EQUALS = "=";
	private static final String COLON_SPACE_BAR = ": ";
	private static final String QUESTION_MARK = "\\?";

	public static Map<String, String> parseRequestLine(final String requestLine) {
		Map<String, String> result = Maps.newHashMap();

		if (StringUtils.isEmpty(requestLine)) {
			return result;
		}

		// {METHOD} {PATH?QUERY_STRING} {VERSION}
		List<String> splitedRequestLine = Arrays.asList(requestLine.split(SPACE_BAR));
		// {PATH} {QUERY_STRING}
		List<String> splitedPath = Arrays.asList(splitedRequestLine.get(1).split(QUESTION_MARK));

		result.put(REQUEST_METHOD_STRING, splitedRequestLine.get(0));
		result.put(REQUEST_PATH_STRING, splitedPath.get(0));
		result.put(REQUEST_VERSION_STRING, splitedRequestLine.get(2));

		if (splitedPath.size() == 2) {
			result.put(QUERY_STRING, splitedPath.get(1));
		}

		return result;
	}

	/**
	 * @param queryString은
	 *            URL에서 ? 이후에 전달되는 field1=value1&field2=value2 형식임
	 * @return
	 */
	public static Map<String, String> parseQueryString(final String queryString) {
		return parseValues(queryString, AND);
	}

	/**
	 * @param 쿠키
	 *            값은 name1=value1; name2=value2 형식임
	 * @return
	 */
	public static Map<String, String> parseCookies(final String cookies) {
		return parseValues(cookies, SEMI_COLON);
	}

	private static Map<String, String> parseValues(final String values, final String separator) {
		if (Strings.isNullOrEmpty(values)) {
			return Maps.newHashMap();
		}

		String[] tokens = values.split(separator);
		return Arrays.stream(tokens).map(t -> getKeyValue(t, EQUALS)).filter(p -> p != null)
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}

	public static class Pair {
		String key;
		String value;

		Pair(final String key, final String value) {
			this.key = key.trim();
			this.value = value.trim();
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Pair other = (Pair)obj;
			if (key == null) {
				if (other.key != null) {
					return false;
				}
			} else if (!key.equals(other.key)) {
				return false;
			}
			if (value == null) {
				if (other.value != null) {
					return false;
				}
			} else if (!value.equals(other.value)) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "Pair [key=" + key + ", value=" + value + "]";
		}
	}

	public static Pair parseHeader(final String header) {
		return getKeyValue(header, COLON_SPACE_BAR);
	}

	static Pair getKeyValue(final String keyValue, final String regex) {
		if (Strings.isNullOrEmpty(keyValue)) {
			return null;
		}

		String[] tokens = keyValue.split(regex);
		if (tokens.length != 2) {
			return null;
		}

		return new Pair(tokens[0], tokens[1]);
	}
}
