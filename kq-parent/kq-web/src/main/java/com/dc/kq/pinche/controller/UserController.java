package com.dc.kq.pinche.controller;

import javax.servlet.http.HttpServletRequest;

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
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister(HttpServletRequest request,String openId) {
		request.setAttribute("openId", openId);
		return "user/register";
	}

	/**
	 * 注册
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("register")
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
	@RequestMapping("toUserInfo")
	public String toUserInfo(HttpServletRequest request,String openId) {
		UserInfo userInfo = userService.selectUserByOpenId(openId);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("openId", openId);
		return "user/userInfo";
	}

	/**
	 * 保存个人信息
	 * 
	 * @param openId
	 * @param key
	 * @param value
	 * @param keyValue
	 * @return
	 */
	public BaseResponse saveUser(String openId, String key, String value, String keyValue) {
		return userService.saveUser(openId, key, keyValue);
	}

}
