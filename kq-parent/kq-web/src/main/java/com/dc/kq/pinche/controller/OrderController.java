package com.dc.kq.pinche.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.request.OrderInfoRequest;
import com.dc.kq.pinche.service.OrderService;

/**
 * 订单controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 跳转到出车发布页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toRelease")
	public String toRelease(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/release";
	}

	/**
	 * 跳转到乘车页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toTake")
	public String toTake(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/take";
	}

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
	 * 出车发布请求
	 * 
	 * @param orderInfoRequest
	 * @return
	 */
	@RequestMapping("release")
	@ResponseBody
	public BaseResponse release(@RequestBody OrderInfoRequest orderInfoRequest) {
		return orderService.doReleaseOrder(orderInfoRequest);
	}

	/**
	 * 我要约车列表页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("getOrderList")
	@ResponseBody
	public BaseResponse getOrderList(HttpServletRequest request, String openId) {
		BaseResponse r = new BaseResponse();
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		String dateType = request.getParameter("dateType");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("size", size);
		List<OrderInfo> orderList = orderService.findReleaseOrderList(params, dateType);
		request.setAttribute("orderList", orderList);
		r.setValue(orderList);
		return r;
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
	@RequestMapping("bookOrder.json")
	@ResponseBody
	public BaseResponse bookOrder(String userId, String orderId, String count, String keyValue) {
		return orderService.doBookOrder(userId, orderId, count);
	}

	/**
	 * 跳转到我的订单页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toMyOrder")
	public String toMyOrder(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/myOrder";
	}

	/**
	 * 跳转到我的约车单页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toMyTakeOrder")
	public String toMyTakeOrder(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/myTakeOrder";
	}

	/**
	 * 跳转到我的历史订单页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toMyHistoryOrder")
	public String toMyHistoryOrder(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/myHistoryOrder";
	}
}
