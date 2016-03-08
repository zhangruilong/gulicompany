<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style2.css" rel="stylesheet" type="text/css">
<style type="text/css">
.data_list_table tr td{
	text-align: center;
}
</style>
</head>
<body>
 <pg:pager maxPageItems="10" url="allOrder.action">
 <pg:param name="ordermcompany" value="${sessionScope.company.companyid }"/>
<div class="page_title">订单管理/全部订单</div>
<div class="button_bar">
</div>
<br />
<table class="data_list_table">
	<tr>
		<th>序号</th>
		<th>订单编号</th>
		<th>支付方式</th>
		<th>种类数</th>
		<th>下单金额</th>
		<th>实际金额</th>
		<th>订单状态</th>
		<th>下单时间</th>
		<th>修改时间</th>
		<th>联系人</th>
		<th>手机</th>
		<th>地址</th>
		<th>打印</th>
		<th>详情</th>
	</tr>
	<c:if test="${fn:length(requestScope.allOrder) != 0 }">
	<c:forEach var="order" items="${requestScope.allOrder }" varStatus="orderSta">
	<pg:item>
		<tr>
			<td><c:out value="${orderSta.count}"></c:out></td>
			<td>${order.ordermcode}</td>
			<td>${order.ordermway}</td>
			<td>${order.ordermnum}</td>
			<td>${order.ordermmoney}</td>
			<td>${order.ordermrightmoney}</td>
			<td>${order.ordermstatue}</td>
			<td>${order.ordermtime}</td>
			<td>${order.updtime}</td>
			<td>${order.ordermconnect}</td>
			<td>${order.ordermphone}</td>
			<td>${order.ordermaddress}</td>
			<td><a href="">打印</a></td>
			<td><a href="">详情</a></td>
		</tr>
		</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.allOrder)==0 }">
		<tr><td colspan="12" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="12" align="center">	
			 <pg:index>
			 <pg:first><a href="${pageUrl }">第一页</a></pg:first>
			 <pg:prev><a href="${pageUrl}">上一页</a></pg:prev>
			 <pg:pages>
			 <a href="${pageUrl}">[${pageNumber }]</a>
			 </pg:pages>
			 <pg:next><a href="${pageUrl}">下一页</a></pg:next>
			 <pg:last><a href="${pageUrl }">最后一页</a></pg:last>
			 </pg:index>
		 </td>
	 </tr>
</table>
</pg:pager>
</body>
</html>