<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%String message = (String)request.getAttribute("message"); %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/formsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<style type="text/css">
.button{
	margin-left: 40px;
}
</style>
</head>
<body>
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
			<h1>商品信息</h1>
			<label><span>商品编码 :</span><input id="goodscode" type="text" value="${requestScope.editGoods.goodscode }"
				name="goodscode" placeholder="商品编码" /></label>
			<label><span>商品名称 :</span><input id="goodsname" type="text" value="${requestScope.editGoods.goodsname }"
				name="goodsname" placeholder="商品名称" /></label>
			<label><span>规格 :</span><input id="goodsunits" type="text" value="${requestScope.editGoods.goodsunits }"
				name="goodsunits" placeholder="规格" /></label>
			<!-- <label><span>描述 :</span><input id="goodsdetail" type="text" value="${requestScope.editGoods.goodsdetail }"
				name="goodsdetail" placeholder="描述" /></label> -->
			<label><span>小类名称 :</span>
			<select name="goodsclass" id="goodsclass">
				<option value="">请选择</option>
			</select>
			</label>
			<label><span>图片路径 :</span><input id="goodsimage" type="text" value="${requestScope.editGoods.goodsimage }"
				name="goodsimage" placeholder="图片路径" /></label>
			<label><span>品牌 :</span><input id="goodsbrand" type="text" value="${requestScope.editGoods.goodsbrand }"
				name="goodsbrand" placeholder="品牌" /></label>
			<label><span>种类 :</span><input id="goodstype" type="text" value="${requestScope.editGoods.goodstype }"
				name="goodstype" placeholder="种类" /></label>
			<label><span>顺序 :</span><input id="goodsorder" type="number" value="${requestScope.editGoods.goodsorder }"
				name="goodsorder" placeholder="顺序" /></label>
			<p><input type="button"
				class="button" value="保存修改" onclick="saveEdit()"/>
			<input type="button"
				class="button" value="返回" onclick="javascript:history.go(-1)"/>
			</p>
		</form>
	</div>
<script type="text/javascript">
$(function(){
	$.getJSON("getallGoodclass.action",function(data){
		$.each(data,function(i,item){
			var opt = '<option ';
			if(item.goodsclassname == '${requestScope.editGoods.gClass.goodsclassname}'){
				opt += ' selected="selected" value="'+item.goodsclassid+'">'+item.goodsclassname+'</option>';
			} else {
				opt += 'value="'+item.goodsclassid+'">'+item.goodsclassname+'</option>';
			}
			$("#goodsclass").append(opt);
		});
	});
});
//保存修改
function saveEdit(){
	var data = '{';
	var count = 0;
	if($("#goodsclass").val() == null || $("#goodsclass").val() == '' ){
		alert("小类不能为空");
		return;
	}
	data += '"timegoodsstatue":"' +$("[name='timegoodsstatue']").val() +'",';
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if($(item).val() == null || $(item).val() == '' ){
			if($(item).attr('placeholder') != '品牌' && $(item).attr('placeholder') != '顺序'&& $(item).attr('placeholder') != '图片路径'){
				alert($(item).attr('placeholder') + '不能为空');
				count++;
				return false;
			} else {
				data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
			}
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	if(count == 0){
		data += '"goodsid":"${requestScope.editGoods.goodsid }"}';
		$.post('editGoods.action',JSON.parse(data),function(data){
			if(data == 'ok'){
				alert('修改成功');
				if('${param.pagefor}' == 'canyinGoodsPage'){
					window.location.href = 'allCanyinGoods.action?goodscompany=${sessionScope.company.companyid}&'+
					'pagenow=${requestScope.pagenow}&goodscode=${requestScope.goodsCon.goodscode}&'+
					'goodsstatue=${requestScope.goodsCon.goodsstatue}&pricesList[0].pricesclass=${requestScope.goodsCon.pricesList[0].pricesclass}';
				} else {
					window.location.href = 'allGoods.action?goodscompany=${sessionScope.company.companyid}&'+
					'pagenow=${requestScope.pagenow}&goodscode=${requestScope.goodsCon.goodscode}&'+
					'goodsstatue=${requestScope.goodsCon.goodsstatue}&pricesList[0].pricesclass=${requestScope.goodsCon.pricesList[0].pricesclass}';
				}
			} else {
				alert('修改失败');
			}
		});
	}
}
</script>
</body>
</html>