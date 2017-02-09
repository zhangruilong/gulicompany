//商品的窗口
function goodsWindow(){
	var Goodstitle = "商品";
	var Goodsaction = "CPGoodsAction.do";
	var Goodsfields = ['goodsid'
       			    ,'goodscompany' 
       			    ,'goodscode' 
       			    ,'goodsname' 
       			    ,'goodsdetail' 
       			    ,'goodsunits' 
       			    ,'goodsclass' 
       			    ,'goodsimage' 
       			    ,'goodsstatue' 
       			    ,'createtime' 
       			    ,'updtime' 
       			    ,'creator' 
       			    ,'updor' 
       			    ,'goodsbrand' 
       			    ,'goodstype' 
       			    ,'goodsorder' 
       			    ,'goodsweight' 
       			      ];// 全部字段
	var Goodskeycolumn = [ 'goodsid' ];// 主键
	var Goodsstore = dataStore(Goodsfields, basePath + Goodsaction + "?method=selQuery&wheresql=goodscompany='"+comid+"'");// 定义Goodsstore
	var Goodsbbar = pagesizebar(Goodsstore);
	var Goodsgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Goodsstore,
		bbar : Goodsbbar,
		selModel: {
	        type: 'checkboxmodel'
	    },
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60,
		},
		 {
			header : '商品编号',
			dataIndex : 'goodscode',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '商品名称',
			dataIndex : 'goodsname',
			sortable : true,  
			width : 137,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '规格',
			dataIndex : 'goodsunits',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '图片路径',
			dataIndex : 'goodsimage',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '品牌',
			dataIndex : 'goodsbrand',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '重量',
			dataIndex : 'goodsweight',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'query',
			name : 'query',
			emptyText : '模糊匹配',
			enableKeyEvents : true,
			width : 100,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query").getValue()) {
							Goodsstore.load();
						} else {
							Goodsstore.load({
								params : {
									query : Ext.getCmp("query").getValue()
								}
							});
						}
					}
				}
			}
		}]
	});
	Goodsstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : Goodstitle,
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		//closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : Goodsgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Goodsgrid.getSelection();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Bkgoodsbkgoodscode').setValue(comcode+selectRows[0].get("goodscode"));
							Ext.getCmp('Bkgoodsbkgoodsname').setValue(selectRows[0].get("goodsname"));
							Ext.getCmp('Bkgoodsbkgoodsunits').setValue(selectRows[0].get("goodsunits"));
							Ext.getCmp('Bkgoodsbkgoodsimage').setValue(selectRows[0].get("goodsimage"));
							Ext.getCmp('Bkgoodsbkgoodsbrand').setValue(selectRows[0].get("goodsbrand"));
							selectgridWindow.close();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							selectgridWindow.close();
						}
					}]
	});
	selectgridWindow.show();
}
//新增年货商品的窗口
function addBKgoodsWindow(url,title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式,当设置为fit时 scrollable 属性的设置会失效
		width : Ext.os.deviceType === 'Phone' ? '100%' : 750, // 窗口宽度
		modal : true,
		closeAction: 'hide',
//		scrollable : true,		//滚动条
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '选择商品',
					iconCls : 'select',
					handler : function() {
						goodsWindow();
					}
				},
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var scope = "";
							Ext.getCmp("scopeCheckbox1").getValue()+Ext.getCmp("scopeCheckbox2").getValue()+Ext.getCmp("scopeCheckbox3").getValue();
							if(Ext.getCmp("scopeCheckbox1").getValue()==true){
								scope += "1";
							}
							if(Ext.getCmp("scopeCheckbox2").getValue()==true){
								scope += "2";
							}
							if(Ext.getCmp("scopeCheckbox3").getValue()==true){
								scope += "3";
							}
							Ext.getCmp("Bkgoodsbkgoodsscope").setValue(scope);		//设置客户范围
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									json : json
								},
								success : function(form, action) {
									Ext.Msg.alert('提示', action.result.msg,function(){
										if(action.result.code==202){
											dataWindow.hide();
											store.reload();
										}
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
	dataWindow.removeAll(false);	//这一行和下面一行如果没有则第二次选择修改时窗口中的选择框没有选项。
	dataWindow.items.add(_form);
	dataWindow.show();
}
//修改年货商品的窗口
function editBKgoodsWindow(url,title,_form,store,bkgoodsscope) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式,当设置为fit时 scrollable 属性的设置会失效
		width : Ext.os.deviceType === 'Phone' ? '100%' : 750, // 窗口宽度
		modal : true,
		closeAction: 'hide',
//		scrollable : true,		//滚动条
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
		{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var scope = "";
							Ext.getCmp("scopeCheckbox1").getValue()+Ext.getCmp("scopeCheckbox2").getValue()+Ext.getCmp("scopeCheckbox3").getValue();
							if(Ext.getCmp("scopeCheckbox1").getValue()==true){
								scope += "1";
							}
							if(Ext.getCmp("scopeCheckbox2").getValue()==true){
								scope += "2";
							}
							if(Ext.getCmp("scopeCheckbox3").getValue()==true){
								scope += "3";
							}
							Ext.getCmp("Bkgoodsbkgoodsscope").setValue(scope);		//设置客户范围
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									json : json
								},
								success : function(form, action) {
									Ext.Msg.alert('提示', action.result.msg,function(){
										dataWindow.hide();
										store.reload();
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
	dataWindow.removeAll(false);	//这一行和下面一行如果没有则第二次选择修改时窗口中的选择框没有选项。
	dataWindow.items.add(_form);
	dataWindow.show();
	if(bkgoodsscope){
		if(bkgoodsscope.indexOf('1')!=-1){
			Ext.getCmp("scopeCheckbox1").setValue(true);
		}
		if(bkgoodsscope.indexOf('2')!=-1){
			Ext.getCmp("scopeCheckbox2").setValue(true);
		}
		if(bkgoodsscope.indexOf('3')!=-1){
			Ext.getCmp("scopeCheckbox3").setValue(true);
		}
	}
}