package ua.nure.yushin.SummaryTask4.validators;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.command.admin.RegisterNewCarCommand;
import ua.nure.yushin.SummaryTask4.controller.Path;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;

public class ValidatorOfInputParameters {

	private static final Logger LOG = Logger.getLogger(ValidatorOfInputParameters.class);

	//private static final String PASS_SERIES_REGEX = "^([a-z0-9_\\-]+\\.)*[a-z0-9_\\-]+@([a-z0-9][a-z0-9\\-]*[a-z0-9]\\.)+[a-z]{2,6}$";
	private static final String EMAIL_REGEX = "^([a-z0-9_\\-]+\\.)*[a-z0-9_\\-]+@([a-z0-9][a-z0-9\\-]*[a-z0-9]\\.)+[a-z]{2,6}$";
	private static final String CAR_BREND_OR_MODEL_REGEX = "^[а-яА-ЯёЁa-zA-Z0-9 ()]+$";
	

	public static void validateUserPassSeries(String userPassSeries) {
	}

	public static void validateUserPassNumber(String userPassNumber) {
	}

	public static void validateUserFIO(String userOneOfName) {
	}

	public static void validateUserPassDateOfBirth(String userPassDateOfBirth) {
	}
	
	public static void validateSex(String userSex) throws ValidationException {
		for (Sex sex : Sex.values()) {
			if (userSex.equals(sex.name())) {
				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_SEX);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_SEX);
	}

	public static boolean validateUserEmail(String userUserEmail) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(userUserEmail);
		return matcher.matches();
	}

	public static boolean validateUserPassword(String userPassword) {
		if (userPassword.length() < 4 || userPassword.length() > 10) {
			return false;
		}
		return true;
	}

	public static boolean validateClientRegistrationParametrs() {
		return true;
	}

	// ===========================
	// вадидация входных параметров для авто
	// ===========================
	public static void validateCarBrend(String carBrend) throws ValidationException {
		Pattern pattern = Pattern.compile(CAR_BREND_OR_MODEL_REGEX);
		Matcher matcher = pattern.matcher(carBrend);
		if (!matcher.matches()) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_BREND);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_BREND);
		}
	}

	public static void validateCarModel(String carModel) throws ValidationException {
		Pattern pattern = Pattern.compile(CAR_BREND_OR_MODEL_REGEX);
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
	
		/*
		if (!carQualityClass.equals(CarQualityClass.BUSINESS.toString()) 
				& !carQualityClass.equals(CarQualityClass.LUXURY.toString())
				& !carQualityClass.equals(CarQualityClass.ECONOMY.toString())) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_QUALITY_CLASS);
		}
		*/
	}
	
	public static void validateCarStatus (String carStatus) throws ValidationException {
		
		for (CarStatus s : CarStatus.values()) {
			if (carStatus.equals(s.name())) {
				return;
			}
		}
		LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
		throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
	
		/*
		if (!carStatus.equals(CarStatus.FREE.toString()) 
				& !carStatus.equals(CarStatus.ON_REPAIR.toString())) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_STATUS);
		}
		*/
	}

	public static void validateCarRentalCost(int price) throws ValidationException {
		if (price <= 0) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_RENTAL_COST);
		}
	}
	
	public static void validateId (int removeCarId) throws ValidationException {
		if (removeCarId <= 0) {
			LOG.error(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_ID);
			throw new ValidationException(ExceptionMessages.EXCEPTION_VALIDATION_INVALID_CAR_ID);
		}
	}

}
