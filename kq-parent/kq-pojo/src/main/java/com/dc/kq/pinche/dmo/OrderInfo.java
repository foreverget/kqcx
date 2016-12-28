package com.dc.kq.pinche.dmo;

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
	 * 乘车时间
	 */
	private long takeTime;
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
	private int personNum;
	/**
	 * 单价
	 */
	private int price;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 订单积分
	 */
	private int score;
	/**
	 * 创建时间
	 */
	private long createTime;
	/**
	 * 创建人
	 */
	private long createBy;
	/**
	 * 版本号
	 */
	private long version;
	/**
	 * 更新时间
	 */
	private long updateTime;
	/**
	 * 更新人
	 */
	private long updateBy;
	
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
	public long getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(long takeTime) {
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
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}
}
