<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "";
String type = request.getParameter("type");
String id = request.getParameter("id");
if("city".equals(type)){
	url = "http://www.p9line.cn/region.php?type=2&parent="+id;
}else{
	url = "http://www.p9line.cn/region.php?type=3&parent="+id;
}
%>
<aa:http id="region" url="<%=url %>" keepreqdata="false"></aa:http>

<%=aa.regex.regex(".*", "region")%>