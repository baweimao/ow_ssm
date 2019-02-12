<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
 $(function () {
		$("div.one").mouseenter(function() {
			$(this).children("div.oneRight").css({"left":"85px","opacity":"1"});
		});
		$("div.one").mouseleave(function() {
			$(this).children("div.oneRight").css({"left":"0px","opacity":"0"});
		});

		$("a.weixin").hover(function() {
			var wxh = $(this).siblings(".weixinhao").val();
			$(this).siblings("p.oneTitle").html(wxh);
		},function(){
			var p = $(this).siblings(".xinxi").val();
			$(this).siblings("p.oneTitle").html(p);
		});
	});
</script>
<c:forEach items="${ts}" var="t">
	<div class="nav">
		<p>${t.name}</p>
	</div>
	<div class="people background">
		<c:forEach items="${t.ps}" var="p">
			<div class="one">
				<img class="oneImg img-circle" src="/img/peopleLogo/${p.id}.jpg">
				<div class="oneRight">
					<p class="oneTitle">${p.info}</p>
					<c:forEach items="${p.ss}" var="s" begin="0" end="7">
						<a class="oneLogoA <c:if test="${s.wid==44}">weixin</c:if>" <c:if test="${s.wid!=44}">href="${s.url}"</c:if> target="_blank">
							<img class="oneLogo img-circle" src="/img/webLogo/${s.wid}.jpg">
						</a>
						<c:if test="${s.wid==44}">
						<input class="weixinhao" value="${s.url}" hidden="hidden">
						<input class="xinxi" value="${p.info}" hidden="hidden">
						</c:if>
					</c:forEach>
				</div>
				<div class="oneName">${p.name}</div>
			</div>
		</c:forEach>
		<div style="clear: both;"></div>
	</div>
</c:forEach>
<div class="navBar">
