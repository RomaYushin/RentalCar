package ua.nure.yushin.SummaryTask4.entity;

public enum Sex {
	
	MALE, FEMALE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static Sex getByName(String enumName) {
		for(Sex sex : values()) {
			if (sex.name().toLowerCase().equals(enumName.toLowerCase())) {
				return sex;
			}
		}
		
		return null;
	}
}
