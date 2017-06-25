package com.dc.kq.pinche.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import com.dc.kq.pinche.common.Constants;
import com.dc.kq.pinche.wx.JsapiSignature;

/**
 * jsapi签名
 * 
 * @author zhangwei
 *
 */
public class JsapiSignatureUtil {

	public static JsapiSignature sign(String jsapi_ticket, String url) {
		JsapiSignature jsapiSignature = new JsapiSignature();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			System.out.println("signature=" + signature);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		jsapiSignature.setAppId(Constants.WX_APP_ID);
		jsapiSignature.setTimestamp(Long.valueOf(timestamp));
		jsapiSignature.setNonceStr(nonce_str);
		jsapiSignature.setUrl(url);
		jsapiSignature.setSignature(signature);
		return jsapiSignature;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
