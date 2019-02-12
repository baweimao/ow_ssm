package com.dongzhi.ow.pojo;

public class Live {
    private Integer id;

    private Integer gid;

    private Integer wid;

    private String url;

    private Integer liveOrder;

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

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
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

    public Integer getLiveOrder() {
        return liveOrder;
    }

    public void setLiveOrder(Integer liveOrder) {
        this.liveOrder = liveOrder;
    }
}