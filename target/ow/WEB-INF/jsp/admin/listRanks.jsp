<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>

<script>
$(function() {
	$("#addForm").submit(function() {
		if(!checkEmpty("name","队名"))
			return false;
		if(!checkEmpty("ranksPic","图标"))
			return false;
		return true;
	});
	
	$(".delete").click(function() {
		var del = false;
		var page = "admin_ranks_dodelete";
		var id = $(this).attr("id");
		$.ajax({
			async: false,
			type: "post",
			url:page,
			data: {"id":id},
			success:function(result) {
				if("false"==result){
					alert("请先删除对应赛事表数据");
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

<title>赛事队伍</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="admin_game_list">赛事</a></li>
		<li><a href="#">${g.name}队伍</a></li>
	</ul>
	<div class="listDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>图标</th>
					<th>队名</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rs}" var="r">
					<tr>
						<c:if test="${r.ranksOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${r.ranksOrder!=0}">
							<td>${r.ranksOrder}</td>
						</c:if>
						<td><img height="20px" src="/img/ranksLogo/${r.id}.jpg"></td>
						<td>${r.name}</td>
						<td>
							<a href="admin_ranks_image?id=${r.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_ranks_edit?id=${r.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${r.id}" href="admin_ranks_delete?id=${r.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${r.ranksOrder==0}">
							<td>
								<a id="${r.id}" href="admin_ranks_show?id=${r.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${r.ranksOrder!=0}">
							<td>
								<a id="${r.id}" href="admin_ranks_hide?id=${r.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${r.ranksOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${r.ranksOrder!=0}">
							<td>
								<a href="admin_ranks_up?id=${r.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_ranks_down?id=${r.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
							</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增队伍 (图片尺寸150*150)</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_ranks_add" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">队名</td>
						<td><input id="name" name="name" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">图片</td>
						<td><input id="ranksPic" name="image" accept="image/*" type="file"/></td>
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