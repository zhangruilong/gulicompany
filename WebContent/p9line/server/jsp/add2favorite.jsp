<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/user.php?act=collect&id=" + request.getParameter("id");
%>
<aa:http id="add2favorite" url="<%=url %>" keepreqdata="false"></aa:http>
<%
String json2XML = aa.common.jsonToXmlString(aa.regex.regex(".*", "add2favorite"));
%>
<aa:datasource id="newdata" value="<%=json2XML %>" wellformed="true"></aa:datasource>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("result", "0".equals(aa.xpath.xpath("//error/text()", "newdata"))?"success":"fail");
resultJSON.put("msg", aa.xpath.xpath("//message/text()", "newdata"));
out.println(resultJSON.toString());
%>