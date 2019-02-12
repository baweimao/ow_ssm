package com.dongzhi.ow.controller;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dongzhi.ow.pojo.Article;
import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.Foot;
import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.Type;
import com.dongzhi.ow.service.ArticleService;
import com.dongzhi.ow.service.CategoryService;
import com.dongzhi.ow.service.FootService;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.NewsService;
import com.dongzhi.ow.service.TypeService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("")
public class ForeController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	GameService gameService;
	@Autowired
	NewsService newsService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	TypeService typeService;
	@Autowired
	GameTableService gameTableService;
	@Autowired
	FootService footService;
	
	@RequestMapping("")
	public String def(Model model) {
		List<News> ns = newsService.listOrderInit(newsService.top);
		newsService.initNews(ns);
		Date date = new Date();
		List<Category> cs = categoryService.listInit();
		List<Type> ts = typeService.listInit();

		model.addAttribute("ns", ns);
		model.addAttribute("cs", cs);
		model.addAttribute("ts", ts);
		return "/home";
	}
	
	@RequestMapping("home")
	public String home(Model model) {
		List<News> ns = newsService.listOrderInit(newsService.top);
		newsService.initNews(ns);
		Date date = new Date();
		List<Category> cs = categoryService.listInit();
		List<Type> ts = typeService.listInit();

		model.addAttribute("ns", ns);
		model.addAttribute("cs", cs);
		model.addAttribute("ts", ts);
		return "/home";
	}
	
	@ResponseBody
	@RequestMapping("gameTable")
	public List<Game> gameTable(@RequestParam("date") Date date){
		List<Game> gs = gameService.initGame(date);
		return gs;
	}
	
	@RequestMapping("to_news")
	public String toNews(int id, Model model) {
		List<Article> as = articleService.listOrder(articleService.recommend);
		List<News> ns = newsService.listInit();
		int nsSize = ns.size();
		int location = id;
		model.addAttribute("location", location);
		model.addAttribute("nsSize", nsSize);
		model.addAttribute("as", as);
		return "/news";
	}
	
	@RequestMapping("news")
	public String news(Model model) {
		List<Article> as = articleService.listOrder(articleService.recommend);
		List<News> ns = newsService.listInit();
		int nsSize = ns.size();
		
		model.addAttribute("nsSize", nsSize);
		model.addAttribute("as", as);
		return "/news";
	}
	
	@ResponseBody
	@RequestMapping("loadNews")
	public List<News> loadNews(@RequestParam("count") int count){
		PageHelper.offsetPage(0, count);
		List<News> ns = newsService.listInit();
		newsService.initNews(ns);
		return ns;
	}
	
	@RequestMapping("title")
	public String title(Model model) {
		List<Article> asTop = articleService.listOrderInit(articleService.top);
		articleService.initTitle(asTop);
		List<Article> as = articleService.listNotInit(articleService.top);
		int asSize = as.size();

		model.addAttribute("asSize", asSize);
		model.addAttribute("asTop", asTop);
		return "/title";
	}
	
	@ResponseBody
	@RequestMapping(value = "loadTitle")
	public List<Article> loadTitle(@RequestParam("count") int count) {
		PageHelper.offsetPage(0, count);
		List<Article> as = articleService.listNotInit(articleService.top);
		articleService.initTitle(as);
		return as;
	}
	
	@RequestMapping("article")
	public String article(int id, Model model) {
		Article a = articleService.get(id);
		articleService.initArticle(a);
		model.addAttribute("a", a);
		return "/article";
	}
	
	@RequestMapping("event")
	public String event(Model model) {
		List<Game> gsTop = gameService.listOrderInit(gameService.top);
		List<Game> gsArt = gameService.listOrderInit(gameService.art);
		model.addAttribute("gsTop", gsTop);
		model.addAttribute("gsArt", gsArt);
		gameService.initGameRanks(gsTop);
		gameService.initGameRanks(gsArt);
		return "/event";
	}
	
	@RequestMapping("info")
	public String info(int id, Model model) {
		Foot f = footService.get(id);
		model.addAttribute("f", f);
		return "/info";
	}

}
