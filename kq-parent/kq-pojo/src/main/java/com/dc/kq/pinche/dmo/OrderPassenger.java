package com.dc.kq.pinche.dmo;

import com.dc.kq.pinche.base.BaseEntity;

/**
 * 乘客订单关系表
 * 
 * @author xiaogang
 *
 */
public class OrderPassenger extends BaseEntity {
	/**
	 * 主键
	 */
	private long id;
	/**
	 * 订单Id
	 */
	private long orderId;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 乘车人数
	 */
	private int count;
	/**
	 * 乘客名称
	 */
	private String name;
	/**
	 * 乘客手机号
	 */
	private String mobile;
	/**
	 * WXID
	 */
	private String openId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
