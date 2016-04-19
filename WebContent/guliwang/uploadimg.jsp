<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<style type="text/css">
#result{ width:auto; position: absolute; right:0; top:0}
#result img{ width:30px; height:30px; border-radius:50px}
input:focus{ outline:none}
.close{ position:absolute; display:none}
.reg-wrapper li i:after{ height:34px; line-height:34px;content: "\f3d3"; font-size:1.2em;float:right; margin-right:2%; color:#aaa}
.reg-wrapper li textarea{width:64%;float:right;  border:none; margin-right:6%; font-size:1.1em; padding-bottom:2%;  font-weight:normal}

#uploadImg{ width:69%; height:34px; float:left; position:relative; font-size:12px; overflow:hidden}
#uploadImg a{ display:block; text-align:right;  height:34px; line-height:34px; font-size:1.4em; color:#aaa}
#file_input{ width:auto; position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;}
</style>
</head>

<body>
<form action="System_attachAction.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="json" id="json" value="">
<input type="hidden" name="method" id="method" value="uploadImg">
<input type="hidden" name="other" id="other" value="getch">
<div class="reg-wrapper">
	<ul>
    	<li>
        	<span>店铺照片</span> 
        	<div id="uploadImg">
                <input type="file" id="file_input" name=""/>
                <a href="#" id="clo">点击上传</a> 
                <span id="result">
                  <img src="images/mendian.jpg" style="border-radius:50px;">
                </span> 
            </div><i></i>
        </li>
    </ul>
</div>

<div class="confirm-reg">
	<a onclick="uploadimg()" class="confirm-reg-btn">保存修改</a>
</div>
</form>
<script src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
var customer = JSON.parse(window.localStorage.getItem("customer"));
var result = document.getElementById("result");
var input = document.getElementById("file_input");
/*function alertMsg()
  {
  document.getElementById("result").innerHTML = "";
  document.getElementById("clo").innerHTML = "点击上传";
  }*/
if(typeof FileReader === 'undefined'){
	result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
	input.setAttribute('disabled','disabled');
}else{
	input.addEventListener('change',readFile,false);
}
			
function readFile(){
	var file = this.files[0];
	if(!/image\/\w+/.test(file.type)){
		alert("请确保文件为图像类型");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e){
		result.innerHTML = '<img src="'+this.result+'" alt=""/><div class="close" onclick="alertMsg()" style="display:none">×</div>'
		document.getElementById("clo").innerHTML = "";
	}
}
function uploadimg(){
	$("#json").val('[{"code":"bianma","detail":"","classify":"客户","fid":"'+customer.customerid+',"}]');
	document.forms[0].submit();
	/* var json = '[{"code":"bianma","detail":"","classify":"客户","fid":\"'+customer.customerid+'\"}]';
	$.getJSON("System_attachAction.do?other=getch&method=uploadImg",{json:json},function(){alert("ok")}); */
}
</script>
</body>
</html>