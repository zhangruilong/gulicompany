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
	<div class="cart-wrapper">
    <div class="wapper-nav">购物车</div>
    	<h1>天然粮油有限公司</h1>
        <ul>
        	<li>
            	<em><img src="images/pic1.jpg" ></em> <h2>啊萨顶顶撒<span class="price">200.00元/箱</span></h2>
                <span id="min" class="jian min">-</span>
                <input class="text_box shuliang" name="" type="text" value="1"> 
                <span id="add" class="jia add">+</span>
            </li>
            <li>
            	<em><img src="images/pic1.jpg" ></em> <h2>啊萨顶顶撒<span class="price">30.00元/瓶</span></h2>
				<span id="min" class="jian min">-</span>
                <input class="text_box shuliang" name="" type="text" value="1"> 
                <span id="add" class="jia add">+</span>
            </li>
        </ul>
        <div class="songda">送达时间：城区2小时送达，郊区次日送达。</div>
    </div>
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
	<div class="jiesuan-foot-info"><img src="images/jiesuanbg.png" > 种类数：<span id="zhonglei">0</span>总价：<span id="total">0</span> </div><a href="结算.html" class="jiesuan-button">结算</a>
</div>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script> 
$(function(){ 
$(".add").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())+1) 
setTotal(); 
}) 
$(".min").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())-1) 
if(parseInt(t.val())<0){ 
t.val(0); 
} 
setTotal(); 
}) 
function setTotal(){ 
var s=0; 
$(".cart-wrapper li").each(function(){ 
s+=parseInt($(this).find('input[class*=text_box]').val())*parseFloat($(this).find('span[class*=price]').text()); 
}); 
$("#total").html(s.toFixed(2)); 
} 
setTotal(); 

}) 
</script> 
<script>
var selectedTotal = document.getElementById('zhonglei'); 
selectedTotal.innerHTML = document.getElementsByTagName("li").length;
</script>
</body>
</html>
