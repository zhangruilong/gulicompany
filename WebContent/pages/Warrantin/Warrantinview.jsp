<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	var comid = '<%=comid%>';
	</script>
	
	<script type="text/javascript" src="../../js/DateTimePicker.js"></script>
	<script type="text/javascript" src="../../js/DeteTimeField.js"></script>
	
	<script type="text/javascript" src="Warrantinviewfun.js"></script>
	<script type="text/javascript" src="Warrantinview.js"></script>
  </head>
</html>
