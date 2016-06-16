<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customertype = request.getParameter("customer.customertype");
%>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<script type="text/javascript" src="../companySys/js/common.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<style type="text/css">
.elegant-aero{
	margin-top: 0;
}
.fenyelan_input{
	width: 22px;
	text-align: center;
}
.fenyelan_button{
	width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;
}
.goods_select_popup{
	margin:0px auto;
	margin-top:1%;
	width: 60%;
	background-color: #FBF1E5;
}
.elegant-aero p input[type="checkbox"]{
	vertical-align:middle
}
</style>
</head>
<body>
<div class="nowposition">当前位置：客户管理》特殊客户》特殊客户商品</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="largecuspriceprice" name="largecuspriceprice" value="">
<input class="button" type="button" value="查询" onclick="initLargePG(1)">
<input class="button" type="button" value="添加" onclick="addLCPgoods()">
<input class="button" type="button" value="修改" onclick="editLCPgoods()">
<input class="button" type="button" value="删除" onclick="deleteLCPgoods()">
</div>
<table class="bordered" id="LGC_PGtable">
    <thead>
    <tr>
    	<th> </th>
        <th>序号</th>
		<th>编码</th>
		<th>名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>价格</th>
		<th>单位</th>
		<th>描述</th>
		<th>创建时间</th>
		<th>修改时间</th>
    </tr>
    </thead>
</table>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
			<h1>添加特殊客户商品</h1>
			<label><span>编码 :</span><input type="text"
				readonly="readonly" /></label>
			<label><span>名称 :</span><input type="text"
				readonly="readonly" /></label>
			<label><span>规格 :</span><input type="text"
				readonly="readonly" /></label>
			<label><span>单位 :</span><input id="largecuspriceunit" type="text"
				name="largecuspriceunit" placeholder="单位" /></label>
			<label><span>价格 :</span><input id="LCPprice" type="number"
				name="largecuspriceprice" placeholder="价格" /></label>
			<label><span>描述 :</span><textarea id="largecuspricedetail" name="largecuspricedetail"></textarea></label>
			<p><label><input type="button"
				class="popup_button" value="保存" onclick="saveNewGoodsPrice()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="从商品中选择" onclick="dobiaopin()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="关闭窗口" onclick="close_popup()"/>
			</label></p>
	</div>
</div>
<!--弹框-->
<div class="cd-popup2" role="alert">
<div class="goods_select_popup">
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="goodscode" name="goodscode" value="">
<input class="button" type="button" value="查询" onclick="queryGooods()">
<input class="button" type="button" value="关闭" onclick="closeCdPopup2()">
</div>
	<table class="bordered" id="scant" style="margin: 5% auto 0 auto;">
		<thead>
	    <tr>
	        <th>序号</th>
			<th>商品编码</th>
			<th>商品名称</th>
			<th>规格</th>
			<!-- <th>小类名称</th> -->
			<th>点击选择</th>
	    </tr>
	    </thead>
	</table>
