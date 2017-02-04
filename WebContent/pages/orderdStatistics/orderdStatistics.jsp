<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../../css/dig.css" rel="stylesheet" type="text/css">
	<link href="../../css/tabsty.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="../../zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../../js/DateTimePicker.js"></script>
	<script type="text/javascript" src="../../js/DeteTimeField.js"></script>
	<script type="text/javascript">
	var comid = '<%=comid%>';
	</script>
	
	<script type="text/javascript" src="orderdStatisticsfun.js"></script>
	<script type="text/javascript" src="orderdStatistics.js"></script>
	<style type="text/css">
.goods_select_popup{
	margin:0px auto;
	margin-top:1%;
	width: 600px;
	background-color: #FBF1E5;
}
.xdsoft_datetimepicker  .xdsoft_calendar td > div{
   padding-right:10px;
   padding-top: 5px
}
</style>
  </head>
  <body>
  <div id="datagriddiv">
  </div>
<div class="cd-popup emp-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
<input class="button" type="button" value="确定" onclick="empConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenCusShow()">
</div>
<div class="alert-emp-show">
</div>
</div>
</div>
<div class="cd-popup brand-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
<input class="button" type="button" value="确定" onclick="brandConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenCusShow()">
</div>
<div class="alert-brand-show">
</div>
</div>
</div>
<div class="cd-popup cusNames-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="queryShop" name="queryShop" value="" onchange="showCusNames()">
<input class="button" type="button" value="查询" onclick="showCusNames()">
<input class="button" type="button" value="确定"  onclick="cusNameConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenCusShow()">
</div>
<div class="alert-cusNames-show">
</div>
</div>
</div>
  </body>
</html>
