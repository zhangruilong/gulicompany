<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<form id="form1" runat="server">
	<div class="home-search-wrapper">
        <span class="citydrop">静安区 <em><img src="images/dropbg.png" ></em></span>
        <div class="menu">
        	<div class="host-city">
            	<p class="quyu">请选择服务区域 <span class="fr">所在城市：上海市</span> </p>
            </div>
            <div class="menu-tags home-city-drop">
                <ul>
                	<c:forEach items="${requestScope.cityList }" var="city">
                		<li><a href="#">${city.cityname }</a></li>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <input type="text" placeholder="请输入食材名称" />
        <a href="cart.jsp" class="gwc"><img src="images/gwc.png" ></a>
    </div>
    <div class="home-hot-wrap">
    	<img src="images/banner.jpg" >
        <div class="home-hot">特惠商品抢购区</div>
        <ul class="home-hot-commodity">
        	<c:forEach items="${requestScope.companyList }" var="company">
        		<c:forEach items="${company.timegoodsList }" var="timegoods">
        			<li>
	        			<a onclick="chuancan('${timegoods.timegoodsid }','${timegoods.timegoodsdetail }',
	        				'${timegoods.timegoodsprice }','${timegoods.timegoodsorgprice }',
	        				'${timegoods.timegoodsunit }','${timegoods.timegoodsunits }',
	        				'${timegoods.timegoodsname }','${timegoods.timegoodsnum }'
	        				);" 
	        				href="cart.jsp">
		        			<span class="fl">
		        				<img src="images/pic1.jpg" >
		        			</span> 
		        			<h1 >${timegoods.timegoodsname } 
		        				<span>（${timegoods.timegoodsunits }）</span>
		        			</h1>
		        				<span> 
		        					<strong>￥${timegoods.timegoodsorgprice }/${timegoods.timegoodsunit }
		        					</strong> 
		        					<em>￥${timegoods.timegoodsprice }/${timegoods.timegoodsunit }</em> 
		        					<font>限购${timegoods.timegoodsnum }${timegoods.timegoodsunit }</font>
		        				</span>
	        			</a>
	        		</li>
        		</c:forEach>
        	</c:forEach>
        	<!-- <li><a href="cart.jsp"><span class="fl"><img src="images/pic1.jpg" ></span> <h1>冬菇一品鲜 <span>（240ml*12瓶/箱）</span></h1><span> <strong>￥110.00/箱</strong> <em>￥110.00/箱</em> <font>限购5箱</font></span></a></li> -->
        </ul>
    </div>


<div class="personal-center-nav">
    <ul>
        <li class="active"><a href="doGuliwangIndex.action?companyCondition.city.cityname=静安区"><em class="ion-home"></em>首页</a></li>
        <li><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
        <li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
        <li><a href="mine.jsp"><em class="ion-android-person"></em>我的</a></li>
    </ul>
</div>
</form>
</div>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script type="text/javascript">
	function chuancan(timegoodsid,timegoodsdetail,timegoodsprice,timegoodsorgprice,timegoodsunit,timegoodsunits,timegoodsname,timegoodsnum){
		//将需要的值存入到缓存中
		window.localStorage.setItem("timegoodsid",timegoodsid);				
		window.localStorage.setItem("timegoodsdetail",timegoodsdetail);
		window.localStorage.setItem("timegoodsprice",timegoodsprice);
		window.localStorage.setItem("timegoodsorgprice",timegoodsorgprice);
		window.localStorage.setItem("timegoodsunit",timegoodsunit);
		window.localStorage.setItem("timegoodsunits",timegoodsunits);
		window.localStorage.setItem("timegoodsname",timegoodsname);
		window.localStorage.setItem("timegoodsnum",timegoodsnum);
	}
</script>
</body>
</html>
