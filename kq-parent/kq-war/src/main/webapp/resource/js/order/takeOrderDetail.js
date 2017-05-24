/**
 * 约车详细信息页面相关JS
 */
$(function() {
	// 绑定事件
	$("#rulelink").on('click',function(){
		$.alert('1.文明约车 \n;2.不准迟到','<span class="f-blue">系统免责声明</span>');
	});
	/**
	 * 我约触发事件
	 */
	$('#takeBtn').on(
			'click',
			function() {
				// 获取乘车人数
				var count = $('#myCount').val();
				var surplusSeat = $('#surplusSeat').val();
				if ((count == null || count == '' || count <= 0)|| !(parseInt(count)==count)) {
					$.alert("请您输入正确的乘车人数");
					return;
				}
				if (parseInt(count) > parseInt(surplusSeat)) {
					$.alert("抱歉,您输入乘车人数已大于当前可约人数");
					return;
				}
				// 未选择条约
				var $ck = $('#ck_rule:checked').val();
				if ($ck == 'on') {
					
				} else {
					$.alert("抱歉,您未同意遵守本系统条约");
					return;
				}
				$.confirm("您确定要约<span class='f-red'>"+count+"人</span>吗?", "确认?", function() {
					var openId = $('[name=openId]').val();
					var orderId = $('[name=orderId]').val();
					var version = $('[name=version]').val();
					$.ajax({
						type : "POST",
						url : _ctx + "/order/takeOrder?openId=" + openId
								+ "&orderId=" + orderId + "&count=" + count
								+ "&version=" + version,
						contentType : "application/json;charset=UTF-8",
						dataType : 'json',
						success : function(data) {
							if (data.code == 0) {
								$.toast("恭喜您,预约成功");
								// 跳转到我的约车单页面
								window.location.href = _ctx
										+ "/order/toMyTakeOrder?openId="
										+ openId;
							} else {
								$.alert(data.message, "提示");
							}
						},
						error : function(error) {
							// $.alert("网络异常");
							$.toast("网络异常", "forbidden");
						}
					});
				}, function() {
					// 取消操作
				});
			});
});