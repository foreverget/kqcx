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
<title>我要出车</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>

<body ontouchstart style="background-color: #f8f8f8;">
	<!-- 	<div class="weui-header bg-blue"> -->
	<!-- 		<div class="weui-header-left"> -->
	<!-- 			<a class="icon icon-109 f-white">返回</a> -->
	<!-- 		</div> -->
	<!-- 		<h1 class="weui-header-title">出车发布</h1> -->
	<!-- 	</div> -->
	<form id="form">
		<input type="hidden" name="openId" value="${openId}">
		<div class="weui_cells_title" style="color: #F00; font-weight: bold;">出车信息发布</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label for="" class="weui_label">出发时间:</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" required
						placeholder="请选择出发时间" emptyTips="请选择出发时间" value="" id='goTime'
						name="goTime" />
				</div>
			</div>
			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label for="" class="weui_label">起点:</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='startAddr' name="startAddr" class="weui_input"
						type="text" required placeholder="请输入起始地点" tips="请输入起始地点" />
				</div>
				<div class="weui_cell_ft">
					<a id="selectaddr" class="weui-vcode-btn">选常用</a>
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label for="" class="weui_label">终点:</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='endAddr' name="endAddr" class="weui_input" type="text"
						required placeholder="请输入终止地点" tips="请输入终止地点" />
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<textarea id='memo' name="memo" class="weui_textarea"
						placeholder="可输入途径等重要信息" rows="3"></textarea>
					<div class="weui_textarea_counter">
						<span id='count'>0</span>/<span id='count_max'>200</span>
					</div>
				</div>
			</div>
			<!--<div class="weui_cells_title" style="color: #F00; font-weight: bold;">车辆信息</div>-->
			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">车牌号码</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='plates' name="plates" class="weui_input" type="text"
						required placeholder="请输入车牌号" tips="请输入车牌号" />
				</div>
				<div class="weui_cell_ft">
					<a href="javascript:;" id="selectcar" class="weui-vcode-btn">选车辆</a>
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">可乘人数</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='reqNum' name="reqNum" class="weui_input" type="tel"
						required pattern="^[1-4]\$" placeholder="输入1-4"
						emptyTips="请输入可乘人数" notMatchTips="可乘人数请输入1-4">
				</div>
				<label class="weui_label">人</label>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">车费单价</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='price' name="price" class="weui_input" type="tel" pattern="^[1-5]\$"
						required min="0" max="5" placeholder="输入1-5" emptyTips="请输入车费单价"
						notMatchTips="车费单价请输入1-5">
				</div>
				<label class="weui_label">元/人</label>
			</div>
			<!--<div class="weui_cells_title" style="color: #F00; font-weight: bold;">司机信息</div>-->
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">司机姓名</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='name' name="name" class="weui_input" type="text"
						required placeholder="请输入司机姓名" tips="请输入司机姓名">
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">联系电话</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input id='mobile' name="mobile" class="weui_input" type="tel"
						required pattern="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" maxlength="11" placeholder="请输入手机号"
						emptyTips="请输入手机号">
				</div>
			</div>
			<div class="weui_btn_area">
				<a id="formSubmitBtn" class="weui_btn bg-orange">马上发布</a>
			</div>
	</form>
</body>
<script src="${ctx}/resource/js/order/release.js?v=20170518"></script>
</html>
