package com.dc.kq.pinche.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.dao.AddrDAO;
import com.dc.kq.pinche.dao.CarDAO;
import com.dc.kq.pinche.dmo.AddrInfo;
import com.dc.kq.pinche.dmo.CarInfo;

/**
 * 车辆service实现类
 * 
 * @author xiaogang
 *
 */
@Service("carServiceImpl")
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDAO carDao;

	/**
	 * 根据用户Id获取车辆列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	@Override
	public BaseResponse getCarList(long userId, int pageNo) {
		BaseResponse resp = new BaseResponse();
		int startPage = Constants.PAGE_SIZE * pageNo;
		List<CarInfo> list = carDao.selectCarListByParam(userId, startPage, Constants.PAGE_SIZE);
		resp.setValue(list);
		return resp;
	}
	/**
	 * 保存车辆信息
	 * 
	 * @param carInfo
	 * @return
	 */
	@Override
	public BaseResponse save(CarInfo carInfo) {
		BaseResponse resp = new BaseResponse();
		long id = carDao.insert(carInfo);
		resp.setValue(id);
		return resp;
	}

	/**
	 * 根据地址Id删除车辆信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	@Override
	public BaseResponse delete(long userId,long id) {
		BaseResponse resp = new BaseResponse();
		carDao.delete(userId,id);
		return resp;
	}
	
}
