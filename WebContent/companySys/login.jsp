<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
		if(null != session.getAttribute("company")){
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>用户登录</title>
<link href="../sysstyle/style.css" rel="stylesheet" type="text/css" />
<link href="../ExtJS/resources/css/ext-all.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../ExtJS/ext-all.js"></script>
<script type="text/javascript" src="../ExtJS/ext-lang-zh_CN.js" charset="UTF-8"></script>
<script type=text/javascript>
if (top != window) top.location.href = window.location.href;

function check() {
	if(document.getElementById('username').value==''||document.getElementById('username').value==null){
		alert("用户名不能为空");
		return false;
	}else if(document.getElementById('password').value==''||document.getElementById('password').value==null){
		alert("密码不能为空");
		return false;
	}else{
		return true;
	}
	
};
function submitdata() {
	var username = document.getElementById('username').value;
	if(username==''||username==null){
		alert("用户名不能为空");
		return;
	}
	var password = document.getElementById('password').value;
	if(password==''||password==null){
		alert("密码不能为空");
		return;
	}
	document.forms[0].submit();
};
function keyLogin(){
	var event = window.event || arguments.callee.caller.arguments[0];
	if (event.keyCode==13) {
		document.getElementById("submitbutton").click();
		event.returnValue = false;//为了防止浏览器捕捉到用户按下回车键而进行其他操作
	}
};
</script>
<style type="text/css">
	.c_d{
		position:relative;
		padding:5%;
		text-align: center;
		 height: 400px; 
		 width: 600px; 
		 margin: auto;
	}
	.c_d h1{
		font-size: 35px;
		height: 80px; 
		 width: 400px; 
		 margin: auto;
	}
	.c_d h2{
		font-size: 35px;
		height: 80px; 
		 width: 400px; 
		 margin: auto;
	}
	.c_d p{
	
	}
	.c_d p span{
		background: url(../companySys/images/log_input.png) no-repeat left;
	}
	.c_d p input{
		margin: 15px;
		background: url(../companySys/images/log_input.png) no-repeat left top;
		width: 205px;
		height: 30px;
		padding: 0 0 0 5px;
		line-height: 30px;
	}
	
	.c_d .btn{
		background: url(../sysimages/login_btn_bg.gif) no-repeat left top;
	    width: 61px;
	    height: 30px;
	    color: #fff;
	    font-family: "微软雅黑";
	    text-align: center;
	}
</style>
</head>

<body>
<div class="login" onkeydown="keyLogin()">
	<form action="login.action" method="post">
	<div class="login_top">
		<div>&nbsp;
		</div>
	</div>
		<div class="c_d">
			<h1>谷粒网</h1>
			<h2>供应商后台管理系统</h2>
			<p>
				<span><input id="username" type="text" name="loginname" placeholder="用户名:" /></span>
				<input id="password" type="password" name="password" placeholder="密码:" />
				<input id="submitbutton" class="btn" type="button" value="登录" onclick="submitdata()" /> 
			</p>
		</div>
	</form>
</div>
</body>
</html>
