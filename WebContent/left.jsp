<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="left.js"></script>
<script type="text/javascript" src="sysjs/jquery.min.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var b=document.documentElement.clientHeight-4;  
		//取得iframe框架的实际宽度
		document.getElementById("divRsl").style.height=b+"px";
		document.getElementById("img_left").style.top=(b-95)/2+"px";
	}
	function shleft(){
	 if (parent.cen.cols=="10,*"){
	  parent.cen.cols="184,*"
	  window.parent.frames["main"].location.reload();
	  document.getElementById("img_left").src="sysimages/bar_left.gif";
	  document.getElementById("divRsl").style.display="block";
	 }
	 else{
	  parent.cen.cols="10,*"
	  window.parent.frames["main"].location.reload();
	  document.getElementById("img_left").src="sysimages/bar_right.gif";
	  document.getElementById("divRsl").style.display="none";
	 }
}
</script>
<link href="sysstyle/style.css" rel="stylesheet" type="text/css" />
</head>
<body class="left_bg">
	<img src="sysimages/bar_left.gif" width="6" height="95"
		class="bar_left" id="img_left" onclick="shleft();" />
	<div class="left1" id="divRsl" style="display: block;">
		<h1>菜单管理</h1>
		<div id="tree" style="border: 0;"></div>
	</div>
</body>
</html>
