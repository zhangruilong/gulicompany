Ext.onReady(function() {
	var System_roleclassify = "角色";
	var System_roletitle = "当前位置:系统管理》" + System_roleclassify;
	var System_roleaction = "System_roleAction.do";
	var System_rolefields = ['id'
	        			    ,'code' 
	        			    ,'name' 
	        			    ,'detail' 
	        			      ];// 全部字段
	var System_rolekeycolumn = [ 'id' ];// 主键
	var System_rolestore = dataStore(System_rolefields, basePath + System_roleaction + "?method=selQuery");// 定义System_rolestore
	var System_rolesm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_rolecm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_rolesm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'code',
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'name',
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'detail',
			sortable : true
		}
		]
	});
	var System_roledataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'System_roledataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'System_roleid',
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
				id : 'System_rolecode',
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
				id : 'System_rolename',
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
				id : 'System_roledetail',
				name : 'detail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	var System_rolebbar = pagesizebar(System_rolestore);//定义分页
	var System_rolegrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_roletitle,
		store : System_rolestore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_rolecm,
		sm : System_rolesm,
		bbar : System_rolebbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					System_roledataForm.form.reset();
					createWindow(basePath + System_roleaction + "?method=insAll", "新增", System_roledataForm, System_rolestore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = System_rolegrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath +  System_roleaction + "?method=updAll", "修改", System_roledataForm, System_rolestore);
					System_roledataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_rolegrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath +  System_roleaction + "?method=delAll",selections,System_rolestore,System_rolekeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath +  System_roleaction + "?method=impAll","导入",System_rolestore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath +  System_roleaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(System_rolegrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = System_rolegrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<System_rolekeycolumn.length;i++){
						fid += selections[0].data[System_rolekeycolumn[i]] + ","
					}
					commonAttach(fid, System_roleclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_roleaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_roleaction).getValue()) {
								System_rolestore.load();
							} else {
								System_rolestore.load({
									params : {
										query : Ext.getCmp("query"+System_roleaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_rolegrid.addListener('rowclick',function(rid, rowIndex, columnIndex, e){  
		var record = System_rolegrid.getStore().getAt(rowIndex);
    	 editeInfo(record.get('id'));
	});
	System_rolegrid.region = 'center';
	System_rolestore.load();//加载数据
	System_rolestore.on("beforeload",function(){ 
		System_rolestore.baseParams = {
				query : Ext.getCmp("query"+System_roleaction).getValue()
		}; 
	});
	var editPanel = new Ext.Panel({
        id:"editPanel",
        bodyStyle : 'padding:0px;',
        width: 555
    });
	editPanel.region = 'east';
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_rolegrid,editPanel ]
	});
})
