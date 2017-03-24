
var Storehousefields = ['storehouseid'
        			    ,'storehousecode' 
        			    ,'storehousename' 
        			    ,'storehouseaddress' 
        			      ];// 全部字段
var Empfields = ['empid'
     			    ,'empcode' 
     			    ,'empdetail' 
     			      ];// 全部字段
var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"' and storehousestatue='启用'");// 定义Storehousestore
Storehousestore.load();
var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
Empstore.load();

var filWarrantbackviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
	id:'filWarrantbackviewdataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		columnWidth : 1,
		layout : 'form',
		hidden : true,
		items : [ {
			xtype : 'textfield',
			fieldLabel : '退货台账ID',
			id : 'filWarrantbackviewidwarrantback',
			name : 'idwarrantback',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		hidden : true,
		items : [ {
			xtype : 'textfield',
			fieldLabel : '商品',
			id : 'filWarrantbackviewwarrantbackgoods',
			name : 'warrantbackgoods',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '商品编号',
			id : 'filWarrantbackviewgoodscode',
			name : 'goodscode',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '商品名称',
			id : 'filWarrantbackviewgoodsname',
			name : 'goodsname',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '规格',
			id : 'filWarrantbackviewgoodsunits',
			name : 'goodsunits',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '仓库',
			id : 'filWarrantbackviewwarrantbackstore',
			name : 'warrantbackstore',			//小类名称
			//loadingText: 'loading...',			//正在加载时的显示
			//editable : false,						//是否可编辑
			emptyText : '请选择',
			store : Storehousestore,
			mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
											//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
			displayField : 'storehousename',		//显示的字段
			valueField : 'storehouseid',		//作为值的字段
			hiddenName : 'warrantbackstore',
			triggerAction : 'all',
			editable : false,
			maxLength : 100,
			anchor : '95%',
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '数量',
			id : 'filWarrantbackviewwarrantbacknum',
			name : 'warrantbacknum',
			maxLength : 100
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '退货人',
			id : 'filWarrantbackviewwarrantbackwho',
			name : 'warrantbackwho',			//小类名称
			//loadingText: 'loading...',			//正在加载时的显示
			//editable : false,						//是否可编辑
			emptyText : '请选择',
			store : Empstore,
			mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
											//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
			displayField : 'empcode',		//显示的字段
			valueField : 'empcode',		//作为值的字段
			hiddenName : 'warrantbackwho',
			triggerAction : 'all',
			editable : false,
			maxLength : 100,
			anchor : '95%',
		} ]
	}
	, {
		columnWidth : .9,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '退货单位',
			id : 'filWarrantbackviewwarrantbackdetail',
			name : 'warrantbackdetail',
			maxLength : 100,
			readOnly : true
		} ]
	}
	, {
		columnWidth : .1,
		layout : 'form',
		items : [ {
			xtype : 'button',
			iconCls : 'select',
			handler : function() {
				showCustomer();
			}
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '状态',
			id : 'filWarrantbackviewwarrantbackstatue',
			name : 'warrantbackstatue',			//小类名称
			//loadingText: 'loading...',			//正在加载时的显示
			//editable : false,						//是否可编辑
			emptyText : '请选择',
			store : statueStore,
			mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
											//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
			displayField : 'name',		//显示的字段
			valueField : 'name',		//作为值的字段
			hiddenName : 'warrantbackstatue',
			triggerAction : 'all',
			editable : false,
			maxLength : 100,
			anchor : '95%',
		} ]
	}
	]
});

var Ccustomerviewfields = ['ccustomerid'
	        			    ,'ccustomercompany' 
	        			    ,'ccustomerdetail' 
	        			    ,'createtime' 
	        			    ,'creator' 
	        			    ,'customerid' 
	        			    ,'customercode' 
	        			    ,'customername' 
	        			    ,'customerphone' 
	        			    ,'customerpsw' 
	        			    ,'customershop' 
	        			    ,'customercity' 
	        			    ,'customerxian' 
	        			    ,'customeraddress' 
	        			    ,'customertype' 
	        			    ,'customerlevel' 
	        			    ,'openid' 
	        			    ,'customerdetail' 
	        			    ,'customerstatue' 
	        			    ,'cuscreatetime' 
	        			    ,'updtime' 
	        			      ];// 全部字段
var Ccustomerviewkeycolumn = [ 'ccustomerid' ];// 主键
var Ccustomerviewstore = dataStore(Ccustomerviewfields, basePath + "CPCcustomerviewAction.do?method=queryCCustomerwiew&wheresql=ccustomercompany='"+comid+"'");// 定义Ccustomerviewstore
Ccustomerviewbbar = pagesizebar(Ccustomerviewstore);//定义分页
var Ccustomerviewgrid =  Ext.create('Ext.grid.Panel', {
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	store : Ccustomerviewstore,
	bbar : Ccustomerviewbbar,
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
	},
	{// 改
		header : '客户关系ID',
		dataIndex : 'ccustomerid',
		sortable : true,
		hidden : true
	}
	, {
		header : '客户编码',
		dataIndex : 'customercode',
		sortable : true,
		width:75,
	}
	, {
		header : '客户名称',
		dataIndex : 'customershop',
		sortable : true,
		width:150,
	}
	, {
		header : '地址',
		dataIndex : 'customeraddress',
		sortable : true,
		width:150,
	}
	, {
		header : '联系人',
		dataIndex : 'customername',
		sortable : true,
		width:72,
	}
	, {
		header : '联系电话',
		dataIndex : 'customerphone',
		sortable : true,
		width:109,
	}
	],
	tbar : [{
		xtype : 'textfield',
		id : 'queryCcustomerviewaction',
		name : 'query',
		emptyText : '模糊匹配',
		width : 100,
		enableKeyEvents : true,
		listeners : {
			specialkey : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					if ("" == Ext.getCmp("queryCcustomerviewaction").getValue()) {
						Ccustomerviewstore.load();
					} else {
						Ccustomerviewstore.load({
							params : {
								query : Ext.getCmp("queryCcustomerviewaction").getValue()
							}
						});
					}
				}
			}
		}
	}
	]
});
//客户列表
function showCustomer(){
	Ccustomerviewstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : '请选择客户',
		layout : 'fit', // 设置窗口布局模式
		width : 682, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 682 / 2, // 页面定位X坐标
		items : Ccustomerviewgrid, // 嵌入的面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Ccustomerviewgrid.getSelection();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Warrantbackviewwarrantbackdetail').setValue(selectRows[0].get("customershop"));
							selectgridWindow.hide();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							selectgridWindow.hide();
						}
					}]
	});
	selectgridWindow.show();
}

//回滚
function backRollBACK(url, selections, store) {
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
		title : '请选择要退货的商品',
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
							Ext.getCmp('Warrantbackviewgoodscode').setValue(selectRows[0].get("goodscode"));
							Ext.getCmp('Warrantbackviewgoodsname').setValue(selectRows[0].get("goodsname"));
							Ext.getCmp('Warrantbackviewgoodsunits').setValue(selectRows[0].get("goodsunits"));
							Ext.getCmp('Warrantbackviewwarrantbackgoods').setValue(selectRows[0].get("goodsid"));
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
function backQueryWindow(title,_form,store) {
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
//新增退货台账记录的窗口
function addWarrantbackWindow(url,title,_form,store) {
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
