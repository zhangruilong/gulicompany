<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ordermway = request.getParameter("ordermway");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-lang-zh_CN.js" charset="GBK"></script>
<style type="text/css">
.button{
	padding: 0px;
	width: 50px;
	border-radius:6px;
}
</style>
</head>
<body>
<form id='main_form' action="allOrder.action" method="post">
 <input type="hidden" id="staTime" name="staTime" value="${requestScope.staTime }">
 <input type="hidden" id="endTime" name="endTime" value="${requestScope.endTime }">
 <input type="hidden" id="ordermcompany" name="ordermcompany" value="${sessionScope.company.companyid }">
<div class="nowposition">当前位置：订单管理》全部订单</div>
<div class="navigation">
<div>下单时间:</div><div id="divDate" class="date"></div>
<div>到:</div><div id="divDate2"  class="date"></div>
模糊查询:<input type="text" class="ordermcode_query" name="ordermcode" value="${requestScope.order.ordermcode }">
<input class="button" type="button" value="查询" onclick="subfor()">
<input class="button" type="button" value="导出" onclick="report()">
<input class="button" type="button" value="打印" onclick="doprint()">
<input class="button" type="button" value="详情" onclick="operation('详情')">
<input style="background-image: url('');background-color: #83CD1F;" class="button" type="button" value="确认" onclick="operation('状态','已确认');">
<input style="background-image: url('');background-color: #DAD52B;" class="button" type="button" value="发货" onclick="operation('状态','已发货');">
<input style="background-image: url('');background-color: #1D6BE9;" class="button" type="button" value="完成" onclick="operation('状态','已完成');">
<input style="background-image: url('');background-color: #D33E2C;" class="button" type="button" value="删除" onclick="operation('删除')">
</div>
<table class="bordered" >
    <thead>

    <tr>
    	<th></th>
        <th>序号</th>
		<th>订单编号</th>
		<th>支付方式</th>
		<th>种类</th>
		<th>下单金额</th>
		<th>实际金额</th>
		<th style="width:29px;">状态</th>
		<th style="width:118px;">下单时间</th>
		<th>修改时间</th>
		<th>客户名称</th>
		<th>联系人</th>
		<th>手机</th>
		<th>地址</th>
		<th>订单源</th>
		<th>类型</th>
		<th>层级</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.allOrder) != 0 }">
	<c:forEach var="order" items="${requestScope.allOrder }" varStatus="orderSta">
		<tr>
			<td><input type="checkbox" id="${order.ordermid}" name="${order.ordermcustomer },${order.ordermaddress }"></td>
			<td><c:out value="${orderSta.count}"></c:out></td>
			<td>${order.ordermcode}</td>
			<td>${order.ordermway}</td>
			<td>${order.ordermnum}</td>
			<td>${order.ordermmoney}</td>
			<td>${order.ordermrightmoney}</td>
			<td class="td_orderm_statue">${order.ordermstatue}</td>
			<td>${order.ordermtime }</td>
			<td>${order.updtime}</td>
			<td>${order.customershop}</td>
			<td>${order.ordermconnect}</td>
			<td>${order.ordermphone}</td>
			<td>${order.ordermaddress}</td>
			<td>${order.ordermemp}</td>
			<td>${order.ordermcustype=='3'?'餐饮客户':(order.ordermcustype=='2'?'商超客户':(order.ordermcustype=='1'?'组织单位客户':''))}</td>
			<td>${order.ordermcuslevel}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.allOrder)==0 }">
		<tr><td colspan="18" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td class="td_fenye" colspan="18" align="center">	
			 <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('1')">第一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>第一页</span>
		 </c:if>
		  <c:if test="${requestScope.pagenow > 1 }">
		 	<a onclick="fenye('${requestScope.pagenow - 1 }')">上一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == 1 }">
		 	<span>上一页</span>
		 </c:if>
		 	
		 	<span>当前第${requestScope.pagenow }页</span>
		 	
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pagenow + 1 }')">下一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>下一页</span>
		 </c:if>
		 <c:if test="${requestScope.pagenow < requestScope.pageCount }">
		 	<a onclick="fenye('${requestScope.pageCount }')">最后一页</a>
		 </c:if>
		 <c:if test="${requestScope.pagenow == requestScope.pageCount }">
		 	<span>最后一页&nbsp;</span>
		 </c:if>
		 	<span>跳转到第<input style="width: 22px;text-align: center;" size="1" type="text" id="pagenow" name="pagenow" value="${requestScope.pagenow }">页</span>
		 	<input type="submit" value="GO" style="width: 40px;height: 20px;font-size:8px; text-align: center;cursor:pointer;">
		 	<span>一共 ${requestScope.count } 条数据</span>
		 </td>
	 </tr>       
