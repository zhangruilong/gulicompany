<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://www.p9line.cn/user.php?act=delete_collection&collection_id=" + request.getParameter("id");
%>
<aa:http id="delFavorite" url="<%=url %>"></aa:http>
{"result":"success"}