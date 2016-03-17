<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String goodsstatue = request.getParameter("goodsstatue");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<link href="css/style2.css" rel="stylesheet" type="text/css">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var goodsstatue = '<%=goodsstatue %>';
$(function(){
	if(goodsstatue == null || goodsstatue == ''){
		$(".page_title").html("商品管理/全部商品");
	} else if(goodsstatue == '上架'){
		$(".page_title").html("商品管理/上架商品");
	} else if(goodsstatue == '下架'){
		$(".page_title").html("商品管理/下架商品");
	}
	
})

</script>
</head>
<body>
 <pg:pager maxPageItems="8" url="allGoods.action">
 <pg:param name="goodscompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="goodsstatue" value="${requestScope.goodsCon.goodsstatue }"/>
<div class="page_title">商品管理/全部商品</div>
<div class="button_bar">
</div>
<br />
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>商品编号</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>描述</th>
		<th>状态</th>
		<th>创建时间</th>
		<th>创建人</th>
		<th>修改时间</th>
		<th>修改人</th>
		<th>价格设置</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.goodsList) != 0 }">
	<c:forEach var="goods" items="${requestScope.goodsList }" varStatus="goodsSta">
	<pg:item>
		<tr>
			<td><c:out value="${goodsSta.count}"></c:out></td>
			<td>${goods.goodscode}</td>
			<td>${goods.goodsname}</td>
			<td>${goods.goodsunits}</td>
			<td>${goods.gClass.goodsclassname}</td>
			<td>${goods.goodsdetail}</td>
			<td>
			<c:if test="${fn:length(goods.pricesList) == 9 }">
			<a href="putaway.action?goodsid=${goods.goodsid}&goodscompany=${sessionScope.company.companyid }&goodsstatue=${goods.goodsstatue == '上架'?'下架':'上架'}">
			${goods.goodsstatue}</a>
			</c:if>
			<c:if test="${fn:length(goods.pricesList) != 9 }">
			<a style="color: #C6C6C6;">
			${goods.goodsstatue}</a>
			</c:if>
			</td>
			<td>${goods.createtime}</td>
			<td>${goods.creator}</td>
			<td>${goods.updtime}</td>
			<td>${goods.updor}</td>
			<td><a href="doGoodsPrices.action?goodsid=${goods.goodsid}">价格设置</a></td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.goodsList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
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