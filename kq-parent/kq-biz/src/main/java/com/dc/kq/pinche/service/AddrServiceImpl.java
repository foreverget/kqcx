package com.dc.kq.pinche.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.common.ResponseEnum;
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
	 * 根据用户Id获取地址列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	@Override
	public BaseResponse getAddrList(long userId, int pageNo) {
		BaseResponse resp = new BaseResponse();
		try {
			int startPage = Constants.PAGE_SIZE * pageNo;
			List<AddrInfo> list = addrDao.selectAddrListByParam(userId, startPage, Constants.PAGE_SIZE);
			if (!CollectionUtils.isEmpty(list)) {
				resp.setValue(list);
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
				LOGGER.info("getAddrList ::: " + ResponseEnum.LIST_EMPTY.getMemo());
			}
		} catch (Exception e) {
			LOGGER.error("selectUserByOpenId error ", e);
		}
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
	 * @param userId
	 * @param id
	 * @return
	 */
	@Override
	public BaseResponse delete(long userId, long id) {
		BaseResponse resp = new BaseResponse();
		try {
			addrDao.delete(userId, id);
		} catch (Exception e) {
			LOGGER.error("delete addr error ", e);
		}
		return resp;
	}
}
