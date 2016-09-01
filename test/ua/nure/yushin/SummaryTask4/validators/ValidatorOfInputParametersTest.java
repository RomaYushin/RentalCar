package ua.nure.yushin.SummaryTask4.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushin.SummaryTask4.exception.ValidationException;

public class ValidatorOfInputParametersTest {

	@Test 
	public final void testValidateUserPassSeries() throws ValidationException {		
		
		List <String> arrayPassSeries = new ArrayList<>();
		arrayPassSeries.add("АА");
		arrayPassSeries.add("МН");
		arrayPassSeries.add("ZS");
		
		for (String s : arrayPassSeries) {
			ValidatorOfInputParameters.validateUserPassSeries(s);
		}		
	}

	@Test (expected = ValidationException.class)
	public final void testValidateUserPassSeries_shouldThrowException () throws ValidationException {		
		String failUserPassSeries = "d23";
		ValidatorOfInputParameters.validateUserPassSeries(failUserPassSeries);		
	}
	
	@Test
	public final void testValidateUserPassNumber() throws ValidationException {
		
		List <Integer> arrayPassNumber = new ArrayList<>();
		arrayPassNumber.add(1234);
		arrayPassNumber.add(12345);
		arrayPassNumber.add(123456);
		
		for (Integer i : arrayPassNumber) {
			ValidatorOfInputParameters.validateUserPassNumber(i);
		}
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateUserPassNumber_shouldThrowException () throws ValidationException {		
		String failUserPassNumber = "d23";
		ValidatorOfInputParameters.validateUserPassSeries(failUserPassNumber);		
	}

	@Test
	public final void testValidateUserFIO () throws ValidationException {
		
		List <String> arrayFIO = new ArrayList<>();
		arrayFIO.add("John");
		arrayFIO.add("Андреевич");
		arrayFIO.add("Волошин");
		
		for (String s : arrayFIO) {
			ValidatorOfInputParameters.validateUserFIO(s);
		}
	}

	@Test (expected = ValidationException.class)
	public final void testValidateUserFIO_shouldThrowException () throws ValidationException {		
		String failUserFIO = "d23";
		ValidatorOfInputParameters.validateUserPassSeries(failUserFIO);		
	}
	
	@Test
	public final void testValidateUserPassDateOfBirth() throws ValidationException {

		List <Date> arrayDates = new ArrayList<>();
		arrayDates.add(new Date(Date.valueOf("1990-05-26").getTime()));
		arrayDates.add(new Date(Date.valueOf("1992-02-26").getTime()));
		arrayDates.add(new Date(Date.valueOf("1995-02-26").getTime()));
		
		for (Date d : arrayDates) {
			ValidatorOfInputParameters.validateUserPassDateOfBirth(d);
		}		
	}

	@Test (expected = ValidationException.class)
	public final void testValidateUserPassDateOfBirth_shouldThrowException () throws ValidationException {	
		
		double currentDate = System.currentTimeMillis();		
		double _13YearsInMilis = 1000 * 60 * 60 * 24 * 365.25 * 13; // 13 years
		
		Date failDate = new Date ((long) (currentDate - _13YearsInMilis));		
		ValidatorOfInputParameters.validateUserPassDateOfBirth(failDate);		
	}
	
	@Test
	public final void testValidateSex() throws ValidationException {
		for (Sex s : Sex.values()) {
			ValidatorOfInputParameters.validateSex(s);
		}
	}

	@Test
	public final void testValidateUserRole() throws ValidationException {
		for (UserRole ur : UserRole.values()) {
			ValidatorOfInputParameters.validateUserRole(ur);
		}
	}

	@Test
	public final void testValidateUserEmail() throws ValidationException {
		
		List <String> arrayEmail = new ArrayList<>();
		arrayEmail.add("abc-b@yandex.ru");
		arrayEmail.add("abc@gmail.com");
		arrayEmail.add("abc.abc@rambler.ru");
		
		for (String e : arrayEmail) {
			ValidatorOfInputParameters.validateUserEmail(e);
		}
	}	
	
	@Test (expected = ValidationException.class)
	public final void testValidateUserEmail_shouldThrowException () throws ValidationException {		
		String failUserEmail = "d23";
		ValidatorOfInputParameters.validateUserEmail (failUserEmail);		
	}

	@Test
	public final void testValidateUserPassword() throws ValidationException {		

		List <String> arrayPassword = new ArrayList<>();
		arrayPassword.add(DigestUtils.md5Hex("1234"));
		arrayPassword.add(DigestUtils.md5Hex("123456"));
		arrayPassword.add(DigestUtils.md5Hex("password"));
		
		for (String p : arrayPassword) {
			ValidatorOfInputParameters.validateUserPassword(p);
		}		
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateUserPassword_shouldThrowException () throws ValidationException {		
		String failUserPassword = "d2fv3";
		ValidatorOfInputParameters.validateUserPassword (failUserPassword);		
	}

	@Test
	public final void testValidate2Passwords() throws ValidationException {
		
		List <String> array2Password = new ArrayList<>();
		array2Password.add("pass1");
		array2Password.add("pass2");
		array2Password.add("pass3");
		
		for (String a2p : array2Password) {
			ValidatorOfInputParameters.validate2Passwords(a2p, a2p);
		}	
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidate2Passwords_shouldThrowException () throws ValidationException {		
		String failUserPassword1 = "d2fv3";
		String failUserPassword2 = "d254fv3";
		ValidatorOfInputParameters.validate2Passwords ( failUserPassword1, failUserPassword2);		
	}

	@Test
	public final void testValidateRejectionReason() throws ValidationException {

		List <String> arrayRejReas = new ArrayList<>();
		arrayRejReas.add("rejReason1");
		arrayRejReas.add("rejReason2");
		arrayRejReas.add("rejReason3");
		
		for (String aRR : arrayRejReas) {
			ValidatorOfInputParameters.validateRejectionReason(aRR);
		}	
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateRejectionReason_shouldThrowException () throws ValidationException {		
		String failRejReas = "f";
		ValidatorOfInputParameters.validateRejectionReason ( failRejReas);		
	}

	@Test
	public final void testValidateCarBrend () throws ValidationException {
		
		List <String> arrayCarBrend = new ArrayList<>();
		arrayCarBrend.add("Toyota");
		arrayCarBrend.add("Audi");
		arrayCarBrend.add("Porsche");
		
		for (String aCB :arrayCarBrend) {
			ValidatorOfInputParameters.validateCarBrend(aCB);
		}	
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateCarBrend_shouldThrowException () throws ValidationException {		
		String failCarBrend = "#Toyota@";
		ValidatorOfInputParameters.validateCarBrend ( failCarBrend );		
	}

	@Test
	public final void testValidateCarModel() throws ValidationException {
		
		List <String> arrayCarModel = new ArrayList<>();
		arrayCarModel.add("Auris");
		arrayCarModel.add("Q8");
		arrayCarModel.add("911 turbo");
		
		for (String aCM :arrayCarModel) {
			ValidatorOfInputParameters.validateCarModel(aCM);
		}	
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateCarModel_shouldThrowException () throws ValidationException {		
		String failCarModel = "#911";
		ValidatorOfInputParameters.validateCarBrend ( failCarModel );		
	}

	@Test
	public final void testValidateCarYearOfIssue() throws ValidationException {
		
		List <Date> arrayDates = new ArrayList<>();
		arrayDates.add(new Date(Date.valueOf("2015-05-26").getTime()));
		arrayDates.add(new Date(Date.valueOf("2012-02-26").getTime()));
		arrayDates.add(new Date(Date.valueOf("1990-02-26").getTime()));
		
		for (Date d : arrayDates) {
			ValidatorOfInputParameters.validateCarYearOfIssue(d);
		}	
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateCarYearOfIssue_shouldThrowException () throws ValidationException {		
		Date currentDate = new Date (System.currentTimeMillis());
		
		ValidatorOfInputParameters.validateCarYearOfIssue ( currentDate );		
	}

	@Test
	public final void testValidateQualityCarClass() throws ValidationException {
		for (CarQualityClass cQC : CarQualityClass.values()) {
			ValidatorOfInputParameters.validateQualityCarClass(cQC);
		}
	}

	@Test
	public final void testValidateCarStatus() throws ValidationException {
		for (CarStatus cS : CarStatus.values()) {
			ValidatorOfInputParameters.validateCarStatus(cS);
		}
	}

	@Test
	public final void testValidateCarRentalCost() throws ValidationException {
		
		List <Integer> arrayCarRentalCost = new ArrayList<>();
		arrayCarRentalCost.add(100);
		arrayCarRentalCost.add(200);
		arrayCarRentalCost.add(300);
		
		for (Integer aCRC : arrayCarRentalCost) {
			ValidatorOfInputParameters.validateCarRentalCost(aCRC);
		}	
	}	
	
	@Test (expected = ValidationException.class)
	public final void testValidateCarRentalCost_shouldThrowException () throws ValidationException {		
		int failCarRentalCost = -100;		
		ValidatorOfInputParameters.validateCarRentalCost ( failCarRentalCost );	
	}

	@Test
	public final void testValidateId() throws ValidationException {
		
		List <Integer> arrayValidateId = new ArrayList<>();
		arrayValidateId.add(1);
		arrayValidateId.add(2);
		arrayValidateId.add(3);
		
		for (Integer aVI : arrayValidateId) {
			ValidatorOfInputParameters.validateId(aVI);
		}
	}

	@Test (expected = ValidationException.class)
	public final void testValidateId_shouldThrowException () throws ValidationException {		
		int failId = -1;		
		ValidatorOfInputParameters.validateCarRentalCost (failId);	
	}
	
	@Test
	public final void testValidateOrderDate() throws ValidationException {
		
		long currentTime = System.currentTimeMillis();
		long tomorrowTime = currentTime + (1000*60*60*24);
		long theDayAfterTommorowTime =  currentTime + (1000*60*60*24)*2;
		Date start = new Date(tomorrowTime);
		Date end = new Date (theDayAfterTommorowTime);
		
		ValidatorOfInputParameters.validateOrderDate(start, end);
		
	}

	@Test (expected = ValidationException.class)
	public final void testValidateOrderDate_shouldThrowException () throws ValidationException {		

		long currentTime = System.currentTimeMillis();
		long yestardayTime = currentTime - (1000*60*60*24);
		long theDayAfterTommorowTime =  currentTime + (1000*60*60*24)*2;
		Date start = new Date(yestardayTime);
		Date end = new Date (theDayAfterTommorowTime);		
		ValidatorOfInputParameters.validateOrderDate(start, end);	
	}
	
	@Test
	public final void testValidatePrice() throws ValidationException {
		
		List <Integer> arrayValidatePrice = new ArrayList<>();
		arrayValidatePrice.add(1000);
		arrayValidatePrice.add(2000);
		arrayValidatePrice.add(3000);
		
		for (Integer aVP : arrayValidatePrice) {
			ValidatorOfInputParameters.validatePrice (aVP);
		}
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidatePrice_shouldThrowException () throws ValidationException {		
		int failPrice = -1;		
		ValidatorOfInputParameters.validateCarRentalCost (failPrice);	
	}

	@Test
	public final void testValidateEnoughManyForRent() throws ValidationException {
		int need = 200;
		int come = 201;
		ValidatorOfInputParameters.validateEnoughManyForRent(need, come);
	}
	
	@Test (expected = ValidationException.class)
	public final void testValidateEnoughManyForRent_shouldThrowException () throws ValidationException {		
		int need = 200;
		int come = 199;
		ValidatorOfInputParameters.validateEnoughManyForRent(need, come);
	}

}
