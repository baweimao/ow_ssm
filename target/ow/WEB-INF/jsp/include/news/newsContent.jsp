<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function() {
	
	//显示几条信息
	var count = 8;
	var size = Number($("#size").val());
	ajaxNews(count,size);
	
	//根据滚动条判断推荐块是否显示
	fixed();
	var h = $("div.newArticle").height();
	h += 100;
	
	$(document).scroll(function(){
	var p = $(window).scrollTop();
	if(h <= p){
	$('div.newArticle').addClass("newArticleFixed");
	}else{
	$('div.newArticle').removeClass("newArticleFixed");
	}
	});

	//根据窗口自动调整推荐块的定位
	$(window).resize(function() {
		fixed();
	});
	
	// 继续加载
	$("button").click(function() {
		count += 8;
		ajaxNews(count,size);
	});

});

/**
 * 自动调整推荐块的定位
 */
function fixed() {
	var left = [$(window).width()-1024]/2+774;
		if($(window).width()<1024){
			$('div.newArticle').hide();
		}
		else {
			$('div.newArticle').css("left", left);
			$('div.newArticle').show();
		}
}
	
/**
 * 异步加载资讯并写入代码块
 */
function ajaxNews(count,size) {
	var page = "loadNews";
	$.ajax({
		async: false,
		cache: false,
		type: "post",
		url:page,
		data:{"count":count},
		error:function(){
			alert("系统异常，请稍后访问");
		},
		success:function(result) {
			if(count >= size){
				$("button").html("已到结尾");
				$("button").attr("disabled", "disabled");
			}	
			var _html = insertDiv(result);
			$("div.newsContent").html(_html);
		}
	})
}
	
/**
 * 循环资讯代码块
 */
function insertDiv(date) {
	var _list = "";
	for (var i = 0; i < date.length; i++) {
		var n = date[i];
// 		console.log(n);
		var img = n.img;
		var t = n.newsDate;
		var d = new Date(t);
		var month = d.getMonth();
		month=month>9?month:"0"+month;
		var day = d.getDate();
		day=day>9?day:"0"+day;
		_list += '<div class="newsOne">';
		_list += '<div class="leftNews">';
		_list += '<div class="time">'+month+'月'+day+'日</div>';
		_list += '<div class="web">'+n.web.name+'</div>';
		_list += '</div>';
		_list += '<div class="middleNews">';
		_list += '<img class="img-circle" src="/img/webLogo/'+n.wid+'.jpg">';
		_list += '</div>';
		_list += '<div class="rightNews background">';
		_list += '<h2 class="rightTitle">'+n.title+'</h2>';
		_list += '<div class="rightContent">';
		
		if(img == 1){
			_list += '<div class="rightImg">';
			_list += '<img src="/img/news/'+n.id+'.jpg">';
			_list += '</div>';
		}
		else
			_list += '';

		_list += '<p>'+n.content+'</p>';
		_list += '<div class="source"><a href="'+n.url+'" class="source" target="_blank">'+n.web.name+'</a><a class="source">来源：</a></div>';
		_list += '</div>';
		_list += '</div>';
		_list += '<div style="clear: both;"></div>';
		_list += '</div>';
	}
	return _list;
}
</script>
<div class="navTop"></div>
<div class="news">
	<div class="newArticle">
		<p class="newArticleTitle background">推荐文章</p>
		<div class="newArticleContent background">
			<c:forEach items="${as}" var="a">
				<a href="article?id=${a.id}" class="title" target="_black">${a.title}</a>
			</c:forEach>
		</div>
		<div class="newArticleBottom background"></div>
	</div>
	<div class="line">
		<div class="newsContent"></div>
		<input type="hidden" id="size" value="${nsSize}">
		<div class="button">
			<button type="button" class="btn btn-default load">继续加载</button>
		</div>
	</div>
</div>