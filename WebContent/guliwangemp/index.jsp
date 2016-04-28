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
<link rel="stylesheet" href="css/lbstyle.css" type="text/css" />
<style type="text/css">
	.home-search-wrapper .citydrop img{
		margin-top: 10px;
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
			<input id="searchdishes" type="text" placeholder="请输入食材名称" onkeydown="submitSearch(this)" />
			<a onclick="docart(this)" href="cart.jsp" class="gwc"><img src="images/gwc.png"><em id="totalnum">0</em></a>
		</div>
		<div class="home-hot-wrap">
	<div class="slide" id="slide">
		<div class="img-div">
			<img src="../images/banner.jpg" title="图片1"/>
			<img src="../images/banner.jpg" title="图片2"/>
		</div>
		<div class="slide-btn">
			<a href="#" class="hover">●</a>
			<a href="#">●</a>
		</div>
	</div>
			<div class="home-hot">特惠商品抢购区</div>
			<ul class="home-hot-commodity">
			</ul>
		</div>


		<div class="personal-center-nav">
			<ul>
				<li class="active"><a
					href="index.jsp"><em
						class="ion-home"></em>首页</a></li>
				<li><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
				<li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
				<li><a href="customerlist.jsp"><em class="ion-android-person"></em>客户</a></li>
			</ul>
		</div>
	</div>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/jquery-dropdown.js"></script>
	<script type="text/javascript">
	var basePath = '<%=basePath%>';
	var xian = '${param.xian}';
	var city = '${param.city}';
	$(function(){ 
		//得到页面数据
		if(xian != '' && city != ''){		
			$.getJSON("doEmpGuliwangIndex.action",{"city.cityname":xian,"cityid":city},initIndexPage,null);
		} else {
			var customeremp = JSON.parse(window.localStorage.getItem("customeremp"));
			$.getJSON("doEmpGuliwangIndex.action",{"city.cityname":customeremp.customerxian,"cityname":customeremp.customercity},initIndexPage,null);
		}
		//购物车图标上的数量
		if(!window.localStorage.getItem("totalnum")){
			window.localStorage.setItem("totalnum",0);
			$("#totalnum").text(0);
		}else{
			$("#totalnum").text(window.localStorage.getItem("totalnum"));
		}
		if(window.localStorage.getItem("totalnum")==0)
			$("#totalnum").hide();
		
	})
	//初始化页面
	function initIndexPage(data){
		$(".citydrop").html(data.companyCondition.city.cityname + '<em><img src="images/dropbg.png"></em>');	//初始化区域
		$(".fr").text('所在城市：'+data.parentCity.cityname);			//所在城市
		$.each(data.cityList,function(i,item){
			$("#citys-menu").append('<li><a href="index.jsp?xian='+item.cityname+'&city='+item.cityparent+'">'+ item.cityname +'</a></li>');									//得到地区
		});
		$.each(data.companyList,function(i,item1){
			$.each(item1.timegoodsList,function(j,item2){
			$(".home-hot-commodity").append('<li><a '+
					'onclick="chuancan(\''+
						item2.timegoodsid +'\',\''+
						item2.timegoodsdetail +'\',\''+
        				item2.timegoodscompany +'\',\''+
        				item1.companyshop +'\',\''+
        				item1.companydetail +'\',\''+
        				item2.timegoodsclass +'\',\''+
        				item2.timegoodscode +'\',\''+
        				item2.timegoodsorgprice +'\',\''+
        				item2.timegoodsunit +'\',\''+
        				item2.timegoodsname +'\',\''+
        				item2.timegoodsimage +'\',\''+
        				item2.timegoodsunits +'\''+
        				');" '+
						'> <span class="fl"> <img src="../'+item2.timegoodsimage+
		 	         	'" alt="" onerror="javascript:this.src=\'images/default.jpg\'"/></span>'+
							'<h1>'+item2.timegoodsname+
								'<span>（'+item2.timegoodsunits+'）</span>'+
							'</h1> <span> <strong>￥'+item2.timegoodsorgprice+'/'+item2.timegoodsunit+
							'</strong> <em>￥'+item2.timegoodsprice+'/'+item2.timegoodsunit+'</em>'+
								'<font>限购'+item2.timegoodsnum+item2.timegoodsunit+'</font>'+
					'</span></a></li>');									//得到地区
			});
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
						timegoodsunits
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
				
				mdishes.goodsunits = timegoodsunits;
				mdishes.orderdetnum = 1;
				sdishes.push(mdishes); 											//往json对象中添加一个新的元素(订单)
				window.localStorage.setItem("sdishes", JSON.stringify(sdishes));
				var cartnum = parseInt(window.localStorage.getItem("cartnum"));
				window.localStorage.setItem("cartnum",cartnum+1);
				window.localStorage.setItem("totalnum", 1); 					//设置缓存中的种类数量等于一 
				window.localStorage.setItem("totalmoney", timegoodsorgprice);	//总金额等于商品价
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
	<script type="text/javascript">


var zBase = {
	config:{
		index:0,
		auto:true,
		direct:'left'
	},
	init:function(){
		this.slide = this.$id('slide');
		this.img_div = this.$c('img-div')[0],
		this.slide_btn = this.$tag('a',this.$c('slide-btn')[0]);
		this.img_arr = this.$tag('img',this.img_div);
		if(this.config.auto) this.play();
		this.hover();
	},
	$id:function(id){return document.getElementById(id);},
	$tag:function(tagName,obj){return (obj ?obj : document).getElementsByTagName(tagName);	},
	$c: function (claN,obj){
		var tag = this.$tag('*'),reg = new RegExp('(^|\\s)'+claN+'(\\s|$)'),arr=[];
		for(var i=0;i<tag.length;i++){
			if (reg.test(tag[i].className)){
				arr.push(tag[i]);
			}
		}
		return arr;
	},
	$add:function(obj,claN){
		reg = new RegExp('(^|\\s)'+claN+'(\\s|$)');
		if (!reg.test(obj.className)){
			
			obj.className += ' '+claN;
		}
	},
	$remve:function(obj,claN){var cla=obj.className,reg="/\\s*"+claN+"\\b/g";obj.className=cla?cla.replace(eval(reg),''):''},
	css:function(obj,attr,value){
		if(value){
		  obj.style[attr] = value;
		}else{
	   return  typeof window.getComputedStyle != 'undefined' ? window.getComputedStyle(obj,null)[attr] : obj.currentStyle[attr];
	   }
	},
	animate:function(obj,attr,val){
		var d = 1000;//动画时间一秒完成。
		if(obj[attr+'timer']) clearInterval(obj[attr+'timer']);
		var start = parseInt(zBase.css(obj,attr));//动画开始位置
		//space = 动画结束位置-动画开始位置，即动画要运动的距离。
		var space =  val- start,st=(new Date).getTime(),m=space>0? 'ceil':'floor';
		obj[attr+'timer'] = setInterval(function(){
			var t=(new Date).getTime()-st;//表示运行了多少时间，
			if (t < d){//如果运行时间小于动画时间
				zBase.css(obj,attr,Math[m](zBase.easing['easeOut'](t,start,space,d)) +'px');
			}else{
				clearInterval(obj[attr+'timer']);
				zBase.css(obj,attr,top+space+'px');
			}
		},20);
	},
	play:function(){
		this.slide.timer = setInterval(function(){
			zBase.config.index++;
			if(zBase.config.index>=zBase.img_arr.length) zBase.config.index=0;//如果当前索引大于图片总数，把索引设置0
			
			zBase.animate(zBase.img_div,zBase.config.direct,-zBase.config.index*500);
			for(var j=0;j<zBase.slide_btn.length;j++){
				zBase.$remve(zBase.slide_btn[j],'hover');
			}
			zBase.$add(zBase.slide_btn[zBase.config.index],'hover');
			
		},3000)
			
			
	},
	hover:function(){
		for(var i=0;i<this.slide_btn.length;i++){
			this.slide_btn[i].index = i;//储存每个导航的索引值
			this.slide_btn[i].onmouseover = function(){
				if(zBase.slide.timer) clearInterval(zBase.slide.timer);
				zBase.config.index =this.index; 
				
				for(var j=0;j<zBase.slide_btn.length;j++){
					zBase.$remve(zBase.slide_btn[j],'hover') ;
				}
				zBase.$add(zBase.slide_btn[zBase.config.index],'hover');
				zBase.animate(zBase.img_div,zBase.config.direct,-zBase.config.index*500);
			}
			this.slide_btn[i].onmouseout = function(){
				zBase.play();
			}
		}
	},
	 easing :{
		linear:function(t,b,c,d){return c*t/d + b;},
		swing:function(t,b,c,d) {return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;},
		easeIn:function(t,b,c,d){return c*(t/=d)*t*t*t + b;},
		easeOut:function(t,b,c,d){return -c*((t=t/d-1)*t*t*t - 1) + b;},
		easeInOut:function(t,b,c,d){return ((t/=d/2) < 1)?(c/2*t*t*t*t + b):(-c/2*((t-=2)*t*t*t - 2) + b);}
	}
}
		
zBase.init();
			
		
</script>
</body>
</html>
