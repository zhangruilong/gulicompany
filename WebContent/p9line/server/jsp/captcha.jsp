<%-- ExMobi JSP文件，注释和取消快捷键统一为Ctrl+/ 多行注释为Ctrl+Shift+/ --%>
<%@ page language="java" import="java.util.*"
 contentType="img/jpeg; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/client/adapt.jsp"%>
<aa:http id="captcha" url="http://p9line/captcha.php?is_login=1&">
	<aa:header name="Referer" value="http://www.p9line.cn/user.php"/>
</aa:http>

<aa:file-download dsId="captcha"  filename="img.jpg" />
