/**
 * 约车详细信息页面相关JS
 */
$(function() {
	/**
	 * 我约触发事件
	 */
	$('#takeBtn').on(
			'click',
			function() {
				// 获取乘车人数
				var count = $('[name=count]').val();
				if (count == null) {
					$.alert("请输入正确的乘车人数");
					return;
				}
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
							$.alert("预约成功！");
							// 跳转到我的约车单页面
							window.location.href = _ctx
									+ "/order/toMyTakeOrder?openId=" + openId;
						} else {
							$.alert(data.message, "提示");
						}
					},
					error : function(error) {
						$.alert(error);
					}
				});
			});
});