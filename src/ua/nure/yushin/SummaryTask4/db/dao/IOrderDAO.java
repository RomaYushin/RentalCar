package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.entity.User;

public interface IOrderDAO {

	void insertNewOrder(Order newOrder);
	
	void insertNewOrder(Order newOrder, List<Date> busyDates);

	List<Order> getAllOrdersFromDB();
	
	Order getOrderById (int orderId);
	
	int getAccountIdByOrderIdFromOrder (int orderId);
	
	boolean deleteOrderById(int orderId);
	
	void updateOrderCar(Car newOrderCar);
	
	void updateOrderClient (User newOrderClient);
	
	void updateOrderManager (User newOrderManager);
	
	void updateOrderPresenceOfTheDriver (boolean newOrderPresenceOfTheDriver);
	
	void updateOrderStartDate (Date newOrderStartDate);
	
	void updateOrderEndDate (Date newOrderEndDate);
	
	void updateOrderAccount (int newOrderAccount);
	
	void updateOrderStatus (OrderStatus newOrderStatus);
	
	void updateOrderRejectionReason(String newOrderRejectionReason);
	

}
