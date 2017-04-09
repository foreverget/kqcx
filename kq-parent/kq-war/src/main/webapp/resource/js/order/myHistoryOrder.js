/**
 * 我的订单相关JS
 */
// 约车 页数
var page_yc = 1;
// 出车 页数
var page_cc = 1;

// 每页展示20个
var size = 20;
// 当前tab标识
var itemIndex = 0;
// 加载路径
var _action = '';

var divId = '';
$(function() {

	// dropload
	var dropload = $('.weui_panel').dropload({
		scrollArea : window,
		// 自动加载
		autoLoad : true,
		// 加载更多
		loadDownFn : function(me) {
			loadFn("down", me, itemIndex);
		},
		// 刷新
		loadUpFn : function(me) {
			loadFn("up", me, itemIndex);
		}
	});

	// 设置tab显示颜色
	$('#tabdiv').tab({
		defaultIndex : 0,
		activeClass : "tab-green"
	});

	// tab点击事件
	$('.weui_tab .weui_navbar_item').on(
			'click',
			function() {
				var $this = $(this);
				itemIndex = $this.index();
				// 其他隐藏
				$('.weui_panel_bd').eq(itemIndex).show().siblings(
						'.weui_panel_bd').hide();
				// 需要重置dropload组件
				dropload.resetload();
			});

});
/**
 * 绑定事件
 */
function initBindEvent() {
	//查看约车详情
	$('[id^=yc_list_]').unbind('click').bind(
			'click',
			function() {
				var openId = $("[name=openId]").val();
				var orderId = $(this).attr('data-id');
				window.location.href = _ctx
						+ "/order/toYcOrderDetail?orderId=" + orderId
						+ "&openId=1000";// + openId;
			});
	//查看出车详情
	$('[id^=cc_list_]').unbind('click').bind(
			'click',
			function() {
				var openId = $("[name=openId]").val();
				var orderId = $(this).attr('data-id');
				window.location.href = _ctx
						+ "/order/toCcOrderDetail?orderId=" + orderId
						+ "&openId=10001";// + openId;
			});
}
/**
 * 加载数据 loadType: down/up me
 * 
 */
function loadFn(loadType, me, itemIndex) {
	var openId = $("[name=openId]").val();
	var aId_="";
	// 判断divId和路径
	switch (itemIndex) {
	case 0:// 约车
		if (loadType == "up") {// 约车刷新
			page_yc = 1;
		}
		_action = '/order/getYcOrder?page=' + page_yc + '&size=' + size
				+ '&openId=1000';
		divId = '#yc';
		aId_= "yc_list_";
		break;
	case 1:// 出车
		if (loadType == "up") {// 出车刷新
			page_cc = 1;
		}
		_action = '/order/getCcOrder?page=' + page_cc + '&size=' + size
		+ '&openId=10001';
		divId = '#cc';
		aId_= "cc_list_";
		break;
	}
	;
	var result = '';
	// 发送请求获取更多数据
	$
			.ajax({
				type : 'GET',
				url : _ctx + _action,
				dataType : 'json',
				success : function(data) {
					// 获取结果集
					var arr = data.value;
					if (!!arr && arr.length > 0) {// 循环数据，拼装html
						for (var i = 0; i < arr.length; i++) {
							result += '<a id="'+aId_+
									+ arr[i].id
									+ '" data-id="'
									+ arr[i].id
									+ '"  class="weui_media_box weui_media_appmsg">'
									+ '<div class="weui_media_bd">'
									+ '<h4 class="weui_media_title list-line-margin-bottom">'
									+ getSmpFormatDateByLong(arr[i].createTime,
											true)
									+ '&nbsp;&nbsp;&nbsp;&nbsp;单价&nbsp;'
									+ arr[i].price
									+ '&nbsp;元</h4>'
									+ '<p class="weui_media_desc list-line-margin-bottom">起点:'
									+ arr[i].startAddr
									+ '</p>'
									+ '<p class="weui_media_desc list-line-margin-bottom">终点:'
									+ arr[i].endAddr + '</p>' + '</div></a>';
						}
						if (loadType == "down") {// 加载更多
							$(divId).append(result);
						} else {// 刷新
							$(divId).html(result);
						}
						// 绑定事件
						initBindEvent();
						// 每次数据加载完，必须重置
						// me.resetload();
						// 解锁
						me.unlock();
						me.noData(false);
					} else {// 如果没有数据
						// 锁定
						me.lock();
						// 无数据
						me.noData();
					}
					// 增加当前tab的页数
					switch (itemIndex) {
					case 0:// 约车
						if (loadType == "down") {// 约车加载更多
							page_yc++;
						} else {// 约车刷新
							page_yc = 0;
						}
						break;
					case 1:// 出车
						if (loadType == "down") {// 出车加载更多
							page_cc++;
						} else {// 出车刷新
							page_cc = 0;
						}
						break;
					}
					;
				},
				error : function(xhr, type) {
					$.toptips("网络异常", "warning");
					// 即使加载出错，也需要重置下 ## 如果重置会陷入无限循环
					// me.resetload();
				}
			});
}
