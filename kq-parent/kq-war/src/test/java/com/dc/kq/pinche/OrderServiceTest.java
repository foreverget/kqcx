package com.dc.kq.pinche;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.kq.pinche.request.OrderInfoRequest;
import com.dc.kq.pinche.service.OrderService;
/**
 * 订单测试
 * 
 * @author david
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void doReleaseOrderTest(){
		OrderInfoRequest orderInfo = new OrderInfoRequest();
		orderInfo.setCreateBy("zhangwei");
		orderInfo.setCreateTime(new Date());
		orderInfo.setName("张伟");
		orderInfo.setEndAddr("孔雀城紫藤园南门");
		orderInfo.setMobile("15911142542");
		orderInfo.setReqNum(4);
		orderInfo.setPlates("京888888");
		orderInfo.setPrice(new BigDecimal(5));
		orderInfo.setStartAddr("潞城地铁D口");
		orderInfo.setStatus("1");
		orderInfo.setGoTime("2016-01-09 11:20");
		orderInfo.setOpenId(1000);
		orderInfo.setVersion(1);
		orderService.doReleaseOrder(orderInfo);
	}
	
	@Test
	public void doBookOrderTest(){
		orderService.doBookOrder("1000", "6", "1");
	}
	
	@Test
	public void doCancelOrderByDriverTest(){
		orderService.doCancelOrderByDriver("6");
	}
	
	@Test
	public void doCancelOrderByPassengerTest(){
		orderService.doCancelOrderByPassenger("6", "1000");
	}
	

}
