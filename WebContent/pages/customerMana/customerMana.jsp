<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../common/common.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="../../js/common2.js"></script>
	<script type="text/javascript">
		var customertype = nullStr(getUrlParam('customertype'));
		var classify = nullStr(getUrlParam('classify'));
		var creator = nullStr(getUrlParam('creator'));
	</script>
	<script type="text/javascript" src="customerManafun.js"></script>
	<script type="text/javascript" src="customerMana.js"></script>
  </head>
</html>
