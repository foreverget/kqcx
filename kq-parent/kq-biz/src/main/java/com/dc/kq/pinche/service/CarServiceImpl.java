package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dao.CarDAO;
import com.dc.kq.pinche.dmo.CarInfo;

/**
 * 车辆service实现类
 * 
 * @author xiaogang
 *
 */
@Service("carServiceImpl")
public class CarServiceImpl implements CarService {
	public static Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
	@Autowired
	private CarDAO carDao;

	/**
	 * 根据oepnid和汽车ID查询车辆信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	@Override
	public CarInfo getCarInfoByParam(String openId, long id) {
		CarInfo carInfo = new CarInfo();
		try {
			carInfo = carDao.selectCarInfoByParam(openId, id);
		} catch (Exception e) {
			LOGGER.error("getCarInfo error ", e);
		}
		return carInfo;
	}

	/**
	 * 根据openId获取车辆列表
	 * 
	 * @param openId
	 * @return
	 */
	@Override
	public List<CarInfo> getCarList(String openId) {
		List<CarInfo> list = new ArrayList<CarInfo>();
		try {
			list = carDao.selectCarListByParam(openId);
		} catch (Exception e) {
			LOGGER.error("getCarList error ", e);
		}
		return list;
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
		try {
			if(carInfo.getId() == 0 ){
				long id = carDao.insert(carInfo);
				resp.setValue(id);				
			}else{
				carDao.update(carInfo);
			}
		} catch (Exception e) {
			LOGGER.error("save car error ", e);
		}
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
	public BaseResponse delete(long userId, long id) {
		BaseResponse resp = new BaseResponse();
		// try {
		// carDao.delete(userId, id);
		// } catch (Exception e) {
		// LOGGER.error("delete car error ", e);
		// }
		return resp;
	}

}
