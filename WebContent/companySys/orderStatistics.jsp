<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/tabsty.css" rel="stylesheet" type="text/css">
<link href="css/dig.css" rel="stylesheet" type="text/css">
<link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript" src="js/jquery.datetimepicker.js"></script>
<!-- 
<link rel="stylesheet" type="text/css" href="<%=basePath%>ExtJS/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath%>ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>ExtJS/ext-lang-zh_CN.js" charset="GBK"></script> -->
<style type="text/css">
.goods_select_popup{
	margin:0px auto;
	margin-top:1%;
	width: 600px;
	background-color: #FBF1E5;
}
.xdsoft_datetimepicker  .xdsoft_calendar td > div{
   padding-right:10px;
   padding-top: 5px
}
</style>
</head>
<body>
<form id='main_form' action="orderStatistics.action" method="post">
 <input type="hidden" name="companyid" value="${sessionScope.loginInfo.companyid }">
 <input type="hidden" id="staTime" name="staTime" value="${requestScope.staTime }">
 <input type="hidden" id="endTime" name="endTime" value="${requestScope.endTime }">
 <input type="hidden" id="hidden-queryShop" name="queryShop" value="${requestScope.endTime }">
<div class="nowposition">当前位置：订单管理》订单商品统计</div>
 
<div class="orderStat-navigationBar">
<div class="stat-bar1">
<div>下单时间:</div><div id="divDate" class="date"><input id="staDatetime" class="date-time" type="text" ></div>
<div>到:</div><div id="divDate2"  class="date"><input id="endDatetime" class="date-time" type="text" ></div>

模糊查询:<input type="text"  class="condition_query" name="condition" value="${requestScope.condition }">
<input class="button" type="button" value="查询" onclick="subfor()">
<input class="button" type="button" value="导出报表" onclick="report()">
</div>
<div class="stat-bar2">筛选条件：
<span>业务员：<input type="text" id="quEmp" name="quEmp" value="${requestScope.quEmp }" onclick="showEmp()"></span>
<span>品牌：<input type="text" id="quBrand" name="quBrand" value="${requestScope.quBrand }" onclick="showBrand()"></span>
<span>客户：<input type="text" id="quCus" name="quCus" value="${requestScope.quCus }" onclick="showCusNames()"></span>
</div>
</div>
<table class="bordered">
    <thead>
    <tr>
        <th>序号</th>
		<th>商品编码</th>
		<th>商品名称</th>
		<th>规格</th>
		<th>单位</th>
		<th>单价</th>
		<th>数量</th>
		<th>重量</th>
		<th>商品总价</th>
		<th>实际金额</th>
    </tr>
    </thead>
    <c:if test="${fn:length(requestScope.orderdList) != 0 }">
	<c:forEach var="orderd" items="${requestScope.orderdList }" varStatus="orderdSta">
		<tr>	
			<td><c:out value="${orderdSta.count}"></c:out></td>
			<td>${orderd.orderdcode}</td>
			<td>${orderd.orderdname}</td>
			<td>${orderd.orderdunits}</td>
			<td>${orderd.orderdunit}</td>
			<td>${orderd.orderdprice}</td>
			<td>${orderd.sumorderdnum}</td>
			<td>${orderd.orderdweight}</td>
			<td>${orderd.sumorderdmoney}</td>
			<td>${orderd.sumorderdrightmoney}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">总计</td>
		<td>${requestScope.total.numtotal }</td>
		<td>${requestScope.total.weighttotal }</td>
		<td>${requestScope.total.moneytotal }</td>
		<td>${requestScope.total.rightmoneytotal }</td>
	</tr>
	</c:if>
	<c:if test="${fn:length(requestScope.orderdList)==0 }">
		<tr><td colspan="14" align="center" style="font-size: 26px;color: red;"> 没有可显示的信息</td></tr>
	</c:if>
    	<tr>
		 <td class="td_fenye" colspan="14" align="center">	
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
<!--业务员(EMP)弹框-->
<div class="cd-popup emp-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
<input class="button" type="button" value="确定" onclick="empConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenEmpShow()">
</div>
<div class="alert-emp-show">
</div>
</div>
</div>
<!--品牌弹框-->
<div class="cd-popup brand-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
<input class="button" type="button" value="确定" onclick="brandConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenBrandShow()">
</div>
<div class="alert-brand-show">
</div>
</div>
</div>
<!--客户名称弹框-->
<div class="cd-popup cusNames-popup" role="alert">
<div class="goods_select_popup">
<div class="navigation">
<!-- 查询店名 的 查询条件 -->
查询条件:&nbsp;&nbsp;<input type="text" id="queryShop" name="queryShop" value="${ requestScope.queryShop}" onchange="showCusNames()">
<input class="button" type="button" value="查询" onclick="showCusNames()">
<input class="button" type="button" value="确定" onclick="cusNameConditionConfirm()">
<input class="button" type="button" value="取消" onclick="hiddenCusShow()">
</div>
<div class="alert-cusNames-show">
</div>
</div>
</div>
<script type="text/javascript" src="js/getDate.js"></script>
<script type="text/javascript">
$.datetimepicker.setLocale('ch');												//设置日期的中文
var companyid = "${sessionScope.loginInfo.companyid}";
var currDateTime = formatDateTime(new Date());									//当前时间字符串
var quBrand = $('#quBrand').val();			//查询的品牌
var quEmp = $('#quEmp').val();				//查询的业务员
var quCus = $('#quCus').val();				//查询的客户店名
var mquCus = $('#quCus').val();				//查询的客户店名缓存
var queryShop = "${ requestScope.queryShop}";		//查询店名的 查询条件

