<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
		/* if(null != session.getAttribute("company")){
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} */
	 %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>用户登录</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type=text/javascript>
if (top != window) top.location.href = window.location.href;

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
	$.ajax({
		url:"CPCompanyAction.do?method=comlogin",
		type:"post",
		data:{
			loginname:username,
			password:password
		},
		success:function(resp){
			var data = eval('('+resp+')');
			if(data.code==202){
				location.href = 'main.jsp';
			} else {
				alert('输入的账号或密码错误。');
			}
		},
		error:function(resp){
			var data = eval('('+resp+')');
			alert(data.msg);
		}
	});
};
function keyLogin(){
	var event = window.event || arguments.callee.caller.arguments[0];
	if (event.keyCode==13) {
		document.getElementById("submitbutton").click();
		event.returnValue = false;								//为了防止浏览器捕捉到用户按下回车键而进行其他操作
	}
};
</script>
</head>

<body class="body" onkeydown="keyLogin()">
<div class="login">
	<div class="c_d">
		<h1><img src="images/logo.png" alt=""/></h1>
		<h2>
			<img src="images/left_crossband.png" alt="" />
			供应商后台管理系统
			<img src="images/right_crossband.png" alt="" />
		</h2>
		<div class="log_input">
			<div class="log_name"><img alt="" src="images/username.png">
				<input id="username" type="text" name="loginname" placeholder="用户名" />
			</div>
			<div class="log_pwd"><img alt="" src="images/password.png">
				<input id="password" type="password" name="password" placeholder="密码" />
			</div>
				<a id="submitbutton" class="btn" onclick="submitdata()">登录</a>
		</div>
	</div>
</div>
</body>
</html>
