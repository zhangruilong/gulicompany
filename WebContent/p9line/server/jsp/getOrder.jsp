<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/user.php?act=order_list&page="+request.getParameter("page");
%>
<aa:http id="getOrder" url="<%=url %>"></aa:http>
<%
JSONArray arrJSON = new JSONArray();
%>
<aa:for-each var="i" xpath="//div[contains(@class,'userCenterBox')]/table[1]/tr[position()>1]" dsId="getOrder">
<%
JSONObject detail = new JSONObject();
detail.put("orderId", aa.xpath.xpath("./td[1]/a/text()", "i"));
detail.put("id", aa.xpath.xpath("./td[1]/a/@href", "i").replaceAll("[^\\d]*", ""));
detail.put("time", aa.xpath.xpath("./td[2]", "i"));
detail.put("price", aa.xpath.xpath("./td[3]", "i"));
detail.put("status", aa.xpath.xpath("./td[4]", "i"));
detail.put("isDel", aa.xpath.xpath("./td[5]/a", "i").length()>0?false:true);
arrJSON.put(detail);
%>
</aa:for-each>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("list", arrJSON);
out.println(resultJSON.toString());
%>