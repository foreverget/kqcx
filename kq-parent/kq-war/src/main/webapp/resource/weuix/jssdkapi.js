/**
 * JSSDK
 * 
 */
$(function() {
	// 当前的网页请求地址
	var weixinUrl = location.href.split('#')[0];
	//alert(weixinUrl);
	$.ajax({
		type : "POST",
		url : _ctx + "/wx/jssdk?url="+weixinUrl,
		cache : false,
		//data : {url:weixinUrl},
		contentType : "application/json;charset=UTF-8",
		dataType : 'json',
		success : function(data) {
			//alert("code="+data.code);
			//alert("appid="+data.value.appId);
			//alert("timestamp="+data.value.timestamp);
			//alert("nonceStr="+data.value.nonceStr);
			//alert("signature="+data.value.signature);
			if (data.code == 0) {
				wx.config({
					debug : false, // 调试模式
					appId : data.value.appId,
					timestamp : data.value.timestamp,
					nonceStr : data.value.nonceStr,
					signature : data.value.signature,
					jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
							'onMenuShareAppMessage', 'onMenuShareQQ',
							'onMenuShareWeibo', 'hideMenuItems',
							'showMenuItems', 'hideAllNonBaseMenuItem',
							'showAllNonBaseMenuItem', 'translateVoice',
							'startRecord', 'stopRecord', 'onRecordEnd',
							'playVoice', 'pauseVoice', 'stopVoice',
							'uploadVoice', 'downloadVoice', 'chooseImage',
							'previewImage', 'uploadImage', 'downloadImage',
							'getNetworkType', 'openLocation', 'getLocation',
							'hideOptionMenu', 'showOptionMenu', 'closeWindow',
							'scanQRCode', 'chooseWXPay',
							'openProductSpecificView', 'addCard', 'chooseCard',
							'openCard' ]
				});

				wx.ready(function() {
					//$.alert("微信jssdk配置成功!");
					// 批量隐藏菜单项
					wx.hideMenuItems({
						menuList : [ 
						  'menuItem:copyUrl','menuItem:editTag','menuItem:delete',
						  'menuItem:originPage','menuItem:readMode','menuItem:openWithQQBrowser',
						  'menuItem:openWithSafari','menuItem:share:email'
						],
						success : function(res) {
							//$.alert('已隐藏“复制链接”按钮');
						},
						fail : function(res) {
							//$.alert(JSON.stringify(res));
						}
					});
				});
				wx.error(function(res) {
					//$.alert('微信JSSDK配置失败！');
				});
			} else {
				//$.alert("失败", "提示");
			}
		},
		error : function(error) {
			//$.alert(error);
		}
	});

});