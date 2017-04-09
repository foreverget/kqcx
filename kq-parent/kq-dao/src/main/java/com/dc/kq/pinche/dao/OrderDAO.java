package com.dc.kq.pinche.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.OrderInfo;
import com.dc.kq.pinche.dmo.OrderPassenger;

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
			"#{updateTime},#{updateBy,jdbcType=VARCHAR})", })
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
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
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
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
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
			"create_time, create_by, version, update_time, update_by ", "FROM pc_order ", "WHERE 1 = 1"
			// ??
	})
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
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
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
			@Result(column = "create_by", property = "createBy", jdbcType = JdbcType.VARCHAR),
			@Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
			@Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR) })
	List<OrderInfo> selectOrderByParams(Map<String, Object> params, String dateType);

	/**
	 * 根据订单ID更新订单信息
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Update({ "<script> ", "UPDATE pc_order <set> ", "<if test=\"openId != null\">open_id=#{openId},</if>",
			"<if test=\"name != null\">name=#{name},</if>", "<if test=\"mobile != null\">mobile=#{mobile},</if>",
			"<if test=\"goTime != null\">go_time=#{goTime},</if>",
			"<if test=\"startAddr != null\">start_addr=#{startAddr},</if>",
			"<if test=\"endAddr != null\">end_addr=#{endAddr},</if>",
			"<if test=\"plates != null\">plates=#{plates},</if>", "<if test=\"reqNum != null\">req_num=#{reqNum},</if>",
			"<if test=\"price != null\">price=#{price},</if>", "<if test=\"status != null\">status=#{status},</if>",
			"<if test=\"score != null\">score=#{score},</if>", "<if test=\"version != null\">version=#{version},</if>",
			"<if test=\"updateTime != null\">update_time=#{updateTime},</if>",
			"<if test=\"updateBy != null\">update_by=#{updateBy}</if>", "</set> where id=#{id} ", "</script>" })
	int updateOrderById(OrderInfo orderInfo);

	/**
	 * 历史订单--约车单
	 * 
	 * @param opendId
	 * @param page
	 * @param size
	 * @return
	 */
	@Select({ "SELECT po.id,po.open_id,po. NAME,po.mobile,po.go_time,po.start_addr,po.end_addr,"
			+ "po.plates,po.req_num,po.price,po. STATUS,po.score,po.create_time " + "FROM pc_order po "
			+ "WHERE po.id IN " + "(SELECT pop.order_id " + "FROM pc_order_passenger pop " + "WHERE "
			+ "pop.open_id = #{opendId,jdbcType=VARCHAR}) "
			+ "ORDER BY po.create_time DESC  LIMIT  #{startPage,jdbcType=INTEGER}, #{size,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "go_time", property = "goTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_addr", property = "startAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end_addr", property = "endAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT) })
	List<OrderInfo> getYcOrderList(@Param("opendId") String opendId, @Param("startPage") int startPage,
			@Param("size") int size);

	/**
	 * 根据orderId 查询订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	@Select({ "SELECT id,open_id,name,mobile,go_time,start_addr,end_addr,"
			+ "plates,req_num,price,status,score,create_time " + "FROM pc_order po "
			+ "WHERE id = #{orderId,jdbcType=BIGINT} " })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "go_time", property = "goTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_addr", property = "startAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end_addr", property = "endAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT) })
	OrderInfo getYcOrderDetail(@Param("orderId") long orderId);

	/**
	 * 历史订单--出车单
	 * 
	 * @param opendId
	 * @param page
	 * @param size
	 * @return
	 */
	@Select({ "SELECT id,open_id,name,mobile,go_time,start_addr,end_addr,"
			+ "plates,req_num,price,status,score,create_time " + "FROM pc_order  "
			+ "WHERE open_id = #{opendId,jdbcType=VARCHAR} "
			+ "ORDER BY create_time DESC  LIMIT  #{startPage,jdbcType=INTEGER}, #{size,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "go_time", property = "goTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_addr", property = "startAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end_addr", property = "endAddr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
			@Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT) })
	List<OrderInfo> getCcOrderList(@Param("opendId") String opendId, @Param("startPage") int startPage,
			@Param("size") int size);

	/**
	 * 查询乘客列表
	 * 
	 * @param orderId
	 * @return
	 */
	@Select({"SELECT pop.id,pop.open_id,pop.status,pop.count,pu.mobile,pu.name "
			+ "FROM pc_order_passenger pop "
			+ "LEFT JOIN pc_user pu ON pop.open_id = pu.open_id "
			+ "WHERE  order_id = #{orderId,jdbcType=BIGINT}"
	})
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
		@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
		@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
		@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
		@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER),
		@Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)})
	List<OrderPassenger> getPassengerList(long orderId);

}