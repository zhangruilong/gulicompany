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
	<div class="home-search-wrapper">
        <span class="citydrop">酱油 <em><img src="images/dropbg.png" ></em></span>
        <div class="menu">
            <div class="menu-tags home-city-drop">
                <div class="fenlei-tit">食材谱</div>
                <div class="wrapper">
                	<div class="fenlei-left">
                    	<ul>
                        	<li><a href="#">食用油</a></li>
                            <li><a href="#">食用油</a></li>
                            <li class="active"><a href="#">食用油</a></li>
                            <li><a href="#">食用油</a></li>
                            <li><a href="#">食用油</a></li>
                        </ul>
                    </div>
                    <div class="fenlei-right">
                    	<a href="#">豆油</a>
                        <a href="#">菜籽油</a>
                        <a href="#">豆油</a>
                        <a href="#">菜籽油</a>
                        <a href="#">豆油</a>
                        <a href="#">菜籽油</a>
                        <a href="#">豆油</a>
                        <a href="#">菜籽油</a>
                    </div>
                </div>
            </div>
        </div>
        <input type="text" placeholder="请输入食材名称" />
        <a href="cart.jsp" class="gwc"><img src="images/gwc.png" ></a>
    </div>
    <div class="goods-wrapper">
        <ul class="home-hot-commodity">
      	    <li>
            	<span class="fl"><img src="images/pic1.jpg" ></span> 
                <h1>冬菇一品鲜 <span>（240ml*12瓶/箱）</span></h1>
                <div class="block"> 
                	<span>
                        <input type="radio" id="radio-1-5" name="radio-3-set" class="regular-radio" checked />
                        <label for="radio-1-5">单品价:<font class="font-oringe">￥5.00</font>/瓶</label>
                    </span>
                    <span>
                        <input type="radio" id="radio-1-6" name="radio-3-set" class="regular-radio" />
                        <label for="radio-1-6">套装价:<font class="font-oringe">￥60.00</font>/箱</label>
                    </span>
                </div>
                <div class="stock-num">
                    <span id="min" class="jian min">-</span>
                    <input class="text_box shuliang" name="danpin" type="text" value="0"> 
                    <span id="add" class="jia add">+</span>
                    
                    <span hidden="ture" class="jian min">-</span>
                    <input hidden="ture" class="text_box shuliang" name="taozhuan" type="text" value="0"> 
                    <span hidden="ture" class="jia add">+</span>
                    
                    <input type="checkbox" id="checkbox_a3" class="chk_1">
               		<label for="checkbox_a3"></label>
                </div>
            </li>
            <li>
            	<span class="fl"><img src="images/pic1.jpg" ></span> 
                <h1>冬菇一品鲜 <span>（240ml*12瓶/箱）</span></h1>
                <div class="block"> 
                	<span>
                        <input type="radio" id="1radio1" name="1radio" class="regular-radio" checked />
                        <label for="1radio1">单品价:<font class="font-oringe">￥5.00</font>/瓶</label>
                    </span>
                    <span>
                        <input type="radio" id="1radio2" name="1radio" class="regular-radio" />
                        <label for="1radio2">套装价:<font class="font-oringe">￥60.00</font>/箱</label>
                    </span>
                </div>
                <div class="stock-num">
                    <span class="jian min">-</span>
                    <input class="text_box shuliang" name="danpin" type="text" value="0"> 
                    <span class="jia add">+</span>
                    
                    <span hidden="ture" class="jian min">-</span>
                    <input hidden="ture" class="text_box shuliang" name="taozhuan" type="text" value="0"> 
                    <span hidden="ture" class="jia add">+</span>
                    
                    <input type="checkbox" id="1checkbox" class="chk_1">
               		<label for="1checkbox"></label>
                </div>
            </li>
        </ul>
    </div>


<div class="personal-center-nav">
    <ul>
        	<li><a href="index.jsp"><em class="ion-home"></em>首页</a></li>
            <li class="active"><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
            <li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
            <li><a href="mine.jsp"><em class="ion-android-person"></em>我的</a></li>
    </ul>
</div>
</div>

