package com.dc.kq.pinche.request;

import java.math.BigDecimal;

import com.dc.kq.pinche.base.BaseEntity;

/**
 * 用户信息
 * 
 * @author xiaogang
 *
 */
public class UserInfoRequest extends BaseEntity {
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
	private String gender;

	/**
	 * 微信Id
	 */
	private String openId;
	/**
	 * 积分
	 */
	private BigDecimal score;

	private String key;

	private String value;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
