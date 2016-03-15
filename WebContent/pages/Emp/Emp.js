Ext.onReady(function() {
	var Empclassify = "业务员";
	var Emptitle = "当前位置:业务管理》" + Empclassify;
	var Empaction = "EmpAction.do";
	var Empfields = ['empid'
	        			    ,'empcompany' 
	        			    ,'empcode' 
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'empdetail' 
	        			    ,'empstatue' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			      ];// 全部字段
	var Empkeycolumn = [ 'empid' ];// 主键
	var Empstore = dataStore(Empfields, basePath + Empaction + "?method=selQuery");// 定义Empstore
	var Empsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Empcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Empsm, {// 改
			header : '业务员ID',
			dataIndex : 'empid',
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'empcompany',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '编码',
			dataIndex : 'empcode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '账号',
			dataIndex : 'loginname',
			align : 'center',
			width : 80,
			sortable : true
		}
//		, {
//			header : '密码',
//			dataIndex : 'password',
//			align : 'center',
//			width : 80,
//			sortable : true
//		}
		, {
			header : '描述',
			dataIndex : 'empdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'empstatue',
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
			header : '修改时间',
			dataIndex : 'updtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var EmpdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'EmpdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Empempid',
				name : 'empid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '经销商ID',
				id : 'Empempcompany',
				name : 'empcompany',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : .9,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商',
				id : 'Empempcompanyname',
				name : 'companyname',
				readOnly:true,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : .1,
			layout : 'form',
			items : [ {
				xtype : 'button',
				iconCls : 'select',
				maxLength : 100,
				handler : selectCompany.createCallback(),
				scope : this,
				anchor : '25%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Empempcode',
				name : 'empcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '账号',
				id : 'Emploginname',
				name : 'loginname',
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
				id : 'Empempdetail',
				name : 'empdetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				displayField : 'name',
				valueField : 'name',
				hiddenName : 'empstatue',
				fieldLabel : '状态',
				id : 'Empempstatue',
				name : 'empstatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Empbbar = pagesizebar(Empstore);//定义分页
	var Empgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Emptitle,
		store : Empstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Empcm,
		sm : Empsm,
		bbar : Empbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					EmpdataForm.form.reset();
					createWindow(basePath + Empaction + "?method=insAll", "新增", EmpdataForm, Empstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Empaction + "?method=updAll", "修改", EmpdataForm, Empstore);
					EmpdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Empaction + "?method=delAll",selections,Empstore,Empkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Empaction + "?method=impAll","导入",Empstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Empaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Empgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Empkeycolumn.length;i++){
						fid += selections[0].data[Empkeycolumn[i]] + ","
					}
					commonAttach(fid, Empclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Empaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Empaction).getValue()) {
								Empstore.load();
							} else {
								Empstore.load({
									params : {
										query : Ext.getCmp("query"+Empaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Empgrid.region = 'center';
	Empstore.load();//加载数据
	Empstore.on("beforeload",function(){ 
		Empstore.baseParams = {
				query : Ext.getCmp("query"+Empaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Empgrid ]
	});
})
