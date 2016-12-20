package com.dc.kq.pinche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.CarInfo;

/**
 * 车辆controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/car/")
public class CarController {
	/**
	 * 获取车辆列表
	 * 
	 * @param userId
	 * @param startPage
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("carList.json")
	@ResponseBody
	public BaseResponse addrList(long userId, int startPage, String valueKey) {
		return null;
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param carInfo
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("saveCar.json")
	@ResponseBody
	public BaseResponse saveCar(@RequestBody CarInfo carInfo, String valueKey) {
		return null;
	}
	/**
	 * 删除地车辆信息
	 * 
	 * @param user
	 * @param carId
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("deleteCar.json")
	@ResponseBody
	public BaseResponse deleteCar(long user, long carId, String valueKey) {
		return null;
	}
}
