package com.dc.kq.pinche.task;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dc.kq.pinche.util.WeiXinAccessTokenUtil;
import com.dc.kq.pinche.wx.Token;


@Component
public class AccessTokenRefreshJob {
	
	public static Logger LOGGER = LoggerFactory.getLogger(AccessTokenRefreshJob.class);

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;
	
	// 微信企业号ID
	public String appid = "wx71ede00e0504ae80";
	// 微信企业号凭证密钥
	public String appsecret = "cc075e0ae620412e87a4107ed2d9d799";
	
	// 每1小时获取一次
	@Scheduled(cron="0 0 0/1 * * ? ")
	public void refresh(){
		Token accessToken = null;
		try {
			accessToken = WeiXinAccessTokenUtil.getAccessToken(appid, appsecret);
			if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken())) {
				LOGGER.info("定时获取access_token成功并保存，有效时长" + accessToken.getExpiresIn() + "秒 accToken:"
						+ accessToken.getAccessToken());
				// 将token放入redis
				stringRedisTemplate.opsForValue().set("access_token", accessToken.getAccessToken());
			}else{
				refresh();
			}
		} catch (Exception e) {
			LOGGER.error("定时获取accessToken异常:" ,e);
		}
	}

}
