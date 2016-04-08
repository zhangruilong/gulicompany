<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String searchdishesvalue = request.getParameter("searchdishes");
	String searchclassesvalue = request.getParameter("searchclasses");
%>
<!doctype html> 
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<link href="css/dig.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="gl-box">
	<div class="home-search-wrapper">
        <span class="citydrop"><span id="curgoodsclass">大米</span> <em><img src="images/dropbg.png" ></em></span> 
        <div class="menu">
            <div class="menu-tags home-city-drop">
                <div class="fenlei-tit">食材谱</div>
                <div class="wrapper">
                	<div class="fenlei-left">
                    	<ul id="fenlei-left">
                        </ul>
                    </div>
                    <div class="fenlei-right">
                    </div>
                </div>
            </div>
        </div>
        <input id="searchdishes" type="text" placeholder="请输入食材名称" onkeydown="entersearch()"/>
        <a href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a>
    </div>
    <div class="goods-wrapper">
        <ul class="home-hot-commodity">
        </ul>
    </div>
</div>
<div class="personal-center-nav">
    <ul>
        	<li><a href="index.jsp"><em class="ion-home"></em>首页</a></li>
            <li class="active"><a href="goods.jsp"><em class="ion-bag"></em>商城</a></li>
            <li><a href="order.jsp"><em class="ion-clipboard"></em>订单</a></li>
            <li><a href=""><em class="ion-android-person"></em>我的</a></li>
    </ul>
</div>
<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p class="meg">操作成功!</p>
            <a class="cd-popup-close">确定</a>
		</div>
	</div>
