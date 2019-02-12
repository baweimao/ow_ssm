<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

</script>
  
<div class="nav">
	<p>网站导航</p>
</div>
<div class="web background">
	<c:forEach items="${cs}" var="c">
		<div class="webLeft">
		<a>${c.name}</a>
		</div>
		<div class="webRight">
			<c:forEach items="${c.ws}" var="w">
				<div class="webOne">
					<a href="${w.url}" target="_blank">
						<img src="/img/webLogo/${w.id}.jpg">
						${w.name}
					</a>
				</div>
			</c:forEach>
		</div>
		<div style="clear: both;"></div>
<!-- 		<div class="line"></div> -->
	</c:forEach>
</div>