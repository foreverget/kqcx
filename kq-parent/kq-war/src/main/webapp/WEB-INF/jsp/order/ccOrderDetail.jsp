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
<title>作为车主-我的出车单详细信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart class="page-bg">
	<input type="hidden" id='openId' name="openId" value="${openId}" />
	<input type="hidden" id='orderId' name="orderId" value="${order.id}" />
	<input type="hidden" id='version' name="version"
		value="${order.version}" />
	<input type="hidden" id='surplusSeat' name="surplusSeat"
		value="${order.surplusSeat}" />
	<div class="weui-form-preview">
		<div class="weui-form-preview-bd">
			<div class="weui-loadmore weui-loadmore-line">
				<span class="weui-loadmore-tips"><span class='f-red'>已约乘客信息</span></span>
			</div>
			<c:forEach var="op" items="${opList}">
				<p>
					<label class="weui-form-preview-label"> <c:if
							test="${order.status == '0'}">
							<a id="tr_id_${op.openId}" data-openId="${op.openId}"
								data-name="${op.name}">${op.name}</a>
						</c:if> <c:if test="${order.status != '0'}">
						${op.name}
					</c:if> &nbsp;&nbsp;约${op.count}人
					</label> <span class="weui-form-preview-value"><a
						href="tel:${op.mobile}">点击呼叫</a></span>
				</p>
			</c:forEach>
		</div>
		<div class="weui-form-preview-hd">
			<label class="weui-form-preview-label">出发时间</label> <em
				class="weui-form-preview-value">${order.goTime}</em> <label
				class="weui-form-preview-label">拼车单价</label> <em
				class="weui-form-preview-value">¥${order.price}/人</em>
		</div>
		<div class="weui-form-preview-bd">
			<p>
				<label class="weui-form-preview-label">从</label> <span
					class="weui-form-preview-value">${order.startAddr}</span>
			</p>
			<p>
				<label class="weui-form-preview-label">到</label> <span
					class="weui-form-preview-value">${order.endAddr}</span>
			</p>
			<p>
				<label class="weui-form-preview-label">途经</label> <span
					class="weui-form-preview-value">${order.memo}</span>
			</p>
			<p>
				<label class="weui-form-preview-label">车牌号码</label> <span
					class="weui-form-preview-value">${order.plates}</span>
			</p>
			<p>
				<label class="weui-form-preview-label">司机</label> <span
					class="weui-form-preview-value">${order.name}</span>
			</p>
			<p>
				<label class="weui-form-preview-label">司机电话</label> <span
					class="weui-form-preview-value">${order.mobile}</span>
			</p>
		</div>
	</div>
</body>
</html>
