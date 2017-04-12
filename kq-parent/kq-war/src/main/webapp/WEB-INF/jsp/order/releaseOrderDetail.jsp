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
<title>出车单详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart style="background-color: #FFFFFF;">
	<input type="hidden" name="orderId" value="${order.id}" />
	<input type="hidden" name="openId" value="${order.openId}" />
	<div class="weui_media_box weui_media_appmsg">
		<div class="weui_media_bd">
			<h4 class="weui_media_title list-line-margin-bottom">${order.goTime}&nbsp;&nbsp;&nbsp;&nbsp;单价&nbsp;${order.price}&nbsp;元</h4>
			<p class="weui_media_desc list-line-margin-bottom">起点:
				${order.startAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">终点:
				${order.endAddr}</p>
			<p class="weui_media_desc">
				订单状态:
				<c:if test="${order.status == 0}">
					等客中
				</c:if>
				<c:if test="${order.status == 1}">
					客满发车
				</c:if>
				<c:if test="${order.status == 2}">
					已送达
				</c:if>
				<c:if test="${order.status == 3}">
					订单已取消
				</c:if>
			</p>
		</div>
	</div>
	<div class="weui_media_box">
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
		<p class="weui_media_desc ">颜色: ${order.plates}</p>
	</div>
	<div class="weui_media_box">
		<div class="weui_media_desc list-line-margin-bottom">乘客信息</div>
		<c:forEach var="op" items="${opList}">
			<div class="weui_media_desc list-line-margin-bottom  ">
				${op.name}&nbsp;&nbsp;&nbsp;&nbsp;${op.count}人&nbsp;&nbsp;&nbsp;&nbsp;${op.mobile}
				<c:if test="${order.status == '0'}">
					<a id="tr_id_${op.openId}" data-openId="${op.openId}"
						class="weui_btn weui_btn_mini weui_btn_warn">移除</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div class="weui_btn_area weui_btn_area_inline">
		<c:if test="${order.status == '0'}">
			<a class="weui_btn weui_btn_warn" id="channelBtn">取消订单</a>
			<a class="weui_btn weui_btn_primary" id="okBtn">客满出发</a>
		</c:if>
	</div>
</body>
<script src="${ctx}/resource/js/order/releaseOrderDetail.js"></script>
</html>
