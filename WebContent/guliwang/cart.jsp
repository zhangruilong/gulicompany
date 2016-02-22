<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="gl-box">
<form id="form1" runat="server">
    <div class="wapper-nav">购物车</div>
    <div class="cart-wrapper" id="tab">
    	<h1>凯源食品有限公司</h1>
        <ul>
        	<li>
            	<em><img src="images/pic1.jpg" ></em> <h2>啊萨顶顶撒<span class="price">40.00元/瓶</span></h2>
				<span id="min" class="jian min">-</span>
                <input class="text_box shuliang" name="" type="text" value="1"> 
                <span id="add" class="jia add">+</span>
            </li>
            <li>
            	<em><img src="images/pic1.jpg" ></em> <h2>啊萨顶顶撒<span class="price">5.5元/箱</span></h2>
				<span id="min" class="jian min">-</span>
                <input class="text_box shuliang" name="" type="text" value="1"> 
                <span id="add" class="jia add">+</span>
            </li>
        </ul>
        <div class="songda">送达时间：城区2小时送达，郊区次日送达。</div>
    </div>
</form>
</div>
<div class="footer">
	<div class="jiesuan-foot-info"><img src="images/jiesuanbg.png" > 种类数：<span id="totalnum">0</span>总价：<span id="totalmoney">0</span> </div><a href="结算.html" class="jiesuan-button">结算</a>
</div>

<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script> 
$(function(){
	if(!window.localStorage.getItem("totalnum")){
		window.localStorage.setItem("totalnum",0);
		$("#totalnum").text(0);
	}else{
		$("#totalnum").text(window.localStorage.getItem("totalnum"));
	}
	if(!window.localStorage.getItem("totalmoney")){
		window.localStorage.setItem("totalmoney",0);
		$("#totalmoney").text(0);
	}else{
		$("#totalmoney").text(window.localStorage.getItem("totalmoney"));
	}
	var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
	initDishes(sdishes);
});
function initDishes(data){
    $(".cart-wrapper").html("");
    $(".cart-wrapper").append('<h1>凯源食品有限公司</h1><ul>');
	$.each(data, function(i, item) {
          $(".cart-wrapper").append('<li name="'+item.goodsid+'">'+
                    	'<em><img src="images/pic1.jpg" ></em> '+
                    	'<h2>'+item.goodsname+'<span class="price">'+item.pricesprice+'元/'+item.pricesunit+'</span></h2>'+
        				'<span onclick="subnum(this,'+item.pricesprice+')" class="jian min">-</span>'+
                        '<input class="text_box shuliang" name="'+item.goodsdetail+'" type="text" value="'+
     	                getcurrennum(item.goodsid,item.goodsdetail)+'"> '+
                        '<span onclick="addnum(this,'+item.pricesprice+')" class="jia add">+</span>'+
                    '</li>');
     });
	$(".cart-wrapper").append('</ul><div class="songda">送达时间：城区2小时送达，郊区次日送达。</div>');
}
function getcurrennum(dishesid,goodsdetail){
	//订单
	if(window.localStorage.getItem("sdishes")==null){
		return 0;
	}else{
		var orderdetnum = 0;
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
		$.each(sdishes, function(i, item) {
			if(item.goodsid==dishesid
					&&item.goodsdetail==goodsdetail){
				orderdetnum = item.orderdetnum;
				return false;
			}
		});
		return orderdetnum;
	}
}
function addnum(obj,dishesprice){
	//总价
	var tmoney = parseFloat(window.localStorage.getItem("totalmoney"));
	var newtmoney = (tmoney+dishesprice).toFixed(2);
	$("#totalmoney").text(newtmoney);
	window.localStorage.setItem("totalmoney",newtmoney);
	//数量
	var numt = $(obj).prev(); 
	var num = parseInt(numt.val());
	numt.val(num+1);
	//订单
	if(window.localStorage.getItem("sdishes")==null){
		window.localStorage.setItem("sdishes","[]");
	}
	var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
	//修改订单
	$.each(sdishes, function(i, item) {
		if(item.goodsid==$(obj).parent().attr('name')
				&&item.goodsdetail==$(obj).prev().attr('name')){
			item.orderdetnum = item.orderdetnum + 1;
			return false;
		}
	});
	window.localStorage.setItem("sdishes",JSON.stringify(sdishes));
}
function subnum(obj,dishesprice){
	var numt = $(obj).next(); 
	var num = parseInt(numt.val());
	if(num > 0){
		//总价
		var tmoney = parseFloat(window.localStorage.getItem("totalmoney"));
		var newtmoney = (tmoney-dishesprice).toFixed(2);
		$("#totalmoney").text(newtmoney);
		window.localStorage.setItem("totalmoney",newtmoney);
		//数量
		numt.val(num-1);
		//订单
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
		if(num == 1){
			//删除订单
			$.each(sdishes,function(i,item){
				if(item.goodsid==$(obj).parent().attr('name')){
					sdishes.splice(i,1);
					return false;
				};
			});
			//种类数
			var tnum = parseInt(window.localStorage.getItem("totalnum"));
			$("#totalnum").text(tnum-1);
			window.localStorage.setItem("totalnum",tnum-1);
			$(obj).parent().remove();
		}else{
			//修改订单
			$.each(sdishes, function(i, item) {
				if(item.goodsid==$(obj).parent().attr('name')
						&&item.goodsdetail==$(obj).next().attr('name')){
					item.orderdetnum = item.orderdetnum - 1;
					return false;
				}
			});
		}
		window.localStorage.setItem("sdishes",JSON.stringify(sdishes));
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
