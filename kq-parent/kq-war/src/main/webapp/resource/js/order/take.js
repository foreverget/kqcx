/**
 * 我要约车相关JS
 */
var clickTabTakeCount = 0;
// 今天 页数
var page_td = 1;
// 明天 页数
var page_tm = 1;
// 后天 页数
var page_ht = 1;
// 每页展示5个
var size = 5;
var itemIndex = 0;
var down_action = '';
var up_action = '';
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
				if (clickTabTakeCount != 0) {
					// 需要重置dropload组件
					dropload.resetload();					
					loadFn("up", dropload, itemIndex);
				}
				clickTabTakeCount++;
			});
});
/**
 * 绑定事件
 */
function initBindEvent() {
	// 查看订单详情
	$('[id^=order_list_]').unbind('click').bind(
			'click',
			function() {
				var openId = $("[name=openId]").val();
				var orderId = $(this).attr('data-id');
				window.location.href = _ctx + "/order/toTakeOrderDetail?orderId="
						+ orderId + "&openId="+ openId;
			});
}
/**
 * 加载数据 loadType: down/up me
 * 
 */
function loadFn(loadType, me, itemIndex) {
	var openId = $("[name=openId]").val();
	// 判断divId和路径
	switch (itemIndex) {
	case 0:// 今天
		if (loadType == "up") {// 今天刷新
			page_td = 1;
		}
		_action = '/order/getTakeOrderList?type=1&page=' + page_td + '&size=' + size
				+ '&openId='+openId;
		divId = '#td';
		date_desc = '【今天】';

		break;
	case 1:// 明天
		if (loadType == "up") {// 明天刷新
			page_tm = 1;
		}
		_action = '/order/getTakeOrderList?type=2&page=' + page_tm + '&size=' + size
				+ '&openId='+openId;
		divId = '#tm';
		date_desc = '【明天】';
		break;
	case 2:// 后天
		if (loadType == "up") {// 后天刷新
			page_ht = 1;
		}
		_action = '/order/getTakeOrderList?type=3&page=' + page_ht + '&size=' + size
				+ '&openId='+openId;
		divId = '#ht';
		date_desc = '【后天】';
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
							var statusStr = "发布中";
							if ("1" == arr[i].status) {
								statusStr = "已出发";
							} else if ("2" == arr[i].status) {
								statusStr = "已送达";
							} else if ("3" == arr[i].status) {
								statusStr = "已取消";
							}
							result += '<a id="order_list_'
								+ arr[i].id
								+'" data-id="'
								+ arr[i].id
								+ '"  class="weui_media_box weui_media_appmsg">'
								+ '<div class="weui_media_bd">'
								+ '<h4 class="weui_media_title">'
								+ '始：'+arr[i].startAddr
								+ '</h4>'
								+ '<h4 class="weui_media_title">'
								+ '终：'+arr[i].endAddr
								+ '</h4>'
				                +'<div class="weui_cell_bd weui_cell_primary">'
			                    +'<p>出发时间：'+arr[i].goTime+'</p>'
			                    +'</div>'
				                +'<div class="weui_cell_bd weui_cell_primary">'
			                    +'<p>价格：<font class="font-blue">'+arr[i].price+'</font> 元/人</p>'
			                    +'</div>'
				                +'<div class="weui_cell_bd weui_cell_primary">'
			                    +'<p>空余：<font class="font-red">'+arr[i].surplusSeat+' </font>人</p>'
			                    +'</div>'
								+ '</div>'
								+ '</a>';
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
					case 0:// 今天
						if (loadType == "down") {// 今天加载更多
							page_td++;
						} else {// 今天刷新
							page_td = 0;
						}
						break;
					case 1:// 明天
						if (loadType == "down") {// 明天加载更多
							page_tm++;
						} else {// 明天刷新
							page_tm = 0;
						}
						break;
					case 2:// 后天
						if (loadType == "down") {// 后天加载更多
							page_ht++;
						} else {// 后天刷新
							page_ht = 0;
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
