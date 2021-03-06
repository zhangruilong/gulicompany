<%@page import="com.server.pojo.entity.City"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
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
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="css/swipe.css" type="text/css" />
<link href="css/dig.css" type="text/css" rel="stylesheet">
<style type="text/css">
	.home-search-wrapper .citydrop img{
		margin-top: 10px;
	}
	.personal-center a{
		line-height: 250%;
		font-size: 30px;
		padding: 10px;
	}
</style>
</head>

<body>
	<div class="gl-box">
		<div class="home-search-wrapper">
			<span class="citydrop"><em><img src="images/dropbg.png"></em></span>
			<div class="menu">
				<div class="host-city">
					<p class="quyu">
						请选择服务区域 <span class="fr">所在城市：</span>
					</p>
				</div>
				<div class="menu-tags home-city-drop">
					<ul id="citys-menu">
					</ul>
				</div>
			</div>
			<input id="searchdishes" type="text" placeholder="请输入商品名称" onkeydown="submitSearch(this)" />
			<a onclick="docart(this)" href="cart.jsp" class="gwc"><!-- <img src="images/gwc.png"> --><em id="totalnum">0</em></a>
		</div>
		<div class="home-hot-wrap">
<div class="addWrap">
  <div class="swipe" id="mySwipe">
    <div class="swipe-wrap">
      <div><a href="javascript:;"><img class="img-responsive" src="../images/banner1.jpg"/></a></div>
      <div><a href="javascript:;"><img class="img-responsive" src="../images/banner2.jpg"/></a></div>
      <div><a href="../images/youhuida.jpg"><img class="img-responsive" src="../images/youhui.jpg"/></a></div>
    </div>
  </div>
  <ul id="position">
    <li class="cur"></li>
    <li class=""></li>
  </ul>
</div>
			<div class="home-hot">
				<div class="index_xiaotubiao1" onclick="dohrefJump('goodsclass.jsp')"><img src="images/index_fenlei.png">
				<span>商品分类</span></div>
				<div class="index_xiaotubiao1" onclick="dohrefJump('order.jsp')"><img src="images/index_dingdan.png">
				<span>我的订单</span></div>
				<div class="index_xiaotubiao1" onclick="dohrefJump('collect.jsp')"><img src="images/index_goumai.png">
				<span>我的收藏</span></div>
				<div class="index_xiaotubiao1" onclick="dopinpaizhuanqu()"><img src="images/index_pinpai.png">
				<span>品牌推荐</span></div>
			</div>
			<ul class="home-hot-commodity">
			</ul>
		</div>
		
		<div class="" style="padding-top: 10px;margin-bottom: 15%;">
			
	        <a id="a_myshop" onclick="" href="miaosha.jsp?xian=${param.xian }"><img alt="秒杀商品" src="images/index_miaosha.jpg"></a>
	        <a id="a_mycollect" onclick="" href="give.jsp?xian=${param.xian }"><img alt="买赠商品" src="images/index_maizeng.jpg"></a>
	        <a onclick="" href="hotgoods.jsp?xian=${param.xian }"><img alt="热销商品" src="images/index_rexiao.jpg"></a>
	    </div>
		<div class="personal-center-nav">
    	<ul>
        	<li class="active"><a href="index.jsp">
        	<em class="icon-shouye2"></em>首页</a></li>
            <li><a href="goodsclass.jsp"><em class="icon-fenlei1"></em>商城</a></li>
            <li><a onclick="docart(this)" href="cart.jsp"><em class="icon-gwc1"></em>购物车</a></li>
            <li><a href="mine.jsp"><em class="icon-wode1"></em>我的</a></li>
        </ul>
    </div>
	</div>
	<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p class="popup_msg">尚无账号，立即注册？</p>
            <a class="cd-popup-close">取消</a><a class="popup_queding" href="doReg.action">确定</a>
		</div>
	</div>
