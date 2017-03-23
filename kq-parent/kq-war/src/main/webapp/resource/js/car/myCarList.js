/**
 * 我的车辆信息页面相关JS
 */
$(function() {
	// 去添加车辆页面
	$('#toAddCar').on(
			"click",
			function() {
				window.location.href = _ctx + "/car/toAddCar?openId="
						+ $('#openId').val();
			});
	// 去添加车辆页面
	$('[id^=up_carInfo_]').on(
			"click",
			function() {
				var id = $(this).attr("data-id");
				window.location.href = _ctx + "/car/toUpdateCar?openId="
						+ $('#openId').val() + "&id=" + id;
			});
});