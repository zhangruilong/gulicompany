<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印</title>
</head>

<body style="width:675px;">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WindowPrint" 
name="WindowPrint" width="0"></OBJECT>

<script language="javascript" type="text/javascript">
function printpreview()
{
//WindowPrint.execwb(7,1);
//wb.execwb(7,1);
window.print()
}
</script>

<div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border: thin;border-collapse: collapse;">
		<tr style="height: 110px;">
			<td colspan="2" style="text-align: center;font-size: 33px;font-family: 黑体;border-left: hidden;border-top: hidden;border-bottom: hidden;">《谷粒网物流配送单》</td>
			<td colspan="2" style="background-color: #80ffff;">
				<div style="margin-top: 10px;">供货商:${requestScope.printCompany.companyshop }</div>
				<div style="margin-top: 10px;">联系电话:${requestScope.printCompany.companyphone }</div>
				<div style="margin-top: 10px;">${requestScope.printCompany.companydetail }</div>
			</td>
		</tr>
		<tr style="height: 40px;">
			<td width="15%" style="text-align: center;font-family: 黑体;border-left: hidden;border-top: hidden;border-right: hidden;">订单编号：</td>
			<td width="35%" style="font-family: 黑体;border-left: hidden;border-top: hidden;">${requestScope.order.ordermcode }</td>
			<td width="15%" style="background-color: #80ffff;font-family: 黑体;text-align: center;">配送员：</td>
			<td style="background-color: #80ffff;"></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="3" style="padding-top: 20px;">
		<tr>
			<td width="100" align="right">店名：</td>
			<td>${requestScope.printCustomer.customershop }</td>
			<td width="100" align="right">联系人：</td>
			<td>${requestScope.order.ordermconnect }</td>
			
		</tr>
		<tr>
			<td align="right">手机：</td>
			<td>${requestScope.order.ordermphone }</td>
			<td align="right">地址：</td>
			<td colspan="3">${requestScope.order.ordermaddress }</td>
		</tr>
		<tr>
			<td width="100" align="right">下单金额：</td>
			<td>${requestScope.order.ordermmoney }</td>
			<td align="right" style="background-color: #80ffff;">整单备注：</td>
			<td colspan="3" style="background-color: #80ffff;">${requestScope.order.ordermdetail }</td>
		</tr>
	</table>
	
	<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border: thin;border-collapse: collapse;padding-top: 20px;">
	<tr>
		<td style="font-family: 黑体;height: 25px;" colspan="11">订单详情:</td>
	</tr>
	<tr>
		<td width="35" align="center" style="font-family: 黑体;">序号</td>
		<td width="79" align="center" style="font-family: 黑体;">商品编号</td>
		<td width="170" align="center" style="font-family: 黑体;">菜品</td>
		<td width="40" align="center" style="font-family: 黑体;">价格</td>
		<td width="40" align="center" style="font-family: 黑体;">单位</td>
		<td width="99" align="center" style="font-family: 黑体;">规格</td>
		<td width="47" style="font-family: 黑体;">下单数量</td>
		<td width="47" style="font-family: 黑体;">下单金额</td>
		<!-- <td width="47" style="font-family: 黑体;">实际数量</td> -->
		<td width="47" style="font-family: 黑体;">实际金额</td>
		<td width="142" align="center" style="font-family: 黑体;background-color: #80ffff;">备注</td>
	</tr>
	<c:forEach items="${requestScope.order.orderdList }" var="orderDetail" varStatus="sta">
		<tr>
			<td align="right">${sta.count }</td>
			<td align="right">${orderDetail.orderdcode }</td>
			<td align="left">${orderDetail.orderdname }</td>
			<td align="right">${orderDetail.orderdprice }</td>
			<td align="left">${orderDetail.orderdunit }</td>
			<td align="left">${orderDetail.orderdunits }</td>
			<td align="right">${orderDetail.orderdnum }</td>
			<td align="right">${orderDetail.orderdmoney }</td>
			<td align="right">${orderDetail.orderdrightmoney }</td>
			<!-- <td align="right"></td> -->
			<td align="left">${orderDetail.orderddetail }</td>
		</tr>
	</c:forEach>
	</table>
	<span style="font-family: 黑体;float: right;padding-top: 13px;padding-right: 77px;padding-bottom: 13px;">￥${requestScope.order.ordermrightmoney }</span>
	<span style="font-family: 黑体;float: right;padding-top: 13px;padding-right: 77px;padding-bottom: 13px;">实际货款合计:</span>
	<table width="100%" border="1" cellspacing="0" cellpadding="3" style="background-color: #80ffff;border: thin;border-collapse: collapse;padding-top: 20px;font-family: 黑体;">
	<tr>
		<td width="111" align="center">客户签收</td>
		<td width="79" align="center">押筐情况(及金额)</td>
		<td align="center">签收情况(说明)</td>
		<td width="79" align="center">实收货款(现金)</td>
		<td width="79" align="center">支付方式</td>
	</tr>
	<tr height="55px">
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td align="center">${requestScope.order.ordermway }</td>
	</tr>
	<tr height="33px">
		<td align="center">备注:</td>
		<td colspan="4">便携周转箱押金100元个，归还后不影响使用将退回押金</td>
	</tr>
	</table>
	<table cellpadding="7" style="padding-top: 50px;font-family: 黑体;">
	<tr>
		<td>制单员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td>分拣员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td>配送员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
		<td>审核员:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
	</tr>
	</table>
</div>

<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />

</body>

</html>