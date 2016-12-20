package com.dc.kq.pinche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;

/**
 * 订单controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/order/")
public class OrderController {
	/**
	 * 用户订单查询
	 * 
	 * @param userId
	 * @param userType
	 * @param dateType
	 * @param startPage
	 * @param keyValue
	 * @return
	 */
	@RequestMapping("userOrderList.json")
	@ResponseBody
	public BaseResponse userOrderList(long userId, int userType, int dateType, int startPage, String keyValue) {
		return null;
	}

	/**
	 * 操作订单
	 * 
	 * @param orderId
	 * @param type
	 * @param opUserId
	 * @param keyValue
	 * @return
	 */
	@RequestMapping("optionOrder.json")
	@ResponseBody
	public BaseResponse optionOrder(long orderId, int type, long opUserId, String keyValue) {
		return null;
	}

	/**
	 * 出车发布
	 * 
	 * @param orderId
	 * @param type
	 * @param opUserId
	 * @param keyValue
	 * @return
	 */
	@RequestMapping("releaseOrder.json")
	@ResponseBody
	public BaseResponse releaseOrder(@RequestBody OrderInfo orderInfo, String keyValue) {
		return null;
	}

	/**
	 * 我要约车列表页面
	 * 
	 * @param orderInfo
	 * @param keyValue
	 * @return
	 */
	@RequestMapping("orderList.json")
	@ResponseBody
	public BaseResponse orderList(@RequestBody OrderInfo orderInfo, String keyValue) {
		return null;
	}

	/**
	 * 乘客约车
	 * 
	 * @param userId
	 * @param orderId
	 * @param count
	 * @param keyValue
	 * @return
	 */
	@RequestMapping("firmOrder.json")
	@ResponseBody
	public BaseResponse firmOrder(long userId, long orderId, int count, String keyValue) {
		return null;
	}
}
