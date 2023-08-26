package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.StringsException;

public interface IStringsService {
	Integer countDuplicateCharacters(String givenString) throws StringsException;

	String firstNonRepeatingCharacter(String inputString) throws StringsException;

	String checkPalindrome(String inputString) throws StringsException;

	String joinWithDelimiter(String inputStringsToJoin,
	                         String delimiter) throws StringsException;
}
