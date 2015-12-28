<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*,org.json.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/topic.php?topic_id="+request.getParameter("id");
%>

<aa:http id="topic" url="<%=url %>"></aa:http>

<%
JSONObject resultJSON = new JSONObject();

JSONObject listJSON = new JSONObject();

resultJSON.put("title", aa.xpath.xpath("normalize-space(//div[@class='specials']/div[1])", "topic"));

%>

<aa:for-each var="i" xpath="//div[@class='specials']//ul[1]/li/div" dsId="topic">
<%
JSONObject detail = new JSONObject();
detail.put("img", aa.xpath.xpath(".//img[1]/@src", "i"));
detail.put("title", aa.xpath.xpath("normalize-space(.//font/strong/font)", "i"));
detail.put("minprice", aa.xpath.xpath("normalize-space(.//p[@class='prod-price']/span[@class='minprice']/strong)", "i"));
detail.put("maxprice", aa.xpath.xpath("normalize-space(.//p[@class='prod-price']/span[@class='max-price'])", "i"));

String key = aa.xpath.xpath(".//a[1]/@href","i").replaceAll("goods.php[^=]+=(\\d+)", "$1");
listJSON.put(key, detail);
%>
</aa:for-each>


<%
resultJSON.put("list", listJSON);
out.print(resultJSON.toString());
%>