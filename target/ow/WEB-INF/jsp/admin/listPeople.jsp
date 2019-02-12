<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("name","姓名"))
			return false;
		if(!checkEmpty("peoplePic","图标"))
			return false;
		return true;
	});
	$(".delete").click(function() {
		var del = false;
		var page = "admin_people_dodelete";
		var id = $(this).attr("id");
		$.ajax({
			async: false,
			type: "post",
			url:page,
			data: {"id":id},
			success:function(result) {
				if("false"==result){
					alert("请先删除对应社交数据");
					del = true;
				}
			}
		});
		if(del){
			return false;
		}
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

<title>人物</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_type_list">人物分类</a></li>
		<li><a href="#">${t.name}</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>图标</th>
					<th>姓名</th>
					<th>介绍</th>
					<th>社交</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ps}" var="p">
					<tr>
						<td>${p.peopleOrder}</td>
						<td><img height="20px" src="/img/peopleLogo/${p.id}.jpg"></td>
						<td>${p.name}</td>
						<td>${p.info}</td>
						<td>
							<a href="admin_social_list?pid=${p.id}"><span class="glyphicon glyphicon-list"></span></a>
						</td>
						<td>
							<a href="admin_people_image?id=${p.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_people_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${p.id}" href="admin_people_delete?id=${p.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<td>
							<a href="admin_people_up?id=${p.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
						</td>
						<td>
							<a href="admin_people_down?id=${p.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增人物 (图片尺寸120*120)</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_people_add" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">姓名</td>
						<td><input id="name" name="name" type="text" placeholder="中文12字以内，英文24字以内" maxlength="12" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">介绍</td>
						<td><input id="info" name="info" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">图标</td>
						<td><input id="peoplePic" name="image" accept="image/*" type="file"/></td>
					</tr>
					<tr>
						<td class="submitTR" colspan="2" align="center">
							<input type="hidden" name="tid" value="${t.id}"/>
							<button type="submit" class="btn btn-success">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</div>

<%@ include file="../include/admin/adminFoot.jsp"%>