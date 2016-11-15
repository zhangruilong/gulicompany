<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<style type="text/css">
.fenyelan_input{
	width: 22px;
	text-align: center;
}
.goods_select_popup{
	margin:0px auto;
	padding:10px auto;
	width:550px;
	margin-top:1%;
	background-color: #FBF1E5;
}
.elegant-aero label>font{
	font-weight: bold;
	color: #F66B45;
}

</style>
</head>
<body>
<form id='main_form' action="allGoods.action" method="post">
 <input type="hidden" name="goodscompany" value="${sessionScope.company.companyid }"> 
 <input type="hidden" name="goodsstatue" value="${goods.goodsstatue}"> 
 <input type="hidden" name="goodstype" value="${goods.goodstype}"> 
 <input type="hidden" class="setPricesGoodsId" name="goodsid" value="">
<div class="nowposition">当前位置：商品管理》全部商品</div>
<div class="navigation">
商品类型:&nbsp;&nbsp;
<select name="pricesList[0].pricesclass">
	<option value="" >全部商品</option>
	<option value="3" ${goodsCon.pricesList[0].pricesclass== 3?'selected':''}>餐饮商品</option>
	<option value="2" ${goodsCon.pricesList[0].pricesclass== 2?'selected':''}>商超商品</option>
	<option value="1" ${goodsCon.pricesList[0].pricesclass== 1?'selected':''}>组织单位商品</option>
</select>
查询条件:&nbsp;&nbsp;<input type="text" id="goodscode" name="goodscode" value="${requestScope.goodsCon.goodscode }">
<input class="button" type="button" value="查询" onclick="subgoodsfor()">
<input class="button" type="button" value="价格设置" onclick="setgoodsprices()">
<input class="button" type="button" value="添加商品" onclick="addgoods()">
<input class="button" type="button" value="修改" onclick="editgoods()">
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
		<th>小类</th>
		<th>状态</th>
		<th>品牌</th>
		<th>顺序</th>
		<th>重量</th>
		<th>创建时间</th>
		<th>创建人</th>
		<th>修改时间</th>
		<th>修改人</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.goodsList) != 0 }">
	<c:forEach var="goods" items="${requestScope.goodsList }" varStatus="goodsSta">
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
			<td>${goods.goodsbrand}</td>
			<td>${goods.goodsorder}</td>
			<td>${goods.goodsweight}</td>
			<td>${goods.createtime}</td>
			<td>${goods.creator}</td>
			<td>${goods.updtime}</td>
			<td>${goods.updor}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.goodsList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="14" align="center">
		 <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('1')">第一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>第一页</span>
		 </c:if>
		  <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('${requestScope.pagenow - 1 }')">上一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>上一页</span>
		 </c:if>
		 	
		 	<span>当前第${requestScope.pagenow }页</span>
		 	
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pagenow + 1 }')">下一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>下一页</span>
		 </c:if>
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pageCount }')">最后一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>最后一页&nbsp;</span>
		 </c:if>
		 	<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="pagenow" name="pagenow" value="${requestScope.pagenow }">页</span>
		 	<input type="submit" value="GO" style="width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>
</table>
</form>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
			<h1>添加商品</h1>
			<label><span>商品编码 :</span><input id="addgoodscode" type="text"
				name="goodscode" placeholder="商品编码" value="" /><font>(必填)</font></label>
			<label><span>商品名称 :</span><input id="goodsname" type="text"
				name="goodsname" placeholder="商品名称" /><font>(必填)</font></label>
			<label><span>规格 :</span><input id="goodsunits" type="text"
				name="goodsunits" placeholder="规格" /><font>(必填)</font></label>
			<label><span>小类名称 :</span>
			<select name="goodsclass" id="goodsclass">
				<option value="">请选择</option>
			</select><font>(必填)</font>
			</label>
			<label><span>图片路径 :</span><input id="goodsimage" type="text"
				name="goodsimage" placeholder="图片路径" /></label>
			<label><span>品牌 :</span><input id="goodsbrand" type="text"
				name="goodsbrand" placeholder="品牌" /></label>
			<label><span>种类 :</span><input id="goodstype" type="text"
				name="goodstype" placeholder="种类" /><font>(必填)</font></label>
			<label><span>重量（kg） :</span><input id="goodsweight" type="number"
				name="goodsweight" placeholder="重量" value=0/></label>
			<label><span>顺序 :</span><input id="goodsorder" type="number"
				name="goodsorder" placeholder="顺序" value=0/></label>
			
			<p><label><input type="button"
				class="popup_button" value="提交" onclick="popup_formSub()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="从标品库中选择" onclick="dobiaopin()"/>
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
查询条件:&nbsp;&nbsp;<input type="text" id="scantcondition" name="" value="">
<input class="button" type="button" value="查询" onclick="dobiaopin('1')">
<input class="button" type="button" value="关闭" onclick="closeCdPopup2()">
</div>
	<table class="bordered" id="scant" style="margin: 5% auto 0 auto;">
		<thead>
	    <tr>
	        <th>序号</th>
			<th>商品编码</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>品牌</th>
			<th>小类名称</th>
			<th>点击选择</th>
	    </tr>
	    </thead>
	</table>
