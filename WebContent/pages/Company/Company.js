	var Companyclassify = "经销商";
	var Companytitle = "当前位置:业务管理》" + Companyclassify;
	var Companyaction = "CompanyAction.do";
	var Companyfields = ['companyid'
	        			    ,'companycode' 
	        			    ,'username' 
	        			    ,'companyphone' 
	        			    ,'companyshop' 
	        			    ,'companycity' 
	        			    ,'companyaddress' 
	        			    ,'companydetail' 
	        			    ,'companystatue' 
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			    ,'cityid' 
	        			    ,'citycode' 
	        			    ,'cityname' 
	        			    ,'cityparent' 
	        			    ,'citydetail' 
	        			    ,'citystatue' 
	        			      ];// 全部字段
	var Companykeycolumn = [ 'companyid' ];// 主键
	var Companystore = dataStore(Companyfields, basePath + "CompanyviewAction.do" + "?method=selQuery");// 定义Companystore
	var Companysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Companycm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Companysm, {// 改
			header : '经销商ID',
			dataIndex : 'companyid',
			hidden : true
		}
		, {
			header : '城市',
			dataIndex : 'cityname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '编码',
			dataIndex : 'companycode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '姓名',
			dataIndex : 'username',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '手机',
			dataIndex : 'companyphone',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '店铺',
			dataIndex : 'companyshop',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '城市和县ID',
			dataIndex : 'companycity',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '街道地址',
			dataIndex : 'companyaddress',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'companydetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'companystatue',
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
	var CompanydataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'CompanydataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Companycompanyid',
				name : 'companyid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Companycompanycode',
				name : 'companycode',
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
				id : 'Companyusername',
				name : 'username',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '手机',
				id : 'Companycompanyphone',
				name : 'companyphone',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '店铺',
				id : 'Companycompanyshop',
				name : 'companyshop',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '城市和县ID',
				id : 'Companycompanycity',
				name : 'companycity',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : .9,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '城市',
				id : 'Companycompanycityname',
				name : 'cityname',
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
				handler : selectCity.createCallback(),
				scope : this,
				anchor : '25%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '街道地址',
				id : 'Companycompanyaddress',
				name : 'companyaddress',
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
				id : 'Companycompanydetail',
				name : 'companydetail',
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
				hiddenName : 'companystatue',
				fieldLabel : '状态',
				id : 'Companycompanystatue',
				name : 'companystatue',
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
				id : 'Companyloginname',
				name : 'loginname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Companybbar = pagesizebar(Companystore);//定义分页
	var Companygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Companytitle,
		store : Companystore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Companycm,
		sm : Companysm,
		bbar : Companybbar,
		tbar : [{
				text : "业务员",
				iconCls : 'select',
				handler : function() {
					var selections = Companygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					selectEmp(selections[0].data['companyid']);
				}
			},'-',{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					CompanydataForm.form.reset();
					createWindow(basePath + Companyaction + "?method=insAll", "新增", CompanydataForm, Companystore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Companygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Companyaction + "?method=updAll", "修改", CompanydataForm, Companystore);
					CompanydataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Companygrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Companyaction + "?method=delAll",selections,Companystore,Companykeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Companyaction + "?method=impAll","导入",Companystore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Companyaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Companygrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Companygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Companykeycolumn.length;i++){
						fid += selections[0].data[Companykeycolumn[i]] + ","
					}
					commonAttach(fid, Companyclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Companyaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Companyaction).getValue()) {
								Companystore.load();
							} else {
								Companystore.load({
									params : {
										query : Ext.getCmp("query"+Companyaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Companygrid.region = 'center';
	Companystore.load();//加载数据
	Companystore.on("beforeload",function(){ 
		Companystore.baseParams = {
				query : Ext.getCmp("query"+Companyaction).getValue()
		}; 
	});
