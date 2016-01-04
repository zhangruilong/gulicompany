Ext.onReady(function() {
	var Customerclassify = "客户";
	var Customertitle = "当前位置:业务管理》" + Customerclassify;
	var Customeraction = "CustomerAction.do";
	var Customerfields = ['customerid'
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
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			      ];// 全部字段
	var Customerkeycolumn = [ 'customerid' ];// 主键
	var Customerstore = dataStore(Customerfields, basePath + Customeraction + "?method=selQuery");// 定义Customerstore
	var Customersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Customercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Customersm, {// 改
			header : '客户ID',
			dataIndex : 'customerid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'customercode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '姓名',
			dataIndex : 'customername',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '手机',
			dataIndex : 'customerphone',
			align : 'center',
			width : 80,
			sortable : true
		}
//		, {
//			header : '密码',
//			dataIndex : 'customerpsw',
//			align : 'center',
//			width : 80,
//			sortable : true
//		}
		, {
			header : '店铺',
			dataIndex : 'customershop',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '城市',
			dataIndex : 'customercity',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '县',
			dataIndex : 'customerxian',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '街道地址',
			dataIndex : 'customeraddress',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '类型',
			dataIndex : 'customertype',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '等级',
			dataIndex : 'customerlevel',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : 'openid',
			dataIndex : 'openid',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'customerdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'customerstatue',
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
	var CustomerdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'CustomerdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Customercustomerid',
				name : 'customerid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Customercustomercode',
				name : 'customercode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '姓名',
				id : 'Customercustomername',
				name : 'customername',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'numberfield',
				fieldLabel : '手机',
				id : 'Customercustomerphone',
				name : 'customerphone',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
//		, {
//			columnWidth : 1,
//			layout : 'form',
//			items : [ {
//				xtype : 'textfield',
//				fieldLabel : '密码',
//				id : 'Customercustomerpsw',
//				name : 'customerpsw',
//				maxLength : 100,
//				anchor : '95%'
//			} ]
//		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '店铺',
				id : 'Customercustomershop',
				name : 'customershop',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '城市',
				id : 'Customercustomercity',
				name : 'customercity',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '县',
				id : 'Customercustomerxian',
				name : 'customerxian',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '街道地址',
				id : 'Customercustomeraddress',
				name : 'customeraddress',
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
				id : 'Customercustomertype',
				name : 'customertype',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '等级',
				id : 'Customercustomerlevel',
				name : 'customerlevel',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : 'openid',
				id : 'Customeropenid',
				name : 'openid',
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
				id : 'Customercustomerdetail',
				name : 'customerdetail',
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
				hiddenName : 'customerstatue',
				fieldLabel : '状态',
				id : 'Customercustomerstatue',
				name : 'customerstatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Customerbbar = pagesizebar(Customerstore);//定义分页
	var Customergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Customertitle,
		store : Customerstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Customercm,
		sm : Customersm,
		bbar : Customerbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					CustomerdataForm.form.reset();
					createWindow(basePath + Customeraction + "?method=insAll", "新增", CustomerdataForm, Customerstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Customergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Customeraction + "?method=updAll", "修改", CustomerdataForm, Customerstore);
					CustomerdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Customergrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Customeraction + "?method=delAll",selections,Customerstore,Customerkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Customeraction + "?method=impAll","导入",Customerstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Customeraction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Customergrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Customergrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Customerkeycolumn.length;i++){
						fid += selections[0].data[Customerkeycolumn[i]] + ","
					}
					commonAttach(fid, Customerclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Customeraction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Customeraction).getValue()) {
								Customerstore.load();
							} else {
								Customerstore.load({
									params : {
										query : Ext.getCmp("query"+Customeraction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Customergrid.region = 'center';
	Customerstore.load();//加载数据
	Customerstore.on("beforeload",function(){ 
		Customerstore.baseParams = {
				query : Ext.getCmp("query"+Customeraction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Customergrid ]
	});
})
