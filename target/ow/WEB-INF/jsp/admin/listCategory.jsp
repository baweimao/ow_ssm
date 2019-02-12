<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("name","类名"))
			return false;
		return true;
	});

	$(".delete").click(function() {
		var del = false;
		var page = "admin_category_dodelete";
		var id = $(this).attr("id");
		$.ajax({
			async:false,
			type: "post",
			url:page,
			data:{"id":id},
			success:function(result) {
				if("false"==result){
					alert("请先删除对应网站数据");
					del = true;
				}
			}
		});
		if(del){
			return false;
		}
	});
});
</script>

<title>网站分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">网站分类</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号/状态</th>
					<th>类名</th>
					<th>网站</th>
					<th>编辑</th>
					<th>删除</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cs}" var="c">
					<tr>
						<c:if test="${c.categoryOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${c.categoryOrder!=0}">
							<td>${c.categoryOrder}</td>
						</c:if>
						<td>${c.name}</td>
						<td>
							<a href="admin_web_list?cid=${c.id}"><span class="glyphicon glyphicon-list"></span></a>
						</td>
						<td>
							<a href="admin_category_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${c.id}" href="admin_category_delete?id=${c.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${c.categoryOrder==0}">
							<td>
								<a id="${c.id}" href="admin_category_show?id=${c.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${c.categoryOrder!=0}">
							<td>
								<a id="${c.id}" href="admin_category_hide?id=${c.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${c.categoryOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${c.categoryOrder!=0}">
							<td>
							<a href="admin_category_up?id=${c.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_category_down?id=${c.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增分类</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_category_add">
				<table class="addTable">
					<tr>
						<td class="name">类名</td>
						<td><input id="name" name="name" type="text" placeholder="6字以内" maxlength="6" class="form-control"></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>