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
import com.dc.kq.pinche.service.CarService;

/**
 * 车辆controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/car/")
public class CarController {
	@Autowired
	private CarService carService;

	/**
	 * 跳转到我的车辆信息页面
	 * 
	 * @param openId
	 * @return
	 */
	@RequestMapping("toMyCarList")
	public String toMyCarList(HttpServletRequest request, String openId) {
		// 获取车辆列表
		List<CarInfo> list = carService.getCarList(openId);
		request.setAttribute("list", list);
		request.setAttribute("openId", openId);
		return "car/myCarList";
	}

	/**
	 * 跳转到新增车辆信息页面
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("toAddCar")

	public String toAddCar(HttpServletRequest request, String openId) {
		request.setAttribute("openId", openId);
		return "car/addCar";
	}

	/**
	 * 跳转到新增车辆信息页面
	 * 
	 * @param request
	 * @param openId
	 * @param id
	 *            汽车Id
	 * @return
	 */
	@RequestMapping("toUpdateCar")
	public String toUpdateCar(HttpServletRequest request, String openId, long id) {
		// 根据汽车Id和openId查询车辆信息
		CarInfo carInfo = carService.getCarInfoByParam(openId, id);
		request.setAttribute("openId", openId);
		request.setAttribute("carInfo", carInfo);
		return "car/editCar";
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param carInfo
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("saveCar")
	@ResponseBody
	public BaseResponse saveCar(@RequestBody CarInfo carInfo) {
		return carService.save(carInfo);
	}

	/**
	 * 删除地车辆信息
	 * 
	 * @param userId
	 * @param id
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public BaseResponse delete(long userId, long id, String valueKey) {
		return carService.delete(userId, id);
	}

	/**
	 * 出车发布页面--获取用户的车辆信息
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("selectCarList")
	@ResponseBody
	public BaseResponse selectCarList(HttpServletRequest request, String openId) {
		return carService.selectCarList(openId);
	}
}
