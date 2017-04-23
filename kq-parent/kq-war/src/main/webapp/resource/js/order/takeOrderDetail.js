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
				if (!count) {
					$.alert("请输入正确的乘车人数");
					return;
				}
				var openId = $('[name=openId]').val();
				var orderId = $('[name=orderId]').val();
				$.ajax({
					type : "POST",
					url : _ctx + "/order/takeOrder?openId=" + openId
							+ "&orderId=" + orderId,
					contentType : "application/json;charset=UTF-8",
					dataType : 'json',
					success : function(data) {
						if (data.code == 0) {
							$.toast("发车喽！");
							location.reload();
						} else {
							$.alert("取消订单失败!", "提示");
						}
					},
					error : function(error) {
						$.alert(error);
					}
				});
			});
});