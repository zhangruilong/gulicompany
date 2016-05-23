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
<link href="css/dig.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="goods-detail-wrapper">
	<ul>
    	<li><span><img id="goods_det_img1" alt="" src=""></span></li>
        <li id="gdw_t_li2"></li>
        <li id="gdw_t_li3"></li>
    </ul>
</div>
<div class="goods-detail-wrapper" style="margin: 0;border: 0px;">
	<ul class="gd-lower-liebiao">
    	<li>商品编码<span></span></li>
        <li>规格<span></span></li>
        <li>品牌<span></span></li>
        <li>种类<span></span></li>
    </ul>
</div>
<div><img id="goods_det_img2" alt="" src=""></div>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p class="meg">尚无账号，立即注册？</p>
            <a class="cd-popup-close">取消</a><a class="ok" href="doReg.action" style="display: inline-block;">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
$(function(){
	var type = '${param.type}';
	if(type=='商品'){
		$.post("goodsDetail.action",{"goodsid":"${param.goodsid}"},function(data){
			$("#goods_det_img1").attr("src",'../'+data.goodsimage);
			$("#goods_det_img2").attr("src",'../'+data.goodsimage);
			$("#gdw_t_li2").html(data.goodsname+'<span>（'+data.goodsunits+'）</span>'+
					'<input type="checkbox" id="'+data.goodsid+'checkbox" class="chk_1" '+data.goodsdetail+'>'+
	            	'<label for="'+data.goodsid+'checkbox" onclick="checkedgoods(\''+data.goodsid+'\');"></label>');
			$("#gdw_t_li3").html('<span>￥${param.pricesprice}</span>');
			$(".gd-lower-liebiao span:eq(1)").text(data.goodscode);
		});
	} else if(type=='秒杀'){
		$.post("timeGoodsDetail.action",{"timegoodsid":"${param.goodsid}"},function(data){
			
		});
	} else if(type=='买赠'){
		$.post("giveGoodsDetail.action",{"givegoodsid":"${param.goodsid}"},function(data){
			
		});
	}
});
</script>
</body>
</html>
