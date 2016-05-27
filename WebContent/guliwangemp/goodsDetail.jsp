<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 %>
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
<style type="text/css">
#gdw_t_li3{color: #F86B4F;font-size: 24px;}
#gdw_t_li2{border:0;height: 45px;}
#goods_ti{width: 80%;}
.goods-detail-wrapper ul li{width: 100%;}
.chk_1 + label{
	width: 13px;
	height: 13px;
	background-position:center;
}
.chk_1:checked + label{
	background-position:center;
}
</style>
</head>

<body>
<div class="goods-detail-wrapper">
	<ul>
    	<li><span><img id="goods_det_img1" alt="" src=""></span></li>
        <li id="gdw_t_li2">
        	<span id="goods_ti"></span>
        	<span class="gdw_tli2span_collect"></span>
        </li>
        <li id="gdw_t_li3"></li>
    </ul>
</div>
<div class="goods-detail-wrapper" style="margin: 0;border: 0px;">
	<ul class="gd-lower-liebiao">
    	<li>商品详情</li>
        <li>规格<span></span></li>
        <li>品牌<span></span></li>
        <li>编号<span></span></li>
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
var basePath = '<%=basePath%>';
var customer = JSON.parse(window.localStorage.getItem("customeremp"));
$(function(){
	var type = '${param.type}';
	var data = JSON.parse('${param.goods}');
	if(type == '商品'){
		$("#goods_det_img1").attr("src",'../'+data.goodsimage);
		$("#goods_det_img2").attr("src",'../'+data.goodsimage);
	    $("#goods_ti").html(data.goodsname+'<span>（'+data.goodsunits+'）</span>');
		$("#gdw_t_li3").html('<span>￥${param.pricesprice}</span>');
		$(".gd-lower-liebiao span:eq(0)").text(data.goodsunits);
		$(".gd-lower-liebiao span:eq(1)").text(data.goodsbrand);
		$(".gd-lower-liebiao span:eq(2)").text(data.goodscode);
		$(".gdw_tli2span_collect").prepend(' <input type="checkbox" id="'+data.goodsid+'checkbox" class="chk_1" '+data.goodsdetail+'>'+
	     		'<label for="'+data.goodsid+'checkbox" onclick="checkedgoods(\''+data.goodsid+'\');"></label><br>收藏');
	} else if (type == '秒杀'){
		$("#goods_det_img1").attr("src",'../'+data.timegoodsimage);
		$("#goods_det_img2").attr("src",'../'+data.timegoodsimage);
	    $("#goods_ti").html(data.timegoodsname+'<span>（'+data.timegoodsunits+'）</span>');
		$("#gdw_t_li3").html('<span>￥${param.pricesprice}</span>');
		$(".gd-lower-liebiao span:eq(0)").text(data.timegoodsunits);
		$(".gd-lower-liebiao span:eq(1)").text(data.timegoodsbrand);
		$(".gd-lower-liebiao span:eq(2)").text(data.timegoodscode);
		/* $(".gdw_tli2span_collect").prepend(' <input type="checkbox" id="'+data.timegoodsid+'checkbox" class="chk_1" >'+
	     		'<label for="'+data.timegoodsid+'checkbox" ></label><br>收藏'); */
	} else if(type == '买赠'){
		$("#goods_det_img1").attr("src",'../'+data.givegoodsimage);
		$("#goods_det_img2").attr("src",'../'+data.givegoodsimage);
	    $("#goods_ti").html(data.givegoodsname+'<span>（'+data.givegoodsunits+'）</span>');
		$("#gdw_t_li3").html('<span>￥${param.pricesprice}</span>');
		$(".gd-lower-liebiao span:eq(0)").text(data.givegoodsunits);
		$(".gd-lower-liebiao span:eq(1)").text(data.givegoodsbrand);
		$(".gd-lower-liebiao span:eq(2)").text(data.givegoodscode);
		/* $(".gdw_tli2span_collect").prepend(' <input type="checkbox" id="'+data.givegoodsid+'checkbox" class="chk_1" >'+
	     		'<label for="'+data.givegoodsid+'checkbox" ></label><br>收藏'); */
	}
	//弹窗
	$(".cd-popup").on("click",function(event){		//绑定点击事件
		if($(event.target).is(".cd-popup-close") || $(event.target).is(".cd-popup-container")){
			//如果点击的是'取消'或者除'确定'外的其他地方
			$(this).removeClass("is-visible");	//移除'is-visible' class
		}
	});
});
//收藏商品
function checkedgoods(goodsid){
	if(!customer.customerid || typeof(customer.customerid) == 'undefined'){
		$("#"+goodsid+"checkbox").prop("checked",false);
		$("#"+goodsid+"checkbox").attr("checked","");
		$(".cd-buttons .meg").text("尚无账号，立即注册？");
		$(".cd-buttons .ok").css("display","inline-block");
		$(".cd-popup-close").text("取消");
		$(".cd-popup").addClass("is-visible");
		return;
	}
	var url = 'CollectAction.do?method=';
	if($("#"+goodsid+"checkbox").is(':checked')){
		url +='delAllByGoodsid';
	}else{
		url +='insAll';
	}
	var json = '[{"collectgoods":"' + goodsid + 
		'","collectcustomer":"' + customer.customerid + '"}]';
	$.ajax({
		url : url,
		data : {
			json : json
		},
		success : function(resp) {
			var respText = eval('('+resp+')'); 
			if(respText.success == false) 
				alert(respText.msg);
			else {
				$(".cd-buttons .meg").text("操作成功!");
				$(".cd-buttons .ok").css("display","none");
				$(".cd-popup-close").text("确定");
				$(".cd-popup").addClass("is-visible");	//弹出窗口
				setTimeout(function () {  
					$(".cd-popup").removeClass("is-visible");	//一秒钟后关闭弹窗
			    }, 1000);
			}
		},
		error : function(resp) {
			alert('网络出现问题，请稍后再试');
		}
	});
}
</script>
</body>
</html>
