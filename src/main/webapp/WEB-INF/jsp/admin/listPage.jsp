<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>

</script>

<title>页面</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">页面</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>页面</th>
					<th>编辑</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${fs}" var="f">
					<tr>
						<c:if test="${f.id==1}">
							<td>关于我们</td>
						</c:if>
						<c:if test="${f.id==2}">
							<td>联系我们</td>
						</c:if>
						<c:if test="${f.id==3}">
							<td>服务声明</td>
						</c:if>
						<td>
							<a href="admin_foot_edit?id=${f.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td>背景图</td>
					<td>
						<a href="admin_backgroundimage_edit"><span class="glyphicon glyphicon-edit"></span></a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>