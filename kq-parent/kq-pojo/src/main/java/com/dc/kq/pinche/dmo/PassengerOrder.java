package com.dc.kq.pinche.dmo;

import java.math.BigDecimal;

/**
 * 乘客订单信息
 * 
 * @author david
 *
 */
public class PassengerOrder {

	/**
	 * 订单ID
	 */
	private long orderId;
	/**
	 * 发布者ID
	 */
	private long driverId;
	/**
	 * 乘客ID
	 */
	private long passengerId;
	/**
	 * 发布者名称即司机名称
	 */
	private String driverName;
	/**
	 * 发布者手机号即司机电话
	 */
	private String mobile;
	/**
	 * 乘车时间(出发时间)
	 */
	private String takeTime;
	/**
	 * 起点
	 */
	private String start;
	/**
	 * 终点
	 */
	private String end;
	
	// 车辆信息
	/**
	 * 车型
	 */
	private String models;
	/**
	 * 车牌
	 */
	private String plates;
	/**
	 * 座位数
	 */
	private int seat;
	/**
	 * 颜色
	 */
	
	private String color;
	/**
	 * 剩余座位数
	 */
	private int passengerNum;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 发布者订单状态
	 */
	private String driverOrderStatus;
	/**
	 * 乘客订单状态
	 */
	private String passengerOrderStatus;
	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the driverId
	 */
	public long getDriverId() {
		return driverId;
	}
	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	/**
	 * @return the passengerId
	 */
	public long getPassengerId() {
		return passengerId;
	}
	/**
	 * @param passengerId the passengerId to set
	 */
	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the takeTime
	 */
	public String getTakeTime() {
		return takeTime;
	}
	/**
	 * @param takeTime the takeTime to set
	 */
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * @return the models
	 */
	public String getModels() {
		return models;
	}
	/**
	 * @param models the models to set
	 */
	public void setModels(String models) {
		this.models = models;
	}
	/**
	 * @return the plates
	 */
	public String getPlates() {
		return plates;
	}
	/**
	 * @param plates the plates to set
	 */
	public void setPlates(String plates) {
		this.plates = plates;
	}
	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}
	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the passengerNum
	 */
	public int getPassengerNum() {
		return passengerNum;
	}
	/**
	 * @param passengerNum the passengerNum to set
	 */
	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the driverOrderStatus
	 */
	public String getDriverOrderStatus() {
		return driverOrderStatus;
	}
	/**
	 * @param driverOrderStatus the driverOrderStatus to set
	 */
	public void setDriverOrderStatus(String driverOrderStatus) {
		this.driverOrderStatus = driverOrderStatus;
	}
	/**
	 * @return the passengerOrderStatus
	 */
	public String getPassengerOrderStatus() {
		return passengerOrderStatus;
	}
	/**
	 * @param passengerOrderStatus the passengerOrderStatus to set
	 */
	public void setPassengerOrderStatus(String passengerOrderStatus) {
		this.passengerOrderStatus = passengerOrderStatus;
	}
	
	
}
