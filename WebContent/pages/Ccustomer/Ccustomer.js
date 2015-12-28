Ext.onReady(function() {
	var Ccustomerclassify = "经销商和客户";
	var Ccustomertitle = "当前位置:业务管理》" + Ccustomerclassify;
	var Ccustomeraction = "CcustomerAction.do";
	var Ccustomerfields = ['ccustomerid'
	        			    ,'ccustomercompany' 
	        			    ,'ccustomercustomer' 
	        			    ,'ccustomerdetail' 
	        			    ,'createtime' 
	        			    ,'creator' 
	        			      ];// 全部字段
	var Ccustomerkeycolumn = [ 'ccustomerid' ];// 主键
	var Ccustomerstore = dataStore(Ccustomerfields, basePath + Ccustomeraction + "?method=selQuery");// 定义Ccustomerstore
	var Ccustomersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Ccustomercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Ccustomersm, {// 改
			header : '经销商和客户ID',
			dataIndex : 'ccustomerid',
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'ccustomercompany',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '客户ID',
			dataIndex : 'ccustomercustomer',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'ccustomerdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '创建时间',
			dataIndex : 'createtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '创建人',
			dataIndex : 'creator',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var CcustomerdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'CcustomerdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Ccustomerccustomerid',
				name : 'ccustomerid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商ID',
				id : 'Ccustomerccustomercompany',
				name : 'ccustomercompany',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户ID',
				id : 'Ccustomerccustomercustomer',
				name : 'ccustomercustomer',
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
				id : 'Ccustomerccustomerdetail',
				name : 'ccustomerdetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '创建时间',
				id : 'Ccustomercreatetime',
				name : 'createtime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '创建人',
				id : 'Ccustomercreator',
				name : 'creator',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Ccustomerbbar = pagesizebar(Ccustomerstore);//定义分页
	var Ccustomergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Ccustomertitle,
		store : Ccustomerstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Ccustomercm,
		sm : Ccustomersm,
		bbar : Ccustomerbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					CcustomerdataForm.form.reset();
					createWindow(basePath + Ccustomeraction + "?method=insAll", "新增", CcustomerdataForm, Ccustomerstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Ccustomergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Ccustomeraction + "?method=updAll", "修改", CcustomerdataForm, Ccustomerstore);
					CcustomerdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Ccustomergrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Ccustomeraction + "?method=delAll",selections,Ccustomerstore,Ccustomerkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Ccustomeraction + "?method=impAll","导入",Ccustomerstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Ccustomeraction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Ccustomergrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Ccustomergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Ccustomerkeycolumn.length;i++){
						fid += selections[0].data[Ccustomerkeycolumn[i]] + ","
					}
					commonAttach(fid, Ccustomerclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Ccustomeraction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Ccustomeraction).getValue()) {
								Ccustomerstore.load();
							} else {
								Ccustomerstore.load({
									params : {
										query : Ext.getCmp("query"+Ccustomeraction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Ccustomergrid.region = 'center';
	Ccustomerstore.load();//加载数据
	Ccustomerstore.on("beforeload",function(){ 
		Ccustomerstore.baseParams = {
				query : Ext.getCmp("query"+Ccustomeraction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Ccustomergrid ]
	});
})
