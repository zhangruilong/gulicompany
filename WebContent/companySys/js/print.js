var currST = 0;									//当前叶签序号
var rowNum = 0;									//当前行序号
var columnNum = 0;								//当前列
var totalWeight = 0;							//总重量
var dObj = new Date();
var today = dObj.getFullYear()+"-"+(dObj.getMonth()+1)+"-"+dObj.getDate();	//当前日期
var blankNum = 0;
$(function(){
	$.ajax({
		url:"printInfo.action",
		type:"post",
		data:{
			"ordermids":ordermids
		},
		success : function(data){
			if(data.message=='success'){
				var tempList = data.tempList;					//模板
				var printinfo = data.printinfo[0];					//打印信息 (除订单商品外的打印信息)
				var orderdList = data.orderdList;				//订单商品列表
				var rowStyle = '';								//行样式
				var qianshouTDStrle = '';						//签收信息单元格样式
				var totalMon=0;									//总下单金额
				var totalRMon = 0;								//总实际金额
				var orderMsg = '';								//订单留言
				var ordermPrintTimes='';							//订单的打印次数
				
				$.each(data.printinfo,function(i,item){
					if(typeof(item.ordermprinttimes)=='undefined' || !item.ordermprinttimes){
						item.ordermprinttimes = '0';
					}
					ordermprinttimess += (parseInt(item.ordermprinttimes)+1)+",";
					printCount = parseInt(item.ordermprinttimes);
					ordermPrintTimes += '编号:'+item.ordermcode+'&nbsp;打印次数:'+item.ordermprinttimes+'。&nbsp;';
					totalMon += parseFloat(item.ordermmoney);
					totalRMon += parseFloat(item.ordermrightmoney);
					if(typeof(item.ordermdetail)!='undefined' && item.ordermdetail){
						orderMsg += item.ordermdetail+'; ';
					}
				});
				$('#p-PrintTimes').html(ordermPrintTimes);
				if(data.printinfo.length>1){							//得到合并后的订单总金额
					printinfo.ordermmoney = totalMon;
					printinfo.ordermrightmoney = totalRMon;
					if(orderMsg != ''){
						printinfo.ordermdetail = orderMsg;
					}
				}
				var text = '';									//要输入的文本
				$.each(orderdList,function(i,item){
					if(typeof(item.orderdweight)!='undefined' && item.orderdweight != 'undefined'){
						totalWeight += item.orderdweight*item.orderdnum;
					} else if(item.orderdweight == 'undefined'){
						item.orderdweight = 0;
					}
				});
				$('#con-title').text(tempList[0].tempname);
				$.each(tempList,function(i,item){
					//叶签信息
					if(currST != item.sheetno){					//如果是新的叶签
						currST = item.sheetno;					//叶签号
						rowNum = 0;								//行号变为0
						if(item.sheetno =='1'){//供应商信息
							$('#print-content').append(
								'<table width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse;border-bottom:dotted 1px black"></table>');
						} else if(item.sheetno =='2'){//客户信息
							$('#print-content').append('<table width="100%" border="0" cellspacing="0" cellpadding="3"></table>');
						} else if(item.sheetno == '3'){//商品信息
							$('#print-content').append(
								'<table id="goods-tab" class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;"></table>');
						} else if(item.sheetno == '5'){//签收信息
							qianshouTDStrle = typeNullFoString(item.detail);
							$('#print-content').append(
						'<table class="print_tab" id="signFo-tab" width="100%" border="1" cellspacing="0" cellpadding="3" style="background-color: #80ffff;border-collapse: collapse;padding-top: 20px;font-family: 黑体;"></table>');
						} else if(item.sheetno == '7'){//制表信息
							$('#print-content').append('<table cellpadding="7" style="width:100%; margin-left: 10px;padding-top: 5px;font-family: 黑体;">');
						}
					}
					
					//行信息
					if(rowNum != item.startrow && item.sheetno != '4' 
						&& item.sheetno != '99' && item.sheetno != '8' && item.sheetno != '9' && item.sheetno != '10'){	//如果是新的一行就添加一行
						rowNum = item.startrow;										//行号
						if(item.sheetname =='供应商信息'){
							rowStyle = 'height: 40px;';
						} else {
							rowStyle = '';
						}
						$('#print-content table:last').append('<tr style="'+rowStyle+'"></tr>');
					}
					
					//这里开始是列信息
					
					columnNum = item.startcol;									//当前列
					if(typeof(item.fieldname)!='undefined' && item.fieldname){
						text = typeNullFoString(printinfo[item.fieldname]);						//一般的
					} else if(item.headname=='打印日期'){
						text = today;											//打印日期
					} else if(item.headname=='优惠金额'){
						text = totalMon-totalRMon;											//优惠金额
					} else if(item.headname=='大写合计'){
						text = chineseNumber(totalRMon);											//优惠金额
					} else if(item.headname=='重量合计'){
						text = totalWeight;
					}
					if(item.sheetno == '1'){												//供应商信息
						$('#print-content table:last tr:last').append(
								'<td style="text-align: center;font-family: 黑体;font-size: 12px;border-left: hidden;'+
								'border-top: hidden;border-right: hidden;">'+item.headnameas+'：</td>'
								+'<td style="'+item.detail+'">'+text+'</td>');
					} else if(item.sheetno == '2'){											//客户信息
						$('#print-content table:last tr:last').append('<td align="right" style="width: 75px;font-size: 12px;">'+
								item.headnameas+'：</td><td style="'+item.detail+'">'+text+'</td>');
					} else if(item.sheetno == '3'){											//商品信息
						$('#print-content table:last tr:last').append(
								'<td style="font-family: 黑体;font-size: 12px;white-space: nowrap;" name="'
								+item.fieldname+'">'+item.headnameas+'<span hidden=true>'+typeNullFoString(item.detail)+'</span></td>');
					} else if(item.sheetno == '4'){											//合计信息
						$('#print-content').append(
								'<span style="'+item.detail+'">'+item.headnameas+':</span>'+
								'<span style="'+item.detail+'">'+text+'</span>');
					} else if(item.sheetno == '5'){											//签收信息
						$('#print-content table:last tr:last').append(
								'<td style="font-size: 12px;">'+item.headnameas+'</td>');
					} else if(item.sheetno == '6'){											//四联信息
						$('#print-content').append(
								'<span style="'+item.detail+'">'+item.headnameas+'</span>');
					} else if(item.sheetno == '7'){											//制表信息
						$('#print-content table:last tr:last').append(
								'<td style="'+item.detail+'">'+item.headnameas+
								':<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>');
					} else if(item.sheetno == '8'){											//留言信息
						$('#print-content').append(
								'<div style="'+item.detail+'">'+item.headnameas+':'+text+'</div>');
					} else if(item.sheetno == '9'){											//工商说明
						$('#print-content').append('<div style="'+item.detail+'">'+item.headnameas+'</div>');
					} else if(item.sheetno == '99'){										//谷粒网标识
						$('#print-content').append('<div style="'+item.detail+'">'+item.headnameas+'</div>');
					} else if(item.sheetno == '10'){										//是否需要空白行
						blankNum = parseInt(item.headnameas);
					}
					if(item.endcol-item.startcol>1){										//如果跳过了1列或多列
						$('#print-content table:last tr:last td:last').attr('colspan',item.endcol-item.startcol);
					}
					
				});
				//订单商品
				$.each(orderdList,function(i,item1){
					$('#goods-tab').append('<tr></tr>');
					$('#goods-tab tr:first td').each(function(j,item2){
						var goodsInfo = '';
						var curSty = $(item2).children('span').text();
						if($(item2).attr('name') && $(item2).attr('name')!='undefined'&& $(item2).attr('name')!='null'){
							goodsInfo = typeNullFoString(item1[$(item2).attr('name')]);
						} else {
							goodsInfo = $('#goods-tab tr').length-1;
						}
						$('#goods-tab tr:last').append('<td style="'+curSty+'">'+goodsInfo+'</td>');
					});
				});
				//如果需要空白行,且行数小于7就添加空白行
				if(blankNum != 0 && orderdList.length<blankNum){
					var tdList = $('#goods-tab tr:first td');		//td的集合
					for (var i = 0; i < blankNum-orderdList.length; i++) {
						$('#goods-tab').append('<tr></tr>');
						for (var j = 0; j < tdList.length; j++) {
							var curSty = $(tdList[j]).children('span').text();
							$('#goods-tab tr:last').append('<td style="'+curSty+'">&nbsp;</td>');
						}
					}
				}
				//签收信息
				$('#signFo-tab').append('<tr></tr>');
				$('#signFo-tab tr:first td').each(function(i,item){
					if($(item).text()=='支付方式'){
						$('#signFo-tab tr:last').append('<td style="'+qianshouTDStrle+'">货到付款</td>');
					} else {
						$('#signFo-tab tr:last').append('<td style="'+qianshouTDStrle+'"></td>');
					}
				});
				$('[name="Btn_printPreviw"]').show();
			} else {
				alert('没有查询到该订单。');
				window.close();
			}
		},
		error : function(data){
			alert("操作失败!");
		}
	});
});