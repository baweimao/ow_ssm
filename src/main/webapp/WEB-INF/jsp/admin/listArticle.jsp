<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

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
	$("#addForm").submit(function() {
		if(!checkEmpty("title","标题"))
			return false;
		if(!checkEmpty("remark","摘要"))
			return false;
// 		if(!checkEmpty("content","内容"))
// 			return false;
		if(!checkEmpty("newsPic","图片"))
			return false;
		return true;
	});
});
</script>
<title>文章</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">文章</a></li>
	</ul>
	<div class="listDiv">
		<p class="bg-primary">推荐</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>标题</th>
					<th>摘要</th>
					<th>站名</th>
					<th>网址</th>
					<th>时间</th>
					<th>编辑</th>
					<th>删除</th>
					<th>推荐</th>
					<th>置顶</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${asRec}" var="ar">
					<tr>
						<td style="color: blue">推荐</td>
						<c:if test="${ar.articleOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${ar.articleOrder!=0}">
							<td>${ar.articleOrder}</td>
						</c:if>
						<td>${ar.title}</td>
						<td>${ar.remark}</td>
						<td>${ar.web.name}</td>
						<td>${ar.url}</td>
						<td><fmt:formatDate value="${ar.articleDate}" pattern="MM-dd"/></td>
						<td>
							<a href="admin_article_edit?id=${ar.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${ar.id}" href="admin_article_delete?id=${ar.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_article_art?id=${ar.id}"><span class="glyphicon glyphicon-star"></span></a>
						</td>
						<td>
							<a href="admin_article_top?id=${ar.id}"><span class="glyphicon glyphicon-star-empty"></span></a>
						</td>
						<c:if test="${ar.articleOrder==0}">
							<td>
								<a href="admin_article_show?id=${ar.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${ar.articleOrder!=0}">
							<td>
								<a href="admin_article_hide?id=${ar.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${ar.articleOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${ar.articleOrder!=0}">
							<td>
								<a href="admin_article_up?id=${ar.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_article_down?id=${ar.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<div class="listDiv">
		<p class="bg-primary">置顶</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>标题</th>
					<th>摘要</th>
					<th>站名</th>
					<th>网址</th>
					<th>时间</th>
					<th>编辑</th>
					<th>删除</th>
					<th>推荐</th>
					<th>置顶</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${asTop}" var="at">
					<tr>
						<td style="color: red">置顶</td>
						<c:if test="${at.articleOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${at.articleOrder!=0}">
							<td>${at.articleOrder}</td>
						</c:if>
						<td>${at.title}</td>
						<td>${at.remark}</td>
						<td>${at.web.name}</td>
						<td>${at.url}</td>
						<td><fmt:formatDate value="${at.articleDate}" pattern="MM-dd"/></td>
						<td>
							<a href="admin_article_edit?id=${at.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${at.id}" href="admin_article_delete?id=${at.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_article_news?id=${at.id}"><span class="glyphicon glyphicon-star-empty"></span></a>
						</td>
						<td>
							<a href="admin_article_art?id=${at.id}"><span class="glyphicon glyphicon-star"></span></a>
						</td>
						<c:if test="${at.articleOrder==0}">
							<td>
								<a href="admin_article_show?id=${at.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${at.articleOrder!=0}">
							<td>
								<a href="admin_article_hide?id=${at.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${at.articleOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${at.articleOrder!=0}">
							<td>
								<a href="admin_article_up?id=${at.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_article_down?id=${at.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="listDiv">
		<p class="bg-primary">正常</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>标题</th>
					<th>摘要</th>
					<th>站名</th>
					<th>网址</th>
					<th>时间</th>
					<th>编辑</th>
					<th>删除</th>
					<th>推荐</th>
					<th>置顶</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>		
				<c:forEach items="${as}" var="a">
					<tr>
						<td>正常</td>
						<c:if test="${a.articleOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${a.articleOrder!=0}">
							<td style="color: #DCDCDC;">${a.articleOrder}</td>
						</c:if>
						<td>${a.title}</td>
						<td>${a.remark}</td>
						<td>${a.web.name}</td>
						<td>${a.url}</td>
						<td><fmt:formatDate value="${a.articleDate}" pattern="MM-dd"/></td>
						<td>
							<a href="admin_article_edit?id=${a.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${a.id}" href="admin_article_delete?id=${a.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_article_news?id=${a.id}"><span class="	glyphicon glyphicon-star-empty"></span></a>
						</td>
						<td>
							<a href="admin_article_top?id=${a.id}"><span class="	glyphicon glyphicon-star-empty"></span></a>
						</td>
						<c:if test="${a.articleOrder==0}">
							<td>
								<a href="admin_article_show?id=${a.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${a.articleOrder!=0}">
							<td>
								<a href="admin_article_hide?id=${a.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<td>
							<a href="#"><span style="color: #DCDCDC;" class="glyphicon glyphicon-triangle-top"></span></a>
						</td>
						<td>
							<a href="#"><span style="color: #DCDCDC;" class="glyphicon glyphicon-triangle-bottom"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增文章</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_article_add" enctype="multipart/form-data">
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
						<td class="name">文章网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">标题</td>
						<td><input id="title" name="title" type="text" placeholder="37字以内" maxlength="37" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">摘要</td>
						<td><input id="remark" name="remark" type="text" placeholder="110字以内" maxlength="110" class="form-control"></td>
					</tr>
					<tr class="umeditor">
						<td class="name">内容</td>
						<td>
							<script id="content" name="content" type="text/plain"></script>
						</td>
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