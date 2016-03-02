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
	<div class="wapper-nav">我的</div>
    <div style="width:100%; padding:5% 0; color:#fff; text-align:center; background:url(images/minebg.jpg)">
    	<img src="images/mendian.jpg" style="border-radius:50px;" width="70" height="70 ">
        <p>阿萨德超市</p>
    </div>
	<div class="personal-center">
    	<a href="#">我的账号 <span class="sign"></span></a>
        <a href="doCollect.action?comid=1">我的收藏 <span class="sign"></span></a>
        <a href="#">我的谷币 <span class="sign"></span></a>
        <a href="address.jsp">收货地址 <span class="sign"></span></a>
    </div>
    <div class="personal-center">
    	<a href="#">谷粒客服 <span class="sign"></span></a>
        <a href="#">意见反馈 <span class="sign"></span></a>
    </div>
    <div class="personal-center" style=" text-align:center">
        <a href="#">退出登录</a>
    </div>
</div>
<div class="personal-center-nav">
    	<ul>
        	<li><a href="index.jsp"><em class="ion-home"></em>首页</a></li>
            <li><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
            <li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
            <li class="active"><a href="mine.jsp"><em class="ion-android-person"></em>我的</a></li>
        </ul>
    </div>
</body>
</html>
