package com.dongzhi.ow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.dongzhi.ow.pojo.Article;
import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.ArticleService;
import com.dongzhi.ow.service.WebService;
import com.dongzhi.ow.util.Page;
import com.dongzhi.ow.util.Uploader;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	WebService webService;
	
	@RequestMapping("admin_article_list")
	public String list(Model model, Page page) {

		//推荐文章集合
		List<Article> asRec = articleService.listOrder(articleService.recommend);
		articleService.initTitle(asRec);
		
		//置顶文章集合
		List<Article> asTop = articleService.listOrder(articleService.top);
		articleService.initTitle(asTop);
		
		//正常文章集合
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Article> as = articleService.list(articleService.art);
		articleService.initTitle(as);
		
		int total = (int) new PageInfo<>(as).getTotal();
		page.setTotal(total);
		List<Web> ws = webService.list();
		model.addAttribute("asRec", asRec);
		model.addAttribute("asTop", asTop);
		model.addAttribute("as", as);
		model.addAttribute("ws", ws);
		model.addAttribute("page", page);
		return "admin/listArticle";
	}
	
	@RequestMapping("admin_article_add")
	public String add(Article a){
		try {
			Date d = new Date();
			int up = 0;
			a.setArticleDate(d);
			a.setArticleOrder(1);
			a.setUp(up);
			articleService.add(a);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_delete")
	public String delete(int id, Model model) {
		articleService.delete(id);
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_edit")
	public String edit(Model model, int id) {
		Article a = articleService.get(id);
		List<Web> ws = webService.list();
		model.addAttribute("ws", ws);
		model.addAttribute("a", a);
		return "admin/editArticle";
	}
	
	@RequestMapping("admin_article_update")
	public String update(Article a) {
		try {
			articleService.update(a);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_article_list";
	}
	
	//文章置顶
	@RequestMapping("admin_article_top")
	public String top(int id) {
		Article a = articleService.get(id);
		int up = a.getUp();
		
		//如果原表是推荐循环减1
		if(up == articleService.recommend) {
			List<Article> as = articleService.listOrder(up);//获取列表
			Article alast = as.get(as.size()-1); //获取列表最后一位元素
			 //判读是否是最后一位元素
			if (a.getId()!= alast.getId()) {
				as = as.subList(a.getArticleOrder()-1, as.size());	
				for(Article ae:as) {
					ae.setArticleOrder(ae.getArticleOrder()-1);
					articleService.update(ae);
				}
			}
		}
		
		//新表加1
		List<Article> as = articleService.listOrder(articleService.top);//获取列表
		for(Article ae:as) {
			ae.setArticleOrder(ae.getArticleOrder()+1);
			articleService.update(ae);
		}
		
		a.setArticleOrder(1);
		a.setUp(articleService.top);
		articleService.update(a);
		return "redirect:/admin_article_list";
	}
	
	//文章推荐
	@RequestMapping("admin_article_news")
	public String news(int id) {
		Article a = articleService.get(id);
		int up = a.getUp();
		
		//如果原表是置顶循环减1
		if(up == articleService.top) {
			List<Article> as = articleService.listOrder(up);//获取列表
			Article alast = as.get(as.size()-1); //获取列表最后一位元素
			 //判读是否是最后一位元素
			if (a.getId()!= alast.getId()) {
				as = as.subList(a.getArticleOrder()-1, as.size());	
				for(Article ae:as) {
					ae.setArticleOrder(ae.getArticleOrder()-1);
					articleService.update(ae);
				}
			}
		}
		
		//新表加1
		List<Article> as = articleService.listOrder(articleService.recommend);//获取列表
		for(Article ae:as) {
			ae.setArticleOrder(ae.getArticleOrder()+1);
			articleService.update(ae);
		}
		a.setArticleOrder(1);
		a.setUp(articleService.recommend);
		articleService.update(a);
		return "redirect:/admin_article_list";
	}
	
	//普通文章
	@RequestMapping("admin_article_art")
	public String art(int id) {
		Article a = articleService.get(id);
		int order = a.getArticleOrder();
		int up = a.getUp();//获取类别
		List<Article> as = articleService.listOrder(up);//获取列表
		Article alast = as.get(as.size()-1); //获取列表最后一位元素
		 //判读是否是最后一位元素
		if (a.getId()!= alast.getId()) {
			as = as.subList(a.getArticleOrder()-1, as.size());
			for(Article ae:as) {
				ae.setArticleOrder(ae.getArticleOrder()-1);
				articleService.update(ae);
			}
		}	
		a.setArticleOrder(1);
		a.setUp(articleService.art);
		articleService.update(a);
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_down")
	public String down(int id) {
		Article a = articleService.get(id);
		int up = a.getUp();//获取类别
		int order = a.getArticleOrder();

		List<Article> as = articleService.listOrder(up);
		Article alast = as.get(as.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(a.getId()!= alast.getId()) {
			//元素后一位元素往前移动1位
			Article anext = articleService.getOrder(up,order+1).get(0);
			anext.setArticleOrder(order);
			articleService.update(anext);
			//元素本身往后移动1位
			order++;
			a.setArticleOrder(order);
			articleService.update(a);
		}
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_up")
	public String up(int id) {
		Article a = articleService.get(id);
		int up = a.getUp();//获取类别
		int order = a.getArticleOrder();
		
		List<Article> as = articleService.listOrder(up);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			System.out.println(up);
			System.out.println(order);
			Article apre = articleService.getOrder(up,order-1).get(0);
			apre.setArticleOrder(order);
			articleService.update(apre);
			//元素本身往前移动1位
			order--;
			a.setArticleOrder(order);
			articleService.update(a);
		}
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_hide")
	public String hide(int id) {
		
		Article a = articleService.get(id);
		int order = a.getArticleOrder();
		int up = a.getUp();
		if(up!=articleService.art) {
			List<Article> as = articleService.listOrderInit(up);//获取列表
			Article alast = as.get(as.size()-1); //获取列表最后一位元素
			 //判读是否是最后一位元素
			if (a.getId()!= alast.getId()) {
				as = as.subList(a.getArticleOrder()-1, as.size());	
				for(Article ae:as) {
					ae.setArticleOrder(ae.getArticleOrder()-1);
					articleService.update(ae);
				}
			}	
		}
		a.setArticleOrder(0);
		articleService.update(a);
		return "redirect:/admin_article_list";
	}
	
	@RequestMapping("admin_article_show")
	public String show(int id) {
		
		Article a = articleService.get(id);
		int order = a.getArticleOrder();
		int up = a.getUp();
		if(up!=articleService.art) {
			List<Article> as = articleService.listOrderInit(up);
			for(Article ae:as) {
				ae.setArticleOrder(ae.getArticleOrder()+1);
				articleService.update(ae);
			}
		}
		a.setArticleOrder(1);
		articleService.update(a);
		return "redirect:/admin_article_list";
	}
	
//	/**
//	 * UMeditor 图片上传jsp转java
//	 * 
//	 **/
//	@ResponseBody   
//	@RequestMapping("imageUp")
//	public String imageUp(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
// 
//		Uploader up = new Uploader(request);
//	    up.setSavePath("upload");
//	    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
//	    up.setAllowFiles(fileType);
//	    up.setMaxSize(10000); //单位KB
//	    try {
//			up.upload(upfile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//	    String callback = request.getParameter("callback");
// 
//	    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
// 
//	    result = result.replaceAll( "\\\\", "\\\\" );
// 
//	    if(callback == null ){
//	        return result ;
//	    }else{
//	       return "<script>"+ callback +"(" + result + ")</script>";
//	    }
//	}
	
	/**
	 * Ueditor 服务器统一请求接口路径
	 * 
	 **/  
	@RequestMapping("config")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Content-Type" , "text/html");
		 
        String rootPath = request.getSession().getServletContext().getRealPath("/");
 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
