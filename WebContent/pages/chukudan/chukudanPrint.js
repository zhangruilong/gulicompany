
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
				$.each(data.root,function(i,item){
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
				$('#pWho').text('');		//领货人
				
			}
		},
		error: function(resp){
			
		}
	});
});