</div>
</div>
<script type="text/javascript">
var customerid = '${param.customerid}';
var companyid = '${param.companyid}';
if(companyid == ''){
	window.location.href = "login.jsp";
}
$(function(){
	initLargePG(1);
});
//删除特价商品
function deleteLCPgoods(){
	
}
//修改新特价商品
function editLCPgoods(){
	var count = 0;
	var itemJson = '';
	$(":checkbox[name='priceGoods_cb']").each(function(i,item){
		if(item.checked){
			count++
			itemJson = $(item).next().text();
		}
	});
	if(count == 1){
		var lcgpItemJson = JSON.parse(itemJson);
		$(".elegant-aero label:eq(0) input").val(lcgpItemJson.lcpGoods.goodscode);
		$(".elegant-aero label:eq(1) input").val(lcgpItemJson.lcpGoods.goodsname);
		$(".elegant-aero label:eq(2) input").val(lcgpItemJson.lcpGoods.goodsunits);
		$(".elegant-aero label:eq(3) input").val(lcgpItemJson.largecuspriceunit);
		$(".elegant-aero label:eq(4) input").val(lcgpItemJson.largecuspriceprice);
		$(".elegant-aero label:eq(5) textarea").text(typeNullFoString(lcgpItemJson.largecuspricedetail));
		$(".elegant-aero p label input[value='保存']").parent().attr("name",lcgpItemJson.largecuspriceid);
		$(".elegant-aero p label input[value='保存']").attr("onclick","saveEditLCPgoods()");
		$(".cd-popup").addClass("is-visible");	//弹出窗口
	} else if(count > 1){
		alert("一次只能修改一个商品价格");
	} else {
		alert("请选择商品");
	}
}
//保存对特殊价格商品的修改
function saveEditLCPgoods(){
	if(checkIsNull())return;
	$.ajax({
		url:"editLargeGoodsPrice.action",
		type:"POST",
		data:{
			"largecuspriceid":$(".elegant-aero p label input[value='保存']").parent().attr("name"),
			"largecuspriceunit":$(".elegant-aero label:eq(3) input").val(),
			"largecuspriceprice":$(".elegant-aero label:eq(4) input").val(),
			"largecuspricedetail":$(".elegant-aero label:eq(5) textarea").text()
		},
		success:function(data){
			alert("修改成功");
			initLargePG($("#pagenow").val());
			close_popup();
			$(".elegant-aero p label input[value='保存']").attr("onclick","saveNewGoodsPrice()");
		},
		error:function(){}
	});
}
//检验是否为空
function checkIsNull(){
	var count = 0;
	$(".elegant-aero label input").each(function(i,item){
		if(!$(item).val()){
			count++;
			if(!$(item).attr("placeholder")){
				alert("请选择商品");
			} else {
				alert($(item).attr("placeholder")+"不能为空")
			}
			return false;
		}
	});
	if(count>0){
		return true;
	}
}
//保存新特价
function saveNewGoodsPrice(){
	if(checkIsNull())return;
	$.ajax({
		url:"saveNewLargeCusGP.action",
		type:"POST",
		data:{
			"largecuspricecompany":companyid,
			"largecuspricecustomer":customerid,
			"largecuspricegoods":$(".elegant-aero p label input[value='保存']").attr("name"),
			"largecuspriceunit":$(".elegant-aero label:eq(3) input").val(),
			"largecuspriceprice":$(".elegant-aero label:eq(4) input").val(),
			"largecuspricedetail":$(".elegant-aero label:eq(5) input").val()
			},
		success:function(data){
			if(data > 0){
				alert("添加成功！");
			} else {
				alert("此特价商品已存在。")
			}
			initLargePG(1);
			close_popup();
		},
		error:function(data){
			alert("操作失败。");
		}
	});
}
//添加大客户商品
function addLCPgoods(){
	$(".elegant-aero [type!='button']").val("");
	$(".elegant-aero textarea").text("");
	$(".cd-popup").addClass("is-visible");	//弹出窗口
}
//关闭添加秒杀商品窗口
function close_popup(){
	$(".cd-popup").removeClass("is-visible");	//移除'is-visible' class
}
//关闭商品选择窗口
function closeCdPopup2(){
	$(".cd-popup2").removeClass("is-visible");
}
//弹出商品窗口
function dobiaopin(){
	$(".cd-popup2").addClass("is-visible");	//弹出标品窗口
	loadGoodsData(1);
}
//商品分页
function fenyeGoods(targetPage){
	loadGoodsData(targetPage);
}
//条件查询
function queryGooods(){
	loadGoodsData(1);
}
//跳转到第X页
function goodsPageTo(pageCountGoods){
	var pagenowGoods = $("#pagenowGoods").val();
	if(parseInt(pagenowGoods) > parseInt(pageCountGoods)){
		pagenowGoods = pageCountGoods;
	}
	loadGoodsData(pagenowGoods);
}
//选择大客户商品
function seleLCGoods(goodscode,goodsname,goodsunits,goodsid){
	$(".elegant-aero label:eq(0) input").val(goodscode);
	$(".elegant-aero label:eq(1) input").val(goodsname);
	$(".elegant-aero label:eq(2) input").val(goodsunits);
	$(".elegant-aero p label input[value='保存']").attr("name",goodsid);
	closeCdPopup2();
}
//加载商品数据
function loadGoodsData(pagenowGoods){
	var data = {
				'goodscompany':'${sessionScope.company.companyid }',
				'pagenowGoods':pagenowGoods,
				'goodscode':$.trim($("#goodscode").val())
			};
	$.getJSON("getallGoods.action",data,function(data){
		$("#scant td").remove();
		$.each(data.goodsList,function(i,item){
			$("#scant").append(
			'<tr><td>'+(i+1)+'</td>'+
			'<td>'+item.goodscode+'</td>'+
			'<td>'+item.goodsname+'</td>'+
			'<td>'+item.goodsunits+'</td>'+
			'<td><a class="scant_a" onclick="seleLCGoods(\''
					+item.goodscode+
					'\',\''+item.goodsname+
					'\',\''+item.goodsunits+
					'\',\''+item.goodsid+
					'\')">选择</a></td></tr>'
			);
		});
		var goodsfenye = '<tr><td colspan="7">';
		if(data.pagenowGoods > 1){
			goodsfenye += '<a onclick=fenyeGoods("1")>第一页</a><a onclick=fenyeGoods("'+(parseInt(data.pagenowGoods)-1)+'")>上一页</a>';
			
		} else {
			goodsfenye += '<span>第一页</span><span>上一页</span>';
		}
		goodsfenye += '<span>当前第'+data.pagenowGoods+'页</span>';
		if(data.pagenowGoods < data.pageCountGoods){
			goodsfenye += '<a onclick=fenyeGoods("'+(parseInt(data.pagenowGoods)+1)+'")>下一页</a><a onclick=fenyeGoods("'+data.pageCountGoods+'")>最后一页</a>';
		} else {
			goodsfenye += '<span>下一页</span><span>最后一页&nbsp;</span>';
		}
		goodsfenye += '<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="pagenowGoods" value="'+
			data.pagenowGoods+'">页</span>'+
		 	'<input onclick=goodsPageTo("'+data.pageCountGoods+'") type="button" value="GO" class="fenyelan_button">'+
	 		'<span>一共 '+data.countGoods+' 条数据</span>';
		$("#scant").append(goodsfenye);
	});
}
//初始化客户特殊价格商品数据
function initLargePG(targetPage){
	var data = {
			"largecuspricecompany":companyid,
			"largecuspricecustomer":customerid,
			"pagenow":targetPage,
			"largecuspriceprice":$.trim($("#largecuspriceprice").val())
		}
	$.post("querylargeCusPriceGoods.action",data,function(data){
		if(data.largeCusPrice.length == 0){
			$("#LGC_PGtable").append('<tr><td colspan="11" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>');
		} else {
			$("#LGC_PGtable tr:gt(0)").remove();
			$.each(data.largeCusPrice,function(i,item){
				var strJSON = JSON.stringify(item);									//商品数据字符串
				$("#LGC_PGtable").append('<tr>'+
						'<td><input type="checkbox" name="priceGoods_cb" value="'+item.largecuspriceid+'"><span hidden="true">'+strJSON+'</span></td>'+
						'<td>'+(i+1)+'</td>'+
						'<td>'+item.lcpGoods.goodscode+'</td>'+
						'<td>'+item.lcpGoods.goodsname+'</td>'+
						'<td>'+item.lcpGoods.goodsunits+'</td>'+
						'<td>'+item.lcpGoods.gClass.goodsclassname+'</td>'+
						'<td>'+item.largecuspriceprice+'</td>'+
						'<td>'+item.largecuspriceunit+'</td>'+
						'<td>'+ typeNullFoString(item.largecuspricedetail) +'</td>'+
						'<td>'+item.largecuspricecreatetime+'</td>'+
						'<td>'+ typeNullFoString(item.largecuspricecreator) +'</td>'+
					'</tr>');
			});
			var goodsfenye = '<tr><td colspan="12">';
			if(data.pagenow > 1){
				goodsfenye += '<a onclick=fenye("1")>第一页</a><a onclick=fenye("'+(parseInt(data.pagenow)-1)+'")>上一页</a>';
				
			} else {
				goodsfenye += '<span>第一页</span><span>上一页</span>';
			}
			goodsfenye += '<span>当前第'+data.pagenow+'页</span>';
			if(data.pagenow < data.pageCount){
				goodsfenye += '<a onclick=fenye("'+(parseInt(data.pagenow)+1)+'")>下一页</a><a onclick=fenye("'+data.pageCount+'")>最后一页</a>';
			} else {
				goodsfenye += '<span>下一页</span><span>最后一页&nbsp;</span>';
			}
			goodsfenye += '<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="pagenow" value="'+
				data.pagenow+'">页</span>'+
			 	'<input onclick=goodsPageTo("'+data.pageCount+'") type="button" value="GO" class="fenyelan_button">'+
		 		'<span>一共 '+data.count+' 条数据</span>';
			$("#LGC_PGtable").append(goodsfenye);
		}
	});
}
//大客户特殊价格商品分页
function fenye(targetPage){
	initLargePG(targetPage);
}
</script>
</body>
</html>