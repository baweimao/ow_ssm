<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div class="article">
	<div class="null"></div>
	<h1>${a.title}</h1>
	<time>
		<span class="glyphicon glyphicon-time"></span>
		${a.fmtDate}
	</time>
	<a>来源：</a>
	<a class="url" target="_blank" href="${a.url}">${a.web.name}</a>
	<div class="content">${a.content}</div>
	
	<div class="changyan">
		<!--PC版-->
		<div id="SOHUCS" sid="请将此处替换为配置SourceID的语句"></div>
		<script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js" ></script>
		<script type="text/javascript">
		window.changyan.api.config({
		appid: 'cytV3PA8x',
		conf: 'prod_07ef73d658e9b47f83a51e29d6da7801'
		});
		</script>
	</div>
		<div class="navBar"></div>
</div>