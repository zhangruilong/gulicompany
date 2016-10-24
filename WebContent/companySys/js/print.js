var currST = 0;									//当前叶签序号
var rowNum = 0;									//当前行序号
var columnNum = 0;								//当前列
var totalWeight = 0;							//总重量
var dObj = new Date();
var today = dObj.getFullYear()+"-"+(dObj.getMonth()+1)+"-"+dObj.getDate();	//当前日期
$(function(){
	$.ajax({
		url:"printInfo.action",
		type:"post",
		data:{
			"ordermid":ordermid
		},
		success : function(data){
			var tempList = data.tempList;					//模板
			var printinfo = data.printinfo;					//打印信息 (除订单商品外的打印信息)
			var orderdList = data.orderdList;				//订单商品列表
			var rowStyle = '';								//行样式
			var tdStrle1 = '';								//单元格样式1
			var tdStrle2 = '';								//单元格样式2
			
			if(data.message=='success'){
				var text = '';									//要输入的文本
				$.each(orderdList,function(i,item){
					if(typeof(item.orderdweight)!='undefined'){
						totalWeight += item.orderdweight*item.orderdnum;
					}
				});
				$('#con-title').text(tempList[0].tempname);
				$.each(tempList,function(i,item){
					//叶签信息
					if(currST != item.sheetno){					//如果是新的叶签
						currST = item.sheetno;					//叶签号
						rowNum = 0;								//行号变为0
						if(item.sheetname =='供应商信息'){
							$('#print-content').append(
								'<table width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse;"></table>');
						} else if(item.sheetname =='客户信息'){
							$('#print-content').append('<table width="100%" border="0" cellspacing="0" cellpadding="3"></table>');
						} else if(item.sheetname == '商品信息'){
							$('#print-content').append(
								'<table id="goods-tab" class="print_tab" width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;margin-top: 5px;"></table>');
						} else if(item.sheetname == '签收信息'){
							$('#print-content').append(
						'<table class="print_tab" id="signFo-tab" width="100%" border="1" cellspacing="0" cellpadding="3" style="background-color: #80ffff;border-collapse: collapse;padding-top: 20px;font-family: 黑体;"></table>');
						} else if(item.sheetname == '制表信息'){
							$('#print-content').append('<table cellpadding="7" style="width:100%; margin-left: 10px;padding-top: 5px;font-family: 黑体;">');
						}
					}
					
					//行信息
					if(rowNum != item.startrow && item.sheetname != '合计信息' 
						&& item.sheetname != '谷粒网标识' && item.sheetname != '留言信息'){						//如果是新的一行就添加一行
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
					} else if(item.headnameas=='订单日期'){
						text = today;											//订单日期
					} else if(item.headnameas=='优惠金额'){
						text = printinfo['ordermmoney']-printinfo['ordermrightmoney'];											//优惠金额
					} else if(item.headnameas=='大写合计'){
						text = chineseNumber(printinfo['ordermrightmoney']);											//优惠金额
					} else if(item.headnameas=='重量合计'){
						text = totalWeight;
					}
					if(item.sheetname == '供应商信息'){
						$('#print-content table:last tr:last').append(
								'<td style="text-align: center;font-family: 黑体;font-size: 12px;border-left: hidden;'+
								'border-top: hidden;border-right: hidden;">'+item.headnameas+'：</td>'
								+'<td style="font-size: 12px;font-family: 黑体;border-left: hidden;border-top: hidden;">'+text+'</td>');
					} else if(item.sheetname == '客户信息'){
						$('#print-content table:last tr:last').append('<td align="right" style="width: 75px;font-size: 12px;">'+
								item.headnameas+'：</td><td style="width: 171px;">'+text+'</td>');
					} else if(item.sheetname == '商品信息'){
						$('#print-content table:last tr:last').append(
								'<td style="font-family: 黑体;font-size: 12px;" name="'+item.fieldname+'">'+item.headnameas+'</td>');
					} else if(item.sheetname == '合计信息'){
						$('#print-content').append(
								'<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">'+item.headnameas+':</span>'+
								'<span style="font-family: 黑体;float: left;padding:3px 10px 0px 3px;">'+text+'</span>');
					} else if(item.sheetname == '签收信息'){
						$('#print-content table:last tr:last').append(
								'<td style="font-size: 12px;">'+item.headnameas+'</td>');
					} else if(item.sheetname == '制表信息'){
						$('#print-content table:last tr:last').append(
								'<td style="width: 24%;font-size: 12px;">'+item.headnameas+
								':<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></td>');
					} else if(item.sheetname == '留言信息' && text){
						$('#print-content').append(
								'<div style="font-size: 12px;line-height: 30px;margin-left: 17px;">'+item.headnameas+':'+text+'</div>');
					} else if(item.sheetname == '谷粒网标识'){
						$('#print-content').append('<div style="font-size: 12px;">'+item.headnameas+'</div>');
					}
					/*if(item.endcol-item.startcol>1){
						alert(item.endcol-item.startcol);
					}*/
					if(item.endcol-item.startcol>1){									//如果跳过了1列或多列
						$('#print-content table:last tr:last td:last').attr('colspan',item.endcol-item.startcol);
					}
				});
				$('#print-content table:first').css('border-bottom','solid 1px black');
				$.each(orderdList,function(i,item1){							//订单商品
					$('#goods-tab').append('<tr></tr>');
					
					$('#goods-tab tr:first td').each(function(j,item2){
						var goodsInfo = '';
						if($(item2).attr('name') && $(item2).attr('name')!='undefined'&& $(item2).attr('name')!='null'){
							goodsInfo = typeNullFoString(item1[$(item2).attr('name')]);
						} else {
							goodsInfo = i+1;
						}
						$('#goods-tab tr:last').append('<td align="right">'+goodsInfo+'</td>');
					});
				});
				$('#signFo-tab').append('<tr></tr>');
				$('#signFo-tab tr:first td').each(function(i,item){
					if($(item).text()=='支付方式'){
						$('#signFo-tab tr:last').append('<td>货到付款</td>');
					} else {
						$('#signFo-tab tr:last').append('<td></td>');
					}
				});
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