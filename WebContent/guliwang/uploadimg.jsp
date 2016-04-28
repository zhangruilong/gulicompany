<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
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
</head> 

<body>
<form action="Upload" method="post" enctype="multipart/form-data">
	<input type="file" name="fileName"/>
	<input onclick="uploadimg()" type="button" value="上传"/> 
</form>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
function uploadimg(){
	 $('form').attr('action','System_attachAction.do?method=uploadImg&other=getch&json='+'[{"code":"bianma","detail":"","classify":"客户","fid":"'+customer.customerid+',"}]');
	 document.forms[0].submit();
}
</script>
</body>
</html>