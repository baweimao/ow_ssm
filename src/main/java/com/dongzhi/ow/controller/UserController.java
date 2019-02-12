package com.dongzhi.ow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.dongzhi.ow.pojo.User;
import com.dongzhi.ow.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("admin")
	public String admin() {
		return "admin/adminHome";
	}
	
	@RequestMapping("admin_user_login")
	public String login(User user, Model model,HttpSession session) {
		String name = HtmlUtils.htmlEscape(user.getName());
		if(name == null) {
			model.addAttribute("msg", "账号密码不能为空");
			return "admin/adminHome";
		}
		user = userService.get(name, user.getPassword());
		
		if(null == user) {
			model.addAttribute("msg", "账号密码错误");
			return "admin/adminHome";
		}
		session.setAttribute("user", user);
		return "redirect:admin_category_list";
	}
}
