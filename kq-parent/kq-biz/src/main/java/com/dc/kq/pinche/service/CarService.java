package com.dc.kq.pinche.service;

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
	 * 根据用户Id获取车辆列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	public BaseResponse getCarList(long userId, int pageNo);

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
	public BaseResponse delete(long userId,long id);
}
