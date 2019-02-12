package com.dongzhi.ow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.Live;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.LiveService;
import com.dongzhi.ow.service.WebService;

@Controller
@RequestMapping("")
public class LiveController {

	@Autowired
	LiveService liveService;
	@Autowired
	WebService webService;
	@Autowired
	GameService gameService;
	
	@RequestMapping("admin_live_list")
	public String list(int gid, Model model) {
		Game g = gameService.get(gid);
		List<Web> ws = webService.list();
		List<Live> ls = liveService.list(gid);
		for(Live l:ls) {
			int wid = l.getWid();
			Web w = webService.get(wid);
			l.setWeb(w);
		}
		model.addAttribute("g", g);
		model.addAttribute("ls", ls);
		model.addAttribute("ws", ws);
		model.addAttribute("gid", gid);
		return "admin/listLive";
	}
	
	@RequestMapping("admin_live_add")
	public String add(Live l) {
		int gid = l.getGid();
		int order;
		try {
			List<Live> ls = liveService.list(gid);
			if(ls.isEmpty())
				order = 0;
			else
				order = ls.get(ls.size()-1).getLiveOrder();
			order++;
			l.setLiveOrder(order);
			liveService.add(l);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_live_list?gid="+gid;
	}
	
	@RequestMapping("admin_live_delete")
	public String delete(int id, Model model) {
		Live l = liveService.get(id);
		int gid = l.getGid();
		liveService.delete(id);
		return "redirect:/admin_live_list?gid="+gid;
	}

	@RequestMapping("admin_live_edit")
	public String edit(Model model, int id) {
		Live l = liveService.get(id);
		Game g = gameService.get(l.getGid());
		model.addAttribute("g", g);
		model.addAttribute("l", l);
		return "admin/editLive";
	}
	
	@RequestMapping("admin_live_update")
	public String update(Live l) {
		int gid = l.getGid();
		try {
			liveService.update(l);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_live_list?gid="+gid;
	}
	
	@RequestMapping("admin_live_down")
	public String down(int id) {
		Live l = liveService.get(id);
		int gid = l.getGid();
		int order = l.getLiveOrder();

		List<Live> ls = liveService.list(gid);
		Live llast = ls.get(ls.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(l.getId()!=llast.getId()) {
			//元素后一位元素往前移动1位
			Live lnext = liveService.getOrder(gid, order+1).get(0);
			lnext.setLiveOrder(order);
			liveService.update(lnext);
			//元素本身往后移动1位
			order++;
			l.setLiveOrder(order);
			liveService.update(l);
		}
		return "redirect:/admin_live_list?gid="+gid;
	}
	
	@RequestMapping("admin_live_up")
	public String up(int id) {
		Live l = liveService.get(id);
		int gid = l.getGid();
		int order = l.getLiveOrder();
		
		List<Live> ls = liveService.list(gid);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Live lpre = liveService.getOrder(gid, order-1).get(0);
			lpre.setLiveOrder(order);
			liveService.update(lpre);
			//元素本身往前移动1位
			order--;
			l.setLiveOrder(order);
			liveService.update(l);
		}
		return "redirect:/admin_live_list?gid="+gid;
	}
}
