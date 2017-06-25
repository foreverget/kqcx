package com.dc.kq.pinche.wx;

import java.io.Serializable;

/**
 * jsapi签名
 * 
 * @author zhangwei
 *
 */
public class JsapiSignature implements Serializable {
	
	private static final long serialVersionUID = -1L;

	private String appId;

	private String nonceStr;

	private long timestamp;

	private String url;

	private String signature;

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
