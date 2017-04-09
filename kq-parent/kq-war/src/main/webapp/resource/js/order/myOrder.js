/**
 * 我的订单信息页面相关JS
 */
$(function() {
	// 查看我的约车单触发事件
	$('#my_take').on('click', function() {
		var openId = $("[name=openId]").val();
		window.location.href = _ctx + "/order/toMyTakeOrder?openId=" + openId;
	});
	// 查看我的历史订单
	$('#my_history').on('click', function() {
		var openId = $("[name=openId]").val();
		window.location.href = _ctx + "/order/toMyHistoryOrder?openId=" + openId;
	});
});