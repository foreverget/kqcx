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
<title>Hello world</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<script>
	$(function() {
		$('#formSubmitBtn').click(function() {
			var $ck = $('#ck:checked').val();
			if ($ck == 'on') {
				alert('选中');
			} else {
				alert('没有选中');
			}

		});

	});
</script>
</head>

<body ontouchstart style="background-color: #f8f8f8;">
	<div class="page-hd">
		<h1 class="page-hd-title">单选,多选,逻辑开关</h1>
		<p class="page-hd-desc"></p>
	</div>
	<div class="page-bd">
		<div class="weui_cells_title">喜欢的爱好</div>
		<div class="weui_cells weui_cells_radio">
			<label class="weui_cell weui_check_label" for="x11">
				<div class="weui_cell_bd weui_cell_primary">
					<p>足球</p>
				</div>
				<div class="weui_cell_ft">
					<input type="radio" class="weui_check" name="radio1" id="x11">
					<span class="weui_icon_checked"></span>
				</div>
			</label> <label class="weui_cell weui_check_label" for="x12">

				<div class="weui_cell_bd weui_cell_primary">
					<p>篮球</p>
				</div>
				<div class="weui_cell_ft">
					<input type="radio" name="radio1" class="weui_check" id="x12"
						checked="checked"> <span class="weui_icon_checked"></span>
				</div>
			</label> <label class="weui_cell weui_check_label" for="x13">

				<div class="weui_cell_bd weui_cell_primary">
					<p>排球</p>
				</div>
				<div class="weui_cell_ft">
					<input type="radio" name="radio1" class="weui_check" id="x13">
					<span class="weui_icon_checked"></span>
				</div>
			</label>
		</div>


		<div class="weui_cells_title">你喜欢的国家</div>
		<div class="weui_cells weui_cells_checkbox">
			<label class="weui_cell weui_check_label" for="s11">
				<div class="weui_cell_hd">
					<input type="checkbox" class="weui_check" name="checkbox1" id="s11"
						checked="checked"> <i class="weui_icon_checked"></i>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<p>美国</p>
				</div>
			</label> <label class="weui_cell weui_check_label" for="s12">
				<div class="weui_cell_hd">
					<input type="checkbox" name="checkbox1" class="weui_check" id="s12">
					<i class="weui_icon_checked"></i>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<p>英国</p>
				</div>
			</label> <label class="weui_cell weui_check_label" for="s13">
				<div class="weui_cell_hd">
					<input type="checkbox" name="checkbox1" class="weui_check" id="s13">
					<i class="weui_icon_checked"></i>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<p>法国</p>
				</div>
			</label> <label class="weui_cell weui_check_label" for="s14">
				<div class="weui_cell_hd">
					<input type="checkbox" name="checkbox1" class="weui_check" id="s14">
					<i class="weui_icon_checked"></i>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<p>德国</p>
				</div>
			</label> <a href="javascript:void(0);" class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary f14">添加更多</div>
			</a>
		</div>

		<div class="weui_cells_title">开关</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell weui_cell_switch">
				<div class="weui_cell_hd weui_cell_primary">标题文字</div>
				<div class="weui_cell_ft">
					<input class="weui_switch" type="checkbox" checked />
				</div>
			</div>

			<div class="weui_cell weui_cell_switch">
				<div class="weui_cell_hd weui_cell_primary">是否选中</div>
				<div class="weui_cell_ft">
					<input class="weui_switch" type="checkbox" id='ck' />
				</div>
			</div>

		</div>

	</div>

	<div class="weui_btn_area">
		<a id="formSubmitBtn" href="javascript:"
			class="weui_btn weui_btn_primary">提交</a>
	</div>
</body>
</html>