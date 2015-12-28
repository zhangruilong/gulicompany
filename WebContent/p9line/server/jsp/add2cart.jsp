<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<aa:http id="add2cart" url="http://p9line/flow.php?step=add_to_cart" method="post" keepreqdata="false">
	<aa:header name="Content-Type" value="application/x-www-form-urlencoded"/>
	<aa:content>goods={"quick":0,"spec":[],"goods_id":<%=request.getParameter("id")%>,"number":1,"parent":0}</aa:content>
</aa:http>
<%
String json2XML = aa.common.jsonToXmlString(aa.regex.regex(".*", "add2cart"));
%>
<aa:datasource id="newdata" value="<%=json2XML %>" wellformed="true"></aa:datasource>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("result", "0".equals(aa.xpath.xpath("//error/text()", "newdata"))?"success":"fail");
out.println(resultJSON.toString());
%>