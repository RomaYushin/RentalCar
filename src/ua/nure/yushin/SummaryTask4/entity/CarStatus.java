package ua.nure.yushin.SummaryTask4.entity;

public enum CarStatus {
	
	FREE, ON_REPAIR;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static CarStatus getByName(String enumName) {
		for(CarStatus carStatus : values()) {
			if (carStatus.name().toLowerCase().equals(enumName.toLowerCase())) {
				return carStatus;
			}
		}
		
		return null;
	}
}
