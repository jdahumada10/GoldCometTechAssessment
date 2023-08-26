package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.StringsException;
import com.juan.ahumada.techassessment.services.IStringsService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class StringsService implements IStringsService {

	public static final String INVALID_INPUT_STRING_DEFAULT_MESSAGE = "Input string cannot be null.";
	public static final String DEFAULT_DELIMITTER_TO_SPLIT_JOINABLE_STRING = ",";

	@Override
	public Integer countDuplicateCharacters(String givenString) throws StringsException {
		if(givenString == null) {
			throw new StringsException(INVALID_INPUT_STRING_DEFAULT_MESSAGE);
		}
		final Set<Character> seenCharacters = new HashSet<>();
		final Set<Character> duplicateCharacters = new HashSet<>();
		for(char c : givenString.toCharArray()) {
			if(!seenCharacters.add(c)) {
				duplicateCharacters.add(c);
			}
		}
		return duplicateCharacters.size();
	}

	@Override
	public String firstNonRepeatingCharacter(String inputString) throws StringsException {
		if(inputString == null) {
			throw new StringsException(INVALID_INPUT_STRING_DEFAULT_MESSAGE);
		}
		final Map<Character, Integer> charCounts = new HashMap<>();
		for(char c : inputString.toCharArray()) {
			charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
		}
		for(char c : inputString.toCharArray()) {
			if(charCounts.get(c) == 1) {
				return String.valueOf(c);
			}
		}
		return "No non-repeating character found.";
	}

	@Override
	public String checkPalindrome(String inputString) throws StringsException {
		if(inputString == null) {
			throw new StringsException(INVALID_INPUT_STRING_DEFAULT_MESSAGE);
		}
		final String cleanedString = inputString.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		final String reversedString = new StringBuilder(cleanedString).reverse().toString();
		return cleanedString.equals(reversedString) ? "Palindrome" : "Not a palindrome";
	}

	@Override
	public String joinWithDelimiter(String inputStringsToJoin,
	                                String delimiter) throws StringsException {
		if(inputStringsToJoin == null || delimiter == null) {
			throw new StringsException("Input strings and delimiter cannot be null.");
		}
		final String[] stringsArray = inputStringsToJoin.split(DEFAULT_DELIMITTER_TO_SPLIT_JOINABLE_STRING);
		return String.join(delimiter, stringsArray);
	}
}
