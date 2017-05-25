package com.bw.calc;

import java.util.List;

/**
 * @author Byungwook, Lee
 *
 */
public class StringCalculatorAddition extends StringCalculator {

	public StringCalculatorAddition() {}

	public StringCalculatorAddition(final String value) {
		super(value);
	}

	@Override
	protected int operator(final List<Integer> intValues) {
		return intValues.stream().mapToInt(Integer::intValue).sum();
	}

}
