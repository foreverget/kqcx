package com.dc.kq.pinche.service;

import java.util.List;

import com.dc.kq.pinche.dmo.CheckInfo;


public interface CheckInfoService {

	List<CheckInfo> selectByUserIdAndCheckMonth(String checkNum, String checkMonth);


}
