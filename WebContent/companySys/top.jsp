<%@page import="com.server.pojo.entity.Company"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.pojo.System_power_quickview"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Company company = (Company)session.getAttribute("company"); 
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
<link href="../companySys/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body class="top_body">
	<div class="top">
	<div class="title">
		<div class="guliwang"><img src="../companySys/images/orderImg/top_logo.png" alt="" /></div>
	</div>
	<div class="mokuai">
		<ul>
			<li>合作管理</li>
			<li onclick="doSys()">系统管理</li>
			<li onclick="doCustomer()">客户管理</li>
			<li onclick="doGoods()">商品管理</li>
			<li onclick="doOrder()">订单管理</li>
		</ul>
	</div>
	<div class="help"><a href=editPas.jsp  target='main' >修改密码</a>|<a href=loginOut.action  target='_parent' >退出</a>|<a>帮助</a></div>
	</div>
	<div class="sysname">供应商后台管理系统</div>
	<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	$(function(){
		window.parent.main.location.href = "allOrder.action?ordermcompany="+'<%=company.getCompanyid() %>';
		$(".mokuai ul li").each(function(i,item){
			$(item).click(function(){
				$(this).addClass("select_mokuai");
				$(this).siblings().removeClass("select_mokuai");
			});
		});
	})
		function doOrder(){
			var menu_body = $(window.parent.leftFrame.menu);
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
			        +"<li class='nav__item'>"
			        +"<a href=orderStatistics.action?companyid="+'<%=company.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>订单商品统计</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allOrder.action?ordermcompany="+'<%=company.getCompanyid() %>';
		}
		function doGoods(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=company.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=company.getCompanyid() %>'+"&goodsstatue=上架  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>上架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=company.getCompanyid() %>'+"&goodsstatue=下架  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>下架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allTimeGoods.action?timegoodscompany="+'<%=company.getCompanyid() %>'+"  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>促销商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allGoods.action?goodscompany="+'<%=company.getCompanyid() %>';
		}
		function doCustomer(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=company.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=company.getCompanyid() %>'+"&customer.customertype=餐饮客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>餐饮客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=company.getCompanyid() %>'+"&customer.customertype=高级客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>高级客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=company.getCompanyid() %>'+"&customer.customertype=组织单位客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>组织单位客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allCustomer.action?ccustomercompany="+'<%=company.getCompanyid() %>';
		}
		function doSys(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=cusInfo.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>用户信息</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=editPas.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>密码修改</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=loginOut.action  target='_parent' class='nav__item-link'>"
			        +"<span class='nav__item-text'>退出系统</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "cusInfo.jsp";
		}
	</script>
</body>
</html>
