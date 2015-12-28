<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/uixml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/user.php?act=drop_consignee&id="+request.getParameter("id");
%>
<aa:http id="delMyAddress" url="<%=url %>"></aa:http>
{"result":"success"}