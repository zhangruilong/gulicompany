<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>谷粒网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/layout.css" type="text/css" rel="stylesheet">
<link href="css/dig.css" type="text/css" rel="stylesheet">
<link href="../ExtJS/resources/css/ext-all.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../ExtJS/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../ExtJS/ext-all.js"></script>
<script type="text/javascript" src="../ExtJS/ext-lang-zh_CN.js" charset="UTF-8"></script>
<style type="text/css">
.reg-wrapper li span{
	width: 30%;
}
.float_select_quyu{
	position: fixed ;
	left:0%;
	top:50%;
	z-index: 999;
	height: 30px;
	line-height: 30px;
	width: 100px;
	border: solid 1px ;
	display: block;
	background-color: #ccc;
}
#cusCityDiv ul{
	padding: 0px;
	margin: 0px;
	
}
#cusCityDiv ul li{
	list-style: none;
	
}
</style>
</head>

<body>
<form action="">
	<div class="reg-wrapper reg-dianpu-info">
		<ul>
			<li>
			<!-- <span>所在城市</span> <select id="city" name="customercity" style="width:25%;margin-left: 39%;">
    		<option value="">请选择城市</option>
    		<c:forEach items="${requestScope.cityList }" var="c">
				<option>${c.cityname }</option>
			</c:forEach></select><i></i> -->
			<span>所在城市</span>
			<input onclick="input_sele_city()" id="customercity" name="customercity" type="text" style="width:220px; background-color:#fff;"/> <i></i>
         <div id="divList" style="display: none; position: absolute ;width: 30%;left:60%; top:10%; border: 1px solid black; overflow: hidden; position: absolute; background-color:#FFFFFF; "> 
                <table width="100%" border="0" cellpadding="0" cellspacing="0"> 
                    <tr> 
                        <td> 
                               <div id="cusCityDiv"  style="overflow: auto; padding-left:0; width: 100%; background-color: red; ">
                               	<ul>
                               		<c:forEach items="${requestScope.cityList }" var="cyty">
                               			<li>${cyty.cityname }</li>
                               		</c:forEach>
                               	</ul>
                               </div> 
                        </td> 
                    </tr> 
                </table>   
          </div> 
			</li>
        <li><span>所在区域</span> <select  id="xian" name="customerxian" style="width:24%;margin-left: 40%;display:inline-block;color: black;">
        	<option value="">请选择地区</option>
			</select><i></i>
			<span>所在城市</span>
			<input onclick="input_sele_city()" id="customercity" name="customercity" type="text" style="width:220px; background-color:#fff;"/> <i></i>
         <div id="divList" style="display: none; position: absolute ;width: 30%;left:60%; top:10%; border: 1px solid black; overflow: hidden; position: absolute; background-color:#FFFFFF; "> 
                <table width="100%" border="0" cellpadding="0" cellspacing="0"> 
                    <tr> 
                        <td> 
                               <div id="contentDiv"  style="overflow: auto; padding-left:0; width: 100%; background-color: red; ">
                               	<ul>
                               	</ul>
                               </div> 
                        </td> 
                    </tr> 
                </table>   
          </div> 
		</li>
			<!-- <li><span>所在城市</span> 
			<span style="position:absolute;overflow:hidden;margin-left: 170px;"> 
			<select id="city" style="width:160%;">
				<option></option>
				<c:forEach items="${requestScope.cityList }" var="cyty">
					<option>${cyty.cityname }</option>
				</c:forEach>
			</select>
			</span><i></i>
			<span style="position:absolute;display: block;">
				<input onclick="input_sele_city()" id="customercity" name="customercity" type="text" 
				placeholder="请输入城市" style="width:118px;margin-left: 228%;">
			</span>
			</li> -->
			<!-- <li><span>服务区域</span> 
			<span style="position:absolute;overflow:hidden;margin-left: 170px;"> 
			<select id="xian" style="width:170%;">
				<option></option>
			</select>
			<span class="float_select_quyu">
				<a>海盐县</a>
			</span>
			</span><i></i> 
			<span style="position:absolute;display: block;">
				<input id="customerxian" name="customerxian" type="text"  id="customerxian"
				placeholder="请输入地区" style="width:118px;margin-left: 228%;">
			</span>
			</li> -->
			<li><span>店铺名称</span> <input name="customershop" type="text" id="customershop"
				placeholder="请输入店铺名称"></li>
			<li><span>店铺地址</span> <input name="customeraddress" type="text" id="customeraddress"
				placeholder="请输入店铺地址"></li>
			<li><span>联系人</span><input name="customername" type="text" id="customername"
				placeholder="请输入名字"></li>
			<li><span>联系电话</span><input name="customerphone" type="text" id="customerphone"
				placeholder="请输入联系人号码"></li>
		</ul>
	</div>
	<div class="confirm-reg">
		<a onclick="reg()" class="confirm-reg-btn">确认注册</a> <a href="agreement.jsp">确认注册即同意《谷粒网客户注册服务协议》</a>
	</div>
	<!--弹框-->
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<div class="cd-buttons">
        	<h1>谷粒网提示</h1>
			<p class="meg">是否现在登录?</p>
            <a class="cd-popup-close">确定</a>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">
