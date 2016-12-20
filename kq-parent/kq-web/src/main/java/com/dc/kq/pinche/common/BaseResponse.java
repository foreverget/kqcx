package com.dc.kq.pinche.common;
/**
 * 返回结果类
 * @author xiaogang
 *
 */
public class BaseResponse {
	/**
	 * 錯誤碼 0：正確  ，其他表示錯誤
	 */
	private  String code;
	/**
	 * 返回信息
	 */
	private  String message;
	/**
	 * 返回内容
	 */
	private Object value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
