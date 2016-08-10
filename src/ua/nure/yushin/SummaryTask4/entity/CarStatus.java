package ua.nure.yushin.SummaryTask4.entity;

public enum CarStatus {
	FREE, BUSY, ON_REPAIR, BOOKED;
	
	public CarStatus getBy() {
		for(CarStatus c : values()) {
			//c.name().toLowerCase().equals(arg0)
		}
		return null;
	}
}
