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
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>

</head>
<body>
<form action="deleOrderd.action" method="post">
 <pg:pager maxPageItems="10" url="orderDetail.action">
 <pg:param name="ordermid" value="${requestScope.order.ordermid }"/>
 <pg:param name="orderdids"/>
 <input type="hidden" name="ordermid" value="${requestScope.order.ordermid }">
 <input type="hidden" name="ordermrightmoney" value="${requestScope.order.ordermrightmoney }">
 <input type="hidden" name="ordermmoney" value="${requestScope.order.ordermmoney }">
 <input type="hidden" name="ordermnum" value="${requestScope.order.ordermnum }">
<div class="nowposition">订单管理》订单详情</div>
<p class="navigation">
<span>订单编号:${requestScope.order.ordermcode }&nbsp;&nbsp;&nbsp;&nbsp;</span>
<span>订单状态 : ${requestScope.order.ordermstatue }&nbsp;&nbsp;&nbsp;&nbsp;</span>
<input class="button" type="button" value="确认订单" onclick="updateStatue('已确认');">
<input class="button" type="button" value="订单发货" onclick="updateStatue('已发货');">
<input class="button" type="button" value="完成订单" onclick="updateStatue('已完成');">
<input class="button" type="button" value="删除订单" 
onclick="del('editOrder.action?ordermid=${requestScope.order.ordermid }&ordermcompany=${requestScope.order.ordermcompany }','删除')">
<input class="button" type="button" value="删除商品" onclick="delegoods()">
</p>
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
		<th>修改</th>
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
			<td><a href="doeditOrder.action?orderdid=${orderd.orderdid}&ordermcompany=${requestScope.order.ordermcompany }&ordermid=${requestScope.order.ordermid }">修改</a></td>
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
<script type="text/javascript">
var ordermid = '<%=ordermid%>';
var ordermcompany = '${requestScope.order.ordermcompany }';
//修改订单状态
function updateStatue(statue){
	if(confirm("是否修改订单状态")){
		window.location.href = 
			"deliveryGoods.action?ordermid="
					+ordermid
					+"&ordermcompany="
					+ordermcompany
					+"&ordermstatue="
					+statue;
	}
}
//删除商品
function delegoods(){
	var orderdids = $("[name='orderdids']");
	var count = 0;
	$.each(orderdids,function(i,item){
		if(item.checked){
			count ++;
		}
	});
	if(count > 0){
		document.forms[0].submit();
	} else {
		alert("请选择要删除的商品");
	}
}
function del(url,message){
	if(confirm("是否"+message)){
		parent.main.location.href = url;
	}
	
}
</script>
</body>
</html>