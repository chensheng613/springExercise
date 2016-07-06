<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="<spring:url value="/resources/jquery-easyui-1.4.4/themes/default/easyui.css" htmlEscape="true" />"
	rel="stylesheet" media="screen" />
</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height:100px;"></div>
    <div data-options="region:'south'" style="height:80px;"></div>
    <div data-options="region:'west'" style="width:150px;"></div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;"></div>
</div>
</body>
<script type="text/javascript"
	src="<spring:url value="/resources/jquery/jquery-1.12.0.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/jquery-easyui-1.4.4/jquery.easyui.min.js"/>"></script>
</html>