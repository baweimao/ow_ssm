<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("url","个人网址"))
			return false;
		return true;
	});
});
</script>

<title>社交</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_type_list">人物分类</a></li>
		<li><a href="admin_people_list?tid=${p.tid}">${t.name}</a></li>
		<li><a>${p.name}社交</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>图标</th>
					<th>站名</th>
					<th>个人网址</th>
					<th>编辑</th>
					<th>删除</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ss}" var="s">
					<tr>
						<td>${s.socialOrder}</td>
						<td><img height="20px" src="/img/webLogo/${s.wid}.jpg"></td>
						<td>${s.web.name}</td>
						<td>${s.url}</td>
						<td>
							<a href="admin_social_edit?id=${s.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a href="admin_social_delete?id=${s.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_social_up?id=${s.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
						</td>
						<td>
							<a href="admin_social_down?id=${s.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增社交(微信网址填写公众号)</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_social_add">
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
						<td class="name">个人网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="pid" value="${p.id}"/>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>