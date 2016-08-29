package ua.nure.yushin.SummaryTask4.validators;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewCarCommand;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushin.SummaryTask4.exception.AppException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;

public class ValidatorOfInputParameters {

	private static final Logger LOG = Logger.getLogger(ValidatorOfInputParameters.class);

	private static final String REGEX_EMAIL = "^([a-z0-9_\\-]+\\.)*[a-z0-9_\\-]+@([a-z0-9][a-z0-9\\-]*[a-z0-9]\\.)+[a-z]{2,6}$";
	private static final String REGEX_CAR_BREND_OR_MODEL = "^[а-яА-ЯёЁa-zA-Z0-9 ()]+$";
	private static final String REGEX_PASS_SERIES = "^[A-ZА-Я]{0,2}$";
	private static final String REGEX_PASS_NUMBER = "^[0-9]{4,8}$";
	private static final String REGEX_FIO = "^[а-яА-Яa-zA-ZёЁ]{2,20}+$";

	public static void validateUserPassSeries(String userPassSeries) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_PASS_SERIES);
		Matcher matcher = pattern.matcher(userPassSeries);
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PASS_SERIES);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PASS_SERIES);
		}
	}

	public static void validateUserPassNumber(int userPassNumber) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_PASS_NUMBER);
		Matcher matcher = pattern.matcher(String.valueOf(userPassNumber));
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PASS_NUMBER);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PASS_NUMBER);
		}
	}

	public static void validateUserFIO(String userOneOfName) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_FIO);
		Matcher matcher = pattern.matcher(String.valueOf(userOneOfName));
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_FIO);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_FIO);
		}
	}

	// только для 14-летних
	public static void validateUserPassDateOfBirth(Date userPassDateOfBirth) throws ValidationException {

		double userPassDateOfBirthInMilis = userPassDateOfBirth.getTime();
		double currentDate = System.currentTimeMillis();
		double _14YearsInMilis = 1000 * 60 * 60 * 24 * 365.25 * 14; // 14 years
																	// in milis
		double availableYear = currentDate - _14YearsInMilis;
		if (userPassDateOfBirthInMilis > availableYear) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_DATE_OF_BIRTH);
		}
	}

	public static void validateSex(Sex userSex) throws ValidationException {
		for (Sex sex : Sex.values()) {
			if (userSex.toString().equals(sex.name())) {

				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_SEX);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_SEX);
	}

	public static void validateUserRole(UserRole userRole) throws ValidationException {
		for (UserRole role : UserRole.values()) {
			if (userRole.toString().equals(role.name())) {
				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_ROLE);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_ROLE);
	}

	public static void validateUserEmail(String userEmail) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(userEmail);
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_EMAIL);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_EMAIL);
		}
	}

	public static void validateUserPassword(String userPassword) throws ValidationException {
		if (userPassword.length() < 30) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PASSWORD);
		}
	}

	public static void validate2Passwords(String p1, String p2) throws ValidationException {
		if (!p1.equals(p2)) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_NOT_THE_SAME_PASSWORD);
		}
	}

	public static void validateRejectionReason(String rejectionReason) throws ValidationException {
		if (rejectionReason.length() < 2) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_REJECTION_REASON);
		}
	}

	// ===========================
	// вадидация входных параметров для авто
	// ===========================
	public static void validateCarBrend(String carBrend) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_CAR_BREND_OR_MODEL);
		Matcher matcher = pattern.matcher(carBrend);
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_BREND);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_BREND);
		}
	}

	public static void validateCarModel(String carModel) throws ValidationException {
		Pattern pattern = Pattern.compile(REGEX_CAR_BREND_OR_MODEL);
		Matcher matcher = pattern.matcher(carModel);
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_MODEL);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_MODEL);
		}
	}

	public static void validateCarYearOfIssue(Date carYearOfIssue) throws ValidationException {
		long currentTime = System.currentTimeMillis();
		if (carYearOfIssue.getTime() >= currentTime) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_YEAR_OF_ISSUE);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_YEAR_OF_ISSUE);
		}
	}

	public static void validateQualityCarClass(String carQualityClass) throws ValidationException {

		for (CarQualityClass c : CarQualityClass.values()) {
			if (carQualityClass.equals(c.name())) {
				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS);
	}

	public static void validateCarStatus(String carStatus) throws ValidationException {

		for (CarStatus s : CarStatus.values()) {
			if (carStatus.equals(s.name())) {
				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
	}

	public static void validateCarRentalCost(int price) throws ValidationException {
		if (price <= 0) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST);
		}
	}

	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_ID + " " + id);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_ID);
		}
	}

	public static void validateOrderDate(Date start, Date end) throws ValidationException {
		// проверка если введенные даты позже, чем сегодня
		long currentTime = System.currentTimeMillis();
		if ((start.getTime() < currentTime) || (end.getTime() < currentTime)) {
			LOG.error("Invalid input dates, early that today:" + start + " " + end);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_ORDER_DATE);
		}

		// проверка, если дата старта позже даты начала
		if (start.getTime() >= end.getTime()) {
			LOG.error("Invalid input dates, early that today:" + start + " " + end);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_ORDER_DATE);
		}
	}
	
	public static void validatePrice (int price) throws ValidationException {
		if (price <= 0) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_PRICE);
		}
	}
	
	public static void validateEnoughManyForRent (int need, int come) throws ValidationException {
		if (need > come ) {
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_NOT_ENOUGH_MANY_FOR_RENT);
		}
	}

}
