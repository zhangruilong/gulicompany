<%@page import="com.server.pojo.entity.Company"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.pojo.System_power_quickview"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Company company = (Company)session.getAttribute("company"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
<style type="text/css">
body{
background: url(../sysimages/top_repeat.gif) repeat;
margin: 0px auto;
}
.title{
	float:left;
	height: 100px;
	width: 200px;
	text-align: center;
}
.title .guliwang{
	font-size: 24px;.
	width: 200px;
	margin-top: 20px;
}
.title .gong{
	font-size: 18px;
	width: 200px;
}
.mokuai{
	float: right;
	width: 650px;
}

.mokuai ul{
	
}

.mokuai ul li{
	list-style: none;
	float: right;
	height: 60px;
	width: 60px;
	font-size: 24px;
	margin-right: 10%;
}
</style>
</head>
<body>
	<div class="title">
		<div class="guliwang">谷粒网</div>
		<div class="gong">供应商后台管理系统</div>
	</div>
	<div class="mokuai">
		<ul>
			<li>系统管理</li>
			<li>合作管理</li>
			<li>客户管理</li>
			<li>商品管理</li>
			<li onclick="doOrder()">订单管理</li>
		</ul>
	</div>
	<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		function doOrder(){
			var t = $(window.parent.leftFrame.menu_header_title);
			t.text("订单管理");
			var menu_body = $(window.parent.leftFrame.menu__body);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=company.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=company.getCompanyid() %>'+"&ordermway=在线支付  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>在线支付订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=company.getCompanyid() %>'+"&ordermway=货到付款  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>货到付款订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
		}
		
	</script>
</body>
</html>
