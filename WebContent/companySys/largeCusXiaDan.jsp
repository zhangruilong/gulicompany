<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../companySys/css/style.css" rel="stylesheet" type="text/css" />
<link href="../companySys/css/tabsty.css" rel="stylesheet" type="text/css" >
<link href="css/dig.css" rel="stylesheet" type="text/css">
<style type="text/css">
.bordered tr td {
	padding: 5px;
	padding-left: 3px;
	padding-right: 3px;
}
.bordered tr th{
	padding: 5px;
	padding-left: 1px;
	padding-right: 1px;
}
.elegant-aero{
	margin-top: 0;
	overflow: auto;
}
.fenyelan_input{
	width: 22px;
	text-align: center;
}
.fenyelan_button{
	width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;
}
.goods_select_popup{
	margin:0px auto;
	margin-top:1%;
	width: 700px;
	background-color: #FBF1E5;
}
.elegant-aero p input[type="checkbox"]{
	vertical-align:middle
}
</style>
</head>
<body>
	<div class="largeCus_form">
		<h1>订单信息
			<div class="tt_cusInfo">
				客户名称:<span></span>
				类型:<span></span>
				等级:<span></span>
			</div>
		</h1>
		<input type="button" value="地址" class="LCF_button" onclick="showCusAddress()">
		<div class="LCXD_CusAddress">
			联系人:<span></span>
			联系方式:<span></span>
			地址:<span></span>
		</div>
		<input type="button" value="商品" onclick="selectGoods();" class="LCF_button">
		
		<table class="bordered">
			<tr>
				<th>序号</th>
				<th>编码</th>
				<th>类型</th>
				<th>名称</th>
				<th>规格</th>
				<th>价格</th>
				<th>单位</th>
				<th>数量</th>
				<th>下单金额</th>
				<th>实际金额</th>
				<th>操作</th>
			</tr>
			<tr>
				<td name="ord_info_td" colspan="15"><div class="LCXD_OrdermInfo">
					种类数:<span>0</span>
					下单金额:<span>0</span>
					实际金额:<span>0</span>
					支付方式:<span>货到付款</span>
				</div></td>
			</tr>
		</table>
		<span>&nbsp;</span> <input type="button" class="LCF_button" value="保存" onclick="saveOrder()"/> 
			<input style="margin-left: 30px;" type="button" class="LCF_button" value="返回" onclick="javascript:window.history.back()"/>
	</div>
<!--弹框-->
<div class="cd-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="goodscode" name="goodscode" value="">
<input class="button" type="button" value="查询" onclick="queryGooods()">
<input class="button" type="button" value="关闭" onclick="closeCdPopup()">
</div>
	<table class="bordered" id="goods_LCXD" style="margin: 0 auto">
		<thead>
		<tr>
	    	<th>序号</th>
			<th>商品编码</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>价格</th>
			<th>单位</th>
			<th>点击选择</th>
		</tr>
	    </thead>
	</table>
</div>
</div>
<!--弹框-->
<div class="cd-popup2" role="alert">
	<table class="bordered" id="address_LCXD" style="margin: 5% auto 0 auto;">
		<thead>
		<tr>
	    	<th>序号</th>
	    	<th>联系人</th>
	    	<th>联系方式</th>
	    	<th>地址</th>
	    	<th>是否默认</th>
	    	<th>点击选择</th>
		</tr>
	    </thead>
	</table>
