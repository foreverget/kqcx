/**
 * 用户信息页面相关JS
 */
$(function() {
	//基本信息触发事件
	$('[id$=_ce]').on("click",function(){
		var openId = $("#openId").val();
		var key=$(this).attr("data-name");
		var value = $(this).text();
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
});