<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
<html>
<title>守望先锋观测站后台</title>
<head>
<link rel="shortcut icon" href="/img/web/gcz.ico">
<script src="/js/jquery/2.0.0/jquery.min.js"></script>
<script src="/js/bootstrap/3.3.6/bootstrap.js"></script>
<link href="/css/bootstrap/3.3.6/bootstrap.css" rel="stylesheet">
<link href="/css/admin/style.css" rel="stylesheet">

<link href="/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet">  
<script src="/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>

<script>
	function checkEmpty(id,name) {
		var value = $("#"+id).val();
		if (value.length==0){
			alert(name+"不能为空");
			$("#"+id)[0].focus();
			return false;
		}
		return true;
	}
	
// 	/*图片点击放大*/
// 	$(function() {
// 		var flag = true,//状态true为正常的状态,false为放大的状态
// 		           imgH,//图片的高度
// 		           imgW,//图片的宽度
// 		           img = document.getElementsByTagName('img')[0];//图片元素
// 		$("img").click(function() {
// 			 //图片点击事件
// // 			var img=this.getAttribute("img");
// 			       imgH = img.height; //获取图片的高度
// 			       imgW = img.width; //获取图片的宽度
// 			       if(flag){
// 			           //图片为正常状态,设置图片宽高为现在宽高的2倍
// 			           flag = false;//把状态设为放大状态
// 			           img.height = imgH*6;
// 			           img.width = imgW*6;
// 			       }else{
// 			           //图片为放大状态,设置图片宽高为现在宽高的二分之一
// 			           flag = true;//把状态设为正常状态
// 			           img.height = imgH/6;
// 			           img.width = imgW/6;
// 			       }
// 		});
// 	});
</script>
</head>

<body>
