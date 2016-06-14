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
$(function(){
	$.getJSON("queryCcusAndCus.action",{"ccustomerid":"${param.ccustomerid}"},initEditCus,null);
});
function initEditCus(data){
	$("#customertype option").each(function(i,item){
		if($(item).val() == data.editCus.customertype){
			$(item).attr("selected","selected");
		}
	});
	$("#ccustomerdetail").val(data.editCcus.ccustomerdetail);
}
function saveCus(){
	$.getJSON("editCcusAndCus.action",{
		"customerid":"${param.customerid}",
		"customertype":$("#customertype").val(),
		"ccustomerid":"${param.ccustomerid}",
		"ccustomerdetail":$("#ccustomerdetail").val()
		},saveafert,null);
}
function saveafert(data){
	alert("保存成功");
	if('${param.fo}' == 'largeCus'){
		window.location.href = "allCustomer.action?ccustomercompany=${sessionScope.company.companyid}&creator=1&pagenow=${param.pagenow}";
	} else {
		window.location.href = "allCustomer.action?ccustomercompany=${sessionScope.company.companyid}&pagenow=${param.pagenow}";
	}
}
</script>
</head>
<body>
	<div class="elegant-aero">
			<h1>修改客户信息</h1>
			<label> <span>客户类型 :</span>
			<select name="customertype" id="customertype">
				<option value="3">餐饮客户</option>
				<option value="2">商超客户</option>
				<option value="1">组织单位客户</option>
			</select>
			</label> <label> <span>价格层级 :</span> <input id="ccustomerdetail" type="number"
				name="ccustomerdetail" placeholder="价格层级"
				value="" />
			</label> <label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存" onclick="saveCus()"/>
			</label>
	</div>
</body>
</html>