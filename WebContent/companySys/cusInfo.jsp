<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
var message = '<%=message %>'
	if(message != null && message != '' && message != 'null'){
		alert(message);
	}
	function checkAccount(){
		var companyshop = $("[name='companyshop']").val();
		var username = $("[name='username']").val();
		var companyphone = $("[name='companyphone']").val();
		if(companyshop == null ||　companyshop == ''){
			alert("账户名称不能为空");
			return;
		}
		if(username == null ||　username == ''){
			alert("联系人不能为空");
			return;
		}
		if(companyphone == null ||　companyphone == ''){
			alert("联系电话不能为空");
			return;
		}
		document.forms[0].submit();
	}
	
</script>
</head>
<body>
	<div class="elegant-aero">
		<form action="editCompInfo.action" method="post" class="STYLE-NAME">
		<input type="hidden" name="companyid" value="${sessionScope.company.companyid }">
			<h1>用户信息</h1>
			<label> <span>账户名称 :</span> <input id="companyshop" type="text"
				name="companyshop" placeholder="账户名称"
				value="${sessionScope.company.companyshop }" />
			</label> <label> <span>联系人 :</span> <input id="username" type="text"
				name="username" placeholder="联系人"
				value="${sessionScope.company.username }" />
			</label> <label> <span>联系电话 :</span> <input id="companyphone" type="text"
				name="companyphone" placeholder="联系电话"
				value="${sessionScope.company.companyphone }" />
			</label> <label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存" onclick="checkAccount()"/>
			</label>
		</form>
	</div>
</body>
</html>