package com.dongzhi.ow.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Type;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.NewsService;
import com.dongzhi.ow.service.WebService;
import com.dongzhi.ow.util.Filepath;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.Page;
import com.dongzhi.ow.util.UploadImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class NewsController {

	@Autowired
	NewsService newsService;
	@Autowired
	WebService webService;
	
	@RequestMapping("admin_news_list")
	public String list(Model model, Page page) {
		
		//普通文章
		int art = 0;
		//置顶文章
		int top = 1;
		
		//置顶资讯集合
		List<News> nsTop = newsService.listOrder(newsService.top);
		newsService.initNews(nsTop);
		//正常资讯集合
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<News> ns = newsService.list(newsService.art);
		newsService.initNews(ns);
		int total = (int) new PageInfo<>(ns).getTotal();
		page.setTotal(total);
		
		List<Web> ws = webService.list();
		model.addAttribute("nsTop", nsTop);
		model.addAttribute("ns", ns);
		model.addAttribute("ws", ws);
		model.addAttribute("page", page);
		return "admin/listNews";
	}
	
	@RequestMapping("admin_news_add")
	public String add(News n, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		try {
			//判断是否插入内嵌视频
			String content = n.getContent();
			if(content.contains("iframe"))
				n.setVideo(1);
			else
				n.setVideo(0);
			
			Date d = new Date();
			int up = 0;
			n.setImg(1);
			n.setNewsOrder(1);
			n.setNewsDate(d);
			n.setUp(up);
			newsService.add(n);
		} catch (Exception e) {
			return "admin/error";
		}

		File imageFolder = new File(new Filepath().path()+"img/news");
		File file = new File(imageFolder, n.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_addNoPic")
	public String addNoPic(News n) {
		try {
			Date d = new Date();
			int up = 0;
			n.setImg(0);
			n.setNewsOrder(1);
			n.setNewsDate(d);
			n.setUp(up);
			newsService.add(n);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_delete")
	public String delete(HttpSession session, int id, Model model) {
		newsService.delete(id);
		//删除对应图片
		File imageFolder = new File(new Filepath().path()+"img/news");
		File file = new File(imageFolder, id+".jpg");
		file.delete();
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_edit")
	public String edit(Model model, int id) {
		News n = newsService.get(id);
		List<Web> ws = webService.list();
		model.addAttribute("ws", ws);
		model.addAttribute("n", n);
		return "admin/editNews";
	}
	
	@RequestMapping("admin_news_update")
	public String update(News n) {
		try {
			//判断是否插入内嵌视频
			String content = n.getContent();
			if(content.contains("iframe")) {
				n.setVideo(1);
				n.setUp(newsService.art);
				n.setNewsOrder(1);
			}
			else
				n.setVideo(0);
			newsService.update(n);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_top")
	public String top(int id) {
		News n = newsService.get(id);
		int up = n.getUp();
		List<News> ns = newsService.listOrder(newsService.top);//获取top列表
		if(!ns.isEmpty()) {
			for(News ne:ns) {
				ne.setNewsOrder(ne.getNewsOrder()+1);
				newsService.update(ne);
			}
		}
		n.setNewsOrder(1);
		n.setUp(newsService.top);
		newsService.update(n);
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_art")
	public String art(int id) {
		News n = newsService.get(id);
		int order = n.getNewsOrder();
		List<News> ns = newsService.listOrder(newsService.top);//获取top列表
		News nlast = ns.get(ns.size()-1); //获取top列表最后一位元素
		 //判读是否是最后一位元素
		if (n.getId()!= nlast.getId()) {
			ns = ns.subList(n.getNewsOrder()-1, ns.size());
			for(News ne:ns) {
				ne.setNewsOrder(ne.getNewsOrder()-1);
				newsService.update(ne);
			}
		}	
		n.setNewsOrder(1);
		n.setUp(newsService.art);
		newsService.update(n);
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_down")
	public String down(int id) {
		News n = newsService.get(id);
		int order = n.getNewsOrder();
		
		List<News> ns = newsService.listOrder(newsService.top);
		News nlast = ns.get(ns.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(n.getId()!= nlast.getId()) {
			//元素后一位元素往前移动1位
			News nnext = newsService.getOrder(newsService.top,order+1).get(0);
			nnext.setNewsOrder(order);
			newsService.update(nnext);
			//元素本身往后移动1位
			order++;
			n.setNewsOrder(order);
			newsService.update(n);
		}
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_up")
	public String up(int id) {
		News n = newsService.get(id);
		int order = n.getNewsOrder();
		
		List<News> ns = newsService.listOrder(newsService.top);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			News npre = newsService.getOrder(newsService.top,order-1).get(0);
			npre.setNewsOrder(order);
			newsService.update(npre);
			//元素本身往前移动1位
			order--;
			n.setNewsOrder(order);
			newsService.update(n);
		}
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_hide")
	public String hide(int id) {
		News n = newsService.get(id);
		int order = n.getNewsOrder();
		if(n.getUp()==newsService.top) {
			List<News> ns = newsService.listOrderInit(newsService.top);//获取top列表
			News nlast = ns.get(ns.size()-1); //获取top列表最后一位元素
			 //判读是否是最后一位元素
			if (n.getId()!= nlast.getId()) {
				ns = ns.subList(n.getNewsOrder()-1, ns.size());	
				for(News ne:ns) {
					ne.setNewsOrder(ne.getNewsOrder()-1);
					newsService.update(ne);
				}
			}	
		}
		n.setNewsOrder(0);
		newsService.update(n);
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_show")
	public String show(int id) {
		News n = newsService.get(id);
		int order = n.getNewsOrder();
		if(n.getUp()==newsService.top) {
			List<News> ns = newsService.listOrderInit(newsService.top);
			for(News ne:ns) {
				ne.setNewsOrder(ne.getNewsOrder()+1);
				newsService.update(ne);
			}
		}
		order=1;
		n.setNewsOrder(order);
		newsService.update(n);
		return "redirect:/admin_news_list";
	}
	
	@RequestMapping("admin_news_image")
	public String image(int id, Model model) {
		News n = newsService.get(id);
		model.addAttribute("n", n);
		return "admin/imageNews";
	}
	
	@RequestMapping("admin_news_imageupdate")
	public String imageUpdate(int id, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		News n = newsService.get(id);
		n.setImg(1);
		newsService.update(n);
		
		File imageFolder = new File(new Filepath().path()+"img/news");
		File file = new File(imageFolder, n.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		return "redirect:/admin_news_list";
	}

}
