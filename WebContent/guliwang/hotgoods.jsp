<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<style type="text/css">

#menu{width:100%; overflow:hidden; margin:0 auto;border:1px solid #BF9660;}
#menu #nav {display:block;width:100%;padding:0;margin:0;list-style:none;}
#menu #nav li {float:left;width:33.3%;}
#menu #nav li a {display:block;line-height:27px;text-decoration:none;padding:0 0 0 5px; text-align:center; color:#333;}
#menu_con{ width:358px; height:135px; border-top:none}
.tag{ padding:10px; overflow:hidden;}
.selected{background:#C5A069; color:#fff;}
</style>
</head>
<body>
<div class="gl-box">
    <div class="wapper-nav"><a onclick='javascript:history.go(-1);' class='goback'></a>
	热销商品<a onclick="docart(this)" href="cart.jsp" class="gwc"><img src="images/gwc.png" ><em id="totalnum">0</em></a></div>
    <!--代码部分begin-->
<div id="menu">
<!--tag标题-->
    <ul id="nav">
        <li><a href="#" class="selected">今日热销商品</a></li>
        <li><a href="#" class="">本周热销</a></li>
        <li><a href="#" class="">本月热销</a></li>
    </ul>
<!--二级菜单-->
    <div id="menu_con">
        <div class="tag" style="display:block">
            这里是jQuery特效内容列表
         </div> 
        <div class="tag" style="display:none">
            这里是tab切换效果   
         </div> 
        <div class="tag"  style="display:none">
            这里是菜单导航效果
        </div> 
</div>
</div>
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
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
var xian = '${param.xian}';
$(function(){ 
	//购物车图标上的数量
	if(!window.localStorage.getItem("cartnum")){
		window.localStorage.setItem("cartnum",0);
	}else if(window.localStorage.getItem("cartnum")==0){
		$("#totalnum").hide();
		$("#totalnum").text(0);
	}else{
		$("#totalnum").text(window.localStorage.getItem("cartnum"));
	}
	//页面信息
	if(xian != ''){
		$.getJSON("maizengPage.action",{"givegoodcompany.city.cityname":xian},initMiaoshaPage);
	} else {
		$.getJSON("maizengPage.action",{"givegoodcompany.city.cityname":customer.customerxian},initMiaoshaPage);
	}
});
//初始化页面
function initMiaoshaPage(data){
	$(".home-hot-commodity").html("");
	$.each(data.giveList,function(i,item1){
		var liObj = '<li><a '+
		'onclick="judgePurchase(\''+
		item1.givegoodsid +'\',\''+
		item1.givegoodsdetail +'\',\''+
		item1.givegoodscompany +'\',\''+
		item1.givegoodcompany.companyphone +'\',\''+
		item1.givegoodcompany.companydetail +'\',\''+
		item1.givegoodsclass +'\',\''+
		item1.givegoodscode +'\',\''+
		item1.givegoodsprice +'\',\''+
		item1.givegoodsunit +'\',\''+
		item1.givegoodsname +'\',\''+
		item1.givegoodsimage +'\',\''+
		item1.givegoodsunits +'\',\''+
		item1.givegoodsnum+'\');" '+
		'> <span class="fl"> <img src="../'+item1.givegoodsimage+
         	'" alt="" onerror="javascript:this.src=\'images/default.jpg\'"/></span>'+
			'<h1>'+item1.givegoodsname+
				'<span>（'+item1.givegoodsunits+'）</span>'+
			'</h1> <span> <strong>￥'+item1.givegoodsprice+'/'+item1.givegoodsunit+
			'</strong> ';
		if(data.cusOrderdList != null && data.cusOrderdList.length != 0){
			var itemGoodsCount = 0;
			$.each(data.cusOrderdList,function(k,item3){
				if(item3.orderdcode == givegoodscode){
					itemGoodsCount += item3.orderdnum
				}
			});
			liObj += '<font>限购'+(item1.givegoodsnum - itemGoodsCount)+item1.givegoodsunit+'</font><br/>';
		} else {
			liObj += '<font>限购'+item1.givegoodsnum+item1.givegoodsunit+'</font><br/>';
		}
		
		$(".home-hot-commodity").append(liObj+'</span></a></li>');
	});
}
//到购物车页面
function docart(obj){
	if (window.localStorage.getItem("sdishes") == null || window.localStorage.getItem("sdishes") == "[]") {				//判断有没有购物车
		$(obj).attr("href","cartnothing.html");
	}
}
//tab标签特效
var tabs=function(){
    function tag(name,elem){
        return (elem||document).getElementsByTagName(name);
    }
    //获得相应ID的元素
    function id(name){
        return document.getElementById(name);
    }
    function first(elem){
        elem=elem.firstChild;
        return elem&&elem.nodeType==1? elem:next(elem);
    }
    function next(elem){
        do{
            elem=elem.nextSibling;  
        }while(
            elem&&elem.nodeType!=1  
        )
        return elem;
    }
    return {
        set:function(elemId,tabId){
            var elem=tag("li",id(elemId));
            var tabs=tag("div",id(tabId));
            var listNum=elem.length;
            var tabNum=tabs.length;
            for(var i=0;i<listNum;i++){
                    elem[i].onclick=(function(i){
                        return function(){
                            for(var j=0;j<tabNum;j++){
                                if(i==j){
                                    tabs[j].style.display="block";
                                    //alert(elem[j].firstChild);
                                    elem[j].firstChild.className="selected";
                                }
                                else{
                                    tabs[j].style.display="none";
                                    elem[j].firstChild.className="";
                                }
                            }
                        }
                    })(i)
            }
        }
    }
}();
tabs.set("nav","menu_con");//执行
</script>
</body>
</html>