<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.elegant-aero {
	margin-left: 10px auto;
	margin-right: auto;
	background: #D2E9FF;
	padding: 20px 20px 20px 20px;
	font: 12px Arial, Helvetica, sans-serif;
	color: #666;
	max-width: 700px;
}

.elegant-aero h1 {
	font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
	padding: 10px 10px 10px 20px;
	display: block;
	background: #C0E1FF;
	border-bottom: 1px solid #B8DDFF;
	margin: -20px -20px 15px;
	font-weight: bold;
}

.elegant-aero h1>span {
	display: block;
	font-size: 11px;
}

.elegant-aero label>span {
	margin-top: 10px;
	color: #5E5E5E;
	text-align: right;
	padding-right: 8px;
}

.elegant-aero p>span {
	float: left;
	margin-top: 10px;
	color: #5E5E5E;
	text-align: right;
	padding-right: 15px;
	font-weight: bold;
}

.elegant-aero label {
	margin: 0px 0px 5px;
}


.elegant-aero input[type="text"]{
	color: #888;
	padding: 0px 0px 0px 5px;
	border: 1px solid #C5E2FF;
	background: #FBFBFB;
	outline: 0;
	-webkit-box-shadow: inset 0px 1px 6px #ECF3F5;
	box-shadow: inset 0px 1px 6px #ECF3F5;
	font: 200 12px/25px Arial, Helvetica, sans-serif;
	height: 30px;
	line-height: 15px;
	margin: 2px 6px 16px 0px;
}


.elegant-aero .button {
	padding: 10px 30px 10px 30px;
	background: #66C1E4;
	border: none;
	color: #FFF;
	box-shadow: 1px 1px 1px #4C6E91;
	-webkit-box-shadow: 1px 1px 1px #4C6E91;
	-moz-box-shadow: 1px 1px 1px #4C6E91;
	text-shadow: 1px 1px 1px #5079A3;
}

.elegant-aero .button:hover {
	background: #3EB1DD;
}

.elegant-aero p{

}

</style>
<title>Insert title here</title>
</head>
<body>
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
			<h1>商品价格设置</h1>
			<table>
			<tr><td colspan="3"><p><span>餐饮客户价格 :</span></p> </td></tr>
			<tr>	
				
				<td><label><span>单品价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 3}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 2}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 1}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
			</tr>
			<tr>
				<td><label><span>套装价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '餐饮客户' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
			</tr>
			<tr><td colspan="3"><p><span> 高级客户价格 :</span></p></td></tr>
			<tr>
				<td><label><span>单品价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
			</tr>
			<tr>
				<td><label><span>套装价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '高级客户' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
			</tr>
			<tr><td colspan="3"><p><span> 组织单位客户价格 :</span></p></td></tr>
			<tr>
				<td><label><span>单品价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
				<td><label><span>单品价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="单品价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit }</span></label></td>
			 </tr>
			 <tr>
				<td><label><span>套装价等级3 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.goodsCon.pricesList }" var="price"><c:if test="${price.pricesclass == '组织单位客户' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="name" type="text" name="name" placeholder="套装价" /><span>/${requestScope.goodsCon.pricesList[0].pricesunit2 }</span></label></td>
			</tr>
			</table>
			<label> <span>&nbsp;</span> <input type="button"
				class="button" value="提交" />
			</label>
		</form>
	</div>
</body>
</html>