<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customertype = request.getParameter("customer.customertype");
%>

<!DOCTYPE HTML >
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../guliwang/js/jquery-2.1.4.min.js"></script>
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
</head>
<body>
<form action="allCustomer.action" method="post" id="main_form">
<input type="hidden" name="ccustomercompany" value="${requestScope.ccustomerCon.ccustomercompany }">
<input type="hidden" name="customer.customertype" value="${requestScope.ccustomerCon.customer.customertype }">
<input type="hidden" name="creator" value="${requestScope.ccustomerCon.creator }">
<div class="nowposition">当前位置：客户管理》录单客户</div>
<div class="navigation">
查询条件:&nbsp;&nbsp;<input type="text" id="customercode" name="customer.customercode" value="${requestScope.ccustomerCon.customer.customercode }">
<input class="button" type="button" value="查询" onclick="subcustomerfor()">
<input class="button" type="button" value="新增" onclick="addLargeCus()">
<input class="button" type="button" value="特殊商品" onclick="largeCusGoods('1')">
<input class="button" type="button" value="录单" onclick="largeCusGoods('2')">
</div>
<table class="bordered" id="lgCus_tab">
    <thead>
    <tr>
    	<th></th>
        <th>序号</th>
		<th>客户编码</th>
		<th>客户名称</th>
		<th>客户地址</th>
		<th>联系人</th>
		<th>联系电话</th>
		<th>客户类型</th>
		<th>价格等级</th>
		<th>修改时间</th>
		<th>客户经理</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.ccustomerList) != 0 }">
	<c:forEach var="ccustomer" items="${requestScope.ccustomerList }" varStatus="ccustomerSta">
		<tr>
			<td><input type="checkbox" value="${ccustomer.ccustomerid}"></td>
			<td><c:out value="${ccustomerSta.count}"></c:out></td>
			<td>${ccustomer.customer.customercode}</td>
			<td>${ccustomer.customer.customershop}</td>
			<td>${ccustomer.customer.customercity}${ccustomer.customer.customerxian}${ccustomer.customer.customeraddress}</td>
			<td>${ccustomer.customer.customername}</td>
			<td>${ccustomer.customer.customerphone}</td>
			<td>${ccustomer.customer.customertype == 3?'餐饮客户':(ccustomer.customer.customertype == 2?'商超客户':'组织单位客户')}</td>
			<td>${ccustomer.ccustomerdetail}</td>
			<td>${ccustomer.customer.updtime}</td>
			<td>${ccustomer.createtime}</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${fn:length(requestScope.ccustomerList)==0 }">
		<tr><td colspan="11" align="center" style="font-size: 20px;color: red;"> 没有信息</td></tr>
	</c:if>
    	<tr>
		 <td colspan="11" align="center">
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
<!--新增录单客户弹框-->
<div class="cd-popup" role="alert">
	<div class="elegant-aero" id="addLC_popupForm">
			<h1>新增录单客户</h1>
			<label><span>编码 :</span><input id="customer.customercode" type="text"
				name="customer.customercode" placeholder="编码" /></label>
			<label><span>客户名称 :</span><input id="customer.customershop" type="text"
				name="customer.customershop" placeholder="名称" /></label>
			<label><span>所在城市 :</span>
			<select name="newLargeCus_CityName" id="newLargeCus_CityName">
				<option>嘉兴市</option>
			</select>
			</label>
			<label> <span>所在地区 :</span>
			<select name="newLargeCus_xian" id="newLargeCus_xian">
				<option>海盐县</option>
				<option>秀洲区</option>
				<option>嘉善县</option>
				<option>南湖区</option>
			</select>
			</label>
			<label><span>地址 :</span><input id="customer.customeraddress" type="text"
				name="customer.customeraddress" placeholder="地址" /></label>
			<label><span>联系人 :</span><input id="customer.customername" type="text"
				name="customer.customername" placeholder="联系人" /></label>
			<label><span>联系电话 :</span><input id="customer.customerphone" type="text"
				name="customer.customerphone" placeholder="联系电话" /></label>
			<label> <span>客户类型 :</span>
			<select name="newLargeCus_tp" id="newLargeCus_tp">
				<option name="3">餐饮客户</option>
				<option name="2">商超客户</option>
				<option name="1">组织单位客户</option>
			</select>
			</label>
			<label><span>价格层级 :</span><input id="customer.customerlevel" type="number"
				name="customer.customerlevel" placeholder="价格层级" /></label>
			<!-- <label><span>买赠描述 :</span><textarea name="givegoodsdetail"></textarea></label> -->
			<p><label><input type="button"
				class="popup_button" value="提交" onclick="saveLargeCus()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="从商品中选择" onclick="dobiaopin()"/>
			</label>
			<label><input type="button"
				class="popup_button" value="关闭窗口" onclick="close_popup()"/>
			</label></p>
	</div>
