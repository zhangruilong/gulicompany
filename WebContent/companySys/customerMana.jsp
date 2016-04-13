<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customertype = request.getParameter("customer.customertype");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var customertype = '<%=customertype %>';
$(function(){
	if(customertype == null || customertype == ''){
		$(".nowposition").html("当前位置：客户管理》全部客户");
	} else if(customertype == '3'){
		$(".nowposition").html("当前位置：客户管理》餐饮客户");
	} else if(customertype == '2'){
		$(".nowposition").html("当前位置：客户管理》商超客户");
	} else if(customertype == '1'){
		$(".nowposition").html("当前位置：客户管理》组织单位客户");
	}
	
})

</script>
</head>
<body>
 <pg:pager maxPageItems="10" url="allCustomer.action" export="currentNumber=pageNumber">
 <pg:param name="ccustomercompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="customer.customertype" value="${requestScope.ccustomerCon.customer.customertype}"/>
<div class="nowposition">当前位置：客户管理》全部客户</div>
<br />
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>客户编码</th>
		<th>名称</th>
		<th>地址</th>
		<th>联系人</th>
		<th>手机</th>
		<th>客户类型</th>
		<th>价格层级</th>
		<th>操作</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.ccustomerList) != 0 }">
	<c:forEach var="ccustomer" items="${requestScope.ccustomerList }" varStatus="ccustomerSta">
	<pg:item>
		<tr>
			<td><c:out value="${ccustomerSta.count}"></c:out></td>
			<td>${ccustomer.customer.customercode}</td>
			<td>${ccustomer.customer.customershop}</td>
			<td>${ccustomer.customer.customercity}${ccustomer.customer.customerxian}${ccustomer.customer.customeraddress}</td>
			<td>${ccustomer.customer.customername}</td>
			<td>${ccustomer.customer.customerphone}</td>
			<td>${ccustomer.customer.customertype == 3?'餐饮客户':(ccustomer.customer.customertype == 2?'商超客户':'组织单位客户')}</td>
			<td>${ccustomer.ccustomerdetail}</td>
			<td><a href="editCusInfo.jsp?customerid=${ccustomer.customer.customerid}&ccustomerid=${ccustomer.ccustomerid}">修改</a></td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.ccustomerList)==0 }">
		<tr><td colspan="9" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="9" align="center">	
			 <pg:index>
			 <pg:first><a href="${pageUrl }">第一页</a></pg:first>
			 <pg:prev><a href="${pageUrl}">上一页</a></pg:prev>
			 <pg:pages>
			 	<c:if test="${currentNumber != pageNumber }">
				 	<a onclick="nowpage(this)" href="${pageUrl}">[${pageNumber }]</a>
			 	</c:if>
			 	<c:if test="${currentNumber == pageNumber }">
			 		<a class="fenye">${pageNumber }</a>
			 	</c:if>
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