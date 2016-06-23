<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
</head>

<body style="width:740px;height:400px;">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WindowPrint" 
name="WindowPrint" width="0"></OBJECT>

<script language="javascript" type="text/javascript">
function printpreview()
{
window.print()
}
</script>

<div>
	<table width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse;border-bottom:solid 1px black;">
		<tr>
			<td colspan="6" style="text-align: center;font-size: 33px;font-family: 黑体;border-left: hidden;border-top: hidden;border-bottom: hidden;">${requestScope.printCompany.companyshop }销售单</td>
		</tr>
		<tr style="height: 40px;">
			<td width="9%" style="text-align: center;font-family: 黑体;font-size: 12px;border-left: hidden;border-top: hidden;border-right: hidden;">订单编号：</td>
			<td width="19%" style="font-size: 12px;font-family: 黑体;border-left: hidden;border-top: hidden;">${requestScope.order.ordermcode }</td>
			<td width="9%" style="text-align: center;font-family: 黑体;font-size: 12px;border-left: hidden;border-top: hidden;border-right: hidden;">联系地址：</td>
			<td width="41%" style="font-size: 12px;font-family: 黑体;border-left: hidden;border-top: hidden;">${requestScope.printCompany.companyaddress }</td>
			<td width="9%" style="text-align: center;font-family: 黑体;font-size: 12px;border-left: hidden;border-top: hidden;border-right: hidden;">联系电话：</td>
			<td width="13%" style="font-size: 12px;font-family: 黑体;border-left: hidden;border-top: hidden;">${requestScope.printCompany.companyphone }</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="3">
		<tr>
			<td align="right" style="width: 75px;font-size: 12px;">客户名称：</td>
			<td>${requestScope.printCustomer.customershop }</td>
			<td  align="right" style="font-size: 12px;">联系人：</td>
			<td>${requestScope.order.ordermconnect }</td>
			<td align="right" style="font-size: 12px;">联系电话：</td>
			<td>${requestScope.order.ordermphone }</td>
		</tr>
		<tr>
			<td align="right" style="font-size: 12px;">联系地址：</td>
			<td colspan="3">${requestScope.order.ordermaddress }</td>
			<td align="right" style="font-size: 12px;">订单日期：</td>
			<td colspan="2" style=""><fmt:formatDate value="<%=new Date() %>" type="date"/></td>
		</tr>
	</table>
	
	<table class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;">
	<tr>
		<td width="29" align="center" style="font-family: 黑体;font-size: 12px;">序号</td>
		<td width="95" align="center" style="font-family: 黑体;font-size: 12px;">商品编号</td>
		<td width="" style="font-family: 黑体;font-size: 12px;">商品名称</td>
		<td width="79" align="center" style="font-family: 黑体;font-size: 12px;">规格</td>
		<td width="30" align="center" style="font-family: 黑体;font-size: 12px;">单位</td>
		<td width="30" style="font-family: 黑体;font-size: 12px;">数量</td>
		<td width="40" align="center" style="font-family: 黑体;font-size: 12px;">价格</td>
		<td width="53" style="font-family: 黑体;font-size: 12px;">下单金额</td>
		<td width="53" style="font-family: 黑体;font-size: 12px;">实际金额</td>
		<td align="center" style="font-family: 黑体;font-size: 12px;min-width:50px;">备注</td>
		<!-- <td width="143" align="center" style="font-family: 黑体;font-size: 12px;">备注</td> -->
	</tr>
	<c:forEach items="${requestScope.order.orderdList }" var="orderDetail" varStatus="sta">
		<tr>
			<td align="right">${sta.count }</td>
			<td align="right">${orderDetail.orderdcode }</td>
			<td style="text-align: left;">${orderDetail.orderdname }</td>
			<td align="left">${orderDetail.orderdunits }</td>
			<td align="left">${orderDetail.orderdunit }</td>
			<td align="right">${orderDetail.orderdnum }</td>
			<td align="right">${orderDetail.orderdprice }</td>
			<td align="right">${orderDetail.orderdmoney }</td>
			<td align="right">${orderDetail.orderdrightmoney }</td>
			<td align="left" style="-webkit-text-size-adjust:none;font-size: 8px;">${ orderDetail.orderdtype == '买赠'?orderDetail.orderddetail:'' }</td>
		</tr>
	</c:forEach>
	</table>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;">￥${requestScope.order.ordermrightmoney }</span>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;font-size: 12px;">实际应收货款:</span>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;">￥${requestScope.order.ordermmoney - requestScope.order.ordermrightmoney }</span>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;font-size: 12px;">优惠金额：</span>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;">￥${requestScope.order.ordermmoney }</span>
	<span style="font-family: 黑体;float: right;padding-top: 3px;padding-right: 40px;padding-bottom: 3px;font-size: 12px;">下单金额合计：</span>
	<table width="100%" border="1" cellspacing="0" cellpadding="3" style="background-color: #80ffff;border-collapse: collapse;padding-top: 20px;font-family: 黑体;">
	<tr>
		<td width="111" align="center" style="font-size: 12px;">客户签收</td>
		<td align="center" style="font-size: 12px;">签收说明</td>
		<td width="120" align="center" style="font-size: 12px;">实收货款(${requestScope.order.ordermway == '货到付款'?'现金':'在线支付' })</td>
		<td width="79" align="center" style="font-size: 12px;">支付方式</td>
	</tr>
	<tr height="30px">
		<td></td>
		<td></td>
		<td></td>
		<td align="center">${requestScope.order.ordermway }</td>
	</tr>
	</table>
	<table cellpadding="7" style="width:100%; margin-left: 10px;padding-top: 5px;font-family: 黑体;">
	<tr>
		<td style="width: 24%;font-size: 12px;">制单员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td style="width: 24%;font-size: 12px;">配货员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td style="width: 24%;font-size: 12px;">配送员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td style="width: 24%;font-size: 12px;">审核员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
	</tr>
	</table>
	<div style="font-size: 12px;">★ 本订单及系统由谷粒网提供支持</div>
</div>

<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />

</body>

</html>