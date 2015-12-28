<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>

<aa:http id="cartsUpdate" url="http://p9line/flow.php?step=ajax_update_cart" method="post" keepreqdata="false">
	<aa:header name="Content-Type" value="application/x-www-form-urlencoded"/>
	<aa:content>rec_id=<%=request.getParameter("id")%>&goods_number=<%=request.getParameter("num")%></aa:content>
</aa:http>
<%
String json2XML = aa.common.jsonToXmlString(aa.regex.regex(".*", "cartsUpdate"));
%>
<aa:datasource id="newdata" value="<%=json2XML %>" wellformed="true"></aa:datasource>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("result", "0".equals(aa.xpath.xpath("//error/text()", "newdata"))?"success":"fail");
out.println(resultJSON.toString());
%>