<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="css/formsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>

</head>
<body>
	<div class="elegant-aero">
		<form action="editPwd.action" method="post" class="STYLE-NAME">
			<h1>修改密码</h1>
			<label><span>旧密码 :</span><input id="password" type="password"
				name="password" placeholder="密码" /></label>
			<label><span>新密码 :</span><input id="newpassword" type="password"
				name="newpassword" placeholder="密码" />
			</label><label><span>重复密码 :</span><input id="repassword" type="password"
				name="repassword" placeholder="重复密码" />
			</label><label><span>&nbsp;</span><input type="button"
				class="button" value="提交" onclick="checkPwd()"/>
			</label>
		</form>
	</div>
<script type="text/javascript">
$(function(){
	
});

function checkPwd(){
	var newpassword = $("[name='newpassword']").val();
	var repassword = $("[name='repassword']").val();
	var password = $("[name='password']").val();
	if(password == null ||password == ''){
		alert("旧密码不能为空");
		return;
	}
	if(newpassword == null ||newpassword == ''){
		alert("新密码不能为空");
		return;
	}
	if(newpassword != repassword){
		alert("两次输入的密码不一致");
		return;
	}
	$.ajax({
		url:"CPCompanyAction.do?method=editPWD",
		type:"post",
		data:{
			newpassword : newpassword,
			password : password
		},
		success : function(resp){
			var data = eval('('+resp+')');
			if(data.code==202){
				alert('操作成功，用户信息已修改。');
				window.parent.location.href = "CPCompanyAction.do?method=zhuxiao";
			} else {
				alert('操作失败。');
			}
		},
		error : function(resp){
			var data = eval('('+resp+')');
			alert(data.msg);
		}
	});
}

</script>
</body>
</html>