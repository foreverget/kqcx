<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人中心</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<div class="weui_cells weui_cells_access">
		<input type="hidden" id="openId" value="${userInfo.openId}">
		<a class="weui_cell " href="javascript:;" id="name_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>姓名</p>
			</div>
			<div class="weui_cell_ft"  data-name="name">${userInfo.name}</div>
		</a> <a class="weui_cell " href="javascript:;"   id="gender_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>性别</p>
			</div>
			<div class="weui_cell_ft" data-name="gender">${userInfo.gender}</div>
		</a> <a class="weui_cell " href="javascript:;" id="mobile_ce" >
			<div class="weui_cell_bd weui_cell_primary">
				<p>手机号</p>
			</div>
			<div class="weui_cell_ft" data-name="mobile">${userInfo.mobile}</div>
		</a> <a class="weui_cell " href="javascript:;" id="email_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>常用邮箱</p>
			</div>
			<div class="weui_cell_ft"  data-name="email">${userInfo.email}</div>
		</a> <a class="weui_cell " href="javascript:;" id="addr_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>住址</p>
			</div>
			<div class="weui_cell_ft" data-name="addr">${userInfo.addr}</div>
		</a>
	</div>
</body>
<script src="${ctx}/resource/js/user/userInfo.js"></script>
</html>