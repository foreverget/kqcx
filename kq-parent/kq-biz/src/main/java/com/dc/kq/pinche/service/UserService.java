package com.dc.kq.pinche.service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.UserInfo;

/**
 * 用户service
 * 
 * @author xiaogang
 *
 */
public interface UserService {
	/**
	 * 根据openId查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	public BaseResponse selectUserByOpenId(String openId);

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public BaseResponse selectUserByUserId(long userId);

	/**
	 * 注册用户
	 * 
	 * @param userInfo
	 * @return
	 */
	public BaseResponse registeUser(UserInfo userInfo);

	/**
	 * 保存用户信息
	 * 
	 * @param userId
	 * @param key
	 * @param value
	 * @return
	 */
	public BaseResponse saveUser(long userId, String key, String value);
}
