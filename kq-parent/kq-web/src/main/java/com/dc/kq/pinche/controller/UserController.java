package com.dc.kq.pinche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.UserInfo;

/**
 * 用户controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	/**
	 * 根据openId验证用户
	 * 
	 * @param openId
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("checkUser.json")
	@ResponseBody
	public BaseResponse checkUser(String openId, String valueKey) {
		return null;
	}

	/**
	 * 注册
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("registe.json")
	@ResponseBody
	public BaseResponse registe(@RequestBody UserInfo userInfo) {
		return null;
	}

	/**
	 * 获取个人信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("userInfo.json")
	@ResponseBody
	public BaseResponse userInfo(long userId) {
		return null;
	}

	/**
	 * 保存个人信息
	 * 
	 * @param userId
	 * @param key
	 * @param value
	 * @param keyValue
	 * @return
	 */
	public BaseResponse saveUser(long userId, String key, String value, String keyValue) {
		return null;
	}

	
}
