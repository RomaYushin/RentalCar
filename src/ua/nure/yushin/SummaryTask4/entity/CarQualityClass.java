package ua.nure.yushin.SummaryTask4.entity;

public enum CarQualityClass {
	
	LUXURY, BUSINESS, ECONOMY;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static CarQualityClass getByName(String enumName) {
		for(CarQualityClass carQualityClass : values()) {
			if (carQualityClass.name().toLowerCase().equals(enumName.toLowerCase())) {
				return carQualityClass;
			}
		}
		
		return null;
	}

}
