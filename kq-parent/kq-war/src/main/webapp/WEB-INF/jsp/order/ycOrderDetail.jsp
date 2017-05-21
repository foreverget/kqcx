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
<title>约车单详细信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart class="page-bg">
	<input type="hidden" id='openId' name="openId" value="${openId}" />
	<input type="hidden" id='orderId' name="orderId" value="${order.id}" />
	<input type="hidden" id='version' name="version" value="${order.version}" />
	<input type="hidden" id='surplusSeat' name="surplusSeat" value="${order.surplusSeat}" />
	<div class="weui_cells_title">您约车的信息如下</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview-hd">
			<label class="weui-form-preview-label"><span class='f-orange'>出发时间</span></label>
			<span class="weui-form-preview-value"><span class='f-orange'>${order.goTime}</span></span>
			<label class="weui-form-preview-label"><span class='f-black'>从</span></label>
			<span class="weui-form-preview-value">${order.startAddr} </span><label
				class="weui-form-preview-label"><span class='f-black'>到</span></label>
			<span class="weui-form-preview-value">${order.endAddr}</span> <label
				class="weui-form-preview-label"><span class='f-black'>拼车单价</span></label>
			<span class="weui-form-preview-value"><span class='f-red'>¥${order.price}元/人</span></span>

			<label class="weui-form-preview-label"><span class='f-black'>司机</span></label>
			<span class="weui-form-preview-value">${order.name}</span> <label
				class="weui-form-preview-label"><span class='f-black'>联系司机</span></label>
			<span class="weui-form-preview-value"><a
				href="tel:${order.mobile}">点击呼叫</a></span> <label
				class="weui-form-preview-label"><span class='f-black'>车牌号码</span></label>
			<span class="weui-form-preview-value">${order.plates}</span>
		</div>
	</div>
</body>
</html>