var defStaTime = '${requestScope.staTime }';
var defEndTime = '${requestScope.endTime }';
//alert(defStaTime);
if(defStaTime == ''){
	defStaTime = currDateTime;
}
if(defEndTime == ''){
	defEndTime = currDateTime;
}
function cusConfirmAndSubfor(){
	cusNameConditionConfirm();
	subfor()
}
$(function(){
	$.ajax({
		url:"queryTimeCus.action",						//Com_orderCtl 店铺名称
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":defStaTime,
			"endTime":defEndTime
		},
		success:function(data){
			if(data.msg =='success'){
				$('.alert-cusNames-show').html('');
				$.each(data.cusNames ,function(i,item){
					if(quCus.indexOf(item) == -1){
						$('.alert-cusNames-show').append('<span>'+item+'</span>');
					} else {
						$('.alert-cusNames-show').append('<span class="alert-cusNames-selspan">'+item+'</span>');
					}
				});
				$(".alert-cusNames-show span").click(function(){
					if($(this).attr('class')=='alert-cusNames-selspan'){
						$(this).removeClass('alert-cusNames-selspan');
						mquCus = mquCus.replace($(this).text()+',','');
					} else {
						$(this).addClass('alert-cusNames-selspan');
						mquCus += $(this).text()+',';
					}
				});
			}
		},
		error:function(data){
			
		}
	});
	$.ajax({
		url:"timeOrderdGoodsBrand.action",						//Com_goodsCtl 商品品牌
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":defStaTime,
			"endTime":defEndTime
		},
		success:function(data){
			if(data.msg =='success'){
				$('.alert-brand-show').html('');
				$.each(data.brand ,function(i,item){
					if(typeof(item)=='undefined' || !item){
						item = '无填充';
					}
					if(quBrand.indexOf(item) == -1){
						$('.alert-brand-show').append('<span>'+item+'</span>');
					} else {
						$('.alert-brand-show').append('<span class="alert-brand-selspan">'+item+'</span>');
					}
				});
				$(".alert-brand-show span").click(function(){
					if($(this).attr('class')=='alert-brand-selspan'){
						$(this).removeClass('alert-brand-selspan');
					} else {
						$(this).addClass('alert-brand-selspan');
					}
				});
			}
		},
		error:function(data){
			
		}
	});
	$.ajax({
		url:"queryTimeEmp.action",				//在customerCtl里面 业务员名称
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":defStaTime,
			"endTime":defEndTime
		},
		success:function(data){
			if(data.msg=='success'){
				$(".alert-emp-show").html('');
				$.each(data.empLi,function(i,item){
					if(typeof(item)=='undefined' || !item){
						item = '无填充';
					}
					if(quEmp.indexOf(item) == -1){
						$(".alert-emp-show").append('<span>'+item+'</span>');
					} else {
						$('.alert-emp-show').append('<span class="alert-emp-selspan">'+item+'</span>');
					}
				});
				$(".alert-emp-show span").click(function(){
					if($(this).attr('class')=='alert-emp-selspan'){
						$(this).removeClass('alert-emp-selspan');
					} else {
						$(this).addClass('alert-emp-selspan');
					}
				});
			}
		},
		error:function(data){
			
		}
	});
	
	$('#staDatetime').datetimepicker({
		dayOfWeekStart : 1,
		lang:'ch',
		format:"Y-m-d H:i",      //格式化日期
		//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],				//禁用日期
		//startDate:	defStaTime,
		value: defStaTime,
		step:30
	});
	$('#endDatetime').datetimepicker({
		dayOfWeekStart : 1,
		lang:'ch',
		format:"Y-m-d H:i",      //格式化日期
		//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],				//禁用日期
		//startDate:	defEndTime,
		value: defEndTime,
		step:30
	});
	$('#staDatetime').change(function(){
		$('#endDatetime').focus();
	});
	$('#endDatetime').change(function(){
		$('.condition_query').focus();
	});
});
//筛选条件:业务员 确定
function empConditionConfirm(){
	var nowEmp = '';
	$('.alert-emp-show .alert-emp-selspan').each(function(i,item){
		nowEmp += $(item).text()+",";
	});
	$('#quEmp').val(nowEmp);
	$(".emp-popup").removeClass("is-visible");
}
//筛选条件:品牌 确定
function brandConditionConfirm(){
	var nowBrand = '';
	$('.alert-brand-show .alert-brand-selspan').each(function(i,item){
		nowBrand += $(item).text()+",";
	});
	$('#quBrand').val(nowBrand);
	$(".brand-popup").removeClass("is-visible");
}
//筛选条件:客户 确定
function cusNameConditionConfirm(){
	quCus=mquCus;
	$('#quCus').val(quCus);
	$(".cusNames-popup").removeClass("is-visible");
}
//关闭cusNames弹窗
function hiddenCusShow(){
	$(".cusNames-popup").removeClass("is-visible");
	mquCus = quCus;
}
//关闭brand弹窗
function hiddenBrandShow(){
	$(".brand-popup").removeClass("is-visible");
}
//关闭emp弹窗
function hiddenEmpShow(){
	$(".emp-popup").removeClass("is-visible");
}

