package com.dc.kq.pinche.dmo;

/**
 * 乘客订单关系表
 * 
 * @author xiaogang
 *
 */
public class OrderPassenger {
	/**
	 * 主键2
	 */
	private long id;
	/**
	 * 订单Id
	 */
	private long orderId;
	/**
	 * 用户Id
	 */
	private long userId;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 乘车人数
	 */
	private int count;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
