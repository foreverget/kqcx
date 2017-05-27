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
<title>信息详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart class="page-bg">
	<input type="hidden" id='openId' name="openId" value="${openId}" />
	<input type="hidden" id='orderId' name="orderId" value="${order.id}" />
	<input type="hidden" id='version' name="version" value="${order.version}" />
	<input type="hidden" id='surplusSeat' name="surplusSeat" value="${order.surplusSeat}" />
	<h3>请您认真确认以下约车详细信息</h3>
	<div class="weui-form-preview">
		<div class="weui-form-preview-hd">
			<label class="weui-form-preview-label"><span class='f-orange'>出发时间</span></label>
			<span class="weui-form-preview-value"><span class='f-orange'>${order.goTime}</span></span>
			<label class="weui-form-preview-label"><span class='f-black'>从</span></label>
			<span class="weui-form-preview-value">${order.startAddr} </span><label
				class="weui-form-preview-label"><span class='f-black'>到</span></label>
			<span class="weui-form-preview-value">${order.endAddr}</span><label
				class="weui-form-preview-label"><span class='f-black'>途经</span></label>
			<span class="weui-form-preview-value">&nbsp;${order.memo}</span> <label
				class="weui-form-preview-label"><span class='f-black'>拼车单价</span></label>
			<span class="weui-form-preview-value"><span class='f-red'>¥${order.price}元/人</span></span>

			<label class="weui-form-preview-label"><span class='f-black'>司机</span></label>
			<span class="weui-form-preview-value">${order.name}</span> <label
				class="weui-form-preview-label"><span class='f-black'>联系司机</span></label>
			<span class="weui-form-preview-value"><a
				href="tel:${order.mobile}">点击呼叫</a></span> <label
				class="weui-form-preview-label"><span class='f-black'>车牌号码</span></label>
			<span class="weui-form-preview-value">${order.plates}</span> <label
				class="weui-form-preview-label"><span class='f-black'>当前可约</span></label>
			<span class="weui-form-preview-value">${order.surplusSeat}&nbsp;&nbsp;&nbsp;&nbsp;人</span>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label class="weui_label">约车人数</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input id='myCount' name="myCount" class="weui_input f-red" type="tel" value="1"
					required pattern="^[1-4]\$" placeholder="请输入约车人数" emptyTips="请输入约车人数"
					notMatchTips="已超过当前可约人数">
			</div>
			<label class="weui_label">人</label>
		</div>
		<div class="weui_cell weui_cell_switch">
			<div class="weui_cell_hd weui_cell_primary">
				<span >遵守<a id='rulelink' class="open-popup" href="javascript:;" data-target="#popup1">《系统使用条约及免责声明》</a></span>
			</div>
			<div class="weui_cell_ft">
				<input class="weui_switch" type="checkbox" id='ck_rule' checked/>
			</div>
		</div>

		<div class="weui_btn_area">
			<c:if test="${order.surplusSeat == 0}">
				<a class="weui_btn weui_btn_disabled weui_btn_default">已满</a>
			</c:if>
			<c:if test="${order.surplusSeat > 0}">
				<a class="weui_btn weui_btn_primary" id="takeBtn">我约</a>
			</c:if>
		</div>
	</div>
	<div id="popup1" class="weui-popup-container">
		<div class="weui-popup-modal">
			<div class="weui_article">
				<h1>欢迎使用孔雀拼车</h1>
				<section>
					<h2 class="title">您使用本系统应遵循以下条款</h2>
					<section>
						<h3>1.标题</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua. Ut enim ad minim veniam, quis nostrud exercitation
							ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
							aute</p>
					</section>
					<section>
						<h3>2.什么是小说</h3>
						<p>小说：以刻画人物形象为中心，通过完整的故事情节和环境描写来反映社会生活的文学体裁。人物、情节、环境是小说的三要素。情节一般包括开端、发展、高潮、结局四部分，有的包括序幕、尾声。环境包括自然环境和社会环境。</p>
					</section>
				</section>
			</div>
			<div class="weui_btn_area">
				<a id="p0_ok_btn" href="javascript:;"
					class="weui_btn weui_btn_primary close-popup" href="javascript:;">返回</a>
			</div>
		</div>
	</div>
</body>
<script src="${ctx}/resource/js/order/takeOrderDetail.js?v=20170540"></script>
</html>
