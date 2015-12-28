Ext.onReady(function() {
	var System_userclassify = "用户";
	var System_usertitle = "当前位置:系统管理》" + System_userclassify;
	var System_useraction = "System_userAction.do";
	var System_userfields = ['id'
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'username' 
	        			    ,'statue' 
	        			      ];// 全部字段
	var System_userkeycolumn = [ 'id' ];// 主键
	var System_userstore = dataStore(System_userfields, basePath + System_useraction + "?method=selQuery");// 定义System_userstore
	var System_usersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_usercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_usersm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '登录名',
			dataIndex : 'loginname',
			sortable : true
		}
		, {
			header : '用户名',
			dataIndex : 'username',
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'statue',
			sortable : true
		}
		]
	});
	var statusStore = new Ext.data.ArrayStore({//
    	fields:["name"],
    	data:[["启用"],["禁用"]]
    });
	var System_userdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'System_userdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'System_userid',
				name : 'id',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '登录名',
				id : 'System_userloginname',
				name : 'loginname',
				allowBlank : false,
				maxLength : 20,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '用户名',
				id : 'System_userusername',
				name : 'username',
				allowBlank : false,
				maxLength : 20,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'System_userstatue',
				name : 'statue',
				emptyText : '请选择',
				store : statusStore,
				mode : 'local',
				displayField : 'name',
				valueField : 'name',
				hiddenName : 'statue',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
 		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : "checkbox",
				id : "resetpassword",
				name : "resetpassword",
				boxLabel : "初始化密码"
			} ]
 		}
 		]
	});
	var System_userbbar = pagesizebar(System_userstore);//定义分页
	var System_usergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_usertitle,
		store : System_userstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_usercm,
		sm : System_usersm,
		bbar : System_userbbar,
		tbar : [{
			text : '初始化密码',
			iconCls : 'reset',
			handler : function() {
				var currRecord = System_usergrid.getSelectionModel().getSelections();
				if (currRecord) {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要初始化密码？', function(btn, text) {
						if (btn == 'yes') {
							var ids = '[';
							for (var i = 0; i < currRecord.length; i++) {
								ids += "{'id':'" + currRecord[i].data['id'] + "'},"
							};
							Ext.Ajax.request({
								url : basePath + System_useraction + "?method=resetpassword",
								method : 'POST',
								params : {
									json : ids.substr(0, ids.length - 1) + "]"
								},
								success : function(response) {
									Ext.Msg.alert('提示', '操作成功',function(){
										System_userstore.reload();
									});
								},
								failure : function(response) {
									Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
								}
							});
						}
					});
				} else {
					Ext.Msg.alert('提示', '请选择您要初始化密码的数据！');
				}
			}
		},'-',{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					System_userdataForm.form.reset();
					createWindow(basePath + System_useraction + "?method=insAll", "新增", System_userdataForm, System_userstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = System_usergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + System_useraction + "?method=updAll", "修改", System_userdataForm, System_userstore);
					System_userdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_usergrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + System_useraction + "?method=delAll",selections,System_userstore,System_userkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + System_useraction + "?method=impAll","导入",System_userstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + System_useraction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(System_usergrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = System_usergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<System_userkeycolumn.length;i++){
						fid += selections[0].data[System_userkeycolumn[i]] + ","
					}
					commonAttach(fid, System_userclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_useraction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_useraction).getValue()) {
								System_userstore.load();
							} else {
								System_userstore.load({
									params : {
										query : Ext.getCmp("query"+System_useraction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_usergrid.region = 'center';
	System_userstore.load();//加载数据
	System_userstore.on("beforeload",function(){ 
		System_userstore.baseParams = {
				query : Ext.getCmp("query"+System_useraction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_usergrid ]
	});
})
