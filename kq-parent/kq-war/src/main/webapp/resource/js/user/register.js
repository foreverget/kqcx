/**
 * 用户注册页面相关JS
 */
$(function() {
	var $form = $("#form");
	$form.form();
	$("#btn").on("click", function() {
		var openId = $('input[name=openId]').val();
		// 未选择条约
		var $ck = $('#ck_rule:checked').val();
		if ($ck == 'on') {
			
		} else {
			$.alert("抱歉,您未同意遵守本系统条约");
			return;
		}
		$form.validate(function(error) {
			if (!error) {
				// 校验成功后提交数据
				var obj = getFormJson($form);
				$.ajax({
					type : "POST",
					url : _ctx + "/user/register",
					cache:false,
					data : JSON.stringify(obj),
					contentType : "application/json;charset=UTF-8",
		            dataType:'json',
					success : function(data) {
						 if(data.code == 0){
						     $.toast("注册成功");
							 window.location.href=_ctx + "/user/toUserInfo?openId="+openId;
						 }else{
							 $.alert("注册失败", "提示");
						 }
					},
					error : function(error) {
						$.toptips("网络或服务异常", "warning");
					}
				});
			}
		});
	});
});