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
    <div class="wapper-nav"><a onclick='javascript:history.go(-1);' class='goback'></a>
	热销商品<a onclick="docart(this)" href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div>
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
		$.getJSON("maizengPage.action",{"givegoodcompany.city.cityname":xian},initMiaoshaPage);
	} else {
		$.getJSON("maizengPage.action",{"givegoodcompany.city.cityname":customer.customerxian},initMiaoshaPage);
	}
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
			'</h1> <span> <strong>￥'+item1.givegoodsprice+'/'+item1.givegoodsunit+
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
</script>
</body>
</html>