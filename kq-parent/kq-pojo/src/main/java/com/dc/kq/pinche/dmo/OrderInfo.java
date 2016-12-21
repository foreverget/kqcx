package com.dc.kq.pinche.dmo;

/**
 * 车辆信息
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
	private long owner;
	/**
	 * 发布者名称
	 */
	private String name;
	/**
	 * 发布者手机号
	 */
	private String mobile;
	/**
	 * 乘车时间
	 */
	private long time;
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
	 * 人数
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
	 * 积分
	 */
	private int score;
	/**
	 * 发布时间
	 */
	private long createTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
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
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
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
}
