/**
 * 我要历史订单相关JS
 */
$(function() {
	var openId = $("#openId").val();
	// 每页展示2个
	var size = 20;
	// 我的约车单
	var page_0 = 1;
	// 我的出车单
	var page_1 = 1;
	require(
			[ 'zepto', 'iscroll', 'listloading' ],
			function() {
				var Listloading = require('listloading');
				var hei = $(window).height();

				// 我的约车单
				var createHtml0 = function(loadType,flg) {
					// 上拉加载
					if(loadType == "up"){
						if(flg){
							return;
						}
						page_0++;
						
					}else{
						page_0 = 1;
					}
					var __html = '';
					var _action = '/order/getYcOrder?type=0&page=' + page_0 + '&size=' + size + '&openId='+openId;
					// 发送请求获取更多数据
					$.ajax({
						type : 'GET',
						async: false,
						url : _ctx + _action,
						cache:false,
						dataType : 'json',
						success : function(data) {					
							// 获取结果集
							var arr = data.value;
							//alert(arr.length);
							if (!!arr && arr.length > 0) {// 循环数据，拼装html
								for (var i = 0; i < arr.length; i++) {
									var statusStr = "发布中";
									var liclass="";
									if ("1" == arr[i].status) {
										statusStr = "已约满";
										liclass = "over";
									} else if ("2" == arr[i].status) {
										statusStr = "已送达";
										liclass = "over";
									} else if ("3" == arr[i].status) {
										statusStr = "已取消";
										liclass = "over";
									}
									__html += '<li class='+liclass+' id=yc_list_'+arr[i].id+' open-id='+arr[i].openId+' order-id='+arr[i].id+'>'
											+'<span class="icon">约</span>'
											+ '<p class="r"><font class="font-red">'+arr[i].price+'</font>元/位</p>'
											+ '<p class="title">'+arr[i].goTime+'</p>'
											+'<p>从：'+arr[i].startAddr+'</p>'
											+'<p>到：'+arr[i].endAddr+'</p>'
											+'<p id=orderstatus_'+arr[i].id+' class="r">'+statusStr+'</p>'
											+'<p class="r">车牌号：'+arr[i].plates+'&nbsp;&nbsp;&nbsp;&nbsp;</p>'
											+'</li>';
								}
								//alert(__html);
								// 绑定事件
								initBindEvent();
							// 如果没有数据
							} else {
								page_0 = 1;
							}
						},
						error : function(xhr, type) {
							$.toptips("网络或服务异常", "warning");
						}
					});
					return __html;
				}

				// 我的出车单
				var createHtml1 = function(loadType,flg) {
					// 上拉加载
					if(loadType == "up"){
						if(flg){
							return;
						}
						page_1++;
						
					}else{
						page_1 = 1;
					}
					var __html = '';
					var _action = '/order/getCcOrder?type=0&page=' + page_1 + '&size=' + size + '&openId='+openId;
					// 发送请求获取更多数据
					$.ajax({
						type : 'GET',
						async: false,
						url : _ctx + _action,
						cache:false,
						dataType : 'json',
						success : function(data) {					
							// 获取结果集
							var arr = data.value;
							//alert(arr.length);
							if (!!arr && arr.length > 0) {// 循环数据，拼装html
								for (var i = 0; i < arr.length; i++) {
									var statusStr = "发布中";
									var liclass="";
									if ("1" == arr[i].status) {
										statusStr = "已约满";
										liclass = "over";
									} else if ("2" == arr[i].status) {
										statusStr = "已送达";
										liclass = "over";
									} else if ("3" == arr[i].status) {
										statusStr = "已取消";
										liclass = "over";
									}
									__html += '<li class='+liclass+' id=cc_list_'+arr[i].id+' open-id='+arr[i].openId+' order-id='+arr[i].id+'>'
											+'<span class="icon">出</span>'
											+ '<p class="r"><font class="font-red">'+arr[i].price+'</font>元/位</p>'
											+ '<p class="title">'+arr[i].goTime+'</p>'
											+'<p>从：'+arr[i].startAddr+'</p>'
											+'<p>到：'+arr[i].endAddr+'</p>'
											+'<p id=orderstatus_'+arr[i].id+' class="r">'+statusStr+'</p>'
											+'<p class="r">车牌号：'+arr[i].plates+'&nbsp;&nbsp;&nbsp;&nbsp;</p>'
											+'</li>';
								}
								//alert(__html);
								// 绑定事件
								initBindEvent();
							// 如果没有数据
							} else {
								page_1 = 1;
							}
						},
						error : function(xhr, type) {
							$.toptips("网络或服务异常", "warning");
						}
					});
					return __html;
				}

				// tab0
				var flg_0 = false;
				var listloading = new Listloading('#listloading0', {
					disableTime : true, // 是否需要显示时间
					pullUpAction : function(cb) { // 上拉加载更多
						var __html = createHtml0('up',flg_0);
						if (__html=='') {
							flg_0 = true;
						} else {
							$('#order-list-0').append(__html);
						}
						// 数据加载完毕需要返回 end为true则为全部数据加载完毕
						// alert(flg_0);
						cb(flg_0);
					},
					pullDownAction : function(cb, flg) { // 下拉刷新
						// true则为默认加载 false为下拉刷新
						if (flg) {
							console.log('默认加载');
						}
						var __html = createHtml0('down',flg);
						//alert(__html);
						if(__html!=''){
							$('#order-list-0').html(__html);
						}else{
							$('#order-list-0').html("<div class='weui_msg_box'><p><i class='icon icon-40 f20 f-green'></i>暂无数据</p></div>");
						}
						// 执行完执行方法之后必须执行回调
						// 回调的作用是通知默认加载已经全部执行完毕，程序需要去创建iscroll或者做下拉刷新动作
						cb();
					},
					Realtimetxt : '官人不要，请放开我！',
					loaderendtxt : '我是有底线的',
					// iscroll的API
					iscrollOptions : {
						scrollbars : false
					// 显示iscroll滚动条
					}
				});
				// tab1
				var flg_1 = false;
				var Tab1Listloading = function() {
					new Listloading('#listloading1', {
						disableTime : true, // 是否需要显示时间
						pullUpAction : function(cb) { // 上拉加载更多
							var __html = createHtml1('up',flg_1);
							if (__html=='') {
								flg_1 = true;
							} else {
								$('#order-list-1').append(__html);
							}
							// 数据加载完毕需要返回 end为true则为全部数据加载完毕
							// alert(flg_1);
							cb(flg_1);

						},
						pullDownAction : function(cb, flg) { // 下拉刷新
							// true则为默认加载 false为下拉刷新
							if (flg) {
								console.log('默认加载');
							}
							var __html = createHtml1('down',flg);
							//alert(__html);
							if(__html!=''){
								$('#order-list-1').html(__html);
							}else{
								$('#order-list-1').html("<div class='weui_msg_box'><p><i class='icon icon-40 f20 f-green'></i>暂无数据</p></div>");
							}
							// 执行完执行方法之后必须执行回调
							// 回调的作用是通知默认加载已经全部执行完毕，程序需要去创建iscroll或者做下拉刷新动作
							cb();
						},
						Realtimetxt : '官人不要，请放开我！',
						loaderendtxt : '我是有底线的',
						// iscroll的API
						iscrollOptions : {
							scrollbars : false
						// 显示iscroll滚动条
						}
					});
				}

				// 只有第一次load才执行
				var tabFirstLoad1 = true;
				// 切换
				$('#listloadingtab>span').click(
						function() {
							var index = $(this).index();
							$('#listloadingtab>span').eq(index).addClass('active').siblings().removeClass('active');
							$('.listloading-tab').hide();
							$('.listloading-tab').eq(index).show();
							// 第二个tab
							if (index === 1 && tabFirstLoad1) {
								tabFirstLoad1 = false;
								Tab1Listloading();
							}
						});
			});
	require.config({
		paths : {
			"zepto" : _ctx+"/resource/listloading/src/jslib/zepto.min",
			"iscroll" : _ctx+"/resource/listloading/src/jslib/iscroll",
			"listloading" :_ctx+ "/resource/listloading/src/jslib/listloading"
		}
	});

});
/**
 * 绑定事件
 */
function initBindEvent() {
	// 查看约车单详情
	$('[id^=yc_list_]').live('click',function(){
		var openId = $(this).attr('open-id');
		var orderId = $(this).attr('order-id');
//		alert(openId);
//		alert(orderId);
//		return;
		window.location.href = _ctx
				+ "/order/toYcOrderDetail?orderId=" + orderId
				+ "&openId=" + openId;
	
	});

	// 查看出车单详情
	$('[id^=cc_list_]').live('click',function(){
		var openId = $(this).attr('open-id');
		var orderId = $(this).attr('order-id');
//		alert(openId);
//		alert(orderId);
//		return;
		window.location.href = _ctx
				+ "/order/toCcOrderDetail?orderId=" + orderId
				+ "&openId=" + openId;
	
	});
}
