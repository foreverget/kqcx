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
<title>我的个人信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
<script src="${ctx}/resource/weuix/jweixin-1.0.0.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
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
		</a> 
		<!--  -->
		<a class="weui_cell " href="javascript:;" id="email_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>常用邮箱</p>
			</div>
			<div class="weui_cell_ft"  data-name="email">${userInfo.email}</div>
		</a> 
		<a class="weui_cell " href="javascript:;" id="addr_ce">
			<div class="weui_cell_bd weui_cell_primary">
				<p>住址</p>
			</div>
			<div class="weui_cell_ft" data-name="addr">${userInfo.addr}</div>
		</a>
		<a class="weui_cell " href="javascript:;" id="up_car_btn">
			<div class="weui_cell_bd weui_cell_primary">
				<p>车辆信息</p>
			</div>
			<div class="weui_cell_ft f-red">车主需绑定</div>
		</a> <a class="weui_cell " href="javascript:;" id="up_addr_btn">
			<div class="weui_cell_bd weui_cell_primary">
				<p>常用地址</p>
			</div>
			<div class="weui_cell_ft f-red">车主需绑定</div>
		</a>
	</div>
	<div class="weui_cells">
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>当前状态</p>
			</div>
			<c:if test="${userInfo.status == 0}">
				<div class="weui_cell_ft f-red">
					不可用
				</div>
			</c:if>
			<c:if test="${userInfo.status == 1}">
				<div class="weui_cell_ft f-green">
					可用
				</div>
			</c:if>
			
		</div>
	</div>
	<!--  
	<div class="weui_cells weui_cells_form">
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<div class="weui_uploader">
					<div class="weui_uploader_hd weui_cell">
						<div class="weui_cell_bd weui_cell_primary">车主请上传您的驾驶证 行驶证 身份证</div>
						<div class="weui_cell_ft"></div>
					</div>
					<div class="weui_uploader_bd">
						<ul class="weui_uploader_files" id='imgcar'>

						</ul>
						<div class="weui_uploader_input_wrp" id="files">
							<span class="weui_uploader_input" id='headimgurl'></span>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	-->
</body>
<script src="${ctx}/resource/js/user/userInfo.js?v=20170514"></script>
</html>