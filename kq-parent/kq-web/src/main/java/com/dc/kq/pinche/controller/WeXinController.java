package com.dc.kq.pinche.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dc.kq.pinche.common.Oauth2Token;
import com.dc.kq.pinche.dmo.UserInfo;
import com.dc.kq.pinche.service.OAuthService;
import com.dc.kq.pinche.service.UserService;

/**
 * 微信controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/wx/")
public class WeXinController {
	public static Logger LOGGER = LoggerFactory.getLogger(WeXinController.class);

	@Autowired
	private OAuthService oAuthService;

	@Autowired
	private UserService userService;

	/**
	 * 根据code取得openId
	 * 
	 * @param appid
	 *            公众号的唯一标识
	 * @param secret
	 *            公众号的appsecret密钥
	 * @param code
	 *            code为换取access_token的票据
	 * @return
	 * 
	 */
	@RequestMapping("authorization")
	public String authorization(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 参数
		String code = request.getParameter("code");
		if (null != code && !"".equals(code)) {
			LOGGER.info("获取网页授权code=" + code);
			// 用户同意授权
			if (!"authdeny".equals(code)) {
				// 获取网页授权access_token
				Oauth2Token oauth2Token = oAuthService.getOauth2AccessToken("wxcbec67470e5bfae3",
						"6593f1a3ada82ee297e34b99743841f6", code);
				// 执行保存绑定
				String openId = oauth2Token.getOpenId();
				request.setAttribute("openId", openId);

				LOGGER.info("微信的openId=" + openId);
				// 根据openid查询用户表
				UserInfo userInfo = userService.selectUserByOpenId(openId);
				if (userInfo == null) {// 用户不存在跳转到注册页面
					return "redirect:/user/toRegister?openId=" + openId;

				}
				// 存在，根据state 跳转到不同的页面
				String state = request.getParameter("state");
				LOGGER.info("state=" + state);
				switch (state) {
				case "1":// 我
					return "redirect:/user/toUserInfo?openId=" + openId;
				case "2":// 我的订单
					return "redirect:/order/toMyOrder?openId=" + openId;
				case "3":// 意见反馈
					return "redirect:/user/toUserInfo?openId=" + openId;
				case "4":// 乘客
					return "redirect:/order/toTake?openId=" + openId;
				case "5":// 车主
					return "redirect:/order/toRelease?openId=" + openId;
				}
			}
		} else {
			LOGGER.info("获取网页授权code失败！");
		}
		return "";
	}
}
