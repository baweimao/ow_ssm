<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#editForm").submit(function() {
		if(!checkEmpty("name","站名"))
			return false;
		if(!checkEmpty("url","网址"))
			return false;
		if(!checkEmpty("webOrder","序号"))
			return false;
		if(!checkEmpty("webPic","图标"))
			return false;
		return true;
	});
	
	//中英文输入分别限制长度
	$('#name').on('input', function (e) {
	        var $that =  $(this),
	            limit = 24;                            //定义所需字节数
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

<title>编辑分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_type_list">人物分类</a></li>
		<li><a href="admin_people_list?tid=${p.tid}">${t.name}</a></li>
		<li><a href="#">编辑人物</a></li>
	</ul>
	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑人物</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="admin_people_update">
				<table class="editTable">
					<tr>
						<td class="name">姓名</td>
						<td><input id="name" value="${p.name}" name="name" type="text" placeholder="中文12字以内，英文24字以内" maxlength="12" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">介绍</td>
						<td><input id="info" value="${p.info}" name="info" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">类型选择</td>
						<td>
							<select name="tid"  class="form-control">
								<c:forEach items="${ts}" var="t">
									<option value="${t.id}" <c:if test="${p.tid==t.id}">selected = "selected"</c:if>>${t.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${p.id}"/>
							<input type="hidden" name="tid" value="${p.tid}"/>
							<input type="hidden" name="peopleOrder" value="${p.peopleOrder}"/>
							<input type="hidden" name="oldTid" value="${t.id}"/>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>