//得到查询条件中的客户名称
function showCusNames(){
	if(!companyid){
		return;
	}
	var curQueryStaDatetime = $('#staDatetime').val();
	var curQueryEndDatetime = $('#endDatetime').val();
	if(curQueryStaDatetime.length==16){
		curQueryStaDatetime += ':00';
	}
	if(curQueryEndDatetime.length==16){
		curQueryEndDatetime += ':00';
	}
	$.ajax({
		url:"queryTimeCus.action",						//Com_orderCtl
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":curQueryStaDatetime,
			"endTime":curQueryEndDatetime,
			"queryShop":$('#queryShop').val()
		},
		success:function(data){
			if(data.msg =='success'){
				$('.alert-cusNames-show').html('');
				
				$.each(data.cusNames ,function(i,item){
					if(mquCus.indexOf(item) == -1){
						$('.alert-cusNames-show').append('<span>'+item+'</span>');
					} else {
						$('.alert-cusNames-show').append('<span class="alert-cusNames-selspan">'+item+'</span>');
					}
				});
				$(".cusNames-popup").addClass("is-visible");
				$(".alert-cusNames-show span").click(function(){
					if($(this).attr('class')=='alert-cusNames-selspan'){
						$(this).removeClass('alert-cusNames-selspan');
						mquCus = mquCus.replace($(this).text()+',','');
					} else {
						$(this).addClass('alert-cusNames-selspan');
						mquCus += $(this).text()+',';
					}
				});
			} else {
				alert('没有查询到可用的客户名称,可以重新设置查询时间后重试。');
			}
		},
		error:function(data){
			alert('操作失败!');
		}
	});
}

