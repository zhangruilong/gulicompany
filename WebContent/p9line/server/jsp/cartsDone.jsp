<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "";
String method = "";
String type = request.getParameter("type");
if("detail".equals(type)){
	url = "http://p9line/user.php?act=order_detail&order_id="+request.getParameter("id");
	method = "get";
}else{
	url = "http://p9line/flow.php?step=done";
	method = "post";
}
%>
<aa:http id="cartsDone" url="<%=url %>" method="<%=method %>"></aa:http>
<%
JSONObject resultJSON = new JSONObject();

if("detail".equals(type)){
	resultJSON.put("shipping", aa.xpath.xpath("//div/table[last()]/tr[1]/td[2]/text()", "cartsDone"));
	resultJSON.put("payment", aa.xpath.xpath("//div/table[last()]/tr[2]/td[2]/text()", "cartsDone"));
	resultJSON.put("price", aa.xpath.xpath("//table[last()-1]/tr[1]/td[1]", "cartsDone").replaceAll("[^\\d\\.]*", ""));
	resultJSON.put("orderId", aa.xpath.xpath("//div/table[1]/tr[1]/td[2]/text()", "cartsDone"));
	resultJSON.put("url", aa.xpath.xpath("//div[contains(@class,'userCenterBox')][1]//input[@type='button']/@onclick", "cartsDone").replaceAll("window.open.'([^']*)'.", "$1"));
	resultJSON.put("yinlian", aa.xpath.copyOfNodeAsStr("//form[contains(@action, 'unionpaysecure')]", "cartsDone").replaceAll("<form", "<form id='yinlianForm'"));
	resultJSON.put("wxqrcode", aa.xpath.xpath("//div[@class='wx_qrcode']/img/@src", "cartsDone"));
	
}else{
	resultJSON.put("shipping", aa.xpath.xpath("//div[@class='flowBox'][1]//table[1]//strong[1]", "cartsDone"));
	resultJSON.put("payment", aa.xpath.xpath("//div[@class='flowBox'][1]//table[1]//strong[2]", "cartsDone"));
	resultJSON.put("price", aa.xpath.xpath("//div[@class='flowBox'][1]//table[1]//strong[3]", "cartsDone"));
	resultJSON.put("orderId", aa.xpath.xpath("//div[@class='content'][1]//h6/font", "cartsDone"));
	resultJSON.put("url", aa.xpath.xpath("//div[@class='flowBox'][1]//table[1]//input[@type='button']/@onclick", "cartsDone").replaceAll("window.open.'([^']*)'.", "$1"));
	resultJSON.put("yinlian", aa.xpath.copyOfNodeAsStr("//form[contains(@action, 'unionpaysecure')]", "cartsDone").replaceAll("<form", "<form id='yinlianForm'"));
	resultJSON.put("wxqrcode", aa.xpath.xpath("//div[@class='wx_qrcode']/img/@src", "cartsDone"));
}

JSONArray list = new JSONArray();
%>
<aa:for-each var="i" xpath="//table[3]/tr[position() > 1]" dsId="cartsDone">
<%
JSONObject detail = new JSONObject();
detail.put("title", aa.xpath.xpath("./td[1]/a/text()", "i"));
detail.put("price", aa.xpath.xpath("./td[3]/text()", "i"));
detail.put("num", aa.xpath.xpath("./td[4]/text()", "i"));
list.put(detail);
%>
</aa:for-each>
<%
resultJSON.put("list", list);
out.println(resultJSON.toString());
%>