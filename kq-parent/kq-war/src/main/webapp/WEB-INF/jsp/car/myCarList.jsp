<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<input type="hidden" id="openId" value="${openId}">
	<div class="weui_cells weui_cells_form">
		<div class="weui_cells">
			<c:forEach var="car" items="${list}">
				<a class="weui_cell " href="javascript:;" id="up_carInfo_${car.id}"  data-id='${car.id}'>
					<div class="weui_cell" id="car_id_${car.id}">
						<div class="weui_cell_bd weui_cell_primary">
							<p>车牌号：${car.plates}</p>
						</div>
					</div>
					<div class="weui_cell_ft">修改</div>
				</a>
			</c:forEach>
		</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary" href="javascript:" id="toAddCar">去添加车辆</a>
		</div>
	</div>

</body>
<script src="${ctx}/resource/js/car/myCarList.js"></script>
</html>