package com.dongzhi.ow.pojo;

public class Social {
    private Integer id;

    private Integer pid;

    private Integer wid;

    private String url;

    private Integer socialOrder;
    
    private Web web;

    public Web getWeb() {
		return web;
	}

	public void setWeb(Web web) {
		this.web = web;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSocialOrder() {
        return socialOrder;
    }

    public void setSocialOrder(Integer socialOrder) {
        this.socialOrder = socialOrder;
    }
}