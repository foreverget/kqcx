<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的常用地址</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<input type="hidden" id="openId" value="${openId}">
	<h4><span class='f-red'>请您填写具体常用往返地点</span></h4>
	<div class="weui_cells weui_cells_form">
		<div class="weui_cells">
			<div class="weui_cell id="start_cell"">
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" placeholder="请输入起点描述" type="text" name="start" required
					emptyTips="请输入起点描述">
				</div>
			</div>
			<div class="weui_cell" id="end_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" placeholder="请输入终点描述" type="text" name="end" required
					emptyTips="请输入终点描述" >
				</div>
			</div>
		</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary" href="javascript:"
				id="addAddrBtn">确认添加</a>
		</div>
	</div>
	<div class="weui_cells">
		<c:forEach var="addr" items="${list}">
			<div class="weui_cell" id="addr_id_${addr.id}" data-id='${addr.id}'>
				<div class="weui_cell_bd weui_cell_primary">
					<p>${addr.start}-->${addr.end}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
<script src="${ctx}/resource/weuix/jssdkapi.js?_time=<%=_timestamp%>"></script>
<script src="${ctx}/resource/js/addr/updateAddr.js?_time=<%=_timestamp%>"></script>
</html>