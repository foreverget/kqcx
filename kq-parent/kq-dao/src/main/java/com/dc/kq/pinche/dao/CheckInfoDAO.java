package com.dc.kq.pinche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.CheckInfo;

public interface CheckInfoDAO {

    @Insert({
        "insert into check_info (id, check_user_id, ",
        "chack_time)",
        "values (#{id,jdbcType=BIGINT}, #{checkUserId,jdbcType=VARCHAR}, ",
        "#{chackTime,jdbcType=VARCHAR})"
    })
    int insert(CheckInfo record);


    @Select({
        "select",
        "id, check_user_id, chack_time",
        "from check_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="check_user_id", property="checkUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="chack_time", property="chackTime", jdbcType=JdbcType.VARCHAR)
    })
    CheckInfo selectByPrimaryKey(Long id);


    @Update({
        "update check_info",
        "set check_user_id = #{checkUserId,jdbcType=VARCHAR},",
          "chack_time = #{chackTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CheckInfo record);


	/**
	 * 功能描述：
	 *	根据日期和工号获得打卡信息
	 * @param checkNum
	 * @param checkMonth
	 * @return
	 * 
	 * @author 李鹏
	 *
	 * @since 2016年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
    @Select({
        "select",
        "id, check_user_id, chack_time",
        "from check_info",
        "where check_user_id = #{checkNum} and chack_time between concat(#{checkMonth},'-01 00:00:00') and concat(#{checkMonth},'-31 23:59:59') "
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="check_user_id", property="checkUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="chack_time", property="chackTime", jdbcType=JdbcType.VARCHAR)
    })
	List<CheckInfo> selectByUserIdAndCheckMonth(@Param("checkNum")String checkNum, @Param("checkMonth")String checkMonth);
    
    
}