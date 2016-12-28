<%@page import="com.server.pojo.entity.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo"); 
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
<link href="../companySys/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body class="top_body">
	<div class="top">
	<div class="title">
		<div class="guliwang"><img src="../companySys/images/orderImg/top_logo.png" alt="" /></div>
	</div>
	<div class="mokuai">
		<ul>
			<li onclick="doFile()">帮助中心</li>
			<li onclick="doSys()">系统管理</li>
			<li onclick="doCustomer()">客户管理</li>
			<li onclick="doGoods()">商品管理</li>
			<li onclick="doOrder()">订单管理</li>
		</ul>
	</div>
	<div class="haveNewOrderm"><audio id="tishiyin" src="MP3/tishi.mp3"></audio></div>
	<div class="help"><a href=editPas.jsp  target='main' >修改密码</a>|<a href=loginOut.action  target='_parent' >退出</a>|<a>帮助</a></div>
	</div>
	<div class="sysname">供应商后台管理系统</div>

	<script type="text/javascript">
	var timer1;
	window.onbeforeunload = function(){			//关闭浏览器时执行的方法 
		clearInterval(timer1);
	};
	var tishiyin = $("#tishiyin")[0];
	$(function(){
		window.parent.main.location.href = "allOrder.action?ordermcompany="+'<%=loginInfo.getCompanyid() %>';
		
		$(".mokuai ul li").each(function(i,item){
			if(i == 4){
				$(item).addClass("select_mokuai");
			}
			$(item).click(function(){
				$(this).addClass("select_mokuai");
				$(this).siblings().removeClass("select_mokuai");
			});
		});
		$.post("queryNewestOrderm.action",{"ordermcompany":'${sessionScope.loginInfo.companyid}'},function(data){
			
			if(data.ordermstatue == '已下单'){
				$(".haveNewOrderm").html(
					data.ordermcode+'&nbsp;'
					+data.ordermmoney+'&nbsp;'
					+data.ordermconnect+'&nbsp;'
					+data.orderdCustomer.customershop+'&nbsp;'
					+'<span>'+data.ordermtime+'</span>'
					+'<span name="ordermtime" hidden="true">'+data.ordermtime+'</span>'
				);
			} else {
				$(".haveNewOrderm").html('<span name="ordermtime" hidden="true">'+data.ordermtime+'</span>');
			}
		});
		//显示最新订单信息
		//timer1 = setInterval(showNewestOrderm,10000);
		
	})
	function showNewestOrderm(){
		//clearInterval(timer1);
		if('${sessionScope.loginInfo.companyid}' == ''){
			window.prent.location.href = 'login.jsp';
		}else
		$.post("queryNewestOrderm.action",{"ordermcompany":'${sessionScope.loginInfo.companyid}'},function(data){
			if(data.ordermstatue == '已下单'&&$("[name='ordermtime']").text() != data.ordermtime){
				play();
				$(".haveNewOrderm").html(
						'有新的订单!&nbsp;'+data.ordermcode+'&nbsp;'
						+data.ordermmoney+'&nbsp;'
						+data.ordermconnect+'&nbsp;'
						+data.orderdCustomer.customershop+'&nbsp;'
						+'<span>'+data.ordermtime+'</span>&nbsp;'
						+'<span name="ordermtime" hidden="true">'+data.ordermtime+'</span>'
					);
			} else {
				$(".haveNewOrderm").html('<span name="ordermtime" hidden="true">'+data.ordermtime+'</span>');
			}
		});
	}
	function play() {
		tishiyin.play();
	}
		//订单管理
		function doOrder(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=loginInfo.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=loginInfo.getCompanyid() %>'+"&ordermway=在线支付  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>在线支付订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allOrder.action?ordermcompany="+'<%=loginInfo.getCompanyid() %>'+"&ordermway=货到付款  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>货到付款订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=orderStatistics.action?companyid="+'<%=loginInfo.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>订单商品统计</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=cusStatistic.action?companyid="+'<%=loginInfo.getCompanyid() %>'+"  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>订单业务统计</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allOrder.action?ordermcompany="+'<%=loginInfo.getCompanyid() %>';
		}
		//商品管理
		function doGoods(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=loginInfo.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=loginInfo.getCompanyid() %>'+"&goodsstatue=上架  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>上架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=loginInfo.getCompanyid() %>'+"&goodsstatue=下架  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>下架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCarnivalGoods.action?bkgoodscompany="+'<%=loginInfo.getCompanyid() %>'+"&bkgoodstype=秒杀商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>秒杀专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCarnivalGoods.action?bkgoodscompany="+'<%=loginInfo.getCompanyid() %>'+"&bkgoodstype=买赠商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>买赠专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allGoods.action?goodscompany="+'<%=loginInfo.getCompanyid() %>'+"&goodstype=裸价商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>裸价专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCarnivalGoods.action?bkgoodscompany="+'<%=loginInfo.getCompanyid() %>'+"&bkgoodstype=组合商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>组合专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCarnivalGoods.action?bkgoodscompany="+'<%=loginInfo.getCompanyid() %>'+"&bkgoodstype=年货  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>年货专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allGoods.action?goodscompany="+'<%=loginInfo.getCompanyid() %>';
		}
		//客户管理
		function doCustomer(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>'+" target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>'+"&customer.customertype=3  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>餐饮客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>'+"&customer.customertype=2  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>商超客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>'+"&customer.customertype=1  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>组织单位客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>'+"&creator=1  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>录入客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "allCustomer.action?ccustomercompany="+'<%=loginInfo.getCompanyid() %>';
		}
		//系统管理
		function doSys(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=cusInfo.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>用户信息</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=editPas.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>密码修改</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=loginOut.action  target='_parent' class='nav__item-link'>"
			        +"<span class='nav__item-text'>退出系统</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "cusInfo.jsp";
		}
		//文档管理
		function doFile(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=fileMana.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>帮助文档</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "fileMana.jsp";
		}
	</script>
</body>
</html>
