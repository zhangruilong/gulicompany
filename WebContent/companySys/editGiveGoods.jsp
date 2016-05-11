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
		<input type="hidden" name="givegoodscompany" value="${requestScope.editGiveGoods.givegoodscompany }">
			<h1>买赠商品信息</h1>
			<label><span>编码 :</span><input id="givegoodscode" type="text"
				name="givegoodscode" value="${requestScope.editGiveGoods.givegoodscode }" placeholder="编码" /></label>
			<label><span>名称 :</span><input id="givegoodsname" type="text"
				name="givegoodsname" value="${requestScope.editGiveGoods.givegoodsname }" placeholder="名称" /></label>
			<label><span>规格 :</span><input id="givegoodsunits" type="text"
				name="givegoodsunits" value="${requestScope.editGiveGoods.givegoodsunits }" placeholder="规格" /></label>
			<!-- <label><span>小类名称 :</span><input id="givegoodsclass" type="text"
				name="givegoodsclass" value="${requestScope.editGiveGoods.givegoodsclass }" placeholder="小类名称" /></label> -->
			<label><span>单位 :</span><input id="givegoodsunit" type="text"
				name="givegoodsunit" value="${requestScope.editGiveGoods.givegoodsunit }" placeholder="单位" /></label>
			<label><span>售价 :</span><input id="givegoodsprice" type="number"
				name="givegoodsprice" value="${requestScope.editGiveGoods.givegoodsprice }" placeholder="原价" /></label>
			<label><span>个人限量 :</span><input id="givegoodsnum" type="number"
				name="givegoodsnum" value="${requestScope.editGiveGoods.givegoodsnum }" placeholder="个人限量" /></label>
			<label><span>顺序 :</span><input id="givegoodsseq" type="number"
				name="givegoodsseq" value="${requestScope.editGiveGoods.givegoodsseq }" placeholder="顺序" /></label>
			<label><span>图片路径 :</span><input id="givegoodsimage" type="number"
				name="givegoodsimage" value="${requestScope.editGiveGoods.givegoodsimage }" placeholder="图片路径" /></label>
			<label><span>状态 :</span><select name="givegoodsstatue">
				<option ${requestScope.editGiveGoods.givegoodsstatue == '启用'?'selected':'' }>启用</option>
				<option ${requestScope.editGiveGoods.givegoodsstatue == '禁用'?'selected':'' }>禁用</option>
			</select></label>
			<label><span>描述 :</span><textarea name="givegoodsdetail">${requestScope.editGiveGoods.givegoodsdetail }</textarea></label>
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
	if($("[name='givegoodsdetail']").val == null || $("[name='givegoodsdetail']").val == '' ){
		alert("描述不能为空");
	} else {
		data += '"givegoodsdetail":"' +$("[name='givegoodsdetail']").val() +'",';
	}
	data += '"givegoodsstatue":"' +$("[name='givegoodsstatue']").val() +'",';
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if($(item).val() == null || $(item).val() == '' ){
			alert($(item).attr('placeholder') + '不能为空');
			count++;
			return false;
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	if(count == 0){
		data += '"givegoodsid":"${requestScope.editGiveGoods.givegoodsid }"}';
		$.post('editGiveGoods.action',JSON.parse(data),function(data){
			if(data == 'ok'){
				alert('修改成功');
				window.location.href = 'allGiveGoods.action?givegoodscompany=${requestScope.editGiveGoods.givegoodscompany }&pagenow=${requestScope.pagenow}';
			} else {
				alert('修改失败');
			}
		});
	}
}
</script>
</body>
</html>