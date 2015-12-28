<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.pojo.System_power_quickview"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList<System_power_quickview> quickmenu = (ArrayList<System_power_quickview>)request.getSession().getAttribute("quickmenu");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
<link href="sysstyle/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="sysjs/png.js"></script>
</head>
<body>
	<div class="topnav">
		<blockquote>
			<div class="member_msg">
				<a href="#" class="icon01">欢迎，用户:<%=request.getSession().getAttribute("username")%></a>
				<a href="pages/system/System_password/System_password.jsp"
					target="main" class="icon03">修改密码</a> <a
					href="BaseAction.do?method=logout" class="icon02">注销</a>
			</div>
			<img src="sysimages/danonglogo.png" width="154" height="25" class="logo" />
			<h1>谷粒管理平台</h1>
			<h6 class="clear"></h6>
		</blockquote>
	</div>
	<div class="quick_menu">
		<h1>
			<a href="pages/system/System_power_quick/System_power_quick.jsp"
				target="main">我的自定义菜单</a>
		</h1>
		<div class="menu_detail">
			<%
	for(System_power_quickview temp:quickmenu){
	%>
			<a href="<%= temp.getEntrance() %>"
				target="<%= temp.getHreftarget() %>"><span><%= temp.getName() %></span></a>|
			<%
    }
    %>
		</div>
	</div>
</body>
</html>
