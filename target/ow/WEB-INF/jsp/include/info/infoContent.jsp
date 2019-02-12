<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function() {
	var h = $("div.info").height()+50;
	$("div.infoDiv").height(h);
});
</script>

<div class="footInfo">
	<div class="navTop"></div>
	<div class="info">${f.content}</div>
	<div class="infoDiv"></div>
</div>