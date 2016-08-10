package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.util.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.entity.User;

public class MySQLOrderDAO implements IOrderDAO {

	@Override
	public void insertNewOrder(Order newOrder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Order> getAllOrdersFromDB() {
		// throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public void updateOrderCar(Car newOrderCar) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderClient(User newOrderClient) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderManager(User newOrderManager) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderPresenceOfTheDriver(boolean newOrderPresenceOfTheDriver) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderStartDate(Date newOrderStartDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderEndDate(Date newOrderEndDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderAccount(int newOrderAccount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderStatus(OrderStatus newOrderStatus) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderRejectionReason(String newOrderRejectionReason) {
		throw new UnsupportedOperationException();
	}
	
}
