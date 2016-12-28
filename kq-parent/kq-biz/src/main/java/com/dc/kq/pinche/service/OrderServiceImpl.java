package com.dc.kq.pinche.service;

import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.OrderInfo;

/**
 * 订单service实现类
 * 
 * @author xiaogang
 *
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#myBookOrderList(java.lang.String)
	 */
	@Override
	public BaseResponse myBookOrderList(String dateType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#myReleaseOrderList(java.lang.String)
	 */
	@Override
	public BaseResponse myReleaseOrderList(String dateType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#myHistoryOrderList(java.lang.String)
	 */
	@Override
	public BaseResponse myHistoryOrderList(String orderType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#doReleaseOrder(com.dc.kq.pinche.dmo.OrderInfo)
	 */
	@Override
	public BaseResponse doReleaseOrder(OrderInfo order) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#findReleaseOrderList(java.lang.String)
	 */
	@Override
	public BaseResponse findReleaseOrderList(String dateType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dc.kq.pinche.service.OrderService#doBookOrder(java.lang.String)
	 */
	@Override
	public BaseResponse doBookOrder(String orderId) {
		return null;
	}
	
}
