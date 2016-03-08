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
</head>

<body>
	<div class="reg-wrapper">
		<ul>
			<li><span>登录账号</span> <input name="customerphone" type="text"
				placeholder="请输入手机号码"></li>
			<li><span>设置密码</span> <input name="customerpsw" type="text"
				placeholder="请输入6-12位字符"></li>
			<li><span>确认密码</span> <input name="" type="text"
				placeholder="请再次输入密码"></li>
		</ul>
	</div>
	<div class="reg-wrapper reg-dianpu-info">
		<ul>
			<li><span>所在城市</span> 
			<select name="customercity">
				<c:forEach items="${requestScope.cityParents }" var="cyty">
					<option value="${cyty.cityparent }">${cyty.cityparent }</option>
				</c:forEach>
			</select><i></i></li>
			<li><span>服务区域</span> 
			<select name="customerxian">
				<c:forEach items="${requestScope.cityList }" var="cyty">
					<option value="${cyty.cityname }">${cyty.cityname }</option>
				</c:forEach>
					<!-- <option>黄浦区</option> -->
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
		<a href="#" class="confirm-reg-btn">确认注册</a> <a href="#">确认注册即同意《谷粒网客户注册网络协议》</a>
	</div>
</body>
</html>
