Ext.onReady(function() {
	var System_tempruleclassify = "模板样式";
	var System_tempruletitle = "当前位置:系统管理》" + System_tempruleclassify;
	var System_tempruleaction = "System_tempruleAction.do";
	var System_temprulefields = ['id'
	        			    ,'tempcode' 
	        			    ,'tempname' 
	        			    ,'sheetno' 
	        			    ,'sheetname' 
	        			    ,'tablecode' 
	        			    ,'headno' 
	        			    ,'headcode' 
	        			    ,'headname' 
	        			    ,'headnameas' 
	        			    ,'fieldname' 
	        			    ,'headlevel' 
	        			    ,'startrow' 
	        			    ,'endrow' 
	        			    ,'startcol' 
	        			    ,'endcol' 
	        			    ,'detail' 
	        			      ];// 全部字段
	var System_temprulekeycolumn = [ 'id' ];// 主键
	var System_temprulestore = dataStore(System_temprulefields, basePath + System_tempruleaction + "?method=selQuery");// 定义System_temprulestore
	var System_temprulesm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_temprulecm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_temprulesm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '模板代码',
			dataIndex : 'tempcode',
			sortable : true
		}
		, {
			header : '模板名称',
			dataIndex : 'tempname',
			sortable : true
		}
		, {
			header : '页签序号',
			dataIndex : 'sheetno',
			sortable : true
		}
		, {
			header : '页签名称',
			dataIndex : 'sheetname',
			sortable : true
		}
		, {
			header : '录入表代码',
			dataIndex : 'tablecode',
			sortable : true
		}
		, {
			header : '表头序号',
			dataIndex : 'headno',
			sortable : true
		}
		, {
			header : '表头代码',
			dataIndex : 'headcode',
			sortable : true
		}
		, {
			header : '表头名称',
			dataIndex : 'headname',
			sortable : true
		}
		, {
			header : '表头别名',
			dataIndex : 'headnameas',
			sortable : true
		}
		, {
			header : '表头对应录入表字段名',
			dataIndex : 'fieldname',
			sortable : true
		}
		, {
			header : '表头级次',
			dataIndex : 'headlevel',
			sortable : true
		}
		, {
			header : '表头开始行',
			dataIndex : 'startrow',
			sortable : true
		}
		, {
			header : '表头结束行',
			dataIndex : 'endrow',
			sortable : true
		}
		, {
			header : '表头开始列',
			dataIndex : 'startcol',
			sortable : true
		}
		, {
			header : '表头结束列',
			dataIndex : 'endcol',
			sortable : true
		}
		, {
			header : '备注',
			dataIndex : 'detail',
			sortable : true
		}
		]
	});
	var System_tempruledataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'System_tempruledataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'System_tempruleid',
				name : 'id',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '模板代码',
				id : 'System_tempruletempcode',
				name : 'tempcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '模板名称',
				id : 'System_tempruletempname',
				name : 'tempname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '页签序号',
				id : 'System_temprulesheetno',
				name : 'sheetno',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '页签名称',
				id : 'System_temprulesheetname',
				name : 'sheetname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '录入表代码',
				id : 'System_tempruletablecode',
				name : 'tablecode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头序号',
				id : 'System_tempruleheadno',
				name : 'headno',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头代码',
				id : 'System_tempruleheadcode',
				name : 'headcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头名称',
				id : 'System_tempruleheadname',
				name : 'headname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头别名',
				id : 'System_tempruleheadnameas',
				name : 'headnameas',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头对应录入表字段名',
				id : 'System_temprulefieldname',
				name : 'fieldname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头级次',
				id : 'System_tempruleheadlevel',
				name : 'headlevel',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头开始行',
				id : 'System_temprulestartrow',
				name : 'startrow',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头结束行',
				id : 'System_tempruleendrow',
				name : 'endrow',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头开始列',
				id : 'System_temprulestartcol',
				name : 'startcol',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '表头结束列',
				id : 'System_tempruleendcol',
				name : 'endcol',
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
				id : 'System_tempruledetail',
				name : 'detail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	var System_temprulebbar = pagesizebar(System_temprulestore);//定义分页
	var System_temprulegrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_tempruletitle,
		store : System_temprulestore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_temprulecm,
		sm : System_temprulesm,
		bbar : System_temprulebbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					System_tempruledataForm.form.reset();
					createWindow(basePath + System_tempruleaction + "?method=insAll", "新增", System_tempruledataForm, System_temprulestore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = System_temprulegrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + System_tempruleaction + "?method=updAll", "修改", System_tempruledataForm, System_temprulestore);
					System_tempruledataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_temprulegrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + System_tempruleaction + "?method=delAll",selections,System_temprulestore,System_temprulekeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + System_tempruleaction + "?method=impAll","导入",System_temprulestore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + System_tempruleaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(System_temprulegrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = System_temprulegrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<System_temprulekeycolumn.length;i++){
						fid += selections[0].data[System_temprulekeycolumn[i]] + ","
					}
					commonAttach(fid, System_tempruleclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_tempruleaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_tempruleaction).getValue()) {
								System_temprulestore.load();
							} else {
								System_temprulestore.load({
									params : {
										query : Ext.getCmp("query"+System_tempruleaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_temprulegrid.region = 'center';
	System_temprulestore.load();//加载数据
	System_temprulestore.on("beforeload",function(){ 
		System_temprulestore.baseParams = {
				query : Ext.getCmp("query"+System_tempruleaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_temprulegrid ]
	});
})
