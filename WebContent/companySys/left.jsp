<%@page import="com.server.pojo.entity.Company"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<%Company company = (Company)session.getAttribute("company"); %>
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
        <a href="allOrder.action?ordermcompany=<%=company.getCompanyid() %>" target="main" class="nav__item-link">
          <span class="nav__item-text">全部订单</span>
        </a>   
      </li>
      <li class="nav__item">
        <a href="allOrder.action?ordermcompany=<%=company.getCompanyid() %>&ordermway=在线支付" target="main" class="nav__item-link">
          <span class="nav__item-text">在线支付订单</span>
        </a>   
      </li>
       <li class="nav__item">
        <a href="allOrder.action?ordermcompany=<%=company.getCompanyid() %>&ordermway=货到付款" target="main" class="nav__item-link">
          <span class="nav__item-text">货到付款订单</span>
        </a>   
      </li>
      </ul>
</div>
</body>
</html>
