<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
		if(null == session.getAttribute("loginInfo")){
			response.sendRedirect("login.jsp");
		}
	 %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒网管理平台</title>
</head>
<frameset rows="137,*" cols="*" frameborder="no" border="1"
	framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize"
		id="topFrame" title="topFrame" />
	<frameset cols="150,*" name="cen" frameborder="no" border="1"
		framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="" name="main" id="main" title="main" />
	</frameset>
</frameset>
</html>
