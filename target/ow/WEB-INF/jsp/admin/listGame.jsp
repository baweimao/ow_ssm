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
		if(!checkEmpty("gamePic","图标"))
			return false;
		return true;
	});

	$(".delete").click(function() {
		var del = false;
		var page = "admin_game_dodelete";
		var id = $(this).attr("id");
		$.ajax({
			async: false,
			type: "post",
			url:page,
			data: {"id":id},
			success:function(result) {
				if("false"==result){
					alert("请先删除对应直播 队伍和赛事表数据");
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

<title>网站分类</title>
<div class="div">
	<ul class="breadcrumb">
		<li><a href="#">赛事</a></li>
	</ul>
	<div class="listDiv">
		<p class="bg-primary">官方</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>图标</th>
					<th>赛事名</th>
					<th>赛事介绍</th>
					<th>网址</th>
					<th>背景色</th>
					<th>直播</th>
					<th>队伍</th>
					<th>赛事表</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gsTop}" var="g">
					<tr>
						<td>官方</td>
						<c:if test="${g.gameOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${g.gameOrder!=0}">
							<td>${g.gameOrder}</td>
						</c:if>
						<td><img height="20px" src="/img/gameLogo/${g.id}.jpg"></td>
						<td>${g.name}</td>
						<td>${g.info}</td>
						<td>${g.url}</td>
						<td>${g.color}</td>
						<td>
							<a href="admin_live_list?gid=${g.id}"><span class="glyphicon glyphicon-facetime-video"></span></a>
						</td>
						<td>
							<a href="admin_ranks_list?gid=${g.id}"><span class="glyphicon glyphicon-user"></span></a>
						</td>
						<td>
							<a href="admin_gametable_list?gid=${g.id}"><span class="glyphicon glyphicon-list-alt"></span></a>
						</td>
						<td>
							<a href="admin_game_image?id=${g.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_game_edit?id=${g.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${g.id}" href="admin_game_delete?id=${g.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${g.gameOrder==0}">
							<td>
								<a href="admin_game_show?id=${g.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${g.gameOrder!=0}">
							<td>
								<a href="admin_game_hide?id=${g.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${g.gameOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${g.gameOrder!=0}">
							<td>
								<a href="admin_game_up?id=${g.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_game_down?id=${g.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="listDiv">
		<p class="bg-primary">第三方</p>
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>类型</th>
					<th>序号/状态</th>
					<th>图标</th>
					<th>赛事名</th>
					<th>赛事介绍</th>
					<th>网址</th>
					<th>背景色</th>
					<th>直播</th>
					<th>队伍</th>
					<th>赛事表</th>
					<th>修改图片</th>
					<th>编辑</th>
					<th>删除</th>
					<th>隐藏</th>
					<th colspan="2">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gsArt}" var="ga">
					<tr>
						<td>第三方</td>
						<c:if test="${ga.gameOrder==0}">
							<td style="color: red;">已隐藏</td>
						</c:if>
						<c:if test="${ga.gameOrder!=0}">
							<td>${ga.gameOrder}</td>
						</c:if>
						<td><img height="20px" src="/img/gameLogo/${ga.id}.jpg"></td>
						<td>${ga.name}</td>
						<td>${ga.info}</td>
						<td>${ga.url}</td>
						<td>${ga.color}</td>
						<td>
							<a href="admin_live_list?gid=${ga.id}"><span class="glyphicon glyphicon-facetime-video"></span></a>
						</td>
						<td>
							<a href="admin_ranks_list?gid=${ga.id}"><span class="glyphicon glyphicon-user"></span></a>
						</td>
						<td>
							<a href="admin_gametable_list?gid=${ga.id}"><span class="glyphicon glyphicon-list-alt"></span></a>
						</td>
						<td>
							<a href="admin_game_image?id=${ga.id}"><span class="glyphicon glyphicon-picture"></span></a>
						</td>
						<td>
							<a href="admin_game_edit?id=${ga.id}"><span class="glyphicon glyphicon-edit"></span></a>
						</td>
						<td>
							<a class="delete" id="${ga.id}" href="admin_game_delete?id=${ga.id}"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						<c:if test="${ga.gameOrder==0}">
							<td>
								<a href="admin_game_show?id=${ga.id}"><span class="glyphicon glyphicon-eye-close"></a>
							</td>
						</c:if>
						<c:if test="${ga.gameOrder!=0}">
							<td>
								<a href="admin_game_hide?id=${ga.id}"><span class="glyphicon glyphicon-eye-open"></a>
							</td>
						</c:if>
						<c:if test="${ga.gameOrder==0}">
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="#" style="color: #DCDCDC;"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
						<c:if test="${ga.gameOrder!=0}">
							<td>
								<a href="admin_game_up?id=${ga.id}"><span class="glyphicon glyphicon-triangle-top"></span></a>
							</td>
							<td>
								<a href="admin_game_down?id=${ga.id}"><span class="glyphicon glyphicon-triangle-bottom"></span></a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-warning addDiv">
		<div class="panel-heading">新增赛事 (图片尺寸400*340)</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="admin_game_add" enctype="multipart/form-data">
				<table class="addTable">
					<tr>
						<td class="name">类型选择</td>
						<td>
							<select name="up" class="form-control">
								<option value="0">官方赛事</option>
								<option value="1">第三方赛事</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="name">赛事名</td>
						<td><input id="name" name="name" type="text" placeholder="10字以内" maxlength="10" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">赛事介绍</td>
						<td><textarea wrap="virtual" id="info" name="info" type="text" placeholder="272字以内" maxlength="272" class="form-control"></textarea></td>
					</tr>
					<tr>
						<td class="name">网址</td>
						<td><input id="url" name="url" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">背景色</td>
						<td><input id="color" placeholder="输入格式#000000" name="color" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td class="name">图标</td>
						<td><input id="gamePic" name="image" accept="image/*" type="file"/></td>
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