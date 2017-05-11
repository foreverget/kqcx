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
			"未找到相关记录"), OPERATION_ULTRA_VIRES(90004, "没有操作权限"), NO_ORDER(90005, "未找到订单"), ORDER_VERSION_ERROR(90006,
					"订单数据已被其他人更新"), NO_SEAT(90007, "没有足够的剩余座位数"), ALREADY_HAS_THIS_ORDER(90008,
							"您已经是这个订单的乘客了，不能重复下单！"), TAKE_ERROR(10001, "系统出错啦,约车失败!"), GET_TAKE_LIST_ERROR(10002,
									"获取列表信息异常!"), GET_TAKE_DETAIL_ERROR(10002, "获取约车单详情异常!");

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
