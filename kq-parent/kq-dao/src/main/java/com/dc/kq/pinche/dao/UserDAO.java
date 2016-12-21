package com.dc.kq.pinche.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.UserInfo;

/**
 * 用户相关数据库操作
 * 
 * @author xiaogang
 *
 */
public interface UserDAO {
	/**
	 * 根据openId查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@Select({ "SELECT", "id,name,mobile,addr,email,gender,head_img,status,open_id ", "FROM pc_user ",
			"WHERE open_id = #{openId,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "addr", property = "addr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
			@Result(column = "head_img", property = "headImg", jdbcType = JdbcType.VARCHAR),
			@Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR) })
	UserInfo selectUserByOpenId(String openId);

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@Select({ "SELECT", "id,name,mobile,addr,email,gender,head_img,status,open_id ", "FROM pc_user ",
			"WHERE id = #{userId,jdbcType=BIGINT}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
			@Result(column = "addr", property = "addr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
			@Result(column = "head_img", property = "headImg", jdbcType = JdbcType.VARCHAR),
			@Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR) })
	UserInfo selectUserByUserId(long userId);

	/**
	 * 新增用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@Insert({ "INSERT INTO pc_user (name, mobile, addr, email, gender, head_img, status, open_id)",
			"VALUES ( #{name,jdbcType=VARCHAR}, ", "#{mobile,jdbcType=VARCHAR}, #{addr,jdbcType=VARCHAR}, ",
			"#{email,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, ",
			"#{headImg,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ", "#{openId,jdbcType=VARCHAR})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	int insert(UserInfo userInfo);

	/**
	 * 修改用户信息
	 * 
	 * @param userId
	 * @param key
	 * @param value
	 * @return
	 */
	@Update({
			"UPDATE pc_user set  #{key,jdbcType=VARCHAR} = #{value,jdbcType=VARCHAR} WHERE id = #{userId,jdbcType=BIGINT} " })
	int updateUser(@Param("userId") long userId, @Param("key") String key, @Param("value") String value);
}