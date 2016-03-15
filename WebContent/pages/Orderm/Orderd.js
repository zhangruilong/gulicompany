function selectOrderd(ordermid) {
	var Orderdclassify = "订单详细";
	var Orderdtitle = "当前位置:业务管理》" + Orderdclassify;
	var Orderdaction = "OrderdAction.do";
	var Orderdfields = ['orderdid'
	        			    ,'orderdorderm' 
	        			    ,'orderdcode' 
	        			    ,'orderdtype' 
	        			    ,'orderdname' 
	        			    ,'orderddetail' 
	        			    ,'orderdunits' 
	        			    ,'orderdprice' 
	        			    ,'orderdunit' 
	        			    ,'orderdclass' 
	        			    ,'orderdnum' 
	        			    ,'orderdmoney' 
	        			    ,'orderdrightmoney' 
	        			      ];// 全部字段
	var Orderdkeycolumn = [ 'orderdid' ];// 主键
	var Orderdstore = dataStore(Orderdfields, basePath + Orderdaction + "?method=selQuery");// 定义Orderdstore
	var Orderdsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Orderdcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Orderdsm, {// 改
			header : '订单详细ID',
			dataIndex : 'orderdid',
			hidden : true
		}
		, {
			header : '订单ID',
			dataIndex : 'orderdorderm',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '商品编码',
			dataIndex : 'orderdcode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '商品类型',
			dataIndex : 'orderdtype',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'orderdname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'orderddetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '规格',
			dataIndex : 'orderdunits',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '价格',
			dataIndex : 'orderdprice',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '单位',
			dataIndex : 'orderdunit',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '小类',
			dataIndex : 'orderdclass',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '数量',
			dataIndex : 'orderdnum',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '下单金额',
			dataIndex : 'orderdmoney',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '实际金额',
			dataIndex : 'orderdrightmoney',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var OrderddataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'OrderddataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Orderdorderdid',
				name : 'orderdid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '订单ID',
				id : 'Orderdorderdorderm',
				name : 'orderdorderm',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编码',
				id : 'Orderdorderdcode',
				name : 'orderdcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品类型',
				id : 'Orderdorderdtype',
				name : 'orderdtype',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '名称',
				id : 'Orderdorderdname',
				name : 'orderdname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Orderdorderddetail',
				name : 'orderddetail',
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '小类',
				id : 'Orderdorderdclass',
				name : 'orderdclass',
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
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
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Orderdbbar = pagesizebar(Orderdstore);//定义分页
	var Orderdgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Orderdstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Orderdcm,
		sm : Orderdsm,
		bbar : Orderdbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					OrderddataForm.form.reset();
					createWindow(basePath + Orderdaction + "?method=insAll", "新增", OrderddataForm, Orderdstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Orderdgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Orderdaction + "?method=updAll", "修改", OrderddataForm, Orderdstore);
					OrderddataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Orderdgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Orderdaction + "?method=delAll",selections,Orderdstore,Orderdkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Orderdaction + "?method=impAll","导入",Orderdstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Orderdaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Orderdgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Orderdgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Orderdkeycolumn.length;i++){
						fid += selections[0].data[Orderdkeycolumn[i]] + ","
					}
					commonAttach(fid, Orderdclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Orderdaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Orderdaction).getValue()) {
								Orderdstore.load();
							} else {
								Orderdstore.load({
									params : {
										query : Ext.getCmp("query"+Orderdaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Orderdgrid.region = 'center';
	Orderdstore.on("beforeload",function(){ 
		Orderdstore.baseParams = {
				wheresql : "orderdorderm='"+ordermid+"'"
		}; 
	});
	Orderdstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : Orderdtitle,
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : Orderdgrid
	});
	selectgridWindow.show();
}
