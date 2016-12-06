package com.dc.kq.pinche.dmo;

import java.io.Serializable;

public class CheckInfo implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6224219648500194412L;

	private Long id;

    private String checkUserId;

    private String chackTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getChackTime() {
        return chackTime;
    }

    public void setChackTime(String chackTime) {
        this.chackTime = chackTime;
    }
}