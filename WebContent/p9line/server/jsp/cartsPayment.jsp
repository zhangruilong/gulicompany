<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String type = request.getParameter("type");
String val = request.getParameter("val");
String url = "http://p9line/flow.php?step=select_"+type+"&"+type+"="+val;
%>
<aa:http id="cartsPayment" url="<%=url %>"></aa:http>
<%
String json2XML = aa.common.jsonToXmlString(aa.regex.regex(".*", "cartsPayment"));
%>
<aa:datasource id="newdata" value="<%=json2XML %>" wellformed="true"></aa:datasource>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("result", "".equals(aa.xpath.xpath("//error/text()", "newdata"))?"success":"fail");
out.println(resultJSON.toString());
%>