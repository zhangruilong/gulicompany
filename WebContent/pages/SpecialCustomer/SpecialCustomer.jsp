<%@page import="com.server.pojo.LoginInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<%@ include file="../../zrlextpages/common/common.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="../../js/common2.js"></script>
	<%
	LoginInfo info = (LoginInfo)session.getAttribute("loginInfo"); 
	String comid = info.getCompanyid();
	%>
	<script type="text/javascript" src="../../zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		var comid = '<%=comid%>';
		var customertype = nullStr(getUrlParam('customertype'));
		var classify = nullStr(getUrlParam('classify'));
		var creator = nullStr(getUrlParam('creator'));
		var xian = new Array();
		var city='';
		$.ajax({
			url : "CPCompanyviewAction.do?method=selAll",
			type : "post",
			data : {
				wheresql : "companyid='"+comid+"'"
			},
			success : function(resp){
				var data = eval('('+resp+')');
				if(data.code=202){
					var companyview = data.root[0];
					xian = companyview.createtime.split('/');
					city = companyview.cityparentname;
				}
			},
			error : function(resp){
				var data = eval('('+resp+')');
				alert(data.msg);
			}
		});
	</script>
	<script type="text/javascript" src="SpecialCustomerfun.js"></script>
	<script type="text/javascript" src="SpecialCustomer.js"></script>
<style type="text/css">
.kb-textField-not-border-wrap {
	border-style: none;
}

.kb-textField-not-border-trigger-wrap {
	border-style: none;
}
/* .x-column-header {
	background-color: #EFF6FF;
} */
</style>
  </head>
</html>
