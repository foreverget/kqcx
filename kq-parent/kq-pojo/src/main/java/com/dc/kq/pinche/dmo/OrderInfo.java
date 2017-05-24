package com.dc.kq.pinche.dmo;

import java.math.BigDecimal;

import com.dc.kq.pinche.base.BaseEntity;

/**
 * 订单信息
 * 
 * @author xiaogang
 *
 */
public class OrderInfo extends BaseEntity {
	/**
	 * 主键
	 */
	private long id;
	/**
	 * 订单积分
	 */
	private BigDecimal score;
	/**
	 * 发布者
	 */
	private String openId;
	/**
	 * 发布者名称即司机名称
	 */
	private String name;
	/**
	 * 发布者手机号即司机电话
	 */
	private String mobile;
	/**
	 * 乘车时间(出发时间)
	 */
	private String goTime;
	/**
	 * 起点
	 */
	private String startAddr;
	/**
	 * 终点
	 */
	private String endAddr;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 车牌号
	 */
	private String plates;
	/**
	 * 拼车人数
	 */
	private int reqNum;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 订单状态 0：发布中，1：已发车，2：已送达，3：已取消
	 */
	private String status;
	/**
	 * 剩余座位数
	 */
	private int surplusSeat;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId
	 *            the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the goTime
	 */
	public String getGoTime() {
		return goTime;
	}

	/**
	 * @param goTime
	 *            the goTime to set
	 */
	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	/**
	 * @return the startAddr
	 */
	public String getStartAddr() {
		return startAddr;
	}

	/**
	 * @param startAddr
	 *            the startAddr to set
	 */
	public void setStartAddr(String startAddr) {
		this.startAddr = startAddr;
	}

	/**
	 * @return the endAddr
	 */
	public String getEndAddr() {
		return endAddr;
	}

	/**
	 * @param endAddr
	 *            the endAddr to set
	 */
	public void setEndAddr(String endAddr) {
		this.endAddr = endAddr;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the plates
	 */
	public String getPlates() {
		return plates;
	}

	/**
	 * @param plates
	 *            the plates to set
	 */
	public void setPlates(String plates) {
		this.plates = plates;
	}

	/**
	 * @return the reqNum
	 */
	public int getReqNum() {
		return reqNum;
	}

	/**
	 * @param reqNum
	 *            the reqNum to set
	 */
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public int getSurplusSeat() {
		return surplusSeat;
	}

	public void setSurplusSeat(int surplusSeat) {
		this.surplusSeat = surplusSeat;
	}

}
