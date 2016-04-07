<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ordermid = request.getParameter("ordermid");
String ordermcompany = request.getParameter("ordermcompany");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/tabsty.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="deleOrderd.action" method="post">
 <pg:pager maxPageItems="10" url="orderDetail.action">
<div class="nowposition">商品管理/添加商品</div>
<p class="navigation">
<input>
<input class="button" type="button" value="查询" onclick="">
</p>
<br />
<table class="bordered">
    <thead>
    <tr>
    	<th>&nbsp;</th>
        <th>序号</th>
		<th>商品编码</th>
		<th>商品名称</th>
		<th>描述</th>
		<th>规格</th>
		<th>小类名称</th>
		<th>状态</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.order.orderdList) != 0 }">
	<c:forEach var="orderd" items="${requestScope.order.orderdList }" varStatus="orderdSta">
	<pg:item>
		<tr>	
			<td><input type="checkbox" name="orderdids" value="${orderd.orderdid}"></td>
			<td><c:out value="${orderdSta.count}"></c:out></td>
			<td>${orderd.orderdcode}</td>
			<td>${orderd.orderdname}</td>
			<td>${orderd.orderdunits}</td>
			<td>${orderd.orderdtype}</td>
			<td>${orderd.orderdclass}</td>
			<td>${orderd.orderddetail}</td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.order.orderdList)==0 }">
		<tr><td colspan="8" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="8" align="center">	
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
</form>
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
</body>
</html>