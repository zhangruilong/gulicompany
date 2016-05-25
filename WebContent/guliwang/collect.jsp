<%@page import="com.server.pojo.entity.Customer"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">  
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<style type="text/css">

input {
	float: left;
	
  -webkit-appearance: none; /* remove default */
  display: block;
  margin: 38px 10px 10px 10px;
  width: 24px;
  height: 24px;
  border-radius: 12px;
  cursor: pointer;
  vertical-align: middle;
  box-shadow: hsla(0,0%,100%,.15) 0 1px 1px, inset hsla(0,0%,0%,.5) 0 0 0 1px;
  background-color: white;
  background-image: -webkit-radial-gradient( red 0%, red 15%, red 28%, hsla(200,100%,30%,0) 70% );
  background-repeat: no-repeat;
  -webkit-transition: background-position .15s cubic-bezier(.8, 0, 1, 1),
    -webkit-transform .25s cubic-bezier(.8, 0, 1, 1);
}
input:checked {
  -webkit-transition: background-position .2s .15s cubic-bezier(0, 0, .2, 1),
    -webkit-transform .25s cubic-bezier(0, 0, .2, 1);
}
input:active {
  -webkit-transform: scale(1.5);
  -webkit-transition: -webkit-transform .1s cubic-bezier(0, 0, .2, 1);
}



/* The up/down direction logic */

input,
input:active {
  background-position: 0 24px;
}
input:checked {
  background-position: 0 0;
}
input:checked ~ input,
input:checked ~ input:active {
  background-position: 0 -24px;
}
#cwn_a_xiadan{
	right: 16%;
}
</style>
</head>

<body>
	<form action="delCollect.action" method="post">
	<div class="gl-box">
		<div class="wapper-nav"><a onclick="javascript:window.history.go(-1)" class='goback'></a>
	我的收藏<a id="cwn_a_xiadan" onclick="xiadan()">下单</a><a id="cwn_a_bianji" onclick="editToDel()">编辑</a></div>
		</div>
		<!-- <input type="hidden" value="${requestScope.customerCollect.customerid }" name="comid"/> -->
		<div class="shoucang-wrap">
			<ul>
				<!-- <c:forEach items="${requestScope.customerCollect.collectList }" var="collect">
					<li name="${collect.collectid }"><a name="${collect.goods.goodsid }" href="goods.jsp?searchdishes=${collect.goods.goodscode }"><span class="fl">
					<img src="../${collect.goods.goodsimage }" alt="" onerror="javascript:this.src='images/default.jpg'"/></span>
						<h1>${collect.goods.goodsname }<span>（${collect.goods.goodsunits }）</span>
							</h1>
							<p>
										<font>&nbsp;</font>
									</p>
									<p>
										<font>&nbsp;</font>
									</p>
						</a>
					</li>
				</c:forEach> -->
			</ul>
		</div>
	</form>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
