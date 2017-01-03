package com.dc.kq.pinche.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.common.ResponseEnum;
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
	 * 根据用户Id获取车辆列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	@Override
	public BaseResponse getCarList(long userId, int pageNo) {
		BaseResponse resp = new BaseResponse();
		try {
			int startPage = Constants.PAGE_SIZE * pageNo;
			List<CarInfo> list = carDao.selectCarListByParam(userId, startPage, Constants.PAGE_SIZE);
			if (!CollectionUtils.isEmpty(list)) {
				resp.setValue(list);
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
				LOGGER.info("getCarList ::: " + ResponseEnum.LIST_EMPTY.getMemo());
			}
		} catch (Exception e) {
			LOGGER.error("getCarList error ", e);
		}
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
		try {
			long id = carDao.insert(carInfo);
			resp.setValue(id);
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
		try {
			carDao.delete(userId, id);
		} catch (Exception e) {
			LOGGER.error("delete car error ", e);
		}
		return resp;
	}

}
