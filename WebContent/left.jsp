<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<%String comid = (String)session.getAttribute("comid"); %>
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
        <a href="pages/orderMana/orderMana.jsp" target="main" class="nav__item-link">
          <span class="nav__item-text">全部订单</span>
        </a>   
      </li>
      <li class="nav__item">
        <a href="pages/orderdStatistics/orderdStatistics.jsp" target="main" class="nav__item-link">
          <span class="nav__item-text">订单商品统计</span>
        </a>   
      </li>
      <li class='nav__item'>
      	<a href='pages/cusInfoStatistic/cusInfoStatistic.jsp'  target='main' class='nav__item-link'>
      		<span class='nav__item-text'>订单业务统计</span>
      	</a>   
      </li>
      </ul>
</div>
</body>
</html>
