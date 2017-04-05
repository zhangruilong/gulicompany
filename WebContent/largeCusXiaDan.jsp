<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	String username = info.getUsername();
	%>
<%@ include file="../../zrlextpages/common/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/tabsty.css" rel="stylesheet" type="text/css" >
<link href="css/dig.css" rel="stylesheet" type="text/css">
<style type="text/css">
.bordered{margin: 15px 0px 15px 10px;}
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
	height: 381px;
	background-color: #FFFFFF;
}
.elegant-aero p input[type="checkbox"]{
	vertical-align:middle
}
.cd-popup .goods_select_popup .navigation .sel-goo-input{
	border:1px solid #A9A9A9;
}
.navigation{
	height: 30px;
}
.close-button {
	border: 0;
    background-image: url(zrlextpages/common/sencha/build/classic/theme-crisp/resources/images/tools/tool-sprites.png);
	background-position: 0 0;
	background-color: white;
	width: 16px;
	height: 16px;
	font-size: 16px;
	color: #666666;
	cursor:pointer;
	margin: 0px 0px 0px 10px;
	border-radius: 5px;
	float: right;
	
}
</style>
</head>
<body>
	<div class="largeCus_form">
			<h1>录单信息
			<div class="tt_cusInfo">
				客户名称:<span></span>
				类型:<span></span>
				等级:<span></span>
			</div>
		</h1>
		<input type="button" value="收货信息" class="LCF_button" onclick="showCusAddress()">
		<div class="LCXD_CusAddress">
			联系人:<span></span>
			联系方式:<span></span>
			地址:<span></span>
		</div>
		<input type="button" value="选择商品" onclick="selectGoods();" class="LCF_button">
		
		<table class="bordered">
			<tr name="ordLi_tr1">
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
			<tr name="ord_info_tr">
				<td name="ord_info_td" colspan="15"><div class="LCXD_OrdermInfo">
					种类数:<span>0</span>
					下单金额:<span>0</span>
					实际金额:<span>0</span>
					支付方式:<span name="OdInfo_ZFFS">货到付款</span>
				</div></td>
			</tr>
		</table>
		<span>&nbsp;</span> <input type="button" class="LCF_button" value="生成订单" onclick="saveOrder()"/> 
			<input style="margin-left: 30px;" type="button" class="LCF_button" value="返回" onclick="javascript:window.history.back()"/>
	</div>
