package com.dc.kq.pinche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.CheckDetail;

public interface CheckDetailDAO {
	 @Insert({
	        "insert into check_detail (work_date, ",
	        "name, check_user_id, ",
	        "job_number, legion_name, ",
	        "dep_name, group_name, ",
	        "check_begin_time, check_end_time, ",
	        "schedule_type, check_result)",
	        "values ( #{workDate,jdbcType=VARCHAR}, ",
	        "#{name,jdbcType=VARCHAR}, #{checkUserId,jdbcType=VARCHAR}, ",
	        "#{jobNumber,jdbcType=VARCHAR}, #{legionName,jdbcType=VARCHAR}, ",
	        "#{depName,jdbcType=VARCHAR}, #{groupName,jdbcType=VARCHAR}, ",
	        "#{checkBeginTime,jdbcType=VARCHAR}, #{checkEndTime,jdbcType=VARCHAR}, ",
	        "#{scheduleType,jdbcType=VARCHAR}, #{checkResult,jdbcType=VARCHAR})"
	    })
	    int insert(CheckDetail record);


	    @Select({
	        "select",
	        "id, work_date, name, check_user_id, job_number, legion_name, dep_name, group_name, ",
	        "check_begin_time, check_end_time, schedule_type, check_result",
	        "from check_detail",
	        "where id = #{id,jdbcType=INTEGER}"
	    })
	    @Results({
	        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="work_date", property="workDate", jdbcType=JdbcType.VARCHAR),
	        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_user_id", property="checkUserId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="job_number", property="jobNumber", jdbcType=JdbcType.VARCHAR),
	        @Result(column="legion_name", property="legionName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="dep_name", property="depName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_begin_time", property="checkBeginTime", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_end_time", property="checkEndTime", jdbcType=JdbcType.VARCHAR),
	        @Result(column="schedule_type", property="scheduleType", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_result", property="checkResult", jdbcType=JdbcType.VARCHAR)
	    })
	    CheckDetail selectByPrimaryKey(Integer id);


	    @Update({
	        "update check_detail",
	        "set work_date = #{workDate,jdbcType=VARCHAR},",
	          "name = #{name,jdbcType=VARCHAR},",
	          "check_user_id = #{checkUserId,jdbcType=VARCHAR},",
	          "job_number = #{jobNumber,jdbcType=VARCHAR},",
	          "legion_name = #{legionName,jdbcType=VARCHAR},",
	          "dep_name = #{depName,jdbcType=VARCHAR},",
	          "group_name = #{groupName,jdbcType=VARCHAR},",
	          "check_begin_time = #{checkBeginTime,jdbcType=VARCHAR},",
	          "check_end_time = #{checkEndTime,jdbcType=VARCHAR},",
	          "schedule_type = #{scheduleType,jdbcType=VARCHAR},",
	          "check_result = #{checkResult,jdbcType=VARCHAR}",
	        "where id = #{id,jdbcType=INTEGER}"
	    })
	    int updateByPrimaryKey(CheckDetail record);
	    
	    /**
	     * 根据考勤结果查询记录
	     * @param checkUserId
	     * @param checkRusult
	     * @return
	     */
	    @Select({
	    	"select id,work_date,name,check_user_id,job_number,legion_name,dep_name,group_name,check_begin_time,check_end_time,"
	    	+ "schedule_type,check_result from check_detail  "
	    	+ "where check_user_id = #{checkUserId,jdbcType=VARCHAR} and check_result = #{checkRusult,jdbcType=VARCHAR}"
	    })
	    @Results({
	        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="work_date", property="workDate", jdbcType=JdbcType.VARCHAR),
	        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_user_id", property="checkUserId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="job_number", property="jobNumber", jdbcType=JdbcType.VARCHAR),
	        @Result(column="legion_name", property="legionName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="dep_name", property="depName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_begin_time", property="checkBeginTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="check_end_time", property="checkEndTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="schedule_type", property="scheduleType", jdbcType=JdbcType.VARCHAR),
	        @Result(column="check_result", property="checkResult", jdbcType=JdbcType.VARCHAR)
	    })
	    List<CheckDetail> selectCheckDetailListByCheckResultAndUserId(String checkUserId,String checkRusult);
}