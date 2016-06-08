<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../companySys/css/style.css" rel="stylesheet" type="text/css" />
<link href="../companySys/css/tabsty.css" rel="stylesheet" type="text/css" >
<link href="css/dig.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="largeCus_form">
		<h1>订单信息
			<div class="tt_cusInfo">
				客户名称:<span>陶璃超</span>
				类型:<span>餐饮客户</span>
				等级:<span>1</span>
			</div>
		</h1>
		<input type="button" value="地址" class="LCF_button">
		<div class="LCXD_CusAddress">
			联系人:<span>陶璃超</span>
			联系方式:<span>18207194276</span>
			地址:<span>xx路xx号范德萨范德萨范德萨</span>
		</div>
		<input type="button" value="商品" onclick="selectGoods();" class="LCF_button">
		<table class="bordered">
			<tr>
				<th>编码</th>
				<th>类型</th>
				<th>名称</th>
				<th>规格</th>
				<th>价格</th>
				<th>单位</th>
				<th>数量</th>
				<th>下单金额</th>
				<th>实际金额</th>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<span>&nbsp;</span> <input type="button" class="LCF_button" value="保存" onclick="addData()"/> 
			<input style="margin-left: 30px;" type="button" class="LCF_button" value="返回" onclick="javascript:window.history.back()"/>
	</div>
<!--弹框-->
<div class="cd-popup" role="alert">
	<table class="bordered" id="scant" style="margin: 5% auto 0 auto;">
		<thead>
	    <tr>
	        <th>序号</th>
			<th>商品编码</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>小类名称</th>
			<th>点击选择</th>
	    </tr>
	    </thead>
	</table>
</div>
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$.post("largeCusXiaDanInfo.action",{"ccustomerid":"${param.ccustomerid}","addresscustomer":"${param.customerid}"},function(data){
		//客户信息
		$(".tt_cusInfo span:eq(0)").text(data.largeCCus.customer.customershop);
		if(data.largeCCus.customer.customertype == 1){
			$(".tt_cusInfo span:eq(1)").text("组织单位客户");
		} else if(data.largeCCus.customer.customertype == 2){
			$(".tt_cusInfo span:eq(1)").text("商超客户");
		} else if(data.largeCCus.customer.customertype == 3){
			$(".tt_cusInfo span:eq(1)").text("餐饮客户");
		}
		$(".tt_cusInfo span:eq(2)").text(data.largeCCus.ccustomerdetail);
		//地址
		if(!data.cusAddress){
			alert("此客户还没有地址，请先添加客户地址。");
			history.go(-1);
		} else {
			$(".LCXD_CusAddress span:eq(0)").text(data.cusAddress.addressconnect);
			$(".LCXD_CusAddress span:eq(1)").text(data.cusAddress.addressphone);
			$(".LCXD_CusAddress span:eq(2)").text(data.cusAddress.addressaddress);
		}
		
	})
});

//选择商品
function selectGoods(){
	$.post("");
}
</script>
</body>
</html>

















