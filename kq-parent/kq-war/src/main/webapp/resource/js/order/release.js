/**
 * 车主发布相关JS
 */
$(function() {

	$("#goTime").datetimePicker({
		title : "<span class='f-red'>请选择出发时间</span>"
	});

	var max = $('#count_max').text();

	$('#memo').on('input', function() {
		var text = $(this).val();
		var len = text.length;
		$('#count').text(len);
		if (len > max) {
			$(this).closest('.weui_cell').addClass('weui_cell_warn');
		} else {
			$(this).closest('.weui_cell').removeClass('weui_cell_warn');
		}
	});

	// 绑定事件
//	$("#rulelink").on('click',function(){
//		$.alert('1.文明出车 \n;2.不准迟到','<span class="f-blue">系统免责声明</span>');
//	});
	
	var $form = $("#form");
	$form.form();
	// 提交
	$("#formSubmitBtn").on("click",function() {
		// 未选择条约
		var $ck = $('#ck_rule:checked').val();
		if ($ck == 'on') {
			
		} else {
			$.alert("抱歉,您未同意遵守本系统条约");
			return;
		}
		$form.validate(function(error) {
			if (!error) {$.confirm("您确定要发布吗?","",function() {
									// 校验成功后提交数据
									var obj = getFormJson($form);
									$.ajax({
										type : "POST",
										url : _ctx + "/order/release",
										data : JSON.stringify(obj),
										contentType : "application/json;charset=UTF-8",
										dataType : 'json',
										success : function(data) {
											if (data.code == 0) {
												$.toast("发布成功");
												// 跳转到我的订单页面
												window.location.href = _ctx
														+ "/order/toMyReleaseOrder?openId="
														+ $('[name=openId]').val();
											} else {
												$.alert(data.message, "提示");
											}
										},
										error : function(error) {
											$.toptips("网络或服务异常","warning");
										}
									});
								}, function() {
									$.toast("取消发布","cancel");
								});
					}
				});
	});
		
	initSelect();
});
/**
 * 初始化下拉框
 */
function initSelect() {
	var openId = $('[name=openId]').val();
	initSelectAddr(openId);
	initSelectCar(openId);
}
/**
 * 初始化选择地址下拉框
 */
function initSelectAddr(openId) {
	$.ajax({
		type : 'GET',
		url : _ctx + "/addr/selectAddrList?openId=" + openId,
		dataType : 'json',
		success : function(data) {
			// 获取结果集
			var addrList = data.value;
			var arr = new Array();

			if (!!addrList && addrList.length > 0) {
				for ( var i in addrList) {
					var obj = new Object();
					obj.title = addrList[i].start + "--->" + addrList[i].end;
					obj.value = addrList[i].id;
					arr.push(obj);
				}
			}
			// 绑定事件
			$("#selectaddr").select({
				title : "<span class='f-red'>请选择常用地址</span>",
				items : arr,
				onChange : function(c) {
					if (c.titles.length > 0) {
						var arr = c.titles.split("--->");
						$('#startAddr').val(arr[0]);
						$('#endAddr').val(arr[1]);
					}

				}
			});
		},
		error : function(xhr, type) {
			$.toptips("网络或服务异常", "warning");
		}
	});
}

/**
 * 初始化选择车辆下拉框
 */
function initSelectCar(openId) {
	$.ajax({
		type : 'GET',
		url : _ctx + "/car/selectCarList?openId=" + openId,
		dataType : 'json',
		success : function(data) {
			// 获取结果集
			var carList = data.value;
			var arr = new Array();
			if (!!carList && carList.length > 0) {
				for ( var i in carList) {
					var obj = new Object();
					obj.title = carList[i].plates;
					obj.value = carList[i].id;
					arr.push(obj);
				}
			}
			// 绑定事件
			$("#selectcar").select({
				title : "<span class='f-red'>请选择车辆</span>",
				items : arr,
				onChange : function(c) {
					if (c.titles.length > 0) {
						$('#plates').val(c.titles);
					}
				}
			});
		},
		error : function(xhr, type) {
			$.toptips("网络或服务异常", "warning");
		}
	});
}