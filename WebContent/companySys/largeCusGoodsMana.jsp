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

</head>
<body>
<div class="nowposition">当前位置：客户管理》大客户》客户特殊价格商品</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="customercode" name="customer.customercode" value="">
<input class="button" type="button" value="查询" onclick="subcustomerfor()">
<input class="button" type="button" value="导出" onclick="reportCustomer()">
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
		<th>关联商品</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
$(function(){
	$.post("querylargeCusPriceGoods.action",{},initLargePG);
});
//初始化客户特殊价格商品数据
function initLargePG(data){
	
}
</script>
</body>
</html>