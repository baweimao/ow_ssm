<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("webPic","图标"))
			return false;
		return true;
	});
});
</script>

<title>修改图片</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="admin_ranks_list?gid=${r.gid}">队伍</a></li>
		<li><a href="#">修改图片</a></li>
	</ul>
	<div class="panel panel-warning editDiv">
		<div class="panel-heading">修改图片(图片尺寸150*150)</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_ranks_imageupdate" enctype="multipart/form-data">
				<table class="editTable">
					<tr>
						<td class="name">原图</td>
						<td><img height="80px" src="/img/ranksLogo/${r.id}.jpg"></td>
					</tr>
					<tr>
						<td class="name">新图</td>
						<td><input id="webPic" name="image" accept="image/*" type="file"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${r.id}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>