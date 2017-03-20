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
	String comcode = info.getCompanycode(); 
	%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印</title>
<style type="text/css">
body{
	margin: 0px; 
 	padding: 0px;
 	font-size: 14px;
 	font-family: 黑体;
}
.print_tab tr td{
	text-align: center;
}
</style>

<script type="text/javascript" src="../../zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>

</head>

<body style="width:740px;height:400px;">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WindowPrint" 
name="WindowPrint" width="0"></OBJECT>

<script language="javascript" type="text/javascript">
var ordermid = '${param.ordermid}';
var idwarrantouts = '${param.idwarrantouts}';
var ordermcode = '${param.ordermcode}';
var customershop = '${param.customershop}'
function printpreview()
{
window.print()
}
</script>

<div>
	<table width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse;border-bottom:solid 1px black;">
		<tr>
			<td colspan="6" style="text-align: center;font-size: 33px;font-family: 黑体;border-left: hidden;border-top: hidden;border-bottom: hidden;">海盐天然粮油有限公司出库单</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="3">
		<tr>
			<td width="75" align="right" style="font-size: 12px;">单据编号：</td>
			<td width="295" id="pOdmCode">111</td>
			<td width="75" align="right" style="font-size: 12px;">打印日期：</td>
			<td width="295" id="pDate">11111</td>
		</tr>
		<tr>
			<td width="75" align="right" style="font-size: 12px;">购买单位：</td>
			<td width="295" id="pCustomer">111</td>
			<td width="75" align="right" style="font-size: 12px;">领货人：</td>
			<td width="295" id="pWho">小陶</td>
		</tr>
	</table>
	
	<table class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;">
	<tr>
		<td width="29" align="center" style="font-family: 黑体;font-size: 12px;">序号</td>
		<td width="168" align="center" style="font-family: 黑体;font-size: 12px;">商品编号</td>
		<td width="" style="font-family: 黑体;font-size: 12px;">商品名称</td>
		<td width="110" align="center" style="font-family: 黑体;font-size: 12px;">规格</td>
		<td width="45" align="center" style="font-family: 黑体;font-size: 12px;">单位</td>
		<td width="45" style="font-family: 黑体;font-size: 12px;">数量</td>
		<td width="80" align="center" style="font-family: 黑体;font-size: 12px;">仓库</td>
		<!-- <td width="143" align="center" style="font-family: 黑体;font-size: 12px;">备注</td> -->
	</tr>
	<tr>
		<td align="right">啊</td>
		<td align="right">啊</td>
		<td align="left">啊</td>
		<td align="left">啊</td>
		<td align="left">啊</td>
		<td align="right">啊</td>
		<td align="right">啊</td>
	</tr>
	</table>
	<div style="font-size: 12px;">创建人: 徐成</div>
</div>

<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />
<script type="text/javascript" src="chukudanPrint.js"></script>
</body>

</html>