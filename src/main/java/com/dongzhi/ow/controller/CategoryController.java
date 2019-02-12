package com.dongzhi.ow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.CategoryService;
import com.dongzhi.ow.service.WebService;

@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	WebService webService;
	
	@RequestMapping("admin_category_list")
	public String list(Model model) {
		List<Category> cs = categoryService.list();
		model.addAttribute("cs", cs);
		return "admin/listCategory";
	}
	
	@RequestMapping("admin_categoryshow_list")
	public String listShow(Model model) {
		List<Category> cs = categoryService.listInit();
		model.addAttribute("cs", cs);
		return "admin/listCategory";
	}
	
	@RequestMapping("admin_category_add")
	public String add(Category c) {
		try {
			int order;
			List<Category> cs = categoryService.list();
			if(cs.isEmpty())
				order = 0;
			else
				order = cs.get(cs.size()-1).getCategoryOrder();
			order++;
			c.setCategoryOrder(order);
			categoryService.add(c);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_delete")
	public String delete(int id, Model model) {
		List<Web> ws = webService.list(id);
		categoryService.delete(id);
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_dodelete")
	@ResponseBody
	public String dodelete(int id) {
		List<Web> ws = webService.list(id);
		if(!ws.isEmpty()) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("admin_category_edit")
	public String edit(Model model, int id) {
		Category c = categoryService.get(id);
		model.addAttribute("c", c);
		return "admin/editCategory";
	}
	
	@RequestMapping("admin_category_update")
	public String update(Category c) {
		try {
			categoryService.update(c);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_down")
	public String down(int id) {
		Category c = categoryService.get(id);
		int order = c.getCategoryOrder();

		List<Category> cs = categoryService.list();
		Category clast = cs.get(cs.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(c.getId()!=clast.getId()) {
			//元素后一位元素往前移动1位
			Category cnext = categoryService.getOrder(order+1).get(0);
			cnext.setCategoryOrder(order);
			categoryService.update(cnext);
			//元素本身往后移动1位
			order++;
			c.setCategoryOrder(order);
			categoryService.update(c);
		}
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_up")
	public String up(int id) {
		Category c = categoryService.get(id);
		int order = c.getCategoryOrder();
		
		List<Category> cs = categoryService.list();
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Category cpre = categoryService.getOrder(order-1).get(0);
			cpre.setCategoryOrder(order);
			categoryService.update(cpre);
			//元素本身往前移动1位
			order--;
			c.setCategoryOrder(order);
			categoryService.update(c);
		}
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_hide")
	public String hide(int id) {
		Category c = categoryService.get(id);
		int order = c.getCategoryOrder();
		
		List<Category> cs = categoryService.listInit();
		Category clast = cs.get(cs.size()-1); //获取top列表最后一位元素
		 //判读是否是最后一位元素
		if (c.getId()!=clast.getId()) {
			cs = cs.subList(c.getCategoryOrder()-1, cs.size());	
			for(Category ce:cs) {
				ce.setCategoryOrder(ce.getCategoryOrder()-1);
				categoryService.update(ce);
			}
		}
		order=0;
		c.setCategoryOrder(order);
		categoryService.update(c);
		return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_show")
	public String show(int id) {
		Category c = categoryService.get(id);

		List<Category> cs = categoryService.listInit();
		for(Category ce:cs) {
			ce.setCategoryOrder(ce.getCategoryOrder()+1);
			categoryService.update(ce);
		}
		c.setCategoryOrder(1);
		categoryService.update(c);
		return "redirect:/admin_category_list";
	}
}
