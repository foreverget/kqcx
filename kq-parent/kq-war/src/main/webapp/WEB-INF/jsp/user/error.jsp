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
<title>操作错误</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui2.css" />
<link rel="stylesheet" href="${ctx}/resource/weuix/style/weui3.css" />
<script src="${ctx}/resource/weuix/zepto.min.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<div class="weui_msg" id="msg">
		<div class="weui_icon_area">
			<i class="weui_icon_warn weui_icon_msg"></i>
		</div>
		<div class="weui_text_area">
			<h2 class="weui_msg_title">操作失败</h2>
			<p class="weui_msg_desc">您目前没有操作的权限<br>请及时联系客服或公众号内给我们留言</p>
		</div>
		<!--  
		<div class="weui_opr_area">
			<p class="weui_btn_area">
				<a href="javascript:history.go(-1);"
					class="weui_btn weui_btn_primary">返回</a>
			</p>
		</div>
		-->
		<div class="weui_extra_area"></div>
	</div>
</body>
<html>
