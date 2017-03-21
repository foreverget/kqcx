package com.dc.kq.pinche.service;

import java.util.List;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.CarInfo;

/**
 * 车辆service
 * 
 * @author xiaogang
 *
 */
public interface CarService {
	/**
	 * 根据oepnid和汽车ID查询车辆信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	public CarInfo getCarInfoByParam(String openId, long id);

	/**
	 * 根据openId获取车辆列表
	 * 
	 * @param openId
	 * @param pageNo
	 * @return
	 */
	public List<CarInfo> getCarList(String openId);

	/**
	 * 保存车辆信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	public BaseResponse save(CarInfo carInfo);

	/**
	 * 根据地址Id删除车辆信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	public BaseResponse delete(long userId, long id);
}
