package com.dc.kq.pinche.dmo;

/**
 * 订单信息
 * 
 * @author xiaogang
 *
 */
public class CarInfo {
	/**
	 * 主键
	 */
	private long id;
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
	 * openId
	 */
	private String openId;
	/**
	 * 车照片
	 */
	private String image;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getPlates() {
		return plates;
	}
	public void setPlates(String plates) {
		this.plates = plates;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
