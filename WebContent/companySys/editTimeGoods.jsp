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
</style>
</head>
<body>
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
		<input type="hidden" name="timegoodscompany" value="${requestScope.editTimeGoods.timegoodscompany }">
			<h1>秒杀商品信息</h1>
			<p><span>客户范围 :</span>&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="timegoodsscope" value="3" ${fn:contains(requestScope.editTimeGoods.timegoodsscope,'3')?'checked':'' }/>
			餐饮客户&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="timegoodsscope" value="2" ${fn:contains(requestScope.editTimeGoods.timegoodsscope,'2')?'checked':'' }/>
			商超客户 &nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="timegoodsscope" value="1" ${fn:contains(requestScope.editTimeGoods.timegoodsscope,'1')?'checked':'' }/>
			组织单位客户 &nbsp;&nbsp;&nbsp;
			</p>
			<label><span>编码 :</span><input id="timegoodscode" type="text"
				name="timegoodscode" value="${requestScope.editTimeGoods.timegoodscode }" placeholder="编码" /></label>
			<label><span>名称 :</span><input id="timegoodsname" type="text"
				name="timegoodsname" value="${requestScope.editTimeGoods.timegoodsname }" placeholder="名称" /></label>
			<label><span>规格 :</span><input id="timegoodsunits" type="text"
				name="timegoodsunits" value="${requestScope.editTimeGoods.timegoodsunits }" placeholder="规格" /></label>
			<!-- <label><span>小类名称 :</span><input id="timegoodsclass" type="text"
				name="timegoodsclass" value="${requestScope.editTimeGoods.timegoodsclass }" placeholder="小类名称" /></label> -->
			<label><span>单位 :</span><input id="timegoodsunit" type="text"
				name="timegoodsunit" value="${requestScope.editTimeGoods.timegoodsunit }" placeholder="单位" /></label>
			<label><span>原价 :</span><input id="timegoodsprice" type="number"
				name="timegoodsprice" value="${requestScope.editTimeGoods.timegoodsprice }" placeholder="原价" /></label>
			<label><span>现价 :</span><input id="timegoodsorgprice" type="number"
				name="timegoodsorgprice" value="${requestScope.editTimeGoods.timegoodsorgprice }" placeholder="现价" /></label>
			<label><span>重量 :</span><input id="timegoodsweight" type="number"
				name="timegoodsweight" value="${requestScope.editTimeGoods.timegoodsweight }" placeholder="重量" /></label>
			<label><span>个人限量 :</span><input id="timegoodsnum" type="number"
				name="timegoodsnum" value="${requestScope.editTimeGoods.timegoodsnum }" placeholder="个人限量" /></label>
			<label><span>全部限量 :</span><input id="allnum" type="number"
				name="allnum" value="${requestScope.editTimeGoods.allnum }" placeholder="全部限量" /></label>
			<label><span>剩余数量 :</span><input id="surplusnum" type="number"
				name="surplusnum" value="${requestScope.editTimeGoods.surplusnum }" placeholder="剩余数量" /></label>
			<label><span>顺序 :</span><input id="timegoodsseq" type="number"
				name="timegoodsseq" value="${requestScope.editTimeGoods.timegoodsseq }" placeholder="顺序" /></label>
			<label><span>图片路径 :</span><input id="timegoodsimage" type="text"
				name="timegoodsimage" value="${requestScope.editTimeGoods.timegoodsimage }" placeholder="图片路径" /></label>
			<label><span>状态 :</span><select name="timegoodsstatue">
				<option ${requestScope.editTimeGoods.timegoodsstatue == '启用'?'selected':'' }>启用</option>
				<option ${requestScope.editTimeGoods.timegoodsstatue == '禁用'?'selected':'' }>禁用</option>
			</select></label>
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
	data += '"timegoodsstatue":"' +$("[name='timegoodsstatue']").val() +'",';
	var timegoodsscope = '';
	$("input[name='timegoodsscope']").each(function(i,item){
		if(item.checked==true){
			timegoodsscope += $(item).val();
		}
	});
	data += '"timegoodsscope":"' + timegoodsscope +'",';
	$(".elegant-aero [type='text']").add(".elegant-aero [type='number']").each(function(i,item){
		if($(item).val() == null || $(item).val() == '' ){
			if($(item).attr('placeholder') != '顺序' && $(item).attr('placeholder') != '图片路径' && $(item).attr('placeholder') != '重量'){
				alert($(item).attr('placeholder') + '不能为空');
				count++;
				return false;
			}
		} else {
			data += '"'+$(item).attr("name") + '":"' + $(item).val() + '",';
		}
	});
	if(count == 0){
		data += '"timegoodsid":"${requestScope.editTimeGoods.timegoodsid }"}';
		$.post('editTimeGoods.action',JSON.parse(data),function(data){
			if(data == 'ok'){
				alert('修改成功!');
				window.location.href = 'allTimeGoods.action?timegoodscompany=${requestScope.editTimeGoods.timegoodscompany }'+
						'&timegoodsscope=${requestScope.timegoodsCon.timegoodsscope }&pagenow=${requestScope.pagenow}';
			} else {
				alert('要修改的秒杀商品不存在.');
			}
		});
	}
}
</script>
</body>
</html>