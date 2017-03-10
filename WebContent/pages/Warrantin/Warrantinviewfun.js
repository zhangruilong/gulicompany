//标品的窗口
function scantWindow(){
	var Scanttitle = "标品库";
	var Scantaction = "CPScantAction.do";
	var Scantfields = ['scantid'
       			    ,'scantcode' 
    			    ,'scantname' 
    			    ,'scantdetail' 
    			    ,'scantunits' 
    			    ,'scantclass' 
    			    ,'scantimage' 
    			    ,'scantbrand' 
    			    ,'scanttype' 
    			    ,'scantstatue' 
    			    ,'goodsclassid' 
    			    ,'goodsclasscode' 
    			    ,'goodsclassname' 
    			    ,'goodsclassparent' 
    			    ,'goodsclassdetail' 
    			    ,'goodsclassstatue' 
    			    ,'goodsclasscity' 
    			    ,'goodsclassorder' 
    			    ,'goodsclasscompany' 
	        			      ];// 全部字段
	var Scantkeycolumn = [ 'scantid' ];// 主键
	var Scantstore = dataStore(Scantfields, basePath + Scantaction + "?method=selQuery");// 定义Scantstore
	var Scantbbar = pagesizebar(Scantstore);
	var Scantgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Scantstore,
		bbar : Scantbbar,
		selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		}
		, {// 改
			header : '标品ID',
			dataIndex : 'scantid',
			hidden : true,
			sortable : true, 
		}
		, {
			header : '编码',
			dataIndex : 'scantcode',
			sortable : true, 
		}
		, {
			header : '名称',
			dataIndex : 'scantname',
			sortable : true, 
			width : 137,
		}
		, {
			header : '规格',
			dataIndex : 'scantunits',
			sortable : true, 
		}
		, {
			header : '小类ID',
			dataIndex : 'scantclass',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '小类',
			dataIndex : 'goodsclassname',
			sortable : true,  
		}
		, {
			header : '图片路径',
			dataIndex : 'scantimage',
			sortable : true,
			hidden : true, 
		}
		, {
			header : '品牌',
			dataIndex : 'scantbrand',
			sortable : true, 
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
							Scantstore.load();
						} else {
							Scantstore.load({
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
	Scantstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : Scanttitle,
		layout : 'fit', // 设置窗口布局模式
		width : 720, // 窗口宽度
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
		pageX : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : Scantgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Scantgrid.getSelection();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Goodsgoodscode').setValue(comcode+selectRows[0].get("scantcode"));
							Ext.getCmp('Goodsgoodsname').setValue(selectRows[0].get("scantname"));
							Ext.getCmp('Goodsgoodsunits').setValue(selectRows[0].get("scantunits"));
							Ext.getCmp('Goodsgoodsimage').setValue(nullStr(selectRows[0].get("scantimage")));
							Ext.getCmp('Goodsgoodsbrand').setValue(nullStr(selectRows[0].get("scantbrand")));
							Ext.getCmp('Goodsgoodsclass').setValue(nullStr(selectRows[0].get("scantclass")));
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
//新增库存总账记录的窗口
function addGoodsnumWindow(url,title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式,当设置为fit时 scrollable 属性的设置会失效
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		scrollable : true,		//滚动条
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
					text : '查找标品库',
					iconCls : 'select',
					handler : function() {
						scantWindow();
					}
				},
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							$.ajax({
								url: 'CPGoodsAction.do?method=selAll',
								type: 'post',
								data: {
									wheresql: "goodscode='"+Ext.getCmp('Goodsgoodscode').getValue()+
											"' and goodsunits='"+Ext.getCmp('Goodsgoodsunits').getValue()+
											"' and goodsname='"+Ext.getCmp('Goodsgoodsname').getValue()+
											"' and goodscompany='"+comid+"'"
								},
								success: function(resp){
									var data = eval('('+resp+')');
									if(data.root.length==0){
										var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
										_form.form.submit({
											url : url,
											waitTitle : '提示',
											params : {//改
												json : json,
											},
											success : function(form, action) {
												Ext.Msg.alert('提示', action.result.msg,function(){
													if(action.result.code==400){
														
													} else {
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
										Ext.Msg.confirm('请确认','商品已存在,是否要新增入库台账?',function(btn, text){
											if (btn == 'yes') {
												Ext.getCmp('newWarrantinviewwarrantingoods').setValue(data.root[0].goodsid);
												var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
												_form.form.submit({
													url : basePath + 'CPWarrantinAction.do?method=addWarrantin',
													waitTitle : '提示',
													params : {//改
														json : json
													},
													success : function(form, action) {
														if(action.result.code==202){
															Ext.Msg.alert('提示', '提交成功，数据已过账！',function(){
																dataWindow.hide();
																store.reload();
															});
														} else {
															Ext.Msg.alert('提示', action.result.msg);
														}
													},
													failure : function(form, action) {
														Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
													},
													waitMsg : '正在处理数据,请稍候...'
												});
											}
										});
									}
								},
								error: function(resp){
									
								}
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

//商品的窗口
function goodsWindow(){
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
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
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
			width : 158,
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
		title : '请选择要入库的商品',
		layout : 'fit', // 设置窗口布局模式
		width : 682, // 窗口宽度
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
		pageX : document.body.clientWidth / 2 - 682 / 2, // 页面定位X坐标
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
							Ext.getCmp('Warrantinviewgoodscode').setValue(selectRows[0].get("goodscode"));
							Ext.getCmp('Warrantinviewgoodsname').setValue(selectRows[0].get("goodsname"));
							Ext.getCmp('Warrantinviewgoodsunits').setValue(selectRows[0].get("goodsunits"));
							Ext.getCmp('Warrantinviewwarrantingoods').setValue(selectRows[0].get("goodsid"));
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
//筛选
function inQueryWindow(title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
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
						queryjson = "[" + Ext.encode(_form.form.getValues(false)) + "]";
//						json = json.replace(/""/g,null);
						store.load({
							params : {
								json : queryjson
							}
						});
						dataWindow.hide();
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
//新增入库台账记录的窗口
function addWarrantinWindow(url,title,_form,store) {
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
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									json : json
								},
								success : function(form, action) {
									if(action.result.code==202){
										Ext.Msg.alert('提示', '提交成功，数据已过账！',function(){
											dataWindow.hide();
											store.reload();
										});
									} else {
										Ext.Msg.alert('提示', action.result.msg);
									}
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
//修改入库台账记录的窗口
function editWarrantinWindow(url,title,_form,store,bkgoodsscope) {
	
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
}
//回滚入库台账
function warrantinRollBACK(url, selections, store) {
	Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要回滚当前选择的条目？', function(btn, text) {
		if (btn == 'yes') {
			var json = "["+Ext.encode(selections[0].getData()) + "]";
			Ext.Ajax.request({
				url : url,
				method : 'POST',
				params : {
					json : json
				},
				success : function(response) {
					var resp = Ext.decode(response.responseText); 
					Ext.Msg.alert('提示', resp.msg, function(){
						store.reload();
					});
				},
				failure : function(response) {
					Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
				}
			});
		}
	});
}










