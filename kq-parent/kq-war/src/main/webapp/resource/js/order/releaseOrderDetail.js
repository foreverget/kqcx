/**
 * 出车单详细信息页面相关JS
 */
$(function() {
	/**
	 * 取消订单触发事件
	 */
	$('#channelBtn').on(
			'click',
			function() {
				var openId =  $('[name=openId]').val();
				openId = 10001
				var orderId = $('[name=orderId]').val();
				$.confirm("您确定要取消订单吗?", "确认取消?", function() {
					$.ajax({
						type : "POST",
						url : _ctx + "/order/channelOrder?openId=" + openId
								+ "&orderId=" + orderId,
						contentType : "application/json;charset=UTF-8",
						dataType : 'json',
						success : function(data) {
							if (data.code == 0) {
								window.location.href = _ctx
										+ "/order/toMyReleaseOrder?openId="
										+ openId;

							} else {
								$.alert("取消订单失败!", "提示");
							}
						},
						error : function(error) {
							$.alert(error);
						}
					});
				}, function() {
				});
			});
	/**
	 * 客满发车触发事件
	 */
	$('#okBtn').on('click', function() {
		var openId =  $('[name=openId]').val();
		openId = 10001
		var orderId = $('[name=orderId]').val();
		$.ajax({
			type : "POST",
			url : _ctx + "/order/subReleaseOrder?openId=" + openId
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

	/**
	 * 移除触发事件
	 */
	$('[id^=tr_id_]').on('click', function() {
		var openId =  $('[name=openId]').val();
		openId = 10001
		var orderId = $('[name=orderId]').val();
		//获取被踢的openId
		var opId=$(this).attr('data-openId')
		$.ajax({
			type : "POST",
			url : _ctx + "/order/removePassenger?openId=" + openId
					+ "&orderId=" + orderId+"&opId="+opId,
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			success : function(data) {
				if (data.code == 0) {
					$.toast("移除成功");
					location.reload(); 					
				} else {
					$.alert("移除乘客失败!", "提示");
				}
			},
			error : function(error) {
				$.alert(error);
			}
		});
	});
});