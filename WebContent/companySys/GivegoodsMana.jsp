<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
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
<form id="main_form" action="allGiveGoods.action" method="post">
<input type="hidden" name="givegoodscompany" value="${requestScope.givegoodsCon.givegoodscompany }">
<input type="hidden" name="givegoodsid" value="">
<div class="nowposition">当前位置：商品管理》买赠商品</div>
<div class="navigation">
<input class="button" type="button" value="添加" onclick="addgivegoods()">
<input class="button" type="button" value="修改" onclick="editGiveGoods()">
<input class="button" type="button" value="删除" onclick="removeGiveGoods()">
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
		<th>售价</th>
		<th>描述</th>
		<th>状态</th>
		<th>顺序</th>
		<th>创建时间</th>
		<th>创建人</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.givegoodsList) != 0 }">
	<c:forEach var="givegoods" items="${requestScope.givegoodsList }" varStatus="givegoodsSta">
		<tr>
			<td><input type="checkbox" id="${givegoods.givegoodsid}"></td>
			<td><c:out value="${givegoodsSta.count}"></c:out></td>
			<td>${givegoods.givegoodscode}</td>
			<td>${givegoods.givegoodsname}</td>
			<td>${givegoods.givegoodsunits}</td>
			<td>${givegoods.givegoodsclass}</td>
			<td>${givegoods.givegoodsprice}</td>
			<td>${givegoods.givegoodsdetail}</td>
			<td>${givegoods.givegoodsstatue}</td>
			<td>${givegoods.givegoodsseq}</td>
			<td>${givegoods.createtime}</td>
			<td>${givegoods.creator}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.givegoodsList)==0 }">
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
		 	<input type="button" onclick="givegoodsjump()" value="GO" class="fenyelan_button">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>       
