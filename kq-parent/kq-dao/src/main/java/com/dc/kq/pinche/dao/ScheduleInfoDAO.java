package com.dc.kq.pinche.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.dc.kq.pinche.dmo.ScheduleInfo;

public interface ScheduleInfoDAO {

    @Insert({
        "insert into schedule_info (id, legion_name, ",
        "department_name, group_name, ",
        "check_num, crm_num, ",
        "em_accout, level, ",
        "schedule_month, schedule_1, ",
        "schedule_2, schedule_3, ",
        "schedule_4, schedule_5, ",
        "schedule_6, schedule_7, ",
        "schedule_8, schedule_9, ",
        "schedule_10, schedule_11, ",
        "schedule_12, schedule_13, ",
        "schedule_14, schedule_15, ",
        "schedule_16, schedule_17, ",
        "schedule_18, schedule_19, ",
        "schedule_20, schedule_21, ",
        "schedule_22, schedule_23, ",
        "schedule_24, schedule_25, ",
        "schedule_26, schedule_27, ",
        "schedule_28, schedule_29, ",
        "schedule_30, schedule_31)",
        "values (#{id,jdbcType=BIGINT}, #{legionName,jdbcType=VARCHAR}, ",
        "#{departmentName,jdbcType=VARCHAR}, #{groupName,jdbcType=VARCHAR}, ",
        "#{checkNum,jdbcType=VARCHAR}, #{crmNum,jdbcType=VARCHAR}, ",
        "#{emAccout,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, ",
        "#{scheduleMonth,jdbcType=VARCHAR}, #{schedule1,jdbcType=VARCHAR}, ",
        "#{schedule2,jdbcType=VARCHAR}, #{schedule3,jdbcType=VARCHAR}, ",
        "#{schedule4,jdbcType=VARCHAR}, #{schedule5,jdbcType=VARCHAR}, ",
        "#{schedule6,jdbcType=VARCHAR}, #{schedule7,jdbcType=VARCHAR}, ",
        "#{schedule8,jdbcType=VARCHAR}, #{schedule9,jdbcType=VARCHAR}, ",
        "#{schedule10,jdbcType=VARCHAR}, #{schedule11,jdbcType=VARCHAR}, ",
        "#{schedule12,jdbcType=VARCHAR}, #{schedule13,jdbcType=VARCHAR}, ",
        "#{schedule14,jdbcType=VARCHAR}, #{schedule15,jdbcType=VARCHAR}, ",
        "#{schedule16,jdbcType=VARCHAR}, #{schedule17,jdbcType=VARCHAR}, ",
        "#{schedule18,jdbcType=VARCHAR}, #{schedule19,jdbcType=VARCHAR}, ",
        "#{schedule20,jdbcType=VARCHAR}, #{schedule21,jdbcType=VARCHAR}, ",
        "#{schedule22,jdbcType=VARCHAR}, #{schedule23,jdbcType=VARCHAR}, ",
        "#{schedule24,jdbcType=VARCHAR}, #{schedule25,jdbcType=VARCHAR}, ",
        "#{schedule26,jdbcType=VARCHAR}, #{schedule27,jdbcType=VARCHAR}, ",
        "#{schedule28,jdbcType=VARCHAR}, #{schedule29,jdbcType=VARCHAR}, ",
        "#{schedule30,jdbcType=VARCHAR}, #{schedule31,jdbcType=VARCHAR})"
    })
    int insert(ScheduleInfo record);


