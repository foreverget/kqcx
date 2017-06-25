package com.dc.kq.pinche.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dc.kq.pinche.wx.JsapiTicket;
import com.dc.kq.pinche.wx.Token;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 获取微信access_token工具类
 * 
 * @author xiaogang
 *
 */
public class WeiXinAccessTokenUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinAccessTokenUtil.class);
	// 获取access_token的接口地址（GET） 限200(次/天)
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取jssdk票据
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static Token getAccessToken(String appid, String appsecret) {
		Token accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		String result = HttpUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (StringUtils.isNotBlank(result)) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			try {
				accessToken = new Token();
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				// 获取token失败
				log.info("获取token失败 errcode:" + jsonObject.getInt("errcode") + ",errmsg:"
						+ jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 获取 JsapiTicket
	 * 
	 * @param accessToken
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;
		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
		String result = HttpUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (StringUtils.isNotBlank(result)) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			try {
				jsapiTicket = new JsapiTicket();
				jsapiTicket.setTicket(jsonObject.getString("ticket"));
				jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				// 获取token失败
				log.info("获取jsapiTicket失败 errcode:" + jsonObject.getInt("errcode") + ",errmsg:"
						+ jsonObject.getString("errmsg"));
			}
		}
		return jsapiTicket;
	}
}