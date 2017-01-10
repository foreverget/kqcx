package com.dc.kq.pinche.dao;

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
	
	@Insert({ "INSERT INTO pc_order_passenger (user_id, order_id, status, count, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{userId,jdbcType=BIGINT},#{orderId,jdbcType=BIGINT}, #{status,jdbcType=VARCHAR},#{count,jdbcType=INTEGER},", 
			"#{createTime},#{createBy,jdbcType=VARCHAR},#{version,jdbcType=INTEGER},",
			"#{updateTime},#{updateBy,jdbcType=VARCHAR})",
			})
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")  
	long insert(OrderPassenger orderPassenger);
	
	/**
	 * 根据用户ID、订单ID查询唯一记录
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 */
	@Select({ "SELECT", "id,order_id,user_id,status, count,",
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order_passenger ",
			"WHERE user_id = #{userId} and order_id = #{orderId} and status='1'"})
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "order_id", property = "orderId", jdbcType = JdbcType.BIGINT),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.DATE),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	OrderPassenger selectOrderPassengerById(@Param("userId") String userId,@Param("orderId") String orderId);
	
	/**
	 * 根据ID更新记录信息
	 * 
	 * @param orderPassenger
	 * @return
	 */
	@Update({ "<script> ", "UPDATE pc_order_passenger <set> ",
			"<if test=\"orderId != null\">order_id=#{orderId},</if>",
			"<if test=\"userId != null\">user_id=#{userId},</if>",
			"<if test=\"status != null\">status=#{status},</if>",
			"<if test=\"count != null\">count=#{count},</if>",
			"<if test=\"version != null\">version=#{version},</if>",
			"<if test=\"updateTime != null\">update_time=#{updateTime},</if>",
			"<if test=\"updateBy != null\">update_by=#{updateBy}</if>",
			"</set> where id=#{id} ",
			"</script>" })
	int updateOrderPassengerById(OrderPassenger orderPassenger);
	
}