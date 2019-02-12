package com.dongzhi.ow.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class GameTable {
    private Integer id;

    private Integer rid_a;

    private Integer rid_b;

    private Date gameDate;

    private Integer gid;
    
    private Ranks ranks_a;
    
    private Ranks ranks_b;
    
    private String strDate;

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

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid_a() {
        return rid_a;
    }

    public void setRid_a(Integer rid_a) {
        this.rid_a = rid_a;
    }

    public Integer getRid_b() {
        return rid_b;
    }

    public void setRid_b(Integer rid_b) {
        this.rid_b = rid_b;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}