</table>
</form>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero">
			<h1>添加买赠商品</h1>
			<input type="hidden" id="givegoodsimage" value="">
			<p><span>客户范围 :</span>
			餐饮客户:
			<input type="checkbox" name="givegoodsscope" value="1" checked/>
			商超客户 :
			<input type="checkbox" name="givegoodsscope" value="2" checked/>
			组织单位客户 :
			<input type="checkbox" name="givegoodsscope" value="3" checked/>
			</p>
			<label><span>编码 :</span><input id="givegoodscode" type="text"
				name="givegoodscode" placeholder="编码" /></label>
			<label><span>名称 :</span><input id="givegoodsname" type="text"
				name="givegoodsname" placeholder="名称" /></label>
			<label><span>规格 :</span><input id="givegoodsunits" type="text"
				name="givegoodsunits" placeholder="规格" /></label>
			<!-- <label><span>小类名称 :</span>
			<select name="givegoodsclass" id="givegoodsclass">
				<option value="">请选择</option>
			</select>
			</label> -->
			<label><span>单位 :</span><input id="givegoodsunit" type="text"
				name="givegoodsunit" placeholder="单位" /></label>
			<label><span>售价 :</span><input id="givegoodsprice" type="number"
				name="givegoodsprice" placeholder="售价" /></label>
			<label><span>个人限量 :</span><input id="givegoodsnum" type="number"
				name="givegoodsnum" placeholder="个人限量" /></label>
			<label><span>顺序 :</span><input id="givegoodsseq" type="number"
				name="givegoodsseq" placeholder="顺序" /></label>
			<label><span>买赠描述 :</span><textarea name="givegoodsdetail"></textarea></label>
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
<script type="text/javascript">
$(function(){
	$("#main_form").on("submit",function(){
		checkCondition();
	});
})
//检查查询条件是否变化
function checkCondition(){
	if(parseInt($("#pagenow").val()) > '${requestScope.pageCount }' ){
		$("#pagenow").val('${requestScope.pageCount }');
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
function givegoodsjump(){
	if(parseInt($("#pagenow").val()) > '${requestScope.pageCount }' ){
		$("#pagenow").val('${requestScope.pageCount }');
	}
	document.forms[0].submit();
}
//跳转到第X页
function goodsPageTo(pageCountGoods){
	var pagenowGoods = $("#pagenowGoods").val();
	if(parseInt(pagenowGoods) > parseInt(pageCountGoods)){
		pagenowGoods = pageCountGoods;
	}
	loadGoodsData(pagenowGoods);
}
//弹出添加买赠商品的窗口
function addgivegoods(){
	$(".elegant-aero [type!='button']").val("");
	$(".cd-popup").addClass("is-visible");	//弹出窗口
	/* $.getJSON("getallGoodclass.action",function(data){
		$.each(data,function(i,item){
			$("#givegoodsclass").append(
				'<option value="'+item.goodsclassid+'">'+item.goodsclassname+'</option>'
			)
		});
	}); */
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
			'<td><a class="scant_a" onclick="seleScant(\''
					+item.goodscode+
					'\',\''+item.goodsname+
					'\',\''+item.goodsunits+
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
function seleScant(givegoodscode,givegoodsname,givegoodsunits,givegoodsimage){
	$("#givegoodscode").val(givegoodscode);
	$("#givegoodsname").val(givegoodsname);
	$("#givegoodsunits").val(givegoodsunits);
	/* $("#givegoodsclass option").each(function(i,item){
		if($(item).text() == goodsclassname){
			$(item).attr("selected",true);
		}
	}); */
	$("#givegoodsimage").val(givegoodsimage);
	$(".cd-popup2").removeClass("is-visible");	//移除'is-visible' class
	
}
//提交添加商品的表单
function popup_formSub(){
	var data = '{';
		data += '"givegoodsclass":"买赠商品",';
	if($(".elegant-aero textarea").val() == null || $(".elegant-aero textarea").val() == ""){
		alert("买赠描述不能为空");
		return;
	} else {
		data += '"'+$(".elegant-aero textarea").attr("name") + '":"' + $(".elegant-aero textarea").val() + '",'
	}
	var count = 0;
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if($(item).val() == null || $(item).val() == '' ){
			if($(item).attr('placeholder') != '顺序'){
				alert($(item).attr('placeholder') + '不能为空');
				count++;
				return false;
			}
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	var givegoodsscope = '';
	$("input[name='givegoodsscope']").each(function(i,item){
		if(item.checked == true){
			givegoodsscope += $(item).val();
		}
	});
	data += '"givegoodsscope":"' + givegoodsscope +'",';
	if(count == 0){
		data += '"givegoodsimage":"'+$("#givegoodsimage").val()+'","givegoodscompany":"${requestScope.givegoodsCon.givegoodscompany }","creator":"${sessionScope.company.companyshop }"}';
		$.getJSON('addGiveGoods.action',JSON.parse(data),function(){
			alert('添加成功');
			document.forms[0].submit();
		});
	}
}
//条件查询
function queryGooods(){
	loadGoodsData(1);
}
//修改买赠商品
function editGiveGoods(){
	var count = 0;
	var itemid;
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		//window.location.href = "doGoodsPrices.action?goodsid="+itemid+"&pagenow=${requestScope.pagenow}";
		$('#main_form').attr('action','doEditGiveGoods.action');
		$('[name="givegoodsid"]').val(itemid);
		$('#main_form').submit();
	} else if(count == 0){
		alert("请选择秒杀商品");
	} else {
		alert("只能选择一个秒杀商品");
	}
}
//删除买赠商品
function removeGiveGoods(){
	var count = 0;
	var itemid;
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemid = $(item).attr("id");
			count++;
		}
	});
	if(count > 0 && count < 2){
		if(confirm("是否删除")){
			$.post('removeGiveGoods.action',{'givegoodsid':itemid},function(data){
				if(data == 'ok'){
					alert("删除成功");
					window.location.reload();
				} else {
					alert("删除失败");
				}
			});
		}
	} else if(count == 0){
		alert("请选择秒杀商品");
	} else {
		alert("只能选择一个秒杀商品");
	}
}
</script>
</body>
</html>