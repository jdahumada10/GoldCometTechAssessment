package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.ObjectsException;
import java.util.Set;

public interface IObjectsService {
	String performNullCheck(String jsonObject) throws ObjectsException;

	String buildSwitchExample(final String cases);
}
