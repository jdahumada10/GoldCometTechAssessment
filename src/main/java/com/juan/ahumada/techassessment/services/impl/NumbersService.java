package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.NumbersException;
import com.juan.ahumada.techassessment.services.INumbersService;
import org.springframework.stereotype.Service;

@Service
public class NumbersService implements INumbersService {

	public static final String INVALID_DATA_DEFAULT_MESSAGE = "Invalid data.";

	@Override
	public Double findMinNumber(final Double firstNumber,
	                            final Double secondNumber) throws NumbersException {
		if(firstNumber == null || secondNumber == null) {
			throw new NumbersException(INVALID_DATA_DEFAULT_MESSAGE);
		}
		return Math.min(firstNumber, secondNumber);
	}

	@Override
	public Double findMaxNumber(final Double firstNumber,
	                            final Double secondNumber) throws NumbersException {
		if(firstNumber == null || secondNumber == null) {
			throw new NumbersException(INVALID_DATA_DEFAULT_MESSAGE);
		}
		return Math.max(firstNumber, secondNumber);
	}

	@Override
	public Long multiplyNumbers(final Long firstNumber,
	                            final Long secondNumber) throws NumbersException {
		if(firstNumber == null || secondNumber == null) {
			throw new NumbersException(INVALID_DATA_DEFAULT_MESSAGE);
		}
		try {
			return Math.multiplyExact(firstNumber, secondNumber);
		} catch (ArithmeticException exception) {
			throw new NumbersException("Overflow detected: " + exception.getMessage());
		}
	}

	@Override
	public Double computeValues(final Double numberA,
	                            final Double numberB,
	                            final Double numberC) throws NumbersException {
		if(numberA == null || numberB == null || numberC == null) {
			throw new NumbersException(INVALID_DATA_DEFAULT_MESSAGE);
		}
		return numberA * numberB + numberC;
	}
}
