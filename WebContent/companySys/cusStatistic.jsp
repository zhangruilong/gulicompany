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
<form id='main_form' action="cusStatistic.action" method="post">
 <input type="hidden" name="companyid" value="${sessionScope.loginInfo.companyid }">
 <input type="hidden" id="staTime" name="staTime" value="${requestScope.staTime }">
 <input type="hidden" id="endTime" name="endTime" value="${requestScope.endTime }">
<div class="nowposition">当前位置：客户管理》客户信息统计</div>
 
<div class="navigation">
<div>下单时间:</div><div id="divDate" class="date"></div>
<div>到:</div><div id="divDate2"  class="date"></div>
模糊查询:<input type="text"  class="condition_query" name="staCusQuery" value="${requestScope.staCusQuery }">
<input class="button" type="button" value="查询" onclick="subfor()">
<input class="button" type="button" value="导出报表" onclick="report()">
</div>
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>店名</th>
		<th>联系人</th>
		<th>手机</th>
		<th>地址</th>
		<th>客户经理</th>
		<th>订单数量</th>
		<th>订单总额</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.cusStaVoList) != 0 }">
	<c:forEach var="cusSta" items="${requestScope.cusStaVoList }" varStatus="cusStaSta">
		<tr>	
			<td><c:out value="${cusStaSta.count}"></c:out></td>
			<td>${cusSta.customershop}</td>
			<td>${cusSta.customername}</td>
			<td>${cusSta.customerphone}</td>
			<td>${cusSta.customeraddress}</td>
			<td>${cusSta.cccreatetime}</td>
			<td>${cusSta.odm_num}</td>
			<td>${cusSta.odm_money}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">总计</td>
		<td>${requestScope.total.odm_num }</td>
		<td>${requestScope.total.odm_money }</td>
	</tr>
	</c:if>
	<c:if test="${fn:length(requestScope.cusStaVoList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td class="td_fenye" colspan="14" align="center">	
			 <c:if test="${requestScope.nowpage > 1 }">
		 	<a onclick="fenye('1')">第一页</a>
		 </c:if>
		 <c:if test="${requestScope.nowpage == 1 }">
		 	<span>第一页</span>
		 </c:if>
		  <c:if test="${requestScope.nowpage > 1 }">
		 	<a onclick="fenye('${requestScope.nowpage - 1 }')">上一页</a>
		 </c:if>
		 <c:if test="${requestScope.nowpage == 1 }">
		 	<span>上一页</span>
		 </c:if>
		 	
		 	<span>当前第${requestScope.nowpage }页</span>
		 	
		 <c:if test="${requestScope.nowpage < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.nowpage + 1 }')">下一页</a>
		 </c:if>
		 <c:if test="${requestScope.nowpage == requestScope.pageCount }">
		 	<span>下一页</span>
		 </c:if>
		 <c:if test="${requestScope.nowpage < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pageCount }')">最后一页</a>
		 </c:if>
		 <c:if test="${requestScope.nowpage == requestScope.pageCount }">
		 	<span>最后一页&nbsp;</span>
		 </c:if>
		 	<span>跳转到第<input style="width: 22px;text-align: center;" size="1" type="text" id="nowpage" name="nowpage" value="${requestScope.nowpage }">页</span>
		 	<input type="submit" value="GO" style="width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>       
</table>
</form>

<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
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
	var pageFlag = 0;
   	if($(".condition_query").val() != '${requestScope.condition }'){
   		$("#nowpage").val('1');
   		pageFlag++;
   	}
   	if($('#staTime').val() != '${requestScope.staTime }'){
   		$("#nowpage").val('1');
   		pageFlag++;
   	}
   	if($('#endTime').val() != '${requestScope.endTime }'){
   		$("#nowpage").val('1');
   		pageFlag++;
   	}
  }
//导出报表
function report(){
	window.location.href ="exportCusStatisticReport.action?companyid=${sessionScope.loginInfo.companyid }"+
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
	$("#nowpage").val(targetPage);
	checkCondition();
	document.forms[0].submit();
}
</script>
</body>
</html>