<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("url","网址"))
			return false;
		return true;
	});
});
</script>

<title>赛事直播</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="#">${g.name}直播</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>站名</th>
					<th>网址</th>
					<th>编辑</th>
					<th>删除</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ls}" var="l">
					<tr>
						<td>${l.liveOrder}</td>
						<td>${l.web.name}</td>
						<td>${l.url}</td>
						<td>
							<a href="admin_live_edit?id=${l.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${l.id}" href="admin_live_delete?id=${l.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_live_up?id=${l.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
						</td>
						<td>
							<a href="admin_live_down?id=${l.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增直播</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_live_add" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">网站选择</td>
						<td>
							<select name="wid" class="form-control">
								<c:forEach items="${ws}" var="w">
									<option value="${w.id}">${w.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="name">网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="gid" value="${gid}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>