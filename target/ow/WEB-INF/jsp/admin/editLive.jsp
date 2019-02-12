<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editDiv").submit(function() {
		if(!checkEmpty("liveOrder","序号"))
			return false;
		if(!checkEmpty("url","网址"))
			return false;
		return true;
	});
});
</script>

<title>编辑分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="admin_live_list?gid=${l.gid}">${g.name}直播</a></li>
		<li><a href="#">编辑直播</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑赛事直播</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_live_update">
				<table class="addTable">
					<tr>
						<td class="name">网址</td>
						<td><input id="url" value="${l.url}" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="id" value="${l.id}">
							<input type="hidden" name="gid" value="${l.gid}">
							<input type="hidden" name="wid" value="${l.wid}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>