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
	private  int code= 0;
	/**
	 * 返回信息
	 */
	private  String message;
	/**
	 * 返回内容
	 */
	private Object value;
	
	public void setEnum(ResponseEnum responseEnum){
		this.setCode(responseEnum.getCode());
		this.setMessage(responseEnum.getMemo());
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
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
