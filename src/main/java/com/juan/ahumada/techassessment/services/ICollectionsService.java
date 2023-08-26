package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.CollectionsException;
import java.util.List;

public interface ICollectionsService {

	String sortArray(List<String> inputList) throws CollectionsException;

	String checkArrays(List<String> list1,
	                   List<String> list2) throws CollectionsException;
}