</div>
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var customerid = '${param.customerid}';
$(function(){
	$.post("largeCusXiaDanInfo.action",{"ccustomerid":"${param.ccustomerid}","addresscustomer":"${param.customerid}"},function(data){
		var strJSON = JSON.stringify(data);
		$(".tt_cusInfo").append('<span hidden="true">'+strJSON+'</span>');
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
	$(".LCXD_OrdermInfo span").dblclick(function(){
		$(this).attr("contentEditable","true");
		$(this).css("background-color","white");
	})
	$(".LCXD_OrdermInfo span").blur(function(){
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
	});
	tabEdit();			//绑定双击事件可编辑
});
//绑定双击事件可编辑
function tabEdit(){
	$(".largeCus_form td[name!='ord_info_td']").dblclick(function(){
		$(this).attr("contentEditable","true");
		$(this).css("background-color","white");
	});
	$(".largeCus_form td[name!='odd_num'][name!='odd_num']").blur(function(){
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
	});
	$(".largeCus_form td[name='odd_num']").blur(function(){
		var pric = parseFloat($(this).prev().prev().text()).toFixed(2);
		var num = parseFloat($(this).text()).toFixed(2);
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
		$(this).next()
	});
}
//保存订单
function saveOrder(){
	var orderCCus = JSON.parse($(".tt_cusInfo span[hidden='true']").text());
	$.post("largeCusOrderSave.action",{
		"ordermcustomer":customerid,
		"ordermcompany":orderCCus.ccustomercompany,
		"ordermnum":$(".LCXD_CusAddress span:eq(0)").text(),
		"ordermmoney":$(".LCXD_CusAddress span:eq(1)").text(),
		"ordermrightmoney":$(".LCXD_CusAddress span:eq(2)").text()
	});
}
//选择商品
function selectGoods(){
	$(".cd-popup").addClass("is-visible");	//弹出商品窗口
	loadGoodsData(1);
	
}
//商品分页
function fenyeGoods(targetPage){
	loadGoodsData(targetPage);
}
//关闭商品选择窗口
function closeCdPopup(){
	$(".cd-popup").removeClass("is-visible");
}
//加载商品数据
function loadGoodsData(pagenowGoods){
	$.post("getlargeCusGoods.action",{"goodscompany":"${param.ccustomercompany}","pagenowGoods":pagenowGoods},function(data){
		$("#goods_LCXD tr:gt(0)").remove();
		$.each(data.goodsList,function(i,item){
			var strJSON = JSON.stringify(item);
			var price = 0;													//商品价格
			var unit = '';													//商品单位
			if(!item.largecuspriceList && item.largecuspriceList.length != 0){
				price = item.largecuspriceList[0].largecuspriceprice;
				unit = item.largecuspriceList[0].largecuspriceunit;
			} else {
				price = item.pricesList[0].pricesprice;
				unit = item.pricesList[0].pricesunit;
			}
			$("#goods_LCXD").append('<tr>'+
					'<td>'+(i+1)+'</td>'+
					'<td>'+item.goodscode+'</td>'+
					'<td>'+item.goodsname+'</td>'+
					'<td>'+item.goodsunits+'</td>'+
					'<td>'+price+'</td>'+
					'<td>'+unit+'</td>'+
					'<td><a class="scant_a" onclick="seleGoods(\''+item.goodscode+
							'\',\''+'商品'+
							'\',\''+item.goodsname+
							'\',\''+item.goodsunits+
							'\',\''+price+
							'\',\''+unit+
							'\','+'this'+
					')">选择</a><span hidden="true">'+strJSON+'</span></td></td>'+
				'</tr>');
		});
		var goodsfenye = '<tr><td colspan="7">';
		if(data.pagenowGoods > 1){
			goodsfenye += '<a onclick=fenyeGoods("1")>第一页</a><a onclick=fenyeGoods("'+(parseInt(data.pagenowGoods)-1)+'")>上一页</a>';
			
		} else {
			goodsfenye += '<span>第一页</span><span>上一页</span>';
		}
		goodsfenye += '<span>当前第'+data.pagenowGoods+'页</span>';
		if(data.pagenowGoods < data.pageCountGoods){
			goodsfenye += '<a onclick=fenyeGoods("'+(parseInt(data.pagenowGoods)+1)+'")>下一页</a><a onclick=fenyeGoods("'+data.pageCountGoods+'")>最后一页</a>';
		} else {
			goodsfenye += '<span>下一页</span><span>最后一页&nbsp;</span>';
		}
		goodsfenye += '<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="pagenowGoods" value="'+
			data.pagenowGoods+'">页</span>'+
		 	'<input onclick=goodsPageTo("'+data.pageCountGoods+'") type="button" value="GO" class="fenyelan_button">'+
	 		'<span>一共 '+data.countGoods+' 条数据</span>';
		$("#goods_LCXD").append(goodsfenye);
	});
}
//自动改变商品序号
function goodsAutoSort(){
	$.each($(".largeCus_form table tr"),function(i,item){
		if(i < $(".largeCus_form table tr").length-1){
			$(item).children("td:eq(0)").text(i);
		}
	});
}
//选择商品
function seleGoods(goodscode,orderdtype,goodsname,goodsunits,price,unit,obj){
	var seleStrJson = $(obj).next().text();
	
	$(".largeCus_form table tr:eq(0)").after('<tr>'+
			'<td>'+'</td>'+
			'<td>'+goodscode+'</td>'+
			'<td>'+orderdtype+'</td>'+
			'<td>'+goodsname+'</td>'+
			'<td>'+goodsunits+'</td>'+
			'<td>'+price+'</td>'+
			'<td>'+unit+'</td>'+
			'<td name="odd_num">1</td>'+
			'<td>'+price+'</td>'+
			'<td>'+price+'</td>'+
			'<td><a onclick="deleteRows(this)">删除</a><span hidden="true">'+seleStrJson+'</span></td>'+
		'</tr>');
	$(".LCXD_OrdermInfo span:eq(0)").text(parseInt($(".LCXD_OrdermInfo span:eq(0)").text())+1);
	$(".LCXD_OrdermInfo span:eq(1)").text((parseFloat($(".LCXD_OrdermInfo span:eq(1)").text())+parseFloat(price)).toFixed(2));
	$(".LCXD_OrdermInfo span:eq(2)").text((parseFloat($(".LCXD_OrdermInfo span:eq(2)").text())+parseFloat(price)).toFixed(2));
	goodsAutoSort();								//自动编号
	closeCdPopup();				
	tabEdit();										//绑定事件
}
//删除订单商品
function deleteRows(obj){
	var price = parseFloat($(obj).parent().prev().text());
	$(".LCXD_OrdermInfo span:eq(0)").text(parseInt($(".LCXD_OrdermInfo span:eq(0)").text())-1);		//商品种类数减一
	$(".LCXD_OrdermInfo span:eq(1)").text((parseFloat($(".LCXD_OrdermInfo span:eq(1)").text())-price).toFixed(2));
	$(".LCXD_OrdermInfo span:eq(2)").text((parseFloat($(".LCXD_OrdermInfo span:eq(2)").text())-price).toFixed(2));
	$(obj).parent().parent().remove();
	goodsAutoSort();								//自动编号
}
//显示地址
function showCusAddress(){
	$(".cd-popup2").addClass("is-visible");
	$.post("queryLargeCusAllAddress.action",{"customerid":customerid},function(data){
		$("#address_LCXD tr:gt(0)").remove();
		$.each(data,function(i,item){
			var isDef = '';
			if(item.addressture == '1'){
				isDef = '默认';
			}
			$("#address_LCXD").append('<tr>'+
					'<td>'+(i+1)+'</td>'+
					'<td>'+item.addressconnect+'</td>'+
					'<td>'+item.addressphone+'</td>'+
					'<td>'+item.addressaddress+'</td>'+
					'<td>'+isDef+'</td>'+
					'<td><a class="scant_a" onclick="selectCusAddress(\''+item.addressconnect+
							'\',\''+item.addressphone+
							'\',\''+item.addressaddress+
					'\')">选择</a></td></td>'+
				'</tr>');
		});
	});
}
//选择地址
function selectCusAddress(addressconnect,addressphone,addressaddress){
	$(".LCXD_CusAddress span:eq(0)").text(addressconnect);
	$(".LCXD_CusAddress span:eq(1)").text(addressphone);
	$(".LCXD_CusAddress span:eq(2)").text(addressaddress);
	$(".cd-popup2").removeClass("is-visible");
}
</script>
</body>
</html>

















