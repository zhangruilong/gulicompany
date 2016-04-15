<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath%>ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-lang-zh_CN.js" charset="GBK"></script>
</head>
<body>
<form id='main_form' action="orderStatistics.action" method="post">
 <input type="hidden" name="companyid" value="${sessionScope.company.companyid }">
 <input type="hidden" id="staTime" name="staTime" value="${requestScope.staTime }">
 <input type="hidden" id="endTime" name="endTime" value="${requestScope.endTime }">
<div class="nowposition">当前位置：订单管理》订单商品统计</div>
 
<div class="navigation">
<div>交易时间:</div><div id="divDate" class="date"></div>
<div>到:</div><div id="divDate2"  class="date"></div>
模糊查询:<input type="text"  class="condition_query" name="condition" value="${requestScope.condition }">
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
		<th>单价</th>
		<th>商品总价</th>
		<th>实际金额</th>
		<th>订单时间</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.orderdList) != 0 }">
	<c:forEach var="orderd" items="${requestScope.orderdList }" varStatus="orderdSta">
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
	</c:forEach>
	<tr>
		<td colspan="6">总计</td>
		<td>${requestScope.total.numtotal }</td>
		<td>${requestScope.total.moneytotal }</td>
		<td>${requestScope.total.rightmoneytotal }</td>
		<td></td>
	</tr>
	</c:if>
	<c:if test="${fn:length(requestScope.orderdList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td class="td_fenye" colspan="14" align="center">	
			 <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('1')">第一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>第一页</span>
		 </c:if>
		  <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('${requestScope.pagenow - 1 }')">上一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>上一页</span>
		 </c:if>
		 	
		 	<span>当前第${requestScope.pagenow }页</span>
		 	
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pagenow + 1 }')">下一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>下一页</span>
		 </c:if>
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pageCount }')">最后一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>最后一页&nbsp;</span>
		 </c:if>
		 	<span>跳转到第<input style="width: 22px;text-align: center;" size="1" type="text" id="pagenow" name="pagenow" value="${requestScope.pagenow }">页</span>
		 	<input type="submit" value="GO" style="width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>       
</table>
</form>

<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var md;						//第一个日期对象
var md2;					//第二个日期对象
   Ext.onReady(function(){
		md = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:90,
			format:"Y-m-d",
			emptyText:"${(requestScope.staTime == null || requestScope.staTime == '')?'请选择日期...':requestScope.staTime}"		//默认显示的日期
		});
		md.render('divDate');
		
		md2 = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:90,
			format:"Y-m-d",
			emptyText:"${(requestScope.endTime == null || requestScope.endTime == '')?'请选择日期...':requestScope.endTime}"		//默认显示的日期
		});
		md2.render('divDate2');
		$("#main_form").on("submit",function(){
			checkCondition();
		});
   });
 //检查查询条件是否变化
  function checkCondition(){
	  $(".condition_query").val($.trim($(".condition_query").val()));
   	if($(".condition_query").val() != '${requestScope.condition }'){
   		$("#pagenow").val('1');
   	}
   	if($('#staTime').val() != '${requestScope.staTime }'){
   		$("#pagenow").val('1');
   	}
   	if($('#endTime').val() != '${requestScope.endTime }'){
   		$("#pagenow").val('1');
   	}
   	if(parseInt($("#pagenow").val()) > '${requestScope.pageCount }' ){
   		$("#pagenow").val('${requestScope.pageCount }');
   	}
  }
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
	checkCondition();
	document.forms[0].submit();
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition();
	document.forms[0].submit();
}
</script>
</body>
</html>