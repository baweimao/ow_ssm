package com.dongzhi.ow.pojo;

import java.util.List;

public class Type {
    private Integer id;

    private String name;

    private Integer typeOrder;
    
    private List<People> ps;

    public List<People> getPs() {
		return ps;
	}

	public void setPs(List<People> ps) {
		this.ps = ps;
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

    public Integer getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(Integer typeOrder) {
        this.typeOrder = typeOrder;
    }
}