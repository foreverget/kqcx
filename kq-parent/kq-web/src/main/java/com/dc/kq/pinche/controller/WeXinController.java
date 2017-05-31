package com.dc.kq.pinche.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dc.kq.pinche.common.Oauth2Token;
import com.dc.kq.pinche.dmo.UserInfo;
import com.dc.kq.pinche.service.OAuthService;
import com.dc.kq.pinche.service.UserService;
import com.dc.kq.pinche.util.WeiXinAccessTokenUtil;
import com.dc.kq.pinche.wx.Token;

/**
 * 微信controller
 * 
 * @author xiaogang
 *
 */
@Controller
@RequestMapping("/wx/")
public class WeXinController implements ApplicationListener<ContextRefreshedEvent> {
	public static Logger LOGGER = LoggerFactory.getLogger(WeXinController.class);
	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;
	@Autowired
	private OAuthService oAuthService;
	@Autowired
	private UserService userService;
	// 微信企业号ID
	public String appid = "wx71ede00e0504ae80";
	// 微信企业号凭证密钥
	public String appsecret = "cc075e0ae620412e87a4107ed2d9d799";

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
				Oauth2Token oauth2Token = oAuthService.getOauth2AccessToken(appid, appsecret, code);
				// 执行保存绑定
				String openId = oauth2Token.getOpenId();
				LOGGER.info("微信的openId=" + openId);
				// 根据openid查询用户表
				UserInfo userInfo = userService.selectUserByOpenId(openId);
				if (userInfo == null || StringUtils.isBlank(userInfo.getOpenId())) {// 用户不存在跳转到注册页面
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
				case "6":// 修改地址信息
					return "redirect:/addr/toUpdateAddr?openId=" + openId;
				case "7":// 修改车辆信息
					return "redirect:/car/toMyCarList?openId=" + openId;
				}
			}
		} else {
			LOGGER.error("获取网页授权code失败！");
		}
		return "";
	}

	public class TokenThread implements Runnable {

		public Token accessToken = null;

		public void run() {
			while (true) {
				try {
					accessToken = WeiXinAccessTokenUtil.getAccessToken(appid, appsecret);
					if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken())) {
						LOGGER.info("定时获取access_token成功并保存，有效时长" + accessToken.getExpiresIn() + "秒 accToken:"
								+ accessToken.getAccessToken());
						// 将token放入redis
						stringRedisTemplate.opsForValue().set("access_token", accessToken.getAccessToken());
						// 线程休眠6000秒,6000秒后刷新token，防止token过期
						Thread.sleep((accessToken.getExpiresIn() - 1200) * 1000);
					} else {
						// 如果access_token为null，60秒后再重新获取
						Thread.sleep(60 * 1000);
					}
				} catch (InterruptedException e) {
					try {
						// 如果出现异常，60秒后再重新获取
						Thread.sleep(60 * 1000);
					} catch (InterruptedException e1) {
						LOGGER.error("定时获取token异常:" + e1);
					}
					LOGGER.error("定时获取token异常:" + e);
				}
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new Thread(new TokenThread()).run();
	}
}
