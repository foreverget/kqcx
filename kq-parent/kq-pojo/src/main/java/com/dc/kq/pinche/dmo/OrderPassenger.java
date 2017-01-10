package com.dc.kq.pinche.dmo;

import com.dc.kq.pinche.base.BaseEntity;

/**
 * 乘客订单关系表
 * 
 * @author xiaogang
 *
 */
public class OrderPassenger extends BaseEntity{
	/**
	 * 主键
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
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
