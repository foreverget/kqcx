/**
 * 我的订单相关JS
 */
$(function() {
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
	// dropload
	var dropload = $('.weui_panel').dropload({
		scrollArea : window,
		// 自动加载
		autoLoad : true,
		// 加载更多
		loadDownFn : function(me) {loadFn("down",me);},
		// 刷新
		loadUpFn : function(me) {loadFn("up",me);}
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
 * 加载数据
 * loadType: down/up
 * me
 * 
 */
function loadFn(loadType,me){
	//判断divId和路径
	switch (itemIndex) {
	case 0:// 约车
		if(loadType == "down"){//约车加载更多
			_action = 'http://ons.me/tools/dropload/json.php?page='
				+ page_yc + '&size=' + size;
			divId = '#yc';
		}else{//约车刷新
			_action = 'http://ximan.github.io/dropload/examples/json/update.json';
			divId = '#yc';
		}
		break;
	case 1:// 出车
		if(loadType == "down"){//出车加载更多
			_action = 'http://ons.me/tools/dropload/json.php?page='
				+ page_cc + '&size=' + size;
			divId = '#cc';
		}else{//出车刷新
			_action = 'http://ximan.github.io/dropload/examples/json/update.json';
			divId = '#cc';
		}
		break;
	};
	var result = '';
	// 发送请求获取更多数据
	$.ajax({
		type : 'GET',
		url : _action,
		dataType : 'json',
		success : function(data) {
			//获取结果集
			var arrLen = data.length;
			if (arrLen > 0) {//循环数据，拼装html
				for (var i = 0; i < arrLen; i++) {
					result += '<a href="cell4_.html" class="weui_media_box weui_media_appmsg">'
							+ '<div class="weui_media_hd weui-updown">'
							+ '<img class="weui_media_appmsg_thumb lazyload" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUAAAAFACAYAAADNkKWqAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8wNS8xNrqrthwAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzbovLKMAAAEMElEQVR4nO3UQQEAEADAQPRvqIESxPDYXYK9Nvc+dwAErd8BAL8YIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBAlgECWQYIZBkgkGWAQJYBAlkGCGQZIJBlgECWAQJZBghkGSCQZYBA1gPYJgYfB4WzDQAAAABJRU5ErkJggg==" alt="" data-img="'
							+ data[i].pic
							+ '">'
							+ '</div>'
							+ '<div class="weui_media_bd">'
							+ '<h4 class="weui_media_title">'
							+ data[i].id
							+ ' '
							+ data[i].title
							+ '</h4>'
							+ '<p class="weui_media_desc">'
							+ data[i].date
							+ '</p>'
							+ '</div>'
							+ '</a>';
				}
				if(loadType == "down"){//加载更多
					$(divId).append(result);
				}else{//刷新
					$(divId).html(result);
				}
				// 每次数据加载完，必须重置
				me.resetload();
				// 解锁
				me.unlock();
				me.noData(false);
			} else {// 如果没有数据
				// 锁定
				me.lock();
				// 无数据
				me.noData();
			}
			//增加当前tab的页数
			switch (itemIndex) {
				case 0:// 约车
					if(loadType == "down"){//约车加载更多
						page_yc++;
					}else{//约车刷新
						page_yc=0;
					}
					break;
				case 1:// 出车
					if(loadType == "down"){//出车加载更多
						page_cc++;
					}else{//出车刷新
						page_cc=0;
					}
					break;
			};
		},
		error : function(xhr, type) {
			$.toptips("网络异常", "warning");
			// 即使加载出错，也需要重置下
			me.resetload();
		}
	});

}
