/**
 * 用户注册页面相关JS
 */
$(function() {
	var $form = $("#form");
	$form.form();
	$("#btn").on("click", function() {
		$form.validate(function(error) {
			if (!error) {
				// 校验成功后提交数据
				var obj = getFormJson($form);
				$.ajax({
					type : "POST",
					url : _ctx + "/user/register.json",
					data : JSON.stringify(obj),
					contentType : "application/json;charset=UTF-8",
					success : function(data) {
						 if(data.){}
					},
					error : function(error) {
						alert(error);
					}
				});
			}
		});
	});
});