<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script> 
$(function(){ 
	initDishes(1);
	$(".regular-radio").change(function(){ 
		var t = $(this).parent().parent().next().find('input[name*=danpin]');
		t.toggle();
		t.prev().toggle();
		t.next().toggle();
		var t2 = $(this).parent().parent().next().find('input[name*=taozhuan]');
		t2.toggle();
		t2.prev().toggle();
		t2.next().toggle();
	})
	$(".add").click(function(){
		var t=$(this).prev(); 
		t.val(parseInt(t.val())+1);
	}) 
	$(".min").click(function(){ 
		var t=$(this).next(); 
		t.val(parseInt(t.val())-1);
		if(parseInt(t.val())<0){ 
			t.val(0); 
		} 
	}) 
	function setTotal(){ 
		var s=0; 
		$(".cart-wrapper li").each(function(){ 
		s+=parseInt($(this).find('input[class*=text_box]').val())*parseFloat($(this).find('span[class*=price]').text()); 
		}); 
		$("#total").html(s.toFixed(2)); 
	} 
}) 
function initDishes(data){
     $(".home-hot-commodity").html("");
     $(".home-hot-commodity").append('<li>'+
         	'<span class="fl"><img src="images/pic1.jpg" ></span> '+
             '<h1>冬菇一品鲜 <span>（240ml*12瓶/箱）</span></h1>'+
           '  <div class="block"> '+
             	'<span>'+
                 '    <input type="radio" id="radio-1-7" name="radio-4-set" class="regular-radio" checked />'+
                 '    <label for="radio-1-7">单品价:<font class="font-oringe">￥5.00</font>/瓶</label>'+
               '  </span>'+
               '  <span>'+
                   '  <input type="radio" id="radio-1-8" name="radio-4-set" class="regular-radio" />'+
               '      <label for="radio-1-8">套装价:<font class="font-oringe">￥60.00</font>/箱</label>'+
               '  </span>'+
            ' </div>'+
           '  <div class="stock-num">'+
                ' <span class="jian min">-</span>'+
                 '<input class="text_box shuliang" name="danpin" type="text" value="0"> '+
                ' <span class="jia add">+</span>'+
                 
               '  <span hidden="ture" class="jian min">-</span>'+
                ' <input hidden="ture" class="text_box shuliang" name="taozhuan" type="text" value="0"> '+
                ' <span hidden="ture" class="jia add">+</span>'+
                 
                ' <input type="checkbox" id="checkbox_a4" class="chk_1">'+
            		'<label for="checkbox_a4"></label>'+
             '</div>'+
         '</li>');
     $(".home-hot-commodity").append('<li>'+
         	'<span class="fl"><img src="images/pic1.jpg" ></span> '+
             '<h1>冬菇一品鲜 <span>（240ml*12瓶/箱）</span></h1>'+
           '  <div class="block"> '+
             	'<span>'+
                 '    <input type="radio" id="1radio1" name="1radio" class="regular-radio" checked />'+
                 '    <label for="1radio1">单品价:<font class="font-oringe">￥5.00</font>/瓶</label>'+
               '  </span>'+
               '  <span>'+
                   '  <input type="radio" id="1radio2" name="1radio" class="regular-radio" />'+
               '      <label for="1radio2">套装价:<font class="font-oringe">￥60.00</font>/箱</label>'+
               '  </span>'+
            ' </div>'+
           '  <div class="stock-num">'+
                ' <span class="jian min">-</span>'+
                 '<input class="text_box shuliang" name="danpin" type="text" value="0"> '+
                ' <span class="jia add">+</span>'+
                 
               '  <span hidden="ture" class="jian min">-</span>'+
                ' <input hidden="ture" class="text_box shuliang" name="taozhuan" type="text" value="0"> '+
                ' <span hidden="ture" class="jia add">+</span>'+
                 
                ' <input type="checkbox" id="1checkbox" class="chk_1">'+
            		'<label for="1checkbox"></label>'+
             '</div>'+
         '</li>');
/* 	 $.each(data.root, function(i, item) {
           
        });
 */}
</script> 
</body>
</html>
