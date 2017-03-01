package com.dc.kq.pinche.request;

/**
 * 用户信息
 * 
 * @author xiaogang
 *
 */
public class UserInfoRequest {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 常用地址
	 */
	private String addr;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 性别
	 */
	private int gender;

	/**
	 * 微信Id
	 */
	private String openId;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
