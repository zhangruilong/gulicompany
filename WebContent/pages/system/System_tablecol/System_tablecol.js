Ext.onReady(function() {
	var System_tablecolclassify = "数据字典";
	var System_tablecoltitle = "当前位置:系统管理》" + System_tablecolclassify;
	var System_tablecolaction = "System_tablecolAction.do";
	var System_tablecolfields = ['id'
	        			    ,'tablecode' 
	        			    ,'tablename' 
	        			    ,'colno' 
	        			    ,'colcode' 
	        			    ,'colname' 
	        			    ,'coltype' 
	        			    ,'collength' 
	        			    ,'coldefault' 
	        			      ];// 全部字段
	var System_tablecolkeycolumn = [ 'id' ];// 主键
	var System_tablecolstore = dataStore(System_tablecolfields, basePath + System_tablecolaction + "?method=selQuery");// 定义System_tablecolstore
	var System_tablecolsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_tablecolcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_tablecolsm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '录入表代码',
			dataIndex : 'tablecode',
			sortable : true
		}
		, {
			header : '录入表名称',
			dataIndex : 'tablename',
			sortable : true
		}
		, {
			header : '字段序号',
			dataIndex : 'colno',
			sortable : true
		}
		, {
			header : '字段列名',
			dataIndex : 'colcode',
			sortable : true
		}
		, {
			header : '字段中文名',
			dataIndex : 'colname',
			sortable : true
		}
		, {
			header : '数据类型',
			dataIndex : 'coltype',
			sortable : true
		}
		, {
			header : '字段长度',
			dataIndex : 'collength',
			sortable : true
		}
		, {
			header : '字段默认值',
			dataIndex : 'coldefault',
			sortable : true
		}
		]
	});
	var System_tablecoldataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'System_tablecoldataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'System_tablecolid',
				name : 'id',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '录入表代码',
				id : 'System_tablecoltablecode',
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
				fieldLabel : '录入表名称',
				id : 'System_tablecoltablename',
				name : 'tablename',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '字段序号',
				id : 'System_tablecolcolno',
				name : 'colno',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '字段列名',
				id : 'System_tablecolcolcode',
				name : 'colcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '字段中文名',
				id : 'System_tablecolcolname',
				name : 'colname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数据类型',
				id : 'System_tablecolcoltype',
				name : 'coltype',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '字段长度',
				id : 'System_tablecolcollength',
				name : 'collength',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '字段默认值',
				id : 'System_tablecolcoldefault',
				name : 'coldefault',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	var System_tablecolbbar = pagesizebar(System_tablecolstore);//定义分页
	var System_tablecolgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_tablecoltitle,
		store : System_tablecolstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_tablecolcm,
		sm : System_tablecolsm,
		bbar : System_tablecolbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					System_tablecoldataForm.form.reset();
					createWindow(basePath + System_tablecolaction + "?method=insAll", "新增", System_tablecoldataForm, System_tablecolstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = System_tablecolgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + System_tablecolaction + "?method=updAll", "修改", System_tablecoldataForm, System_tablecolstore);
					System_tablecoldataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_tablecolgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + System_tablecolaction + "?method=delAll",selections,System_tablecolstore,System_tablecolkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + System_tablecolaction + "?method=impAll","导入",System_tablecolstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + System_tablecolaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(System_tablecolgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = System_tablecolgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<System_tablecolkeycolumn.length;i++){
						fid += selections[0].data[System_tablecolkeycolumn[i]] + ","
					}
					commonAttach(fid, System_tablecolclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_tablecolaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_tablecolaction).getValue()) {
								System_tablecolstore.load();
							} else {
								System_tablecolstore.load({
									params : {
										query : Ext.getCmp("query"+System_tablecolaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_tablecolgrid.region = 'center';
	System_tablecolstore.load();//加载数据
	System_tablecolstore.on("beforeload",function(){ 
		System_tablecolstore.baseParams = {
				query : Ext.getCmp("query"+System_tablecolaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_tablecolgrid ]
	});
})
