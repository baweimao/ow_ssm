<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function() {
	var img = $("div.first input.imgHide").val();
	var id = $("div.first input.idHide").val();

	if(img == 1)
		$("div.leftNews").css("background-image", "url(/img/news/"+id+".jpg)");
	else
		$("div.leftNews").css("background-image", "url(/img/news/01.jpg)");
	
	//右边栏鼠标滑过效果
	$("div.rightNews a").mouseover(function() {
		$(this).addClass("homeNewsClick").siblings().removeClass("homeNewsClick");
		var id = $(this).children("input").val();
		$("div.select").hide();
		$("div."+id+"").show();
		var img = $("div."+id+" input.imgHide").val();
		if(img == 1)
			$("div.leftNews").css("background-image", "url(/img/news/"+id+".jpg)");
		else
			$("div.leftNews").css("background-image", "url(/img/news/01.jpg)");
	});
	
// 	//右边栏点击效果
// 	$("div.rightNews a").click(function() {
// 		$(this).addClass("homeNewsClick").siblings().removeClass("homeNewsClick");
// 		var id = $(this).children("input").val();
// 		$("div.select").hide();
// 		$("div."+id+"").show();
// 		var img = $("div."+id+" input.imgHide").val();
// 		if(img == 1)
// 			$("div.leftNews").css("background-image", "url(/resources/news/"+id+".jpg)");
// 		else
// 			$("div.leftNews").css("background-image", "url(/resources/news/01.jpg)");
// 	});
});
</script>
<div class="navTop"></div>
<div class="nav">
	<p>最新动态</p>
</div>
<div class="homeNews">
	<div class="leftNews">
		<c:forEach items="${ns}" var="n" begin="0" end="0">
			<div class="first select ${n.id}">
				<div class="backgroundUp backgroundE"></div>
				<div class="backgroundDown backgroundE"></div>
				<p>${n.content}</p>
				<div class="logoTitle">
					<img src="/img/webLogo/${n.web.id}.jpg">
					<a>${n.title}</a>
				</div>
				<input class="idHide" type="hidden" value="${n.id}">
				<input class="imgHide" type="hidden" value="${n.img}">
			</div>
		</c:forEach>
		<c:forEach items="${ns}" var="n" begin="1">
			<div class="select ${n.id}" hidden="hidden">
				<div class="backgroundUp backgroundE"></div>
				<div class="backgroundDown backgroundE"></div>
				<p>${n.content}</p>
				<div class="logoTitle">
					<img src="/img/webLogo/${n.web.id}.jpg">
					<a>${n.title}</a>
				</div>
				<input class="imgHide" type="hidden" value="${n.img}">
			</div>
		</c:forEach>
	</div>
	<div class="rightNews backgroundB">
		<c:forEach items="${ns}" var="n" begin="0" end="0">
			<a class="homeNewsClick first" href="${n.url}" target="_blank"><input id="hide" type="hidden" value="${n.id}">${n.title}</a>
		</c:forEach>
		<c:forEach items="${ns}" var="n" begin="1" end="5">
			<a href="${n.url}" target="_blank"><input class="idHide" type="hidden" value="${n.id}">${n.title}</a>
		</c:forEach>
	</div>
</div>