<!--弹框-->
<div class="cd-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="goodscode" name="goodscode" class="sel-goo-input" value="">
<input class="query-button" type="button" value="查询" onclick="loadGoodsData()">
<input class="close-button" type="button" value="" onclick="closeCdPopup()">
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
<script type="text/javascript" src="zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/common2.js"></script>
<script type="text/javascript">
var customerid = getUrlParam('customerid');
var comid = '<%=comid%>';
var username = '<%=username%>';
$(function(){
	$.ajax({
		url: "CPCcustomerviewAction.do?method=queryCusAndAddress",
		type: "post",
		data: {"customerid":customerid},
		success: function(resp){
			var data = eval('('+resp+')');
			if(data.code!=202){
				alert(data.msg);
				history.go(-1);
			}
			var strJSON = JSON.stringify(data);
			$(".tt_cusInfo").append('<span class="cusInfo-jsonInfo" hidden="true">'+strJSON+'</span>');
			//客户信息
			$(".tt_cusInfo span:eq(0)").text(data.customerInfo.customershop);
			if(data.customerInfo.customertype == 1){
				$(".tt_cusInfo span:eq(1)").text("组织单位客户");
			} else if(data.customerInfo.customertype == 2){
				$(".tt_cusInfo span:eq(1)").text("商超客户");
			} else if(data.customerInfo.customertype == 3){
				$(".tt_cusInfo span:eq(1)").text("餐饮客户");
			}
			$(".tt_cusInfo span:eq(2)").text(data.customerInfo.ccustomerdetail);
			//地址
			if(!data.address){
				alert("此客户还没有地址，请先添加客户地址。");
				history.go(-1);
			} else {
				$(".LCXD_CusAddress span:eq(0)").text(data.address.addressconnect);
				$(".LCXD_CusAddress span:eq(1)").text(data.address.addressphone);
				$(".LCXD_CusAddress span:eq(2)").text(data.address.addressaddress);
			}
		},
		error : function(resp){
			var data = eval('('+resp+')');
			alert(data.msg);
		}
	});
	$(".LCXD_OrdermInfo span").dblclick(function(){
		$(this).attr("contentEditable","true");
		$(this).css("background-color","white");
		$(this).focus();
	});
	$(".LCXD_OrdermInfo span").blur(function(){
		var numb = parseFloat($(this).text());
		if($(this).attr("name") != "OdInfo_ZFFS" && isNaN(numb)){
			alert("只能输入数字");
			$(this).text("0000");
		}
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
	});
	tabEdit();			//绑定双击事件可编辑
});
//得到总下单金额和总实际金额
function showXDMoneyAndSJMoney(){
	var xdMoney = 0;
	var sjMoney = 0;
	$(".largeCus_form td[name='xd_money']").each(function(i,item){
		xdMoney += parseFloat($(item).text());
	});
	$(".largeCus_form td[name='sj_money']").each(function(i,item){
		sjMoney += parseFloat($(item).text());
	});
	$(".LCXD_OrdermInfo span:eq(1)").text(xdMoney.toFixed(2));
	$(".LCXD_OrdermInfo span:eq(2)").text(sjMoney.toFixed(2))
}
//绑定双击事件可编辑
function tabEdit(){
	$(".largeCus_form td[name!='ord_info_td']").dblclick(function(){
		$(this).attr("contentEditable","true");
		$(this).css("background-color","white");
		$(this).focus();
	});
	$(".largeCus_form td:not([name^='odd_']):not([name$='_money'])").blur(function(){
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
	});
	//下单数量
	$(".largeCus_form td[name='odd_num']").blur(function(){
		var num = parseInt($(this).text());
		if(isNaN(num)){
			alert("只能输入数字");
			$(this).text(1);
			num = 1;
		}
		var pric = parseFloat($(this).prev().prev().text()).toFixed(2);
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
		$(this).next().text(pric*num);
		$(this).next().next().text(pric*num);
		showXDMoneyAndSJMoney();
	});
	//商品价格
	$(".largeCus_form td[name='odd_price']").blur(function(){
		var price = parseFloat($(this).text()).toFixed(2);
		var numobj = $(this).next().next();			//数量
		var monobj = numobj.next();					//下单金额
		var sjmonobj = monobj.next();				//实际金额
		if(isNaN(price)){
			alert("只能输入数字");
			if(monobj.text() && numobj.text()){
				var orprice = parseFloat(monobj.text()) / parseInt(numobj.text());
				$(this).text(orprice);
			} else {
				$(this).text("");
			}
		} else if(monobj.text() && numobj.text()){
			monobj.text(price * parseFloat(numobj.text()));								//下单金额
			sjmonobj.text(price * parseFloat(numobj.text()));
			showXDMoneyAndSJMoney();
		}
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
	});
	//实际金额
	$(".largeCus_form td[name='sj_money']").blur(function(){
		var sjmon = parseFloat($(this).text()).toFixed(2);				//实际金额
		var numobj = $(this).prev().prev();									//数量
		var price = numobj.prev().prev();									//价格
		
		if(isNaN(sjmon)){
			alert("只能输入数字");
			if(price.text() && numobj.text()){
				var ormon = parseFloat(price.text()) * parseInt(numobj.text());
				$(this).text(ormon);
			} else {
				$(this).text("");
			}
		}
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
		showXDMoneyAndSJMoney();
	});
	//下单金额
	$(".largeCus_form td[name='xd_money']").blur(function(){
		var xdmon = parseFloat($(this).text()).toFixed(2);				//下单金额
		var numobj = $(this).prev();									//数量
		var price = numobj.prev().prev();								//价格
		
		if(isNaN(xdmon)){
			alert("只能输入数字");
			if(price.text() && numobj.text()){
				var ormon = parseFloat(price.text()) * parseInt(numobj.text());
				$(this).text(ormon);
			} else {
				$(this).text("");
			}
		}
		$(this).attr("contentEditable","false");
		$(this).css("background-color","transparent");
		showXDMoneyAndSJMoney();
	});
}
//保存订单
function saveOrder(){
	if($(".largeCus_form tr[name!='ordLi_tr1'][name!='ord_info_tr']").length == 0){
		alert("至少要有一个订单商品");
		return;
	}
	var orderCCus = JSON.parse($(".tt_cusInfo span[hidden='true']").text());
	var orderdetjson = "[";
	$(".largeCus_form tr[name!='ordLi_tr1'][name!='ord_info_tr']").each(function(i,item){
		var goodsJson = JSON.parse($(item).find("td span[hidden='true']").text());
		var unit = "";
		if(typeof(goodsJson.largecuspriceunit) != "undefined" && goodsJson.largecuspriceunit){
			unit = goodsJson.largecuspriceunit;
		} else {
			unit = goodsJson.pricesunit;
		}
		orderdetjson += '{"orderdid":"'+i
			+ '","orderdcode":"' + goodsJson.goodscode
			+ '","orderdtype":"' + $(item).find("td:eq(2)").text()
			+ '","orderdname":"' + goodsJson.goodsname
			+ '","orderddetail":"' +'danpin'
			+ '","orderdunits":"' + goodsJson.goodsunits
			+ '","orderdprice":"' + $(item).find("td:eq(5)").text()
			+ '","orderdunit":"' + unit
			+ '","orderdweight":"' + goodsJson.goodsweight
			+ '","orderdnote":"' + ''								//订单备注
			+ '","orderdgoods":"' + goodsJson.goodsid				//商品id
			+ '","orderdbrand":"' + nullStr(goodsJson.goodsbrand)		//商品品牌
			+ '","orderdclass":"' + goodsJson.goodsclassname
			+ '","orderdnum":"' + $(item).find("td:eq(7)").text()
			+ '","orderdmoney":"' + $(item).find("td:eq(8)").text()
			+ '","orderdrightmoney":"' + $(item).find("td:eq(9)").text()
			+'"},';
	});
	orderdetjson = orderdetjson.substr(0, orderdetjson.length - 1) + ']';
	var ordermjson = '[{'
			+ '"ordermcustomer":"' + customerid
			+ '","ordermcompany":"' + orderCCus.customerInfo.ccustomercompany
			+ '","ordermnum":"' + $(".LCXD_OrdermInfo span:eq(0)").text()
			+ '","ordermmoney":"' + $(".LCXD_OrdermInfo span:eq(1)").text()
			+ '","ordermrightmoney":"' + $(".LCXD_OrdermInfo span:eq(2)").text()
			+ '","ordermway":"' + $(".LCXD_OrdermInfo span:eq(3)").text()
			+ '","ordermstatue":"' + '已下单'
			+ '","ordermemp":"' + '录单('+username+')' 
			+ '","ordermcustype":"' + orderCCus.customerInfo.customertype
			+ '","ordermcuslevel":"' + orderCCus.customerInfo.ccustomerdetail
			+ '","ordermcusshop":"' + orderCCus.customerInfo.customershop
			+ '","ordermconnect":"' + $(".LCXD_CusAddress span:eq(0)").text()
			+ '","ordermphone":"' + $(".LCXD_CusAddress span:eq(1)").text()
			+ '","ordermaddress":"' + $(".LCXD_CusAddress span:eq(2)").text()
			+ '"}]';
	$.ajax({
        url: "CPOrdermAction.do?method=addOrder",
        type: "post",
        //contentType : 'application/json;charset=utf-8', //设置请求头信息
        //dataType:"json",
        data: {
        	json : ordermjson,
			orderdetjson : orderdetjson
        },
        success: function(data){
            alert("录单成功!");
            history.go(-1);
        },
        error: function(res){
            alert("操作失败");
        }
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
	$("#goods_LCXD tr:gt(0)").remove();
	var jsonDATA = eval('('+$(".tt_cusInfo span:eq(3)").text()+')');
	$.post("CPGoodsviewAction.do?method=customerGoods",{
			"companyid" : comid,
			"pagenowGoods" : pagenowGoods,
			"goodscode" : $.trim($("#goodscode").val()),
			"priceslevel" : jsonDATA.customerInfo.ccustomerdetail,
			"pricesclass" : jsonDATA.customerInfo.customertype,
			customerid : jsonDATA.customerInfo.customerid
	},function(resp){
		var data = eval('('+resp+')');
		$.each(data.goodsList,function(i,item){
			var strJSON = JSON.stringify(item);
			var price = 0;													//商品价格
			var unit = '';													//商品单位
			if(typeof(item.largecuspriceprice)!='undefined' && item.largecuspriceprice){
				price = item.largecuspriceprice;
			}
			if(typeof(item.largecuspriceunit)!='undefined' && item.largecuspriceunit){
				unit = item.largecuspriceunit;
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
	var selData = JSON.parse(seleStrJson);
	var flag = 0;
	$(".largeCus_form table tr[name!=ordLi_tr1][name!=ord_info_tr]").each(function(i,item){
		if($(item).children('td:last span[hidden="true"]')){
			//alert($(item).find('td:last a').length);
			var currPrice = $(item).find('td:eq(5)').text();		//当前价格
			var itemData = JSON.parse($(item).find('td:last span[hidden="true"]').text());
			if(selData.goodsid==itemData.goodsid){
				var itemNumEle = $(item).children('td[name="odd_num"]');
				var itemNum = parseInt(itemNumEle.text())+1;
				itemNumEle.text(itemNum);
				flag++;
				$(item).children('td[name="xd_money"]').text(parseFloat(currPrice)*itemNum);
				$(item).children('td[name="sj_money"]').text(parseFloat(currPrice)*itemNum);
				showXDMoneyAndSJMoney();			//得到总下单金额和总实际金额
			}
		}
	});
	if(flag==0){
		$(".largeCus_form table tr:eq(0)").after('<tr>'+
				'<td>'+'</td>'+
				'<td>'+goodscode+'</td>'+
				'<td>'+orderdtype+'</td>'+
				'<td>'+goodsname+'</td>'+
				'<td>'+goodsunits+'</td>'+
				'<td name="odd_price">'+price+'</td>'+
				'<td>'+unit+'</td>'+
				'<td name="odd_num">1</td>'+
				'<td name="xd_money">'+price+'</td>'+
				'<td name="sj_money">'+price+'</td>'+
				'<td><a onclick="deleteRows(this)">删除</a><span hidden="true">'+seleStrJson+'</span></td>'+
			'</tr>');
		$(".LCXD_OrdermInfo span:eq(0)").text(parseInt($(".LCXD_OrdermInfo span:eq(0)").text())+1);
		$(".LCXD_OrdermInfo span:eq(1)").text((parseFloat($(".LCXD_OrdermInfo span:eq(1)").text())+parseFloat(price)).toFixed(2));
		$(".LCXD_OrdermInfo span:eq(2)").text((parseFloat($(".LCXD_OrdermInfo span:eq(2)").text())+parseFloat(price)).toFixed(2));
		tabEdit();										//绑定事件
		goodsAutoSort();								//自动编号
	}
	
	closeCdPopup();							//关闭商品选择窗口
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
	$.post("AddressAction.do?method=selAll",{"wheresql":"addresscustomer='"+customerid+"'"},function(resp){
		var data = eval('('+resp+')');
		$("#address_LCXD tr:gt(0)").remove();
		$.each(data.root,function(i,item){
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

















