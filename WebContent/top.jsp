<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.pojo.System_power_quickview"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
String comid = info.getCompanyid();
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body class="top_body">
	<div class="top">
	<div class="title">
		<div class="guliwang"><img src="images/orderImg/top_logo.png" alt="" /></div>
	</div>
	<div class="mokuai">
		<ul>
			<li onclick="doFile()">帮助中心</li>
			<li onclick="doSys()">系统管理</li>
			<li onclick="doStock()">库存管理</li>
			<li onclick="doCustomer()">客户管理</li>
			<li onclick="doGoods()">商品管理</li>
			<li class="select_mokuai" onclick="doOrder()">订单管理</li>
		</ul>
	</div>
	<div class="haveNewOrderm"><audio id="tishiyin" src="MP3/tishi.mp3"></audio></div>
	<div class="help"><a href=editPas.jsp  target='main' >修改密码</a>|<a href=CPCompanyAction.do?method=zhuxiao  target='_parent' >退出</a>|<a>帮助</a></div>
	</div>
	<div class="sysname">供应商后台管理系统</div>

	<script type="text/javascript">
	var comid = '<%=comid %>';
	var timer1;
	window.onbeforeunload = function(){			//关闭浏览器时执行的方法
		clearInterval(timer1);
	};
	var tishiyin = $("#tishiyin")[0];
	$(function(){
		window.parent.main.location.href = "pages/orderMana/orderMana.jsp";
		
		$(".mokuai ul li:eq(5)").addClass("select_mokuai");
		$(".mokuai ul li").click(function(){
			$(this).addClass("select_mokuai");
			$(this).siblings().removeClass("select_mokuai");
		});
		$.ajax({
			url:"CPOrderAction.do?method=selQuery",
			type:"post",
			data:{
				wheresql:"ordermcompany='"+comid+"'",
				limit:'1',
				order:'ordermtime desc'
			},
			success:function(resp){
				var data = eval('('+resp+')');
				if(data.root[0].ordermstatue == '已下单'){
					$(".haveNewOrderm").html(
						data.root[0].ordermcode+'&nbsp;'
						+data.root[0].ordermmoney+'&nbsp;'
						+data.root[0].ordermconnect+'&nbsp;'
						+data.root[0].customershop+'&nbsp;'
						+'<span>'+data.root[0].ordermtime+'</span>'
						+'<span name="ordermtime" hidden="true">'+data.root[0].ordermtime+'</span>'
					);
				} else {
					$(".haveNewOrderm").html('<span name="ordermtime" hidden="true">'+data.root[0].ordermtime+'</span>');
				}
			},
			error:function(resp){
				var data = eval('('+resp+')');
				alert(data.msg);
			}
		});
		//显示最新订单信息
		//timer1 = setInterval(showNewestOrderm,10000);
		
	});
	function showNewestOrderm(){
			$.ajax({
				url:"CPOrderAction.do?method=selQuery",
				type:"post",
				data:{
					wheresql:"ordermcompany='"+comid+"'",
					limit:'1',
					order:'ordermtime desc'
				},
				success:function(resp){
					var data = eval('('+resp+')');
					if(data.root[0].ordermstatue == '已下单' && data.root[0].ordermtime && $("[name='ordermtime']").text()!='' 
							&& $("[name='ordermtime']").text() != data.root[0].ordermtime ){
						play();
						$(".haveNewOrderm").html(
								'有新的订单!&nbsp;'+data.root[0].ordermcode+'&nbsp;'
								+data.root[0].ordermmoney+'&nbsp;'
								+data.root[0].ordermconnect+'&nbsp;'
								+data.root[0].customershop+'&nbsp;'
								+'<span>'+data.root[0].ordermtime+'</span>&nbsp;'
								+'<span name="ordermtime" hidden="true">'+data.root[0].ordermtime+'</span>'
							);
					} else {
						$(".haveNewOrderm").html('<span name="ordermtime" hidden="true">'+data.root[0].ordermtime+'</span>');
					}
				},
				error:function(resp){
					var data = eval('('+resp+')');
					alert(data.msg);
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
			        +"<a href=pages/orderMana/orderMana.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部订单</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/orderdStatistics/orderdStatistics.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>订单商品统计</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/cusInfoStatistic/cusInfoStatistic.jsp target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>订单业务统计</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "pages/orderMana/orderMana.jsp";
		}
		
		//商品管理
		function doGoods(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/goodsMana/GoodsMana.jsp?classify=全部商品 target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/goodsMana/GoodsMana.jsp?goodsstatue=上架&classify=上架商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>上架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/goodsMana/GoodsMana.jsp?goodsstatue=下架&classify=下架商品  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>下架商品</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/goodsMana/GoodsMana.jsp?goodstype=裸价商品&classify=裸价专区  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>裸价专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/timegoods/timegoods.jsp?bkgoodstype=秒杀&classify=秒杀专区  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>秒杀专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/givegoods/givegoods.jsp?bkgoodstype=买赠&classify=买赠专区  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>买赠专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/modulargoods/modulargoods.jsp?bkgoodstype=组合&classify=组合专区  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>组合专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/carnivalgoods/carnivalgoods.jsp?bkgoodstype=年货&classify=年货专区  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>年货专区</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "pages/goodsMana/GoodsMana.jsp?classify=全部商品";
		}
		
		//客户管理
		function doCustomer(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/customerMana/customerMana.jsp?classify=全部客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>全部客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/customerMana/customerMana.jsp?customertype=3&classify=餐饮客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>餐饮客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/customerMana/customerMana.jsp?customertype=2&classify=商超客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>商超客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/customerMana/customerMana.jsp?customertype=1&classify=组织单位客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>组织单位客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/customerMana/customerMana.jsp?creator=1&classify=录单客户  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>录入客户</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "pages/customerMana/customerMana.jsp?classify=全部客户";
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
			        +"<a href=CPCompanyAction.do?method=zhuxiao  target='_parent' class='nav__item-link'>"
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
		//库存管理
		function doStock(){
			var menu_body = $(window.parent.leftFrame.menu);
			menu_body.html("<ul class='nav'>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/Goodsnum/Goodsnumview.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>库存总帐</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/Warrantin/Warrantinview.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>入库台账</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/Warrantout/Warrantoutview.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>出库台账</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/Warrantback/Warrantbackview.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>退货台账</span>"
			        +"</a>   "
			        +"</li>"
			        +"<li class='nav__item'>"
			        +"<a href=pages/Warrantcheck/Warrantcheckview.jsp  target='main' class='nav__item-link'>"
			        +"<span class='nav__item-text'>盘点纪录</span>"
			        +"</a>   "
			        +"</li>"
			        +"</ul>");
			window.parent.main.location.href = "pages/Goodsnum/Goodsnumview.jsp";
		}
	</script>
</body>
</html>
