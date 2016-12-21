package com.dc.kq.pinche.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dao.UserDAO;
import com.dc.kq.pinche.dmo.UserInfo;

/**
 * 用户service实现类
 * 
 * @author xiaogang
 *
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	/**
	 * 根据openId查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@Override
	public BaseResponse selectUserByOpenId(String openId) {
		BaseResponse resp = new BaseResponse();
		UserInfo userInfo = userDao.selectUserByOpenId(openId);
		resp.setValue(userInfo);
		return resp;
	}

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public BaseResponse selectUserByUserId(long userId) {
		BaseResponse resp = new BaseResponse();
		UserInfo userInfo = userDao.selectUserByUserId(userId);
		resp.setValue(userInfo);
		return resp;
	}

	/**
	 * 注册用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@Override
	public BaseResponse registeUser(UserInfo userInfo) {
		BaseResponse resp = new BaseResponse();
		long id = userDao.insert(userInfo);
		resp.setValue(id);
		return resp;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userId
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public BaseResponse saveUser(long userId, String key, String value) {
		BaseResponse resp = new BaseResponse();
		userDao.updateUser(userId, key, value);
		return resp;
	}
}
