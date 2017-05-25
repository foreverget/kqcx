package com.dc.kq.pinche.util;

import com.dc.kq.pinche.wx.Template;

import net.sf.json.JSONObject;

/**
 * 微信发送模板消息
 * 
 * @author xiaogang
 *
 */
public class WxMsgUtil {
	/**
	 * 发送消息
	 * 
	 * @param token
	 * @param template
	 * @return
	 */
	public static boolean sendTemplateMsg(String token, Template template) {
		boolean flag = false;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
		String result = HttpUtil.httpRequest(requestUrl, "POST", template.toJSON());
		JSONObject jsonResult = JSONObject.fromObject(result);
		if (jsonResult != null) {
			int errorCode = jsonResult.getInt("errcode");
			String errorMessage = jsonResult.getString("errmsg");
			if (errorCode == 0) {
				flag = true;
			} else {
				System.out.println("模板消息发送失败:" + errorCode + "," + errorMessage);
				flag = false;
			}
		}
		return flag;

	}
}
