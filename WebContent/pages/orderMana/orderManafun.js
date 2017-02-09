//生成订单商品窗口
function createOrderdWindow(url,title,_form,Orderdgrid,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'border', // 设置窗口布局模式
		//width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		width : '100%',
		height : '100%',
//		scrollable : 'y',	//滚动条
		modal : true, //是否掩盖后面的元素
		closeAction: 'hide', //点击关闭时采取的动作
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),			//显示时的动画
		//pageY : 0, // 页面定位Y坐标
		//pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : [_form,Orderdgrid] // 嵌入的表单面板
	});
	dataWindow.show();
}

//生成修改订单详情(订单商品)窗口
function createEditOrderdWindow(url,title,_form,orderdStore,ordermviewStore) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									orderdjson : json
									
								},
								success : function(form, action) {
									Ext.Msg.alert('提示', action.result.msg,function(){
										dataWindow.hide();
										orderdStore.reload();
										ordermviewStore.reload();
									});
								},
								failure : function(form, action) {
									Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
								},
								waitMsg : '正在处理数据,请稍候...'
							});
						} else {
					        Ext.Msg.alert('提示', '请正确填写表单!');
					    }
					}
				}, {
					text : '关闭',
					iconCls : 'close',
					handler : function() {
						dataWindow.hide();
					}
				}]
	});
	dataWindow.show();
}

/*   修改 orderd(订单商品) 的 form 表单  开始    */
var OrderddataForm = Ext.create('Ext.form.Panel', {
	id:'OrderddataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ 
	{
		layout : 'form',
		hidden : true,
		items : [ {						//订单商品id
			xtype : 'textfield',
			id : 'Orderdorderdid',
			name : 'orderdid',
		} ]
	}
	, {
		layout : 'form',
		hidden : true,
		items : [ {						//订单id
			xtype : 'textfield',
			id : 'Orderdorderdorderm',
			name : 'orderdorderm',
		} ]
	}
	, {
		hidden : true,
		items : [ {
			xtype : 'textfield',
			editable : false,
			fieldLabel : '订单详细ID',
			id : 'Orderdorderdid',
			name : 'orderdid'
		} ]
	}
	,{
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '商品编号',
			id : 'Orderdorderdcode',
			name : 'orderdcode',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '商品名称',
			id : 'Orderdorderdname',
			name : 'orderdname',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '规格',
			id : 'Orderdorderdunits',
			name : 'orderdunits',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '价格',
			id : 'Orderdorderdprice',
			name : 'orderdprice',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '单位',
			id : 'Orderdorderdunit',
			name : 'orderdunit',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '数量',
			id : 'Orderdorderdnum',
			name : 'orderdnum',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '下单金额',
			id : 'Orderdorderdmoney',
			name : 'orderdmoney',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '实际金额',
			id : 'Orderdorderdrightmoney',
			name : 'orderdrightmoney',
			maxLength : 100
		} ]
	}
	]
});
/*   修改 orderd(订单商品) 的 form 表单  结束    */