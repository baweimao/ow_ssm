package com.dongzhi.ow.service;

import java.util.Date;
import java.util.List;

import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.Live;

public interface GameService {

	//官方赛事
	int top = 0;
	//第三方赛事
	int art = 1;
	
	void add(Game game);
	void delete(int id);
	void update(Game game);
	Game get(int id);
	List<Game> getOrder(int up, int order);
	List<Game> list();
	
	List<Game> listOrder(int up);
	List<Game> listOrderInit(int up);
	
	//赛事表直播网站初始化
	List<Game> initGame(Date date);
	
	//赛事队伍初始化
	void initGameRanks(List<Game> gs);
}
