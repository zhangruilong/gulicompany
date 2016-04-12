<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link href="css/dig.css" type="text/css" rel="stylesheet">
<link href="../ExtJS/resources/css/ext-all.css" type="text/css" rel="stylesheet">
<style type="text/css">
#result{ width:auto; position: absolute; top:0;text-align:center;}
#result img{ width:70px; height:70px; border-radius:50px;}
input:focus{ outline:none}
.close{ position:absolute; display:none}

#uploadImg{width:82%; height:70px; position:relative; font-size:12px; overflow:hidden}
#uploadImg a{ display:block; text-align:right;  height:70px; line-height:70px; font-size:1.4em; color:#aaa}
#file_input{ width:auto; position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;}
#myshopname{line-height: 27px;}
</style>
</head>

<body>
<div class="gl-box">
	<div class="wapper-nav">我的</div>
	<form action="System_attachAction.do?other=getch&method=upload">
	<input type="hidden" name="json" id="json" value="">
    <div style="width:100%; padding-top:2%; color:#fff; text-align:center; background:url(images/minebg.jpg)">
    	<div id="uploadImg">
                <input type="file" id="file_input" />
                <a id="clo"></a> 
                <span id="result">
                  <img src="images/mendian.jpg" style="border-radius:50px;">
                </span> 
            </div>
        <p id="myshopname"></p>
    </div>
    </form>
	<div class="personal-center num1div">
        <a id="a_myshop" href="">我的店铺 <span class="sign"></span></a>
        <a id="a_mycollect" href="">我的收藏 <span class="sign"></span></a>
        <a href="#">我的谷币 <span class="sign"></span></a>
        <a id="a_address" href="">收货地址 <span class="sign"></span></a>
    </div>
    <div class="personal-center">
    	<a onclick="clearlocalstore();">清除缓存 <span class="sign"></span></a>
    	<a href="#">谷粒客服 <span class="sign"></span></a>
        <a href="objection.jsp">意见反馈 <span class="sign"></span></a>
    </div>
</div>
<div class="personal-center-nav">
    	<ul>
        	<li><a href="index.jsp">
        	<em class="ion-home"></em>首页</a></li>
            <li><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
            <li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
            <li class="active"><a href="mine.jsp"><em class="ion-android-person"></em>我的</a></li>
        </ul>
    </div>
    <!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p>是否注册?</p>
            <a href="#" class="cd-popup-close">取消</a><a href="doReg.action">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../ExtJS/ext-all.js"></script>
<script type="text/javascript" src="../ExtJS/ext-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
$(function(){
	$("#myshopname").text(customer.customershop);
	if(customer.customerid != null && customer.customerid != ''){
		$("#a_myshop").attr("href","doEditCus.action?customerid="+customer.customerid);
		$("#a_mycollect").attr("href","doCollect.action?comid="+customer.customerid);
		$("#a_address").attr("href","doAddressMana.action?customerId="+customer.customerid);
	} else {
		$(".num1div a").addClass("cd-popup-trigger");
	}
	$(".cd-popup-trigger").on("click",function(event){
		event.preventDefault();				//防止默认事件发生
		$(".cd-popup").addClass("is-visible");
	});
	$(".cd-popup").on("click",function(event){		//绑定点击事件
		if($(event.target).is(".cd-popup-close") || $(event.target).is(".cd-popup-container")){
			//如果点击的是'取消'或者除'确定'外的其他地方
			$(this).removeClass("is-visible");	//移除'is-visible' class
			
		}
	});
})

function douploadimg(){
	window.location.href = "uploadimg.jsp";
}

function clearlocalstore(){
	localStorage.removeItem("openid");
	localStorage.removeItem("customer");
	alert("清除缓存成功！");
}
</script>
</body>
</html>