</div>
<script type="text/javascript">
var customertype = '<%=customertype %>';
$(function(){
	$("#main_form").on("submit",function(){
		checkCondition();
	});
	$("#addLC_popupForm select[name='newLargeCus_CityName']").on("change",function(){
		var  myselect=document.getElementById("newLargeCus_CityName");			//根据城市复选框id得到城市复选框对象
		var index=myselect.selectedIndex ;     									//得到被选择的option的下标
		var cityName = myselect.options[index].text;							//根据下标得到文本内容
		queryXian();
	});
})

//跳转到特殊商品详情页或者跳转到录单页
function largeCusGoods(option){
	var cusid = "";
	var count = 0;
	$("#lgCus_tab :checkbox").each(function(i,item){
		if(item.checked){
			cusid = $(item).val();
			count++;
		}
	});
	if(count>0 && count<2){
		if(option == '1'){
			window.location.href = "largeCusGoodsMana.jsp?customerid="+cusid+"&companyid=${requestScope.ccustomerCon.ccustomercompany }";
		} else if(option == '2'){
			window.location.href = "largeCusXiaDan.jsp?ccustomerid="+cusid+"&ccustomercompany=${requestScope.ccustomerCon.ccustomercompany }";
		}
	} else if(count == 0){
		alert("请选择一个客户!");
	} else {
		alert("只能选择一个客户!");
	}
}
//得到地区选项
function queryXian(){
	return ;
	$.ajax({
		url:"lcpQueryCity.action",
		type:"post",
		data:{
			"cityname":16
		},
		success:function(data){
			$.each(data,function(i,item){
				$("#customer.customerxian").append('<option value="'+item.cityid+'">'+item.cityname+'</option>');
			});
		},
		error:function(){
			alert("查询失败,没有查询到任何地区.");
		}
	});
}
//添加新录单客户
function addLargeCus(){
	$(".elegant-aero [type!='button']").val("");
	$(".cd-popup").addClass("is-visible");	//弹出窗口
}
//关闭添加录单客户窗口
function close_popup(){
	$(".cd-popup").removeClass("is-visible");	//移除'is-visible' class
}
//保存新增的录单客户
function saveLargeCus(){
	var  myselect1=document.getElementById("newLargeCus_CityName");			
	var index1=myselect1.selectedIndex ;     									
	
	var cityName = myselect1.options[index1].text;							
	
	var  myselect2=document.getElementById("newLargeCus_xian");				
	var index2=myselect2.selectedIndex ;     									
	
	var xian = myselect2.options[index2].text;								
	var  myselect3=document.getElementById("newLargeCus_tp");				
	var index3=myselect3.selectedIndex ;     									
	
	var type = myselect3.options[index3].getAttribute("name");					
	if(!cityName){
		alert("城市不能为空!");
		return;
	} 
	if(!xian){
		alert("地区不能为空!");
		return;
	}
	if(!type){
		alert("客户类型不能为空!");
		return;
	}
	var count = 0;
	$("#addLC_popupForm [name^='customer.']").each(function(i,item){
		var val = $(item).val();
		if( $.trim(val) == ''){
			alert($(item).attr("placeholder")+'不能为空!');
			count++;
			return false;
		}
	});
	if(count == 0){
		$.ajax({
			url:"saveLargeCus.action",
			type:"post",
			data:{
				"customercode":$("#addLC_popupForm input:eq(0)").val(),
				"customershop":$("#addLC_popupForm input:eq(1)").val(),
				"customeraddress":$("#addLC_popupForm input:eq(2)").val(),
				"customername":$("#addLC_popupForm input:eq(3)").val(),
				"customerphone":$("#addLC_popupForm input:eq(4)").val(),
				"customerlevel":$("#addLC_popupForm input:eq(5)").val(),
				"customertype":type,
				"customercity":cityName,
				"customerxian":xian,
			},
			success:function(data){
				alert("添加成功!");
				window.location.reload();
			},
			error:function(){alert("添加新客户失败,请重新添加.")}
		});
	}
}
//检查查询条件是否变化
function checkCondition(){
	if($("#customercode").val() != '${requestScope.ccustomerCon.customer.customercode }'){
		$("#pagenow").val('1');
	}
	if(parseInt($("#pagenow").val()) > '${requestScope.pageCount }' ){
		$("#pagenow").val('${requestScope.pageCount }');
	}
}
//提交查询条件
function subcustomerfor(){
	$("#customercode").val($.trim($("#customercode").val()));
	checkCondition();
	document.forms[0].submit();
}
//分页
function fenye(targetPage){
	$("#pagenow").val(targetPage);
	checkCondition()
	document.forms[0].submit();
}
//导出
function reportCustomer(){
	window.location.href ="exportCustomerReport.action?"+
			"ccustomercompany=${requestScope.ccustomerCon.ccustomercompany }"+
			"&customer.customertype=${requestScope.ccustomerCon.customer.customertype }"+
			"&customer.customercode=${requestScope.ccustomerCon.customer.customercode }"+
			"&creator=${requestScope.ccustomerCon.creator }";
}
</script>
</body>
</html>