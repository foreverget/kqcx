/**
 * 编辑常用地址信息页面相关JS
 */
$(function() {
	// 添加
	$('#addAddrBtn').on(
			"click",
			function() {
				var start = $('[name=start]').val();
				if (!start) {
					$.alert("请输入起点信息");
					return;
				}
				var end = $('[name=end]').val();
				if (!end) {
					$.alert("请输入终点信息");
					return;
				}
				var obj = {};
				obj.start = start;
				obj.end = end;
				obj.openId = $('#openId').val();
				$.ajax({
						type : "POST",
						url : _ctx + "/addr/saveAddr",
						data : JSON.stringify(obj),
						contentType : "application/json;charset=UTF-8",
						dataType : 'json',
						success : function(data) {
							if (data.code == 0) {
								$.alert("添加成功！");
								window.location.href = _ctx
										+ "/addr/toUpdateAddr?openId="
										+ obj.openId;
							} else {
								$.alert("添加失败!", "提示");
							}
						},
						error : function(error) {
							$.alert(error);
						}
					});
			});
	/**
	 * 删除事件
	 */
	$('[id^=addr_id_]').on('click', function() {
	 	 var id = $(this).attr('data-id');
		 $.confirm("您确定要删除吗?", "确认删除?", function() {
				var openId = $('#openId').val();
				$.ajax({
					type : "POST",
					url : _ctx + "/addr/deleteAddr?openId="+openId+"&id="+id,
					contentType : "application/json;charset=UTF-8",
					dataType : 'json',
					success : function(data) {
						if (data.code == 0) {
							$.alert("删除成功！");
							window.location.href = _ctx+ "/addr/toUpdateAddr?openId="+ openId;
						} else {
							$.alert("删除失败!", "提示");
						}
					},
					error : function(error) {
						$.alert(error);
					}
				});
        }, function() {
        });
		
	});
});