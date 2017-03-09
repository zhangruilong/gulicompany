
//一键出库
function warrantoutPlacing(url, selections, store, fields) {
	Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要一键出库？', function(btn, text) {
		if (btn == 'yes') {
			var ids = '[';
			var msg = '';	//数量小于出库数量的提示信息
			var staMsg = '';	//状态不对的商品的提示信息
			for (var i = 0; i < selections.length; i++) {
				if(selections[i].data['warrantoutstatue'] == '发货请求'){
					var goodsnum = selections[i].data['goodsnumnum'];
					//如果商品在主仓库中的数量信息 未知 或 数量小于出库数量
					if(Ext.isEmpty(goodsnum) || selections[i].data['warrantoutnum'] > goodsnum){
						msg += selections[i].data['warrantoutgname']+',';
					}
					ids += "{";
					for (var j = 0; j < fields.length; j++){
						ids += "'"+fields[j]+"':'" + selections[i].data[fields[j]] + "',";
					}
					ids = ids.substr(0, ids.length - 1) + "},";
				} else {
					staMsg += selections[i].data['warrantoutgname']+',';
				}
			};
			if(msg.length >0 && staMsg.length >0){
				msg = '商品:'+msg+'在主仓库中的数量不足<br/> 商品:'+staMsg+'状态不对,无法出库。';
			} else if(staMsg.length >0 && msg.length ==0){
				msg = '商品:'+staMsg+'状态不对,无法出库。';
			} else if(msg.length >0 && staMsg.length ==0){
				msg = '商品:'+msg+'在主仓库中的数量不足。';
			}
			if(msg.length >0){
				Ext.Msg.confirm('请确认', msg+'<br/>是否出库？',function(btn, text){
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : url,
							method : 'POST',
							params : {
								json : ids.substr(0, ids.length - 1) + "]",
								warrantoutwho: warrantoutwho
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
			} else {
				Ext.Ajax.request({
					url : url,
					method : 'POST',
					params : {
						json : ids.substr(0, ids.length - 1) + "]"
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
		}
	});
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
		title : '请选择要出库的商品',
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
							Ext.getCmp('Warrantoutviewwarrantoutgoods').setValue(selectRows[0].get("goodsid"));
							Ext.getCmp('Warrantoutviewwarrantoutgcode').setValue(selectRows[0].get("goodscode"));
							Ext.getCmp('Warrantoutviewwarrantoutgname').setValue(selectRows[0].get("goodsname"));
							Ext.getCmp('Warrantoutviewwarrantoutgunits').setValue(selectRows[0].get("goodsunits"));
							Ext.getCmp('Warrantoutviewwarrantoutgtype').setValue("商品");
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
function outQueryWindow(title,_form,store) {
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
//新增出库台账记录的窗口
function addWarrantoutWindow(url,title,_form,store) {
	
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
										Ext.Msg.alert('提示', action.result.msg,function(){
											dataWindow.hide();
											store.reload();
										});
									} else if(action.result.code==401){
										//没有查询到对应的库存总账信息时
										Ext.Msg.confirm('提示','未找到相关的“库存总账”信息,请问要出库么?',function(buttonId,value,opt){
											if(buttonId=='yes'){
												_form.form.submit({
													url : url,
													waitTitle : '提示',
													params : {//改
														json : json,
														type : '直接出库'
													},
													success : function(form, action) {
														Ext.Msg.alert('提示', action.result.msg,function(){
															dataWindow.hide();
															store.reload();
														});
													},failure : function(form, action) {
														Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
													},
													waitMsg : '正在处理数据,请稍候...'
												});
											}
										});
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

//修改出库台账记录的窗口
function editWarrantoutWindow(url,title,_form,store) {
	
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
							if(action.result.code==202){
								Ext.Msg.alert('提示', action.result.msg,function(){
									dataWindow.hide();
									store.reload();
								});
							} else if(action.result.code==401){
								//没有查询到对应的库存总账信息时
								Ext.Msg.confirm('提示',action.result.msg,function(buttonId,value,opt){
									if(buttonId=='yes'){
										_form.form.submit({
											url : url,
											waitTitle : '提示',
											params : {//改
												json : json,
												type : '直接出库'
											},
											success : function(form, action) {
												Ext.Msg.alert('提示', action.result.msg,function(){
													dataWindow.hide();
													store.reload();
												});
											},failure : function(form, action) {
												Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
											},
											waitMsg : '正在处理数据,请稍候...'
										});
									}
								});
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
//删除出库台账
function delWarrantout(url, selections, store) {
	Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要删除当前选择的条目？', function(btn, text) {
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