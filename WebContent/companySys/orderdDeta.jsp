<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/formsty.css" rel="stylesheet" type="text/css"/>
<title>Insert title here</title>
</head>
<body>
	<div class="elegant-aero">
		<form action="editOrderd.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="orderdid" value="${requestScope.orderd.orderdid }">
		<input type="hidden" name="ordermcompany" value="${sessionScope.company.companyid }">
 		<input type="hidden" name="ordermid" value="${requestScope.order.ordermid }">
			<h1>修改订单商品
				<span>商品名称:${requestScope.orderd.orderdname }</span>
			</h1>
			<label> <span>商品数量 :</span> <input id="orderdnum" type="text"
				name="orderdnum" placeholder="商品数量" value="${requestScope.orderd.orderdnum } "/>
			</label> <label> <span>下单金额 :</span> <input id="orderdmoney" type="text"
				name="orderdmoney" placeholder="下单金额"  value="${requestScope.orderd.orderdmoney } "/>
			</label> <label> <span>实际金额 :</span> <input id="orderdrightmoney" type="text"
				name="orderdrightmoney" placeholder="实际金额"  value="${requestScope.orderd.orderdrightmoney } "/>
			</label> <label> <span>&nbsp;</span> <input type="submit"
				class="button" value="保存修改" />
			</label>
		</form>
	</div>
</body>
</html>