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
<title>我的约车单</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>

<body ontouchstart style="background-color: #f8f8f8;">
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
<script src="${ctx}/resource/js/order/take.js"></script>
</html>
