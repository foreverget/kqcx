/**
 * 车主发布相关JS
 */
$(function() {
	
	$("#goTime").datetimePicker({
		title : "请选择出发时间"
	});
	
	var max = $('#count_max').text();
	
	$('#memo').on(
			'input',
			function() {
				var text = $(this).val();
				var len = text.length;
				$('#count').text(len);
				if (len > max) {
					$(this).closest('.weui_cell').addClass(
							'weui_cell_warn');
				} else {
					$(this).closest('.weui_cell').removeClass(
							'weui_cell_warn');
				}
			});
	
	$("#selectaddr").select({
		title : "选择常用地址",
		items : [ {
			title : "孔雀城英国宫五期南门--->北京潞城地铁",
			value : "001",
		}, {
			title : "北京潞城地铁--->孔雀城英国宫五期南门",
			value : "002",
		}, {
			title : "北京土桥地铁--->孔雀城英国宫五期南门",
			value : "003",
		} ],
		onChange : function(d) {
			$.alert("你选择了" + d.values + d.titles);
		}
	});

	$("#selectcar").select({
			title : "选择爱好",
			autoClose : true,
			items : [ '冀A8888', '京A6666', '京J2986' ],
			onChange : function(c) {
				$.alert("你选择了" + c.values);
			}
		});
});

// 提交
var $form = $("#form");
$form.form();
$("#formSubmitBtn").on("click", function() {
	$form.validate(function(error) {
		if (!error) {
			$.confirm("您确定要发布吗?", "", function() {
				// 校验成功后提交数据
				var obj = getFormJson($form);
				$.ajax({
					type : "POST",
					url : _ctx + "/order/release",
					data : JSON.stringify(obj),
					contentType : "application/json;charset=UTF-8",
		            dataType:'json',
					success : function(data) {
						 if(data.code == 0){
						     $.toast("发布成功");
						     // 跳转到我的订单页面
							 //window.location.href=_ctx + "/user/toUserInfo?openId="+openId;
						 }else{
							 $.toast("发布失败", "forbidden");
						 }
					},
					error : function(error) {
						$.alert("网络异常","错误");
					}
				});
			}, function() {
				$.toast("取消发布", "cancel");
			});
		}
		
	});

});