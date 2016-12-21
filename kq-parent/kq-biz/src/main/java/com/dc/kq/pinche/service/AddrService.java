package com.dc.kq.pinche.service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.AddrInfo;

/**
 * 地址service
 * 
 * @author xiaogang
 *
 */
public interface AddrService {
	/**
	 * 根据用户Id获取地址列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	public BaseResponse getAddrList(long userId, int pageNo);

	/**
	 * 保存地址信息
	 * 
	 * @param addrInfo
	 * @return
	 */
	public BaseResponse save(AddrInfo addrInfo);

	/**
	 * 根据地址Id删除Addr信息
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	public BaseResponse delete(long userId,long id);

}
