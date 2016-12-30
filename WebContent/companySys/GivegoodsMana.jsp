<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
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
.elegant-aero label>font{
	font-weight: bold;
	color: #F66B45;
}
</style>
</head>
<body>
<form id="main_form" action="allCarnivalGoods.action" method="post">
<input type="hidden" name="bkgoodscompany" value="${requestScope.bkgoodsCon.bkgoodscompany }">
<input type="hidden" name="bkgoodstype" value="买赠">
<input type="hidden" name="bkgoodsid" value="">
<div class="nowposition">当前位置：商品管理》买赠商品</div>
<div class="navigation">
客户类型:&nbsp;&nbsp;
<select name="bkgoodsscope">
	<option value="" >全部商品</option>
	<option value="3" ${fn:contains(requestScope.bkgoodsCon.bkgoodsscope,'3')?'selected':''}>餐饮商品</option>
	<option value="2" ${fn:contains(requestScope.bkgoodsCon.bkgoodsscope,'2')?'selected':''}>商超商品</option>
	<option value="1" ${fn:contains(requestScope.bkgoodsCon.bkgoodsscope,'1')?'selected':''}>组织单位商品</option>
</select>
查询条件:&nbsp;&nbsp;<input type="text" id="query_Con" name="bkgoodscode" value="${requestScope.bkgoodsCon.bkgoodscode }">
<input class="button" type="button" value="查询" onclick="bkgoodsjump()">
<input class="button" type="button" value="添加" onclick="addbkgoods()">
<input class="button" type="button" value="修改" onclick="editbkgoods()">
<input class="button" type="button" value="删除" onclick="removeBkgoods()">
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
		<th>品牌</th>
		<th>售价</th>
		<th>重量(kg)</th>
		<th>描述</th>
		<th>状态</th>
		<th>顺序</th>
		<th>修改时间</th>
		<th>修改人</th>
		<th>创建时间</th>
		<th>创建人</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.bkgoodsList) != 0 }">
	<c:forEach var="bkgoods" items="${requestScope.bkgoodsList }" varStatus="bkgoodsSta">
		<tr>
			<td><input type="checkbox" id="${bkgoods.bkgoodsid}"></td>
			<td><c:out value="${bkgoodsSta.count}"></c:out></td>
			<td>${bkgoods.bkgoodscode}</td>
			<td>${bkgoods.bkgoodsname}</td>
			<td>${bkgoods.bkgoodsunits}</td>
			<td>${bkgoods.bkgoodsclass}</td>
			<td>${bkgoods.bkgoodsbrand}</td>
			<td>${bkgoods.bkgoodsprice}</td>
			<td>${bkgoods.bkgoodsweight}</td>
			<td>${bkgoods.bkgoodsdetail}</td>
			<td>${bkgoods.bkgoodsstatue}</td>
			<td>${bkgoods.bkgoodsseq}</td>
			<td>${bkgoods.bkgoodsupdtime}</td>
			<td>${bkgoods.bkgoodsupdor}</td>
			<td>${bkgoods.bkcreatetime}</td>
			<td>${bkgoods.bkcreator}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.bkgoodsList)==0 }">
		<tr><td colspan="18" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="18" align="center">
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
		 	<input type="button" onclick="bkgoodsjump()" value="GO" class="fenyelan_button">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>       
