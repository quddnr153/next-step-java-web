package com.bw.calc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Byungwook, Lee
 *
 */
public class StringCalculatorAdditionTest {

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
		int actual = (new StringCalculatorAddition().operator(intValues));

		// Then
		assertEquals("1 + 2 + 3 + 4 = 10 이다.", expected, actual);
	}

}
