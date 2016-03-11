<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>

<body>
<div class="reg-wrapper">
	<ul>
    	<li><span>收件人</span> <input name="" type="text" placeholder="请输入收件人姓名"></li>
        <li><span>手机号</span> <input name="" type="text" placeholder="请输入手机号"></li>
    </ul>
</div>
<div class="reg-wrapper reg-dianpu-info">
	<ul>
    	<li><span>所在区域</span> <select name=""><option>黄浦区</option><option>静安区</option></select><i></i></li>
        <li><span>所在街道</span> <select name=""><option>XX乡</option></select><i></i></li>
        <li><span>详细地址</span> <input name="" type="text" placeholder="请输入详细地址"></li>
    </ul>
</div>
<div class="reg-wrapper">
	<ul>
    	<li><label><input name="" type="checkbox" value="" class="set-default"> <span>设置默认</span></label></li>
    </ul>
</div>
<div class="add-address-btn">
	<a href="#">删除</a>
    <a href="#">保存</a>
</div>
</body>
</html>