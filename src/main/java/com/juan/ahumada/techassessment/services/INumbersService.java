package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.NumbersException;

public interface INumbersService {
	Double findMinNumber(Double firstNumber,
	                     Double secondNumber) throws NumbersException;

	Double findMaxNumber(Double firstNumber,
	                     Double secondNumber) throws NumbersException;

	Long multiplyNumbers(Long firstNumber,
	                     Long secondNumber) throws NumbersException;

	Double computeValues(Double numberA,
	                     Double numberB,
	                     Double numberC) throws NumbersException;
}
