<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/css.jsp"%>
<%@include file="/WEB-INF/jsp/common/js.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的订单信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<input type="hidden" name="openId" value="${openId}">
	<div class="slide" id="slidediv">
		<ul>
			<li><a href="#"> <img
					src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"
					data-src="http://7xr193.com1.z0.glb.clouddn.com/1.jpg" alt="">
			</a>
				<div class="slide-desc">广告位招租</div></li>
			<li><a href="#"> <img
					src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"
					data-src="http://7xr193.com1.z0.glb.clouddn.com/2.jpg" alt="">
			</a>
				<div class="slide-desc">广告位招租</div></li>
			<li><a href="#"> <img
					src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"
					data-src="http://7xr193.com1.z0.glb.clouddn.com/3.jpg" alt="">
			</a>
				<div class="slide-desc">广告位招租</div></li>
		</ul>
		<div class="dot">
			<span></span> <span></span> <span></span>
		</div>
	</div>
	<div class="weui_cells weui_cells_access">
		<a class="weui_cell " href="javascript:;" id="my_take">
			<div class="weui_cell_bd weui_cell_primary">
				<p>我是乘客-查看约车单[3天内]</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a>
		<a class="weui_cell " href="javascript:;" id="my_release">
			<div class="weui_cell_bd weui_cell_primary">
				<p>我是车主-查看出车单[3天内]</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a> 
		<a class="weui_cell " href="javascript:;" id="my_history">
			<div class="weui_cell_bd weui_cell_primary">
				<p>历史订单</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a> 
	</div>
</body>
<script src="${ctx}/resource/js/order/myOrder.js?v=20170512"></script>
</html>