<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("name","站名"))
			return false;
		if(!checkEmpty("url","网址"))
			return false;
		if(!checkEmpty("webPic","图标"))
			return false;
		return true;
	});
	
	//中英文输入分别限制长度
	$('#name').on('input', function (e) {
	        var $that =  $(this),
	            limit = 14;                            //定义所需字节数
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

<title>网站</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_category_list">网站分类</a></li>
		<li><a href="#">${c.name}</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>图标</th>
					<th>站名</th>
					<th>网址</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ws}" var="w">
					<tr>
						<td>${w.webOrder}</td>
						<td><img height="20px" src="/img/webLogo/${w.id}.jpg"></td>
						<td>${w.name}</td>
						<td>${w.url}</td>
						<td>
							<a href="admin_web_image?id=${w.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_web_edit?id=${w.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a href="admin_web_delete?id=${w.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_web_up?id=${w.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
						</td>
						<td>
							<a href="admin_web_down?id=${w.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增网站 (图片尺寸50*50)</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_web_add" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">站名</td>
						<td><input id="name" name="name" type="text" placeholder="中文7字以内，英文14字以内" maxlength="7" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">图标</td>
						<td><input id="webPic" name="image" accept="image/*" type="file"/></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="cid" value="${c.id}"/>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>