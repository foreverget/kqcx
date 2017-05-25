<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/tag.jsp"%>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/css.jsp"%>
<%@include file="/WEB-INF/jsp/common/js.jsp"%>
<!doctype html>
<html>
<head>
<title>我要约车</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/listloading/src/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/listloading/src/css/listloading.min.css" />
<script src="${ctx}/resource/listloading/src/jslib/require.js"></script>
</head>
<body ontouchstart style="background-color: #f8f8f8;">
	<input type="hidden" name="openId" id="openId" value="${openId}" />
	<style type="text/css">
		html, body {
			height: 100%;
			background: #E6E6E6;
		}
		
		.listloading-tab {
			display: none;
			position: absolute;
			top: 51px;
			left: 0;
			width: 100%;
			height: calc(100% - 51px);
			overflow: hidden;
		}
		
		.order-list>li {
			height: 100px;
			line-height: 24px;
			padding: 10px 15px 10px 10px;
			margin: 10px;
			border-radius: 3px;
			box-shadow: 1px 1px 2px #ccc;
			background: #fff;
		}
		
		.order-list>li>span.icon {
			float: left;
			width: 50px;
			height: 50px;
			line-height: 50px;
			margin-right: 10px;
			border-radius: 50%;
			background: #FFC107;
			text-align: center;
			font-size: 36px;
		}
		
		.svg-icon {
			width: 1em;
			height: 1em;
			vertical-align: -0.1em;
			fill: currentColor;
			overflow: hidden;
		}
		
		.order-list>li>.title {
			font-size: 16px;
			color: #292929;
		}
		
		.order-list>li>.text {
			color: #7B7B7B;
		}
		
		.listloadingClass {
			overflow: hidden;
		}
		
		/*tab*/
		#listloadingtab {
			display: flex;
			border-bottom: 1px solid #8c887d;
		}
		
		#listloadingtab>span {
			flex: 1;
			height: 50px;
			line-height: 50px;
			font-size: 20px;
			text-align: center;
			cursor: pointer;
		}
		
		#listloadingtab>span.active {
			background: #FFC107;
			color: #fff;
		}
	</style>
	<div id="listloadingtab">
		<span class="active">今天</span> <span>明天</span> <span>后天</span>
	</div>
	<div id="listloading0" class="listloading-tab" style="display: block;">
		<div>
			<ul>
				<div class='page-hd'>
					<a id="searchlink0" href="javascript:;" class="weui_btn weui_btn_primary open-popup "
						data-target="#popup0">检索</a>
				</div>
			</ul>
			<ul id="order-list-0" class="order-list"></ul>
		</div>
	</div>
	<div id="listloading1" class="listloading-tab">
		<div>
			<ul>
				<div class='page-hd'>
					<a id="searchlink1" href="javascript:;" class="weui_btn weui_btn_primary open-popup "
						data-target="#popup1">检索</a>
				</div>
			</ul>
			<ul id="order-list-1" class="order-list"></ul>
		</div>
	</div>
	<div id="listloading2" class="listloading-tab">
		<div>
			<ul>
				<div class='page-hd'>
					<a id="searchlink2" href="javascript:;" class="weui_btn weui_btn_primary open-popup "
						data-target="#popup2">检索</a>
				</div>
			</ul>
			<ul id="order-list-2" class="order-list"></ul>
		</div>
	</div>
	
	<div id="popup0" class="weui-popup-container">
		<div class="weui-popup-modal">
			<form id="form">
				<div class="weui_cells weui_cells_form ">
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">出发地点:</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" value="" id='text1' />
						</div>

					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">目的地点:</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" value="" id='text2' />

						</div>

					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label">出发时间:</label>
						</div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" value="" id='time' />
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd">
							<label for="" class="weui_label"></label>
						</div>
					</div>
				</div>
			</form>
			<div class="weui-form-preview">
				<div class="weui-form-preview-ft">
					<a id="p0_close"
						class="weui-form-preview-btn weui-form-preview-btn-default close-popup"
						href="javascript:;"><span class='f116'>取消检索</span></a>
					<button id="p0_reset"
						class="weui-form-preview-btn weui-form-preview-btn-primary"><span class='f116 f-orange'>重置条件</span></button>
				</div>
			</div>
			<div class="weui_btn_area">
				<a id="p0_ok" href="javascript:;"
					class="weui_btn weui_btn_primary close-popup" onclick="">开始检索</a>
			</div>
		</div>
	</div>
	
	<div id="popup1" class="weui-popup-container">
		<div class="weui-popup-modal">
			<div class="weui_article">
				<h2 class="title">按条件检索明天出车信息</h2>
			</div>
			<p class='page-bd-15'>
				<a href="javascript:;"
					class="weui_btn weui_btn_plain_primary close-popup" id="p1">确定</a>
			</p>
		</div>
	</div>
	
	<div id="popup2" class="weui-popup-container">
		<div class="weui-popup-modal">
			<div class="weui_article">
				<h2 class="title">按条件检索后天出车信息</h2>
			</div>
			<p class='page-bd-15'>
				<a href="javascript:;"
					class="weui_btn weui_btn_plain_primary close-popup" id="p2">确定</a>
			</p>
		</div>
	</div>
</body>
<script src="${ctx}/resource/js/order/take.js?v=20170546"></script>
</html>
