package com.dongzhi.ow.pojo;

public class Ranks {
    private Integer id;

    private String name;

    private Integer gid;

    private Integer ranksOrder;
    
    private Ranks ranks_a;
    
    private Ranks ranks_b;

    public Ranks getRanks_a() {
		return ranks_a;
	}

	public void setRanks_a(Ranks ranks_a) {
		this.ranks_a = ranks_a;
	}

	public Ranks getRanks_b() {
		return ranks_b;
	}

	public void setRanks_b(Ranks ranks_b) {
		this.ranks_b = ranks_b;
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

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getRanksOrder() {
        return ranksOrder;
    }

    public void setRanksOrder(Integer ranksOrder) {
        this.ranksOrder = ranksOrder;
    }
}