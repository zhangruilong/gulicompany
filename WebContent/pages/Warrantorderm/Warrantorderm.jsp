<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.server.pojo.LoginInfo"%>
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	%>
	<script type="text/javascript" src="../../zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../../js/common2.js"></script>
	<script type="text/javascript">
	var comid = '<%=comid%>';
	</script>
	<script type="text/javascript" src="Warrantoutviewfun.js"></script>
	<script type="text/javascript" src="Goodsnumview.js"></script>
	<script type="text/javascript" src="Warrantout.js"></script>
	<script type="text/javascript" src="Warrantorderm.js"></script>
  </head>
</html>
