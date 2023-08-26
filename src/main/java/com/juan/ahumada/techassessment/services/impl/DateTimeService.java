package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.DateTimeQuestionException;
import com.juan.ahumada.techassessment.services.IDateTimeService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateTimeService implements IDateTimeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeService.class);
	public static final String DEFAULT_INVALID_DATA_MESSAGE = "Invalid data.";
	public static final String ADD_OPERATION = "add";
	public static final String SUBTRACT_OPERATION = "subtract";

	@Override
	public LocalDateTime convertStringToLocalDateTime(final String dateString,
	                                                  final String dateStringPattern) throws DateTimeQuestionException {
		if(dateString == null || dateStringPattern == null) {
			throw new DateTimeQuestionException(DEFAULT_INVALID_DATA_MESSAGE);
		}
		try {
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStringPattern.trim());
			return LocalDateTime.parse(dateString.trim(), formatter);
		} catch (Exception e) {
			LOGGER.error(String.format("Unparseable date. Date: %s", dateString), e);
			throw new DateTimeQuestionException("Unparseable date. Please give a correct String and a Pattern that matches the format.");
		}
	}

	@Override
	public String convertLocalDateTimeToString(final LocalDateTime dateTime) throws DateTimeQuestionException {
		if(dateTime == null) {
			throw new DateTimeQuestionException(DEFAULT_INVALID_DATA_MESSAGE);
		}
		return dateTime.toString();
	}

	@Override
	public String calculateFirstDayOfMonth(final LocalDateTime dateTime) throws DateTimeQuestionException {
		if(dateTime == null) {
			throw new DateTimeQuestionException(DEFAULT_INVALID_DATA_MESSAGE);
		}
		final LocalDateTime firstDayOfMonth = LocalDateTime.of(
				dateTime.getYear(),
				dateTime.getMonthValue(),
				1,
				0,
				0
		);
		return firstDayOfMonth.getDayOfWeek() + " " + firstDayOfMonth.getDayOfMonth();
	}

	@Override
	public String calculateLastDayOfMonth(final LocalDateTime dateTime) throws DateTimeQuestionException {
		if(dateTime == null) {
			throw new DateTimeQuestionException(DEFAULT_INVALID_DATA_MESSAGE);
		}
		final int year = dateTime.getYear();
		final int month = dateTime.getMonthValue();
		final YearMonth yearMonth = YearMonth.of(year, month);
		final int lastDay = yearMonth.lengthOfMonth();
		final LocalDateTime lastDayOfMonth = LocalDateTime.of(year, month, lastDay, 23, 59, 59);
		return lastDayOfMonth.getDayOfWeek() + " " + lastDayOfMonth.getDayOfMonth();
	}

	@Override
	public Integer calculateDaysBetweenTwoDates(final LocalDateTime firstDate,
	                                            final LocalDateTime secondDate) throws DateTimeQuestionException {
		if(firstDate == null || secondDate == null) {
			throw new DateTimeQuestionException(DEFAULT_INVALID_DATA_MESSAGE);
		}
		final Duration duration = Duration.between(firstDate, secondDate);
		return Math.abs(Math.toIntExact(duration.toDays()));
	}

	@Override
	public LocalDateTime addOrSubtractTime(final LocalDateTime dateTime,
	                                       final String operation,
	                                       final Integer minutes,
	                                       final Integer hours,
	                                       final Integer days) throws DateTimeQuestionException {
		validateAddOrSubtractTime(dateTime, operation);
		switch (operation) {
			case ADD_OPERATION:
				return dateTime.plusMinutes(minutes != null ? minutes : 0)
						.plusHours(hours != null ? hours : 0)
						.plusDays(days != null ? days : 0);
			case SUBTRACT_OPERATION:
				return dateTime.minusMinutes(minutes != null ? minutes : 0)
						.minusHours(hours != null ? hours : 0)
						.minusDays(days != null ? days : 0);
			default:
				throw new IllegalArgumentException("Operation must be add or subtract.");
		}
	}

	private void validateAddOrSubtractTime(final LocalDateTime dateTime,
	                                       final String operation) throws DateTimeQuestionException {
		if(dateTime == null || operation == null) {
			throw new DateTimeQuestionException("DateTime and operation must not be null");
		}
	}


}