//得到查询条件中的品牌名称
function showBrand(){
	if(!companyid){
		return;
	}
	var curQueryStaDatetime = $('#staDatetime').val();
	var curQueryEndDatetime = $('#endDatetime').val();
	if(curQueryStaDatetime.length==16){
		curQueryStaDatetime += ':00';
	}
	if(curQueryEndDatetime.length==16){
		curQueryEndDatetime += ':00';
	}
	$.ajax({
		url:"timeOrderdGoodsBrand.action",						//Com_goodsCtl
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":$('#staDatetime').val(),
			"endTime":$('#endDatetime').val()
		},
		success:function(data){
			if(data.msg =='success'){
				$('.alert-brand-show').html('');
				var currQuBrand = $('#quBrand').val();
				$.each(data.brand ,function(i,item){
					if(typeof(item)=='undefined' || !item){
						item = '无填充';
					}
					if(currQuBrand.indexOf(item) == -1){
						$('.alert-brand-show').append('<span>'+item+'</span>');
					} else {
						$('.alert-brand-show').append('<span class="alert-brand-selspan">'+item+'</span>');
					}
				});
				$(".brand-popup").addClass("is-visible");
				$(".alert-brand-show span").click(function(){
					if($(this).attr('class')=='alert-brand-selspan'){
						$(this).removeClass('alert-brand-selspan');
					} else {
						$(this).addClass('alert-brand-selspan');
					}
				});
			} else {
				alert('没有查询到可用的品牌名称,可以重新设置查询时间后重试。');
			}
		},
		error:function(data){
			alert('操作失败!');
		}
	});
}
//得到查询条件中的业务员
function showEmp(){
	if(!companyid){
		return;
	}
	var curQueryStaDatetime = $('#staDatetime').val();
	var curQueryEndDatetime = $('#endDatetime').val();
	if(curQueryStaDatetime.length==16){
		curQueryStaDatetime += ':00';
	}
	if(curQueryEndDatetime.length==16){
		curQueryEndDatetime += ':00';
	}
	$.ajax({
		url:"queryTimeEmp.action",				//在customerCtl里面
		type:"post",
		data:{
			"companyid":companyid,
			"staTime":$('#staDatetime').val(),
			"endTime":$('#endDatetime').val()
		},
		success:function(data){
			if(data.msg=='success'){
				$(".alert-emp-show").html('');
				var currQuEmp = $('#quEmp').val();
				$.each(data.empLi,function(i,item){
					if(typeof(item)=='undefined' || !item){
						item = '无填充';
					}
					if(currQuEmp.indexOf(item) == -1){
						$(".alert-emp-show").append('<span>'+item+'</span>');
					} else {
						$('.alert-emp-show').append('<span class="alert-emp-selspan">'+item+'</span>');
					}
				});
				$(".emp-popup").addClass("is-visible");
				$(".alert-emp-show span").click(function(){
					if($(this).attr('class')=='alert-emp-selspan'){
						$(this).removeClass('alert-emp-selspan');
					} else {
						$(this).addClass('alert-emp-selspan');
					}
				});
			} else if(data.msg=='error'){
				alert('没有查询到可用的业务员名称,可以重新设置查询时间后重试。');
			}
		},
		error:function(data){
			alert('操作失败!');
		}
	});
}

 //检查查询条件是否变化
  function checkCondition(){
	var nowEmp = $('#quEmp').val();
	var nowBrand = $('#quBrand').val();
	var nowCus = $('#quCus').val();
	  
	$(".condition_query").val($.trim($(".condition_query").val()));
	//alert(nowEmp != quEmp);
	//alert(nowEmp+' -不等于- '+quEmp);
	if(nowEmp != quEmp){
   		$("#pagenow").val('1');
   	}
	if(nowBrand != quBrand){
   		$("#pagenow").val('1');
   	}
	if(nowCus != quCus){
   		$("#pagenow").val('1');
   	}
   	if($(".condition_query").val() != '${requestScope.condition }'){
   		$("#pagenow").val('1');
   	}
   	if($('#staTime').val() != '${requestScope.staTime }'){
   		$("#pagenow").val('1');
   	}
   	if($('#endTime').val() != '${requestScope.endTime }'){
   		$("#pagenow").val('1');
   	}
  }
//导出报表
function report(){
	window.location.href ="exportReport.action?companyid=${sessionScope.loginInfo.companyid }"+
					"&staTime=${requestScope.staTime }&endTime=${requestScope.endTime }"+
					"&quBrand=${requestScope.quBrand }&quEmp=${requestScope.quEmp }"+
					"&quCus=${requestScope.quCus }&condition=${requestScope.condition }";
}
//查询
function subfor(){
	var staDT = $('#staDatetime').val();
	var endDT = $('#endDatetime').val();
	
	if(staDT.length==16){
		staDT += ':00';
	}
	if(endDT.length==16){
		endDT += ':00';
	}
	//得到查询时间
	if(staDT && endDT){
		$('#staTime').val(staDT);
		$('#endTime').val(endDT);
	} else {
		alert('查询时间不能为空。');
		return;
	}
	checkCondition();
	$('#hidden-queryShop').val($('#queryShop').val());
	document.forms[0].submit();
}
//分页
function fenye(targetPage){
	//alert(targetPage);
	$("#pagenow").val(targetPage);
	checkCondition();
	$('#hidden-queryShop').val($('#queryShop').val());
	document.forms[0].submit();
}
</script>
</body>
</html>