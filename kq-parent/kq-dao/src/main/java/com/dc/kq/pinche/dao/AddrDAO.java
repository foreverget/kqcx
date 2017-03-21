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
	 * 根据openId查询地址信息
	 * 
	 * @param openId
	 * @return
	 */
	@Select({ "SELECT", "id,start,end,open_id,is_default ", "FROM pc_address_info ",
			"WHERE open_id = #{openId,jdbcType=VARCHAR} " })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "start", property = "start", jdbcType = JdbcType.VARCHAR),
			@Result(column = "end", property = "end", jdbcType = JdbcType.VARCHAR),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "is_default", property = "isDefault", jdbcType = JdbcType.INTEGER) })
	List<AddrInfo> selectAddrListByParam(@Param("openId") String openId);

	/**
	 * 新增地址信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	@Insert({ "INSERT INTO pc_address_info (start, end, open_id, is_default )", "VALUES ( #{start,jdbcType=VARCHAR}, ",
			"#{end,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR}, ", "#{isDefault,jdbcType=INTEGER})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	long insert(AddrInfo addrInfo);

	/**
	 * 删除地址信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	@Delete({
			"DELETE FROM pc_address_info WHERE  id = #{id,jdbcType=BIGINT} and open_id = #{openId,jdbcType=VARCHAR}" })
	int delete(@Param("openId") String openId, @Param("id") long id);
}