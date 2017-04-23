package com.dc.kq.pinche.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	 * 跳转到编辑常用地址页面
	 * 
	 * @return
	 */
	@RequestMapping("toUpdateAddr")
	public String toUpdateAddr(HttpServletRequest request, String openId) {
		// 获取常用地址列表
		List<AddrInfo> list = addrService.getAddrList(openId);
		request.setAttribute("list", list);
		request.setAttribute("openId", openId);
		return "addr/updateAddr";
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
	@RequestMapping("saveAddr")
	@ResponseBody
	public BaseResponse save(@RequestBody AddrInfo addrInfo) {
		return addrService.save(addrInfo);
	}

	/**
	 * 删除地址信息
	 * 
	 * @param openId
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteAddr")
	@ResponseBody
	public BaseResponse deleteAddr(String openId, long id) {
		return addrService.delete(openId, id);
	}

	/**
	 * 出车发布页面--获取用户的常用地址信息
	 * 
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping("selectAddrList")
	@ResponseBody
	public BaseResponse selectAddrList(HttpServletRequest request, String openId) {
		return addrService.selectAddrList(openId);
	}
}
