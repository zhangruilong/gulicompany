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
	秒杀商品<a href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div>
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
	
});
</script>
</body>
</html>