package com.dongzhi.ow.pojo;

import java.util.List;

public class Game {
    private Integer id;

    private String name;

    private String url;

    private Integer gameOrder;

    private String color;

    private Integer up;

    private String info;
    
    private List<Ranks> rs;

    private List<GameTable> gts;
    
    private List<Live> ls;
    
    public List<Live> getLs() {
		return ls;
	}

	public void setLs(List<Live> ls) {
		this.ls = ls;
	}

	public List<GameTable> getGts() {
		return gts;
	}

	public void setGts(List<GameTable> gts) {
		this.gts = gts;
	}

	public List<Ranks> getRs() {
		return rs;
	}

	public void setRs(List<Ranks> rs) {
		this.rs = rs;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getGameOrder() {
        return gameOrder;
    }

    public void setGameOrder(Integer gameOrder) {
        this.gameOrder = gameOrder;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}