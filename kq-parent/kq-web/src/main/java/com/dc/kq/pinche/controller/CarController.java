package com.dc.kq.pinche.controller;

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
	 * 获取车辆列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("carList.json")
	@ResponseBody
	public BaseResponse addrList(long userId, int pageNo, String valueKey) {
		return carService.getCarList(userId, pageNo);
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param carInfo
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public BaseResponse save(@RequestBody CarInfo carInfo, String valueKey) {
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
}
