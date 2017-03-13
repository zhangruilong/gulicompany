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
	<script type="text/javascript" src="../../js/common2.js"></script>
	<script type="text/javascript">
		var comid = '<%=comid%>';
		var bkgoodstype = nullStr(getUrlParam('bkgoodstype'));
		var classify = nullStr(getUrlParam('classify'));
	</script>
	<script type="text/javascript" src="carnivalgoodsfun.js"></script>
	<script type="text/javascript" src="carnivalgoods.js"></script>
	
  </head>
</html>
