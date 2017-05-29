package com.dc.kq.pinche.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.CarInfo;
import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;
import com.dc.kq.pinche.dmo.UserInfo;
import com.dc.kq.pinche.request.OrderInfoRequest;
import com.dc.kq.pinche.service.CarService;
import com.dc.kq.pinche.service.OrderService;
import com.dc.kq.pinche.service.UserService;

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

	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;
	

	/**
	 * 跳转到出车发布页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toRelease")
	public String toRelease(HttpServletRequest request, String openId) {
		// 根据openid获取 姓名 及 电话、车牌号码
		UserInfo userInfo = userService.selectUserByOpenId(openId);
		// 账户不可用
		if(userInfo==null || userInfo.getStatus()!=1){
			return "user/error";
		}
		List<CarInfo> carInfoList = carService.getCarList(openId);
		if(null!=carInfoList&&carInfoList.size()==1){
			CarInfo carInfo = carInfoList.get(0);
			request.setAttribute("plates", carInfo.getPlates());
		}
		request.setAttribute("openId", openId);
		request.setAttribute("name", userInfo.getName());
		request.setAttribute("mobile", userInfo.getMobile());
		return "order/release";
	}

	/**
	 * 跳转到乘车页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toTakeOld")
	public String toTakeOld(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/takeOld";
	}
	
	@RequestMapping("toTake")
	public String toTake(HttpServletRequest request, String openId) {
		// 根据openid获取 姓名 及 电话、车牌号码
		UserInfo userInfo = userService.selectUserByOpenId(openId);
		// 账户不可用
		if(userInfo==null || userInfo.getStatus()!=1){
			return "user/error";
		}
		request.setAttribute("openId", openId);
		return "order/take";
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

	/**
	 * 我的约车单
	 * 
	 * @param request
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 *            0：历史订单，1：今天，2：明天，3：后天
	 * @return
	 */
	@RequestMapping("getYcOrder")
	@ResponseBody
	public BaseResponse getYcOrderList(HttpServletRequest request, int page, int size, String openId, int type) {
		BaseResponse resp = orderService.getYcOrderList(page, size, openId, type);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 跳转到我得约车单详情页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toYcOrderDetail")
	public String toYcOrderDetail(HttpServletRequest request, long orderId, String openId) {
		// 根据orderId 查询order信息
		OrderInfo orderInfo = orderService.getOrderDetail(orderId);
		request.setAttribute("openId", openId);
		request.setAttribute("order", orderInfo);
		return "order/ycOrderDetail";
	}

	/**
	 * 跳转到我的出车单[3天内]的页面
	 * 
	 * @param request
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 * @return
	 */
	@RequestMapping("toMyReleaseOrder")
	public String myReleaseOrder(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "order/myReleaseOrder";
	}

	/**
	 * 我的出车单
	 * 
	 * @param request
	 * @param page
	 * @param size
	 * @param openId
	 * @return
	 */
	@RequestMapping("getCcOrder")
	@ResponseBody
	public BaseResponse getCcOrderList(HttpServletRequest request, int page, int size, String openId, int type) {
		BaseResponse resp = orderService.getCcOrderList(page, size, openId, type);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 跳转到我的出车单详情页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toReleaseOrderDetail")
	public String toReleaseOrderDetail(HttpServletRequest request, long orderId, String openId) {
		// 根据orderId 查询order信息
		OrderInfo orderInfo = orderService.getOrderDetail(orderId);
		// 通过orderId查询乘客信息
		List<OrderPassenger> opList = orderService.getPassengerList(orderId);
		request.setAttribute("openId", openId);
		request.setAttribute("order", orderInfo);
		request.setAttribute("opList", opList);
		return "order/releaseOrderDetail";
	}

	/**
	 * 跳转到历史订单----我的出车单详情页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toCcOrderDetail")
	public String toCcOrderDetail(HttpServletRequest request, long orderId, String openId) {
		// 根据orderId 查询order信息
		OrderInfo orderInfo = orderService.getOrderDetail(orderId);
		// 通过orderId查询乘客信息
		List<OrderPassenger> opList = orderService.getPassengerList(orderId);
		request.setAttribute("openId", openId);
		request.setAttribute("order", orderInfo);
		request.setAttribute("opList", opList);
		return "order/ccOrderDetail";
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param orderId
	 * @param openId
	 * @return
	 */
	@RequestMapping("channelOrder")
	@ResponseBody
	public BaseResponse channelOrder(HttpServletRequest request, long orderId, String openId) {
		BaseResponse resp = orderService.channelOrder(openId, orderId);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 客满发车
	 * 
	 * @param request
	 * @param orderId
	 * @param openId
	 * @return
	 */
	@RequestMapping("subReleaseOrder")
	@ResponseBody
	public BaseResponse subReleaseOrder(HttpServletRequest request, long orderId, String openId) {
		BaseResponse resp = orderService.subReleaseOrder(openId, orderId);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 移除乘客
	 * 
	 * @param request
	 * @param orderId
	 * @param openId
	 * @param opId
	 *            被移除乘客openId
	 * @return
	 */
	@RequestMapping("removePassenger")
	@ResponseBody
	public BaseResponse removePassenger(HttpServletRequest request, long orderId, String openId, String opId) {
		BaseResponse resp = orderService.removePassenger(orderId, openId, opId);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 获取我要约车页面列表
	 * 
	 * @param request
	 * @param page
	 * @param size
	 * @param openId
	 * @param type
	 * @return
	 */
	@RequestMapping("getTakeOrderList")
	@ResponseBody
	public BaseResponse getTakeOrderList(HttpServletRequest request, int page, int size, String openId, int type) {
		BaseResponse resp = orderService.getTakeOrderList(page, size, openId, type);
		resp.setOpenId(openId);
		return resp;
	}

	/**
	 * 跳转到我要约车单详情页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toTakeOrderDetail")
	public String toTakeOrderDetail(HttpServletRequest request, long orderId, String openId) {
		// 根据orderId 查询order信息
		OrderInfo orderInfo = orderService.getOrderDetail(orderId);
		request.setAttribute("openId", openId);
		request.setAttribute("order", orderInfo);
		return "order/takeOrderDetail";
	}

	/**
	 * 我约
	 * 
	 * @param request
	 * @param orderId
	 * @param openId
	 * @param count
	 * @param version
	 * @return
	 */
	@RequestMapping("takeOrder")
	@ResponseBody
	public BaseResponse takeOrder(HttpServletRequest request, Long orderId, String openId, Integer count, Integer version) {
		return orderService.takeOrder(orderId, openId, count, version);
	}
}
