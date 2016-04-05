<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../companySys/js/showdate.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath%>ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-lang-zh_CN.js" charset="GBK"></script>
</head>
<body>
<form action="orderStatistics.action" method="post">
 <pg:pager maxPageItems="8" url="orderStatistics.action">
 <pg:param name="companyid" value="${sessionScope.company.companyid }"/>
 <pg:param name="staTime" value="${requestScope.staTime }"/>
 <pg:param name="endTime" value="${requestScope.endTime }"/>
 <pg:param name="condition" value="${requestScope.condition }"/>
 <input type="hidden" name="companyid" value="${sessionScope.company.companyid }">
 <input type="hidden" id="staTime" name="staTime" value="${requestScope.staTime }">
 <input type="hidden" id="endTime" name="endTime" value="${requestScope.endTime }">
<div class="nowposition">当前位置：订单管理》订单商品统计</div>
 
<div class="navigation">
<div>交易时间:</div><div id="divDate" class="date"></div>
<div>到:</div><div id="divDate2"  class="date"></div>
查询条件:<input type="text" name="condition" value="${requestScope.condition }">
<input class="button" type="button" value="查询" onclick="subfor()">
<input class="button" type="button" value="导出报表" onclick="report()">
</div>
<br />
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>商品编码</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>单位</th>
		<th>数量</th>
		<th>商品单价</th>
		<th>商品总价</th>
		<th>实际金额</th>
		<th>订单时间</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.orderdList) != 0 }">
	<c:forEach var="orderd" items="${requestScope.orderdList }" varStatus="orderdSta">
	<pg:item>
		<tr>	
			<td><c:out value="${orderdSta.count}"></c:out></td>
			<td>${orderd.orderdcode}</td>
			<td>${orderd.orderdname}</td>
			<td>${orderd.orderdunits}</td>
			<td>${orderd.orderdunit}</td>
			<td>${orderd.orderdnum}</td>
			<td>${orderd.orderdprice}</td>
			<td>${orderd.orderdmoney}</td>
			<td>${orderd.orderdrightmoney}</td>
			<td>${orderd.orderm.ordermtime}</td>
		</tr>
	</pg:item>
	</c:forEach>
	<tr>
		<td colspan="5">总计</td>
		<td>${requestScope.total.numtotal }</td>
		<td>${requestScope.total.pricetotal }</td>
		<td>${requestScope.total.moneytotal }</td>
		<td>${requestScope.total.rightmoneytotal }</td>
		<td></td>
	</tr>
	</c:if>
	<c:if test="${fn:length(requestScope.orderdList)==0 }">
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

<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var md;						//第一个日期对象
var md2;					//第二个日期对象
   Ext.onReady(function(){
		md = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:100,
			format:"Y-m-d",
			emptyText:"${(requestScope.staTime == null || requestScope.staTime == '')?'请选择日期...':requestScope.staTime}"		//默认显示的日期
		});
		md.render('divDate');
		
		md2 = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:100,
			format:"Y-m-d",
			emptyText:"${(requestScope.endTime == null || requestScope.endTime == '')?'请选择日期...':requestScope.endTime}"		//默认显示的日期
		});
		md2.render('divDate2');
   });
//导出报表
function report(){
	window.location.href ="exportReport.action?companyid=${sessionScope.company.companyid }"+
	"&staTime=${requestScope.staTime }&endTime=${requestScope.endTime }&condition=${requestScope.condition }";
}
//查询
function subfor(){
	var gedt = Ext.util.Format.date(md.getValue(), 'Y-m-d');	//得到查询时间
	var gedt2 = Ext.util.Format.date(md2.getValue(), 'Y-m-d');	
	if(gedt == ''){
		gedt = "${requestScope.staTime}";
	}
	if(gedt2 == ''){
		gedt2 = "${requestScope.endTime}";
	}
	$("#staTime").val(gedt);
	$("#endTime").val(gedt2);
	document.forms[0].submit();
}
</script>
</body>
</html>