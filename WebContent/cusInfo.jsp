<%@page import="com.server.pojo.LoginInfo"%> 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	LoginInfo info = (LoginInfo) session.getAttribute("loginInfo");
	String power = info.getPower();
	String companyid = info.getCompanyid();
	String companyshop = info.getCompanyshop();
	String comusername = info.getComusername();
	String companyphone = info.getCompanyphone();
%>

<!DOCTYPE HTML >
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/formsty.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
	<div class="elegant-aero">
		<form action="editCompInfo.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="companyid" value="">
			<h1>用户信息</h1>
			<label> <span>账户名称 :</span> <input id="companyshop" type="text"
				name="companyshop" placeholder="账户名称" value="" />
			</label> <label> <span>联系人 :</span> <input id="comusername" type="text"
				name="comusername" placeholder="联系人" value="" />
			</label> <label> <span>联系电话 :</span> <input id="companyphone" type="text"
				name="companyphone" placeholder="联系电话" value="" />
			</label> <label> <span>&nbsp;</span> <input type="button" id="save-btn"
				class="button" value="保存" onclick="checkAccount()"/>
			</label>
		</form>
	</div>
<script type="text/javascript">
var power = '<%=power%>';
var companyid = '<%=companyid%>';
var companyshop = '<%=companyshop%>';
var comusername = '<%=comusername%>';
var companyphone = '<%=companyphone%>';
$(function(){
	if(power=='emp'){
		$('#save-btn').hide();
	}
	$('#companyshop').val(companyshop);
	$('#comusername').val(comusername);
	$('#companyphone').val(companyphone);
});
//保存修改
function checkAccount(){
	var inpcompanyshop = $("[name='companyshop']").val();
	var inpcomusername = $("[name='comusername']").val();
	var inpcompanyphone = $("[name='companyphone']").val();
	if(inpcompanyshop == null ||inpcompanyshop == ''){
		alert("账户名称不能为空");
		return;
	}
	if(inpcomusername == null ||inpcomusername == ''){
		alert("联系人不能为空");
		return;
	}
	if(inpcompanyphone == null ||inpcompanyphone == ''){
		alert("联系电话不能为空");
		return;
	}
	$.ajax({
		url:"CPCompanyAction.do?method=editComInfo",
		type:"post",
		data:{
			json : '[{"companyshop":"'+inpcompanyshop+'","username":"'+inpcomusername+'","companyphone":"'+inpcompanyphone+'","companyid":"'+companyid+'"}]'
		},
		success : function(resp){
			var data = eval('('+resp+')');
			if(data.code==202){
				alert('操作成功，用户信息已修改。');
			} else {
				alert('操作失败。');
			}
		},
		error : function(resp){
			var data = eval('('+resp+')');
			alert(data.msg);
		}
	});
}
</script>
</body>
</html>