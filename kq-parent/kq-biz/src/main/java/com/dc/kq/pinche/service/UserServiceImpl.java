package com.dc.kq.pinche.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dao.UserDAO;
import com.dc.kq.pinche.dmo.UserInfo;
import com.dc.kq.pinche.request.UserInfoRequest;

/**
 * 用户service实现类
 * 
 * @author xiaogang
 *
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	public static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	/**
	 * 根据openId查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	@Override
	public UserInfo selectUserByOpenId(String openId) {
		return userDao.selectUserByOpenId(openId);
	}

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserInfo selectUserByUserId(long userId) {
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = userDao.selectUserByUserId(userId);
		} catch (Exception e) {
			LOGGER.error("selectUserByUserId error ", e);
		}
		return userInfo;
	}

	/**
	 * 注册用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@Override
	public BaseResponse registerUser(UserInfo userInfo) {
		BaseResponse resp = new BaseResponse();
		try {
			UserInfo user = userDao.selectUserByOpenId(userInfo.getOpenId());
			if (user == null) {
				long id = userDao.insert(userInfo);
				resp.setValue(id);
			} else {
				LOGGER.info("用户已存在直接登录");
			}
		} catch (Exception e) {
			LOGGER.error("registeUser error ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
		}
		return resp;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param openId
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public BaseResponse saveUser(UserInfoRequest userInfo) {
		BaseResponse resp = new BaseResponse();
		try {
			userDao.updateUser(userInfo.getOpenId(), userInfo.getKey(), userInfo.getValue());
		} catch (Exception e) {
			LOGGER.error("saveUser error ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
		}
		return resp;
	}
}
