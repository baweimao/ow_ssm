<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
$(function() {
	
	//显示5条信息
	var count = 5;
	var size = Number($("#size").val());
	ajaxArticle(count,size);

	// 继续加载
	$("#button").click(function() {	
		count += 5;
		ajaxArticle(count,size);
	});
	
});

/**
 * 异步加载资讯并写入代码块
 */
function ajaxArticle(count,size) {
	var page = "loadTitle";
	$.ajax({
		async: false,
		cache: false,
		type: "post",
		url:page,
		data:{"count":count},
		error:function(){
			alert("系统异常，请稍后访问");
		},
		success:function(data) {
			if(count >= size){
				$("#button").html("已到结尾");
				$("#button").attr("disabled", "disabled");
			}
			var _html = insertDiv(data);
			$("#content").html(_html);
			}
		})
		$("div.titleOne div.right a").hover(function() {
			$(this).children("div.hoverUp").show();
			$(this).children("div.hoverDown").css("width", "100%");
		},function(){
			$(this).children("div.hoverUp").hide();
			$(this).children("div.hoverDown").css("width", "0");
		});
		
		$("div.titleUp a").hover(function() {
			$(this).children("div.hoverUp").show();
			$(this).children("div.hoverDown").css("width", "100%");
		},function(){
			$(this).children("div.hoverUp").hide();
			$(this).children("div.hoverDown").css("width", "0");
		});
	}

	// 数据循环插入Div块
	function insertDiv(data) {
		var _list = "";
		for (var i = 0; i < data.length; i++){
			title = data[i].title;
			remark = data[i].remark;
			articleDate = data[i].articleDate;
			fmtDate = data[i].fmtDate;
			imgUrl = data[i].imgUrl;
			id = data[i].id;
			
			_list += '<div class="titleOne">';
			_list += '<div class="left">';
			_list += '<a target="_blank" href="article?id='+id+'">'+title+'</a>';
			_list += '<p>'+remark+'</p>';
			_list += '<div class="time">';
			_list += '<span class="glyphicon glyphicon-time"></span>';
			_list += '<span>'+fmtDate+'</span>';
			_list += '</div>';
			_list += '</div>';
			_list += '<div class="right">';
			_list += '<a target="_blank" href="article?id='+id+'">';
			_list += '<img src="'+imgUrl+'">';
			_list += '<div class="hoverUp"></div>';
			_list += '<div class="hoverDown"></div>';
			_list += '</a>';
			_list += '<div style="clear: both;"></div>';
			_list += '</div>';
			_list += '</div>';
		}
		return _list;
	}
</script>
<div class="navTop"></div>
<div class="title">
<div class="nav">
	<p>推荐阅读</p>
</div>
<div class="titleUp backgroundB">
	<div class="left">
		<c:forEach items="${asTop}" var="at" begin="0" end="0">
			<a href="article?id=${at.id}" target="_blank">
				<img src="${at.imgUrl}">
				<div class="background"></div>
				<p>${at.title}</p>
				<div class="hoverUp"></div>
				<div class="hoverDown"></div>
			</a>
		</c:forEach>
	</div>
	<div class="right">
		<div class="rightUp">
			<c:forEach items="${asTop}" var="at" begin="1" end="1">
				<a href="article?id=${at.id}" target="_blank">
					<img src="${at.imgUrl}">
					<div class="background"></div>
					<p>${at.title}</p>
					<div class="hoverUp"></div>
					<div class="hoverDown"></div>
				</a>
			</c:forEach>
		</div>
		<div class="rightDown">
			<c:forEach items="${asTop}" var="at" begin="2" end="2">
				<a href="article?id=${at.id}" target="_blank">
					<img src="${at.imgUrl}">
					<div class="background"></div>
					<p>${at.title}</p>
					<div class="hoverUp"></div>
					<div class="hoverDown"></div>
				</a>
			</c:forEach>
		</div>
	</div>
</div>
<div class="nav">
	<p>最新文章</p>
</div>
<div class="titleContent background">
	<div id="content"></div>
	<input type="hidden" id="size" value="${asSize}">
	<div class="button background">
		<button id="button" type="button" class="btn btn-default load">继续加载</button>
	</div>
</div>
</div>