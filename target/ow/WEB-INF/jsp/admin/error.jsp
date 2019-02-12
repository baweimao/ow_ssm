<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../include/admin/adminHead.jsp"%>
<%@ include file="../include/admin/adminTop.jsp"%>


<div class="jumbotron">
  <div class="container" align="center">
      <h2 class="text-info" style="font-family:宋体;font-weight:bold;font-size:49px">输入数据错误</h2>
      <br>
      <div class="text-muted">请检查内容，避免输入表情符号等元素</div>
      <br>
      <br>
      <p><a role="button" onclick="javascript:history.back(-1)" href="#" class="btn btn-success">返回</a></p>
  </div>
</div>