//筛选条件:客户 确定
function cusNameConditionConfirm(){
	Ext.getCmp("quCustextfield").setValue(quCus);
	$(".cusNames-popup").removeClass("is-visible");
}
//筛选条件:品牌 确定
function brandConditionConfirm(){
	Ext.getCmp("quBrandtextfield").setValue(quBrand);
	$(".brand-popup").removeClass("is-visible");
}
//筛选条件:业务员 确定
function empConditionConfirm(){
	Ext.getCmp("quEmptextfield").setValue(quEmp);
	$(".emp-popup").removeClass("is-visible");
}
//cusNames弹窗的取消
function hiddenCusShow(){
	$(".cusNames-popup").removeClass("is-visible");
	$(".brand-popup").removeClass("is-visible");
	$(".emp-popup").removeClass("is-visible");
}
//得到查询条件中的客户名称
function showCusNames(){
	$.ajax({
		url:"CPCustomerAction.do?method=queryOrderCusName",	
		type:"post",
		data:{
			"startDate":startDate,
			"endDate":endDate,
			"query":$('#queryShop').val()
		},
		success:function(resp){
			var data = eval('('+resp+')');
			if(data.msg =='操作成功' && data.root.length >0){
				$('.alert-cusNames-show').html('');
				
				$.each(data.root ,function(i,item){
					var shop = item.ordermcusshop;
					if(typeof(shop)=='undefined'){
						brand = '未填充';
					}
					if(quCus.indexOf(shop) == -1){
						$('.alert-cusNames-show').append('<span>'+shop+'</span>');
					} else {
						$('.alert-cusNames-show').append('<span class="alert-cusNames-selspan">'+shop+'</span>');
					}
				});
				$(".cusNames-popup").addClass("is-visible");		//显示弹窗
				$(".alert-cusNames-show span").click(function(){
					if($(this).attr('class')=='alert-cusNames-selspan'){
						$(this).removeClass('alert-cusNames-selspan');
						quCus.splice(quCus.indexOf($(this).text()),1);
					} else {
						$(this).addClass('alert-cusNames-selspan');
						quCus.push($(this).text());
					}
				});
			} else {
				Ext.Msg.alert('提示', '没有查询到可用的客户名称,请重新设置查询时间后重试。');
				
			}
		},
		error:function(resp){
			Ext.Msg.alert('提示', '操作失败!');
		}
	});
}

//得到查询条件中的品牌名称
function showBrand(){
	$.ajax({
		url:"CPOrderdAction.do?method=selectTimeOrderdGoodsBrand",	
		type:"post",
		data:{
			"startDate":startDate,
			"endDate":endDate
		},
		success:function(resp){
			var data = eval('('+resp+')');
			if(data.msg =='操作成功' && data.root.length >0){
				$('.alert-brand-show').html('');
				
				$.each(data.root ,function(i,item){
					var brand = item.orderdbrand;
					if(typeof(brand)=='undefined'){
						brand = '未填充';
					}
					if(quBrand.indexOf(brand) == -1){
						$('.alert-brand-show').append('<span>'+brand+'</span>');
					} else {
						$('.alert-brand-show').append('<span class="alert-brand-selspan">'+brand+'</span>');
					}
				});
				$(".brand-popup").addClass("is-visible");
				$(".alert-brand-show span").click(function(){
					if($(this).attr('class')=='alert-brand-selspan'){
						$(this).removeClass('alert-brand-selspan');
						quBrand.splice(quBrand.indexOf($(this).text()),1);
					} else {
						$(this).addClass('alert-brand-selspan');
						quBrand.push($(this).text());
					}
				});
			} else {
				Ext.Msg.alert('提示', '没有查询到可用的品牌名称,请重新设置查询时间后重试。');
			}
		},
		error:function(resp){
			Ext.Msg.alert('提示', '操作失败!');
		}
	});
}

//得到查询条件中的业务员
function showEmp(){
	$.ajax({
		url:"CPCustomerAction.do?method=selectTimeEmpName",	
		type:"post",
		data:{
			"startDate":startDate,
			"endDate":endDate
		},
		success:function(resp){
			var data = eval('('+resp+')');
			if(data.msg =='操作成功' && data.root.length >0){
				$('.alert-emp-show').html('');
				$.each(data.root ,function(i,item){
					var empName = item.createtime;
					if(typeof(empName)=='undefined'){
						empName = '未填充';
					}
					if(quEmp.indexOf(empName) == -1){
						$('.alert-emp-show').append('<span>'+empName+'</span>');
					} else {
						$('.alert-emp-show').append('<span class="alert-emp-selspan">'+empName+'</span>');
					}
				});
				$(".emp-popup").addClass("is-visible");
				$(".alert-emp-show span").click(function(){
					if($(this).attr('class')=='alert-emp-selspan'){
						$(this).removeClass('alert-emp-selspan');
						quEmp.splice(quEmp.indexOf($(this).text()),1);
					} else {
						$(this).addClass('alert-emp-selspan');
						quEmp.push($(this).text());
					}
				});
			} else {
				Ext.Msg.alert('提示', '没有查询到可用的业务员名称,请重新设置查询时间后重试。');
				Ext.getCmp("quEmptextfield").blur();
			}
		},
		error:function(resp){
			Ext.Msg.alert('提示', '操作失败!');
		}
	});
}
