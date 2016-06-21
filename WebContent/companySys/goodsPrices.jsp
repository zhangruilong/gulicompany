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
	background: #FBF1E5;
	padding: 20px 20px 20px 20px;
	font: 12px Arial, Helvetica, sans-serif;
	color: #666;
	width: 700px;
}

.elegant-aero h1 {
	font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
	padding: 10px 10px 10px 20px;
	display: block;
	background: #F6B543;
	border-bottom: 1px solid white;
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


.elegant-aero input[type="text"],input[type="number"]{
	color: #888;
	padding: 0px 0px 0px 5px;
	border: 1px solid #C5E2FF;
	background: #FBFBFB;
	outline: 0;
	-webkit-box-shadow: inset 0px 1px 6px #ECF3F5;
	box-shadow: inset 0px 1px 6px #ECF3F5;
	font: 200 12px/25px Arial, Helvetica, sans-serif;
	height: 30px;
	width: 63px;
	line-height: 15px;
	margin: 2px 6px 16px 0px;
}


.elegant-aero .button {
	padding: 10px 30px 10px 30px;
	background: #EE8704;
	border: none;
	color: #FFF;
	box-shadow: 1px 1px 1px #4C6E91;
	-webkit-box-shadow: 1px 1px 1px #4C6E91;
	-moz-box-shadow: 1px 1px 1px #4C6E91;
	text-shadow: 1px 1px 1px #5079A3;
}

.elegant-aero .button:hover {
	background: #FF9802;
}

.elegant-aero p{

}
h1 .title_goodsinfo {
	font-size: 12px;
	font-weight: normal;
	line-height: 22px;
}
h1 .title_goodsinfo span{
	font-weight: bold;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="elegant-aero">
			<c:if test="${requestScope.editPriGoods.pricesList[0].pricesid !=null }">
				<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price">
					<input type="hidden" name="pricesid" value="${price.pricesid }">
					<input type="hidden" name="pricesprice2" value="">
				</c:forEach>
			</c:if>
			<h1>商品价格设置
			<div class="title_goodsinfo">
			<span>商品名称:&nbsp;</span>${requestScope.editPriGoods.goodsname }&nbsp;&nbsp;
			<span>编码:&nbsp;</span>${requestScope.editPriGoods.goodscode }&nbsp;&nbsp;
			<span>规格:&nbsp;</span>${requestScope.editPriGoods.goodsunits }
			</div>
			</h1>
			<table>
			<tr><td colspan="3"><p><span>单位:<input size="4" type="text" id="pricesunit" name="pricesunit" value="${requestScope.editPriGoods.pricesList[0].pricesunit }">
			<!-- 套装单位:<input size="4" type="text" id="pricesunit2" name="pricesunit2" value="${requestScope.editPriGoods.pricesList[0].pricesunit2 }"> --></span></p> </td></tr>
			<tr><td colspan="3"><p style="margin-top: 0px;"><span>餐饮客户价格 :&nbsp;&nbsp;&nbsp;&nbsp;
			价格启用:<input type="checkbox" name="creator"
			<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price">
				<c:if test="${price.pricesclass == '3' && price.priceslevel == 3 && price.creator == '启用'}">checked</c:if>
			</c:forEach>
			style="vertical-align:middle"></span></p></td></tr>
			<tr>	
				
				<td><label><span>售价等级3 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 3}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="1" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 2}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="2" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 1}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="3" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
			</tr>
			<!-- <tr>
				<td><label><span>套装价等级3 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="10" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="11" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '3' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="12" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
			</tr> -->
			<tr><td colspan="3"><p><span> 商超客户价格 :&nbsp;&nbsp;&nbsp;&nbsp;
			价格启用:<input type="checkbox" name="creator" 
			<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price">
				<c:if test="${price.pricesclass == '2' && price.priceslevel == 3 && price.creator == '启用'}">checked</c:if>
			</c:forEach>
			 style="vertical-align:middle"></span></p></td></tr>
			<tr>
				<td><label><span>售价等级3 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 3}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="4" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 2}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="5" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 1}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="6" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
			</tr>
			<!--<tr>
				<td><label><span>套装价等级3 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="13" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="14" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '2' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="15" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
			</tr> -->
			<tr><td colspan="3"><p><span> 组织单位客户价格 :&nbsp;&nbsp;&nbsp;&nbsp;
			价格启用:<input type="checkbox" name="creator" 
			<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price">
				<c:if test="${price.pricesclass == '1' && price.priceslevel == 3 && price.creator == '启用'}">checked</c:if>
			</c:forEach>
			 style="vertical-align:middle"></span></p></td></tr>
			<tr>
				<td><label><span>售价等级3 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 3}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="7" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 2}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="8" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
				<td><label><span>售价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 1}">${price.pricesprice }</c:if></c:forEach>" 
				size="5" id="9" type="number" name="pricesprice" placeholder="售价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit }</c:if></span></label></td>
			 </tr>
			<!-- <tr>
				<td><label><span>套装价等级3 :</span><input  
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 3}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="16" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级2 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 2}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="17" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
				<td><label><span>套装价等级1 :</span><input 
				value="<c:forEach items="${requestScope.editPriGoods.pricesList }" var="price"><c:if test="${price.pricesclass == '1' && price.priceslevel == 1}">${price.pricesprice2 }</c:if></c:forEach>" 
				size="5" id="18" type="text" name="pricesprice2" placeholder="套装价" />
				<span><c:if test="${requestScope.editPriGoods.pricesList[0].pricesunit2 !=null }">/${requestScope.editPriGoods.pricesList[0].pricesunit2 }</c:if></span></label></td>
			</tr> -->
			
			</table>
			<span>&nbsp;</span> <input type="button" class="button" value="保存" onclick="addData()"/> 
			<input style="margin-left: 30px;" type="button" class="button" value="返回" onclick="javascript:window.history.back()"/>
			
	</div>
	<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	$(function(){
		if('${requestScope.message}' == '添加商品'){
			$("[name='creator']").attr("checked",true);
		}
	});
	//添加或修改价格(原)
	function addData(){
		//window.location.reload();
		//return;
		var val1 = $("#1").val();
		var val10 = $("#10").val();
		var pricesunit = $("#pricesunit").val();		//单位
		if(val1 == null || val1 == "" ){
			alert("等级为 3 的餐饮客户价格不能为空.");
			return;
		}
		if(pricesunit == null || pricesunit == "" ){
			alert("单位不能为空");
			return;
		}
		var allInput = $("input");
		//设置默认价格
		$.each(allInput,function(i,item){
			var itemVal = $(item);
					var itemId = itemVal.attr("id");
				if((itemVal.val() == null || itemVal.val() == "") && parseInt(itemId) < 10){
					var ofVal = $("#"+(itemId-1)).val();
					itemVal.val(ofVal);
				}
				if(val10 != null && val10 != ""){
					if((itemVal.val() == null || itemVal.val() == "") && parseInt(itemId) > 10){
						var ofVal = $("#"+(itemId-1)).val();
						itemVal.val(ofVal);
					}
				}
		}); 
		//提交信息
		var pricesid = '';
		var pricesprice = '';
		var pricesprice2 = '';
		var creator = '';
		if($("[name='pricesid']") != null){
			$("[name='pricesid']").each(function(i,item){
				if(i < ($("[name='pricesid']").length -1)){
					pricesid += $(item).val()+',';
				} else {
					pricesid += $(item).val();
				}
			})
		}
		$("[name='pricesprice']").each(function(i,item){
			if(i < ($("[name='pricesprice']").length -1)){
				pricesprice += $(item).val()+',';
			} else {
				pricesprice += $(item).val();
			}
		})
		$("[name='pricesprice2']").each(function(i,item){
			if(i < ($("[name='pricesprice2']").length -1)){
				pricesprice2 += $(item).val()+',';
			} else {
				pricesprice2 += $(item).val();
			}
		})
		$("[name='creator']").each(function(i,item){
			if(i < ($("[name='creator']").length -1)){
				if(item.checked == true){
					creator += '1,'
				} else {
					creator += '0,'
				}
			} else {
				if(item.checked == true){
					creator += '1'
				} else {
					creator += '0'
				}
			}
		});
		
		$.ajax({
				url:"addGoodsPrices.action",
				data:{
					"pricesid":pricesid,
					"strpricesprice":pricesprice,
					"strpricesprice2":pricesprice2,
					"goodsid":'${requestScope.editPriGoods.goodsid }',
					"pricesgoods":'${requestScope.editPriGoods.goodsid }',
					"goodscompany":'${requestScope.editPriGoods.goodscompany }',
					'pricesunit':$("#pricesunit").val(),
					'pricesunit2':$("#pricesunit2").val(),
					'creator':creator
				},
				success: function(data){
						alert('价格已更新！');
						if('${param.pagefor}' == 'canyinGoodsPage'){
							window.location.href = 'allCanyinGoods.action?goodscompany=${requestScope.editPriGoods.goodscompany }'+
							'&goodscode=${requestScope.goodsCon.goodscode }&goodsstatue=${requestScope.goodsCon.goodsstatue }'+
							'&goodsid=${requestScope.goodsCon.goodsid }&pagenow=${requestScope.pagenow }';
						} else {
							window.location.href = 'allGoods.action?goodscompany=${requestScope.editPriGoods.goodscompany }'+
									'&goodscode=${requestScope.goodsCon.goodscode }&goodsstatue=${requestScope.goodsCon.goodsstatue }'+
									'&goodsid=${requestScope.goodsCon.goodsid }&pagenow=${requestScope.pagenow }';
						}
					},
				error:function(){
					
				}
		});
	}
	//添加或修改价格
	function addOrEditPrice(){
		var val1 = $("#1").val();
		var val10 = $("#10").val();
		var pricesunit = $("#pricesunit").val();		//单位
		if(val1 == null || val1 == "" ){
			alert("等级为 3 的餐饮客户价格不能为空.");
			return;
		}
		if(pricesunit == null || pricesunit == "" ){
			alert("单位不能为空");
			return;
		}
		var allInput = $("input");
		//设置默认价格
		$.each(allInput,function(i,item){
			var itemVal = $(item);
					var itemId = itemVal.attr("id");
				if((itemVal.val() == null || itemVal.val() == "") && parseInt(itemId) < 10){
					var ofVal = $("#"+(itemId-1)).val();
					itemVal.val(ofVal);
				}
				if(val10 != null && val10 != ""){
					if((itemVal.val() == null || itemVal.val() == "") && parseInt(itemId) > 10){
						var ofVal = $("#"+(itemId-1)).val();
						itemVal.val(ofVal);
					}
				}
		}); 
		//添加价格参数
		var pricesList = new Array();
		$("[name='pricesprice']").each(function(i,item){
			if(i < 3){
				var pricesclass = '3';	
			} else if(i>=3 && i<6){
				var pricesclass = '2';
			} else if(i>=6 && i<9){
				var pricesclass = '1';
			}
			var priceslevel = 3-(i%3);
			if(i < 3){
				if($("[name='creator']")[0].checked){
					var creator = '1';	
				} else {
					var creator = '0';	
				}
			} else if(i>=3 && i<6){
				if($("[name='creator']")[1].checked){
					var creator = '1';	
				} else {
					var creator = '0';	
				}
			} else if(i>=6 && i<9){
				if($("[name='creator']")[2].checked){
					var creator = '1';	
				} else {
					var creator = '0';	
				}
			}
			var price = {
					pricesid:$("[name='pricesid']").eq(i).val(),
					pricesprice:$(item).val(),
					pricesgoods:'${requestScope.editPriGoods.goodsid }',
					pricesclass:pricesclass,
					priceslevel:priceslevel,
					pricesunit:pricesunit,
					pricesprice2:$("[name='pricesprice2']").eq(i).val(),
					pricesunit2:$("#pricesunit2").val(),
					creator:creator,
			}
			pricesList.push(price);
		});
		if('${requestScope.editPriGoods.pricesList[0].pricesid != null }' == 'true' ){		//判断是添加商品价格还是修改商品价格
			var isAdd = 'no';			//不是添加
		} else {
			var isAdd = 'yes';			//是添加
		}
		var data = {
				goodsid:'${requestScope.editPriGoods.goodsid }',
				pricesList:pricesList
		};
		$.ajax({
			url : "setGoodsPrices.action?isAdd="+isAdd,
			type : "post",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data),
			success : function(data){
				alert("操作成功!");
			},
			error : function(data){
				alert('操作失败,要修改的商品价格不存在.');
			}
		});
	}
	</script>
</body>
</html>