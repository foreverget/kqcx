package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.dao.ScheduleInfoDAO;
import com.dc.kq.pinche.dmo.ScheduleInfo;

@Service
@Scope("singleton")
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

	@Autowired
	private ScheduleInfoDAO dao; 
	/**
	 * 功能描述： 通过绩效月份获得对应的排班信息
	 * 
	 * @param checkMonth
	 * @return
	 * 
	 * @author 李鹏
	 * 
	 * @since 2016年4月25日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Override
	public List<ScheduleInfo> selectByMonth(String checkMonth) {
		
		
		List<ScheduleInfo> list =  new ArrayList<>();
		try {
			list = dao.selectByMonth(checkMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
