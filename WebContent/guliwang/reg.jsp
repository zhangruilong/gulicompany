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
<script type="text/javascript">
	$(function(){
		//防止openid 为 字符串"null"
		if(!window.localStorage.getItem("openid") || window.localStorage.getItem("openid")== 'null' || window.localStorage.getItem("openid") == ''){
			//$(".meg").text("请先清理缓存");
			//$(".cd-popup").addClass("is-visible");
			window.location.href = "index.jsp";
			return;
		}
		$("#city").change(function(){
			var customercity = $("#city").val();
			document.getElementById('customercity').value=document.getElementById('city').options[document.getElementById('city').selectedIndex].value;
			$("#city").val("");
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
					var $option = $("<option></option>");
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
		
		$("#xian").change(function(){
			var xian = $("#xian").val();
			$("#xian").val("");
			$("#customerxian").val(xian);
		});
		
		$(".cd-popup").on("click",function(event){		//绑定点击事件
				$(this).removeClass("is-visible");	//移除'is-visible' class
		});
	})
	function reg(){
		var customerpsw = $("[name='customerpsw']").val();
		var repwd = $("[name='repwd']").val();
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
		if(repwd != customerpsw){
			$(".meg").text("两次输入的密码不相等");		//修改弹窗信息
			$(".cd-popup").addClass("is-visible");	//弹出窗口
			return;
		}
		$.getJSON("reg.action",{
			"openid":window.localStorage.getItem("openid"),
			"customercity":$("#customercity").val(),
			"customerphone":$("#customerphone").val(),
			"customerxian":$("#customerxian").val(),
			"customershop":$("#customershop").val(),
			"customername":$("#customername").val(),
			"customeraddress":$("#customeraddress").val()
		},function(data){window.localStorage.setItem("customer",JSON.stringify(data));alert("注册成功");window.location.href = "index.jsp";});
	}
	
</script>
</head>

<body>
	<div class="reg-wrapper reg-dianpu-info">
		<ul>
			<li><span>所在城市</span> 
			<span style="position:absolute;overflow:hidden;margin-left: 170px;"> 
			<select id="city" style="width:160%;">
				<option></option>
				<c:forEach items="${requestScope.cityList }" var="cyty">
					<option>${cyty.cityname }</option>
				</c:forEach>
			</select>
			</span><i></i> 
			<span style="position:absolute;display: block;">
				<input id="customercity" name="customercity" type="text"  id="customercity"
				placeholder="请输入城市" style="width:118px;margin-left: 200px;">
			</span>
			</li>
			<li><span>服务区域</span> 
			<span style="position:absolute;overflow:hidden;margin-left: 170px;"> 
			<select id="xian" style="width:160%;">
				<option></option>
			</select>
			</span><i></i> 
			<span style="position:absolute;display: block;">
				<input id="customerxian" name="customerxian" type="text"  id="customerxian"
				placeholder="请输入地区" style="width:118px;margin-left: 200px;">
			</span>
			</li>
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
</body>
</html>
