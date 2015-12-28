Ext.onReady(function() {
	var Payclassify = "在线支付记录";
	var Paytitle = "当前位置:业务管理》" + Payclassify;
	var Payaction = "PayAction.do";
	var Payfields = ['payid'
	        			    ,'payorderm' 
	        			    ,'paydetail' 
	        			    ,'paystatue' 
	        			    ,'updtime' 
	        			    ,'updor' 
	        			      ];// 全部字段
	var Paykeycolumn = [ 'payid' ];// 主键
	var Paystore = dataStore(Payfields, basePath + Payaction + "?method=selQuery");// 定义Paystore
	var Paysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Paycm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Paysm, {// 改
			header : '在线支付ID',
			dataIndex : 'payid',
			hidden : true
		}
		, {
			header : '订单ID',
			dataIndex : 'payorderm',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '备注',
			dataIndex : 'paydetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'paystatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '修改时间',
			dataIndex : 'updtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var PaydataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'PaydataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Paypayid',
				name : 'payid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '订单ID',
				id : 'Paypayorderm',
				name : 'payorderm',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '备注',
				id : 'Paypaydetail',
				name : 'paydetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '状态',
				id : 'Paypaystatue',
				name : 'paystatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '修改时间',
				id : 'Payupdtime',
				name : 'updtime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '修改人',
				id : 'Payupdor',
				name : 'updor',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Paybbar = pagesizebar(Paystore);//定义分页
	var Paygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Paytitle,
		store : Paystore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Paycm,
		sm : Paysm,
		bbar : Paybbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					PaydataForm.form.reset();
					createWindow(basePath + Payaction + "?method=insAll", "新增", PaydataForm, Paystore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Paygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Payaction + "?method=updAll", "修改", PaydataForm, Paystore);
					PaydataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Paygrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Payaction + "?method=delAll",selections,Paystore,Paykeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Payaction + "?method=impAll","导入",Paystore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Payaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Paygrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Paygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Paykeycolumn.length;i++){
						fid += selections[0].data[Paykeycolumn[i]] + ","
					}
					commonAttach(fid, Payclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Payaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Payaction).getValue()) {
								Paystore.load();
							} else {
								Paystore.load({
									params : {
										query : Ext.getCmp("query"+Payaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Paygrid.region = 'center';
	Paystore.load();//加载数据
	Paystore.on("beforeload",function(){ 
		Paystore.baseParams = {
				query : Ext.getCmp("query"+Payaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Paygrid ]
	});
})
