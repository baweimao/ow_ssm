package com.dongzhi.ow.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.ArticleMapper;
import com.dongzhi.ow.pojo.Article;
import com.dongzhi.ow.pojo.ArticleExample;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.ArticleService;
import com.dongzhi.ow.service.WebService;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	WebService webService;
	
	@Override
	public List<Article> list(int up) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andUpEqualTo(up);
		example.setOrderByClause("articleDate desc");
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Article> listOrder(int up) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andUpEqualTo(up);
		example.setOrderByClause("articleOrder asc");
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Article> listOrderInit(int up) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andArticleOrderNotEqualTo(0).andUpEqualTo(up);
		example.setOrderByClause("articleOrder asc");
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Article> listNot(int up) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andUpNotEqualTo(up);
		example.setOrderByClause("articleDate desc");
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<Article> listNotInit(int up) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andArticleOrderNotEqualTo(0).andUpNotEqualTo(up);
		example.setOrderByClause("articleDate desc");
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void initTitle(List<Article> as) {
		for(Article a:as) {
			//初始化格式化时间
			Date d = a.getArticleDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String fmtDate = format.format(d);
			a.setFmtDate(fmtDate);
			//初始化web
			int wid = a.getWid();
			Web w = webService.get(wid);
			a.setWeb(w);
			//初始化img
			String content = a.getContent();
			Pattern pt = Pattern.compile("(src=\"[^\"]*\")");
			Matcher matcher = pt.matcher(content);
			String str;
			if(matcher.find()) {
				str = matcher.group();
//				System.out.println(str);
				str = str.substring(5,str.length()-1);
//				System.out.println(str);
			}
			else {
				str = "img/news/01.jpg";
			}
			a.setImgUrl(str);
		}
	}
	
	@Override
	public void initArticle(Article a) {
		//初始化格式化时间
		Date d = a.getArticleDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fmtDate = format.format(d);
		a.setFmtDate(fmtDate);
		//初始化web
		int wid = a.getWid();
		Web w = webService.get(wid);
		a.setWeb(w);
	}
	
	@Override
	public void add(Article article) {
		articleMapper.insert(article);
	}
	
	@Override
	public void delete(int id) {
		articleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Article get(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Article> getOrder(int up, int order) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andUpEqualTo(up).andArticleOrderEqualTo(order);
		return articleMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void update(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}
}
