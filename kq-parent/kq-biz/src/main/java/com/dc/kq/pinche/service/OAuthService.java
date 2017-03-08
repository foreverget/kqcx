package com.dc.kq.pinche.service;

import com.dc.kq.pinche.common.Oauth2Token;
import com.dc.kq.pinche.wx.SNSUserInfo;

public interface OAuthService {
	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return Oauth2Token
	 */
	public Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code);

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	public SNSUserInfo getSNSUserInfo(String accessToken, String openId);
}
