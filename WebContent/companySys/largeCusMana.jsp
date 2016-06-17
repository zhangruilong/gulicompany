<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customertype = request.getParameter("customer.customertype");
%>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="allCustomer.action" method="post" id="main_form">
<input type="hidden" name="ccustomercompany" value="${requestScope.ccustomerCon.ccustomercompany }">
<input type="hidden" name="customer.customertype" value="${requestScope.ccustomerCon.customer.customertype }">
<input type="hidden" name="creator" value="${requestScope.ccustomerCon.creator }">
<div class="nowposition">当前位置：客户管理》大客户</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="customercode" name="customer.customercode" value="${requestScope.ccustomerCon.customer.customercode }">
<input class="button" type="button" value="查询" onclick="subcustomerfor()">
<!-- <input class="button" type="button" value="新增" onclick="addLargeCus()"> -->
</div>
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
		<th>价格等级</th>
		<th>修改时间</th>
		<th>操作</th>
		<th> </th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.ccustomerList) != 0 }">
	<c:forEach var="ccustomer" items="${requestScope.ccustomerList }" varStatus="ccustomerSta">
		<tr>
			<td><c:out value="${ccustomerSta.count}"></c:out></td>
			<td>${ccustomer.customer.customercode}</td>
			<td>${ccustomer.customer.customershop}</td>
			<td>${ccustomer.customer.customercity}${ccustomer.customer.customerxian}${ccustomer.customer.customeraddress}</td>
			<td>${ccustomer.customer.customername}</td>
			<td>${ccustomer.customer.customerphone}</td>
			<td>${ccustomer.customer.customertype == 3?'餐饮客户':(ccustomer.customer.customertype == 2?'商超客户':'组织单位客户')}</td>
			<td>${ccustomer.ccustomerdetail}</td>
			<td>${ccustomer.customer.updtime}</td>
			<td>
				<a href="editCusInfo.jsp?ccustomerid=${ccustomer.ccustomerid}&customerid=${ccustomer.customer.customerid}&pagenow=${requestScope.pagenow }&fo=largeCus">修改</a>/
				<a href="largeCusXiaDan.jsp?customerid=${ccustomer.customer.customerid}&ccustomerid=${ccustomer.ccustomerid}&ccustomercompany=${requestScope.ccustomerCon.ccustomercompany }">下单</a>
			</td>
			<td><a href="largeCusGoodsMana.jsp?customerid=${ccustomer.ccustomerid}&companyid=${requestScope.ccustomerCon.ccustomercompany }">详情</a></td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.ccustomerList)==0 }">
		<tr><td colspan="11" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="11" align="center">
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
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
			<h1>添加大客户</h1>
			<label><span>编码 :</span><input id="givegoodscode" type="text"
				name="givegoodscode" placeholder="编码" /></label>
			<label><span>名称 :</span><input id="givegoodsname" type="text"
				name="givegoodsname" placeholder="名称" /></label>
			<label><span>规格 :</span><input id="givegoodsunits" type="text"
				name="givegoodsunits" placeholder="规格" /></label>
			<label><span>单位 :</span><input id="givegoodsunit" type="text"
				name="givegoodsunit" placeholder="单位" /></label>
			<label><span>售价 :</span><input id="givegoodsprice" type="number"
				name="givegoodsprice" placeholder="售价" /></label>
			<label><span>个人限量 :</span><input id="givegoodsnum" type="number"
				name="givegoodsnum" placeholder="个人限量" /></label>
			<label><span>顺序 :</span><input id="givegoodsseq" type="number"
				name="givegoodsseq" placeholder="顺序" /></label>
			<label><span>买赠描述 :</span><textarea name="givegoodsdetail"></textarea></label>
			<p><label><input type="button"
				class="popup_button" value="提交" onclick="popup_formSub()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="从商品中选择" onclick="dobiaopin()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="关闭窗口" onclick="close_popup()"/>
			</label></p>
	</div>
</div>
<script type="text/javascript">
var customertype = '<%=customertype %>';
$(function(){
	$("#main_form").on("submit",function(){
		checkCondition();
	});
})
//添加新大客户
function addLargeCus(){
	
}
//关闭添加大客户窗口
function close_popup(){
	$(".cd-popup").removeClass("is-visible");	//移除'is-visible' class
}
//弹出添加大客户的窗口
function addgivegoods(){
	$(".elegant-aero [type!='button']").val("");
	$(".cd-popup").addClass("is-visible");	//弹出窗口
}
//检查查询条件是否变化
function checkCondition(){
	if($("#customercode").val() != '${requestScope.ccustomerCon.customer.customercode }'){
		$("#pagenow").val('1');
	}
	if(parseInt($("#pagenow").val()) > '${requestScope.pageCount }' ){
		$("#pagenow").val('${requestScope.pageCount }');
	}
}
//提交查询条件
function subcustomerfor(){
	$("#customercode").val($.trim($("#customercode").val()));
	checkCondition();
	document.forms[0].submit();
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition()
	document.forms[0].submit();
}
//导出
function reportCustomer(){
	window.location.href ="exportCustomerReport.action?"+
			"ccustomercompany=${requestScope.ccustomerCon.ccustomercompany }"+
			"&customer.customertype=${requestScope.ccustomerCon.customer.customertype }"+
			"&customer.customercode=${requestScope.ccustomerCon.customer.customercode }"+
			"&creator=${requestScope.ccustomerCon.creator }";
}
</script>
</body>
</html>