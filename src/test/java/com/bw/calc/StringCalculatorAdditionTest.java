package com.bw.calc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Byungwook, Lee
 *
 */
public class StringCalculatorAdditionTest {
	private StringCalculator calc;

	@Before
	public void setUp() {
		calc = new StringCalculatorAddition();
	}

	@Test
	public void testCalculate_Basic_Guidance() {
		// Given
		int expected = 6;
		String value = "//;\n1;2;3";
		calc.setValue(value);

		// When
		int actual = calc.calculate();

		// Then
		assertEquals("1 + 2 + 3 = 6 이다.", expected, actual);
	}

	@Test
	public void testOperator() {
		// Given
		int expected = 10;
		List<Integer> intValues = new ArrayList<>();
		intValues.add(1);
		intValues.add(2);
		intValues.add(3);
		intValues.add(4);

		// When
		int actual = calc.operator(intValues);

		// Then
		assertEquals("1 + 2 + 3 + 4 = 10 이다.", expected, actual);
	}

}
