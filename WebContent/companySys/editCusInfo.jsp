<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">

</script>
</head>
<body>
	<div class="elegant-aero">
	<input type="hidden" name="edit_cusid">
			<h1>修改客户信息</h1>
			<label> <span>客户类型 :</span>
			<select name="customertype" id="customertype">
				<option value="3">餐饮客户</option>
				<option value="2">商超客户</option>
				<option value="1">组织单位客户</option>
			</select>
			</label> 
			<label> <span>价格层级 :</span> <input id="ccustomerdetail" type="number"
				name="ccustomerdetail" placeholder="价格层级" value="" /></label> 
			<label> <span>客户经理 :</span>
			<select name="accountManager">
				<option value="">请选择客户经理</option>
			</select>
			</label>
			
			<label> <span>客户名称 :</span> <input id="customershop" type="text"
				name="customershop" placeholder="客户名称" value="" /></label>
			<label> <span>联系人 :</span> <input id="customername" type="text"
				name="customername" placeholder="联系人" value="" /></label> 
			<label> <span>联系电话 :</span> <input id="customerphone" type="text"
				name="customerphone" placeholder="联系电话" value="" /></label> 
			<label> <span>联系地址 :</span> <input id="customeraddress" type="text"
				name="customeraddress" placeholder="地址" value="" /></label> 
			<label><span>状态 :</span><select name=customerstatue>
				<option>启用</option>
				<option>禁用</option>
			</select></label>
			<!-- <label><span>所在城市 :</span>
			<select name="customercity">
				<option>嘉兴市</option>
			</select>
			</label> -->
			<label> <span>所在城市 :</span> <input id="customercity" type="text"
				name="customercity" placeholder="所在城市" value="" /></label> 
			<!-- <label> <span>所在地区 :</span>
			<select name="customerxian">
				<option>海盐县</option>
				<option>秀洲区</option>
				<option>嘉善县</option>
				<option>南湖区</option>
			</select>
			</label> -->
			<label> <span>所在地区 :</span> <input id="customerxian" type="text"
				name="customerxian" placeholder="所在地区" value="" /></label> 
			<label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存" onclick="saveCus()"/>
			</label>
	</div>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	$.getJSON("queryCcusAndCus.action",{"ccustomerid":"${param.ccustomerid}"},initEditCus,null);
});
//初始化页面信息
function initEditCus(data){
	$.each(data.emps,function(i,item){
		$("select[name='accountManager']").append('<option>'+item.empdetail+'</option>');
	});
	$("input:hidden[name='edit_cusid']").val(data.editCus.customerid);
	
	$("#customertype").val(data.editCus.customertype);
	$("select[name='accountManager']").val(typeNullFoString(data.editCcus.createtime));
	$("#ccustomerdetail").val(data.editCcus.ccustomerdetail);
	$("#customerphone").val(data.editCus.customerphone);
	$("#customername").val(data.editCus.customername);
	$("#customershop").val(data.editCus.customershop);
	$("select[name='customerstatue']").val(data.editCus.customerstatue);
	$("#customeraddress").val(data.editCus.customeraddress);
	$("[name='customercity']").val(data.editCus.customercity);
	$("[name='customerxian']").val(data.editCus.customerxian);
}
//保存修改
function saveCus(){
	//alert($("select[name='customercity']").val());
	//alert($("select[name='customerxian']").val());
	$.getJSON("editCcusAndCus.action",{
		"customerid":$("input:hidden[name='edit_cusid']").val(),
		"customertype":$("#customertype").val(),
		"accountManager":$("select[name='accountManager']").val(),
		"customerphone":$("#customerphone").val(),
		"customername":$("#customername").val(),
		"customershop":$("#customershop").val(),
		"customerstatue":$("select[name='customerstatue']").val(),
		"customeraddress":$("#customeraddress").val(),
		"customercity":$("[name='customercity']").val(),
		"customerxian":$("[name='customerxian']").val(),
		"ccustomerid":"${param.ccustomerid}",
		"ccustomerdetail":$("#ccustomerdetail").val(),
		},saveafert,null);
}
//保存成功
function saveafert(data){
	alert("保存成功");
	if('${param.fo}' == 'largeCus'){
		window.location.href = "allCustomer.action?ccustomercompany=${sessionScope.company.companyid}&creator=1&pagenow=${param.pagenow}"+
		"&customer.customercode=${param.customercode}";
	} else {
		window.location.href = "allCustomer.action?ccustomercompany=${sessionScope.company.companyid}&pagenow=${param.pagenow}"+
				"&customer.customercode=${param.customercode}";
	}
}
</script>
</body>
</html>