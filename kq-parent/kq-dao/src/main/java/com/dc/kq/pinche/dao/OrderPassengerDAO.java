package com.dc.kq.pinche.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

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

	@Insert({ "INSERT INTO pc_order_passenger (open_id, order_id, status, count, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{openId,jdbcType=VARCHAR},#{orderId,jdbcType=BIGINT}, #{status,jdbcType=VARCHAR},#{count,jdbcType=INTEGER},",
			"#{createTime},#{createBy,jdbcType=VARCHAR},#{version,jdbcType=INTEGER},",
			"#{updateTime},#{updateBy,jdbcType=VARCHAR})", })
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	long insert(OrderPassenger orderPassenger);

	/**
	 * 根据用户ID、订单ID查询唯一记录
	 * 
	 * @param orderId
	 * @param openId
	 * @param status
	 * @return
	 */
	@Select({ "SELECT", "id,order_id,open_id,status, count,",
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order_passenger ",
			"WHERE open_id = #{openId,jdbcType=VARCHAR} and order_id = #{orderId,jdbcType=BIGINT} and status=#{status,jdbcType=VARCHAR} " })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "order_id", property = "orderId", jdbcType = JdbcType.BIGINT),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.BIGINT),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.DATE),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	OrderPassenger selectOneOrderPassengerById(@Param("openId") String openId, @Param("orderId") long orderId,
											@Param("status") String status);

	/**
	 * 根据ID更新记录信息
	 * 
	 * @param orderPassenger
	 * @return
	 */
	@Update({ "<script> ", "UPDATE pc_order_passenger <set> ", "<if test=\"orderId != null\">order_id=#{orderId},</if>",
			"<if test=\"openId != null\">open_id=#{openId},</if>", "<if test=\"status != null\">status=#{status},</if>",
			"<if test=\"count != null\">count=#{count},</if>", "<if test=\"version != null\">version=#{version},</if>",
			"<if test=\"updateTime != null\">update_time=#{updateTime},</if>",
			"<if test=\"updateBy != null\">update_by=#{updateBy}</if>", "</set> where id=#{id} ", "</script>" })
	int updateOrderPassengerById(OrderPassenger orderPassenger);

	/**
	 * 根据orderId和openId修改乘客状态
	 * 
	 * @param orderId
	 * @param openId
	 * @return
	 */
	@Update({ "<script> ", "UPDATE pc_order_passenger set status=0 WHERE order_id=  #{orderId,jdbcType=BIGINT}"
			+ "  AND open_id = #{openId,jdbcType=VARCHAR} ",
			"<if test=\"updateTime != null\">update_time=#{updateTime},</if>",
			"<if test=\"updateBy != null\">update_by=#{updateBy}</if>", "</set> where id=#{id} ", "</script>" })
	int updateStatusByParam(@Param("orderId") long orderId, @Param("openId") String openId,
			@Param("updateTime") Date updateTime, @Param("updateBy") String updateBy);

}