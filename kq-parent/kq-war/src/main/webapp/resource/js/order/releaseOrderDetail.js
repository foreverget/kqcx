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
				var openId = $('[name=openId]').val();
				var orderId = $('[name=orderId]').val();
				if(!($('#ppp').attr('id')==undefined)){
					$.alert("已有乘客约车,请电话通知乘客");
					return;
				}
				$.confirm("您确定要取消订单吗?", "确认?", function() {
					$.ajax({
						type : "POST",
						url : _ctx + "/order/channelOrder?openId=" + openId
								+ "&orderId=" + orderId,
						contentType : "application/json;charset=UTF-8",
						dataType : 'json',
						success : function(data) {
							if (data.code == 0) {
								$.toast("取消成功");
								window.location.href = _ctx
										+ "/order/toMyReleaseOrder?openId="
										+ openId;

							} else {
								$.alert("取消失败!", "提示");
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
		var orderId = $('[name=orderId]').val();
		$.confirm("您确定要停止接单,现在出发吗?", "确认?", function() {
			$.ajax({
				type : "POST",
				url : _ctx + "/order/subReleaseOrder?openId=" + openId
						+ "&orderId=" + orderId,
				contentType : "application/json;charset=UTF-8",
				dataType : 'json',
				success : function(data) {
					if (data.code == 0) {
						$.toast("起飞喽...");
						location.reload(); 					
					} else {
						$.alert("取消失败", "提示");
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
	 * 移除触发事件
	 */
	$('[id^=tr_id_]').on('click', function() {
		var openId =  $('[name=openId]').val();
		var orderId = $('[name=orderId]').val();
		//获取被踢的openId
		var opId=$(this).attr('data-openId');
		var pname=$(this).attr('data-name');
		var pcount=$(this).attr('data-value');
		$.confirm("移除将释放<span class='f-red'>"+pcount+"</span>座位给其他乘客约车，您确定要移除乘客<span class='f-red'>"+pname+"</span>吗?", "确认?", function() {
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
						$.alert("移除失败", "提示");
					}
				},
				error : function(error) {
					$.alert(error);
				}
			});
		}, function() {
			
		});
	});
});