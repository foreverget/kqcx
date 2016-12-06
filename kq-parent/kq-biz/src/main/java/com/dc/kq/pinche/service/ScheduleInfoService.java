package com.dc.kq.pinche.service;

import java.util.List;

import com.dc.kq.pinche.dmo.ScheduleInfo;


public interface ScheduleInfoService {

	List<ScheduleInfo> selectByMonth(String checkMonth);

}
