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
	height: 30px;
	border-radius: 5px;
	font-size: 1em;
	border: none;
	padding: 0 5% 0 5%;
	width: 51%;
	background:  #fff no-repeat left;
	margin: 8% 8% 8% 15%;
}
.home-search-wrapper textarea {
	border-radius: 5px;
	font-size: 1.2em;
	border: none;
	padding: 0% 5% 0% 5%;
	width: 51%;
	background:  #fff no-repeat left;
	background-size: 8%;
	background-position: 6px 7px;
	margin: 8% 8% 8% 15%;
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
	text-align: center;
	position: absolute;
	width: 90%;
	margin: 20px 0px 20px 0px;
}


.home-search-wrapper {
	background-color: #F2F2F2  ;
	padding: 10px 4%;
	height: 100%;
}

.wapper-nav{
	margin-left: 0px;
	width: 80%;
	float:right;
}


.p-a{
	float: left;
	width: 20%;
	 position: relative; background-color: #2c77e6; height: 30px; line-height: 30px; padding: 10px 0; color: #fff ; text-align: center 
}
.p-a a{
	color: #fff ;
}
</style>
</head>
<body>
	<form action="addAddress.action" method="post">
	<div class="gl-box">
	<p class="p-a"><a aling="left" href="doAddressMana.action?customerId=${requestScope.customerId }" >&lt;返回</a></p><div class="wapper-nav">
	地址管理</div>
	<div class="home-search-wrapper">
	<table cellpadding="2" cellspacing="4">
		<tr>
			<td><input size="30" value="${requestScope.address.addressconnect }" name="addressconnect" type="text" placeholder="请输入联系人名" /></td>
		</tr>
		<tr>
			<td><input value="${requestScope.address.addressphone }" name="addressphone" type="text" placeholder="请输入手机号" /></td>
		</tr>
		<tr>
			<td><textarea name="addressaddress" placeholder="请输入地址" cols="10" rows="6">${requestScope.address.addressaddress }</textarea></td>
		</tr>
    </table>
    <input type="hidden" name="addresscustomer" value="1">
    <input type="hidden" name="customerId" value="1">
    </div>
    <div class="add-address" onclick="javascript:document.forms[0].submit();"><a>  确&nbsp;认&nbsp;添&nbsp;加&nbsp;新&nbsp;地&nbsp;址  </a></div>
</div>
</form>
</body>
</html>