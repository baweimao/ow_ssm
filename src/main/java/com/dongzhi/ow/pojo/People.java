package com.dongzhi.ow.pojo;

import java.util.List;

public class People {
    private Integer id;

    private String name;

    private String info;

    private Integer peopleOrder;

    private Integer tid;
    
    private List<Social> ss;

    public List<Social> getSs() {
		return ss;
	}

	public void setSs(List<Social> ss) {
		this.ss = ss;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getPeopleOrder() {
        return peopleOrder;
    }

    public void setPeopleOrder(Integer peopleOrder) {
        this.peopleOrder = peopleOrder;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}