/**
 * 新增车辆信息页面相关JS
 */
$(function() {
	var $form = $("#form");
	$form.form();
	// 修改车辆
	$('#editBtn').on(
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
									$.toast("保存成功");
									window.location.href = _ctx
											+ "/car/toMyCarList?openId="
											+ openId;
								} else {
									$.alert("保存失败", "提示");
								}
							},
							error : function(error) {
								$.alert(error);
							}
						});
					}
				});
			});
	// 删除车辆
	$('#delBtn').on(
			"click",
			function() {
				var openId = $('input[name=openId]').val();
				var id = $('input[name=id]').val();
				$.confirm("您确定要删除吗?", "确认删除?", function() {
					// 提交数据
					var obj = getFormJson($form);
					$.ajax({
						type : "POST",
						url : _ctx + "/car/deleteCar",
						data : JSON.stringify(obj),
						contentType : "application/json;charset=UTF-8",
						dataType : 'json',
						success : function(data) {
							if (data.code == 0) {
								$.toast("删除成功");
								window.location.href = _ctx
										+ "/car/toMyCarList?openId="
										+ openId;
							} else {
								$.alert("删除失败", "提示");
							}
						},
						error : function(error) {
							$.alert(error);
						}
					});

				}, function() {
					//取消操作
				});
			});
});