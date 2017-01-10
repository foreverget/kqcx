package com.dc.kq.pinche;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.kq.pinche.dmo.OrderInfo;
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
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateBy("zhangwei");
		orderInfo.setCreateTime(new Date());
		orderInfo.setDriverName("张伟");
		orderInfo.setEnd("孔雀城紫藤园南门");
		orderInfo.setMobile("15911142542");
		orderInfo.setPassengerNum(4);
		orderInfo.setPlates("京888888");
		orderInfo.setPrice(new BigDecimal(5));
		orderInfo.setScore(new BigDecimal(2));
		orderInfo.setStart("潞城地铁D口");
		orderInfo.setStatus("1");
		orderInfo.setTakeTime("2016-01-09 11:20");
		orderInfo.setUserId(1000);
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
