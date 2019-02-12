package com.dongzhi.ow.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private Integer categoryOrder;
    
    private List<Web> ws;

    public List<Web> getWs() {
		return ws;
	}

	public void setWs(List<Web> ws) {
		this.ws = ws;
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

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }
}