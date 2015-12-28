<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/user.php?act=collection_list&page="+request.getParameter("page");
%>
<aa:http id="getFavorite" url="<%=url %>"></aa:http>
<%
JSONArray arrJSON = new JSONArray();
%>
<aa:for-each var="i" xpath="//div[@class='main']/div/table//tr[position()>1]" dsId="getFavorite">
<%
JSONObject detail = new JSONObject();
detail.put("title", aa.xpath.xpath("./td[1]/a/text()", "i"));
detail.put("favoriteId", aa.xpath.xpath("./td[last()]/a[last()]/@href", "i").replaceAll("[^\\d]*", ""));
detail.put("id", aa.xpath.xpath("./td[1]/a/@href", "i").replaceAll("[^\\d]*", ""));
detail.put("price", aa.xpath.xpath("./td[2]", "i"));
arrJSON.put(detail);
%>
</aa:for-each>
<%
JSONObject resultJSON = new JSONObject();
resultJSON.put("list", arrJSON);
out.println(resultJSON.toString());
%>