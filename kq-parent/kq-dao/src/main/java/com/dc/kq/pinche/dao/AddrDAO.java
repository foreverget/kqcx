package com.dc.kq.pinche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.AddrInfo;

/**
 * 地址相关数据库操作
 * 
 * @author xiaogang
 *
 */
public interface AddrDAO {
	/**
	 * 根据用户Id查询地址信息
	 * 
	 * @param userId
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@Select({ "SELECT", "id,start,end,user_id,is_default ", "FROM pc_address_info ",
			"WHERE user_id = #{userId,jdbcType=BIGINT} limit #{startPage,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "start", property = "start", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end", property = "end", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
			@Result(column = "is_default", property = "isDefault", jdbcType = JdbcType.INTEGER) })
	List<AddrInfo> selectAddrListByParam(long userId, int startPage, int pageSize);

	/**
	 * 新增地址信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	@Insert({ "INSERT INTO pc_address_info (start, end, user_id, is_default )",
			"VALUES ( #{start,jdbcType=VARCHAR}, ", "#{end,jdbcType=VARCHAR}, #{user_id,jdbcType=BIGINT}, ",
			"#{is_default,jdbcType=INTEGER})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	long insert(AddrInfo addrInfo);

	/**
	 * 删除地址信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	@Delete({ "DELETE FROM pc_address_info WHERE  id = #{id,jdbcType=BIGINT} and user_id = #{userId,jdbcType=BIGINT}" })
	int delete(@Param("userId") long userId, @Param("id") long id);
}