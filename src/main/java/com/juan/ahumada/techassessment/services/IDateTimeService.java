package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.DateTimeQuestionException;
import java.time.LocalDateTime;

public interface IDateTimeService {
	LocalDateTime convertStringToLocalDateTime(String dateString,
	                                           String dateStringPattern) throws DateTimeQuestionException;

	String convertLocalDateTimeToString(LocalDateTime dateTime) throws DateTimeQuestionException;

	String calculateFirstDayOfMonth(LocalDateTime dateTime) throws DateTimeQuestionException;

	String calculateLastDayOfMonth(LocalDateTime dateTime) throws DateTimeQuestionException;

	Integer calculateDaysBetweenTwoDates(LocalDateTime firstDate,
	                                     LocalDateTime secondDate) throws DateTimeQuestionException;

	LocalDateTime addOrSubtractTime(LocalDateTime dateTime,
	                                String operation,
	                                Integer minutes,
	                                Integer hours,
	                                Integer days) throws DateTimeQuestionException;
}
