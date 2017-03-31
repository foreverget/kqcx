package com.dc.kq.pinche.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Insert({ "INSERT INTO pc_order (open_id, name, mobile, go_time, ",
			"start_addr, end_addr, plates, req_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by)",
			"VALUES ( #{openId,jdbcType=BIGINT},#{name,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR},#{goTime,jdbcType=VARCHAR},", 
			"#{startAddr,jdbcType=VARCHAR},#{endAddr,jdbcType=VARCHAR}, #{plates,jdbcType=VARCHAR},#{reqNum,jdbcType=INTEGER},",
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
	@Select({ "SELECT", "id,open_id, name, mobile, go_time, ",
			"start_addr, end_addr, plates, req_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order ",
			"WHERE id = #{orderId,jdbcType=BIGINT}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.BIGINT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "go_time", property = "goTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_addr", property = "startAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end_addr", property = "endAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "req_num", property = "reqNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.DATE),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	OrderInfo selectOrderById(long orderId);
	
	/**
	 * 根据参数查询订单数据
	 * 
	 * @param orderId
	 * @return
	 */
	@Select({ "SELECT", "id,open_id, name, mobile, go_time, ",
			"start_addr, end_addr, plates, req_num, price, status, score, ",
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order ",
			"WHERE 1 = 1"
			//??
	})
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.BIGINT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "go_time", property = "goTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_addr", property = "startAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end_addr", property = "endAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "req_num", property = "reqNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.DATE),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	List<OrderInfo> selectOrderByParams(Map<String, Object> params, String dateType);
	
	/**
	 * 根据订单ID更新订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Update({ "<script> ", "UPDATE pc_order <set> ",
			"<if test=\"openId != null\">open_id=#{openId},</if>",
			"<if test=\"name != null\">name=#{name},</if>",
			"<if test=\"mobile != null\">mobile=#{mobile},</if>",
			"<if test=\"goTime != null\">go_time=#{goTime},</if>",
			"<if test=\"startAddr != null\">start_addr=#{startAddr},</if>",
			"<if test=\"endAddr != null\">end_addr=#{endAddr},</if>",
			"<if test=\"plates != null\">plates=#{plates},</if>",
			"<if test=\"reqNum != null\">req_num=#{reqNum},</if>",
			"<if test=\"price != null\">price=#{price},</if>", 
			"<if test=\"status != null\">status=#{status},</if>",
			"<if test=\"score != null\">score=#{score},</if>",
			"<if test=\"version != null\">version=#{version},</if>",
			"<if test=\"updateTime != null\">update_time=#{updateTime},</if>",
			"<if test=\"updateBy != null\">update_by=#{updateBy}</if>",
			"</set> where id=#{id} ",
			"</script>" })
	int updateOrderById(OrderInfo orderInfo);
	
}