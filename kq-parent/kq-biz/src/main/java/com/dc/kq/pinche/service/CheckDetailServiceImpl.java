package com.dc.kq.pinche.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.dao.CheckDetailDAO;
import com.dc.kq.pinche.dmo.CheckDetail;

@Service("checkDetailServiceImpl")
@Scope("singleton")
public class CheckDetailServiceImpl implements CheckDetailService {

	@Autowired
	private CheckDetailDAO checkDetailDAO;

	@Override
	public int insert(CheckDetail checkInfo) {
		checkDetailDAO.insert(checkInfo);
		return 0;
	}

}
