<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<aa:http id="carts" url="http://www.p9line.cn/flow.php" keepreqdata="false"></aa:http>
<%
JSONArray listJSON = new JSONArray();
%>
<aa:for-each var="i" xpath="//form[@id='formCart']/table/tr[position()>1]" dsId="carts">
<%
JSONObject detailJSON = new JSONObject();
detailJSON.put("id", aa.xpath.xpath("./@id","i").replaceAll("[^\\d]*", ""));
detailJSON.put("goodsId", aa.xpath.xpath("./td/table//a[1]/@href","i").replaceAll("[^\\d]*", ""));
detailJSON.put("img", aa.xpath.xpath("./td/table//img[1]/@src", "i"));	
detailJSON.put("title", aa.xpath.xpath("./td/table//img[1]/@title", "i"));
detailJSON.put("price", aa.xpath.xpath("./td[2]/text()", "i"));
detailJSON.put("num", aa.xpath.xpath("./td/input[1]/@value", "i"));
listJSON.put(detailJSON);
%>
</aa:for-each>


<%
JSONObject resultJSON = new JSONObject();

resultJSON.put("list", listJSON);
resultJSON.put("total", aa.xpath.xpath("//td[@id='total_desc']/text()", "carts").replaceAll("[^\\d\\.]*", ""));
		
out.println(resultJSON.toString());
%>