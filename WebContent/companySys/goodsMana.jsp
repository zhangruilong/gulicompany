<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String goodsstatue = request.getParameter("goodsstatue");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var goodsstatue = '<%=goodsstatue %>';
$(function(){
	if(goodsstatue == null || goodsstatue == ''){
		$(".nowposition").html("当前位置：商品管理》全部商品");
	} else if(goodsstatue == '上架'){
		$(".nowposition").html("当前位置：商品管理》上架商品");
	} else if(goodsstatue == '下架'){
		$(".nowposition").html("当前位置：商品管理》下架商品");
	}
})
function addgoods(){
	$(".cd-popup").addClass("is-visible");	//弹出窗口
	$.getJSON("getallGoodclass.action",function(data){
		$.each(data,function(i,item){
			$("#goodsclass").append(
				'<option value="'+item.goodsclassid+'">'+item.goodsclassname+'</option>'
			)
		});
	});
}
function close_popup(){
	$(".cd-popup").removeClass("is-visible");	//移除'is-visible' class
}
function dobiaopin(){
	$(".cd-popup2").addClass("is-visible");	//弹出标品窗口
	$.getJSON("getallScant.action",function(data){
		$("#scant").html('<thead><tr><th>序号</th>'+
		'<th>商品编码</th>'+
		'<th>商品名称</th>'+
		'<th>描述</th>'+
		'<th>规格</th>'+
		'<th>小类名称</th>'+
		'<th>点击选择</th></tr></thead>');
		$.each(data,function(i,item){
			if(item.scantdetail == null){
				item.scantdetail = '';
			}
			$("#scant").append(
			'<tr><td>'+(i+1)+'</td>'+
			'<td>'+item.scantcode+'</td>'+
			'<td>'+item.scantname+'</td>'+
			'<td>'+item.scantdetail+'</td>'+
			'<td>'+item.scantunits+'</td>'+
			'<td>'+item.goodsclass.goodsclassname+'</td>'+
			'<td><a class="scant_a" onclick="seleScant(\''
					+item.scantcode+
					'\',\''+item.scantname+
					'\',\''+item.scantdetail+
					'\',\''+item.scantunits+
					'\',\''+item.goodsclass.goodsclassname+
					'\')">选择</a></td></tr>'
			);
		});
	});
}
function seleScant(scantcode,scantname,scantdetail,scantunits,goodsclassname){
	$("#goodscode").val(scantcode);
	$("#goodsname").val(scantname);
	$("#goodsdetail").val(scantdetail);
	$("#goodsunits").val(scantunits);
	$("#goodsclass option").each(function(i,item){
		if($(item).text() == goodsclassname){
			$(item).attr("selected",true);
		}
	});
	$(".cd-popup2").removeClass("is-visible");	//移除'is-visible' class
	
}
function popup_formSub(){
	if($("#goodscode").val() == "" || $("#goodscode").val() == null){
		alert("商品编码不能为空");
		return;
	}
	if($("#goodsunits").val() == "" || $("#goodsunits").val() == null){
		alert("规格不能为空");
		return;
	}
	if($("#goodsname").val() == "" || $("#goodsname").val() == null){
		alert("商品名称不能为空");
		return;
	}
	if($("#goodsclass").val() == "" || $("#goodsclass").val() == null){
		alert("小类名称不能为空");
		return;
	}
	$("#popup_form").submit();
}
</script>
<style type="text/css">
.bordered tr td{
	background-color: white;
}
</style>
</head>
<body>
 <pg:pager maxPageItems="8" url="allGoods.action">
 <pg:param name="goodscompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="goodsstatue" value="${requestScope.goodsCon.goodsstatue }"/>
<div class="nowposition">当前位置：商品管理》全部商品</div>
<p class="navigation">
<input class="button" type="button" value="添加商品" onclick="addgoods()">
</p>
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>商品编号</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>描述</th>
		<th>状态</th>
		<th>创建时间</th>
		<th>创建人</th>
		<th>修改时间</th>
		<th>修改人</th>
		<th>价格设置</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.goodsList) != 0 }">
	<c:forEach var="goods" items="${requestScope.goodsList }" varStatus="goodsSta">
	<pg:item>
		<tr>
			<td><c:out value="${goodsSta.count}"></c:out></td>
			<td>${goods.goodscode}</td>
			<td>${goods.goodsname}</td>
			<td>${goods.goodsunits}</td>
			<td>${goods.gClass.goodsclassname}</td>
			<td>${goods.goodsdetail}</td>
			<td>
			<c:if test="${fn:length(goods.pricesList) == 9 }">
			<a href="putaway.action?goodsid=${goods.goodsid}&goodscompany=${sessionScope.company.companyid }&goodsstatue=${goods.goodsstatue == '上架'?'下架':'上架'}">
			${goods.goodsstatue}</a>
			</c:if>
			<c:if test="${fn:length(goods.pricesList) != 9 }">
			<a style="color: #C6C6C6;">
			${goods.goodsstatue}</a>
			</c:if>
			</td>
			<td>${goods.createtime}</td>
			<td>${goods.creator}</td>
			<td>${goods.updtime}</td>
			<td>${goods.updor}</td>
			<td><a href="doGoodsPrices.action?goodsid=${goods.goodsid}">价格设置</a></td>
		</tr>
	</pg:item>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.goodsList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="14" align="center">	
			 <pg:index>
			 <pg:first><a href="${pageUrl }">第一页</a></pg:first>
			 <pg:prev><a href="${pageUrl}">上一页</a></pg:prev>
			 <pg:pages>
			 <a href="${pageUrl}">[${pageNumber }]</a>
			 </pg:pages>
			 <pg:next><a href="${pageUrl}">下一页</a></pg:next>
			 <pg:last><a href="${pageUrl }">最后一页</a></pg:last>
			 </pg:index>
		 </td>
	 </tr>
</table>
</pg:pager>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
		<form id="popup_form" action="addGoods.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="creator" value="${sessionScope.company.companyshop }">
		<input type="hidden" name="goodscompany" value="${sessionScope.company.companyid }">
			<h1>添加商品</h1>
			<label><span>商品编码 :</span><input id="goodscode" type="text"
				name="goodscode" placeholder="商品编码" /></label>
			<label><span>商品名称 :</span><input id="goodsname" type="text"
				name="goodsname" placeholder="商品名称" /></label>
			<label><span>描述 :</span><input id="goodsdetail" type="text"
				name="goodsdetail" placeholder="描述" /></label>
			<label><span>规格 :</span><input id="goodsunits" type="text"
				name="goodsunits" placeholder="规格" /></label>
			<label><span>小类名称 :</span>
			<select name="goodsclass" id="goodsclass">
				<option value="">请选择</option>
			</select>
			</label>
			<p><label><input type="button"
				class="popup_button" value="提交" onclick="popup_formSub()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="从标品库中选择" onclick="dobiaopin()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="关闭窗口" onclick="close_popup()"/>
			</label></p>
		</form>
	</div>
</div>
<!--弹框-->
<div class="cd-popup2" role="alert">
	<table class="bordered" id="scant" style="margin: 5% auto 0 auto;">
		<thead>
	    <tr>
	        <th>序号</th>
			<th>商品编码</th>
			<th>商品名称</th>
			<th>描述</th>
			<th>规格</th>
			<th>小类名称</th>
			<th>点击选择</th>
	    </tr>
	    </thead>
	</table>
</div>
</body>
</html>