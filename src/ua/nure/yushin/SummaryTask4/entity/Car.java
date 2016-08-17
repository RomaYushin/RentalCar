package ua.nure.yushin.SummaryTask4.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Car extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227192958840835144L;

	private String carBrend;
	private String carModel;
	private CarQualityClass carQualityClass;
	private int carRentalCost;
	private CarStatus carStatus;
	private Date carYearOfIssue;
	private List<Date> carBusyDates = new ArrayList<Date>();

	public Car() {
		super();
	}

	public Car(String carBrend, String carModel, CarQualityClass carQualityClass, int carRentalCost,
			CarStatus carStatus, Date carYearOfIssue) {
		super();
		this.carBrend = carBrend;
		this.carModel = carModel;
		this.carQualityClass = carQualityClass;
		this.carRentalCost = carRentalCost;
		this.carStatus = carStatus;
		this.carYearOfIssue = carYearOfIssue;
		this.carBusyDates = new ArrayList<>();
	}

	public String getCarBrend() {
		return carBrend;
	}

	public void setCarBrend(String carBrend) {
		this.carBrend = carBrend;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public CarQualityClass getCarQualityClass() {
		return carQualityClass;
	}

	public void setCarQualityClass(CarQualityClass carQualityClass) {
		this.carQualityClass = carQualityClass;
	}

	public int getCarRentalCost() {
		return carRentalCost;
	}

	public void setCarRentalCost(int carRentalCost) {
		this.carRentalCost = carRentalCost;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public Date getCarYearOfIssue() {
		return carYearOfIssue;
	}

	public void setCarYearOfIssue(Date carYearOfIssue) {
		this.carYearOfIssue = carYearOfIssue;
	}

	public List<Date> getCarBusyDates() {
		return carBusyDates;
	}

	public void setCarBusyDates(List<Date> carBusyDates) {
		this.carBusyDates = carBusyDates;
	}

	@Override
	public String toString() {
		return "Car [carBrend=" + carBrend + ", carModel=" + carModel + ", carQualityClass=" + carQualityClass
				+ ", carRentalCost=" + carRentalCost + ", carStatus=" + carStatus + ", carYearOfIssue=" + carYearOfIssue
				+ ", carBusyDates=" + carBusyDates + "]";
	}

}
