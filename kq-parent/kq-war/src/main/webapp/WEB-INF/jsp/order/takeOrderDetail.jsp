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
<title>约车信息详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart class="page-bg">
	<input type="hidden" id='openId' name="openId" value="${openId}" />
	<input type="hidden" id='orderId' name="orderId" value="${order.id}" />
	<input type="hidden" id='version' name="version" value="${order.version}" />
	<input type="hidden" id='surplusSeat' name="surplusSeat" value="${order.surplusSeat}" />
	<h3><span class='f-red'>请您认真确认以下详细信息再约</span></h3>
	<div class="weui-form-preview">
		<!--  
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
		-->
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
					class="weui-form-preview-value"><a
					href="tel:${order.mobile}">点击呼叫</a></span>
			</p>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label class="weui_label">我要约</label>
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
				<span >同意遵守<a id='rulelink' class="open-popup" href="javascript:;" data-target="#popup1">《平台使用条约及免责声明》</a></span>
			</div>
			<div class="weui_cell_ft">
				<input class="weui_switch" type="checkbox" id='ck_rule' checked/>
			</div>
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
	<div id="popup1" class="weui-popup-container">
		<div class="weui-popup-modal">
			<div class="weui_article">
				<h1>欢迎使用孔雀拼车微信公众服务平台</h1>
				<section>
					<h2 class="title" align="center">《平台使用条约及免责声明》</h2>
					<section>
						<h3>1.用户系统使用条约</h3>
						<p>您使用孔雀拼车公众号服务平台(以下简称“平台”), 即表示您同意以下事项:<br>
							a.您应保证在本平台提供的个人信息准确无误，避免发布虚假信息；<br>
							b.您不会利用本平台进行骚扰、妨碍他人或造成他人不便;<br>
							c.您不会尝试危害服务或平台程序;<br>
							d.当我们提出合理请求时,您会提供准确的身份证明;<br>
							e.您将遵守自己国家/地区以及您在使用应用程序或服务时所处国家/地区、 省和/或市的所有适用法律。<br>
							如果您违反以上任一规则,我们保留立即终止向您提供本平台服务和法律保护的权利。
						</p>
					</section>
					<section>
						<h3>2.平台免责声明</h3>
						<p>
							a.平台里所载信息均出于为公众传播有益资讯信息之目的，平台管理员有责任保证其准确性、完整性、有效性、时效性，
							      但不意味着赞同其观点或证实其内容的真实性，不能完全保证信息的合理性、准确性和完整性，
							      亦不对因信息的不合理、不准确导致的任何损失或损害承担任何法律责任。<br>
							b.本平台提供拼车出行信息，供用户参考。为了避免争议纠纷的发生，保障用户的合法权益， 
							      建议用户事先核实各自身份证、驾驶证、行驶证等安全信息，建议拼车用户签订书面的合乘协议，并购买适用的保险。
							      如产生纠纷，争议双方友好协商解决；如产生财产损失或人身伤亡等重大事故，应按照国家相关法律法规和保险相应条款处理和进行保险理赔。<br>
							c.本平台对用户在使用免费资讯过程中所产生的争议、纠纷、责任、赔偿等相关问题概不负责。<br>
							d.本平台积极响应国家相关政策规定，坚决抵制任何形式的非法营运活动，一旦发现有任何非法行为有权向执法部门提供相关线索和信息，
							      并对相关用户进行封号处理。<br>
							e.本平台使用者因为违反本声明的规定而触犯法律法规或政策的，一切后果自行负责，本平台不承担任何责任。<br>
							本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。
						</p>
					</section>
				</section>
				<div class="weui_btn_area">
					<a id="p0_ok_btn" href="javascript:;"
						class="weui_btn weui_btn_primary close-popup" href="javascript:;">同意遵守</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${ctx}/resource/js/order/takeOrderDetail.js?v=20170540"></script>
</html>
