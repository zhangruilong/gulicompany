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
			<td colspan="6" style="text-align: center;font-size: 33px;font-family: 黑体;border-left: hidden;border-top: hidden;border-bottom: hidden;">${requestScope.printCompany.companyshop }销售单(台账)</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="3">
		<tr>
			<td align="right" style="width: 85px;font-size: 12px;">购货单位：</td>
			<td>${requestScope.printCustomer.customershop }</td>
			<td  align="right" style="font-size: 12px;"></td>
			<td></td>
			<td align="right" style="width: 170px;font-size: 12px;">单号：</td>
			<td>${requestScope.order.ordermcode }</td>
		</tr>
		<tr>
			<td align="right" style="font-size: 12px;">地址电话：</td>
			<td colspan="3">${requestScope.order.ordermphone }</td>
			<td align="right" style="font-size: 12px;">日期：</td>
			<td colspan="2" style=""><fmt:formatDate value="<%=new Date() %>" type="date"/></td>
		</tr>
	</table>
	
	<table class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;">
	<tr>
		<td width="20" align="center" style="font-family: 黑体;font-size: 12px;"></td>
		<td width="95" align="center" style="font-family: 黑体;font-size: 12px;">代码</td>
		<td width="" align="center" style="font-family: 黑体;font-size: 12px;">商品名称</td>
		<td width="79" align="center" style="font-family: 黑体;font-size: 12px;">规格</td>
		<td width="30" style="font-family: 黑体;font-size: 12px;">数量</td>
		<td width="30" align="center" style="font-family: 黑体;font-size: 12px;">单位</td>
		<td width="40" align="center" style="font-family: 黑体;font-size: 12px;">单价</td>
		<td width="53" style="font-family: 黑体;font-size: 12px;">实际金额</td>
	</tr>
	<c:forEach items="${requestScope.order.orderdList }" var="orderDetail" varStatus="sta">
		<tr>
			<td align="center">${sta.count }</td>
			<td align="center">${orderDetail.orderdcode }</td>
			<td align="center">${orderDetail.orderdname }</td>
			<td align="center">${orderDetail.orderdunits }</td>
			<td align="center">${orderDetail.orderdnum }</td>
			<td align="center">${orderDetail.orderdunit }</td>
			<td align="center">${orderDetail.orderdprice }</td>
			<td align="center">${orderDetail.orderdrightmoney }</td>
		</tr>
	</c:forEach>
		
	</table>
	<table width="100%" border="1" cellspacing="0" cellpadding="3" style="border-top:0px;border-collapse: collapse;padding-top: 20px;font-family: 黑体;">
		<tr>
			<td width="98" align="center" colspan="2">合计金额</td>
			<td width="350" align="left" colspan="4">&nbsp;</td>
			<td width="150" align="left" colspan="2">&nbsp;${requestScope.order.ordermrightmoney }</td>
		</tr>
		<tr>
			<td align="center" colspan="2">摘&nbsp; &nbsp;要</td>
			<td align="left" colspan="6">${requestScope.order.ordermdetail }</td>
		</tr>
		<tr>
			<td align="center" rowspan="2">销货<br/>单位</td>
			<td align="center">名称地址</td>
			<td align="left" colspan="6">盛通粮油&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.printCompany.companyaddress }</td>
		</tr>
		<tr>
			<td align="center">联系电话</td>
			<td align="left" colspan="6">${requestScope.printCompany.companyphone}</td>
		</tr>
	</table>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">注明:</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">第一联(白):</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">存根联</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">第二联(蓝):</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">发票联</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">第三联(红):</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">客户联</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">第四联(黄):</span>
	<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">仓库联</span>
	<table cellpadding="7" style="width:100%; margin-left: 10px;font-family: 黑体;">
	<tr>
		<td style="width: 24%;font-size: 12px;">客户签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td style="width: 24%;font-size: 12px;">送货人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td style="width: 24%;font-size: 12px;">业务员:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td style="width: 24%;font-size: 12px;">开单人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	</table>
	<div style="font-size: 12px;">以上商品均已履行进货检查验收法定程序,检索票证齐全,供货者特此声明。</div>
	<div style="font-size: 12px;">此联由批发单位直接用于批发台账资料留存。</div>
</div>

<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />

</body>

</html>