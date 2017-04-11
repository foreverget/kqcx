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
<title>约车单详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart style="background-color: #FFFFFF;">
<div href="javascritp:;" class="weui_media_box weui_media_appmsg">
		<div class="weui_media_bd">
			<h4 class="weui_media_title list-line-margin-bottom">${order.goTime}&nbsp;&nbsp;&nbsp;&nbsp;单价&nbsp;${order.price}&nbsp;元</h4>
			<p class="weui_media_desc list-line-margin-bottom">起点:
				${order.startAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">终点:
				${order.endAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">订单状态:
				${order.status}</p>
			<br />
			<p class="weui_media_desc list-line-margin-bottom">乘客信息</p>
			<p class="weui_media_desc list-line-margin-bottom">司机:
				${order.name}</p>
			<p class="weui_media_desc list-line-margin-bottom">电话:
				${order.mobile}</p>	
			<p class="weui_media_desc list-line-margin-bottom">车型:
				${order.plates}</p>
			<p class="weui_media_desc list-line-margin-bottom">车牌:
				${order.plates}</p>
			<p class="weui_media_desc list-line-margin-bottom">座位数量:
				${order.plates}</p>
			<p class="weui_media_desc list-line-margin-bottom">颜色:
				${order.plates}</p>
		</div>
</body>
</html>
