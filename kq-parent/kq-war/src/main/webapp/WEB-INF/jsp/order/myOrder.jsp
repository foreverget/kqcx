<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的订单</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<div class="weui_cells weui_cells_access">
		<a class="weui_cell " href="javascript:;" id="my_take">
			<div class="weui_cell_bd weui_cell_primary">
				<p>我的约车单[3天内]</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a>
		<a class="weui_cell " href="javascript:;" id="my_release">
			<div class="weui_cell_bd weui_cell_primary">
				<p>我的出车单[3天内]</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a> 
		<a class="weui_cell " href="javascript:;" id="my_history">
			<div class="weui_cell_bd weui_cell_primary">
				<p>我的历史订单</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a> 
	</div>
</body>
<script src="${ctx}/resource/js/order/myOrder.js"></script>
</html>