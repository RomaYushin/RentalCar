package ua.nure.yushin.SummaryTask4.entity;

public enum UserRole {

	ADMIN, MANAGER, CLIENT;

	public String getName() {
		return name().toLowerCase();
	}
	
	public static UserRole getByName(String enumName) {
		for(UserRole userRole : values()) {
			if (userRole.name().toLowerCase().equals(enumName.toLowerCase())) {
				return userRole;
			}
		}
		
		return null;
	}
}
