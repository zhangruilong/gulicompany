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
	<script type="text/javascript" src="../../js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../../js/common2.js"></script>
	<script type="text/javascript">
	var wheresql = '';
	var comcode = '<%=comcode%>';
	var goodsstatue = nullStr(getUrlParam('goodsstatue'));
	var goodstype = nullStr(getUrlParam('goodstype'));
	var classify = nullStr(getUrlParam('classify'));
	</script>
	<script type="text/javascript" src="GoodsMana.js"></script>
	<script type="text/javascript" src="GoodsManafun.js"></script>
  </head>
</html>
