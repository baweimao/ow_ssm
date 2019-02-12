<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("name","类名"))
			return false;
		if(!checkEmpty("categoryOrder","序号"))
			return false;
		return true;
	});
});
</script>

<title>编辑分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_type_list">人物分类</a></li>
		<li><a href="#">编辑分类</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑分类</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_type_update">
				<table class="editTable">
					<tr>
						<td class="name">类名</td>
						<td><input id="name" value="${t.name}" name="name" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${t.id}">
							<input type="hidden" name="typeOrder" value="${t.typeOrder}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>