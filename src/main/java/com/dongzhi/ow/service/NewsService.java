package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.Web;

public interface NewsService {

	//普通资讯
	int art = 0;
	//置顶资讯
	int top = 1;
	
	void add(News news);
	void delete(int id);
	void update(News news);
	News get(int id);
	List<News> getOrder(int up, int order);
	List<News> list(int up);
	List<News> listInit();
	
	List<News> listOrder(int up);
	List<News> listOrderInit(int up);
	
	void initNews(List<News> ns);
}
