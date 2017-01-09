package com.dc.kq.pinche.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.dc.kq.pinche.dmo.OrderInfo;

/**
 * 订单相关数据库操作
 * 
 * @author xiaogang
 *
 */
public interface OrderDAO {
	/**
	 * 新增订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	
	@Insert({ "INSERT INTO pc_order (user_id, driver_name, mobile, take_time, ",
			"start, end, plates, passenger_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{userId,jdbcType=BIGINT},#{driverName,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR},#{takeTime,jdbcType=VARCHAR},", 
			"#{start,jdbcType=VARCHAR},#{end,jdbcType=VARCHAR}, #{plates,jdbcType=VARCHAR},#{passengerNum,jdbcType=INTEGER},",
			"#{price,jdbcType=DECIMAL},#{status,jdbcType=VARCHAR},#{score,jdbcType=DECIMAL},",
			"#{createTime},#{createBy,jdbcType=VARCHAR},#{version,jdbcType=INTEGER},",
			"#{updateTime},#{updateBy,jdbcType=VARCHAR})",
			})
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  
	long insert(OrderInfo orderInfo);
	
}