</div>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery-dropdown.js"></script>
<script> 
var basePath = '<%=basePath%>';
var searchdishesvalue = '<%=searchdishesvalue%>';
var searchclassesvalue = '<%=searchclassesvalue%>';
var emp = JSON.parse(window.localStorage.getItem("emp"));
var customer = JSON.parse(window.localStorage.getItem("customeremp"));
$(function(){ 
	if(!window.localStorage.getItem("totalnum")){
		//如果没有totalnum
		window.localStorage.setItem("totalnum",0);
		$("#totalnum").text(0);
	}else{
		$("#totalnum").text(window.localStorage.getItem("totalnum"));
	}
	if(window.localStorage.getItem("totalnum")==0)
		$("#totalnum").hide();
	if(!window.localStorage.getItem("totalmoney")){
		window.localStorage.setItem("totalmoney",0);
	}
	//通过ajax查询大类
	getJson(basePath+"GoodsclassAction.do",{method:"mselAll",wheresql:"goodsclassparent='root'"},initGoodsclass,null);
	if(searchdishesvalue!="null"&&searchdishesvalue!=""){
		getJson(basePath+"GoodsviewAction.do",{method:"mselAll",query:searchdishesvalue,companyid:emp.empcompany,customerid:customer.customerid,customertype:customer.customertype,customerlevel:customer.customerlevel},initDishes,null);
	}else if(searchclassesvalue!="null"&&searchclassesvalue!=""){
		$("#curgoodsclass").html(searchclassesvalue);
		getJson(basePath+"GoodsviewAction.do",{method:"mselAll",companyid:emp.empcompany,customerid:customer.customerid,customertype:customer.customertype,customerlevel:customer.customerlevel,wheresql:"goodsclassname='"+searchclassesvalue+"'"},initDishes,null);
	}else{
		getJson(basePath+"GoodsviewAction.do",{method:"mselAll",companyid:emp.empcompany,customerid:customer.customerid,customertype:customer.customertype,customerlevel:customer.customerlevel,wheresql:"goodsclassname='大米'"},initDishes,null);
	}
	$(".cd-popup").on("click",function(event){		//绑定点击事件
		$(this).removeClass("is-visible");	//移除'is-visible' class
	});
})
function entersearch(){
    var event = window.event || arguments.callee.caller.arguments[0];
    if (event.keyCode == 13)
    {
    	searchdishesvalue = $("#searchdishes").val();
    	getJson(basePath+"GoodsviewAction.do",{method:"mselAll",query:searchdishesvalue,companyid:emp.empcompany,customerid:customer.customerid,customertype:customer.customertype,customerlevel:customer.customerlevel},initDishes,null);
    }
}
/* $(".citydrop").click(function(){ 
	getJson(basePath+"GoodsclassAction.do",{method:"mselAll",wheresql:"goodsclassparent='root'"},initGoodsclass,null);
})  */
//商品大小类
function initGoodsclass(data){																								//初始化商品大小类
	 $("#fenlei-left").html("");
	 $.each(data.root, function(i, item) {				//遍历 data 中的 root 
		if(i==0){
			$("#fenlei-left").append('<li class="active" name="'+item.goodsclassid+'"><a href="#"><img src="'+item.goodsclassdetail+'" > '+item.goodsclassname+'</a></li>');
			getJson(basePath+"GoodsclassAction.do",{method:"mselAll",wheresql:"goodsclassparent = '"+item.goodsclassid+"'"},initGoodsclassright,null);
		}else{
			$("#fenlei-left").append('<li name="'+item.goodsclassid+'"><a href="#"><img src="'+item.goodsclassdetail+'" > '+item.goodsclassname+'</a></li>');
		}
    });
 	$("#fenlei-left li").each(function(){				//遍历 li
		$(this).click(function(){
			$(this).addClass('active').siblings().removeClass('active');	//当前元素被点击时添加 class 'active' 同时把其他同级元素 去除  class 'active'
			//ajax查询小类并初始化
			getJson(basePath+"GoodsclassAction.do",{method:"mselAll",wheresql:"goodsclassparent = '"+$(this).attr('name')+"'"},initGoodsclassright,null);
		})
	});
}
//小类
function initGoodsclassright(data){																							//大小类右边
	 $(".fenlei-right").html("");
	 $.each(data.root, function(i, item) {
		$(".fenlei-right").append('<a href="goods.jsp?searchclasses='+item.goodsclassname+'">'+item.goodsclassname+'</a>');
    });
}
//商品
function initDishes(data){
     $(".home-hot-commodity").html("");
 	 $.each(data.root, function(i, item) {
 		$(".home-hot-commodity").append('<li>'+
 	         	'<span class="fl"><img src="images/pic2.jpg" ></span> '+
 	             '<h1>'+item.goodsname+'<span>('+item.goodsunits+')</span></h1>'+
 	           '  <div class="block"> '+
 	             	'<span>'+
 	                 '    <input type="radio" id="'+item.goodsid+'radio1" name="'+item.goodsid+'radio" class="regular-radio" checked />'+
 	                 '    <label for="'+item.goodsid+'radio1">单品价:<font class="font-oringe">￥'+item.pricesprice+'</font>/'+item.pricesunit+'</label>'+
 	               '  </span>'+
 	               '  <span>'+
 	                   '  <input type="radio" id="'+item.goodsid+'radio2" name="'+item.goodsid+'radio" class="regular-radio" />'+
 	               '      <label for="'+item.goodsid+'radio2">套装价:<font class="font-oringe">￥'+item.pricesprice2+'</font>/'+item.pricesunit2+'</label>'+
 	               '  </span>'+
 	            ' </div>'+
 	           '  <div class="stock-num" name="'+item.goodsid+'">'+
 	                ' <span class="jian min" onclick="subnum(this,'+item.pricesprice
					   +')"></span>'+
 	                 '<input readonly="readonly" class="text_box shuliang" name="danpin" type="text" value="'+
 	                 getcurrennumdanpin(item.goodsid)+'"> '+
 	                ' <span class="jia add" onclick="addnum(this,'+item.pricesprice
					   +',\''+item.goodsname+'\',\''+item.pricesunit+'\',\''+item.goodsunits
					   +'\',\''+item.goodscode+'\',\''+item.goodsclassname
					   +'\',\''+item.goodscompany+'\',\''+item.companyshop+'\',\''+item.companydetail
					   +'\')"></span>'+
 	               '  <span hidden="ture" class="jian min" onclick="subnum(this,'+item.pricesprice2
				   +')"></span>'+
 	                ' <input readonly="readonly" hidden="ture" class="text_box shuliang" name="taozhuan" type="text" value="'+
 	                getcurrennumtaozhuan(item.goodsid)+'"> '+
 	                ' <span hidden="ture" class="jia add" onclick="addnum(this,'+item.pricesprice2
					   +',\''+item.goodsname+'\',\''+item.pricesunit2+'\',\''+item.goodsunits
					   +'\',\''+item.goodscode+'\',\''+item.goodsclassname
					   +'\',\''+item.goodscompany+'\',\''+item.companyshop+'\',\''+item.companydetail
					   +'\')"></span>'+
 	                ' <input type="checkbox" id="'+item.goodsid+'checkbox" class="chk_1" '+item.goodsdetail+'>'+
 	            		'<label for="'+item.goodsid+'checkbox" onclick="checkedgoods(\''+item.goodsid+'\');"></label>'+
 	             '</div>'+
 	         '</li>');
 			if(item.pricesunit2==null||item.pricesunit2==''||item.pricesunit2==undefined){
 				$("#"+item.goodsid+"radio2").parent().css("display","none");
 			}
     });
 	$(".regular-radio").change(function(){ 
		var t = $(this).parent().parent().next().find('input[name*=danpin]');
		t.toggle();
		t.prev().toggle();
		t.next().toggle();
		var t2 = $(this).parent().parent().next().find('input[name*=taozhuan]');
		t2.toggle();
		t2.prev().toggle();
		t2.next().toggle();
	})
}
//收藏商品
function checkedgoods(goodsid){
	var url = 'CollectAction.do?method=';
	if($("#"+goodsid+"checkbox").is(':checked')){
		url +='delAllByGoodsid';
	}else{
		url +='insAll';
	}
	var json = '[{"collectgoods":"' + goodsid + 
		'","collectcustomer":"' + customer.customerid + '"}]';
	$.ajax({
		url : url,
		data : {
			json : json
		},
		success : function(resp) {
			var respText = eval('('+resp+')'); 
			if(respText.success == false) 
				alert(respText.msg);
			else {
				$(".cd-popup").addClass("is-visible");	//弹出窗口
				setTimeout(function () {  
					$(".cd-popup").removeClass("is-visible");	//一秒钟后关闭弹窗
			    }, 1000);
			}
		},
		error : function(resp) {
			alert('网络出现问题，请稍后再试');
		}
	});
}
function getcurrennumdanpin(dishesid){
	//订单
	if(window.localStorage.getItem("sdishes")==null){
		return 0;
	}else{
		var orderdetnum = 0;
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
		$.each(sdishes, function(i, item) {
			if(item.goodsid==dishesid
					&&item.goodsdetail=="danpin"){
				orderdetnum = item.orderdetnum;
				return false;
			}
		});
		return orderdetnum;
	}
}
function getcurrennumtaozhuan(dishesid){
	//订单
	if(window.localStorage.getItem("sdishes")==null){
		return 0;
	}else{
		var orderdetnum = 0;
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
		$.each(sdishes, function(i, item) {
			if(item.goodsid==dishesid
					&&item.goodsdetail=="taozhuan"){
				orderdetnum = item.orderdetnum;
				return false;
			}
		});
		return orderdetnum;
	}
}
function addnum(obj,pricesprice,goodsname,pricesunit,goodsunits,goodscode,goodsclassname,goodscompany,companyshop,companydetail){
	//总价
	var tmoney = parseFloat(window.localStorage.getItem("totalmoney"));
	var newtmoney = (tmoney+pricesprice).toFixed(2);
	window.localStorage.setItem("totalmoney",newtmoney);
	//数量
	var numt = $(obj).prev(); 
	var num = parseInt(numt.val());
	numt.val(num+1);
	//订单
	if(window.localStorage.getItem("sdishes")==null){
		window.localStorage.setItem("sdishes","[]");
	}
	var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
	if(num == 0){
		$("#totalnum").show();
		//新增订单
		var mdishes = new Object();
		mdishes.goodsid = $(obj).parent().attr('name');
		mdishes.goodsdetail = $(obj).prev().attr('name');
		mdishes.goodscompany = goodscompany;
		mdishes.companyshop = companyshop;
		mdishes.companydetail = companydetail;
		mdishes.goodsclassname = goodsclassname;
		mdishes.goodscode = goodscode;
		mdishes.pricesprice = pricesprice;
		mdishes.pricesunit = pricesunit;
		mdishes.goodsname = goodsname;
		mdishes.goodsunits = goodsunits;
		mdishes.orderdetnum = num + 1;
		
		sdishes.push(mdishes);
		//种类数
		var tnum = parseInt(window.localStorage.getItem("totalnum"));
		window.localStorage.setItem("totalnum",tnum+1);
	}else{
		//修改订单
		$.each(sdishes, function(i, item) {
			if(item.goodsid==$(obj).parent().attr('name')
					&&item.goodsdetail==$(obj).prev().attr('name')){
				item.orderdetnum = item.orderdetnum + 1;
				return false;
			}
		});
	}
	window.localStorage.setItem("sdishes",JSON.stringify(sdishes));
	$("#totalnum").text(window.localStorage.getItem("totalnum"));
}
function subnum(obj,pricesprice){
	var numt = $(obj).next(); 
	var num = parseInt(numt.val());
	if(num > 0){
		//总价
		var tmoney = parseFloat(window.localStorage.getItem("totalmoney"));
		var newtmoney = (tmoney-pricesprice).toFixed(2);
		window.localStorage.setItem("totalmoney",newtmoney);
		//数量
		numt.val(num-1);
		//订单
		var sdishes = JSON.parse(window.localStorage.getItem("sdishes"));
		if(num == 1){
			//删除订单
			$.each(sdishes,function(i,item){
				if(item.goodsid==$(obj).parent().attr('name')){
					sdishes.splice(i,1);
					return false;
				};
			});
			//种类数
			var tnum = parseInt(window.localStorage.getItem("totalnum"));
			window.localStorage.setItem("totalnum",tnum-1);
			if(tnum == 1)
			$("#totalnum").hide();
		}else{
			//修改订单
			$.each(sdishes, function(i, item) {
				if(item.goodsid==$(obj).parent().attr('name')
						&&item.goodsdetail==$(obj).next().attr('name')){
					item.orderdetnum = item.orderdetnum - 1;
					return false;
				}
			});
		}
		window.localStorage.setItem("sdishes",JSON.stringify(sdishes));
	}
	$("#totalnum").text(window.localStorage.getItem("totalnum"));
}

function nextpage(){
	if(window.localStorage.getItem("totalnum")==0)
		window.location.href = "cartnothing.html";
	else
		window.location.href = "cart.jsp";
}

function testJsonp(data){
	//console.log(data);
}

function callbackparam(data){
	//console.log(data);
}

function successCB(r, cb) {
	cb && cb(r);
}

function getJson(url, param, sCallback, fCallBack) {
	try
	{
		$.ajax({
			url: url,
			data: param,
			dataType:"json",
			success: function(r) {
				successCB(r, sCallback);
				successCB(r, fCallBack);
			},
			error:function(r) {
				var resp = eval(r); 
				alert(resp.msg);
			}
		});
	}
	catch (ex)
	{
		alert(ex);
	}
}
</script> 
</body>
</html>
