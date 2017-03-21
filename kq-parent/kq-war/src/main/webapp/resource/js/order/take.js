/**
 * 乘车相关JS
 */
$(function() {
	// 今天 页数 
	var page_td = 1;
	// 明天 页数 
	var page_tm = 1;
	// 后天 页数 
	var page_ht = 1;
	// 全部 页数 
	var page_qb = 1;
	// 每页展示5个
	var size = 5;
	var itemIndex = 0;
	var down_action = '';
	var up_action = '';
	var divId ='';
    // dropload
    var dropload = $('.weui_panel').dropload({
		scrollArea : window,
		// 自动加载
		autoLoad : true,
		// 加载更多
		loadDownFn : function(me) {
			// alert(itemIndex);
			switch (itemIndex) {
				case 0:
					down_action = _ctx+'/order/getOrderList?page=' + page_td + '&size=' + size;
					divId = '#td';
					break;
				case 1:
					down_action = 'http://ons.me/tools/dropload/json.php?page=' + page_tm + '&size=' + size;
					divId = '#tm';
					break;
				case 2:
					down_action = 'http://ons.me/tools/dropload/json.php?page=' + page_ht + '&size=' + size;
					divId = '#ht';
					break;
				case 3:
					down_action = 'http://ons.me/tools/dropload/json.php?page=' + page_qb + '&size=' + size;
					divId = '#qb';
					break;
				default:
					;
			};
			var result = '';
			$.ajax({
				type : 'GET',
				url : down_action,
				dataType : 'json',
				success : function(data) {
					//alert(JSON.stringify(data.value));
					var date_desc;
					switch (itemIndex) {
						case 0:
							date_desc='【今天】';
							page_td++;
							break;
						case 1:
							date_desc='【明天】';
							page_tm++;
							break;
						case 2:
							date_desc='【后天】';
							page_ht++;
							break;
						case 3:
							date_desc='';
							page_qb++;
							break;
						default:
							;
					};
					var arrLen = data.value.length;
					//alert('arrLen='+arrLen);
					if (arrLen > 0) {
						for (var i = 0; i < arrLen; i++) {
							result += '<a href="#" class="weui_media_box weui_media_appmsg">'
									+ '<div class="weui_media_hd weui-updown">'
									+ '<img class="weui_media_appmsg_thumb lazyload" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUAAAAFACAYAAADNkKWqAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8wNS8xNrqrthwAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzbovLKMAAAEMElEQVR4nO3UQQEAEADAQPRvqIESxPDYXYK9Nvc+dwAErd8BAL8YIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBA1gPYJgYfB4WzDQAAAABJRU5ErkJggg==" alt="center" data-img="'+_ctx+'/resource/images/list_bg_img.png">'
									+ '</div>'
									+ '<div class="weui_media_bd">'
									+ '<h4 class="weui_media_title">'
									+ '始：'+data.value[i].startAddr
									+ '</h4>'
									+ '<h4 class="weui_media_title">'
									+ '终：'+data.value[i].endAddr
									+ '</h4>'
					                +'<div class="weui_cell_bd weui_cell_primary">'
				                    +'<p>出发时间：'+data.value[i].goTime+'</p>'
				                    +'</div>'
					                +'<div class="weui_cell_bd weui_cell_primary">'
				                    +'<p>单价：'+data.value[i].price+' 元/位</p>'
				                    +'</div>'
					                +'<div class="weui_cell_bd weui_cell_primary">'
				                    +'<p>空余：'+data.value[i].reqNum+' 座位</p>'
				                    +'</div>'
									+ '</div>'
									+ '</a>';
						}
						// 如果没有数据
					} else {
						// 锁定
						me.lock();
						// 无数据
						me.noData();
					}
					// 为了测试，延迟1秒加载
					setTimeout(function() {
						$(divId).append(result);
						var lazyloadImg = new LazyloadImg({
							el : '.weui-updown [data-img]', //匹配元素
							top : 50, //元素在顶部伸出长度触发加载机制
							right : 50, //元素在右边伸出长度触发加载机制
							bottom : 50, //元素在底部伸出长度触发加载机制
							left : 50, //元素在左边伸出长度触发加载机制
							qriginal : false, // true，自动将图片剪切成默认图片的宽高；false显示图片真实宽高
							load : function(el) {
								el.style.cssText += '-webkit-animation: fadeIn 01s ease 0.2s 1 both;animation: fadeIn 1s ease 0.2s 1 both;';
							},
							error : function(el) {

							}
						});
						// 每次数据加载完，必须重置
						me.resetload();
					}, 1000);
				},
				error : function(xhr, type) {
					$.toptips("请求异常","warning");
					// 即使加载出错，也需要重置下
					me.resetload();
					return;
				}
			});
		},
		// 刷新
		loadUpFn : function(me) {
			var date_desc;
			alert(itemIndex);
			switch (itemIndex) {
				case 0:
					date_desc='【今天】';
					up_action =  _ctx+'/order/getOrderList';
					divId = '#td';
					break;
				case 1:
					date_desc='【明天】';
					up_action = 'http://ximan.github.io/dropload/examples/json/update.json';
					divId = '#tm';
					break;
				case 2:
					date_desc='【后天】';
					up_action = 'http://ximan.github.io/dropload/examples/json/update.json';
					divId = '#ht';
					break;
				case 3:
					date_desc='';
					up_action = 'http://ximan.github.io/dropload/examples/json/update.json';
					divId = '#qb';
					break;
				default:
					;
			};
			$.ajax({
				type : 'GET',
				url : up_action,
				dataType : 'json',
				success : function(data) {
					var result = '';
					for (var i = 0; i < data.lists.length; i++) {
						result += '<a href="cell4_.html" class="weui_media_box weui_media_appmsg">'
								+ '<div class="weui_media_hd weui-updown">'
								+ '<img class="weui_media_appmsg_thumb lazyload" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUAAAAFACAYAAADNkKWqAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8wNS8xNrqrthwAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzbovLKMAAAEMElEQVR4nO3UQQEAEADAQPRvqIESxPDYXYK9Nvc+dwAErd8BAL8YIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBA1gPYJgYfB4WzDQAAAABJRU5ErkJggg==" alt="" data-img="'+data.lists[i].pic+'">'
								+ '</div>'
								+ '<div class="weui_media_bd">'
								+ '<h4 class="weui_media_title">'
								+ i
								+ ' '
								+ data.lists[i].title
								+ '</h4>'
								+ '<p class="weui_media_desc">'
								+ data.lists[i].date
								+ '</p>'
								+ '</div>'
								+ '</a>';
					}
					// 为了测试，延迟1秒加载
					setTimeout(function() {
								$(divId).html(result);
								var lazyloadImg = new LazyloadImg({
									el : '.weui-updown [data-img]', //匹配元素
									top : 50, //元素在顶部伸出长度触发加载机制
									right : 50, //元素在右边伸出长度触发加载机制
									bottom : 50, //元素在底部伸出长度触发加载机制
									left : 50, //元素在左边伸出长度触发加载机制
									qriginal : false, // true，自动将图片剪切成默认图片的宽高；false显示图片真实宽高
									load : function(el) {
										el.style.cssText += '-webkit-animation: fadeIn 01s ease 0.2s 1 both;animation: fadeIn 1s ease 0.2s 1 both;';
									},
									error : function(el) {
	
									}
								});
								// 每次数据加载完，必须重置
								me.resetload();
								// 重置索引值，重新拼接数据
								switch (itemIndex) {
									case 0:
										page_td = 0;
										break;
									case 1:
										page_tm = 0;
										break;
									case 2:
										page_ht = 0;
										break;
									case 3:
										page_qb = 0;
										break;
									default:
										;
								};
								// 解锁
								me.unlock();
								me.noData(false);
							}, 1000);
				},
				error : function(xhr, type) {
					$.toptips("网络异常","warning");
					// 即使加载出错，也需要重置下
					me.resetload();
				}
			});
		}
		
    });
    
    // 设置tab显示颜色
	$('#tabdiv').tab({
		defaultIndex : 0,
		activeClass : "tab-green"
	});
	
	// tab点击事件
	$('.weui_tab .weui_navbar_item').on('click', function() {
		var $this = $(this);
		itemIndex = $this.index();
		// 其他隐藏
		$('.weui_panel_bd').eq(itemIndex).show().siblings('.weui_panel_bd').hide();
		// 需要重置dropload组件
		dropload.resetload();
	});

});
