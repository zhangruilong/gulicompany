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
.goods-wrapper .home-hot-commodity li a{
	padding: 0;
}
</style>
</head>
<body>
<div class="gl-box">
    <div class="wapper-nav"><a onclick='javascript:history.go(-1);' class='goback'></a>
	买赠商品<a onclick="docart(this)" href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div>
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
			<p class="popup_msg">尚无账号，立即注册？</p>
            <a class="cd-popup-close">取消</a><a class="popup_queding" href="doReg.action">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
var xian = '${param.xian}';
var givegoodscode = '${param.givegoodscode}';
$(function(){ 
	$(".cd-popup").on("click",function(event){		//绑定点击事件
		if($(event.target).is(".cd-popup-close") || $(event.target).is(".cd-popup-container")){
			//如果点击的是'取消'或者除'确定'外的其他地方
			$(this).removeClass("is-visible");	//移除'is-visible' class
			
		}
	});
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
	if(xian == ''){
		xian = customer.customerxian
	}
	$.getJSON("maizengPage.action",{"givegoodcompany.city.cityname":xian,"givegoodscode":givegoodscode},initMiaoshaPage);
});
//初始化页面
function initMiaoshaPage(data){
	$(".home-hot-commodity").html("");
	$.each(data.giveList,function(i,item1){
		var liObj = '<li><a '+
		'onclick="judgePurchase(\''+
		item1.givegoodsid +'\',\''+
		item1.givegoodsdetail +'\',\''+
		item1.givegoodscompany +'\',\''+
		item1.givegoodcompany.companyphone +'\',\''+
		item1.givegoodcompany.companydetail +'\',\''+
		item1.givegoodsclass +'\',\''+
		item1.givegoodscode +'\',\''+
		item1.givegoodsprice +'\',\''+
		item1.givegoodsunit +'\',\''+
		item1.givegoodsname +'\',\''+
		item1.givegoodsimage +'\',\''+
		item1.givegoodsunits +'\',\''+
		item1.givegoodsnum+'\');" '+
		'> <span class="fl"> <img src="../'+item1.givegoodsimage+
         	'" alt="" onerror="javascript:this.src=\'images/default.jpg\'"/></span>'+
			'<h1>'+item1.givegoodsname+
				'<span>（'+item1.givegoodsunits+'）</span>'+
			'</h1><span style="font-size: 16px;">'+item1.givegoodsdetail+'</span><br> <span> <strong>￥'+item1.givegoodsprice+'/'+item1.givegoodsunit+
			'</strong> ';
		if(data.cusOrderdList != null && data.cusOrderdList.length != 0){
			var itemGoodsCount = 0;
			$.each(data.cusOrderdList,function(k,item3){
				if(item3.orderdcode == givegoodscode){
					itemGoodsCount += item3.orderdnum
				}
			});
			liObj += '<font>限购'+(item1.givegoodsnum - itemGoodsCount)+item1.givegoodsunit+'</font><br/>';
		} else {
			liObj += '<font>限购'+item1.givegoodsnum+item1.givegoodsunit+'</font><br/>';
		}
		
		$(".home-hot-commodity").append(liObj+'</span></a></li>');
	});
}
//判断是否到达限购数量
function judgePurchase(
		givegoodsid,
		givegoodsdetail,
		givegoodscompany,
		companyshop,
		companydetail,
		givegoodsclass,
		givegoodscode,
		givegoodsorgprice,
		givegoodsunit,
		givegoodsname,
		givegoodsimage,
		givegoodsunits,
		givegoodsnum
		) {
	if(!customer.customerid || customer.customerid == ''){
		$(".cd-popup").addClass("is-visible");
		return;
	}
	$.getJSON('judgePurchaseGiveGoods.action',{
		'givegoodsnum':givegoodsnum,
		'givegoodscode':givegoodscode,
		'customerid':customer.customerid
	},function(data){
		if(data.result == 'ok'){
			chuancan(
					givegoodsid,
					givegoodsdetail,
					givegoodscompany,
					companyshop,
					companydetail,
					givegoodsclass,
					givegoodscode,
					givegoodsorgprice,
					givegoodsunit,
					givegoodsname,
					givegoodsimage,
					givegoodsunits,
					givegoodsnum
					);
		} else {
			alert('您的购买数量超过限购数量');
		}
	});
}
//将商品信息存入缓存2
function chuancan(
				givegoodsid,
				givegoodsdetail,
				givegoodscompany,
				companyshop,
				companydetail,
				givegoodsclass,
				givegoodscode,
				givegoodsorgprice,
				givegoodsunit,
				givegoodsname,
				givegoodsimage,
				givegoodsunits,
				givegoodsnum
				) {
	if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
		//没有购物车
		window.localStorage.setItem("sdishes", "[]");					//创建一个购物车
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes")); 	//将缓存中的sdishes(字符串)转换为json对象
		//新增订单
		var mdishes = new Object();
		mdishes.goodsid = givegoodsid;
		mdishes.goodsdetail = givegoodsdetail;
		mdishes.goodscompany = givegoodscompany;
		mdishes.companyshop = companyshop;
		mdishes.companydetail = companydetail;
		mdishes.goodsclassname = givegoodsclass;
		mdishes.goodscode = givegoodscode;
		mdishes.pricesprice = givegoodsorgprice;
		mdishes.pricesunit = givegoodsunit;
		mdishes.goodsname = givegoodsname;
		mdishes.goodsimage = givegoodsimage;
		mdishes.orderdtype = '买赠';
		mdishes.timegoodsnum = givegoodsnum;
		mdishes.goodsunits = givegoodsunits;
		mdishes.orderdetnum = 1;
		sdishes.push(mdishes); 											//往json对象中添加一个新的元素(订单)
		window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
		
		window.localStorage.setItem("totalnum", 1); 					//设置缓存中的种类数量等于一 
		window.localStorage.setItem("totalmoney", givegoodsorgprice);	//总金额等于商品价
		var cartnum = parseInt(window.localStorage.getItem("cartnum"));
		window.localStorage.setItem("cartnum",cartnum+1);
		window.location.href = "cart.jsp";
	} else {
		
		//有购物车
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));	//将缓存中的sdishes(字符串)转换为json对象
		var tnum = parseInt(window.localStorage.getItem("totalnum"));		//取出商品的总类数
		$.each(sdishes,function(i,item) {								//遍历购物车中的商品
			if( item.goodsid == givegoodsid && item.orderdtype == '买赠'){
				//如果商品id相同
				window.location.href = "cart.jsp";
				return false;
			} else if(i == (tnum-1)){
				//如果最后一次进入时goodsid不相同
				//新增订单
				var mdishes = new Object();
				mdishes.goodsid = givegoodsid;
				mdishes.goodsdetail = givegoodsdetail;
				mdishes.goodscompany = givegoodscompany;
				mdishes.companyshop = companyshop;
				mdishes.companydetail = companydetail;
				mdishes.goodsclassname = givegoodsclass;
				mdishes.goodscode = givegoodscode;
				mdishes.pricesprice = givegoodsorgprice;
				mdishes.pricesunit = givegoodsunit;
				mdishes.goodsname = givegoodsname;
				mdishes.goodsimage = givegoodsimage;
				mdishes.orderdtype = '买赠';
				mdishes.timegoodsnum = givegoodsnum;
				mdishes.goodsunits = givegoodsunits;
				mdishes.orderdetnum = 1;
				sdishes.push(mdishes); 												//往json对象中添加一个新的元素(订单)
				window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
				window.localStorage.setItem("totalnum", tnum + 1);					//商品种类数加一
				var tmoney = parseFloat(window.localStorage.getItem("totalmoney")); //从缓存中取出总金额
				var newtmoney = (tmoney+parseFloat(givegoodsorgprice)).toFixed(2);
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
</script>
</body>
</html>