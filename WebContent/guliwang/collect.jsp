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
  background-color: hsla(0,0%,0%,.2);
  background-image: -webkit-radial-gradient( hsla(200,100%,90%,1) 0%, hsla(200,100%,70%,1) 15%, hsla(200,100%,60%,.3) 28%, hsla(200,100%,30%,0) 70% );
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
.p-a{
	float: left;
	width: 20%;
	position: relative; 
	background-color: #2c77e6; 
	height: 30px; 
	line-height: 30px; 
	color: #fff ; 
	text-align: center ;
}
.p-a a{
	position: static;
	height: 50px; 
	line-height: 20px; 
	text-align: center ;
	font-size: 20px;
}


.pp{
	width: 80%;
}
</style>
</head>

<body>
	<form action="delCollect.action" method="post">
	<div class="gl-box">
		<div class="wapper-nav"><p class="p-a"><a href="mine.jsp" >&lt;返回</a></p>
	<p class="pp">我的收藏</p><a onclick="editToDel()">编辑</a></div>
		</div>
		<input type="hidden" value="${requestScope.customerCollect.customerid }" name="comid"/>
		<div class="shoucang-wrap">
			<ul>
				<c:forEach items="${requestScope.customerCollect.collectList }" var="collect">
					<li name="${collect.collectid }"><a><span class="fl">
					<img src="../${collect.goods.goodsimage }" alt="" onerror="javascript:this.src='images/default.jpg'"/></span>
						<h1>${collect.goods.goodsname }<span>（${collect.goods.goodsunits }）</span>
							</h1>
							<c:forEach items="${collect.goods.pricesList }" var="price">
								<c:if test="${price.pricesclass == requestScope.customerCollect.customertype && price.priceslevel == collect.goods.cclevel }">
									<p>
										单品价:<font>￥${price.pricesprice } </font>/${price.pricesunit }
									</p>
									<p>
										套装价:<font>￥${price.pricesprice2 }</font>/${price.pricesunit2 }
									</p>
								</c:if>
								<c:if test="${collect.goods.cclevel == null && price.priceslevel == 3 && price.pricesclass == '餐饮客户'}">
									<p>
										单品价:<font>￥${price.pricesprice } </font>/${price.pricesunit }
									</p>
									<p>
										套装价:<font>￥${price.pricesprice2 }</font>/${price.pricesunit2 }
									</p>
								</c:if>
							</c:forEach>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</form>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script type="text/javascript">
	function editToDel(){
		$(".wapper-nav").html("<p class='p-a'><a href='mine.jsp' >&lt;返回</a></p>"+
				"<p class='pp'>我的收藏</p><a onclick='delCollects()'>删除</a>");
		
		$.each($("li"),function(i,item){
			$(item).prepend("<input type='checkbox' value='"+$(item).attr("name")+"' name='collectids'>");
		})
	}
	
	function delCollects(){
		$.each($("[type='checkbox']"),function(i,item){
			if(item.checked){
				document.forms[0].submit(); 
			}else if(i+1 == $("[type='checkbox']").length){
				$(".wapper-nav").html("<p class='p-a'><a href='mine.jsp' >&lt;返回</a></p>"+
				"<p class='pp'>我的收藏</p><a onclick='editToDel()'>编辑</a>");
			}
		})
		$("li input").remove();
	}
</script>
</body>

</html>
