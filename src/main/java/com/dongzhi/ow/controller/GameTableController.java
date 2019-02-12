package com.dongzhi.ow.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.GameTable;
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.RanksService;
import com.dongzhi.ow.util.DateUtil;
import com.dongzhi.ow.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class GameTableController {

	@Autowired
	GameTableService gameTableService;
	@Autowired
	RanksService ranksService;
	@Autowired
	GameService gameService;
	
	@RequestMapping("admin_gametable_list")
	public String list(int gid, Model model,Page page) {
		Game g = gameService.get(gid);
		List<Ranks> rs = ranksService.list(gid);
		String param = "&gid="+gid;
		page.setParam(param);
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<GameTable> gbs = gameTableService.listDesc(gid);
		int total = (int) new PageInfo<>(gbs).getTotal();
		page.setTotal(total);
		List<GameTable> gbsList = gameTableService.list();
		
		//获取最后一次输入赛事表时间
		if(!gbsList.isEmpty()) {
			GameTable gb = gbsList.get(0);
			String datePattern = "yyyy-MM-dd HH:mm";
			DateFormat dateFormat = new SimpleDateFormat(datePattern);
			String strDate = dateFormat.format(gb.getGameDate());
			gb.setStrDate(strDate);
			model.addAttribute("gb", gb);
		}
		model.addAttribute("g", g);	
		model.addAttribute("gbs", gbs);
		model.addAttribute("rs", rs);
		model.addAttribute("gid", gid);
		model.addAttribute("page", page);
		return "admin/listGameTable";
	}
	
	@RequestMapping("admin_gametable_add")
	public String add(GameTable gb) throws ParseException {
		int gid = gb.getGid();
		try {
			String datePattern = "yyyy-MM-dd HH:mm";
			DateFormat dateFormat = new SimpleDateFormat(datePattern);
			Date gameDate = dateFormat.parse(gb.getStrDate());
			
			gb.setGameDate(gameDate);
			gameTableService.add(gb);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_gametable_list?gid="+gid;
	}
	
	@RequestMapping("admin_gametable_delete")
	public String delete(int id, Model model) {
		GameTable gb = gameTableService.get(id);
		int gid = gb.getGid();
		gameTableService.delete(id);
		return "redirect:/admin_gametable_list?gid="+gid;
	}

	@RequestMapping("admin_gametable_edit")
	public String edit(Model model, int id) {
		GameTable gb = gameTableService.get(id);
		Game g = gameService.get(gb.getGid());
		Date date = gb.getGameDate();
		
		String datePattern = "yyyy-MM-dd HH:mm";
		DateFormat dateFormat = new SimpleDateFormat(datePattern);
		String strDate = dateFormat.format(date);
		
		gb.setStrDate(strDate);
		model.addAttribute("g", g);
		model.addAttribute("gb", gb);
		return "admin/editGameTable";
	}
	
	@RequestMapping("admin_gametable_update")
	public String update(GameTable gb) throws ParseException {
		int gid = gb.getGid();
		try {
			String datePattern = "yyyy-MM-dd HH:mm";
			DateFormat dateFormat = new SimpleDateFormat(datePattern);
			Date gameDate = dateFormat.parse(gb.getStrDate());
			
			gb.setGameDate(gameDate);
			gameTableService.update(gb);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_gametable_list?gid="+gid;
	}
	
	@ResponseBody
	@RequestMapping("admin_gametable_gamedate")
	public String gamedate(String strDate) {
		String datePattern = "yyyy-MM-dd HH:mm";
		if(DateUtil.isRightDateStr(strDate, datePattern)) {
			return "true";
		}
		return "false";
	}
}
