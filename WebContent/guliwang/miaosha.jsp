<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<link href="css/dig.css" type="text/css" rel="stylesheet">
<style type="text/css">
/* .home-search-wrapper{
	text-align: center;
	color: white;
	font-size: 20px;
} */
</style>
</head>
<body>
<div class="gl-box">
	<!-- <div class="home-search-wrapper"><a onclick='javascript:history.go(-1);' class='goback'></a>
	<span>秒杀商品</span><a href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div> -->
    <div class="wapper-nav"><a onclick='javascript:history.go(-1);' class='goback'></a>
	秒杀商品<a onclick="docart(this)" href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div>
    <div class="goods-wrapper">
        <ul class="home-hot-commodity">
        </ul>
    </div>
</div>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p class="meg">操作成功!</p>
            <a class="cd-popup-close">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
var xian = '${param.xian}';
$(function(){ 
	//购物车图标上的数量
	if(!window.localStorage.getItem("cartnum")){
		window.localStorage.setItem("cartnum",0);
	}else if(window.localStorage.getItem("cartnum")==0){
		$("#totalnum").hide();
		$("#totalnum").text(0);
	}else{
		$("#totalnum").text(window.localStorage.getItem("cartnum"));
	}
	//页面信息
	if(xian != ''){
		$.getJSON("miaoshaPage.action",{"city.cityname":xian},initMiaoshaPage);
	} else {
		$.getJSON("miaoshaPage.action",{"city.cityname":customer.customerxian},initMiaoshaPage);
	}
});
//初始化页面
function initMiaoshaPage(data){
		$(".home-hot-commodity").html("");
	 	$.each(data.companyList,function(i,item1){
		$.each(item1.timegoodsList,function(j,item2){
		var liObj = '<li><a '+
		'onclick="judgePurchase(\''+
		item2.timegoodsid +'\',\''+
		item2.timegoodsdetail +'\',\''+
		item2.timegoodscompany +'\',\''+
		item1.companyshop +'\',\''+
		item1.companydetail +'\',\''+
		item2.timegoodsclass +'\',\''+
		item2.timegoodscode +'\',\''+
		item2.timegoodsorgprice +'\',\''+
		item2.timegoodsunit +'\',\''+
		item2.timegoodsname +'\',\''+
		item2.timegoodsimage +'\',\''+
		item2.timegoodsunits +'\',\''+
		item2.timegoodsnum+'\');" '+
		'> <span class="fl"> <img src="../'+item2.timegoodsimage+
         	'" alt="" onerror="javascript:this.src=\'images/default.jpg\'"/></span>'+
			'<h1>'+item2.timegoodsname+
				'<span>（'+item2.timegoodsunits+'）</span>'+
			'</h1> <span> <strong>￥'+item2.timegoodsorgprice+'/'+item2.timegoodsunit+
			'</strong> <em>￥'+item2.timegoodsprice+'/'+item2.timegoodsunit+'</em>';
		if(data.cusOrderdList != null && data.cusOrderdList.length != 0){
			var itemGoodsCount = 0;
			$.each(data.cusOrderdList,function(k,item3){
				if(item3.orderdcode == timegoodscode){
					itemGoodsCount += item3.orderdnum
				}
			});
			liObj += '<font>限购'+(item2.timegoodsnum - itemGoodsCount)+item2.timegoodsunit+'</font><br/>'
						+'<font>限量'+item2.allnum+'箱还剩'+item2.surplusnum+'箱</font>';
		} else {
			liObj += '<font>限购'+item2.timegoodsnum+item2.timegoodsunit+'</font><br/>'
						+'<font>限量'+item2.allnum+'箱还剩'+item2.surplusnum+'箱</font>';
		}
		
		$(".home-hot-commodity").append(liObj+'</span></a></li>');
		});
	});
}
//判断是否到达限购数量
function judgePurchase(
		timegoodsid,
		timegoodsdetail,
		timegoodscompany,
		companyshop,
		companydetail,
		timegoodsclass,
		timegoodscode,
		timegoodsorgprice,
		timegoodsunit,
		timegoodsname,
		timegoodsimage,
		timegoodsunits,
		timegoodsnum
		) {
	var customer = JSON.parse(window.localStorage.getItem("customer"));
	if(!customer.customerid){
		alert("购买前需注册");
		return;
	}
	$.getJSON('judgePurchase.action',{
		'timegoodsnum':timegoodsnum,
		'timegoodscode':timegoodscode,
		'customerid':customer.customerid
	},function(data){
		if(data.result == 'ok'){
			chuancan(
					timegoodsid,
					timegoodsdetail,
					timegoodscompany,
					companyshop,
					companydetail,
					timegoodsclass,
					timegoodscode,
					timegoodsorgprice,
					timegoodsunit,
					timegoodsname,
					timegoodsimage,
					timegoodsunits,
					timegoodsnum
					);
		} else {
			alert('购买数量超过限购数量');
		}
	});
}
//将商品信息存入缓存2
function chuancan(
				timegoodsid,
				timegoodsdetail,
				timegoodscompany,
				companyshop,
				companydetail,
				timegoodsclass,
				timegoodscode,
				timegoodsorgprice,
				timegoodsunit,
				timegoodsname,
				timegoodsimage,
				timegoodsunits,
				timegoodsnum
				) {
	if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
		//没有购物车
		window.localStorage.setItem("sdishes", "[]");					//创建一个购物车
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes")); 	//将缓存中的sdishes(字符串)转换为json对象
		//新增订单
		var mdishes = new Object();
		mdishes.goodsid = timegoodsid;
		mdishes.goodsdetail = timegoodsdetail;
		mdishes.goodscompany = timegoodscompany;
		mdishes.companyshop = companyshop;
		mdishes.companydetail = companydetail;
		mdishes.goodsclassname = timegoodsclass;
		mdishes.goodscode = timegoodscode;
		mdishes.pricesprice = timegoodsorgprice;
		mdishes.pricesunit = timegoodsunit;
		mdishes.goodsname = timegoodsname;
		mdishes.goodsimage = timegoodsimage;
		mdishes.orderdtype = '秒杀';
		mdishes.timegoodsnum = timegoodsnum;
		mdishes.goodsunits = timegoodsunits;
		mdishes.orderdetnum = 1;
		sdishes.push(mdishes); 											//往json对象中添加一个新的元素(订单)
		window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
		
		window.localStorage.setItem("totalnum", 1); 					//设置缓存中的种类数量等于一 
		window.localStorage.setItem("totalmoney", timegoodsorgprice);	//总金额等于商品价
		var cartnum = parseInt(window.localStorage.getItem("cartnum"));
		window.localStorage.setItem("cartnum",cartnum+1);
		window.location.href = "cart.jsp";
	} else {
		
		//有购物车
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));	//将缓存中的sdishes(字符串)转换为json对象
		var tnum = parseInt(window.localStorage.getItem("totalnum"));		//取出商品的总类数
		$.each(sdishes,function(i,item) {								//遍历购物车中的商品
			if( item.goodsid == timegoodsid && item.orderdtype == '秒杀'){
				//如果商品id相同
				window.location.href = "cart.jsp";
				return false;
			} else if(i == (tnum-1)){
				//如果最后一次进入时goodsid不相同
				//新增订单
				var mdishes = new Object();
				mdishes.goodsid = timegoodsid;
				mdishes.goodsdetail = timegoodsdetail;
				mdishes.goodscompany = timegoodscompany;
				mdishes.companyshop = companyshop;
				mdishes.companydetail = companydetail;
				mdishes.goodsclassname = timegoodsclass;
				mdishes.goodscode = timegoodscode;
				mdishes.pricesprice = timegoodsorgprice;
				mdishes.pricesunit = timegoodsunit;
				mdishes.goodsname = timegoodsname;
				mdishes.goodsimage = timegoodsimage;
				mdishes.orderdtype = '秒杀';
				mdishes.timegoodsnum = timegoodsnum;
				mdishes.goodsunits = timegoodsunits;
				mdishes.orderdetnum = 1;
				sdishes.push(mdishes); 												//往json对象中添加一个新的元素(订单)
				window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
				window.localStorage.setItem("totalnum", tnum + 1);					//商品种类数加一
				var tmoney = parseFloat(window.localStorage.getItem("totalmoney")); //从缓存中取出总金额
				var newtmoney = (tmoney+parseFloat(timegoodsorgprice)).toFixed(2);
				window.localStorage.setItem("totalmoney",newtmoney);	
				var cartnum = parseInt(window.localStorage.getItem("cartnum"));
				window.localStorage.setItem("cartnum",cartnum+1);
				window.location.href = "cart.jsp";
			}	
		})
	}
}
//到购物车页面
function docart(obj){
	if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
		$(obj).attr("href","cartnothing.html");
	}
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