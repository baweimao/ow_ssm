package com.dongzhi.ow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Type;

import com.dongzhi.ow.service.PeopleService;
import com.dongzhi.ow.service.TypeService;

@Controller
@RequestMapping("")
public class TypeController {

	@Autowired
	TypeService typeService;
	@Autowired
	PeopleService peopleService;
	
	@RequestMapping("admin_type_list")
	public String list(Model model) {
		List<Type> ts = typeService.list();
		model.addAttribute("ts", ts);
		return "admin/listType";
	}
	
	@RequestMapping("admin_type_add")
	public String add(Type t) {
		try {
			int order;
			List<Type> ts = typeService.list();
			if(ts.isEmpty())
				order = 1;
			else
				order = ts.get(ts.size()-1).getTypeOrder();
			order++;
			t.setTypeOrder(order);
			typeService.add(t);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_delete")
	public String delete(int id, Model model) {
		typeService.delete(id);
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_dodelete")
	@ResponseBody
	public String dodelete(int id) {
		List<People> ps = peopleService.list(id);
		if(!ps.isEmpty()) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("admin_type_edit")
	public String edit(Model model, int id) {
		Type t = typeService.get(id);
		model.addAttribute("t", t);
		return "admin/editType";
	}
	
	@RequestMapping("admin_type_update")
	public String update(Type t) {
		try {
			typeService.update(t);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_down")
	public String down(int id) {
		Type t = typeService.get(id);
		int order = t.getTypeOrder();

		List<Type> ts = typeService.list();
		Type tlast = typeService.get(ts.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(t.getId()!=tlast.getId()) {
			//元素后一位元素往前移动1位
			Type tnext = typeService.getOrder(order+1).get(0);
			tnext.setTypeOrder(order);
			typeService.update(tnext);
			//元素本身往后移动1位
			order++;
			t.setTypeOrder(order);
			typeService.update(t);
		}
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_up")
	public String up(int id) {
		Type t = typeService.get(id);
		int order = t.getTypeOrder();
		List<Type> ts = typeService.list();
		if(order > 1) {
			//元素前一位元素往后移动1位
			Type tpre = typeService.getOrder(order-1).get(0);
			tpre.setTypeOrder(order);
			typeService.update(tpre);
			//元素本身往前移动1位
			order--;
			t.setTypeOrder(order);
			typeService.update(t);
		}
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_hide")
	public String hide(int id) {
		Type t = typeService.get(id);
		int order = t.getTypeOrder();

		List<Type> ts = typeService.listInit();
		Type tlast = ts.get(ts.size()-1); //获取top列表最后一位元素
		 //判读是否是最后一位元素
		if (t.getId()!=tlast.getId()) {
			ts = ts.subList(t.getTypeOrder()-1, ts.size());	
			for(Type te:ts) {
				te.setTypeOrder(te.getTypeOrder()-1);
				typeService.update(te);
			}
		}
		order=0;
		t.setTypeOrder(order);
		typeService.update(t);
		return "redirect:/admin_type_list";
	}
	
	@RequestMapping("admin_type_show")
	public String show(int id) {
		Type t = typeService.get(id);

		List<Type> ts = typeService.listInit();
		for(Type te:ts) {
			te.setTypeOrder(te.getTypeOrder()+1);
			typeService.update(te);
		}
		t.setTypeOrder(1);
		typeService.update(t);
		return "redirect:/admin_type_list";
	}
}

