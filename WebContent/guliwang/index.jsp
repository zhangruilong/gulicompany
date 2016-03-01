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
<form id="form1" runat="server" >
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
        <input type="text" placeholder="请输入食材名称" onkeydown="submitSeach(this)"/>
        <a  onclick="chuancan('${timegoods.timegoodsid }','${timegoods.timegoodsdetail }',
	        				'${timegoods.timegoodsprice }','${timegoods.timegoodsorgprice }',
	        				'${timegoods.timegoodsunit }','${timegoods.timegoodsunits }',
	        				'${timegoods.timegoodsname }','${timegoods.timegoodsnum }'
	        				);"
	        				 href="cart.jsp" class="gwc"><img src="images/gwc.png" ></a>
    </div>
    <div class="home-hot-wrap">
    	<img src="images/banner.jpg" >
        <div class="home-hot">特惠商品抢购区</div>
        <ul class="home-hot-commodity">
        	<c:forEach items="${requestScope.companyList }" var="company">
        		<c:forEach items="${company.timegoodsList }" var="timegoods">
        			<li>
	        			<a onclick="chuancan('${timegoods.timegoodsid }','${timegoods.timegoodsdetail }',
	        				'${timegoods.timegoodsorgprice }',
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
	//将商品信息存入缓存 
	function chuancan(timegoodsid,timegoodsdetail,timegoodsorgprice,timegoodsunit,timegoodsunits,timegoodsname,timegoodsnum){
		if(window.localStorage.getItem("totalmoney") == null){
			window.localStorage.setItem("totalmoney",timegoodsorgprice)
		} else {
			var tmoney = parseFloat(window.localStorage.getItem("totalmoney"));		//从缓存中取出总金额
			var newtmoney = (tmoney+pricesprice).toFixed(2);						//'总金额' 加上 '单价 得到' '新的总金额' 精确到两位
		}
		//将需要的值存入到缓存中
		if(window.localStorage.getItem("sdishes") == null){
			window.localStorage.setItem("sdishes","[]");
		}
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes")); 	//将缓存中的sdishes(字符串)转换为json对象
		//新增订单
		var mdishes = new Object();	
		mdishes.goodsid = timegoodsid;
		mdishes.goodsdetail = timegoodsdetail;
		mdishes.pricesprice = timegoodsorgprice;
		mdishes.pricesunit = timegoodsunit;
		mdishes.goodsname = timegoodsname;
		mdishes.goodsunits = timegoodsunits;
		mdishes.orderdetnum = 1;
		sdishes.push(mdishes);				//往json对象中添加一个新的元素(订单)
		if(window.localStorage.getItem("totalnum") == null){
			window.localStorage.setItem("totalnum",1);					//设置缓存中的种类数量等于一 
		} else {
			var tnum = parseInt(window.localStorage.getItem("totalnum"));
			window.localStorage.setItem("totalnum",tnum +1);
		}
		window.localStorage.setItem("sdishes",JSON.stringify(sdishes));
	}
	//提交搜索条件 
	function submitSeach(obj){
		var seachVal = obj.val();		//得到搜索条件 
		alert(seachVal);
		window.local.href = 'goods.jsp';
	}
</script>
</body>
</html>
