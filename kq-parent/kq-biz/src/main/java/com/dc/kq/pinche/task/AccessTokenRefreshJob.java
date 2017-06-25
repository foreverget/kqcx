package com.dc.kq.pinche.task;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.util.WeiXinAccessTokenUtil;
import com.dc.kq.pinche.wx.JsapiTicket;
import com.dc.kq.pinche.wx.Token;

@Component
public class AccessTokenRefreshJob {

	public static Logger LOGGER = LoggerFactory.getLogger(AccessTokenRefreshJob.class);

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	// 每1小时获取一次
	@Scheduled(cron = "0 0 0/1 * * ? ")
	public void refresh() {
		Token accessToken = null;
		try {
			accessToken = WeiXinAccessTokenUtil.getAccessToken(Constants.WX_APP_ID, Constants.WX_APP_SECRET);
			if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken())) {
				LOGGER.info("定时获取access_token成功并保存，有效时长" + accessToken.getExpiresIn() + "秒 accToken:"
						+ accessToken.getAccessToken());
				// 将token放入redis
				stringRedisTemplate.opsForValue().set("access_token", accessToken.getAccessToken());
				// 获取jsapi票据
				JsapiTicket jsapiTicket = WeiXinAccessTokenUtil.getJsapiTicket(accessToken.getAccessToken());
				stringRedisTemplate.opsForValue().set("jsapi_ticket", jsapiTicket.getTicket());

				LOGGER.info("定时获取jsapi_ticket成功并保存，有效时长" + jsapiTicket.getExpiresIn() + "秒 jsapiTicket:"
						+ jsapiTicket.getTicket());
			} else {
				refresh();
			}
		} catch (Exception e) {
			LOGGER.error("定时获取accessToken、jsapiTicket异常:", e);
		}
	}

}
