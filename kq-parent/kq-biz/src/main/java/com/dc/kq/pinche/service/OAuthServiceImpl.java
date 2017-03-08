package com.dc.kq.pinche.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dc.kq.pinche.common.Oauth2Token;
import com.dc.kq.pinche.util.HttpUtil;
import com.dc.kq.pinche.wx.SNSUserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class OAuthServiceImpl implements OAuthService {
	public static Logger LOGGER = LoggerFactory.getLogger(OAuthServiceImpl.class);

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return
	 */
	@Override
	public Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		Oauth2Token ot = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		LOGGER.info("requestUrl" + requestUrl);
		// 获取网页授权凭证
		String res = HttpUtil.httpRequest(requestUrl, HttpUtil.GET_METHOD, null);
		JSONObject jsonObject = JSONObject.fromObject(res);
		if (null != jsonObject) {
			try {
				ot = new Oauth2Token();
				ot.setAccessToken(jsonObject.getString("access_token"));
				ot.setExpiresIn(jsonObject.getInt("expires_in"));
				ot.setRefreshToken(jsonObject.getString("refresh_token"));
				ot.setOpenId(jsonObject.getString("openid"));
				ot.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				LOGGER.info("获取网页授权凭证失败,错误码:" + errorCode + ",错误提示:" + errorMsg);
			}
		}
		return ot;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		String res = HttpUtil.httpRequest(requestUrl, "GET", null);
		JSONObject jsonObject = JSONObject.fromObject(res);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				LOGGER.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}

}
