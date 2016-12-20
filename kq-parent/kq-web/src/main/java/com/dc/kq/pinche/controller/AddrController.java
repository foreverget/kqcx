package com.dc.kq.pinche.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.AddrInfo;

/**
 * 地址controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/addr/")
public class AddrController {
	/**
	 * 获取常用地址信息
	 * 
	 * @param userId
	 * @param startPage
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("addrList.json")
	@ResponseBody
	public BaseResponse addrList(long userId, int startPage, String valueKey) {
		return null;
	}

	/**
	 * 保存地址信息
	 * 
	 * @param addrId
	 * @param start
	 * @param end
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("saveAddr.json")
	@ResponseBody
	public BaseResponse saveAddr(@RequestBody AddrInfo addrInfo, String valueKey) {
		return null;
	}

	/**
	 * 删除地址信息
	 * 
	 * @param user
	 * @param addrId
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("deleteAddr.json")
	@ResponseBody
	public BaseResponse deleteAddr(long user, long addrId, String valueKey) {
		return null;
	}
}
