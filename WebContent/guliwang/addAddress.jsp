<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html> 
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<style type="text/css">
.home-search-wrapper input {
	float:right;
	height: 30px;
	border-radius: 5px;
	font-size: .8em;
	border: none;
	padding: 0 10% 0 9%;
	width: 51%;
	background: url(../images/searchbg.png) #fff no-repeat left;
	background-size: 8%;
	background-position: 6px 7px
}
.home-search-wrapper span {
	float:left;
	height: 30px;
	font-size: 18px;
	font-weight: 800;
	width: 80px;
	color: white;
}
.home-search-wrapper table {
	width: 90%;
	margin: 20px 0px 20px 0px;
}
.home-search-wrapper {
	background-color: #2c77e6;
	padding: 10px 4%;
	height: 100%;
}
</style>
</head>
<body>
	<form action="#" method="post">
	<div class="gl-box">
	<div class="wapper-nav">地址管理</div>
	<div class="home-search-wrapper">
	<table cellpadding="2" cellspacing="4">
		<tr>
			<td><span >联系人名:</span><input type="text" placeholder="请输入联系人名" /></td>
		</tr>
		<tr>
			<td><span >手机号:</span><input type="text" placeholder="请输入手机号" /></td>
		</tr>
		<tr>
			<td><span >收货地址:</span><input type="text" placeholder="请输入收货地址" /></td>
		</tr>
    </table>
    </div>
    <div class="add-address"><a href="addAddress.jsp">  确&nbsp;认&nbsp;添&nbsp;加&nbsp;新&nbsp;地&nbsp;址  </a></div>
</div>
</form>
</body>
</html>