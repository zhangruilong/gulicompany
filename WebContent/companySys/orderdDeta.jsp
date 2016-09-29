<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/formsty.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="elegant-aero">
		<form action="editOrderd.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="orderdid" value="${requestScope.orderd.orderdid }">
		<input type="hidden" name="ordermcompany" value="${sessionScope.company.companyid }">
 		<input type="hidden" name="ordermid" value="${requestScope.order.ordermid }">
 		<input type="hidden" id="diffOrderdmoney" name="diffOrderdmoney" value="">
 		<input type="hidden" id="diffOrderdrightmoney" name="diffOrderdrightmoney" value="">
			<h1>修改订单商品
				<span>商品名称 : ${requestScope.orderd.orderdname }&nbsp;&nbsp;&nbsp;&nbsp;单位 : ${requestScope.orderd.orderdunit }&nbsp;&nbsp;&nbsp;&nbsp;规格 : ${requestScope.orderd.orderdunits }</span>
			</h1>
			<label> <span>商品数量 :</span> <input id="orderdnum" type="number"
				name="orderdnum" placeholder="商品数量" value="${requestScope.orderd.orderdnum }"/>
			</label> <label> <span>下单金额 :</span> <input id="orderdmoney" type="number"
				name="orderdmoney" placeholder="下单金额"  value="${requestScope.orderd.orderdmoney }"/>
			</label> <label> <span>实际金额 :</span> <input id="orderdrightmoney" type="number"
				name="orderdrightmoney" placeholder="实际金额"  value="${requestScope.orderd.orderdrightmoney }"/>
			</label> <label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存修改" onclick="editMoney()"/>
			</label>
		</form>
	</div>
	<script type="text/javascript">
	var money = '${requestScope.orderd.orderdprice }';							//价格
	var orderdnum = $("#orderdnum").val();									//商品数量
	var orderdmoney = $("#orderdmoney").val();								//下单金额
	var orderdrightmoney = $("#orderdrightmoney").val();					//实际金额
	$(function(){
		$("#orderdnum").blur(function(){
			//自动计算金额
			var currNum = $("#orderdnum").val();									//得到当前输入的数量 
			orderdnum = currNum;
			orderdmoney = parseFloat(parseFloat(orderdnum)*parseFloat(money)).toFixed(2);					//下单金额和实际金额 相等
			orderdrightmoney = orderdmoney;
			$("#orderdmoney").val(orderdmoney);
			$("#orderdrightmoney").val(orderdrightmoney);
		});
	})
	//修改
	function editMoney(){
		orderdmoney = $("#orderdmoney").val();
		orderdrightmoney = $("#orderdrightmoney").val();
		var orderdmoney0 = '${requestScope.orderd.orderdmoney }';				//原来的下单金额
		var orderdrightmoney0 = '${requestScope.orderd.orderdrightmoney }';		//原来的实际金额
		var diffOrderdmoney = parseFloat(orderdmoney0).toFixed(2) - parseFloat(orderdmoney).toFixed(2);				//计算得到差价
		var diffOrderdrightmoney = parseFloat(orderdrightmoney0).toFixed(2) - parseFloat(orderdrightmoney).toFixed(2);
		$("#diffOrderdmoney").val(parseFloat(diffOrderdmoney).toFixed(2));								//设置隐藏表单的值
		$("#diffOrderdrightmoney").val(parseFloat(diffOrderdrightmoney).toFixed(2));
		//return;
		document.forms[0].submit();												//提交表单
	}
	</script>
</body>
</html>