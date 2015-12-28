<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<aa:http id="checkout" url="http://p9line/flow.php?step=checkout"></aa:http>
<%
JSONObject resultJSON = new JSONObject();
String username = aa.xpath.xpath("normalize-space(//ul[@class='userinfos']//a[contains(@href,'logout')]/../a[1]/text())", "checkout");
resultJSON.put("username", username);

JSONArray arrJSON = new JSONArray();
%>
<aa:for-each var="i" xpath="//div[@class='flowBox']" dsId="checkout">
<%
arrJSON.put(aa.xpath.copyOfNodeAsStr(".", "i").replaceAll("<a[^>]*>修改</a>", "").replaceAll("href=\"goods.php\\?id=([\\d]*)\"", "data-toggle='section' href='goods_section.html?id=$1'").replaceAll("(<textarea[^>]*)/>", "$1></textarea>").replaceAll("<table[^>]*>", "<table class='gridtable'>").replaceAll("</?script[^>]*>", "").replaceAll("<input src[^>]*>", "").replaceAll("onclick=\"[^\"]*\"", ""));
%>
</aa:for-each>
<%
resultJSON.put("flows", arrJSON);
out.println(resultJSON.toString());
%>