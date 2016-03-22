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
</head>

<body>
<div class="gl-box">
	<div class="wapper-nav">我的</div>
    <div style="width:100%; padding:5% 0; color:#fff; text-align:center; background:url(images/minebg.jpg)">
    	<img src="images/mendian.jpg" style="border-radius:50px;" width="70" height="70 ">
        <p>${sessionScope.customer.customershop == null?'我的店铺':sessionScope.customer.customershop }</p>
    </div>
	<div class="personal-center">
    	
        <c:if test="${sessionScope.customer.customerid != null }">
        	<a href="doEditCus.action?customerid=${sessionScope.customer.customerid }">我的店铺 <span class="sign"></span></a>
        </c:if>
        <c:if test="${sessionScope.customer.customerid == null }">
        	<a class="cd-popup-trigger">我的店铺 <span class="sign"></span></a>
        </c:if>
        <c:if test="${sessionScope.customer.customerid != null }">
        	<a href="doCollect.action?comid=${sessionScope.customer.customerid }">我的收藏 <span class="sign"></span></a>
        </c:if>
        <c:if test="${sessionScope.customer.customerid == null }">
        	<a class="cd-popup-trigger">我的收藏 <span class="sign"></span></a>
        </c:if>
        <a href="#">我的谷币 <span class="sign"></span></a>
        <c:if test="${sessionScope.customer.customerid != null }">
        	<a href="doAddressMana.action?customerId=${sessionScope.customer.customerid }">收货地址 <span class="sign"></span></a>
        </c:if>
        <c:if test="${sessionScope.customer.customerid == null }">
        	<a class="cd-popup-trigger">收货地址 <span class="sign"></span></a>
        </c:if>
    </div>
    <div class="personal-center">
    	<a href="#">谷粒客服 <span class="sign"></span></a>
        <a href="objection.jsp">意见反馈 <span class="sign"></span></a>
    </div>
    <div class="personal-center" style=" text-align:center">
        <a href="loginOut.action">退出登录</a>
    </div>
</div>
<div class="personal-center-nav">
    	<ul>
        	<li><a href="doGuliwangIndex.action?city.cityname=${sessionScope.customer.customerxian }&cityname=${sessionScope.customer.customercity }">
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
			<p>是否现在登录?</p>
            <a href="#" class="cd-popup-close">取消</a><a href="login.jsp">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function(){
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
</script>
</body>
</html>
