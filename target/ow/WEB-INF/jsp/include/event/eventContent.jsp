<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function() {
	$("div.showRanks").click(function() {
		$(this).next(".ranks").toggle(100);
	});
});
</script>

<div class="navTop"></div>
<div class="event">
	<div class="nav">
		<p>官方赛事</p>
	</div>
	<c:forEach items="${gsTop}" var="g">
		<div class="owl" style="background-color:${g.color};">
			<div class="owlImg">
				<img src="/img/gameLogo/${g.id}.jpg">
			</div>
			<div class="right">
				<div class="title">
				<span>${g.name}</span>
				<a href="${g.url}" target="_blank">(官网)</a>
				</div>
				<div class="info">${g.info}</div>
				<div class="backDiv"></div>
			</div>
		</div>
		<div class="showRanks" style="background-color:${g.color};"<c:if test="${empty g.rs}">hidden="hidden"</c:if>>查看参赛队伍</div>
		<div class="ranks background" hidden="hidden">
			<c:forEach items="${g.rs}" var="r">
				<div class="ranksOne">
					<img src="/img/ranksLogo/${r.id}.jpg">
					<div>${r.name}</div>
				</div>
			</c:forEach>
			<div style="clear: both;"></div>
		</div>
	</c:forEach>
	
	<div class="nav">
		<p>第三方赛事</p>
	</div>
	<c:forEach items="${gsArt}" var="ga">
		<div class="owl" style="background-color:${ga.color};">
			<div class="owlImg">
				<img src="/img/gameLogo/${ga.id}.jpg">
			</div>
			<div class="right">
				<div class="title">
					<span>${ga.name}</span>
					<a href="${ga.url}" target="_blank">(官网)</a>
				</div>
				<div class="info">${ga.info}</div>
				<div class="backDiv"></div>
			</div>
		</div>
		<div class="showRanks" style="background-color:${ga.color};"<c:if test="${empty ga.rs}">hidden="hidden"</c:if>>查看参赛队伍</div>
		<div class="ranks background" hidden="hidden">
			<c:forEach items="${ga.rs}" var="r">
				<div class="ranksOne">
					<img src="/img/ranksLogo/${r.id}.jpg">
					<div>${r.name}</div>
				</div>
			</c:forEach>
			<div style="clear: both;"></div>
		</div>
	</c:forEach>
	<div class="navBar">
</div>