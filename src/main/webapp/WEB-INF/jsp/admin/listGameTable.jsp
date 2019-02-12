<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("strDate","时间"))
			return false;
		var page = "admin_gametable_gamedate";
		var strDate = $("#strDate").val();
		$.post(
			page,
			{"strDate":strDate},
			function(result) {
				if("false"==result){
					alert("日期格式有误，请重新输入");
					return false;
				}
				$("form")[0].submit();
			}
		)
		return false;
	});
});
</script>

<title>赛事直播</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="#">${g.name}赛事表</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>时间</th>
					<th>图标</th>
					<th>队名</th>
					<th>对抗</th>
					<th>图标</th>
					<th>队名</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gbs}" var="gb">
					<tr>
						<td><fmt:formatDate value="${gb.gameDate}" pattern="MM-dd HH:mm"/></td>
						<td><img height="20px" src="/img/ranksLogo/${gb.ranks_a.id}.jpg"></td>
						<td>${gb.ranks_a.name}</td>
						<td>VS</td>
						<td><img height="20px" src="/img/ranksLogo/${gb.ranks_b.id}.jpg"></td>
						<td>${gb.ranks_b.name}</td>
						<td>
							<a href="admin_gametable_edit?id=${gb.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a href="admin_gametable_delete?id=${gb.id}"><span class="glyphicon glyphicon-trash"></span></a>
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
		<div class="panel-heading">新增赛事表</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_gametable_add">
				<table class="addTable">
					<tr>
						<td class="name">日期时间</td>
						<td><input id="strDate" value="${gb.strDate}" placeholder="请按照“2018-09-28 14:30”格式输入" name="strDate" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">选择队伍1</td>
						<td>
							<select name="rid_a" class="form-control">
								<c:forEach items="${rs}" var="r">
									<option value="${r.id}">${r.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="name">选择队伍2</td>
						<td>
							<select name="rid_b" class="form-control">
								<c:forEach items="${rs}" var="r">
									<option value="${r.id}">${r.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="gid" value="${gid}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>