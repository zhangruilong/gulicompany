<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html> 
<html>
<head>
<%response.setHeader("Pragma","No-cache");  
	response.setHeader("Cache-Control","no-cache");  
	response.setDateHeader("Expires", 0);  
 %>
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
	width: 57%;
	background:  #fff no-repeat left;
	margin: 8% 8% 8% 11%;
}
.home-search-wrapper textarea {
	border-radius: 5px;
	font-size: 1.2em;
	border: none;
	padding: 0% 5% 0% 5%;
	width: 57%;
	background:  #fff no-repeat left;
	background-size: 8%;
	background-position: 6px 7px;
	margin: 8% 8% 8% 11%;
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
	padding-left:5%;
	position: absolute;
	width: 100%;
	margin: 20px 0px 20px 0px;
}


.home-search-wrapper {
	background-color: #F2F2F2  ;
	padding: 10px 4%;
	height: 100%;
}

.wapper-nav{
	margin-left: 0px;
	width: 100%;
	float:right;
}


.button, .button:visited {
	margin-right:5px;
	float : right;
	background: red url(overlay.png) repeat-x;
	display: inline-block;
	padding: 0px 10px 6px 0px;
	color: #fff;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
	border-bottom: 1px solid rgba(0,0,0,0.25);
	position: relative;
	cursor: pointer
}

.button2, .button:visited {
	margin-right:5px;
	float : right;
	background: green url(overlay.png) repeat-x;
	display: inline-block;
	padding: 0px 10px 6px 0px;
	color: #fff;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.6);
	text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
	border-bottom: 1px solid rgba(0,0,0,0.25);
	position: relative;
	cursor: pointer
}
.p-a a{
	height: 50px; 
	line-height: 20px; 
	text-align: center ;
	color: #fff ;
}


.pp{
	width: 100%;
}
.p-a{
	
	float: left;
	width: 20%;
	 position: relative; 
	 background-color: #2c77e6; 
	 height: 30px; 
	 line-height: 30px; 
	 color: #fff ; 
	 text-align: center ;
}
</style>
</head>
<body>
	<form action="editAddress.action" method="post">
	<div class="gl-box">
	<div class="wapper-nav" ><p class="p-a"><a href="doAddressMana.action?customerId=${sessionScope.customer.customerid }" >&lt;返回</a></p>
	<p class="pp">地址管理<input class="button" type="button" value="删除" onclick="javascript:document.forms[0].action = 'delAddress.action';document.forms[0].submit();"/><input class="button2" type="button" value="保存" onclick="javascript:document.forms[0].submit();"/></p></div>
	<div class="home-search-wrapper">
	<table cellpadding="2" cellspacing="4">
		<tr>
			<td><input value="${requestScope.address.addressconnect }" name="addressconnect" type="text" placeholder="请输入联系人名" /></td>
		</tr>
		<tr>
			<td><input value="${requestScope.address.addressphone }" name="addressphone" type="text" placeholder="请输入手机号" /></td>
		</tr>
		<tr>
			<td><textarea name="addressaddress" placeholder="请输入地址" cols="10" rows="6">${requestScope.address.addressaddress }</textarea></td>
		</tr>
    </table>
    <input type="hidden" name="customerId" value="${requestScope.address.addresscustomer }">
    <input type="hidden" name="addressid" value="${requestScope.address.addressid }">
    </div>
    <!--  <div class="add-address" onclick="javascript:document.forms[0].submit();"><a>  确&nbsp;认&nbsp;修&nbsp;改&nbsp;地&nbsp;址  </a></div> -->
</div>
</form>
</body>
</html>