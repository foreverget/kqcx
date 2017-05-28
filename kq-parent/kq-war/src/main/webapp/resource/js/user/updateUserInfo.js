/**
 * 编辑用户信息页面相关JS
 */
$(function() {
	//根据key控制页面显示项
	if(key == "name"){
		$('#name_cell').show();
	}else if(key == "mobile"){
		$('#mobile_cell').show();
	}else if(key == "email"){
		$('#email_cell').show();
	}else if(key == "addr"){
		$('#addr_cell').show();
	}else if(key == "gender"){
		$('#gender_cell').show();
	}
	//保存
	$('#saveBtn').on("click",function(){
		var value = $('[name='+key+"]").val();
		if(!value){
			$.alert("请您输入信息");
			return;
		}
		var obj = {};
		obj.value = value;
		obj.key = key;
		obj.openId = $('#openId').val();
		$.ajax({
			type : "POST",
			url : _ctx + "/user/saveUser",
			data : JSON.stringify(obj),
			contentType : "application/json;charset=UTF-8",
            dataType:'json',
			success : function(data) {
				 if(data.code == 0){
					 window.location.href=_ctx + "/user/toUserInfo?openId="+obj.openId;
				 }else{
					 $.alert("修改失败", "提示");
				 }
			},
			error : function(error) {
				$.alert(error);
			}
		});
	});
});