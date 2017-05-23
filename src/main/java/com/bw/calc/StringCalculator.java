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
	private int result = 0;

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

	protected List<Integer> separateStringToInt() {
		List<Integer> intValues = new ArrayList<Integer>();
		List<String> stringValues = Arrays.asList(StringUtils.split(value, separatorChars));

		for (String stringValue : stringValues) {
			intValues.add(Integer.parseInt(stringValue));
		}

		return intValues;
	}

	abstract protected int operator(final List<Integer> intValues);
}
