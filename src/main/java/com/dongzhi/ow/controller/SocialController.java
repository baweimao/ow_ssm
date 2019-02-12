package com.dongzhi.ow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Social;
import com.dongzhi.ow.pojo.Type;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.PeopleService;
import com.dongzhi.ow.service.SocialService;
import com.dongzhi.ow.service.TypeService;
import com.dongzhi.ow.service.WebService;

@Controller
@RequestMapping("")
public class SocialController {

	@Autowired
	SocialService socialService;
	@Autowired
	PeopleService peopleService;
	@Autowired
	WebService webService;
	@Autowired
	TypeService typeService;
	
	@RequestMapping("admin_social_list")
	public String list(int pid, Model model) {
		List<Social> ss = socialService.list(pid);	
		for(Social s:ss) {
			int wid = s.getWid();
			Web w = webService.get(wid);
			s.setWeb(w);
		}
		People p = peopleService.get(pid);
		Type t = typeService.get(p.getTid());	
		List<Web> ws = webService.list();	
		model.addAttribute("ss", ss);
		model.addAttribute("p", p);
		model.addAttribute("t", t);
		model.addAttribute("ws", ws);
		return "admin/listSocial";
	}
	
	@RequestMapping("admin_social_add")
	public String add(Social s) {
		int order;
		int pid = s.getPid();
		try {
			List<Social> ss = socialService.list(pid);
			if(ss.isEmpty())
				order = 0;
			else
				order = ss.get(ss.size()-1).getSocialOrder();
			order++;
			s.setSocialOrder(order);
			socialService.add(s);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_social_list?pid="+pid;
	}
	
	@RequestMapping("admin_social_delete")
	public String delete(int id, Model model) {
		Social s = socialService.get(id);
		int pid = s.getPid();
		socialService.delete(id);
		return "redirect:/admin_social_list?pid="+pid;
	}
	
	@RequestMapping("admin_social_edit")
	public String edit(Model model, int id) {
		Social s = socialService.get(id);
		Web w = webService.get(s.getWid());
		People p = peopleService.get(s.getPid());
		Type t = typeService.get(p.getTid());
		model.addAttribute("p", p);
		model.addAttribute("t", t);
		model.addAttribute("s", s);
		model.addAttribute("w", w);
		return "admin/editSocial";
	}
	
	@RequestMapping("admin_social_update")
	public String update(Social s) {
		int pid = s.getPid();
		try {
			socialService.update(s);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_social_list?pid="+pid;
	}
	
	@RequestMapping("admin_social_down")
	public String down(int id) {
		Social s = socialService.get(id);
		int pid = s.getPid();
		int order = s.getSocialOrder();

		List<Social> ss = socialService.list(pid);
		Social slast = ss.get(ss.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(s.getId()!=slast.getId()) {
			//元素后一位元素往前移动1位
			Social snext = socialService.getOrder(pid,order+1).get(0);
			snext.setSocialOrder(order);
			socialService.update(snext);
			//元素本身往后移动1位
			order++;
			s.setSocialOrder(order);
			socialService.update(s);
		}
		return "redirect:/admin_social_list?pid="+pid;
	}
	
	@RequestMapping("admin_social_up")
	public String up(int id) {
		Social s = socialService.get(id);
		int pid = s.getPid();
		int order = s.getSocialOrder();

		List<Social> ss = socialService.list(pid);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Social spre = socialService.getOrder(pid,order-1).get(0);
			spre.setSocialOrder(order);
			socialService.update(spre);
			//元素本身往前移动1位
			order--;
			s.setSocialOrder(order);
			socialService.update(s);
		}
		return "redirect:/admin_social_list?pid="+pid;
	}

}
