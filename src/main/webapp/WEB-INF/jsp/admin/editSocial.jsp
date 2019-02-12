<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("url","个人网址"))
			return false;
		if(!checkEmpty("socialOrder","序号"))
			return false;
		return true;
	});
});
</script>

<title>编辑社交</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_type_list">人物分类</a></li>
		<li><a href="admin_people_list?tid=${p.tid}">${t.name}</a></li>
		<li><a href="admin_social_list?pid=${s.pid}">${p.name}社交</a></li>
		<li><a>编辑${w.name}</a></li>
	</ul>
	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑社交(微信网址填写公众号)</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_social_update">
				<table class="editTable">
					<tr>
						<td class="name">个人网址</td>
						<td><input id="url" value="${s.url}" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${s.id}"/>
							<input type="hidden" name="pid" value="${s.pid}"/>
							<input type="hidden" name="wid" value="${s.wid}"/>
							<input type="hidden" name="socialOrder" value="${s.socialOrder}"/>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>