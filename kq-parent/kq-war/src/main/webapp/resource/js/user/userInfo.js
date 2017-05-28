/**
 * 用户信息页面相关JS
 */
$(function() {
	//基本信息触发事件
	$('[id$=_ce]').on("click",function(){
		var openId = $("#openId").val();
		var key=$(this).children(".weui_cell_ft").attr("data-name");
		var value = $(this).children(".weui_cell_ft").text();
		window.location.href=_ctx + "/user/toUpdateUserInfo?openId="+openId+"&key="+key+'&value='+value;
	});
	//编辑常用地址触发事件
	$('#up_addr_btn').on('click',function(){
		var openId = $("#openId").val();
		window.location.href=_ctx + "/addr/toUpdateAddr?openId="+openId;
	});
	//编辑车辆信息触发事件
	$('#up_car_btn').on('click',function(){
		var openId = $("#openId").val();
		window.location.href=_ctx + "/car/toMyCarList?openId="+openId;
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