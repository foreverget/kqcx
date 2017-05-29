<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改车辆信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>

<body ontouchstart style="background-color: #f8f8f8;">
	<form id="form">
		<input type="hidden" id='openId' name="openId" value="${openId}">
		<input type="hidden" id='id' name="id" value="${carInfo.id}">
		<h4><span class='f-red'>请您保证信息准确</span></h4>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">车型</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" name="models" required
						placeholder="请输入车型" emptyTips="请输入车型" value="${carInfo.models}" />
				</div>
			</div>

			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">车牌号</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" name="plates" required
						placeholder="输入车牌号" emptyTips="请输入车牌号" value="${carInfo.plates}" />
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">座位数</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" name="seat" value="${carInfo.seat}" required type="tel"
						pattern="^[1-8]\$" placeholder="请您输入车辆座位数1-8"
						emptyTips="请您输入正确车辆座位数" notMatchTips="请您输入正确车辆座位数" />
				</div>
			</div>

			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">颜色</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" name="color" required
						placeholder="请输入颜色" emptyTips="请输入颜色" value="${carInfo.color}" />
				</div>
			</div>
		</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary" href="javascript:;" id="editBtn">保存</a>
			<a class="weui_btn weui_btn_warn" href="javascript:;" id="delBtn">删除</a>
		</div>
	</form>
</body>
<script src="${ctx}/resource/js/car/editCar.js?_time=<%=_timestamp%>"></script>
</html>