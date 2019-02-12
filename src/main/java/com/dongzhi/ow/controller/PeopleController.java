package com.dongzhi.ow.controller;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Social;
import com.dongzhi.ow.pojo.Type;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.PeopleService;
import com.dongzhi.ow.service.SocialService;
import com.dongzhi.ow.service.TypeService;
import com.dongzhi.ow.util.Filepath;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.Page;
import com.dongzhi.ow.util.UploadImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class PeopleController {

	@Autowired
	PeopleService peopleService;
	@Autowired
	SocialService socialService;
	@Autowired
	TypeService typeService;
	
	@RequestMapping("admin_people_list")
	public String list(int tid, Model model) {
		Type t = typeService.get(tid);
		List<People> ps = peopleService.list(tid);
		model.addAttribute("ps", ps);
		model.addAttribute("t", t);
		return "admin/listPeople";
	}
	
	@RequestMapping("admin_people_add")
	public String add(People p, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		try {
			int order;
			int tid = p.getTid();
			List<People> ps = peopleService.list(tid);
			if(ps.isEmpty())
				order = 0;
			else
				order = ps.get(ps.size()-1).getPeopleOrder();
			order++;
			p.setPeopleOrder(order);
			peopleService.add(p);
		} catch (Exception e) {
			return "admin/error";
		}
		
		File imageFolder = new File(new Filepath().path()+"img/peopleLogo");
		File file = new File(imageFolder, p.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 120, 120, file);
		return "redirect:/admin_people_list?tid="+ p.getTid();
	}
	
	@RequestMapping("admin_people_delete")
	public String delete(HttpSession session, int id) {
		People p = peopleService.get(id);
		int tid = p.getTid();
		peopleService.delete(id);
		//删除对应图片
		File imageFolder = new File(new Filepath().path()+"img/peopleLogo");
		File file = new File(imageFolder, p.getId()+".jpg");
		file.delete();
		return "redirect:/admin_people_list?tid="+tid;
	}
	
	
	@RequestMapping("admin_people_dodelete")
	@ResponseBody
	public String dodelete(int id) {
		List<Social> ss = socialService.list(id);
		if(!ss.isEmpty()) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("admin_people_edit")
	public String edit(Model model, int id) {
		People p = peopleService.get(id);
		Type t = typeService.get(p.getTid());
		List<Type> ts = typeService.list();
		model.addAttribute("t", t);
		model.addAttribute("ts", ts);
		model.addAttribute("p", p);
		return "admin/editPeople";
	}
	
	@RequestMapping("admin_people_update")
	public String update(People p, int oldTid){
		try {
			//判断是否更换分类
			if(p.getTid()!=oldTid) {
				int order = p.getPeopleOrder();
				
				//旧列表元素之后均往前移1位
				People pod = peopleService.getOrder(oldTid,order).get(0); //获取更新前的元素
				List<People> pods = peopleService.list(oldTid);	//获取旧列表
				People podlast = pods.get(pods.size()-1);	//获取旧列表最后一位元素	
				if (pod.getId()!= podlast.getId()) //判读是否是最后一位元素
					pods = pods.subList(pod.getPeopleOrder()-1, pods.size());	
				else
					pods = pods.subList(pods.size(), pods.size());
				
				for(People po:pods) {
					po.setPeopleOrder(po.getPeopleOrder()-1);
					peopleService.update(po);
				}
				
				//获取新列表序号
				int tid = p.getTid();
				List<People> pns = peopleService.list(tid);
				if(pns.isEmpty())
					order = 0;
				else
					order = pns.get(pns.size()-1).getPeopleOrder();
				order++;
				p.setPeopleOrder(order);
			}
			peopleService.update(p);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_people_list?tid="+ oldTid;
	}
	
	@RequestMapping("admin_people_down")
	public String down(int id) {
		People p = peopleService.get(id);
		int order = p.getPeopleOrder();
		int tid = p.getTid();
		
		List<People> ps = peopleService.list(tid);
		People plast = ps.get(ps.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(p.getId()!=plast.getId()) {
			//元素后一位元素往前移动1位
			People pnext = peopleService.getOrder(tid,order+1).get(0);
			pnext.setPeopleOrder(order);
			peopleService.update(pnext);
			//元素本身往后移动1位
			order++;
			p.setPeopleOrder(order);
			peopleService.update(p);
		}
		return "redirect:/admin_people_list?tid="+ p.getTid();
	}
	
	@RequestMapping("admin_people_up")
	public String up(int id) {
		People p = peopleService.get(id);
		int order = p.getPeopleOrder();
		int tid = p.getTid();
		
		List<People> ps = peopleService.list(tid);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			People ppre = peopleService.getOrder(tid,order-1).get(0);
			ppre.setPeopleOrder(order);
			peopleService.update(ppre);
			//元素本身往前移动1位
			order--;
			p.setPeopleOrder(order);
			peopleService.update(p);
		}
		return "redirect:/admin_people_list?tid="+ p.getTid();
	}
	
	@RequestMapping("admin_people_image")
	public String image(int id, Model model) {
		People p = peopleService.get(id);
		model.addAttribute("p", p);
		return "admin/imagePeople";
	}
	
	@RequestMapping("admin_people_imageupdate")
	public String imageUpdate(int id, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		People p = peopleService.get(id);
		
		File imageFolder = new File(new Filepath().path()+"img/peopleLogo");
		File file = new File(imageFolder, p.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 120, 120, file);
		return "redirect:/admin_people_list?tid="+ p.getTid();
	}
}
