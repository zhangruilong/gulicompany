<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ordermway = request.getParameter("ordermway");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<link href="css/style2.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
    width: 1200px;
    margin: 40px auto;
    font-family: 'trebuchet MS', 'Lucida sans', Arial;
    font-size: 14px;
    color: #444;
}

table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
}

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}

}
  
</style>
<script type="text/javascript">
var ordermway = '<%=ordermway %>';
$(function(){
	if(ordermway == null || ordermway == ''){
		$(".page_title").html("订单管理/全部订单");
	} else if(ordermway == '在线支付'){
		$(".page_title").html("订单管理/在线支付订单");
	} else if(ordermway == '货到付款'){
		$(".page_title").html("订单管理/货到付款订单");
	}
	
})

</script>
</head>
<body>
 <pg:pager maxPageItems="8" url="allOrder.action">
 <pg:param name="ordermcompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="ordermway" value="${request.order.ordermway }"/>
<div class="page_title">订单管理/全部订单</div>
<div class="button_bar">
</div>
<br />
<table class="bordered">
    <thead>

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
    </thead>
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
		<tr><td colspan="14" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="14" align="center">	
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