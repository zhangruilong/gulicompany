<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
</head>
<body>
 <pg:pager maxPageItems="10" url="allTimeGoods.action" export="currentNumber=pageNumber">
 <pg:param name="timegoodscompany" value="${sessionScope.company.companyid }"/>
<div class="nowposition">当前位置：商品管理》促销商品</div>
<br />
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>商品编号</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>原价</th>
		<th>现价</th>
		<th>限量</th>
		<th>状态</th>
		<th>创建时间</th>
		<th>创建人</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.timegoodsList) != 0 }">
	<c:forEach var="timegoods" items="${requestScope.timegoodsList }" varStatus="timegoodsSta">
	<pg:item>
		<tr>
			<td><c:out value="${timegoodsSta.count}"></c:out></td>
			<td>${timegoods.timegoodscode}</td>
			<td>${timegoods.timegoodsname}</td>
			<td>${timegoods.timegoodsunits}</td>
			<td>${timegoods.timegoodsclass}</td>
			<td>${timegoods.timegoodsprice}</td>
			<td>${timegoods.timegoodsorgprice}</td>
			<td>${timegoods.timegoodsnum}</td>
			<td>${timegoods.timegoodsstatue}</td>
			<td>${timegoods.createtime}</td>
			<td>${timegoods.creator}</td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.timegoodsList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="14" align="center">	
			 <pg:index>
			 <pg:first><a href="${pageUrl }">第一页</a></pg:first>
			 <pg:prev><a href="${pageUrl}">上一页</a></pg:prev>
			 <pg:pages>
			 	<c:if test="${currentNumber != pageNumber }">
				 	<a onclick="nowpage(this)" href="${pageUrl}">[${pageNumber }]</a>
			 	</c:if>
			 	<c:if test="${currentNumber == pageNumber }">
			 		<span class="fenye">${pageNumber }</span>
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