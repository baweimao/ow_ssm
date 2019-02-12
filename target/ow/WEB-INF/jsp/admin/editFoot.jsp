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

<title>编辑页面</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_page_list">页面</a></li>
		<li>
			<c:if test="${f.id==1}">
				<a href="#">关于我们</a>
			</c:if>
			<c:if test="${f.id==2}">
				<a href="#">联系我们</a>
			</c:if>
			<c:if test="${f.id==3}">
				<a href="#">服务声明</a>
			</c:if>
		</li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑页面</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_foot_update">
				<table class="editTable">
					<tr>
						<td class="name">内容</td>
						<script id="content" name="content" type="text/plain">${f.content}</script>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${f.id}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>