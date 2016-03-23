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
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/style2.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var ordermid = '<%=ordermid%>';
var ordermcompany = '<%=ordermcompany%>';
function updateStatue(statue){
	if(confirm("是否修改订单状态")){
		window.parent.main.location.href = 
			"deliveryGoods.action?ordermid="
					+ordermid
					+"&ordermcompany="
					+ordermcompany
					+"&ordermstatue="
					+statue;
	}
}
function delegoods(){
	var orderdids = $("[name='orderdids']");
	var count = 0;
	$.each(orderdids,function(i,item){
		if($(item).attr("checked") == "checked"){
			count ++;
		}
	});
	if(count > 0){
		document.forms[0].submit();
	} else {
		alert("请选择要删除的商品");
	}
}
</script>
<style type="text/css">
.button {
	margin-left:20px;
	border-radius: 5px;
	color: #fef4e9;
	border: solid 1px #66ccff;
	background: #66ccff;
	background: -webkit-gradient(linear, left top, left bottom, from(#66ccff), to(#6699ff));
	background: -moz-linear-gradient(top,  #faa51a,  #f47a20);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a', endColorstr='#f47a20');
}
.button:hover {
	cursor:pointer;
	border-radius: 5px;
	background: #6666ff;
	background: -webkit-gradient(linear, left top, left bottom, from(#6666ff), to(#6699ff));
	background: -moz-linear-gradient(top,  #f88e11,  #f06015);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11', endColorstr='#f06015');
}
.button:active {
	border-radius: 5px;
	color: #fcd3a5;
	background: -webkit-gradient(linear, left top, left bottom, from(#6666ff), to(#6699ff));
	background: -moz-linear-gradient(top,  #f47a20,  #faa51a);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20', endColorstr='#faa51a');
}
.select{
	margin-left: 20px;
}
</style>
</head>
<body>
<form action="deleOrderd.action" method="post">
 <pg:pager maxPageItems="8" url="orderDetail.action">
 <pg:param name="ordermcompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="ordermid" value="${requestScope.order.ordermid }"/>
 <pg:param name="orderdids"/>
 <input type="hidden" name="ordermcompany" value="${sessionScope.company.companyid }">
 <input type="hidden" name="ordermid" value="${requestScope.order.ordermid }">
<div class="page_title">订单管理/订单详情</div>
<p>
<span>订单编号:${requestScope.order.ordermcode }&nbsp;&nbsp;&nbsp;&nbsp;</span>
<!-- <select name="ordermstatue" id="ordermstatue">
	<option ${requestScope.order.ordermstatue =='已下单'?'selected':'' } >已下单</option>
	<option ${requestScope.order.ordermstatue =='已确认'?'selected':'' } >已确认</option>
	<option ${requestScope.order.ordermstatue =='已发货'?'selected':'' } >已发货</option>
	<option ${requestScope.order.ordermstatue =='已完成'?'selected':'' } >已完成</option>
</select> -->
<span>订单状态 : ${requestScope.order.ordermstatue }&nbsp;&nbsp;&nbsp;&nbsp;</span>
<input class="button" type="button" value="确认订单" onclick="updateStatue('已确认');">
<input class="button" type="button" value="订单发货" onclick="updateStatue('已发货');">
<input class="button" type="button" value="完成订单" onclick="updateStatue('已完成');">
<input class="button" type="button" value="删除订单" 
onclick="del('editOrder.action?ordermid=${requestScope.order.ordermid }&ordermcompany=${sessionScope.company.companyid }','删除')">
<input class="button" type="button" value="删除商品" onclick="delegoods()">
</p>
<div class="button_bar">
</div>
<br />
<table class="bordered">
    <thead>
    <tr>
    	<th>&nbsp;</th>
        <th>序号</th>
		<th>商品编码</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>商品类型</th>
		<th>类型</th>
		<th>描述</th>
		<th>价格</th>
		<th>单位</th>
		<th>数量</th>
		<th>下单金额</th>
		<th>实际金额</th>
		<th>删除</th>
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
			<td>${orderd.orderdprice}</td>
			<td>${orderd.orderdunit}</td>
			<td>${orderd.orderdnum}</td>
			<td>${orderd.orderdmoney}</td>
			<td>${orderd.orderdrightmoney}</td>
			<td><a href="doeditOrder.action?orderdid=${orderd.orderdid}&ordermcompany=${sessionScope.company.companyid }&ordermid=${requestScope.order.ordermid }">修改</a></td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.order.orderdList)==0 }">
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
</form>

</body>
</html>