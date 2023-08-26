package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.CollectionsException;
import com.juan.ahumada.techassessment.services.ICollectionsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CollectionsService implements ICollectionsService {

	public static final String LINE_BREAK = "\n";

	@Override
	public String sortArray(List<String> inputList) throws CollectionsException {
		try {
			final List<String> sortedList = new ArrayList<>(inputList);
			final List<String> sortingSteps = new ArrayList<>();
			final String sortingMessage = "Bubble Sort" + LINE_BREAK;
			final String initialArray = "Initial array: " + sortedList + LINE_BREAK;
			final int n = sortedList.size();
			boolean swapped;
			for(int i = 0; i < n - 1; i++) {
				swapped = false;
				for(int j = 0; j < n - i - 1; j++) {
					if(sortedList.get(j).compareTo(sortedList.get(j + 1)) > 0) {
						// Swap elements
						String temp = sortedList.get(j);
						sortedList.set(j, sortedList.get(j + 1));
						sortedList.set(j + 1, temp);
						swapped = true;
					}
				}
				if(!swapped) {
					break; // No swaps were made, the array is sorted
				}
				sortingSteps.add("Step " + (sortingSteps.size() + 1) + ": " + sortedList);
			}
			sortingSteps.add("Final sorted array: " + sortedList);
			return sortingMessage + initialArray + String.join(LINE_BREAK, sortingSteps);
		} catch (Exception e) {
			throw new CollectionsException("Error sorting array: " + e.getMessage());
		}
	}


	@Override
	public String checkArrays(List<String> array1,
	                          List<String> array2) throws CollectionsException {
		try {
			if(array1.equals(array2)) {
				return "Arrays are equal.";
			} else {
				int minLength = Math.min(array1.size(), array2.size());
				for(int i = 0; i < minLength; i++) {
					if(!array1.get(i).equals(array2.get(i))) {
						return "Arrays are different at index " + i + ": " + array1.get(i) + " != " + array2.get(i);
					}
				}
				return "Arrays are different in length.";
			}
		} catch (Exception e) {
			throw new CollectionsException("Error comparing arrays: " + e.getMessage());
		}
	}
}