<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<aa:http id="myddress" url="http://p9line/user.php?act=address_list"></aa:http>
<%
JSONObject resultJSON = new JSONObject();

JSONArray arrJSON = new JSONArray();

JSONArray formJSON = new JSONArray();
%>
<aa:for-each var="i" xpath="//form[@name='theForm']" dsId="myddress">
<%
JSONObject detail = new JSONObject();
%>
<aa:for-each var="j" xpath=".//input[not(@type='button' or @type='submit')]" dsId="i">
<%
detail.put(aa.xpath.xpath("./@name", "j"), aa.xpath.copyOfNodeAsStr(".", "j").replaceAll("onclick=\"[^\"]*\"", "").replaceAll("style=.[^\"]*.", ""));
%>
</aa:for-each>
<aa:for-each var="k" xpath=".//select" dsId="i">
<%
detail.put(aa.xpath.xpath("./@name", "k"), aa.xpath.copyOfNodeAsStr(".", "k").replaceAll("onclick=\"[^\"]*\"", "").replaceAll("style=.[^\"]*.", ""));
%>
</aa:for-each>
<%
detail.put("hasAddress", aa.xpath.xpath(".//input[@name='address_id']/@value", "i").length()>0?true:false);
formJSON.put(detail);
%>
</aa:for-each>
<%
resultJSON.put("flows", arrJSON);
resultJSON.put("form", formJSON);
out.println(resultJSON.toString());
%>
