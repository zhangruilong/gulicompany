<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<form action="addAddress.action" method="post">
<div class="reg-wrapper">
	<ul>
    	<li><span>收件人</span> <input name="addressconnect" type="text" placeholder="请输入联系人名" /></li>
        <li><span>手机号</span> <input name="addressphone" type="text" placeholder="请输入手机号" /></li>
    </ul>
</div>
<div class="reg-wrapper reg-dianpu-info">
	<ul>
    	<li><span>所在城市</span> <select id="city">
    		<c:forEach items="${requestScope.cityList }" var="cyty">
    		<c:if test="${cyty.cityparent=='root' }">
				<option value="${cyty.cityname }">${cyty.cityname }</option>
			</c:if>
			</c:forEach></select><i></i></li>
        <li><span>所在区域</span> <select  id="xian">
        	<c:forEach items="${requestScope.cityList }" var="cyty">
        		<c:if test="${cyty.cityparent!='root' }">
					<option value="${cyty.cityname }">${cyty.cityname }</option>
				</c:if>
			</c:forEach></select><i></i></li>
        <li><span>详细地址</span> <input id="detaAddressa" type="text" placeholder="请输入详细地址"></li>
    </ul>
</div>
<div class="reg-wrapper">
	<ul>
    	<li><label><input name="addressture" type="checkbox" value="1" class="set-default"> <span>设置默认</span></label></li>
    </ul>
</div>
<input id="addressaddress" type="hidden" name="addressaddress" value="">
<input type="hidden" name="addresscustomer" value="${sessionScope.customer.customerid }">
    <input type="hidden" name="customerId" value="${sessionScope.customer.customerid }">
<div class="add-address-btn">
	<a onclick="javascript:window.location.href = 'doAddressMana.action?customerId=${sessionScope.customer.customerid }'">返回</a>
    <a onclick="addAddress()">保存</a>
</div>
</form>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function addAddress(){
		var city = $("#city").val();
		var xian = $("#xian").val();
		var detaAddressa = $("#detaAddressa").val();
		var addressaddress = $("#addressaddress").val(city+xian+detaAddressa);
		document.forms[0].submit();
	}
</script>
</body>
</html>







