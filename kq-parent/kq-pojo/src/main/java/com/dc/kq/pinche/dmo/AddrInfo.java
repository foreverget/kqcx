package com.dc.kq.pinche.dmo;

/**
 * 地址信息
 * 
 * @author xiaogang
 *
 */
public class AddrInfo {
	/**
	 * 主键
	 */
	private long id;
	/**
	 * 起点
	 */
	private String start;
	/**
	 * 终点
	 */
	private String end;
	/**
	 * openId
	 */
	private String openId;
	/**
	 * 是否默认（0：否，1：是），暂时无用
	 */
	private int isDefault;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
