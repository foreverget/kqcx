package com.dc.kq.pinche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.UserInfo;
import com.dc.kq.pinche.request.UserInfoRequest;
import com.dc.kq.pinche.service.UserService;

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
	 * 审核通过
	 */
	private final int STATUS_AUDIT_SUCCESS = 1;
	@Autowired
	private UserService userService;

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
		return userService.selectUserByOpenId(openId);
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/register";
	}

	/**
	 * 注册
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("register.json")
	@ResponseBody
	public BaseResponse register(@RequestBody UserInfoRequest userInfo) {
		UserInfo info = new UserInfo();
		info.setName(userInfo.getName());
		info.setMobile(userInfo.getMobile());
		info.setAddr(userInfo.getAddr());
		info.setEmail(userInfo.getEmail());
		info.setGender(userInfo.getGender());
		info.setOpenId(userInfo.getOpenId());
		info.setStatus(STATUS_AUDIT_SUCCESS);// TODO 目前初始为审核通过
		return userService.registerUser(info);
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
		return userService.selectUserByUserId(userId);
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
		return userService.saveUser(userId, key, keyValue);
	}

}
