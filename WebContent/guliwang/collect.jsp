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
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
</head>

<body>
	<div class="gl-box">
		<div class="wapper-nav">
			我的收藏<a href="#">编辑</a>
		</div>
		<div class="shoucang-wrap">
			<ul>
				<c:forEach items="${requestScope.customerCollect.collectList }" var="collect">
					<li><a href="#"><span class="fl"><img
								src="images/pic1.jpg"></span>
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
							</c:forEach>
						</a>
					</li>
				</c:forEach>
				<!-- <li><a href="#"><span class="fl"><img
							src="images/pic1.jpg"></span>
					<h1>
							冬菇一品鲜<span>（240ml*12瓶/箱）</span>
						</h1>
						<p>
							单品价:<font>￥5.00</font>/瓶
						</p>
						<p>
							套装价:<font>￥60.00</font>/箱
						</p></a></li> -->
			</ul>
		</div>
	</div>

</body>
</html>
