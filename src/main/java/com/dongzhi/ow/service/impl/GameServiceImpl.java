package com.dongzhi.ow.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.GameMapper;
import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.GameExample;
import com.dongzhi.ow.pojo.GameTable;
import com.dongzhi.ow.pojo.Live;
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.service.ArticleService;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.LiveService;
import com.dongzhi.ow.service.RanksService;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	GameMapper gameMapper;
	@Autowired
	GameTableService gameTableService;
	@Autowired
	LiveService liveService;
	@Autowired
	RanksService ranksService;
	
	@Override
	public List<Game> list() {
		GameExample example = new GameExample();
		example.setOrderByClause("gameOrder asc");
		return gameMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Game> listOrder(int up) {
		GameExample example = new GameExample();
		example.createCriteria().andUpEqualTo(up);
		example.setOrderByClause("gameOrder asc");
		return gameMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Game> listOrderInit(int up) {
		GameExample example = new GameExample();
		example.createCriteria().andGameOrderNotEqualTo(0).andUpEqualTo(up);
		example.setOrderByClause("gameOrder asc");
		return gameMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void add(Game game) {
		gameMapper.insert(game);
	}
	
	@Override
	public void delete(int id) {
		gameMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Game get(int id) {
		return gameMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Game> getOrder(int up, int order) {
		GameExample example = new GameExample();
		example.createCriteria().andUpEqualTo(up).andGameOrderEqualTo(order);
		return gameMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void update(Game game) {
		gameMapper.updateByPrimaryKeySelective(game);
	}
	
	@Override
	public List<Game> initGame(Date date) {
		Date d1 = DateUtils.truncate(date, Calendar.DATE);
		
		Calendar c = new GregorianCalendar();
		c.setTime(d1);
		c.add(Calendar.DAY_OF_MONTH, 1);
		
		Date d2 = c.getTime();
		List<Game> gs = list();
		List<Game> noEmptyGs = new ArrayList<Game>();
		System.out.println(d1);
		System.out.println(d2);
		for(Game g:gs) {
			int gid = g.getId();
			List<GameTable> gts = gameTableService.listAsc(gid, d1, d2);
			if(!gts.isEmpty()) {
				g.setGts(gts);
				List<Live> ls = liveService.listInit(gid);
				g.setLs(ls);
				noEmptyGs.add(g);
			}
		}
		return noEmptyGs;
	}
	
	@Override
	public void initGameRanks(List<Game> gs) {
		for(Game g:gs) {
			int gid = g.getId();
			List<Ranks> rs = ranksService.listInit(gid);
			g.setRs(rs);
		}
		
	}
}
