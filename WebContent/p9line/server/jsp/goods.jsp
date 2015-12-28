<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/goods.php?id="+aa.req.getParameterFromUrl("id");
%>

<aa:http id="goods" url="<%=url %>"></aa:http>
<%
JSONObject resultJSON = new JSONObject();

JSONObject detailJSON = new JSONObject();

JSONArray sliderArray = new JSONArray();


detailJSON.put("title", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']//h1[1]/text())", "goods"));
detailJSON.put("description", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']//h2[1]/text())", "goods"));
detailJSON.put("orgPrice", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']/ul/li[1]/ins)", "goods"));
detailJSON.put("delPrice", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']/ul/li[1]/span)", "goods"));
detailJSON.put("price", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']/ul/li[2]/b)", "goods"));
detailJSON.put("sale", aa.xpath.xpath("normalize-space(//div[@class='promotionMiddleTop']/ul/li[3])", "goods"));
detailJSON.put("more", aa.xpath.copyOfNodeAsStr("//div[@class='xiangqing']", "goods"));

resultJSON.put("detail", detailJSON);

%>
<aa:for-each var="i" xpath="//div[@id='preview']/..//ul//img" dsId="goods">
<%
sliderArray.put(aa.xpath.xpath("./@bimg", "i"));
%>
</aa:for-each>

<%

resultJSON.put("slider", sliderArray);
		
out.print(resultJSON.toString());
%>