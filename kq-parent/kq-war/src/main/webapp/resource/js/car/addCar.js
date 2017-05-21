/**
 * 新增车辆信息页面相关JS
 */
$(function() {
	var $form = $("#form");
	$form.form();
	// 添加车辆
	$('#addBtn').on(
			"click",
			function() {
				var openId = $('input[name=openId]').val();
				$form.validate(function(error) {
					if (!error) {
						// 校验成功后提交数据
						var obj = getFormJson($form);
						$.ajax({
							type : "POST",
							url : _ctx + "/car/saveCar",
							data : JSON.stringify(obj),
							contentType : "application/json;charset=UTF-8",
							dataType : 'json',
							success : function(data) {
								if (data.code == 0) {
									$.toast("添加成功");
									window.location.href = _ctx
											+ "/car/toMyCarList?openId="
											+ openId;
								} else {
									$.alert("添加失败", "提示");
								}
							},
							error : function(error) {
								alert(error);
							}
						});
					}
				});
			});
});