<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../companySys/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
</head>
<body>
	<div class="largeCus_form">
		<h1>订单信息
			<div class="tt_cusInfo">
				<span>商品名称:&nbsp;</span>${requestScope.editPriGoods.goodsname }&nbsp;&nbsp;
				<span>编码:&nbsp;</span>${requestScope.editPriGoods.goodscode }&nbsp;&nbsp;
				<span>规格:&nbsp;</span>${requestScope.editPriGoods.goodsunits }
			</div>
			</h1>
		<input type="button" value=" + " class="LCF_button">
		<table>
			<tr>
				<td><label><span>编码 :</span><input id="timegoodscode" type="text"
				name="timegoodscode" value="${requestScope.editTimeGoods.timegoodscode }" placeholder="编码" /></label></td>
				<td><label><span>编码 :</span><input id="timegoodscode" type="text"
				name="timegoodscode" value="${requestScope.editTimeGoods.timegoodscode }" placeholder="编码" /></label></td>
				<td><label><span>编码 :</span><input id="timegoodscode" type="text"
				name="timegoodscode" value="${requestScope.editTimeGoods.timegoodscode }" placeholder="编码" /></label></td>
			</tr>
		</table>
		<span>&nbsp;</span> <input type="button" class="LCF_button" value="保存" onclick="addData()"/> 
			<input style="margin-left: 30px;" type="button" class="LCF_button" value="返回" onclick="javascript:window.history.back()"/>
	</div>
</body>
</html>