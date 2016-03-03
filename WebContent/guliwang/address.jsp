<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>

<body>
<div class="gl-box">
	<div class="wapper-nav">地址管理</div>
	<div class="add-admin">
		<c:forEach items="${requestScope.addressList }" var="address">
			<a href="#"><span>${address.addressconnect }</span><span>  ${address.addressphone } </span><span class="sign"></span>${address.addressture == 1?'[默认]':'' }收货地址: ${address.addressaddress } </a>
		</c:forEach>
    	
        <!-- <a href="#"><span>王金宝</span><span>  16563529810 </span><span class="sign"></span>收货地址: 嘉兴市沿海向城东路89号706室 </a> -->
    </div>
    <div class="add-address"><a href="addAddress.jsp">+ 新增收货地址</a></div>
</div>
</body>
</html>
