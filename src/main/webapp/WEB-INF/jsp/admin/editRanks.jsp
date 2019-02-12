<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editDiv").submit(function() {
		if(!checkEmpty("liveOrder","序号"))
			return false;
		if(!checkEmpty("url","网址"))
			return false;
		return true;
	});
	
	//中英文输入分别限制长度
	$('#name').on('input', function (e) {
	        var $that =  $(this),
	            limit = 20;                            //定义所需字节数
	    $that.attr('maxlength',limit);
	    setTimeout(function(){
	        var value =  $that.val(),
	            reg = /[\u4e00-\u9fa5]{1}/g,             
	            notReg = /\w{1}/g;                     
	        var Cn = value.match(reg);
	        var En = value.match(notReg);
	        if(Cn){
	            limit = limit - (Cn.length*2);
	        }
	        if(En){

	            limit = limit - En.length;
	        }
	        if(limit<=0){
	            var finalLen = value.length+limit;
	            value = value.substring(0,finalLen);
	            $that.attr('maxlength',limit);
	            $that[0].value = value;
	        }
	    },0);
	});
});
</script>

<title>编辑队伍</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="admin_ranks_list?gid=${r.gid}">${g.name}队伍</a></li>
		<li><a>编辑队伍</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑队伍</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_ranks_update">
				<table class="editTable">
					<tr>
						<td class="name">队名</td>
						<td><input id="name" value="${r.name}" name="name" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">赛事选择</td>
						<td>
							<select name="gid"  class="form-control">
								<c:forEach items="${gs}" var="g">
									<option value="${g.id}" <c:if test="${r.gid==g.id}">selected = "selected"</c:if>>${g.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="id" value="${r.id}">
							<input type="hidden" name="gid" value="${r.gid}">
							<input type="hidden" name="oldGid" value="${g.id}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>