    @Select({
        "select",
        "id, legion_name, department_name, group_name, check_num, crm_num, em_accout, ",
        "level, schedule_month, schedule_1, schedule_2, schedule_3, schedule_4, schedule_5, ",
        "schedule_6, schedule_7, schedule_8, schedule_9, schedule_10, schedule_11, schedule_12, ",
        "schedule_13, schedule_14, schedule_15, schedule_16, schedule_17, schedule_18, ",
        "schedule_19, schedule_20, schedule_21, schedule_22, schedule_23, schedule_24, ",
        "schedule_25, schedule_26, schedule_27, schedule_28, schedule_29, schedule_30, ",
        "schedule_31",
        "from schedule_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="legion_name", property="legionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="check_num", property="checkNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="crm_num", property="crmNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="em_accout", property="emAccout", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_month", property="scheduleMonth", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_1", property="schedule1", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_2", property="schedule2", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_3", property="schedule3", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_4", property="schedule4", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_5", property="schedule5", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_6", property="schedule6", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_7", property="schedule7", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_8", property="schedule8", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_9", property="schedule9", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_10", property="schedule10", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_11", property="schedule11", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_12", property="schedule12", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_13", property="schedule13", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_14", property="schedule14", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_15", property="schedule15", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_16", property="schedule16", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_17", property="schedule17", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_18", property="schedule18", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_19", property="schedule19", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_20", property="schedule20", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_21", property="schedule21", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_22", property="schedule22", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_23", property="schedule23", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_24", property="schedule24", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_25", property="schedule25", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_26", property="schedule26", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_27", property="schedule27", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_28", property="schedule28", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_29", property="schedule29", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_30", property="schedule30", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_31", property="schedule31", jdbcType=JdbcType.VARCHAR)
    })
    ScheduleInfo selectByPrimaryKey(Long id);


    @Update({
        "update schedule_info",
        "set legion_name = #{legionName,jdbcType=VARCHAR},",
          "department_name = #{departmentName,jdbcType=VARCHAR},",
          "group_name = #{groupName,jdbcType=VARCHAR},",
          "check_num = #{checkNum,jdbcType=VARCHAR},",
          "crm_num = #{crmNum,jdbcType=VARCHAR},",
          "em_accout = #{emAccout,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "schedule_month = #{scheduleMonth,jdbcType=VARCHAR},",
          "schedule_1 = #{schedule1,jdbcType=VARCHAR},",
          "schedule_2 = #{schedule2,jdbcType=VARCHAR},",
          "schedule_3 = #{schedule3,jdbcType=VARCHAR},",
          "schedule_4 = #{schedule4,jdbcType=VARCHAR},",
          "schedule_5 = #{schedule5,jdbcType=VARCHAR},",
          "schedule_6 = #{schedule6,jdbcType=VARCHAR},",
          "schedule_7 = #{schedule7,jdbcType=VARCHAR},",
          "schedule_8 = #{schedule8,jdbcType=VARCHAR},",
          "schedule_9 = #{schedule9,jdbcType=VARCHAR},",
          "schedule_10 = #{schedule10,jdbcType=VARCHAR},",
          "schedule_11 = #{schedule11,jdbcType=VARCHAR},",
          "schedule_12 = #{schedule12,jdbcType=VARCHAR},",
          "schedule_13 = #{schedule13,jdbcType=VARCHAR},",
          "schedule_14 = #{schedule14,jdbcType=VARCHAR},",
          "schedule_15 = #{schedule15,jdbcType=VARCHAR},",
          "schedule_16 = #{schedule16,jdbcType=VARCHAR},",
          "schedule_17 = #{schedule17,jdbcType=VARCHAR},",
          "schedule_18 = #{schedule18,jdbcType=VARCHAR},",
          "schedule_19 = #{schedule19,jdbcType=VARCHAR},",
          "schedule_20 = #{schedule20,jdbcType=VARCHAR},",
          "schedule_21 = #{schedule21,jdbcType=VARCHAR},",
          "schedule_22 = #{schedule22,jdbcType=VARCHAR},",
          "schedule_23 = #{schedule23,jdbcType=VARCHAR},",
          "schedule_24 = #{schedule24,jdbcType=VARCHAR},",
          "schedule_25 = #{schedule25,jdbcType=VARCHAR},",
          "schedule_26 = #{schedule26,jdbcType=VARCHAR},",
          "schedule_27 = #{schedule27,jdbcType=VARCHAR},",
          "schedule_28 = #{schedule28,jdbcType=VARCHAR},",
          "schedule_29 = #{schedule29,jdbcType=VARCHAR},",
          "schedule_30 = #{schedule30,jdbcType=VARCHAR},",
          "schedule_31 = #{schedule31,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ScheduleInfo record);

    

    
    
    /**
     * 功能描述：
     *	通过考勤月份获得对应的考勤记录
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
        "id, legion_name, user_name,department_name, group_name, check_num, crm_num, em_accout, ",
        "level, schedule_month, schedule_1, schedule_2, schedule_3, schedule_4, schedule_5, ",
        "schedule_6, schedule_7, schedule_8, schedule_9, schedule_10, schedule_11, schedule_12, ",
        "schedule_13, schedule_14, schedule_15, schedule_16, schedule_17, schedule_18, ",
        "schedule_19, schedule_20, schedule_21, schedule_22, schedule_23, schedule_24, ",
        "schedule_25, schedule_26, schedule_27, schedule_28, schedule_29, schedule_30, ",
        "schedule_31",
        "from schedule_info",
        "where schedule_month = #{checkMonth}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="legion_name", property="legionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="check_num", property="checkNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="crm_num", property="crmNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="em_accout", property="emAccout", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_month", property="scheduleMonth", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_1", property="schedule1", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_2", property="schedule2", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_3", property="schedule3", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_4", property="schedule4", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_5", property="schedule5", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_6", property="schedule6", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_7", property="schedule7", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_8", property="schedule8", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_9", property="schedule9", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_10", property="schedule10", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_11", property="schedule11", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_12", property="schedule12", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_13", property="schedule13", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_14", property="schedule14", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_15", property="schedule15", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_16", property="schedule16", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_17", property="schedule17", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_18", property="schedule18", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_19", property="schedule19", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_20", property="schedule20", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_21", property="schedule21", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_22", property="schedule22", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_23", property="schedule23", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_24", property="schedule24", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_25", property="schedule25", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_26", property="schedule26", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_27", property="schedule27", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_28", property="schedule28", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_29", property="schedule29", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_30", property="schedule30", jdbcType=JdbcType.VARCHAR),
        @Result(column="schedule_31", property="schedule31", jdbcType=JdbcType.VARCHAR)
    })
	List<ScheduleInfo> selectByMonth(@Param("checkMonth")String checkMonth);
}