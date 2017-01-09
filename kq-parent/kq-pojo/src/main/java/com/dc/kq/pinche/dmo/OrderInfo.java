package com.dc.kq.pinche.dmo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 * 
 * @author xiaogang
 *
 */
public class OrderInfo {
	/**
	 * 主键
	 */
	private long id;
	/**
	 * 发布者
	 */
	private long userId;
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
	/**
	 * 车牌号
	 */
	private String plates;
	/**
	 * 拼车人数
	 */
	private int passengerNum;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 订单积分
	 */
	private BigDecimal score;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 版本号
	 */
	private int version;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人
	 */
	private String updateBy;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getPlates() {
		return plates;
	}
	public void setPlates(String plates) {
		this.plates = plates;
	}
	public int getPassengerNum() {
		return passengerNum;
	}
	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
