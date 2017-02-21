<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
<style type="text/css">
.button{
	padding: 0px;
	width: 50px;
	border-radius:6px;
}
</style>
</head>
<body>
<div class="nowposition">当前位置：帮助中心 》帮助文档</div>
<table class="bordered" >
    <thead>
    <tr>
        <th>文档名称</th>
		<th>描述</th>
		<th>发布时间</th>
		<th>作者</th>
		<th>下载</th>
    </tr>
    </thead>
		<tr>
			<td> 操作指南 </td>
			<td> 谷粒网供应商后台管理系统操作指南 </td>
			<td>2016-10-17</td>
			<td>管理员</td>
			<td><a href="file/【YF-5】经销商后台系统操作指南.doc">下载</a></td>
		</tr>
		<tr>
			<td colspan="5"></td>
		</tr>
</table>
<script type="text/javascript">
</script>
</body>
</html>