<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customertype = request.getParameter("customer.customertype");
%>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../sysjs/jquery.min.js"></script>
<script type="text/javascript" src="../companySys/js/common.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="nowposition">当前位置：客户管理》大客户》客户特殊价格商品</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="largecuspriceprice" name="largecuspriceprice" value="">
<input class="button" type="button" value="查询" onclick="initLargePG(1)">
<input class="button" type="button" value="查询" onclick="initLargePG(1)">
<input type="checkbox" name="priceGoods_cb">
</div>
<table class="bordered" id="LGC_PGtable">
    <thead>
    <tr>
    	<th> </th>
        <th>序号</th>
		<th>编码</th>
		<th>名称</th>
		<th>规格</th>
		<th>类别</th>
		<th>价格</th>
		<th>单位</th>
		<th>描述</th>
		<th>创建时间</th>
		<th>修改时间</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
var customerid = '${param.customerid}';
var companyid = '${param.companyid}';
$(function(){
	initLargePG(1);
});
//初始化客户特殊价格商品数据
function initLargePG(targetPage){
	var data = {
			"largecuspricecompany":companyid,
			"largecuspricecustomer":customerid,
			"pagenow":targetPage,
			"largecuspriceprice":$.trim($("#largecuspriceprice").val())
		}
	$.post("querylargeCusPriceGoods.action",data,function(data){
		if(data.largeCusPrice.length == 0){
			$("#LGC_PGtable").append('<tr><td colspan="11" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>');
		} else {
			$("#LGC_PGtable tr:gt(0)").remove();
			$.each(data.largeCusPrice,function(i,item){
				var strJSON = JSON.stringify(item);									//商品数据字符串
				$("#LGC_PGtable").append('<tr>'+
						'<td><input type="checkbox" name="priceGoods_cb"></td>'+
						'<td>'+(i+1)+'</td>'+
						'<td>'+item.lcpGoods.goodscode+'</td>'+
						'<td>'+item.lcpGoods.goodsname+'</td>'+
						'<td>'+item.lcpGoods.goodsunits+'</td>'+
						'<td>'+item.lcpGoods.gClass.goodsclassname+'</td>'+
						'<td>'+item.largecuspriceprice+'</td>'+
						'<td>'+item.largecuspriceunit+'</td>'+
						'<td>'+ typeNullFoString(item.largecuspricedetail) +'</td>'+
						'<td>'+item.largecuspricecreatetime+'</td>'+
						'<td>'+ typeNullFoString(item.largecuspricecreator) +'</td>'+
					'</tr>');
			});
			var goodsfenye = '<tr><td colspan="12">';
			if(data.pagenow > 1){
				goodsfenye += '<a onclick=fenye("1")>第一页</a><a onclick=fenye("'+(parseInt(data.pagenow)-1)+'")>上一页</a>';
				
			} else {
				goodsfenye += '<span>第一页</span><span>上一页</span>';
			}
			goodsfenye += '<span>当前第'+data.pagenow+'页</span>';
			if(data.pagenow < data.pageCount){
				goodsfenye += '<a onclick=fenye("'+(parseInt(data.pagenow)+1)+'")>下一页</a><a onclick=fenye("'+data.pageCount+'")>最后一页</a>';
			} else {
				goodsfenye += '<span>下一页</span><span>最后一页&nbsp;</span>';
			}
			goodsfenye += '<span>跳转到第<input class="fenyelan_input" size="1" type="text" id="pagenow" value="'+
				data.pagenow+'">页</span>'+
			 	'<input onclick=goodsPageTo("'+data.pageCount+'") type="button" value="GO" class="fenyelan_button">'+
		 		'<span>一共 '+data.count+' 条数据</span>';
			$("#LGC_PGtable").append(goodsfenye);
		}
	});
}
//大客户特殊价格商品分页
function fenye(targetPage){
	initLargePG(targetPage);
}
</script>
</body>
</html>