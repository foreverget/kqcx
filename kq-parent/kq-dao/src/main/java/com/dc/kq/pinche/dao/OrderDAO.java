package com.dc.kq.pinche.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

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
	
	/**
	 * 根据订单ID查询唯一订单
	 * 
	 * @param orderId
	 * @return
	 */
	@Select({ "SELECT", "user_id, driver_name, mobile, take_time, ",
			"start, end, plates, passenger_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order ",
			"WHERE id = #{orderId,jdbcType=BIGINT}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
			@Result(column = "driver_name", property = "driverName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "take_time", property = "takeTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start", property = "start", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end", property = "end", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "passenger_num", property = "passengerNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.DATE),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	OrderInfo selectOrderById(long orderId);
	
}