<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/category.php?id="+request.getParameter("id")+"&page="+request.getParameter("page");
%>

<aa:http id="list" url="<%=url %>" keepreqdata="false"></aa:http>

<%
JSONObject resultJSON = new JSONObject();

JSONObject listJSON = new JSONObject();

%>

<aa:for-each var="i" xpath="//div[@id='goodsList']//li" dsId="list">
<%
JSONObject detail = new JSONObject();
detail.put("img", aa.xpath.xpath(".//img[1]/@original", "i"));
detail.put("title", aa.xpath.xpath("normalize-space(.//a[@class='pname'])", "i"));
detail.put("minprice", aa.xpath.xpath("normalize-space(.//span[@class='minprice'])", "i"));
detail.put("maxprice", aa.xpath.xpath("normalize-space(.//span[@class='maxprice'])", "i"));

String key = aa.xpath.xpath(".//a[1]/@href","i").replaceAll("goods.php[^=]+=(\\d+)", "$1");
listJSON.put(key, detail);
%>
</aa:for-each>


<%
resultJSON.put("list", listJSON);
out.print(resultJSON.toString());
%>