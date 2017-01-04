package com.dc.kq.pinche.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dao.OrderDAO;
import com.dc.kq.pinche.dmo.OrderInfo;

/**
 * 订单service实现类
 * 
 * @author xiaogang
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	public static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDAO orderDao;
	
	@Override
	public BaseResponse myBookOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse myReleaseOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse myHistoryOrderList(String orderType) {
		return null;
	}

	@Override
	public BaseResponse doReleaseOrder(OrderInfo orderInfo) {
		
		BaseResponse resp = new BaseResponse();
		// 出车时间
		// 始
		// 终
		// 备注 可为空
		// 车牌号码  外键关联车辆信息
		// 拼车人数  先限制最大为7
		// 车费单价  先固定为5元，限制黑车行为，保证为私家车使用
		
		// 以下2项，如未填写，在用户基本信息表获取
		// 司机姓名
		// 联系电话
		
		// 校验订单信息是否符合
		
		try {
			long id = orderDao.insert(orderInfo);
			resp.setValue(id);
		} catch (Exception e) {
			resp.setValue("发布失败");
			LOGGER.error("save order error ", e);
		}
		return resp;
	}

	@Override
	public BaseResponse findReleaseOrderList(String dateType) {
		return null;
	}

	@Override
	public BaseResponse doBookOrder(String orderId) {
		return null;
	}

	@Override
	public BaseResponse doCancelOrderByDriver(String orderId) {
		return null;
	}

	@Override
	public BaseResponse doCancelOrderByPassenger(String orderId, String userId) {
		return null;
	}
	
}
