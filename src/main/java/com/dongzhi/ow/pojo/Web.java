package com.dongzhi.ow.pojo;

public class Web {
    private Integer id;

    private Integer cid;

    private String name;

    private String url;

    private Integer webOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getWebOrder() {
        return webOrder;
    }

    public void setWebOrder(Integer webOrder) {
        this.webOrder = webOrder;
    }
}