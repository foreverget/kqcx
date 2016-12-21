package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.dao.AddrDAO;
import com.dc.kq.pinche.dmo.AddrInfo;

/**
 * 地址service实现类
 * 
 * @author xiaogang
 *
 */
@Service("addrServiceImpl")
public class AddrServiceImpl implements AddrService {

	@Autowired
	private AddrDAO addrDao;

	/**
	 * 根据用户Id获取地址列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	@Override
	public BaseResponse getAddrList(long userId, int pageNo) {
		BaseResponse resp = new BaseResponse();
		int startPage = Constants.PAGE_SIZE * pageNo;
		List<AddrInfo> list = addrDao.selectAddrListByParam(userId, startPage, Constants.PAGE_SIZE);
		resp.setValue(list);
		return resp;
	}

	/**
	 * 保存地址信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	@Override
	public BaseResponse save(AddrInfo addrInfo) {
		BaseResponse resp = new BaseResponse();
		long id = addrDao.insert(addrInfo);
		resp.setValue(id);
		return resp;
	}

	/**
	 * 根据地址Id删除Addr信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	@Override
	public BaseResponse delete(long userId,long id) {
		BaseResponse resp = new BaseResponse();
		addrDao.delete(userId,id);
		return resp;
	}
}
