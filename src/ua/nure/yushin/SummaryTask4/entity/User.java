package ua.nure.yushin.SummaryTask4.entity;

import java.io.Serializable;
import java.sql.Date;

public class User extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1884561977997556208L;
	
	
	private String userPassSeries;
	private int userPassNumber;
	private String userPassSurname;
	private String userPassName;
	private String userPassPatronomic;
	private Date userPassDateOfBirth;
	private Sex userSex;
	private boolean userBlocking;
	private String userPassword;
	private String userEmail;	
	private UserRole userRole;
	private String userLanguage;
	private boolean userConfirmation;

	public User () {
	}
	
	public User(String userPassSeries, int userPassNumber, String userPassSurname, String userPassName,
			String userPassPatronomic, Date userPassDateOfBirth, Sex userSex, String userEmail, String userPassword,
			boolean userBlocking, UserRole userRole, String userLanguage, boolean userConfirmation) {
		super();
		this.userPassSeries = userPassSeries;
		this.userPassNumber = userPassNumber;
		this.userPassSurname = userPassSurname;
		this.userPassName = userPassName;
		this.userPassPatronomic = userPassPatronomic;
		this.userPassDateOfBirth = userPassDateOfBirth;
		this.userSex = userSex;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userBlocking = userBlocking;
		this.userRole = userRole;
		this.userLanguage = userLanguage;
		this.userConfirmation = userConfirmation;
	}

	public String getUserPassSeries() {
		return userPassSeries;
	}

	public void setUserPassSeries(String userPassSeries) {
		this.userPassSeries = userPassSeries;
	}

	public int getUserPassNumber() {
		return userPassNumber;
	}

	public void setUserPassNumber(int userPassNumber) {
		this.userPassNumber = userPassNumber;
	}

	public String getUserPassSurname() {
		return userPassSurname;
	}

	public void setUserPassSurname(String userPassSurname) {
		this.userPassSurname = userPassSurname;
	}

	public String getUserPassName() {
		return userPassName;
	}

	public void setUserPassName(String userPassName) {
		this.userPassName = userPassName;
	}

	public String getUserPassPatronomic() {
		return userPassPatronomic;
	}

	public void setUserPassPatronomic(String userPassPatronomic) {
		this.userPassPatronomic = userPassPatronomic;
	}

	public Date getUserPassDateOfBirth() {
		return userPassDateOfBirth;
	}

	public void setUserPassDateOfBirth(Date userPassDateOfBirth) {
		this.userPassDateOfBirth = userPassDateOfBirth;
	}

	public Sex getUserSex() {
		return userSex;
	}

	public void setUserSex(Sex userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isUserBlocking() {
		return userBlocking;
	}

	public void setUserBlocking(boolean userBlocking) {
		this.userBlocking = userBlocking;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public boolean isUserConfirmation() {
		return userConfirmation;
	}

	public void setUserConfirmation(boolean userConfirmation) {
		this.userConfirmation = userConfirmation;
	}

	@Override
	public String toString() {
		return "User [userPassSeries=" + userPassSeries + ", userPassNumber=" + userPassNumber + ", userPassSurname="
				+ userPassSurname + ", userPassName=" + userPassName + ", userPassPatronomic=" + userPassPatronomic
				+ ", userPassDateOfBirth=" + userPassDateOfBirth + ", userSex=" + userSex + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", userBlocking=" + userBlocking + ", userRole=" + userRole
				+ ", userLanguage=" + userLanguage + ", userConfirmation=" + userConfirmation + "]";
	}

	

}
