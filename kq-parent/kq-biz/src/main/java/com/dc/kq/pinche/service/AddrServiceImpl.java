package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
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
	public static Logger LOGGER = LoggerFactory.getLogger(AddrServiceImpl.class);

	@Autowired
	private AddrDAO addrDao;

	/**
	 * 根据openId获取地址列表
	 * 
	 * @param openId
	 * @return
	 */
	@Override
	public List<AddrInfo> getAddrList(String openId) {
		List<AddrInfo> list  = new ArrayList<AddrInfo>();
		try {
			list = addrDao.selectAddrListByParam(openId);
		} catch (Exception e) {
			LOGGER.error("getAddrList error ", e);
		}
		return list;
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
		try {
			long id = addrDao.insert(addrInfo);
			resp.setValue(id);
		} catch (Exception e) {
			LOGGER.error("save addr error ", e);
		}
		return resp;
	}

	/**
	 * 根据地址Id删除Addr信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	@Override
	public BaseResponse delete(String openId, long id) {
		BaseResponse resp = new BaseResponse();
		try {
			addrDao.delete(openId, id);
		} catch (Exception e) {
			LOGGER.error("delete addr error ", e);
		}
		return resp;
	}
}
