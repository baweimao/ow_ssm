<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>

<script>
$(function() {
	<c:if test="${!empty msg}">
    $("span.errorMessage").html("${msg}");
    $("div.loginErrorMessageDiv").show();
    </c:if>
    
    $("input").keyup(function(){
        $("div.loginErrorMessageDiv").hide();
    });
});
</script>

<div class="loginDiv">
	<div class="loginErrorMessageDiv" hidden="hidden">
    	<div class="alert alert-danger alert-dismissible" role="alert">
        	<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
        	<span class="errorMessage"></span>
        </div>
    </div>
	<div class="panel panel-warning panelDiv">
	<div class="panel-heading">管理员登陆</div>
	<div class="panel-body">
		<form method="post" id="addForm" action="admin_user_login">
			<table class="addTable">
				<tr>
					<td>账号</td>
					<td><input id="name" name="name" type="text" class="form-control"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input id="password" name="password" type="password" class="form-control"></td>
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