package com.dc.kq.pinche.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.common.ResponseEnum;
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
	public BaseResponse selectUserByOpenId(String openId) {
		BaseResponse resp = new BaseResponse();
		try {
			UserInfo userInfo = userDao.selectUserByOpenId(openId);
			if (null != userInfo) {
				resp.setValue(userInfo);
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
				LOGGER.info("selectUserByOpenId ::: " + ResponseEnum.LIST_EMPTY.getMemo());
			}
		} catch (Exception e) {
			LOGGER.error("selectUserByOpenId error ", e);
		}
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
		try {
			UserInfo userInfo = userDao.selectUserByUserId(userId);
			if (null != userInfo) {
				resp.setValue(userInfo);
			} else {
				resp.setEnum(ResponseEnum.LIST_EMPTY);
				LOGGER.info("selectUserByUserId ::: " + ResponseEnum.LIST_EMPTY.getMemo());
			}
		} catch (Exception e) {
			LOGGER.error("selectUserByUserId error ", e);
		}
		return resp;
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
			long id = userDao.insert(userInfo);
			resp.setValue(id);
		} catch (Exception e) {
			LOGGER.error("registeUser error ", e);
		}
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
		try {
			userDao.updateUser(userId, key, value);
		} catch (Exception e) {
			LOGGER.error("saveUser error ", e);
		}
		return resp;
	}
}
