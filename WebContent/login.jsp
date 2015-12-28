<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>用户登录</title>
<link href="sysstyle/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="sysjs/png.js"></script>
<%@ include file="/common/common.jsp"%>
<script type=text/javascript>
if (top != window) top.location.href = window.location.href;
function check() {
	if(document.getElementById('username').value==''||document.getElementById('username').value==null){
		alert("用户名不能为空");
		return false;
	}else if(document.getElementById('password').value==''||document.getElementById('password').value==null){
		alert("密码不能为空");
		return false;
	}else if(document.getElementById('input').value==''||document.getElementById('input').value==null){
		alert("验证码不能为空");
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
	var input = document.getElementById('input').value;
	if(input==''||input==null){
		alert("验证码不能为空");
		return;
	}
	Ext.Ajax.request({
		url : 'System_userAction.do?method=login',
		method : 'POST',
		params : {
			username : username,
			password : password,
			input : input
		},
		success : function(resp,opts) {
			var respText = Ext.util.JSON.decode(resp.responseText); 
			if(respText.success == false) alert(respText.msg);
			else window.location.href = "main.jsp"; ;
		},
		failure : function(resp,opts) {
			Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
		}
	});
};
function keyLogin(){
	if (event.keyCode==13) {
		document.getElementById("submitbutton").click();
		event.returnValue = false;//为了防止浏览器捕捉到用户按下回车键而进行其他操作
	}
};
</script>
</head>

<body class="login" onkeydown="keyLogin();">
	<div class="login_top">
		<div>
			<img src="sysimages/danonglogo.png" width="154" height="25" class="logo" />
			<h1>谷粒管理平台</h1>
			<h6 class="clear"></h6>
		</div>
	</div>
	<div class="login_main">
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100" align="right">用户名：</td>
					<td><input type="text" name="username" id="username"
						class="member" /></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td height="25">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="password" name="password" id="password"
						class="pwd" /></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td height="25">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">验证码：</td>
					<td><blockquote class="test">
							<input type="text" name="input" id="input" maxlength="4" /> <img
								id="code" border="0" src="common/image.jsp"
								onclick="javascript:var dt=new Date();document.getElementById('code').src='common/image.jsp?dt='+dt;" />
						</blockquote></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td height="25">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input id="submitbutton" type="button" value="登录"
						class="btn" onclick="submitdata()" /></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
