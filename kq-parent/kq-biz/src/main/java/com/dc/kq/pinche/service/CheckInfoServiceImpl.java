package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.dao.CheckInfoDAO;
import com.dc.kq.pinche.dmo.CheckInfo;

@Service("checkInfoServiceImpl")
@Scope("singleton")
public class CheckInfoServiceImpl implements CheckInfoService {

	@Autowired
	private CheckInfoDAO dao;
	
	/**
	 * 功能描述：
	 *
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
	@Override
	public List<CheckInfo> selectByUserIdAndCheckMonth(String checkNum, String checkMonth) {
		
		List<CheckInfo> list = new ArrayList<>();
		try {
			list =  dao.selectByUserIdAndCheckMonth(checkNum,checkMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
	
}
