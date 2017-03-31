package com.dc.kq.pinche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	 * 根据openId查询车辆信息
	 * 
	 * @param openId
	 * @return
	 */
	@Select({ "SELECT", "id,models,plates,seat,color,open_id,image ", "FROM pc_car_info ",
			"WHERE open_id = #{openId,jdbcType=VARCHAR} " })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "models", property = "models", jdbcType = JdbcType.VARCHAR),
			@Result(column = "plates", property = "plates", jdbcType = JdbcType.VARCHAR),
			@Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
			@Result(column = "seat", property = "seat", jdbcType = JdbcType.INTEGER) })
	List<CarInfo> selectCarListByParam(String openId);

	/**
	 * 新增车辆信息
	 * 
	 * @param carInfo
	 * @return
	 */
	@Insert({ "INSERT INTO pc_car_info (models, ", "plates,seat, color,open_id, ", "image )",
			"VALUES ( #{models,jdbcType=VARCHAR}, #{plates,jdbcType=VARCHAR},",
			"#{seat,jdbcType=INTEGER},#{color,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR}, ",
			"#{image,jdbcType=VARCHAR})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	long insert(CarInfo carInfo);

	/**
	 * 删除车辆信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	@Delete({ "DELETE FROM pc_car_info WHERE  id = #{id,jdbcType=BIGINT} and open_id = #{openId,jdbcType=VARCHAR}" })
	int delete(@Param("openId") String openId, @Param("id") long id);

	/**
	 * 根据openId和id查询车辆信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	@Select({ "SELECT", "id,models,plates,seat,color,open_id,image ", "FROM pc_car_info ",
			"WHERE open_id = #{openId,jdbcType=VARCHAR} AND id = #{id,jdbcType=BIGINT}" })
	CarInfo selectCarInfoByParam(@Param("openId") String openId, @Param("id") long id);

	/**
	 * 根据open_id 和 id 修改车辆信息
	 * 
	 * @param carInfo
	 * @return
	 */
	@Update({
			"UPDATE pc_car_info set models = #{carInfo.models,jdbcType=VARCHAR}, plates = #{carInfo.plates,jdbcType=VARCHAR},",
			"seat = #{carInfo.seat,jdbcType=INTEGER},color = #{carInfo.color,jdbcType=VARCHAR}",
			" WHERE open_id = #{carInfo.openId,jdbcType=VARCHAR} AND id = #{carInfo.id,jdbcType=BIGINT} " })
	int update(@Param("carInfo") CarInfo carInfo);
}