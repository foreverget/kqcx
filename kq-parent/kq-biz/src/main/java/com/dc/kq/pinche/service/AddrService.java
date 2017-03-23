package com.dc.kq.pinche.service;

import java.util.List;

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
	 * 根据openId获取地址列表
	 * 
	 * @param openId
	 * @return
	 */
	public List<AddrInfo> getAddrList(String openId);

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
	 * @param openId
	 * @param id
	 * @return
	 */
	public BaseResponse delete(String openId, long id);

}
