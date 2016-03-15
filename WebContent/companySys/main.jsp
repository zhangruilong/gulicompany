<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷粒管理平台</title>
</head>
<frameset rows="100,*" cols="*" frameborder="no" border="1"
	framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize"
		id="topFrame" title="topFrame" />
	<frameset cols="150,*" name="cen" frameborder="no" border="1"
		framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="" name="main" id="main" title="main" />
	</frameset>
</frameset>
</html>