</div>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/jquery-dropdown.js"></script>
	<script type="text/javascript">
	var basePath = '<%=basePath%>';
	var xian = '${param.xian}';
	var city = '${param.city}';
	$(function(){ 
		//window.localStorage.setItem("openid","oDFe9wtBY93e1UlaFngmmeVh5cQU");
		//openid
		$(".cd-popup").on("click",function(event){		//绑定点击事件
			if($(event.target).is(".cd-popup-close") || $(event.target).is(".cd-popup-container")){
				//如果点击的是'取消'或者除'确定'外的其他地方
				$(this).removeClass("is-visible");	//移除'is-visible' class
				
			}
		});
		if(!window.localStorage.getItem("openid")||"null"==window.localStorage.getItem("openid")){
			getOpenid();
			window.localStorage.setItem("openid",getParamValue("openid"));		//得到openid
		}
		else if(xian != '' && xian != null && city != '' && city != null){
			
			$.getJSON("doGuliwangIndex.action",{"city.cityname":xian,"cityid":city},initIndexPage,null);
		} else {
			//得到页面数据
			getJson(basePath+"CustomerAction.do",{method:"selCustomer",
				wheresql : "openid='"+window.localStorage.getItem("openid")+"'"},initCustomer,null);		//得到openid
		}
		
		if(!window.localStorage.getItem("totalnum")){
			window.localStorage.setItem("totalnum",0);
		}
		//购物车图标上的数量
		if(!window.localStorage.getItem("cartnum")){
			window.localStorage.setItem("cartnum",0);
		}else if(window.localStorage.getItem("cartnum")==0){
			$("#totalnum").hide();
			$("#totalnum").text(0);
		}else{
			$("#totalnum").text(window.localStorage.getItem("cartnum"));
		}
		
	})
	//openid
	function getOpenid()
	{
	  var thisUrl = location.href;
	  location.href="snsapi-base.api?redir="+encodeURIComponent(thisUrl);
	}
	//openid
	function getParamValue(name)
	{
	  try {
	    return(
	      location.search.match(new RegExp("[\?&]"+name+"=[^&#]*"))[0].split("=")[1]
	    );
	  } catch (ex) {
	    return(null);
	  }
	}
	//得到客户信息
	function initCustomer(data){			//将customer(客户信息放入缓存)
		if(data.root[0].customerid == null || data.root[0].customerid == '' || typeof(data.root[0].customerid) == 'undefined'){
			$(".cd-popup").addClass("is-visible");
		}
		window.localStorage.setItem("customer",JSON.stringify(data.root[0]));
		$.getJSON("doGuliwangIndex.action",{"city.cityname":data.root[0].customerxian,"cityname":data.root[0].customercity},initIndexPage,null);
	}
	//初始化页面
	function initIndexPage(data){
		$(".citydrop").html(data.companyCondition.city.cityname + '<em><img src="images/dropbg.png"></em>');	//初始化区域
		$(".fr").text('所在城市：'+data.parentCity.cityname);			//所在城市
		$.each(data.cityList,function(i,item){
			$("#citys-menu").append('<li><a href="index.jsp?xian='+item.cityname+'&city='+item.cityparent+'">'+ item.cityname +'</a></li>');									//得到地区
		});
	}
	//跳转
	function dohrefJump(url){
		window.location.href = url;
	}
	//到品牌专区
	function dopinpaizhuanqu(){
		window.localStorage.setItem("goodsclassparent",'G14630381061319233');
		dohrefJump('goodsclass.jsp');
	}
	//判断是否到达限购数量
	function judgePurchase(
			timegoodsid,
			timegoodsdetail,
			timegoodscompany,
			companyshop,
			companydetail,
			timegoodsclass,
			timegoodscode,
			timegoodsorgprice,
			timegoodsunit,
			timegoodsname,
			timegoodsimage,
			timegoodsunits,
			timegoodsnum
			) {
		var customer = JSON.parse(window.localStorage.getItem("customer"));
		if(!customer.customerid || typeof(customer.customerid) == 'undefined'){
			$(".cd-popup").addClass("is-visible");
			return;
		}
		$.getJSON('judgePurchase.action',{
			'timegoodsnum':timegoodsnum,
			'timegoodscode':timegoodscode,
			'customerid':customer.customerid
		},function(data){
			if(data.result == 'ok'){
				chuancan(
						timegoodsid,
						timegoodsdetail,
						timegoodscompany,
						companyshop,
						companydetail,
						timegoodsclass,
						timegoodscode,
						timegoodsorgprice,
						timegoodsunit,
						timegoodsname,
						timegoodsimage,
						timegoodsunits,
						timegoodsnum
						);
			} else {
				alert('购买数量超过限购数量');
			}
		});
	}

		//将商品信息存入缓存2
		function chuancan(
						timegoodsid,
						timegoodsdetail,
						timegoodscompany,
						companyshop,
						companydetail,
						timegoodsclass,
						timegoodscode,
						timegoodsorgprice,
						timegoodsunit,
						timegoodsname,
						timegoodsimage,
						timegoodsunits,
						timegoodsnum
						) {
			if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
				//没有购物车
				window.localStorage.setItem("sdishes", "[]");					//创建一个购物车
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
					if( item.goodsid == timegoodsid && item.goodsclassname == timegoodsclass){
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
		}
		
		//提交搜索条件
		function submitSearch(obj) {
			var event = window.event || arguments.callee.caller.arguments[0];
			if (event.keyCode == 13) { //如果按下的是回车键
				var seachVal = $("#searchdishes").val();	//获取搜索条件
				window.location.href = 'goods.jsp?searchdishes=' + seachVal;

			}
		}
		
		//到购物车页面
		function docart(obj){
			if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
				$(obj).attr("href","cartnothing.html");
			}
		}
		function successCB(r, cb) {
			cb && cb(r);
		}

		function getJson(url, param, sCallback, fCallBack) {
			try
			{
				$.ajax({
					url: url,
					data: param,
					dataType:"json",
					success: function(r) {
						successCB(r, sCallback);
						successCB(r, fCallBack);
					},
					error:function(r) {
						var resp = eval(r); 
						alert(resp.msg);
					}
				});
			}
			catch (ex)
			{
				alert(ex);
			}
		}
	</script>
<script src="js/swipe.js"></script> 
<script type="text/javascript">
var bullets = document.getElementById('position').getElementsByTagName('li');
var banner = Swipe(document.getElementById('mySwipe'), {
	auto: 2000,
	continuous: true,
	disableScroll:false,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
		  bullets[i].className = ' ';
		}
		bullets[pos].className = 'cur';
	}
});
</script>
</body>
</html>
