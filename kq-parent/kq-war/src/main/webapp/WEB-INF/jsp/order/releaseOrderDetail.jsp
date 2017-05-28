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
<title>我的出车信息详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart class="page-bg">
	<input type="hidden" id='openId' name="openId" value="${openId}" />
	<input type="hidden" id='orderId' name="orderId" value="${order.id}" />
	<input type="hidden" id='version' name="version" value="${order.version}" />
	<input type="hidden" id='surplusSeat' name="surplusSeat" value="${order.surplusSeat}" />
	<div class="weui-form-preview">
		<div class="weui-form-preview-bd">
			<div class="weui-loadmore weui-loadmore-line">
				<span class="weui-loadmore-tips"><span class='f-red'>已约乘客信息</span>-当前剩余<span class='f-red'>${order.surplusSeat}</span>个座位</span>
			</div>
			<c:forEach var="op" items="${opList}">
				<p id = 'ppp'>
					<label class="weui-form-preview-label f-green">
					<c:if test="${order.status == '0'}">
						<a id="tr_id_${op.openId}" data-openId="${op.openId}" data-name="${op.name}" data-value="${op.count}" >${op.name}</a>
					</c:if>
					<c:if test="${order.status != '0'}">
						${op.name}
					</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;约${op.count}人
					</label>
					<span class="weui-form-preview-value"><a
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
					class="weui-form-preview-value">&nbsp;${order.memo}</span>
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
    <p class="weui_btn_area  weui_btn_area_inline">
        <a id='channelBtn' class="weui_btn weui_btn_default" >取消出车</a>
        <c:if test="${order.surplusSeat != 0}">
        	<a id="okBtn" class="weui_btn weui_btn_primary"><i class="icon icon-71 f20"></i>停止约单</a>
        </c:if>
        <c:if test="${order.surplusSeat == 0}">
        	<a id="okBtn" class="weui_btn weui_btn_primary"><i class="icon icon-71 f20"></i>已约满</a>
        </c:if>
    </p>
	<div class="weui_cells_title">
		<span class="f-red">邻里诚信守则</span><br>
		<span class="f-black">1.如果有已约乘客告知您要取消约车,请点击该乘客名称进行删除。</span><br>
		<span class="f-black">2.如果您想删除某已约乘客,请点击呼叫该乘客进行原因告知，避免发生误解。</span><br>
		<span class="f-black">3.如果您想取消本次出车,请点击呼叫联系所有已约乘客告知本次出车取消原因，避免发生误解，然后点击乘客姓名删除所有乘客，后才可取消出车。</span>
	</div>
</body>
<script src="${ctx}/resource/js/order/releaseOrderDetail.js?v=20170533"></script>
</html>
