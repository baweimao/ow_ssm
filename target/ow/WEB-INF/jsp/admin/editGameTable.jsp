<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("form").submit(function() {
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

<title>编辑赛事表</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="admin_gametable_list?gid=${gb.gid}">${g.name}赛事表</a></li>
		<li><a>编辑赛事表</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑赛事表</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_gametable_update">
				<table class="editTable">
					<tr>
						<td class="name">日期时间</td>
						<td><input id="strDate" value="${gb.strDate}" name="strDate" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="id" value="${gb.id}">
							<input type="hidden" name="rid_a" value="${gb.rid_a}">
							<input type="hidden" name="rid_b" value="${gb.rid_b}">
							<input type="hidden" name="gid" value="${gb.gid}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>