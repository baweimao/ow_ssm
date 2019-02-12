<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
$(function() {
	
	//导航点击变色
	$("nav.top a.topa").each(function () {
	if(this.href==window.location.href)
		$(this).addClass('topClick');
	});
	
	//判断导航何时隐藏
	var p,t=0;
	$(document).scroll(function(){
	var p = $(window).scrollTop();
	if(t <= p){
	$('nav.top').addClass("navHide");
	}else{
	$('nav.top').removeClass("navHide");
	}
	t = p;
	});
	
});


//百度分享
window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"社区导航，赛事查询，联赛资讯，就来守望先锋观测站","bdMini":"1","bdMiniList":["tsina","weixin","qzone","sqq","tieba"],"bdPic":"img/web/logo.jpg","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"0","bdPos":"right","bdTop":"90"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];

</script>

<nav class="top" role="navigation">
	<div class="title">
		<a class="logo" href="home"><img class="logo" src="/img/web/logo.png"></a>
		<a class="topa" href="home">首页</a>
		<a class="topa" href="news">动态</a>
		<a class="topa" href="title">文章</a>
		<a class="topa" href="event">赛事介绍</a>
	</div>
</nav>