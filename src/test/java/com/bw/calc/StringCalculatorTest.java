package com.bw.calc;

import static org.hamcrest.CoreMatchers.*;
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
	public void testSeparateStringToInt_EmptyValue() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);

		// When
		List<Integer> actual = (new StringCalculator() {
			@Override
			protected int operator(List<Integer> intValues) {
				return 0;
			}
		}).separateStringToInt();

		// Then
		assertEquals("value 가 빈 값일 때는 0을 return 한다.", expected, actual);
	}

	@Test
	public void testSeparateStringToInt_NullValue() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);

		// When
		List<Integer> actual = (new StringCalculator(null) {
			@Override
			protected int operator(List<Integer> intValues) {
				return 0;
			}
		}).separateStringToInt();

		// Then
		assertEquals("value 가 빈 값일 때는 0을 return 한다.", expected, actual);
	}

	@Test
	public void testSeparateStringToInt_Basic_Separators() {
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
	public void testSeparateStringToInt_Additional_Separators() {
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

	@Test(expected = RuntimeException.class)
	public void testSeparateStringToInt_NegativeValue_ExceptionHandling() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		String value = "//1,2:\n3;-4";
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

	@Test
	public void testSeparateStringToInt_NegativeValue_Try_Catch() {
		// Given
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		String value = "//1,2:\n3;-4";
		String addSeparatorChars = "//\n;";

		// When
		try {
			(new StringCalculator(value, addSeparatorChars) {
				@Override
				protected int operator(List<Integer> intValues) {
					return 0;
				}
			}).separateStringToInt();
			// Then
			fail("Expected an RuntimeException to be thrown");
		} catch (RuntimeException runtimeException) {
			// Then
			assertThat(runtimeException.getMessage(), is("음수가 들어오면 RuntimeException을 던지라는 요구사항이 있어."));
		}
	}
}
