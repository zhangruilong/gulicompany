<%@page import="com.server.pojo.entity.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<%LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");  %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
</head>

<body>
<div class="menu" id="menu">
    <ul class="nav">
      <li class="nav__item">
        <a href="allOrder.action?ordermcompany=<%=loginInfo.getCompanyid() %>" target="main" class="nav__item-link">
          <span class="nav__item-text">全部订单</span>
        </a>   
      </li>
      <li class="nav__item">
        <a href="allOrder.action?ordermcompany=<%=loginInfo.getCompanyid() %>&ordermway=在线支付" target="main" class="nav__item-link">
          <span class="nav__item-text">在线支付订单</span>
        </a>   
      </li>
       <li class="nav__item">
        <a href="allOrder.action?ordermcompany=<%=loginInfo.getCompanyid() %>&ordermway=货到付款" target="main" class="nav__item-link">
          <span class="nav__item-text">货到付款订单</span>
        </a>   
      </li>
      <li class="nav__item">
        <a href="orderStatistics.action?companyid=<%=loginInfo.getCompanyid() %>" target="main" class="nav__item-link">
          <span class="nav__item-text">订单商品统计</span>
        </a>   
      </li>
      <li class='nav__item'>
      	<a href=cusStatistic.action?companyid=<%=loginInfo.getCompanyid() %>  target='main' class='nav__item-link'>
      		<span class='nav__item-text'>订单业务统计</span>
      	</a>   
      </li>
      </ul>
</div>
</body>
</html>