</table>
</form>
<script type="text/javascript">
var ordermway = '<%=ordermway %>';
var ordermid = '${requestScope.order.ordermid }';
$(function(){
	if(ordermway == null || ordermway == ''){
		$(".nowposition").html("当前位置：订单管理》全部订单");
	} else if(ordermway == '在线支付'){
		$(".nowposition").html("当前位置：订单管理》在线支付订单");
	} else if(ordermway == '货到付款'){
		$(".nowposition").html("当前位置：订单管理》货到付款订单");
	}
	if(ordermid && ordermid != 'null'){
		$('#'+ordermid).attr('checked',true);
	}
	$("#main_form").on("submit",function(){
		checkCondition();
	});
})
//检查查询条件是否变化
function checkCondition(){
	$(".ordermcode_query").val($.trim($(".ordermcode_query").val()));
	if($(".ordermcode_query").val() != '${requestScope.order.ordermcode }'){
		$("#pagenow").val('1');
	}
	if($('#staTime').val() != '${requestScope.staTime }'){
		$("#pagenow").val('1');
	}
	if($('#endTime').val() != '${requestScope.endTime }'){
		$("#pagenow").val('1');
	}
}
//打印
function doprint(){
	var itemids = '';
	var itemCus = '';
	var itemAddress = '';
	var count = 0;
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			var odmInfo = $(item).attr("name").split(',');
			if(itemCus==''){					//判断是否是同一个客户
				itemCus = odmInfo[0];			//订单客户id
			} else if(itemCus != odmInfo[0]){
				count++;
				return false;
			}
			if(itemAddress == ''){				//判断是否是同一个地址
				itemAddress = odmInfo[1];		//订单地址
			} else if(itemAddress != odmInfo[1]){
				count++;
				return false;
			}
			itemids += $(item).attr("id")+',';
		}
	});
	if(count==0 && itemCus!=''){
		window.open("print.jsp?ordermids="+itemids);
	} else {
		alert('操作失败！多个订单合并打印时，须客户和收货信息完全一致。');
	}
}
//详情,状态,删除
function operation(msg,statue){
	var count = 0;
	var itemids = '';
	$("[type='checkbox']").each(function(i,item){
		if(item.checked==true){
			itemids += $(item).attr("id")+',';
			count++;
		}
	});
	if(count > 0 && msg=='打印'){
		window.open("print.jsp?ordermids="+itemids);
		//window.open("printOrder.action?ordermid="+itemid);
	} else if(count > 0 && count < 2){
		var itemid = itemids.substring(0,itemids.length-1);
		if(msg == '详情'){
			window.location.href = "orderDetail.action?ordermid="+itemid+
					"&ordermcompany=${sessionScope.company.companyid }&ordermcode=${requestScope.order.ordermcode }"+
					"&staTime=${requestScope.staTime}&endTime=${requestScope.endTime}&pagenow=${requestScope.pagenow }"+
					"&ordermway="+ordermway;
		} else if(msg == '状态'){
			if(confirm("是否修改订单状态")){
				$.post('updateStatue.action',{
					"ordermid":itemid,
					"ordermstatue":statue
				},function(data){
					if(data == '1'){
						alert("修改成功!");
						$("#"+itemid).parent().nextAll(".td_orderm_statue").text(statue);
					}
				});
			}
		} else if(msg == '删除'){
			if(confirm("是否删除订单")){
				$.post('updateStatue.action',{
					"ordermid":itemid,
					"ordermstatue":"已删除"
				},function(data){
					if(data == '1'){
						alert("删除成功!");
						window.location.reload();
					}
				});
			}
		}
	} else if(count == 0){
		alert("请选择订单");
	} else {
		alert("只能选择一个订单");
	}
	return false;
}
var md;						//第一个日期对象
var md2;					//第二个日期对象
   Ext.onReady(function(){
		md = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:90,
			format:"Y-m-d",
			emptyText:"${(requestScope.staTime == null || requestScope.staTime == '')?'请选择日期...':requestScope.staTime}"		//默认显示的日期
		});
		md.render('divDate');
		
		md2 = new Ext.form.DateField({
			name:"testDate",
			editable:false, //不允许对日期进行编辑
			width:90,
			format:"Y-m-d",
			emptyText:"${(requestScope.endTime == null || requestScope.endTime == '')?'请选择日期...':requestScope.endTime}"		//默认显示的日期
		});
		md2.render('divDate2');
   });
//导出报表
function report(){
	window.location.href ="exportOrderReport.action?ordermcompany=${sessionScope.company.companyid }"+
	"&staTime=${requestScope.staTime }&endTime=${requestScope.endTime }&ordermcode=${requestScope.order.ordermcode }";
}
//查询
function subfor(){
	var gedt = Ext.util.Format.date(md.getValue(), 'Y-m-d');	//得到查询时间
	var gedt2 = Ext.util.Format.date(md2.getValue(), 'Y-m-d');	
	if(gedt == ''){
		gedt = "${requestScope.staTime}";
	}
	if(gedt2 == ''){
		gedt2 = "${requestScope.endTime}";
	}
	$("#staTime").val(gedt);
	$("#endTime").val(gedt2);
	checkCondition();
	document.forms[0].submit();
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition();
	document.forms[0].submit();
}
//修改订单状态
function updateStatue(statue){
	if(confirm("是否修改订单状态")){
		window.parent.main.location.href = 
			"deliveryGoods.action?ordermid="
					+ordermid
					+"&ordermcompany="
					+ordermcompany
					+"&ordermstatue="
					+statue;
	}
}
</script>
</body>
</html>