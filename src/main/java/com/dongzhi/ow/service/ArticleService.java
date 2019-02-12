package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Article;

public interface ArticleService {
	//普通文章
	int art = 0;
	//置顶文章
	int top = 1;
	//推荐文章
	int recommend = 2;
	
	void add(Article article);
	void delete(int id);
	void update(Article article);
	Article get(int id);
	List<Article> getOrder(int up, int order);
	
	List<Article> list(int up);
	
	List<Article> listNot(int up);
	List<Article> listNotInit(int up);
	
	List<Article> listOrder(int up);
	List<Article> listOrderInit(int up);
	
	//文章序列初始化
	void initTitle(List<Article> as);
	//文章内容初始化
	void initArticle(Article a);
}
