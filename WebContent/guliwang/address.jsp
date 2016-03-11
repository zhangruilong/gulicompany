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
<style type="text/css">
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
.p-a a{
	height: 50px; 
	line-height: 20px; 
	text-align: center ;
	color: #fff ;
}


.pp{
	width: 80%;
}

</style>
</head>

<body>
<div class="gl-box">
	<div class="wapper-nav"><p class="p-a"><a href="mine.jsp" >&lt;返回</a></p>
	<p class="pp">地址管理</p></div>
	<div class="add-admin">
		<c:forEach items="${requestScope.addressList }" var="address">
			<a href="doEditAddress.action?addressid=${address.addressid }"><span>${address.addressconnect }</span><span>  ${address.addressphone } </span><span class="sign"></span>${address.addressture == 1?'[默认]':'' }收货地址: ${address.addressaddress } </a>
		</c:forEach>
    	
        <!-- <a href="#"><span>王金宝</span><span>  16563529810 </span><span class="sign"></span>收货地址: 嘉兴市沿海向城东路89号706室 </a> -->
    </div>
    <div class="add-address">
    <c:if test="${sessionScope.customer.customershop != null }">
		<a href="doAddAddress.action">+ 新增收货地址</a>
    </c:if>
    <c:if test="${sessionScope.customer.customershop == null }">
		<a onclick="doLogin()">+ 新增收货地址</a>
    </c:if>
	</div>
</div>
<script type="text/javascript">
function doLogin(){
	if(confirm("是否现在登录")){
		window.location.href = "login.jsp";
	}
}
</script>
</body>
</html>
