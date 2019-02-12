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
		var page = "admin_type_dodelete";
		var id = $(this).attr("id");
		$.ajax({
			async: false,
			type: "post",
			url:page,
			data: {"id":id},
			success:function(result) {
				if("false"==result){
					alert("请先删除对应人物数据");
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

<title>人物分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">人物分类</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号/状态</th>
					<th>类名</th>
					<th>人物</th>
					<th>编辑</th>
					<th>删除</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ts}" var="t">
					<tr>
						<c:if test="${t.typeOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${t.typeOrder!=0}">
							<td>${t.typeOrder}</td>
						</c:if>
						<td>${t.name}</td>
						<td>
							<a href="admin_people_list?tid=${t.id}"><span class="glyphicon glyphicon-list"></span></a>
						</td>
						<td>
							<a href="admin_type_edit?id=${t.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${t.id}" href="admin_type_delete?id=${t.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${t.typeOrder==0}">
							<td>
								<a href="admin_type_show?id=${t.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${t.typeOrder!=0}">
							<td>
								<a href="admin_type_hide?id=${t.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${t.typeOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${t.typeOrder!=0}">
							<td>
								<a href="admin_type_up?id=${t.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_type_down?id=${t.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
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
			<form method="post" id="addForm" action="admin_type_add">
				<table class="addTable">
					<tr class="name">
						<td class="name">类名</td>
						<td><input id="name" name="name" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
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