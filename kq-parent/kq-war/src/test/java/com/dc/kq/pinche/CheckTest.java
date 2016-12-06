package com.dc.kq.pinche;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.kq.pinche.dmo.CheckDetail;
import com.dc.kq.pinche.dmo.CheckInfo;
import com.dc.kq.pinche.dmo.ScheduleInfo;
import com.dc.kq.pinche.service.CheckDetailService;
import com.dc.kq.pinche.service.CheckInfoService;
import com.dc.kq.pinche.service.ScheduleInfoService;

import CrmConstants.CrmConstant;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
	{ "classpath:applicationContext.xml" })
public class CheckTest {

	// 每天的打卡明细
	@Autowired
	private CheckDetailService checkDetailService;

	// 打卡明细
	@Autowired
	private CheckInfoService checkInfoService;

	// 排班信息
	@Autowired
	private ScheduleInfoService scheduleInfoService;
	
	
	@Test
	public void insertInfo() throws ParseException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			String checkMonth = "2016-04";

			List<ScheduleInfo> list = scheduleInfoService.selectByMonth(checkMonth);
			// 遍历
			for (ScheduleInfo info : list) {

				// 判断是否为特殊组
				/**
				 * 正常班打卡开始时间
				 */
				String normal_start = String.valueOf("08:50");
				/**
				 * 正常班打卡结束时间
				 */
				String normal_end = String.valueOf("17:50");
				/**
				 * 加班打卡开始时间
				 */
				String overtime_start = String.valueOf("8:50");
				/**
				 * 加班打卡结束时间
				 */
				String overtime_end = String.valueOf("17:50");
				/**
				 * 白加班打卡开始时间
				 */
				String new_overtime_start = String.valueOf("8:50");
				/**
				 * 白加班打卡结束时间
				 */
				String new_overtime_end = String.valueOf("21:00");
				// 迟到次数
				int late_times = 0;
				//
				BigDecimal late_deduct = BigDecimal.ZERO;
				// 早退次数
				int early_times = 0;
				BigDecimal early_deduct = BigDecimal.ZERO;
				// 迟到早退扣款
				BigDecimal late_early_deduct = BigDecimal.ZERO;

				// 未打卡次数
				int not_check_in_times = 0;

				// 未打卡天数
				int not_check_in_days = 0;
				// 未打卡日期
				StringBuffer not_check_in_date = new StringBuffer();
				// 事假天数
				int personal_leave_days = 0;

				// 事假日期
				StringBuffer personal_leave_date = new StringBuffer();
				// 病假天数
				int sick_leavel_days = 0;

				// 病假日期
				StringBuffer sick_leave_date = new StringBuffer();

				// 婚假天数
				int marriage_leave_days = 0;
				// 丧家天数
				int funeral_leave_days = 0;
				// 产假天数
				int maternity_leave_days = 0;
				// 加班天数
				int overtime_days = 0;
				/**
				 * 五分钟内迟到
				 */
				int five_minute_late_times = 0;
				// 先根据ID获得打卡信息
				List<CheckInfo> checkInfoList = checkInfoService.selectByUserIdAndCheckMonth(info.getCheckNum(), checkMonth);
				Map<String, List<CheckInfo>> checkInfoMap = this.getUserCheckInfo(checkInfoList);
				if (CrmConstant.SPECIALDEP.contains(info.getDepartmentName()) || CrmConstant.SPECIALGROUP.contains(info.getGroupName())) {
					// 判断是否为特殊组
					/**
					 * 正常班打卡开始时间
					 */
					normal_start = CrmConstant.SPECIAL_NORMAL_START;
					/**
					 * 正常班打卡结束时间
					 */
					normal_end = CrmConstant.SPECIAL_NORMAL_END;
					/**
					 * 加班打卡开始时间
					 */
					overtime_start = CrmConstant.SPECIAL_OVERString_START;
					/**
					 * 加班打卡结束时间
					 */
					overtime_end = CrmConstant.SPECIAL_OVERString_END;
					/**
					 * 白加班打卡开始时间
					 */
					new_overtime_start = CrmConstant.SPECIAL_NEWOVERString_START;
					/**
					 * 白加班打卡结束时间
					 */
					new_overtime_end = CrmConstant.SPECIAL_NEWOVERString_END;
				}

				CheckDetail checkInfo = new CheckDetail();
				// TODO 添加姓名信息
				checkInfo.setCheckUserId(info.getCrmNum());
				checkInfo.setDepName(info.getDepartmentName());
				checkInfo.setGroupName(info.getGroupName());
				checkInfo.setLegionName(info.getLegionName());
				checkInfo.setCheckUserId(info.getCrmNum());
				checkInfo.setJobNumber(info.getCheckNum());
				// 获取第一天
				for (int i = 1; i < 32; i++) {
					checkInfo.setCheckBeginTime("");
					checkInfo.setCheckEndTime("");
					checkInfo.setCheckResult("");
					checkInfo.setScheduleType("");				
					Method method = info.getClass().getMethod("getSchedule" + i);
					String date = checkMonth;
					if (i < 10) {
						date = checkMonth + "-0" + i;
					} else {
						date = checkMonth + "-" + i;
					}
					String scheduleType = "";
					if (null == method.invoke(info)) {
						scheduleType="未排班";
					} else {
						scheduleType = method.invoke(info).toString();
					}
					checkInfo.setScheduleType(scheduleType);
					checkInfo.setWorkDate(date);
					List<CheckInfo> checkDetailList = checkInfoMap.get(date);
					 if (scheduleType.equals("正常") || scheduleType.equals("未排班")) {
						Date normalStartDate = DateUtils.parseDate(checkMonth + "-01 " + normal_start + ":00", "yyyy-MM-dd HH:mm:ss");
						Date normalEndDate = DateUtils.parseDate(checkMonth + "-01 " + normal_end + ":00", "yyyy-MM-dd HH:mm:ss");
						// 旷工
						if (null == checkDetailList || checkDetailList.size() == 0) {
							// 未打卡天数
							not_check_in_days++;
							checkInfo.setCheckResult("旷工");
						} else if (checkDetailList.size() == 1) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							not_check_in_times++;
							checkInfo.setCheckResult("未打卡");
						} else if (checkDetailList.size() >= 2) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							checkInfo.setCheckEndTime(checkDetailList.get(checkDetailList.size() - 1).getChackTime());
							Date start = DateUtils.parseDate(checkDetailList.get(0).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							Date end = DateUtils.parseDate(checkDetailList.get(checkDetailList.size() - 1).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							boolean isLate = false;
							if (start.compareTo(normalStartDate) == 1) {
								long minute = (start.getTime() - normalStartDate.getTime()) / (60 * 1000);
								if (minute < 5) {
									five_minute_late_times++;
								} else {
									late_times++;
									isLate = true;
								}
								isLate = true;
							}
							boolean isEarly = false;
							if (end.compareTo(normalEndDate) == -1) {
								early_deduct = early_deduct.add(BigDecimal.valueOf(30l));
								early_times++;
								isEarly = true;
							}
							if (!isLate && !isEarly) {
								checkInfo.setCheckResult("正常");
							} else if (!isLate && isEarly) {
								checkInfo.setCheckResult("早退");
							} else if (isLate && !isEarly) {
								checkInfo.setCheckResult("迟到");
							} else {
								checkInfo.setCheckResult("迟到且早退");
							}
						}

					} else if (scheduleType.equals("加班")) {
						overtime_days++;
						Date normalStartDate = DateUtils.parseDate(date + " " + overtime_start + ":00", "yyyy-MM-dd HH:mm:ss");
						Date normalEndDate = DateUtils.parseDate(date + " " + overtime_end + ":00", "yyyy-MM-dd HH:mm:ss");
						// 旷工
						if (null == checkDetailList || checkDetailList.size() == 0) {
							// 未打卡天数
							not_check_in_days++;
							checkInfo.setCheckResult("旷工");
						} else if (checkDetailList.size() == 1) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							not_check_in_times++;
							checkInfo.setCheckResult("未打卡");
						} else if (checkDetailList.size() >= 2) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							checkInfo.setCheckEndTime(checkDetailList.get(checkDetailList.size() - 1).getChackTime());
							Date start = DateUtils.parseDate(checkDetailList.get(0).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							Date end = DateUtils.parseDate(checkDetailList.get(checkDetailList.size() - 1).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							boolean isLate = false;
							if (start.compareTo(normalStartDate) == 1) {
								long minute = (start.getTime() - normalStartDate.getTime()) / (60 * 1000);
								if (minute < 5) {
									five_minute_late_times++;
								} else {
									late_times++;
									isLate = true;
								}
								isLate = true;
							}
							boolean isEarly = false;
							if (end.compareTo(normalEndDate) == -1) {
								early_deduct = early_deduct.add(BigDecimal.valueOf(30l));
								early_times++;
								isEarly = true;
							}
							if (!isLate && !isEarly) {
								checkInfo.setCheckResult("正常");
							} else if (!isLate && isEarly) {
								checkInfo.setCheckResult("早退");
							} else if (isLate && !isEarly) {
								checkInfo.setCheckResult("迟到");
							} else {
								checkInfo.setCheckResult("迟到且早退");
							}
						}
					} else if (scheduleType.equals("白加")) {
						Date normalStartDate = DateUtils.parseDate(date + " " + new_overtime_start + ":00", "yyyy-MM-dd HH:mm:ss");
						Date normalEndDate = DateUtils.parseDate(date + " " + new_overtime_end + ":00", "yyyy-MM-dd HH:mm:ss");
						// 旷工
						if (null == checkDetailList || checkDetailList.size() == 0) {
							// 未打卡天数
							not_check_in_days++;
							checkInfo.setCheckResult("旷工");
						} else if (checkDetailList.size() == 1) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							not_check_in_times++;
							checkInfo.setCheckResult("未打卡");
						} else if (checkDetailList.size() >= 2) {
							checkInfo.setCheckBeginTime(checkDetailList.get(0).getChackTime());
							checkInfo.setCheckEndTime(checkDetailList.get(checkDetailList.size() - 1).getChackTime());
							Date start = DateUtils.parseDate(checkDetailList.get(0).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							Date end = DateUtils.parseDate(checkDetailList.get(checkDetailList.size() - 1).getChackTime(), "yyyy-MM-dd HH:mm:ss");
							boolean isLate = false;
							if (start.compareTo(normalStartDate) == 1) {
								isLate = true;
							}
							boolean isEarly = false;
							if (end.compareTo(normalEndDate) == -1) {
								early_deduct = early_deduct.add(BigDecimal.valueOf(30l));
								early_times++;
								isEarly = true;
							}
							if (!isLate && !isEarly) {
								checkInfo.setCheckResult("正常");
							} else if (!isLate && isEarly) {
								checkInfo.setCheckResult("早退");
							} else if (isLate && !isEarly) {
								checkInfo.setCheckResult("迟到");
							} else {
								checkInfo.setCheckResult("迟到且早退");
							}
						}
					} else {
						checkInfo.setCheckResult("休假");
					}
					// 插入
					checkDetailService.insert(checkInfo);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 功能描述：
	 * 
	 * 
	 * @param checkInfoList
	 * @return
	 * 
	 * @author 李鹏
	 * 
	 * @since 2016年4月25日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private Map<String, List<CheckInfo>> getUserCheckInfo(List<CheckInfo> checkInfoList) {
		Map<String, List<CheckInfo>> resultMap = new HashMap<String, List<CheckInfo>>();

		for (CheckInfo info : checkInfoList) {
			String date = info.getChackTime().substring(0, 10);
			if (!resultMap.containsKey(date)) {
				List<CheckInfo> list = new ArrayList<>();
				resultMap.put(date, list);
			}
			resultMap.get(date).add(info);
			Collections.sort(resultMap.get(date), new Comparator<CheckInfo>() {

				public int compare(CheckInfo arg0, CheckInfo arg1) {
					try {
						return DateUtils.parseDate(arg0.getChackTime(), "yyyy-MM-dd HH:mm:ss").compareTo(DateUtils.parseDate(arg1.getChackTime(), "yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
				}

			});

		}
		return resultMap;
	}		
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CheckInfo cc = new CheckInfo();
		cc.setChackTime("12321");
		cc.setCheckUserId("456");
		cc.setId(1234l);
		Class userCla = (Class) cc.getClass();
		Method[] methods = userCla.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")) {
				System.out.print("methodName:" + method.getName() + "\t");
				System.out.println("value:" + method.invoke(cc));// 得到get 方法的值
			}
		}
	}

}
