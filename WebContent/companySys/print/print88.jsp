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
		<td align="center" style="font-family: 黑体;font-size: 12px;min-width:25px;">备注</td>
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
			<td align="left" style="-webkit-text-size-adjust:none;font-size: 8px;">${ orderDetail.orderdtype == '买赠'?orderDetail.orderddetail:'' }${ orderDetail.orderdtype == '秒杀'?orderDetail.orderdtype:'' }</td>
		</tr>
	</c:forEach>
		
	</table>
	<table width="100%" border="1" cellspacing="0" cellpadding="3" style="border-top:0px;border-collapse: collapse;padding-top: 20px;font-family: 黑体;">
		<tr>
			<td width="98" align="center" colspan="2">合计金额</td>
			<td width="350" align="left" colspan="4" id="sum-money-ch">&nbsp;</td>
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
	<div style="font-size: 12px;">★ 本订单及系统由谷粒网提供支持</div>
</div>

<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function(){
	var chMone= chineseNumber('${requestScope.order.ordermrightmoney }');
	$('#sum-money-ch').html('&nbsp;'+chMone);
});
/** 
 * 数字转中文 
* @param dValue 
 * @returns 
 */ 
 function chineseNumber(dValue) { 
 var maxDec = 2; 
 // 验证输入金额数值或数值字符串： 
dValue = dValue.toString().replace(/,/g, ""); 
dValue = dValue.replace(/^0+/, ""); // 金额数值转字符、移除逗号、移除前导零 
if (dValue == "") { 
return "零元整"; 
 } // （错误：金额为空！） 
else if (isNaN(dValue)) { 
 return "错误：金额不是合法的数值！"; 
 } 
var minus = ""; // 负数的符号"-"的大写："负"字。可自定义字符，如"（负）"。 
var CN_SYMBOL = ""; // 币种名称（如"人民币"，默认空） 
if (dValue.length > 1) { 
 if (dValue.indexOf('-') == 0) { 
 dValue = dValue.replace("-", ""); 
minus = "负"; 
 } // 处理负数符号"-" 
if (dValue.indexOf('+') == 0) { 
 dValue = dValue.replace("+", ""); 
 } // 处理前导正数符号"+"（无实际意义） 
} 
 // 变量定义： 
var vInt = ""; 
var vDec = ""; // 字符串：金额的整数部分、小数部分 
var resAIW; // 字符串：要输出的结果 
var parts; // 数组（整数部分.小数部分），length=1时则仅为整数。 
var digits, radices, bigRadices, decimals; // 数组：数字（0~9——零~玖）；基（十进制记数系统中每个数字位的基是10——拾,佰,仟）；大基（万,亿,兆,京,垓,杼,穰,沟,涧,正）；辅币（元以下，角/分/厘/毫/丝）。 
var zeroCount; // 零计数 
var i, p, d; // 循环因子；前一位数字；当前位数字。 
var quotient, modulus; // 整数部分计算用：商数、模数。 
// 金额数值转换为字符，分割整数部分和小数部分：整数、小数分开来搞（小数部分有可能四舍五入后对整数部分有进位）。 
var NoneDecLen = (typeof (maxDec) == "undefined" || maxDec == null || Number(maxDec) < 0 || Number(maxDec) > 5); // 是否未指定有效小数位（true/false） 
parts = dValue.split('.'); // 数组赋值：（整数部分.小数部分），Array的length=1则仅为整数。 
if (parts.length > 1) { 
 vInt = parts[0]; 
 vDec = parts[1]; // 变量赋值：金额的整数部分、小数部分 
if (NoneDecLen) { 
 maxDec = vDec.length > 5 ? 5 : vDec.length; 
 } // 未指定有效小数位参数值时，自动取实际小数位长但不超5。 
var rDec = Number("0." + vDec); 
 rDec *= Math.pow(10, maxDec); 
 rDec = Math.round(Math.abs(rDec)); 
 rDec /= Math.pow(10, maxDec); // 小数四舍五入 
var aIntDec = rDec.toString().split('.'); 
if (Number(aIntDec[0]) == 1) { 
 vInt = (Number(vInt) + 1).toString(); 
 } // 小数部分四舍五入后有可能向整数部分的个位进位（值1） 
if (aIntDec.length > 1) { 
 vDec = aIntDec[1]; 
 } else { 
 vDec = ""; 
 } 
 } else { 
 vInt = dValue; 
 vDec = ""; 
if (NoneDecLen) { 
 maxDec = 0; 
 } 
 } 
 if (vInt.length > 44) { 
 return "错误：金额值太大了！整数位长【" + vInt.length.toString() + "】超过了上限——44位/千正/10^43（注：1正=1万涧=1亿亿亿亿亿，10^40）！"; 
 } 
 // 准备各字符数组 Prepare the characters corresponding to the digits: 
 digits = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); // 零~玖 
radices = new Array("", "拾", "佰", "仟"); // 拾,佰,仟 
bigRadices = new Array("", "万", "亿", "兆", "京", "垓", "杼", "穰", "沟", "涧", "正"); // 万,亿,兆,京,垓,杼,穰,沟,涧,正 
decimals = new Array("角", "分", "厘", "毫", "丝"); // 角/分/厘/毫/丝 
resAIW = ""; // 开始处理 
// 处理整数部分（如果有） 
if (Number(vInt) > 0) { 
 zeroCount = 0; 
 for (i = 0; i < vInt.length; i++) { 
 p = vInt.length - i - 1; 
 d = vInt.substr(i, 1); 
 quotient = p / 4; 
 modulus = p % 4; 
 if (d == "0") { 
zeroCount++; 
 } else { 
 if (zeroCount > 0) { 
 resAIW += digits[0]; 
 } 
 zeroCount = 0; 
 resAIW += digits[Number(d)] + radices[modulus]; 
 } 
 if (modulus == 0 && zeroCount < 4) { 
 resAIW += bigRadices[quotient]; 
 } 
 } 
 resAIW += "元"; 
 } 
 // 处理小数部分（如果有） 
for (i = 0; i < vDec.length; i++) { 
 d = vDec.substr(i, 1); 
 if (d != "0") { 
resAIW += digits[Number(d)] + decimals[i]; 
 } 
 } 
 // 处理结果 
if (resAIW == "") { 
resAIW = "零" + "元"; 
 } // 零元 
if (vDec == "") { 
resAIW += "整"; 
 } // …元整 
resAIW = CN_SYMBOL + minus + resAIW; // 人民币/负……元角分/整 
return resAIW; 
 }
</script>
</body>

</html>