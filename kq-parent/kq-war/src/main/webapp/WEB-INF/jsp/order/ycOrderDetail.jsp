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
	<div class="weui_media_box weui_media_appmsg">
		<div class="weui_media_bd">
			<h4 class="weui_media_title list-line-margin-bottom">${order.goTime}&nbsp;&nbsp;<font
					class="font-blue">${order.price}</font>&nbsp;元/人
			</h4>
			<p class="weui_media_desc list-line-margin-bottom">起点:
				${order.startAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">终点:
				${order.endAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">
				订单状态:
				<c:if test="${order.status == 0}">
					<font class="font-blue">等客中</font>
				</c:if>
				<c:if test="${order.status == 1}">
					<font class="font-green">客满发车</font>
				</c:if>
				<c:if test="${order.status == 2}">
					已送达
				</c:if>
				<c:if test="${order.status == 3}">
					<font class="font-red">订单已取消</font>
				</c:if>
			</p>
		</div>
	</div>
	<div class="weui_media_box">
		<p class="weui_media_title list-line-margin-bottom">司机信息</p>
		<p class="weui_media_desc list-line-margin-bottom">司机:
			${order.name}</p>
		<p class="weui_media_desc list-line-margin-bottom">电话:<a href="tel:${order.mobile}">联系司机</a></p>
	</div>
	<div class="weui_media_box">
		<p class="weui_media_title list-line-margin-bottom">车辆信息</p>
		<!-- 		<p class="weui_media_desc list-line-margin-bottom">车型: -->
		<%-- 			${order.plates}</p> --%>
		<p class="weui_media_desc list-line-margin-bottom">车牌:
			${order.plates}</p>
		<!-- 		<p class="weui_media_desc list-line-margin-bottom">颜色: -->
		<%-- 			${order.plates}</p> --%>
		<p class="weui_media_desc list-line-margin-bottom">空余座位:
			${order.surplusSeat}</p>
	</div>
</body>
</html>