$(function(){
	$.post("cusCollectInfo.action",{"comid":customer.customerid},function(data){
		if(data.collectList != ''){
			$.each(data.collectList,function(i,item){
				var jsonitem = JSON.stringify(item.goods);
				$(".shoucang-wrap").append(
					'<li name="'+item.collectid+'"><span hidden="true">'+jsonitem+'</span>'+
					'<a name="'+item.goods.goodsid+'" href="goods.jsp?searchdishes='+item.goods.goodscode+'"><span class="fl">'+
					'<img src="../'+item.goods.goodsimage+'" alt="" onerror="javascript:this.src=\'images/default.jpg\'"/></span>'+
						'<h1>'+item.goods.goodsname+'<span>（'+item.goods.goodsunits+'）</span>'+
							'</h1>'+
							'<p>'+
										'<font>&nbsp;</font>'+
									'</p>'+
									'<p>'+
										'<font>&nbsp;</font>'+
									'</p>'+
						'</a>'+
					'</li>');
			})
		} else {
			window.location.href = 'collectnothing.html';
		}
	});
})
	//下单
	function xiadan(){
		$("#cwn_a_xiadan").text("保存");
		$("#cwn_a_xiadan").attr("onclick","collectDoCart()");
		$.each($("li"),function(i,item){
			$(item).prepend("<input style='background-color:whit;' type='checkbox' value='"+$(item).attr("name")+"' name='collectids'>");
		})
	}
	//加入购物车
	function collectDoCart(){
		var goods = JSON.parse('[]');
		$.each($("[type='checkbox']"),function(i,item){
			if(item.checked){
				goods.push(JSON.parse($(item).next().text()));
			}
		})
		if(goods.length >0){
			$.each(goods,function(i,item){
				if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
					//没有购物车
					window.localStorage.setItem("sdishes", "[]");						//创建一个购物车
					var sdishes = JSON.parse(window.localStorage.getItem("sdishes")); 	//将缓存中的sdishes(字符串)转换为json对象
					//新增订单
					var mdishes = new Object();
					mdishes.goodsid = timegoodsid;
					mdishes.goodsdetail = timegoodsdetail;
					mdishes.goodscompany = timegoodscompany;
					mdishes.companyshop = companyshop;
					mdishes.companydetail = companydetail;
					mdishes.goodsclassname = timegoodsclass;
					mdishes.goodscode = timegoodscode;
					mdishes.pricesprice = timegoodsorgprice;
					mdishes.pricesunit = timegoodsunit;
					mdishes.goodsname = timegoodsname;
					mdishes.goodsimage = timegoodsimage;
					mdishes.orderdtype = '秒杀';
					mdishes.timegoodsnum = timegoodsnum;
					mdishes.goodsunits = timegoodsunits;
					mdishes.orderdetnum = 1;
					sdishes.push(mdishes); 											//往json对象中添加一个新的元素(订单)
					window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
					
					window.localStorage.setItem("totalnum", 1); 					//设置缓存中的种类数量等于一 
					window.localStorage.setItem("totalmoney", timegoodsorgprice);	//总金额等于商品价
					var cartnum = parseInt(window.localStorage.getItem("cartnum"));
					window.localStorage.setItem("cartnum",cartnum+1);
					window.location.href = "cart.jsp";
				} else {
					
					//有购物车
					var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));	//将缓存中的sdishes(字符串)转换为json对象
					var tnum = parseInt(window.localStorage.getItem("totalnum"));		//取出商品的总类数
					$.each(sdishes,function(i,item) {								//遍历购物车中的商品
						//i是增量,item是迭代出来的元素.i从0开始
						if( item.goodsid == timegoodsid){
							//如果商品id相同
							window.location.href = "cart.jsp";
							return false;
						} else if(i == (tnum-1)){
							//如果最后一次进入时goodsid不相同
							//新增订单
							var mdishes = new Object();
							mdishes.goodsid = timegoodsid;
							mdishes.goodsdetail = timegoodsdetail;
							mdishes.goodscompany = timegoodscompany;
							mdishes.companyshop = companyshop;
							mdishes.companydetail = companydetail;
							mdishes.goodsclassname = timegoodsclass;
							mdishes.goodscode = timegoodscode;
							mdishes.pricesprice = timegoodsorgprice;
							mdishes.pricesunit = timegoodsunit;
							mdishes.goodsname = timegoodsname;
							mdishes.goodsimage = timegoodsimage;
							mdishes.orderdtype = '秒杀';
							mdishes.timegoodsnum = timegoodsnum;
							mdishes.goodsunits = timegoodsunits;
							mdishes.orderdetnum = 1;
							sdishes.push(mdishes); 												//往json对象中添加一个新的元素(订单)
							window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
							window.localStorage.setItem("totalnum", tnum + 1);					//商品种类数加一
							var tmoney = parseFloat(window.localStorage.getItem("totalmoney")); //从缓存中取出总金额
							var newtmoney = (tmoney+parseFloat(timegoodsorgprice)).toFixed(2);
							window.localStorage.setItem("totalmoney",newtmoney);	
							var cartnum = parseInt(window.localStorage.getItem("cartnum"));
							window.localStorage.setItem("cartnum",cartnum+1);
							window.location.href = "cart.jsp";
						}	
					})
				}
			})
		}
	}
	//修改
	function editToDel(){
		$("#cwn_a_bianji").text("删除");
		$("#cwn_a_bianji").attr("onclick","delCollects()");
		$.each($("li"),function(i,item){
			$(item).prepend("<input style='background-color:whit;' type='checkbox' value='"+$(item).attr("name")+"' name='collectids'>");
		})
	}
	//删除收藏
	function delCollects(){
		$.each($("[type='checkbox']"),function(i,item){
			if(item.checked){
				document.forms[0].submit(); 
			}
		})
		$(".wapper-nav").html("<a onclick='javascript:window.history.go(-1)' class='goback'></a>"+
				"我的收藏<a id='cwn_a_xiadan' onclick='xiadan()'>下单</a><a id='cwn_a_bianji' onclick='editToDel()'>编辑</a>");
		$("li input").remove();
	}
</script>
</body>

</html>
