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
</head>
<body>
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
			<h1>用户信息</h1>
			<label> <span>账号名称 :</span> <input id="name" type="text"
				name="name" placeholder="账号名称"
				value="${sessionScope.company.companyshop }" />
			</label> <label> <span>联系人 :</span> <input id="name" type="text"
				name="name" placeholder="联系人"
				value="${sessionScope.company.username }" />
			</label> <label> <span>联系电话 :</span> <input id="name" type="text"
				name="name" placeholder="联系电话"
				value="${sessionScope.company.companyphone }" />
			</label> <label> <span>&nbsp;</span> <input type="button"
				class="button" value="保存" />
			</label>
		</form>
	</div>
</body>
</html>