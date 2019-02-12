<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {	
	$("#addForm").submit(function() {
		if(!checkEmpty("title","标题"))
			return false;
// 		if(!checkEmpty("content","内容"))
// 			return false;	
		var pic = $("#newsPic").val();
		if(pic == ""){
			$("#addForm").attr('action', 'admin_news_addNoPic');
			$("#addForm")[0].submit();
			return false;
		}
		return true;
	});
});
</script>

<title>资讯</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">资讯</a></li>
	</ul>
	<div class="listDiv">
		<p class="bg-primary">推荐</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>标题</th>
					<th>图片</th>
					<th>内容</th>
					<th>站名</th>
					<th>网址</th>
					<th>时间</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th>推荐</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${nsTop}" var="nt">
					<tr>
						<td style="color: red">推荐</td>
						<c:if test="${nt.newsOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${nt.newsOrder!=0}">
							<td>${nt.newsOrder}</td>
						</c:if>
						<td>${nt.title}</td>
						<c:if test="${nt.img==1}">
						<td><img height="20px" src="/img/news/${nt.id}.jpg"></td>
						</c:if>
						<c:if test="${nt.img==0}">
						<td>空</td>
						</c:if>
						<td>${nt.content}</td>
						<td>${nt.web.name}</td>
						<td>${nt.url}</td>
						<td><fmt:formatDate value="${nt.newsDate}" pattern="MM-dd"/></td>
						<td>
							<a href="admin_news_image?id=${nt.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_news_edit?id=${nt.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${nt.id}" href="admin_news_delete?id=${nt.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_news_art?id=${nt.id}"><span class="glyphicon glyphicon-star"></span></a>
						</td>
						<c:if test="${nt.newsOrder==0}">
							<td>
								<a href="admin_news_show?id=${nt.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${nt.newsOrder!=0}">
							<td>
								<a href="admin_news_hide?id=${nt.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${nt.newsOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${nt.newsOrder!=0}">
							<td>
								<a href="admin_news_up?id=${nt.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_news_down?id=${nt.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<div class="listDiv">
	<p class="bg-primary">正常 (如果内容包含内嵌视频将不能推荐)</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>标题</th>
					<th>图片</th>
					<th>内容</th>
					<th>站名</th>
					<th>网址</th>
					<th>时间</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th>推荐</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ns}" var="n">
					<tr>
						<td>正常</td>
						<c:if test="${n.newsOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${n.newsOrder!=0}">
							<td style="color: #DCDCDC;">${n.newsOrder}</td>
						</c:if>
						<td>${n.title}</td>
						<c:if test="${n.img==1}">
						<td><img height="20px" src="/img/news/${n.id}.jpg"></td>
						</c:if>
						<c:if test="${n.img==0}">
						<td>空</td>
						</c:if>
						<td>${n.content}</td>
						<td>${n.web.name}</td>
						<td>${n.url}</td>
						<td><fmt:formatDate value="${n.newsDate}" pattern="MM-dd"/></td>
						<td>
							<a href="admin_news_image?id=${n.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_news_edit?id=${n.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${n.id}" href="admin_news_delete?id=${n.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${n.video==0}">
							<td>
								<a href="admin_news_top?id=${n.id}"><span class="glyphicon glyphicon-star-empty"></span></a>
							</td>
						</c:if>
						<c:if test="${n.video!=0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-star-empty"></span></a>
							</td>
						</c:if>
						<c:if test="${n.newsOrder==0}">
							<td>
								<a href="admin_news_show?id=${n.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${n.newsOrder!=0}">
							<td>
								<a href="admin_news_hide?id=${n.id}"><span class="glyphicon glyphicon-eye-open"></a>
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
		<div class="panel-heading">新增资讯</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_news_add" enctype="multipart/form-data">
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
						<td class="name">资讯网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">标题</td>
						<td><input id="title" name="title" type="text" placeholder="推荐14字以内，正常25字以内" maxlength="25" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">内容</td>
						<td><textarea wrap="virtual" id="content" name="content" type="text" class="form-control"></textarea></td>
					</tr>
					<tr>
						<td class="name">图片</td>
						<td><input id="newsPic" name="image" accept="image/*" type="file"/></td>
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