<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印</title>
<style type="text/css">
body{
	margin: 0px; 
 	padding: 0px;
 	font-size: 14px;
 	font-family: 黑体;
}
.print_tab tr td{
	text-align: center;
}
</style>
</head>

<body style="width:740px;height:400px;">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WindowPrint" 
name="WindowPrint" width="0"></OBJECT>

<script language="javascript" type="text/javascript">
function printpreview()
{
	$('[name="Btn_printPreviw"]').hide();
	$('#p-PrintTimes').hide();
	window.print();
	$.ajax({
		url:"CPOrdermAction.do?method=updatePrintCount",
		type:"post",
		data:{
			"ordermids":ordermids,
			"ordermprinttimess":ordermprinttimess
		},
		success:function(resp){
			var data = eval('('+resp+')');
			if(data.code!=202){
				alert('记录打印次数失败。');
			}
		},
		error:function(resp){
			alert('记录打印次数失败。');
		}
	});
}
</script>

<div id='print-content'>
	<div style="text-align: center;font-size: 33px;font-family: 黑体;" id="con-title"></div>
</div>
<div id='p-debug-text'></div>
<div style="float: left;">
<input type="button" name="Btn_printPreviw" value="打印" 
onclick="javascript:this.style.display='none';printpreview();" />
<span id="p-PrintTimes"></span>
</div>

<script type="text/javascript" src="zrlextpages/common/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/numTF.js"></script>
<script type="text/javascript" src="js/common2.js"></script>
<script type="text/javascript">
var ordermids = '${param.ordermids}';
var ordermprinttimess = "";
$('[name="Btn_printPreviw"]').hide();
</script>
<script type="text/javascript" src="js/print.js"></script>
</body>

</html>