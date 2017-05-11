<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/tag.jsp"%>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/css.jsp"%>
<%@include file="/WEB-INF/jsp/common/js.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我要约车</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>

<body ontouchstart style="background-color: #f8f8f8;">
	<input type="hidden" name="openId" value="${openId}" />
	<!-- 	<div class="weui-header bg-blue"> -->
	<!-- 		<div class="weui-header-left"> -->
	<!-- 			<a href="javascript:history.go(-1);" class="icon icon-109 f-white">返回</a> -->
	<!-- 		</div> -->
	<!-- 		<h1 class="weui-header-title">我要约车</h1> -->
	<!-- 		<div class="weui-header-right"> -->
	<!-- 			<a class="icon f-white">查找</a> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<form id="form">
		<div class="weui_tab" style="height: 44px;" id="tabdiv">
			<div class="weui_navbar" style="height: 44px;">
				<div class="weui_navbar_item">今天</div>
				<div class="weui_navbar_item">明天</div>
				<div class="weui_navbar_item">后天</div>
			</div>
		</div>
		<div class="weui_panel weui_panel_access">
			<div id='td' class="weui_panel_bd"></div>
			<div id='tm' class="weui_panel_bd"></div>
			<div id='ht' class="weui_panel_bd"></div>
		</div>
	</form>
</body>
<script src="${ctx}/resource/js/order/take.js?v=20170511"></script>
</html>
