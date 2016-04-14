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
//提交查询条件
function subgoodsfor(){
	document.forms[0].submit();
}
//商品价格设置
function setgoodsprices(){
	var count = 0;
	var itemid;
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		window.location.href = "doGoodsPrices.action?goodsid="+itemid;
	} else if(count == 0){
		alert("请选择商品");
	} else {
		alert("只能选择一个商品");
	}
}
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
			'<td>'+item.scantunits+'</td>'+
			'<td>'+item.goodsclass.goodsclassname+'</td>'+
			'<td><a class="scant_a" onclick="seleScant(\''
					+item.scantcode+
					'\',\''+item.scantname+
					'\',\''+item.scantunits+
					'\',\''+item.goodsclass.goodsclassname+
					'\')">选择</a></td></tr>'
			);
		});
	});
}
function seleScant(scantcode,scantname,scantunits,goodsclassname){
	$("#goodscode").val(scantcode);
	$("#goodsname").val(scantname);
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
function goodsStatusEdit(goodsid){
	var nowstatue = $(".edit_goodsStatus"+goodsid).text();
	var goodsstatue;
	if($.trim(nowstatue) == '上架'){
		goodsstatue = '下架';
	} else {
		goodsstatue = '上架';
	}
	$.getJSON("putaway.action",
			{
				"goodsid":goodsid,
				"goodscompany":"${sessionScope.company.companyid }",
				"goodsstatue":goodsstatue
			},function(data){
				alert(data.editResult);				//返回的提示信息
				if(data.editResult == '修改成功！'){
					$(".edit_goodsStatus"+goodsid).text(goodsstatue);	
				}
			});
}
</script>
</head>
<body>
<form action="allGoods.action" method="post">
 <pg:pager maxPageItems="10" url="allGoods.action" export="currentNumber=pageNumber">
 <pg:param name="goodscompany" value="${sessionScope.company.companyid }"/>
 <pg:param name="goodsstatue" value="${requestScope.goodsCon.goodsstatue }"/>
 <pg:param name="goodsid" value="${requestScope.goodsCon.goodsid }"/>
 <pg:param name="goodscode" value="${requestScope.goodsCon.goodscode }"/>
 <input type="hidden" name="goodscompany" value="${sessionScope.company.companyid }"> 
<div class="nowposition">当前位置：商品管理》全部商品</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" name="goodscode" value="${requestScope.goodsCon.goodscode }">
<input class="button" type="button" value="查询" onclick="subgoodsfor()">
<input class="button" type="button" value="价格设置" onclick="setgoodsprices()">
<input class="button" type="button" value="添加商品" onclick="addgoods()">
<input class="button" type="button" value="刷新" onclick="javascript:window.location.reload()">
</div>
<table class="bordered">
    <thead>
    <tr>
    	<th></th>
        <th>序号</th>
		<th>商品编号</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>状态</th>
		<th>创建时间</th>
		<th>创建人</th>
		<th>修改时间</th>
		<th>修改人</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.goodsList) != 0 }">
	<c:forEach var="goods" items="${requestScope.goodsList }" varStatus="goodsSta">
	<pg:item>
		<tr>
			<td><input type="checkbox" id="${goods.goodsid}"></td>
			<td><c:out value="${goodsSta.count}"></c:out></td>
			<td>${goods.goodscode}</td>
			<td>${goods.goodsname}</td>
			<td>${goods.goodsunits}</td>
			<td>${goods.gClass.goodsclassname}</td>
			<td>
			<a class="edit_goodsStatus${goods.goodsid}" onclick="goodsStatusEdit('${goods.goodsid}')" >
			${goods.goodsstatue}</a>
			</td>
			<td>${goods.createtime}</td>
			<td>${goods.creator}</td>
			<td>${goods.updtime}</td>
			<td>${goods.updor}</td>
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
			 	<c:if test="${currentNumber != pageNumber }">
				 	<a onclick="nowpage(this)" href="${pageUrl}">[${pageNumber }]</a>
			 	</c:if>
			 	<c:if test="${currentNumber == pageNumber }">
			 		<span class="fenye">${pageNumber }</span>
			 	</c:if>
			 </pg:pages>
			 <pg:next><a href="${pageUrl}">下一页</a></pg:next>
			 <pg:last><a href="${pageUrl }">最后一页</a></pg:last>
			 </pg:index>
		 </td>
	 </tr>
</table>
</pg:pager>
</form>
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