<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function reg(){
		var customerpsw = $("[name='customerpsw']").val();
		var repwd = $("[name='repwd']").val();
		
		var count = 0;
		var alt;
		$("input").each(function(i,item){
			if($(item).val() == null || $(item).val() == ''){
				count++;
				alt=$(item).attr("placeholder");
				return false;
			}
		});
		if(count > 0){
			alert(alt);
			return;
		}
		$("select").each(function(i,item){
			if($(item).val() == null || $(item).val() == ''){
				count++;
				alt=$(item).children("option").first().text();
				return false;
			}
		});
		if(count > 0){
			alert(alt);
			return;
		}
		if(repwd != customerpsw){
			alert("两次输入的密码不相等");
			return;
		}
		alert("注册成功");
		document.forms[0].submit();
	}
</script>
</head>

<body>
<form action="reg.action" method="post">
	<div class="reg-wrapper">
		<ul>
			<li><span>登录账号</span> <input name="customerphone" type="text"
				placeholder="请输入手机号码"></li>
			<li><span>设置密码</span> <input name="customerpsw" type="password"
				placeholder="请输入6-12位字符"></li>
			<li><span>确认密码</span> <input name="repwd" type="password"
				placeholder="请再次输入密码"></li>
		</ul>
	</div>
	<div class="reg-wrapper reg-dianpu-info">
		<ul>
			<li><span>所在城市</span> 
			<select name="customercity">
				<option value="">请选择城市</option>
				<c:forEach items="${requestScope.cityList }" var="cyty">
					<c:if test="${cyty.cityparent=='root' }">
					<option value="${cyty.cityname }">${cyty.cityname }</option>
					</c:if>
				</c:forEach>
			</select><i></i></li>
			<li><span>服务区域</span> 
			<select name="customerxian">
				<option value="">请选择区域</option>
				<c:forEach items="${requestScope.cityList }" var="cyty">
				<c:if test="${cyty.cityparent!='root' }">
					<option value="${cyty.cityname }">${cyty.cityname }</option>
				</c:if>
				</c:forEach>
			</select><i></i></li>
			<li><span>店铺名称</span> <input name="customershop" type="text"
				placeholder="请输入店铺名称"></li>
			<li><span>店铺地址</span> <input name="customeraddress" type="text"
				placeholder="请输入店铺地址"></li>
			<li><span>联系人</span><input name="customername" type="text"
				placeholder="请输入名字"></li>
			<li><span>联系电话</span><input name="addressphone" type="text"
				placeholder="请输入联系人号码"></li>
		</ul>
	</div>
	<div class="confirm-reg">
		<a onclick="reg()" class="confirm-reg-btn">确认注册</a> <a href="#">确认注册即同意《谷粒网客户注册网络协议》</a>
	</div>
	</form>
</body>
</html>