</div>
</div>
<script type="text/javascript">
var goodsstatue = '<%=goodsstatue %>';
var goodstype = '${goods.goodstype}';
$(function(){
	if( goodsstatue == '' && goodstype != '裸价商品'){
		$(".nowposition").html("当前位置：商品管理》全部商品");
	} else if(goodsstatue == '上架'){
		$(".nowposition").html("当前位置：商品管理》上架商品");
	} else if(goodsstatue == '下架'){
		$(".nowposition").html("当前位置：商品管理》下架商品");
	} else if(goodstype == '裸价商品'){
		$(".nowposition").html("当前位置：商品管理》裸价商品");
	}
	
	$("#main_form").on("submit",function(){
		checkCondition();
	});
	if('${param.goodsid}'!=''){
		$('#${param.goodsid}').attr("checked",true);
	}
})
//检查查询条件是否变化
function checkCondition(){
	if($("#goodscode").val() != '${requestScope.goodsCon.goodscode }'){
		$("#pagenow").val('1');
	}
}
//提交查询条件
function subgoodsfor(){
	$("#goodscode").val($.trim($("#goodscode").val()));
	checkCondition();
	document.forms[0].submit();
}
//修改商品
function editgoods(){
	var count = 0;
	var itemid;
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		$('#main_form').attr('action','doEditGoods.action');
		$("[name='goodsid']").val(itemid);
		$('#main_form').submit();
	} else if(count == 0){
		alert("请选择商品");
	} else {
		alert("只能选择一个商品");
	}
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
		$('#main_form').attr('action','doGoodsPrices.action');
		$('.setPricesGoodsId').val(itemid);
		$('#main_form').submit();
	} else if(count == 0){
		alert("请选择商品");
	} else {
		alert("只能选择一个商品");
	}
}
//添加商品窗口
function addgoods(){
	$(".elegant-aero [type!='button']").val("");
	$("#addgoodscode").val('${sessionScope.company.companycode }');
	$(".cd-popup").addClass("is-visible");	//弹出窗口
	$.getJSON("getallGoodclass.action",function(data){
		$.each(data,function(i,item){
			$("#goodsclass").append(
				'<option value="'+item.goodsclassid+'">'+item.goodsclassname+'</option>'
			)
		});
	});
}
//关闭添加商品窗口
function close_popup(){
	$(".cd-popup").removeClass("is-visible");	//移除'is-visible' class
}
//关闭标品选择窗口
function closeCdPopup2(){
	$(".cd-popup2").removeClass("is-visible");
}
//商品分页
function fenyeScant(targetPage){
	dobiaopin(targetPage);
}
//弹出标品窗口
function dobiaopin(pagenow){
	$(".cd-popup2").addClass("is-visible");	//弹出标品窗口
	var data = {
			'nowpageScant':pagenow,
			'scantcondition':$.trim($("#scantcondition").val())
	};
	$.getJSON("getallScant.action",data,function(data){
		$("#scant tr:gt(0)").remove();
		$.each(data.Scantlist,function(i,item){
			$("#scant").append(
			'<tr><td>'+(i+1)+'</td>'+
			'<td>'+item.scantcode+'</td>'+
			'<td>'+item.scantname+'</td>'+
			'<td>'+item.scantunits+'</td>'+
			'<td>'+item.scantbrand+'</td>'+
			'<td>'+item.goodsclass.goodsclassname+'</td>'+
			'<td><a class="scant_a" onclick="seleScant(\''
					+item.scantcode+
					'\',\''+item.scantname+
					'\',\''+item.scantunits+
					'\',\''+item.goodsclass.goodsclassname+
					'\',\''+item.scantimage+
					'\',\''+item.scantbrand+
					'\',\''+item.scanttype+
					'\')">选择</a></td></tr>'
			);
		});
		var scantfenye = '<tr><td colspan="7">';
		if(data.nowpageScant > 1){
			scantfenye += '<a onclick=fenyeScant("1")>第一页</a><a onclick=fenyeScant("'+(parseInt(data.nowpageScant)-1)+'")>上一页</a>';
			
		} else {
			scantfenye += '<span>第一页</span><span>上一页</span>';
		}
		scantfenye += '<span>当前第'+data.nowpageScant+'页</span>';
		if(data.nowpageScant < data.pageCountScant){
			scantfenye += '<a onclick=fenyeScant("'+(parseInt(data.nowpageScant)+1)+'")>下一页</a><a onclick=fenyeScant("'+data.pageCountScant+'")>最后一页</a>';
		} else {
			scantfenye += '<span>下一页</span><span>最后一页&nbsp;</span>';
		}
		scantfenye += '<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="nowpageScant" value="'+
			data.nowpageScant+'">页</span>'+
		 	'<input onclick=scantPageTo("'+data.pageCountScant+'") type="button" value="GO" class="fenyelan_button">'+
	 		'<span>一共 '+data.countScant+' 条数据</span>';
		$("#scant").append(scantfenye);
	});
}
//标品跳转到x页
function scantPageTo(pageCount){
	dobiaopin($("#nowpageScant").val());
}
//选择标品
function seleScant(scantcode,scantname,scantunits,goodsclassname,scantimage,scantbrand,scanttype){
	$("#addgoodscode").val('${sessionScope.company.companycode }'+scantcode);
	$("#goodsname").val(scantname);
	$("#goodsunits").val(scantunits);
	$("#goodsclass option").each(function(i,item){
		if($(item).text() == goodsclassname){
			$(item).attr("selected",true);
		}
	});
	$("#goodsbrand").val(scantbrand);
	$("#goodsimage").val(scantimage);
	$("#goodstype").val(scanttype);
	$(".cd-popup2").removeClass("is-visible");	//移除'is-visible' class
	
}
//提交添加商品的表单
function popup_formSub(){
	if($("#goodsclass").val() == "" || $("#goodsclass").val() == null){
		alert("小类名称不能为空");
		return;
	}
	var count = 0;
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if($(item).val() == null || $(item).val() == '' ){
			if($(item).attr('placeholder') != '品牌' && $(item).attr('placeholder') != '顺序'&& $(item).attr('placeholder') != '重量' 
					&& $(item).attr('placeholder') != '图片路径'){
				alert($(item).attr('placeholder') + '不能为空');
				count++;
				return false;
			}
		}
	});
	if(count == 0){
		$.ajax({
			url:"addGoods.action",
			type:"post",
			data:{
				"creator":"${sessionScope.company.companyshop }",
				"goodscompany":"${sessionScope.company.companyid }",
				"goodscode":$("#addgoodscode").val(),
				"goodsname":$("#goodsname").val(),
				"goodsunits":$("#goodsunits").val(),
				"goodsclass":$("#goodsclass").val(),
				"goodsbrand":$("#goodsbrand").val(),
				"goodstype":$("#goodstype").val(),
				"goodsorder":$("#goodsorder").val(),
				"goodsimage":$.trim($("#goodsimage").val()),
				"goodsweight":$("#goodsweight").val()
			},
			success:function(data){
				if(data.message=='success'){
					alert("商品添加成功!");
					window.location.href = "doGoodsPrices.action?goodsid="+data.goods.goodsid+"&pagenow=1&goodscompany="+
							data.goods.goodscompany;
				} else if(data.message == 'fail1'){
					alert("操作失败,没有添加商品。");
				} else if(data.message == 'fail2'){
					alert("商品编号和规格重复，添加失败。");
				}
			},
			error:function(data){
				alert('操作失败!');
			}
		});
	}
}
//修改商品状态
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
				alert(data.editResult);									//返回的提示信息
				if(data.editResult == '修改成功！'){
					$(".edit_goodsStatus"+goodsid).text(goodsstatue);	
				}
			});
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition();
	document.forms[0].submit();
}
</script>
</body>
</html>