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
<title>约车单详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
</head>
<body ontouchstart style="background-color: #FFFFFF;">
	<div href="javascritp:;" class="weui_media_box weui_media_appmsg">
		<div class="weui_media_bd">
			<h4 class="weui_media_title list-line-margin-bottom" id ="ycTime"></h4>
			<p class="weui_media_desc list-line-margin-bottom">起点:
				${order.startAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">终点:
				${order.endAddr}</p>
			<p class="weui_media_desc list-line-margin-bottom">司机:
				${order.name}</p>
			<p class="weui_media_desc list-line-margin-bottom">车牌:
				${order.plates}</p>
			<p class="weui_media_desc list-line-margin-bottom">电话:
				${order.mobile}</p>	
		</div>
	</div>
</body>
<script type="text/javascript">
var date = getSmpFormatDateByLong('${order.createTime}');
var str = date +'&nbsp;&nbsp;&nbsp;&nbsp;单价&nbsp; '+'${order.price}'+' &nbsp;元';
$('#ycTime').html(str);
</script>
</html>
