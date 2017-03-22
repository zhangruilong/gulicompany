
$(function(){
	$.ajax({
		url: 'CPWarrantoutAction.do?method=outPrint',
		type: 'post',
		data: {
			ordermid: ordermid,
			idwarrantouts: idwarrantouts
		},
		success: function(resp){
			var data = eval('('+resp+')');
			if(data.root && data.root.length >0){
				var lhr = '';
				$.each(data.root,function(i,item){
					if(lhr.indexOf(item.warrantoutwho)==-1){
						lhr += item.warrantoutwho+',';
					}
					$('.print_tab').append('<tr>'+
						'<td align="right">'+(i+1)+'</td>'+
						'<td align="right">'+item.warrantoutgcode+'</td>'+
						'<td align="left">'+item.warrantoutgname+'</td>'+
						'<td align="left">'+item.warrantoutgunits+'</td>'+
						'<td align="left">'+item.warrantoutgunit+'</td>'+
						'<td align="right">'+item.warrantoutnum+'</td>'+
						'<td align="right">'+item.storehousename+'</td>'+
					'</tr>');
				});
				$('#pDate').text(Ext.util.Format.date(new Date(),'Y-m-d'));	//打印日期
				$('#pOdmCode').text(ordermcode);	//订单编号
				$('#pCustomer').text(customershop);		//购买单位
				$('#pWho').text(lhr.substr(0,lhr.length-1));		//领货人
				var printitems = data.root[0].warrantoutprint;
				if(typeof(printitems)=='undefined' || !printitems){
					printitems = '0';
				}
				$('#p-PrintTimes').text('打印次数：'+printitems);		//打印次数
			}
		},
		error: function(resp){
			
		}
	});
});