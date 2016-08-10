package ua.nure.yushin.SummaryTask4.entity;

import java.util.Date;

public class Order extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6596183658585193311L;

	private Car orderCar;
	private User orderClient;
	private User orderManager;
	private boolean orderPresenceOfTheDriver;
	private Date orderStartDate;
	private Date orderEndDate;
	private Account orderAccount;
	private OrderStatus orderStatus;
	private String orderRejectionReason;
	
	public Order(Car orderCar, User orderClient, User orderManager, boolean orderPresenceOfTheDriver,
			Date orderStartDate, Date orderEndDate, Account orderAccount) {
		super();
		this.orderCar = orderCar;
		this.orderClient = orderClient;
		this.orderManager = orderManager;
		this.orderPresenceOfTheDriver = orderPresenceOfTheDriver;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.orderAccount = orderAccount;
		this.orderStatus = OrderStatus.UNTREATED;
		this.orderRejectionReason = "no rejection reason";
	}

	public Car getOrderCar() {
		return orderCar;
	}

	public void setOrderCar(Car orderCar) {
		this.orderCar = orderCar;
	}

	public User getOrderClient() {
		return orderClient;
	}

	public void setOrderClient(User orderClient) {
		this.orderClient = orderClient;
	}

	public User getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(User orderManager) {
		this.orderManager = orderManager;
	}

	public boolean isOrderPresenceOfTheDriver() {
		return orderPresenceOfTheDriver;
	}

	public void setOrderPresenceOfTheDriver(boolean orderPresenceOfTheDriver) {
		this.orderPresenceOfTheDriver = orderPresenceOfTheDriver;
	}

	public Date getOrderStartDate() {
		return orderStartDate;
	}

	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public Account getOrderAccount() {
		return orderAccount;
	}

	public void setOrderAccount(Account orderAccount) {
		this.orderAccount = orderAccount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderRejectionReason() {
		return orderRejectionReason;
	}

	public void setOrderRejectionReason(String orderRejectionReason) {
		this.orderRejectionReason = orderRejectionReason;
	}

	@Override
	public String toString() {
		return "Order [orderCar=" + orderCar + ", orderClient=" + orderClient + ", orderManager=" + orderManager
				+ ", orderPresenceOfTheDriver=" + orderPresenceOfTheDriver + ", orderStartDate=" + orderStartDate
				+ ", orderEndDate=" + orderEndDate + ", orderAccount=" + orderAccount + ", orderStatus=" + orderStatus
				+ ", orderRejectionReason=" + orderRejectionReason + "]";
	}
	
	
	
}
