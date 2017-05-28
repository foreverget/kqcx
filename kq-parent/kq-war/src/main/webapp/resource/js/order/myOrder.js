/**
 * 我的订单信息页面相关JS
 */
$(function() {
	// 查看我的约车单[3天内]触发事件
	$('#my_take').on('click', function() {
		var openId = $("[name=openId]").val();
		window.location.href = _ctx + "/order/toMyTakeOrder?openId=" + openId;
	});
	// 查看我的出车单[3天内]触发事件
	$('#my_release').on('click', function() {
		var openId = $("[name=openId]").val();
		window.location.href = _ctx + "/order/toMyReleaseOrder?openId=" + openId;
	});
	// 查看我的历史订单
	$('#my_history').on('click', function() {
		var openId = $("[name=openId]").val();
		window.location.href = _ctx + "/order/toMyHistoryOrder?openId=" + openId;
	});

	$('#slidediv').swipeSlide(
			{
				autoSwipe : true,// 自动切换默认是
				speed : 3000,// 速度默认4000
				continuousScroll : true,// 默认否
				transitionType : 'cubic-bezier(0.22, 0.69, 0.72, 0.88)',// 过渡动画linear/ease/ease-in/ease-out/ease-in-out/cubic-bezier
				lazyLoad : true,// 懒加载默认否
				firstCallback : function(i, sum, me) {
					me.find('.dot').children().first().addClass('cur');
				},
				callback : function(i, sum, me) {
					me.find('.dot').children().eq(i).addClass('cur').siblings()
							.removeClass('cur');
				}
			});
});