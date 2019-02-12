package com.dongzhi.ow.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.GameTableMapper;
import com.dongzhi.ow.pojo.GameTable;
import com.dongzhi.ow.pojo.GameTableExample;
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.RanksService;

@Service
public class GameTableServiceImpl implements GameTableService{

	@Autowired
	GameTableMapper gameTableMapper;
	@Autowired
	RanksService ranksService;
	
	@Override
	public List<GameTable> list() {
		GameTableExample example  = new GameTableExample();
		example.setOrderByClause("id desc");
		return gameTableMapper.selectByExample(example);
	}
	
	@Override
	public List<GameTable> listAsc(int gid,Date date1,Date date2){
		GameTableExample example  = new GameTableExample();
		example.createCriteria().andGameDateBetween(date1, date2).andGidEqualTo(gid);
		example.setOrderByClause("gameDate asc");
		List<GameTable> gts = gameTableMapper.selectByExample(example);
		for(GameTable gt:gts) {
			int rid_a = gt.getRid_a();
			int rid_b = gt.getRid_b();
			Ranks ranks_a = ranksService.get(rid_a);
			Ranks ranks_b = ranksService.get(rid_b);
			gt.setRanks_a(ranks_a);
			gt.setRanks_b(ranks_b);
		}
		return gts;
	}
	
	@Override
	public List<GameTable> listDesc(int gid) {
		GameTableExample example  = new GameTableExample();
		example.createCriteria().andGidEqualTo(gid);
		example.setOrderByClause("gameDate desc");
		List<GameTable> gts = gameTableMapper.selectByExample(example);
		for(GameTable gt:gts) {
			int rid_a = gt.getRid_a();
			int rid_b = gt.getRid_b();
			Ranks ranks_a = ranksService.get(rid_a);
			Ranks ranks_b = ranksService.get(rid_b);
			gt.setRanks_a(ranks_a);
			gt.setRanks_b(ranks_b);
		}
		return gts;
	}
	
	@Override
	public void add(GameTable gameTable) {
		gameTableMapper.insert(gameTable);
	}
	
	@Override
	public void delete(int id) {
		gameTableMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public GameTable get(int id) {
		return gameTableMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(GameTable gameTable) {
		gameTableMapper.updateByPrimaryKeySelective(gameTable);
	}

}
