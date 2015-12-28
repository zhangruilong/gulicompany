Ext.onReady(function() {
	var System_attachclassify = "附件";
	var System_attachtitle = "当前位置:系统管理》" + System_attachclassify;
	var System_attachaction = "System_attachAction.do";
	var System_attachfields = ['id'
	        			    ,'code' 
	        			    ,'name' 
	        			    ,'detail' 
	        			    ,'classify' 
	        			    ,'type' 
	        			    ,'attachsize' 
	        			    ,'fid' 
	        			    ,'creator' 
	        			    ,'createtime' 
	        			      ];// 全部字段
	var System_attachkeycolumn = [ 'id' ];// 主键
	var System_attachstore = dataStore(System_attachfields, basePath + System_attachaction + "?method=selQuery");// 定义System_attachstore
	var System_attachsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_attachcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_attachsm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'code',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'name',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'detail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '分类',
			dataIndex : 'classify',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '类型',
			dataIndex : 'type',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '大小',
			dataIndex : 'attachsize',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '外键',
			dataIndex : 'fid',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '创建人员',
			dataIndex : 'creator',
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
		]
	});
	var System_attachdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'System_attachdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'System_attachid',
				name : 'id',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'System_attachcode',
				name : 'code',
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
				id : 'System_attachname',
				name : 'name',
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
				id : 'System_attachdetail',
				name : 'detail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '分类',
				id : 'System_attachclassify',
				name : 'classify',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '类型',
				id : 'System_attachtype',
				name : 'type',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '大小',
				id : 'System_attachattachsize',
				name : 'attachsize',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '外键',
				id : 'System_attachfid',
				name : 'fid',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var System_attachbbar = pagesizebar(System_attachstore);//定义分页
	var System_attachgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_attachtitle,
		store : System_attachstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_attachcm,
		sm : System_attachsm,
		bbar : System_attachbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					System_attachdataForm.form.reset();
					createWindow(basePath + System_attachaction + "?method=insAll", "新增", System_attachdataForm, System_attachstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = System_attachgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + System_attachaction + "?method=updAll", "修改", System_attachdataForm, System_attachstore);
					System_attachdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_attachgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + System_attachaction + "?method=delAll",selections,System_attachstore,System_attachkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + System_attachaction + "?method=impAll","导入",System_attachstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + System_attachaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(System_attachgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = System_attachgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<System_attachkeycolumn.length;i++){
						fid += selections[0].data[System_attachkeycolumn[i]] + ","
					}
					commonAttach(fid, System_attachclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_attachaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_attachaction).getValue()) {
								System_attachstore.load();
							} else {
								System_attachstore.load({
									params : {
										query : Ext.getCmp("query"+System_attachaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_attachgrid.region = 'center';
	System_attachstore.load();//加载数据
	System_attachstore.on("beforeload",function(){ 
		System_attachstore.baseParams = {
				query : Ext.getCmp("query"+System_attachaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_attachgrid ]
	});
})
