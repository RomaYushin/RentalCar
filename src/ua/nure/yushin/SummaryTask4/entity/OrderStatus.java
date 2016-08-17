package ua.nure.yushin.SummaryTask4.entity;

public enum OrderStatus {

	UNTREATED, ACTIVE, CLOSE, REJECTED;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static OrderStatus getByName(String enumName) {
		for(OrderStatus orderStatus : values()) {
			if (orderStatus.name().toLowerCase().equals(enumName.toLowerCase())) {
				return orderStatus;
			}
		}
		
		return null;
	}
	
}
