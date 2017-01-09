package com.dc.kq.pinche.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.dc.kq.pinche.dmo.OrderPassenger;

/**
 * 订单乘客对应关系数据操作
 * 
 * @author david
 *
 */
public interface OrderPassengerDAO {
	/**
	 * 新增订单乘客关系信息
	 * 
	 * @param orderPassenger
	 * @return
	 */
	
	@Insert({ "INSERT INTO pc_order_passenger (user_id, order_id, status, count, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{userId,jdbcType=BIGINT},#{orderId,jdbcType=BIGINT}, #{status,jdbcType=VARCHAR},#{count,jdbcType=INTEGER},", 
			"#{createTime},#{createBy,jdbcType=VARCHAR},#{version,jdbcType=INTEGER},",
			"#{updateTime},#{updateBy,jdbcType=VARCHAR})",
			})
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  
	long insert(OrderPassenger orderPassenger);
	
}