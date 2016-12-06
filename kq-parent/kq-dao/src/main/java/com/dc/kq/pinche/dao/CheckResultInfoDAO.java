package com.dc.kq.pinche.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.CheckResultInfo;

public interface CheckResultInfoDAO {
    @Delete({
        "delete from check_result_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into check_result_info (id, check_user_id, ",
        "late_times, late_deduct, ",
        "early_times, early_deduct, ",
        "late_early_deduct, not_check_in_times, ",
        "not_check_in_deduct, not_check_in_days, ",
        "not_check_in_date, personal_leave_days, ",
        "personal_leave_date, sick_leavel_days, ",
        "sick_leave_date, marriage_leave_days, ",
        "funeral_leave_days, maternity_leave_days, ",
        "overtime_days, total_deduct, ",
        "remarks)",
        "values (#{id,jdbcType=INTEGER}, #{checkUserId,jdbcType=VARCHAR}, ",
        "#{lateTimes,jdbcType=INTEGER}, #{lateDeduct,jdbcType=DECIMAL}, ",
        "#{earlyTimes,jdbcType=INTEGER}, #{earlyDeduct,jdbcType=DECIMAL}, ",
        "#{lateEarlyDeduct,jdbcType=DECIMAL}, #{notCheckInTimes,jdbcType=INTEGER}, ",
        "#{notCheckInDeduct,jdbcType=DECIMAL}, #{notCheckInDays,jdbcType=INTEGER}, ",
        "#{notCheckInDate,jdbcType=VARCHAR}, #{personalLeaveDays,jdbcType=INTEGER}, ",
        "#{personalLeaveDate,jdbcType=VARCHAR}, #{sickLeavelDays,jdbcType=INTEGER}, ",
        "#{sickLeaveDate,jdbcType=VARCHAR}, #{marriageLeaveDays,jdbcType=INTEGER}, ",
        "#{funeralLeaveDays,jdbcType=INTEGER}, #{maternityLeaveDays,jdbcType=INTEGER}, ",
        "#{overtimeDays,jdbcType=INTEGER}, #{totalDeduct,jdbcType=DECIMAL}, ",
        "#{remarks,jdbcType=VARCHAR})"
    })
    int insert(CheckResultInfo record);


    @Select({
        "select",
        "id, check_user_id, late_times, late_deduct, early_times, early_deduct, late_early_deduct, ",
        "not_check_in_times, not_check_in_deduct, not_check_in_days, not_check_in_date, ",
        "personal_leave_days, personal_leave_date, sick_leavel_days, sick_leave_date, ",
        "marriage_leave_days, funeral_leave_days, maternity_leave_days, overtime_days, ",
        "total_deduct, remarks",
        "from check_result_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="check_user_id", property="checkUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="late_times", property="lateTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="late_deduct", property="lateDeduct", jdbcType=JdbcType.DECIMAL),
        @Result(column="early_times", property="earlyTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="early_deduct", property="earlyDeduct", jdbcType=JdbcType.DECIMAL),
        @Result(column="late_early_deduct", property="lateEarlyDeduct", jdbcType=JdbcType.DECIMAL),
        @Result(column="not_check_in_times", property="notCheckInTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="not_check_in_deduct", property="notCheckInDeduct", jdbcType=JdbcType.DECIMAL),
        @Result(column="not_check_in_days", property="notCheckInDays", jdbcType=JdbcType.INTEGER),
        @Result(column="not_check_in_date", property="notCheckInDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="personal_leave_days", property="personalLeaveDays", jdbcType=JdbcType.INTEGER),
        @Result(column="personal_leave_date", property="personalLeaveDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="sick_leavel_days", property="sickLeavelDays", jdbcType=JdbcType.INTEGER),
        @Result(column="sick_leave_date", property="sickLeaveDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="marriage_leave_days", property="marriageLeaveDays", jdbcType=JdbcType.INTEGER),
        @Result(column="funeral_leave_days", property="funeralLeaveDays", jdbcType=JdbcType.INTEGER),
        @Result(column="maternity_leave_days", property="maternityLeaveDays", jdbcType=JdbcType.INTEGER),
        @Result(column="overtime_days", property="overtimeDays", jdbcType=JdbcType.INTEGER),
        @Result(column="total_deduct", property="totalDeduct", jdbcType=JdbcType.DECIMAL),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    CheckResultInfo selectByPrimaryKey(Integer id);


    @Update({
        "update check_result_info",
        "set check_user_id = #{checkUserId,jdbcType=VARCHAR},",
          "late_times = #{lateTimes,jdbcType=INTEGER},",
          "late_deduct = #{lateDeduct,jdbcType=DECIMAL},",
          "early_times = #{earlyTimes,jdbcType=INTEGER},",
          "early_deduct = #{earlyDeduct,jdbcType=DECIMAL},",
          "late_early_deduct = #{lateEarlyDeduct,jdbcType=DECIMAL},",
          "not_check_in_times = #{notCheckInTimes,jdbcType=INTEGER},",
          "not_check_in_deduct = #{notCheckInDeduct,jdbcType=DECIMAL},",
          "not_check_in_days = #{notCheckInDays,jdbcType=INTEGER},",
          "not_check_in_date = #{notCheckInDate,jdbcType=VARCHAR},",
          "personal_leave_days = #{personalLeaveDays,jdbcType=INTEGER},",
          "personal_leave_date = #{personalLeaveDate,jdbcType=VARCHAR},",
          "sick_leavel_days = #{sickLeavelDays,jdbcType=INTEGER},",
          "sick_leave_date = #{sickLeaveDate,jdbcType=VARCHAR},",
          "marriage_leave_days = #{marriageLeaveDays,jdbcType=INTEGER},",
          "funeral_leave_days = #{funeralLeaveDays,jdbcType=INTEGER},",
          "maternity_leave_days = #{maternityLeaveDays,jdbcType=INTEGER},",
          "overtime_days = #{overtimeDays,jdbcType=INTEGER},",
          "total_deduct = #{totalDeduct,jdbcType=DECIMAL},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CheckResultInfo record);
}