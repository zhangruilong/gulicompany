<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.server.pojo.LoginInfo"%>
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	%>
	<script type="text/javascript">
	var comid = '<%=comid%>';
	</script>
	<script type="text/javascript" src="Warrantbackviewfun.js"></script>
	<script type="text/javascript" src="Warrantbackview.js"></script>
  </head>
</html>
