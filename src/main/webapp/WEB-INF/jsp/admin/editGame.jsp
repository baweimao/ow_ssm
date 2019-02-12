<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("name","赛事名"))
			return false;
		if(!checkEmpty("info","赛事介绍"))
			return false;
		if(!checkEmpty("url","网址"))
			return false;
		if(!checkEmpty("color","背景色"))
			return false;
		var col = $("#color").val();
		if(col.charAt(0)!="#"){
			alert("请重新确认背景色格式#000000");
			return false;
		}
		if(col.length!=7){
			alert("请重新确认背景色格式#000000");
			return false;
		}
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

<title>编辑分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="#">编辑赛事</a></li>
	</ul>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑赛事</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_game_update" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">赛事名</td>
						<td><input id="name" value="${g.name}" name="name" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">赛事介绍</td>
						<td><textarea wrap="virtual" id="info" name="info" type="text" placeholder="272字以内" maxlength="272" class="form-control">${g.info}</textarea></td>
					</tr>
					<tr>
						<td class="name">网址</td>
						<td><input id="url" value="${g.url}" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">背景色</td>
						<td><input id="color" value="${g.color}" name="color" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">类型选择</td>
						<td>
							<select name="up"  class="form-control">
								<option value="0" <c:if test="${g.up==0}">selected = "selected"</c:if>>官方赛事</option>
								<option value="1" <c:if test="${g.up==1}">selected = "selected"</c:if>>第三方赛事</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="id" value="${g.id}">
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>