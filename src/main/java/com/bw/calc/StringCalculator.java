package com.bw.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Byungwook, Lee
 *
 */
abstract public class StringCalculator {
	private String value = "";
	private String separatorChars = ",:";

	public StringCalculator() {}

	public StringCalculator(final String value) {
		this.value = value;
	}

	public StringCalculator(final String value, final String separatorChars) {
		this.value = value;
		this.separatorChars += separatorChars;
	}

	public int calculate() {
		return operator(separateStringToInt());
	}

	protected List<Integer> separateStringToInt() throws RuntimeException {
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

	/**
	 * 덧셈, 곱셈, 나눗셈 등 구현하고자하는 연산자로 구현하시오.
	 * @param intValues
	 * @return
	 */
	abstract protected int operator(final List<Integer> intValues);
}
