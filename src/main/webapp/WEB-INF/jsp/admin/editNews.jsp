<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("title","标题"))
			return false;
// 		if(!checkEmpty("content","内容"))
// 			return false;
		return true;
	});
});
</script>

<title>编辑资讯</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_news_list">资讯</a></li>
		<li><a href="#">编辑资讯</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑资讯</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_news_update">
				<table class="editTable">
					<tr>
						<td class="name">标题</td>
						<td><input id="title" value="${n.title}" name="title" type="text" placeholder="推荐14字以内，正常25字以内" maxlength="25" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">内容</td>
						<td><textarea wrap="virtual" id="content" name="content" type="text" class="form-control">${n.content}</textarea></td>
					</tr>
					<tr>
						<td class="name">网站选择</td>
						<td>
							<select name="wid"  class="form-control">
								<c:forEach items="${ws}" var="w">
									<option value="${w.id}" <c:if test="${n.wid==w.id}">selected = "selected"</c:if>>${w.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="name">资讯网址</td>
						<td><input id="url" value="${n.url}" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${n.id}">
<%-- 							<input type="hidden" name="wid" value="${n.wid}"> --%>
<%-- 							<input type="hidden" name="up" value="${n.up}"> --%>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>