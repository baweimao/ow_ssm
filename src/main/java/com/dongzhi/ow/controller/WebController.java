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

import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.CategoryService;
import com.dongzhi.ow.service.WebService;
import com.dongzhi.ow.util.Filepath;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.Page;
import com.dongzhi.ow.util.UploadImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class WebController {
	@Autowired
	WebService webService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("admin_web_list")
	public String list(int cid, Model model,Page page) {
		Category c = categoryService.get(cid);
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Web> ws = webService.list(cid);
		int total = (int) new PageInfo<>(ws).getTotal();
		page.setTotal(total);
		model.addAttribute("ws", ws);
		model.addAttribute("c", c);
		return "admin/listWeb";
	}
	
	@RequestMapping("admin_web_add")
	public String add(Web w, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		try {
			int order;
			int cid = w.getCid();
			List<Web> ws = webService.list(cid);
			if(ws.isEmpty())
				order = 0;
			else
				order = ws.get(ws.size()-1).getWebOrder();
			order++;
			w.setWebOrder(order);
			webService.add(w);
		} catch (Exception e) {
			return "admin/error";
		}
		
		//图片上传
		File imageFolder = new File(new Filepath().path()+"/img/webLogo");
		File file = new File(imageFolder, w.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 50, 50, file);
		return "redirect:/admin_web_list?cid="+ w.getCid();
	}
	
	@RequestMapping("admin_web_delete")
	public String delete(HttpSession session, int id) {
		Web w = webService.get(id);
		int cid = w.getCid();
		if(id!=44) {
			webService.delete(id);
			//删除对应图片
			File imageFolder = new File(new Filepath().path()+"/img/webLogo");
			File file = new File(imageFolder, w.getId()+".jpg");
			file.delete();
		}
		return "redirect:/admin_web_list?cid="+cid;
	}
	
	@RequestMapping("admin_web_edit")
	public String edit(Model model, int id) {
		Web w = webService.get(id);
		Category c = categoryService.get(w.getCid());
		List<Category> cs = categoryService.list();
		model.addAttribute("c", c);
		model.addAttribute("cs", cs);
		model.addAttribute("w", w);
		return "admin/editWeb";
	}
	
	@RequestMapping("admin_web_update")
	public String update(Web w, int oldCid) {
		try {
			//判断是否更换分类
			if(w.getCid()!=oldCid) {
				int order = w.getWebOrder();
				
				//旧列表元素之后均往前移1位
				Web wod = webService.getOrder(oldCid,order).get(0); //获取更新前的元素
				List<Web> wods = webService.list(oldCid);	//获取旧列表
				Web wodlast = wods.get(wods.size()-1);	//获取旧列表最后一位元素	
				if (wod.getId()!= wodlast.getId()) //判读是否是最后一位元素
					wods = wods.subList(wod.getWebOrder()-1, wods.size());	
				else
					wods = wods.subList(wods.size(), wods.size());
				
				for(Web wo:wods) {
					wo.setWebOrder(wo.getWebOrder()-1);
					webService.update(wo);
				}
				
				//获取新列表序号
				int cid = w.getCid();
				List<Web> wns = webService.list(cid);
				if(wns.isEmpty())
					order = 0;
				else
					order = wns.get(wns.size()-1).getWebOrder();
				order++;
				w.setWebOrder(order);
			}
			webService.update(w);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_web_list?cid="+oldCid;
	}
	
	@RequestMapping("admin_web_down")
	public String down(int id) {
		Web w = webService.get(id);
		int cid = w.getCid();
		int order = w.getWebOrder();
		
		List<Web> ws = webService.list(cid);
		Web wlast = ws.get(ws.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(w.getId()!=wlast.getId()) {
			//元素后一位元素往前移动1位
			Web wnext = webService.getOrder(cid,order+1).get(0);
			wnext.setWebOrder(order);
			webService.update(wnext);
			//元素本身往后移动1位
			order++;
			w.setWebOrder(order);
			webService.update(w);
		}
		return "redirect:/admin_web_list?cid="+cid;
	}
	
	@RequestMapping("admin_web_up")
	public String up(int id) {
		Web w = webService.get(id);
		int cid = w.getCid();
		int order = w.getWebOrder();
		
		List<Web> ws = webService.list(cid);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Web wpre = webService.getOrder(cid,order-1).get(0);
			wpre.setWebOrder(order);
			webService.update(wpre);
			//元素本身往前移动1位
			order--;
			w.setWebOrder(order);
			webService.update(w);
		}
		return "redirect:/admin_web_list?cid="+cid;
	}
	
	@RequestMapping("admin_web_image")
	public String image(int id, Model model) {
		Web w = webService.get(id);
		model.addAttribute("w", w);
		return "admin/imageWeb";
	}
	
	@RequestMapping("admin_web_imageupdate")
	public String imageUpdate(int id, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		Web w = webService.get(id);
		int cid = w.getCid();
		
		//图片上传
		File imageFolder = new File(new Filepath().path()+"/img/webLogo");
		System.out.println(imageFolder.getPath());
		File file = new File(imageFolder, w.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 50, 50, file);
		return "redirect:/admin_web_list?cid="+cid;
	}
}
