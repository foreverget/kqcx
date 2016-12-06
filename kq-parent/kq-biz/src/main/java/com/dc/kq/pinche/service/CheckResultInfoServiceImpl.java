package com.dc.kq.pinche.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.dao.CheckDetailDAO;
import com.dc.kq.pinche.dao.CheckResultInfoDAO;
import com.dc.kq.pinche.dmo.CheckDetail;
import com.dc.kq.pinche.dmo.CheckResultInfo;
import com.dc.kq.pinche.dmo.ScheduleInfo;

@Service("checkResultInfoServiceImpl")
@Scope("singleton")
public class CheckResultInfoServiceImpl implements  CheckResultInfoService {
	@Autowired
	private CheckDetailDAO checkDetailDao;
	
	@Autowired
	private CheckResultInfoDAO  checkResultInfoDao;
	@Override
	public void insertCheckResultInfo(ScheduleInfo scheduleInfo) {
		try {
			CheckResultInfo checkResultInfo = new CheckResultInfo();
			checkResultInfo.setCheckUserId(scheduleInfo.getCheckNum());
			//设置迟到次数，迟到扣款
			setLateInfo(checkResultInfo,scheduleInfo.getCheckNum());
			//设置早退次数，早退扣款
			setEarlyInfo(checkResultInfo,scheduleInfo.getCheckNum());
			//设置迟到和早退 扣款合计
			checkResultInfo.setLateEarlyDeduct(checkResultInfo.getLateDeduct()+checkResultInfo.getEarlyDeduct());
			//设置未打卡次数和扣款
			setNoSign(checkResultInfo,scheduleInfo.getCheckNum());
			//设置未打开天数及日期
			setNotChickInDays(checkResultInfo,scheduleInfo.getCheckNum());
			//保存checkResultInfo
			checkResultInfoDao.insert(checkResultInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置迟到信息
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	private void setLateInfo(CheckResultInfo checkResultInfo,String checkUserId ) throws ParseException{
		//查询迟到次数并计算迟到扣款
		List<CheckDetail> lateList= checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_LATE_STR);
		int lateTimes = lateList == null ? 0 :lateList.size();
		//查询迟到且早退的次数
		List<CheckDetail> lateAndEarlyList = checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_LATE_AND_EARLY_STR);
		int lateAndEarlyTimes = lateAndEarlyList == null ? 0 :lateAndEarlyList.size();	
		
		lateList.addAll(lateAndEarlyList);
		//设置迟到次数
		checkResultInfo.setLateTimes(lateTimes+lateAndEarlyTimes);
		DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
		//迟到五分钟以内次数
		int lessFiveMinute = 0 ;
		int realyLateTimes = 0;
		long lateDeduct =0;
		for(int j = 0 ; j<lateTimes;j++){
			CheckDetail checkDetail =lateList.get(j);
			//正常班
			if(CrmConstant.NORMAL_STR.equals(checkDetail.getScheduleType())){
				int beginTime = DateUtils.parseDate(df.format(checkDetail.getCheckBeginTime()),"HH:mm").getSeconds();
				int normalStart = CrmConstant.NORMAL_START.getSeconds();
				//判断迟到是否超过五分钟
				if(beginTime-normalStart <=300  && lessFiveMinute<3){
					lessFiveMinute++;	
				} 
				//超过三次 当做真是迟到次数，大于五分钟当做真是迟到次数
				else{
					realyLateTimes++;
				}
				
				//根据真实迟到次数算钱,真实迟到次数钱三次每次扣30，之后每次扣70
				if(realyLateTimes<=3){
					lateDeduct = realyLateTimes*30;
				}else{
					lateDeduct = 3*30 +(realyLateTimes-3)*70;
				}
			}
			//加班
			else if(CrmConstant.OVERTIME_STR.equals(checkDetail.getScheduleType())){
				int beginTime = DateUtils.parseDate(df.format(checkDetail.getCheckBeginTime()),"HH:mm").getSeconds();
				int normalStart = CrmConstant.OVERTIME_START.getSeconds();
				//判断迟到是否超过五分钟
				if(beginTime-normalStart <=300  && lessFiveMinute<3){
					lessFiveMinute++;	
				} 
				//超过三次 当做真是迟到次数，大于五分钟当做真是迟到次数
				else{
					realyLateTimes++;
				}
				
				//根据真实迟到次数算钱,真实迟到次数钱三次每次扣30，之后每次扣70
				if(realyLateTimes<=3){
					lateDeduct = realyLateTimes*30;
				}else{
					lateDeduct = 3*30 +(realyLateTimes-3)*70;
				}
			}
		}
		//设置迟到扣款
		checkResultInfo.setLateDeduct(lateDeduct);
	}
	/**
	 * 设置早退信息
	 */
	private void setEarlyInfo(CheckResultInfo checkResultInfo,String checkUserId ){
		//查询迟到次数并计算迟到扣款
		List<CheckDetail> earlyList= checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_EARLY_STR);
		int earlyTimes = earlyList == null ? 0 :earlyList.size();
		//查询迟到且早退的次数
		List<CheckDetail> lateAndEarlyList = checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_LATE_AND_EARLY_STR);
		int lateAndEarlyTimes = lateAndEarlyList == null ? 0 :lateAndEarlyList.size();		
		//设置迟到次数
		checkResultInfo.setEarlyTimes(earlyTimes+lateAndEarlyTimes);
		checkResultInfo.setEarlyDeduct(Long.valueOf((earlyTimes+lateAndEarlyTimes)*30));
	}
	/**
	 * 设置未打卡次数信息
	 */
	private void setNoSign(CheckResultInfo checkResultInfo,String checkUserId){
		//查询未打卡次数并计算扣款
		List<CheckDetail> noSignList= checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_NO_SIGN);
		int noSignTimes = noSignList == null ? 0 :noSignList.size();
		checkResultInfo.setNotCheckInTimes(noSignTimes);
		checkResultInfo.setNotCheckInDeduct(Long.valueOf((noSignTimes-1)*50));
	}
	/**
	 * 设置未打卡天数及日期
	 * @param checkResultInfo
	 * @param checkUserId
	 */
	private void setNotChickInDays(CheckResultInfo checkResultInfo,String checkUserId){
		//查询未打卡次数并计算扣款
		List<CheckDetail> noAbseList= checkDetailDao.selectCheckDetailListByCheckResultAndUserId(checkUserId,CrmConstant.CHECK_RESULT_NO_ABSE);
		int noAbseDays = noAbseList == null ? 0 :noAbseList.size();
		checkResultInfo.setNotCheckInDays(noAbseDays);
		String notChickDateStr="";
		for(CheckDetail checkDetail: noAbseList){
			notChickDateStr += checkDetail.getWorkDate();
		}
		checkResultInfo.setNotCheckInDate(notChickDateStr);
	}
	
}
