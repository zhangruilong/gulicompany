<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="css/formsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../sysjs/png.js"></script>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	var message = '<%=message %>'
	if(message != null && message != '' && message != 'null'){
		alert(message);
		if(message == '密码已修改'){
			window.parent.location.href = "login.jsp";
		}
	}
});

function checkPwd(){
	var newpassword = $("[name='newpassword']").val();
	var repassword = $("[name='repassword']").val();
	var password = $("[name='password']").val();
	if(password == null ||　password == ''){
		alert("旧密码不能为空");
		return;
	}
	if(newpassword == null ||　newpassword == ''){
		alert("新密码不能为空");
		return;
	}
	if(newpassword != repassword){
		alert("两次输入的密码不一致");
		return;
	}
	document.forms[0].submit();
}

</script>
</head>
<body>
	<div class="elegant-aero">
		<form action="editPwd.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="loginname" value="${sessionScope.company.loginname }">
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
</body>
</html>