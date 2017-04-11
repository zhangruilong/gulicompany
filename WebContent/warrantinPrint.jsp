<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String username = info.getUsername();
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

<script type="text/javascript" src="zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>

</head>

<body style="width:740px;height:400px;">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WindowPrint" 
name="WindowPrint" width="0"></OBJECT>

<div>
	<table width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse;border-bottom:solid 1px black;">
		<tr>
			<td colspan="6" style="text-align: center;font-size: 33px;font-family: 黑体;border-left: hidden;border-top: hidden;border-bottom: hidden;line-height: 40px;">海盐天然粮油有限公司出库单</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="3">
		<tr>
			<td width="75" align="right" style="font-size: 12px;">供应商：</td>
			<td width="295" id=info-supp></td>
			<td width="75" align="right" style="font-size: 12px;">联系方式：</td>
			<td width="295" id="info-phone"></td>
		</tr>
		<tr>
			<td width="75" align="right" style="font-size: 12px;">联系人：</td>
			<td width="295" id="info-contact"></td>
			<td width="75" align="right" style="font-size: 12px;">打印日期：</td>
			<td width="295" id="info-date"></td>
		</tr>
	</table>
	
	<table class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;">
	<tr>
		<td width="29" align="center" style="font-family: 黑体;font-size: 12px;">序号</td>
		<td width="168" align="center" style="font-family: 黑体;font-size: 12px;">商品编号</td>
		<td width="" style="font-family: 黑体;font-size: 12px;">商品名称</td>
		<td width="110" align="center" style="font-family: 黑体;font-size: 12px;">规格</td>
		<td width="45" align="center" style="font-family: 黑体;font-size: 12px;">仓库</td>
		<td width="50" style="font-family: 黑体;font-size: 12px;">进货单价</td>
		<td width="45" align="center" style="font-family: 黑体;font-size: 12px;">数量</td>
		<td width="60" align="center" style="font-family: 黑体;font-size: 12px;">进货金额</td>
		<td width="72" align="center" style="font-family: 黑体;font-size: 12px;">核验人</td>
		<td width="" align="center" style="font-family: 黑体;font-size: 12px;">备注</td>
		<!-- <td width="143" align="center" style="font-family: 黑体;font-size: 12px;">备注</td> -->
	</tr>
	<!-- <tr>
		<td align="right">啊</td>
		<td align="right">啊</td>
		<td align="left">啊</td>
		<td align="left">啊</td>
		<td align="left">啊</td>
		<td align="right">啊</td>
		<td align="right">啊</td>
	</tr> -->
	</table>
	<div style="font-size: 12px;" id='creat-or'>创建人: </div>
</div>

<div style="float: left;">
<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />
</div>
<script type="text/javascript">
var username = '<%=username%>';
var json = '${param.json}';
var data = JSON.parse(json);
var name = '${param.name}';
var contact = '${param.contact}';
var phone = '${param.phone}';
$(function(){
	$('#creat-or').text('创建人：'+username);
	$('#info-supp').text(name);	//打印日期
	$('#info-phone').text(phone);	//订单编号
	$('#info-contact').text(contact);		//购买单位
	$('#info-date').text(Ext.util.Format.date(new Date(),'Y-m-d'));		//领货人
	$.each(data,function(i,item){
		$('.print_tab').append('<tr>'+
			'<td align="right">'+(i+1)+'</td>'+
			'<td align="right">'+item.goodscode+'</td>'+
			'<td align="left">'+item.goodsname+'</td>'+
			'<td align="left">'+item.goodsunits+'</td>'+
			'<td align="right">'+item.storehousename+'</td>'+
			'<td align="right">'+item.warrantinprice+'</td>'+
			'<td align="right">'+item.warrantinnum+'</td>'+
			'<td align="right">'+item.warrantinmoney+'</td>'+
			'<td align="right">'+item.warrantinwho+'</td>'+
			'<td align="right">'+item.warrantindetail+'</td>'+
		'</tr>');
	});
})

</script>
</body>

</html>