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
<title></title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<script>
	$(function() {
		$("#submitBtn").on("click", function() {
			$.confirm("您确定要约车吗?", "确认?", function() {
		          $.toast("约车成功!");
		        }, function() {
		          //取消操作
		        });

		});
	});
</script>
</head>

<body ontouchstart style="background-color: #f8f8f8;">
	<div class="weui-header bg-blue">
		<div class="weui-header-left">
			<a class="icon icon-109 f-white">返回</a>
		</div>
		<h1 class="weui-header-title">我要约车</h1>
	</div>
	<div class="weui_cells_title" style="color: #F00; font-weight: bold;">路线信息</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview-hd">
			<p>
				<label class="weui-form-preview-label">起点</label> 
				<em class="weui-form-preview-value">大厂孔雀城五期南门</em>
			</p>
			<p>
				<label class="weui-form-preview-label">终点</label> 
				<em class="weui-form-preview-value">北京潞城地铁</em>
			</p>
			<p>
				<label class="weui-form-preview-label">车费单价</label> 
				<em class="weui-form-preview-value">¥5元/人</em>
			</p>
			<p>
				<label class="weui-form-preview-label">剩余座位</label> 
				<em class="weui-form-preview-value">2</em>
			</p>
		</div>
	</div>
	<div class="weui_cells_title" style="color: #F00; font-weight: bold;">车主信息</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview-hd">
			<p>
				<label class="weui-form-preview-label">姓名</label>
				<span class="weui-form-preview-value">张三</span>
			</p>
			<p>
				<label class="weui-form-preview-label">联系电话</label> 
				<em class="weui-form-preview-value">15911142542</em>
			</p>
		</div>
	</div>
	<div class="weui_cells_title" style="color: #F00; font-weight: bold;">车辆信息</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview-hd">
			<label class="weui-form-preview-label">车牌号码</label> 
			<em class="weui-form-preview-value">冀R00018</em>
		</div>
		<div class="weui-form-preview-bd">
			<p>
				<label class="weui-form-preview-label">车型</label> <span
					class="weui-form-preview-value">大众帕萨特</span>
				<label class="weui-form-preview-label">颜色</label> <span
					class="weui-form-preview-value">黑</span>
			</p>
		</div>
	</div>
	<div class="weui_btn_area">
		<a id="submitBtn" href="javascript:" class="weui_btn bg-orange">我约</a>
	</div>
</body>

</html>
