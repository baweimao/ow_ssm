package com.dongzhi.ow.service;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import com.dongzhi.ow.pojo.GameTable;

public interface GameTableService {

	void add(GameTable gameTable);
	void delete(int id);
	void update(GameTable gameTable);
	GameTable get(int id);
	List<GameTable> list();
	List<GameTable> listAsc(int gid, Date date1, Date date2);
	List<GameTable> listDesc(int gid);

}
