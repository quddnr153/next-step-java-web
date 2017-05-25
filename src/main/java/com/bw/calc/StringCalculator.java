package com.bw.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Byungwook, Lee
 *
 */
public abstract class StringCalculator {
	private static final String DEFAULT_VALUE = "0";
	private static final String CUSTOM_START_CHAR = "//";
	private static final String CUSTOM_END_CHAR = "\n";
	private static final int START_INDEX_AFTER_CUSTOM_END_CHAR = 1;

	private String value = "";
	private String separatorChars = ",:";

	public StringCalculator() {}

	public StringCalculator(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int calculate() {
		return operator(separateStringToInt());
	}

	List<Integer> separateStringToInt() throws RuntimeException {
		if (StringUtils.isEmpty(value)) {
			value = DEFAULT_VALUE;
		}

		if (isExistCustomSeparator()) {
			addCustomSeparator();
			splitSubStringExceptCustomSeparator();
		}

		List<Integer> intValues = new ArrayList<Integer>();
		List<String> stringValues = Arrays.asList(StringUtils.split(value, separatorChars));

		for (String stringValue : stringValues) {
			Integer intValue = Integer.parseInt(stringValue);

			if (intValue < 0) {
				throw new RuntimeException("음수가 들어오면 RuntimeException을 던지라는 요구사항이 있어.");
			}

			intValues.add(Integer.parseInt(stringValue));
		}

		return intValues;
	}

	private boolean isExistCustomSeparator() {
		return value.startsWith(CUSTOM_START_CHAR) && value.contains(CUSTOM_END_CHAR);
	}

	private void addCustomSeparator() {
		for (int index = CUSTOM_START_CHAR.length(); index < value.indexOf(CUSTOM_END_CHAR); index++) {
			separatorChars += value.charAt(index);
		}
	}

	private void splitSubStringExceptCustomSeparator() {
		value = value.substring(value.indexOf(CUSTOM_END_CHAR) + START_INDEX_AFTER_CUSTOM_END_CHAR);
	}

	/**
	 * 덧셈, 곱셈, 나눗셈, 평균, 최대/최소 값 등 구현하고자하는 연산자로 구현하시오.
	 * @param intValues
	 * @return
	 */
	abstract int operator(final List<Integer> intValues);
}