var customercity = '';
	$(function(){
		//防止openid 为 字符串"null"
		if(!window.localStorage.getItem("openid") || window.localStorage.getItem("openid")== 'null' || window.localStorage.getItem("openid") == ''){
			//$(".meg").text("请先清理缓存");
			//$(".cd-popup").addClass("is-visible");
			window.location.href = "index.jsp";
			return;
		}
		$("#customercity").change(function(){
			customercity = $("#customercity").val();
			//document.getElementById('customercity').value=document.getElementById('city').options[document.getElementById('city').selectedIndex].value;
			//$("#city").val("");
			Ext.Ajax.request({
				url : "querycity.action",
				method : "post",
				params : {
					"cityname" : customercity
				},
				success : function(resp,opts) {
					var result = resp.responseText;
					var $result = Ext.util.JSON.decode(result);
					$("#xian").empty();			//清空select组件内的原始值
					var $option = $('<option value="">请选择地区</option>');
					$("#xian").append($option);
					for ( var i = 0; i < $result.length; i++) {
						var city = $result[i];
						$option = $("<option>"+city.cityname+"</option>");
						$("#xian").append($option);
					}
				},
				failure : function(resp,opts) {
					Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
				}
			});
		});   
		
		/* $("#xian").change(function(){
			var xian = $("#xian").val();
			$("#xian").val("");
			$("#customerxian").val(xian);
		}); */
		
		$(".cd-popup").on("click",function(event){		//绑定点击事件
				$(this).removeClass("is-visible");	//移除'is-visible' class
		});
	})
	function input_sele_city(){
		$("#city").trigger("change");
	}
	function reg(){
		var count = 0;
		var alt;
		$("input").each(function(i,item){
			if($(item).val() == null || $(item).val() == ''){
				count++;
				alt=$(item).attr("placeholder");
				return false;
			}
		});
		if(count > 0){
			$(".meg").text(alt);		//修改弹窗信息
			$(".cd-popup").addClass("is-visible");	//弹出窗口
			return;
		}
		if($("[name='customercity']").val() == null || $("[name='customercity']").val() == ''){
			$(".meg").text("请选择城市");		//修改弹窗信息
			$(".cd-popup").addClass("is-visible");	//弹出窗口
			return;
		}
		if($("[name='customerxian']").val() == null || $("[name='customerxian']").val() == ''){
			$(".meg").text("请选择地区");		//修改弹窗信息
			$(".cd-popup").addClass("is-visible");	//弹出窗口
			return;
		}
		$.getJSON("reg.action",{
			"openid":window.localStorage.getItem("openid"),
			"customercity":$("[name='customercity']").val(),
			"customerphone":$("#customerphone").val(),
			"customerxian":$("[name='customerxian']").val(),
			"customershop":$("#customershop").val(),
			"customername":$("#customername").val(),
			"customeraddress":$("#customeraddress").val()
		},function(data){
			window.localStorage.setItem("customer",JSON.stringify(data));
			$(".meg").text("注册成功！");
			$(".cd-buttons a").attr("href","index.jsp");
			$(".cd-popup").addClass("is-visible");	//弹出窗口
		});
	}
