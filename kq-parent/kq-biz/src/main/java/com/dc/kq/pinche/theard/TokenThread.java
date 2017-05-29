package com.dc.kq.pinche.theard;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.dc.kq.pinche.util.WeiXinAccessTokenUtil;
import com.dc.kq.pinche.wx.Token;

/**
 * 定时获取微信access_token的线程
 * 
 * @author xiaogang
 *
 */
public class TokenThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	// 微信企业号ID
	public String appid = "wx71ede00e0504ae80";
	// 微信企业号凭证密钥
	public String appsecret = "cc075e0ae620412e87a4107ed2d9d799";
	public Token accessToken = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeiXinAccessTokenUtil.getAccessToken(appid, appsecret);
				if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken())) {
					log.info("定时获取access_token成功并保存，有效时长" + accessToken.getExpiresIn() + "秒 accToken:"
							+ accessToken.getAccessToken());
					// 线程休眠6000秒,6000秒后刷新token，防止token过期
					Thread.sleep((accessToken.getExpiresIn() - 1200) * 1000);
					// 将token放入redis
					redisTemplate.opsForValue().set("access_token", accessToken.getAccessToken());
				} else {
					// 如果access_token为null，60秒后再重新获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					// 如果出现异常，60秒后再重新获取
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("定时获取token异常:" + e1);
				}
				log.error("定时获取token异常:" + e);
			}
		}
	}
}