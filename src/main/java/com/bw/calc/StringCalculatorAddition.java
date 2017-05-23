package com.bw.calc;

import java.util.List;

/**
 * @author Byungwook, Lee
 *
 */
public class StringCalculatorAddition extends StringCalculator {

	@Override
	protected int operator(List<Integer> intValues) {
		return intValues.stream().mapToInt(Integer::intValue).sum();
	}

}
