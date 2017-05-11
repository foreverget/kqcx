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
	<input type="hidden" id="openId" value="${openId}">
	<div class="weui_cells weui_cells_form">
		<div class="weui_cell" id="name_cell" style="display:none" >
			<div class="weui_cell_hd">
				<label class="weui_label">姓名</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="name" value="${value}"
					required emptyTips="请输入姓名" />
			</div>
		</div>

		<div class="weui_cell" id="mobile_cell"  style="display:none" >
			<div class="weui_cell_hd">
				<label class="weui_label">手机号</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="mobile" value="${value}"
					required emptyTips="请输入手机号" />
			</div>
		</div>
		<div class="weui_cell" id="email_cell"  style="display:none" >
			<div class="weui_cell_hd">
				<label class="weui_label">常用邮箱</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="email" value="${value}"
					required emptyTips="请输入邮箱" />
			</div>
		</div>
		<div class="weui_cell"  id="addr_cell"  style="display:none" >
			<div class="weui_cell_hd">
				<label class="weui_label">住址</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="text" name="addr" value="${value}"
					required emptyTips="请输入住址" />
			</div>
		</div>
		<div id="gender_cell"  style="display:none" >
			<div class="weui_cells_title">选择性别</div>
			<div class="weui_cells weui_cells_radio">
				<label class="weui_cell weui_check_label" for="x11">
					<div class="weui_cell_bd weui_cell_primary">
						<p>男</p>
					</div>
					<div class="weui_cell_ft">
						<input type="radio" class="weui_check" name="gender" id="x11"
							value="男"> <span class="weui_icon_checked"></span>
					</div>
				</label> <label class="weui_cell weui_check_label" for="x12">

					<div class="weui_cell_bd weui_cell_primary">
						<p>女</p>
					</div>
					<div class="weui_cell_ft">
						<input type="radio" name="gender" class="weui_check" id="x12"
							value="女"> <span class="weui_icon_checked"></span>
					</div>
				</label>
			</div>
		</div>
		
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary" href="javascript:" id="saveBtn">保存</a>
		</div>
	</div>
</body>
<script type="text/javascript">
var key = '${key}';
</script>
<script src="${ctx}/resource/js/user/updateUserInfo.js?v=20170511"></script>
</html>