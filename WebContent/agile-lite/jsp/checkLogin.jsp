<%@ page language="java" import="java.util.*,com.fiberhome.bcs.appprocess.common.util.*"contentType="application/uixml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ include file="/client/adapt.jsp"%>
  <%
  String username = aa.req.getParameter("username");
  String password = aa.req.getParameter("password");
  String sql = "select * from tbl_user where username='"+username+"' and password='"+password+"'";
  %>
  <aa:sql-excute dbId="postgresql" sql="<%=sql %>" id="checkLogin"></aa:sql-excute>
  {result:"<%=(aa.xpath.xpath("//result/@size", "checkLogin").equals("1")?"success":"fail")%>"}