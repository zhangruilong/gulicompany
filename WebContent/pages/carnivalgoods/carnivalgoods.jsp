<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../common/common.jsp" %>
	<%
		String comcode = info.getCompanycode(); 
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="../../js/common2.js"></script>
	<script type="text/javascript">
		var bkgoodstype = nullStr(getUrlParam('bkgoodstype'));
		var classify = nullStr(getUrlParam('classify'));
		var comcode = '<%=comcode%>';
	</script>
	<script type="text/javascript" src="carnivalgoodsfun.js"></script>
	<script type="text/javascript" src="carnivalgoods.js"></script>
  </head>
</html>