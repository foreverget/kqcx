package com.dc.kq.pinche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.kq.pinche.common.BaseResponse;
import com.dc.kq.pinche.dmo.AddrInfo;
import com.dc.kq.pinche.service.AddrService;

/**
 * 地址controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/addr/")
public class AddrController {
	@Autowired
	private AddrService addrService;

	/**
	 * 获取常用地址信息
	 * 
	 * @param userId
	 * @param pageNo
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("addrList.json")
	@ResponseBody
	public BaseResponse addrList(long userId, int pageNo, String valueKey) {
		return addrService.getAddrList(userId, pageNo);
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
	@RequestMapping("save.json")
	@ResponseBody
	public BaseResponse save(@RequestBody AddrInfo addrInfo, String valueKey) {
		return addrService.save(addrInfo);
	}

	/**
	 * 删除地址信息
	 * 
	 * @param userId
	 * @param addrId
	 * @param valueKey
	 * @return
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public BaseResponse delete(long userId, long id, String valueKey) {
		return addrService.delete(userId,id);
	}
}
