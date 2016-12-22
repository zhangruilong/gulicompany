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
.elegant-aero p span{
	font-weight: bold;
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
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
		<input type="hidden" name="bkgoodscompany" value="${requestScope.editBkgoods.bkgoodscompany }">
			<h1>秒杀商品信息</h1>
			<p><span>客户范围 :</span>&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="bkgoodsscope" value="3" ${fn:contains(requestScope.editBkgoods.bkgoodsscope,'3')?'checked':'' }/>
			餐饮客户&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="bkgoodsscope" value="2" ${fn:contains(requestScope.editBkgoods.bkgoodsscope,'2')?'checked':'' }/>
			商超客户 &nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="bkgoodsscope" value="1" ${fn:contains(requestScope.editBkgoods.bkgoodsscope,'1')?'checked':'' }/>
			组织单位客户 &nbsp;&nbsp;&nbsp;
			</p>
			<label><span>编码 :</span><input id="bkgoodscode" type="text"
				name="bkgoodscode" value="${requestScope.editBkgoods.bkgoodscode }" placeholder="编码" /><font>(必填)</font></label>
			<label><span>名称 :</span><input id="bkgoodsname" type="text"
				name="bkgoodsname" value="${requestScope.editBkgoods.bkgoodsname }" placeholder="名称" /><font>(必填)</font></label>
			<label><span>规格 :</span><input id="bkgoodsunits" type="text"
				name="bkgoodsunits" value="${requestScope.editBkgoods.bkgoodsunits }" placeholder="规格" /><font>(必填)</font></label>
			<!-- <label><span>小类名称 :</span><input id="bkgoodsclass" type="text"
				name="bkgoodsclass" value="${requestScope.editBkgoods.bkgoodsclass }" placeholder="小类名称" /></label> -->
			<label><span>单位 :</span><input id="bkgoodsunit" type="text"
				name="bkgoodsunit" value="${requestScope.editBkgoods.bkgoodsunit }" placeholder="单位" /><font>(必填)</font></label>
			<label><span>原价 :</span><input id="bkgoodsprice" type="number"
				name="bkgoodsprice" value="${requestScope.editBkgoods.bkgoodsprice }" placeholder="原价" /><font>(必填)</font></label>
			<label><span>现价 :</span><input id="bkgoodsorgprice" type="number"
				name="bkgoodsorgprice" value="${requestScope.editBkgoods.bkgoodsorgprice }" placeholder="现价" /><font>(必填)</font></label>
			<label><span>重量 :</span><input id="bkgoodsweight" type="number"
				name="bkgoodsweight" value="${requestScope.editBkgoods.bkgoodsweight }" placeholder="重量" /></label>
			<label><span>品牌 :</span><input id="bkgoodsbrand" type="text"
				name="bkgoodsbrand" value="${requestScope.editBkgoods.bkgoodsbrand }" placeholder="品牌" /></label>
			<label><span>顺序 :</span><input id="bkgoodsseq" type="number"
				name="bkgoodsseq" value="${requestScope.editBkgoods.bkgoodsseq }" placeholder="顺序" /></label>
			<label><span>图片路径 :</span><input id="bkgoodsimage" type="text"
				name="bkgoodsimage" value="${requestScope.editBkgoods.bkgoodsimage }" placeholder="图片路径" /></label>
			<label><span>状态 :</span><select name="bkgoodsstatue">
				<option ${requestScope.editBkgoods.bkgoodsstatue == '启用'?'selected':'' }>启用</option>
				<option ${requestScope.editBkgoods.bkgoodsstatue == '禁用'?'selected':'' }>禁用</option>
			</select><font>(必填)</font></label>
			<label><span>描述 :</span><textarea name="bkgoodsdetail">${requestScope.editBkgoods.bkgoodsdetail }</textarea></label>
			<p><input type="button"
				class="button" value="保存修改" onclick="saveEdit()"/>
			<input type="button"
				class="button" value="返回" onclick="javascript:history.go(-1)"/>
			</p>
		</form>
	</div>
<script type="text/javascript">
function saveEdit(){
	var data = '{';
	var count = 0;
	data += '"bkgoodsstatue":"' +$("[name='bkgoodsstatue']").val() +'",';
	data += '"bkgoodsdetail":"' +$("textarea[name='bkgoodsdetail']").val() +'",';
	var bkgoodsscope = '';
	$("input[name='bkgoodsscope']").each(function(i,item){
		if(item.checked==true){
			bkgoodsscope += $(item).val();
		}
	});
	data += '"bkgoodsscope":"' + bkgoodsscope +'",';
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if(($(item).val() == null || $(item).val() == '') && $(item).attr('placeholder') != '顺序' 
				&& $(item).attr('placeholder') != '重量' && $(item).attr('placeholder') != '品牌'
				&& $(item).attr('placeholder') != '图片路径'){
			alert($(item).attr('placeholder') + '不能为空!');
			count++;
			return false;
		} else if(($(item).val() == null || $(item).val() == '') && $(item).attr('placeholder') == '图片路径'){
			data += '"'+$(item).attr("name") + '":"images/default.jpg",';
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	if(count == 0){
		data += '"bkgoodsid":"${requestScope.editBkgoods.bkgoodsid }"}';
		$.post('editBkgoods.action',JSON.parse(data),function(data){
			if(data == 'ok'){
				alert('修改成功!');
				window.location.href = 'allCarnivalGoods.action?bkgoodscompany=${requestScope.editBkgoods.bkgoodscompany }'+
						'&bkgoodscode=${requestScope.bkgoodsCon.bkgoodscode }&bkgoodstype=${requestScope.bkgoodsCon.bkgoodstype }'+
						'&pagenow=${requestScope.pagenow}';
			} else {
				alert('要修改的年货不存在.');
			}
		});
	}
}
</script>
</body>
</html>