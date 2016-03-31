<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String ordermid = request.getParameter("ordermid");
%>
<!doctype html> 
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="order-detail-info">
	<p>天然粮油有限公司</p>
	<p>联系电话:0579-8247638</p>
	<p>配送时间:城区2小时送达，郊区次日送达</p>
</div>
<div class="order-detail-user">
<i class="info-icon"></i>
<div class="pdl-b8">
  <p>收货人:王金宝 16535789623 </p>
  <p>收货地址:嘉兴市海盐海东程璐89号706号</p>
  <p>支付方式:货到付款</p>
</div>
</div>
<div class="order-detail-wrapper">
  <ul id="orderd">
    	<li><span class="fl">东古一品香 </span><span class="fl">¥60 /箱</span><span class="fl">x2</span><span class="fr"> 120元</span></li>
        <li><span class="fl">东古一品香 </span><span class="fl">¥60 /箱</span><span class="fl">x2</span><span class="fr"> 120元</span></li>
        <li><span class="fl">东古一品香 </span><span class="fl">¥60 /箱</span><span class="fl">x2</span><span class="fr"> 120元</span></li>
        <li><span class="fl">东古一品香 </span><span class="fl">¥60 /箱</span><span class="fl">x2</span><span class="fr"> 120元</span></li>
        <li><span class="fl">东古一品香 </span><span class="fl">¥60 /箱</span><span class="fl">x2</span><span class="fr"> 120元</span></li>
  </ul>
  <p>订单金额: <span>680元</span></p>
    <p>优惠金额: <span>80元</span></p>
    <p>实付金额: <span>600元</span></p>
</div>
<div class="footer">
	<div class="order-detail-foot">
    	<!-- <a href="#">取消订单</a> -->
        <a href="#">重新购买</a>
    </div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var basePath = '<%=basePath%>';
var ordermid = '<%=ordermid%>';
$(function(){ 
	if(ordermid!="null"&&ordermid!=""){
		getJson(basePath+"OrderdAction.do",{method:"selAll",wheresql:"orderdorderm='"+ordermid+"'"},initOrderd,null);
	}
})
function initOrderd(data){
     $(".order-detail-wrapper").html("");
     $(".order-detail-wrapper").append('<ul>');
 	 $.each(data.root, function(i, item) {
 		$(".order-detail-wrapper").append('<li><span class="fl">'+
 				item.orderdname +'</span><span class="fl">¥'+
 				item.orderdprice +'/'+item.orderdunit+'</span><span class="fl">x'+
 				item.orderdnum +'</span><span class="fr"> '+item.orderdmoney+'元</span></li>');
     });
 	$(".order-detail-wrapper").append('</ul>');
 	getJson(basePath+"OrdermviewAction.do",{method:"selAll",wheresql:"ordermid='"+ordermid+"'"},initOrderm,null);
}
function initOrderm(data){
     $(".order-detail-info").html("");
     $(".pdl-b8").html("");
 	 $.each(data.root, function(i, item) {
 		$(".order-detail-info").append('<p>'+item.companyshop+'</p>'+
 				'<p>联系电话:'+item.companyphone+'</p>'+
 				'<p>'+item.companydetail+'</p>');
 		$(".pdl-b8").append('<p>收货人：'+item.ordermconnect+item.ordermphone+'</p>'+
 				'<p>收货地址：'+item.ordermaddress+'</p>'+
 				'<p>支付方式：'+item.ordermway+'</p>');
 		 $(".order-detail-wrapper").append('<p>订单金额: <span>'+item.ordermmoney+'元</span></p>'+
 	 		    '<p>优惠金额: <span>'+(item.ordermmoney-item.ordermrightmoney)+'元</span></p>'+
 	 		    '<p>实付金额: <span>'+item.ordermrightmoney+'元</span></p>');
     });
}
function successCB(r, cb) {
	cb && cb(r);
}

function getJson(url, param, sCallback, fCallBack) {
	try
	{
		$.ajax({
			url: url,
			data: param,
			dataType:"json",
			success: function(r) {
				successCB(r, sCallback);
				successCB(r, fCallBack);
			},
			error:function(r) {
				var resp = eval(r); 
				alert(resp.msg);
			}
		});
	}
	catch (ex)
	{
		alert(ex);
	}
}
</script>
</body>
</html>
