package com.dc.kq.pinche.common;

/**
 * 请求异常的代码
 * 
 * @author xiaogang
 *
 */
public enum ResponseEnum {  
	/**
	 * 以9开头，系统公用提示
	 */
	OPERATE_DB_FAIL(90001, "操作数据库失败"), SYSTEM_EXCEPTION(90002, "系统异常"), LIST_EMPTY(90003,
			"未找到相关记录"), OPERATION_ULTRA_VIRES(90004, "没有操作权限");

	/**
	 * 错误编码
	 */
	private int code;
	/**
	 * 错误描述
	 */
	private String memo;

	ResponseEnum(int code, String memo) {
		this.code = code;
		this.memo = memo;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
