package com.dc.kq.pinche.wx;

/**
 * jsapi票据
 * 
 * @author zhangwei
 *
 */
public class JsapiTicket {

	private int errcode;
	
	private String errmsg;
	
	private String ticket;
	
	private int expiresIn;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
