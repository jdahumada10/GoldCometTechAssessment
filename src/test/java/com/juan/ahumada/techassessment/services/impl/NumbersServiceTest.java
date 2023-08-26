package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.NumbersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumbersServiceTest {

	private NumbersService numbersService;

	@BeforeEach
	public void setUp() {
		numbersService = new NumbersService();
	}

	@Test
	void testFindMinNumber() throws NumbersException {
		assertEquals(3.0, numbersService.findMinNumber(3.0, 5.0));
	}

	@Test
	void testFindMaxNumber() throws NumbersException {
		assertEquals(5.0, numbersService.findMaxNumber(3.0, 5.0));
	}

	@Test
	void testMultiplyNumbers() throws NumbersException {
		assertEquals(15L, numbersService.multiplyNumbers(3L, 5L));
	}

	@Test
	void testComputeValues() throws NumbersException {
		assertEquals(10.0, numbersService.computeValues(2.0, 3.0, 4.0));
	}

	@Test
	void testInvalidDataThrowsException() {
		assertThrows(NumbersException.class, () -> numbersService.findMinNumber(null, 5.0));
		assertThrows(NumbersException.class, () -> numbersService.findMaxNumber(3.0, null));
		assertThrows(NumbersException.class, () -> numbersService.multiplyNumbers(null, 5L));
		assertThrows(NumbersException.class, () -> numbersService.computeValues(2.0, null, 4.0));
	}

	@Test
	void testOverflowThrowsException() {
		assertThrows(NumbersException.class, () -> numbersService.multiplyNumbers(Long.MAX_VALUE, 2L));
	}
}
