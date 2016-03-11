<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.elegant-aero {
	margin-left: 10px auto;
	margin-right: auto;
	background: #D2E9FF;
	padding: 20px 20px 20px 20px;
	font: 12px Arial, Helvetica, sans-serif;
	color: #666;
}

.elegant-aero h1 {
	font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
	padding: 10px 10px 10px 20px;
	display: block;
	background: #C0E1FF;
	border-bottom: 1px solid #B8DDFF;
	margin: -20px -20px 15px;
}

.elegant-aero h1>span {
	display: block;
	font-size: 11px;
}

.elegant-aero label>span {
	float: left;
	margin-top: 10px;
	color: #5E5E5E;
	text-align: right;
	padding-right: 15px;
	font-weight: bold;
}

.elegant-aero label {
	margin: 0px 0px 5px;
}


.elegant-aero input[type="text"]{
	color: #888;
	padding: 0px 0px 0px 5px;
	border: 1px solid #C5E2FF;
	background: #FBFBFB;
	outline: 0;
	-webkit-box-shadow: inset 0px 1px 6px #ECF3F5;
	box-shadow: inset 0px 1px 6px #ECF3F5;
	font: 200 12px/25px Arial, Helvetica, sans-serif;
	height: 30px;
	line-height: 15px;
	margin: 2px 6px 16px 0px;
}


.elegant-aero .button {
	padding: 10px 30px 10px 30px;
	background: #66C1E4;
	border: none;
	color: #FFF;
	box-shadow: 1px 1px 1px #4C6E91;
	-webkit-box-shadow: 1px 1px 1px #4C6E91;
	-moz-box-shadow: 1px 1px 1px #4C6E91;
	text-shadow: 1px 1px 1px #5079A3;
}

.elegant-aero .button:hover {
	background: #3EB1DD;
}

.elegant-aero p{

}

</style>
<title>Insert title here</title>
</head>
<body>
	<div class="elegant-aero">
		<form action="" method="post" class="STYLE-NAME">
			<h1>商品价格设置</h1>
			<p><span>餐饮客户价格 :</span></p> 
			<p><label>
				<span>关系等级3 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级2 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级1 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
			</label> </p>
			<p><span> 高级客户价格 :</span></p>
			<p><label>
				<span>关系等级3 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级2 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级1 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
			</label> </p>
			<p><span> 组织单位客户价格 :</span></p>
			<p><label>
				<span>关系等级3 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级2 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
				<span>关系等级1 :</span><input size="5" id="name" type="text" name="name" placeholder="账号" />
			</label> </p>
			<label> <span>&nbsp;</span> <input type="button"
				class="button" value="提交" />
			</label>
		</form>
	</div>
</body>
</html>