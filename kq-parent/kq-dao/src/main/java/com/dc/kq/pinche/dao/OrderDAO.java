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
	
	@Insert({ "INSERT INTO pc_order (user_id, driver_name, mobile, setout_time, ",
			"start, end, plates, person_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{user_id,jdbcType=BIGINT},#{driver_name,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR},#{setout_time,jdbcType=DATE},", 
			"#{start,jdbcType=VARCHAR},#{end,jdbcType=VARCHAR}, #{plates,jdbcType=VARCHAR},#{person_num,jdbcType=INTGER},",
			"#{price,jdbcType=INTEGER},#{status,jdbcType=VARCHAR},#{score,jdbcType=INTEGER},",
			"#{create_time,jdbcType=DATE},#{create_by,jdbcType=BIGINT},#{version,jdbcType=INTEGER},",
			"#{update_time,jdbcType=DATE},#{update_by,jdbcType=BIGINT})",
			})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	long insert(OrderInfo orderInfo);
	
}