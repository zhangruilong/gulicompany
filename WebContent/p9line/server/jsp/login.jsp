<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*,org.json.*"
 contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<%
String url = "http://p9line/user.php";
%>

<aa:http id="login" method="post" url="<%=url %>">
	<aa:header name="Referer" value="http://www.p9line.cn/user.php"/>
	<aa:param name="act" value="act_login"/>
	<aa:param name="back_act" value="/index.php"/>
</aa:http>
<%
JSONObject resultJSON = new JSONObject();
String username = aa.xpath.xpath("normalize-space(//ul[@class='userinfos']//a[contains(@href,'logout')]/../a[1]/text())", "login");
resultJSON.put("result", username.length()>0?"success":"fail");
if(username.length()>0){
	resultJSON.put("username", username);
}

out.print(resultJSON.toString());
%>