</table>
</form>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
			<h1>添加买赠商品</h1>
			<input type="hidden" id="bkgoodsimage" value="">
			<p><span>客户范围 :</span>
			餐饮客户:
			<input type="checkbox" name="bkgoodsscope" code="3" checked="checked"/>
			商超客户 :
			<input type="checkbox" name="bkgoodsscope" code="2" checked="checked"/>
			组织单位客户 :
			<input type="checkbox" name="bkgoodsscope" code="1" checked="checked"/>
			</p>
			<label><span>编码 :</span><input id="bkgoodscode" type="text"
				name="bkgoodscode" placeholder="编码" /><font>(必填)</font></label>
			<label><span>名称 :</span><input id="bkgoodsname" type="text"
				name="bkgoodsname" placeholder="名称" /><font>(必填)</font></label>
			<label><span>规格 :</span><input id="bkgoodsunits" type="text"
				name="bkgoodsunits" placeholder="规格" /><font>(必填)</font></label>
			<!-- <label><span>小类名称 :</span>
			<select name="bkgoodsclass" id="bkgoodsclass">
				<option value="">请选择</option>
			</select>
			</label> -->
			<label><span>单位 :</span><input id="bkgoodsunit" type="text"
				name="bkgoodsunit" placeholder="单位" /><font>(必填)</font></label>
			<label><span>品牌 :</span><input id="bkgoodsbrand" type="text"
				name="bkgoodsbrand" placeholder="品牌" /></label>
			<label><span>售价 :</span><input id="bkgoodsorgprice" type="number"
				name="bkgoodsorgprice" placeholder="售价" /><font>(必填)</font></label>
			<label><span>重量 :</span><input id="bkgoodsweight" type="number"
				name="bkgoodsweight" placeholder="重量" /></label>
			<label><span>个人限量 :</span><input id="bkgoodsnum" type="number"
				name="bkgoodsnum" placeholder="个人限量" /><font>(必填)</font></label>
			<label><span>顺序 :</span><input id="bkgoodsseq" type="number"
				name="bkgoodsseq" placeholder="顺序" /></label>
			<label><span>买赠描述 :</span><textarea name="bkgoodsdetail"></textarea><font>(必填)</font></label>
			<p><label><input type="button"
				class="popup_button" value="提交" onclick="popup_formSub()"/>
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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	$("#main_form").submit(function(){
		checkCondition();
	});
})
//检查查询条件是否变化
function checkCondition(){
	if($("#query_Con").val() != '${requestScope.bkgoodsCon.bkgoodscode }'){
		$("#pagenow").val(1);
	}
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition();
	document.forms[0].submit();
}
//商品分页
function fenyeGoods(targetPage){
	loadGoodsData(targetPage);
}
//买增商品跳转到第X页
function bkgoodsjump(){
	checkCondition();
	document.forms[0].submit();
}
//跳转到第X页
function goodsPageTo(pageCountGoods){
	var pagenowGoods = $("#pagenowGoods").val();
	loadGoodsData(pagenowGoods);
}
//弹出添加买赠商品的窗口
function addbkgoods(){
	$(".elegant-aero [type!='button']").val("");
	$(".cd-popup").addClass("is-visible");	//弹出窗口
}
//关闭添加买赠商品窗口
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
//加载商品数据
function loadGoodsData(pagenowGoods){
	var data = {
				'goodscompany':'${sessionScope.loginInfo.companyid }',
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
			'<td><a class="scant_a" onclick="seleScant(\''
					+item.goodscode+
					'\',\''+item.goodsname+
					'\',\''+item.goodsunits+
					'\',\''+typeNullFoString(item.goodsbrand)+
					'\',\''+item.goodsimage+
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
//选择商品
function seleScant(bkgoodscode,bkgoodsname,bkgoodsunits,bkgoodsbrand,bkgoodsimage){
	$("#bkgoodscode").val(bkgoodscode);
	$("#bkgoodsname").val(bkgoodsname);
	$("#bkgoodsunits").val(bkgoodsunits);
	$("#bkgoodsbrand").val(bkgoodsbrand);
	$("#bkgoodsimage").val(bkgoodsimage);
	$(".cd-popup2").removeClass("is-visible");	//移除'is-visible' class
	
}
//提交添加商品的表单
function popup_formSub(){
	var data = '{';
		data += '"bkgoodsclass":"买赠商品","bkgoodstype":"买赠",';
	if($(".elegant-aero textarea").val() == null || $(".elegant-aero textarea").val() == ""){
		alert("买赠描述不能为空!");
		return;
	} else {
		data += '"'+$(".elegant-aero textarea").attr("name") + '":"' + $(".elegant-aero textarea").val() + '",';
	}
	var count = 0;
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if(($(item).val() == null || $(item).val() == '') &&
				$(item).attr('placeholder') != '顺序' && $(item).attr('placeholder') != '重量'&& $(item).attr('placeholder') != '品牌'){
			alert($(item).attr('placeholder') + '不能为空!');
			count++;
			return false;
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	var bkgoodsscope = '';
	$(".elegant-aero [name='bkgoodsscope']").each(function(i,item){
		if(item.checked == true){
			bkgoodsscope += $(item).attr("code");
		}
	});
	data += '"bkgoodsscope":"' + bkgoodsscope +'",';
	if(count == 0){
		data += '"bkgoodsimage":"'+$("#bkgoodsimage").val()+'","bkgoodscompany":"${requestScope.bkgoodsCon.bkgoodscompany }","creator":"${sessionScope.loginInfo.companyshop }"}';
		$.getJSON('addBkgoods.action',JSON.parse(data),function(data){
			if(data.message=='success'){
				alert('添加成功!');
				document.forms[0].submit();
			} else if(data.message=='fail2'){
				alert('商品编号和规格重复，添加失败。');
			}
			
		});
	}
}
//条件查询
function queryGooods(){
	loadGoodsData(1);
}
//修改买赠商品
function editbkgoods(){
	var count = 0;
	var itemid;
	$(".bordered [type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		$('#main_form').attr('action','doEditBkgoods.action');
		$('[name="bkgoodsid"]').val(itemid);
		$('#main_form').submit();
	} else if(count == 0){
		alert("请选择买赠商品!");
	} else {
		alert("只能选择一个买赠商品!");
	}
}
//删除买赠商品
function removeBkgoods(){
	var count = 0;
	var itemid;
	$(".bordered [type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		if(confirm("是否删除")){
			$.post('removeBkgoods.action',{'bkgoodsid':itemid},function(data){
				if(data == 'ok'){
					alert("删除成功!");
					window.location.reload();
				} else {
					alert("要删除的买赠商品不存在!");
				}
			});
		}
	} else if(count == 0){
		alert("请选择买赠商品!");
	} else {
		alert("只能选择一个买赠商品!");
	}
}
</script>
</body>
</html>