<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<!-- UE富文本配置 -->
<script src="/ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="/ueditor/ueditor.all.min.js" type="text/javascript" charset="utf-8"></script>
<link href="/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css"/>
<script src="/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var ue = UE.getEditor('content');
</script>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("title","标题"))
			return false;
		if(!checkEmpty("remark","摘要"))
			return false;
// 		if(!checkEmpty("content","内容"))
// 			return false;
		return true;
	});
});
</script>

<title>编辑文章</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_article_list">文章</a></li>
		<li><a href="#">编辑文章</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑文章</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_article_update">
				<table class="editTable">
					<tr>
						<td class="name">标题</td>
						<td><input id="title" value="${a.title}" name="title" type="text" placeholder="37字以内" maxlength="37" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">摘要</td>
						<td><input id="remark" value="${a.remark}" name="remark" type="text" placeholder="110字以内" maxlength="110" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">内容</td>
						<script id="content" name="content" type="text/plain">${a.content}</script>
					</tr>
					<tr>
						<td class="name">文章网址</td>
						<td><input id="url" value="${a.url}" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">网站选择</td>
						<td>
							<select name="wid"  class="form-control">
								<c:forEach items="${ws}" var="w">
									<option value="${w.id}" <c:if test="${a.wid==w.id}">selected = "selected"</c:if>>${w.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${a.id}">
<%-- 							<input type="hidden" name="wid" value="${a.wid}"> --%>
<%-- 							<input type="hidden" name="newDate" value="${a.articleDate}"> --%>
<%-- 							<input type="hidden" name="up" value="${a.up}"> --%>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>