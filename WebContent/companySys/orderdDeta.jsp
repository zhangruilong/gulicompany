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
			<label> <span>商品数量 :</span> <input id="orderdnum" type="text"
				name="orderdnum" placeholder="商品数量" value="${requestScope.orderd.orderdnum } "/>
			</label> <label> <span>下单金额 :</span> <input id="orderdmoney" type="text"
				name="orderdmoney" placeholder="下单金额"  value="${requestScope.orderd.orderdmoney } "/>
			</label> <label> <span>实际金额 :</span> <input id="orderdrightmoney" type="text"
				name="orderdrightmoney" placeholder="实际金额"  value="${requestScope.orderd.orderdrightmoney } "/>
			</label> <label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存修改" onclick="editMoney()"/>
			</label>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		var orderdnum = $("#orderdnum").val();
		var orderdmoney = $("#orderdmoney").val();
		var orderdrightmoney = $("#orderdrightmoney").val();
		var money = parseInt(orderdmoney)/parseInt(orderdnum);
		var rightmoney = parseInt(orderdrightmoney)/parseInt(orderdnum);
		$("#orderdnum").change(function(){
			//自动计算金额
			orderdnum = $("#orderdnum").val();
			orderdmoney = parseInt(orderdnum)*parseInt(money);
			orderdrightmoney = parseInt(orderdnum)*parseInt(rightmoney);
			$("#orderdmoney").val(orderdmoney);
			$("#orderdrightmoney").val(orderdrightmoney);
		});
	})
	function editMoney(){
		var orderdmoney = $("#orderdmoney").val();
		var orderdrightmoney = $("#orderdrightmoney").val();
		var orderdmoney0 = '${requestScope.orderd.orderdmoney }';				//原来的下单金额
		var orderdrightmoney0 = '${requestScope.orderd.orderdrightmoney }';		//原来的实际金额
		var diffOrderdmoney = parseInt(orderdmoney0) - parseInt(orderdmoney);				//计算得到差价
		var diffOrderdrightmoney = parseInt(orderdrightmoney0) - parseInt(orderdrightmoney);
		$("#diffOrderdmoney").val(diffOrderdmoney);								//设置隐藏表单的值
		$("#diffOrderdrightmoney").val(diffOrderdrightmoney);
		document.forms[0].submit();												//提交表单
	}
	</script>
</body>
</html>