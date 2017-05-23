package com.bw.calc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Byungwook, Lee
 *
 */
public class StringCalculatorTest {

	@Test
	public void testSeparateStringToInt_기본구분자() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		String value = "1,2:3";

		// When
		List<Integer> actual = (new StringCalculator(value) {
			@Override
			protected int operator(List<Integer> intValues) {
				return 0;
			}
		}).separateStringToInt();

		// Then
		assertEquals("value를 separator로 나눈 값은 기대값과 같아야 한다.", expected, actual);
	}

	@Test
	public void testSeparateStringToInt_추가구분자() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		String value = "//1,2:\n3";
		String addSeparatorChars = "//\n";

		// When
		List<Integer> actual = (new StringCalculator(value, addSeparatorChars) {
			@Override
			protected int operator(List<Integer> intValues) {
				return 0;
			}
		}).separateStringToInt();

		// Then
		assertEquals("value를 separator로 나눈 값은 기대값과 같아야 한다.", expected, actual);
	}
}
