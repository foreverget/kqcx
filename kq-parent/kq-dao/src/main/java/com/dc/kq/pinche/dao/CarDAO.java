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

import com.dc.kq.pinche.dmo.CarInfo;

/**
 * 车辆相关数据库操作
 * 
 * @author xiaogang
 *
 */
public interface CarDAO {
	/**
	 * 根据用户Id查询车辆信息
	 * 
	 * @param userId
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	@Select({ "SELECT", "id,models,plates,seat,color,user_id,image ", "FROM pc_car_info ",
			"WHERE user_id = #{userId,jdbcType=BIGINT} limit #{startPage,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "models", property = "models", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "seat", property = "seat", jdbcType = JdbcType.INTEGER) })
	List<CarInfo> selectCarListByParam(long userId, int startPage, int pageSize);

	/**
	 * 新增地址信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	@Insert({ "INSERT INTO pc_car_info (models, ", "plates,seat, color,user_id, ", "image )",
			"VALUES ( #{models,jdbcType=VARCHAR}, #{plates,jdbcType=VARCHAR},",
			"#{seat,jdbcType=INTEGER},#{color,jdbcType=VARCHAR}, #{user_id,jdbcType=BIGINT}, ",
			"#{image,jdbcType=VARCHAR})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	long insert(CarInfo carInfo);

	/**
	 * 删除地址信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	@Delete({ "DELETE FROM pc_car_info WHERE  id = #{id,jdbcType=BIGINT} and user_id = #{userId,jdbcType=BIGINT}" })
	int delete(@Param("userId") long userId, @Param("id") long id);

}