////////////////////////////////////////////这是下拉列表菜单的js///////////////////////////////////////////////////////
var oRegion = document.getElementById("customercity");     //需要弹出下拉列表的文本框 
       var oDivList = document.getElementById("divList");         //要弹出的下拉列表
       var contentD = document.getElementById("contentDiv") ;
       //var oClose = document.getElementById("tdClose");   //关闭div的单元格，也可使用按钮实现 
       //var QueryCode ="COPY_aibsm.enums.sm.receive.support_row" ;
       var bNoAdjusted = true;  //控制div是否已经显示的变量
       var html = "" ; 
       var all_html ="" ;
       var colOptions = "" ;
       /* $(document).ready(function(){
              //oRegion.style.background="url(/bomc3/jx/boms/busBackup/select2.jpg)  right -3px no-repeat";
              //oRegion.style.backgroundColor="#fff" ;
              getJsonListFromCode(QueryCode,function(data){
                      if(data!=null&&data!=""){           // 存在查询结果 ;
                           $.each(data,function(i,e){
                                   all_html +="<li style='text-align:left; padding-left:5px;'>"+e.VALUE+"</li>" ;
                           }) ;
                     }
              },'') ;
       }) ; */
     //关闭下拉框
       $(document).click(function (e) {          
          var target_id = $(e.target).attr('id') ;             // 获取点击dom元素id ;
          if(target_id!=oRegion.id){
                  oDivList.style.display = "none";//隐藏div，实现关闭下拉框的效果 ;
                 //oRegion.style.background="url(/bomc3/jx/boms/busBackup/select2.jpg)  right -3px no-repeat";
                  //oRegion.style.backgroundColor="#fff" ;
          }
       }) ;
       $(function(){
    	   $("#contentDiv ul li").click(function(){
    		   $(oRegion).val($(this).text());
    		   customercity = $(this).text();
    		   Ext.Ajax.request({
   				url : "querycity.action",
   				method : "post",
   				params : {
   					"cityname" : customercity
   				},
   				success : function(resp,opts) {
   					var result = resp.responseText;
   					var $result = Ext.util.JSON.decode(result);
   					$("#xian").empty();			//清空select组件内的原始值
   					var $option = $('<option value="">请选择地区</option>');
   					$("#xian").append($option);
   					for ( var i = 0; i < $result.length; i++) {
   						var city = $result[i];
   						$option = $("<option>"+city.cityname+"</option>");
   						$("#xian").append($option);
   					}
   				},
   				failure : function(resp,opts) {
   					Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
   				}
   			});
    	   });
       });
       //设置下列选择项的一些事件 
       function setEvent(colOptions){
              for (var i=0; i<colOptions.length; i++) 
              { 
                  colOptions[i].onclick = function()//为列表项添加单击事件 
                  { 
                      oRegion.value = this.innerText;     //显示选择的文本；
                      oRegion.style.backgroundColor="#219DEF" ;
                      oDivList.style.display = "none";  
                  }; 
                  colOptions[i].onmouseover = function()//为列表项添加鼠标移动事件 
                  { 
                      this.style.backgroundColor = "#219DEF"; 
                  }; 
                  colOptions[i].onmouseout = function()  //为列表项添加鼠标移走事件 
                  { 
                      this.style.backgroundColor = ""; 
                  }; 
              } 
       }
       //文本获得焦点时的事件 (弹出下拉框)
       oRegion.onfocus = function() { 
           //oRegion.style.background="url(/bomc3/jx/boms/busBackup/select.jpg)  right -3px no-repeat";
           oRegion.style.backgroundColor="white" ;
           oDivList.style.display = "block";
           if (bNoAdjusted) //控制div是否已经显示的变量 
           { 
               bNoAdjusted = false; 
               //设置下拉列表的宽度和位置 
               oDivList.style.width = this.offsetWidth - 4; 
               oDivList.style.posTop = oRegion.offsetTop + oRegion.offsetHeight + 1;          // 设定高度
               oDivList.style.posLeft = oRegion.offsetLeft +1 ;               // 设定与左边的位置;
           } 
       }; 
      
       // 文本内容改变时监听事件 ;
       /* oRegion.onpropertychange = function(){
               contentD.innerHTML ="" ; // 情况div中所有li元素;
               html ="" ;
               InitializeDIV( oRegion.value) ;
       }
       function InitializeDIV(value){
              var sql ="" ;
              if(value!=""){
                     html+= "<ul><li style='text-align:left; padding-left:3px;'>按"+'"'+"<font style='color :red;'>"+value+"</font>"+'"'+"检索:</li>";
                     sql += 'value='+value ;
              }else{
                html+= "<ul><li style='text-align:left; padding-left:3px;'>请输入检索条件:"+"</li>";
                     sql ="" ;
              }
              getJsonListFromCodeSync(QueryCode,function(data){
                      if(data!=null&&data!=""){           // 存在查询结果 ;
                           $.each(data,function(i,e){
                                   html+="<li style='text-align:left; padding-left:3px;'>"+e.VALUE+"</li>" ;
                           }) ;
                      }else{         // 没有查询结果;
                                  html ="" ;
                                  html+= "<ul><li style='text-align:left; padding-left:3px;'>无法匹配:"+'"'+"<font style='color :red;'>"+value+"</font>"+'"'+"</li>";
                                  html += all_html ;
                      }
                      html+="</ul>" ;
              },sql) ;
              contentD.innerHTML = html ;
              colOptions = $("#contentDiv li") ; //所有列表元素
              setEvent(colOptions) ;
       } */
</script>
</body>
</html>
