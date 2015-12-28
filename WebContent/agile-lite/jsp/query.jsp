<%@ page language="java" import="java.util.*,com.fiberhome.bcs.appprocess.common.util.*"
  contentType="application/uixml+xml; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ include file="/client/adapt.jsp"%>
  <%
  String search = aa.req.getParameterFromUrl("search");
  String sql = "select * from tbl_task where title like '%"+search+"%' limit 20";
  System.out.println(sql);
  %>
  <aa:sql-excute dbId="postgresql" sql="<%=sql %>" id="query"></aa:sql-excute>
  {
  list:[
  <aa:for-each var="item" xpath="//datarow" dsId="query">
  {
  title : '<%=aa.xpath.xpath("./datacol[@name='title']", "item")%>',
  id : '<%=aa.xpath.xpath("./datacol[@name='id']", "item")%>'
  },
  </aa:for-each>
  ]
  }