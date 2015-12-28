Ext.onReady(function() {
	var Addressclassify = "我的地址";
	var Addresstitle = "当前位置:业务管理》" + Addressclassify;
	var Addressaction = "AddressAction.do";
	var Addressfields = ['addressid'
	        			    ,'addresscustomer' 
	        			    ,'addressconnect' 
	        			    ,'addressphone' 
	        			    ,'addressaddress' 
	        			    ,'addressture' 
	        			      ];// 全部字段
	var Addresskeycolumn = [ 'addressid' ];// 主键
	var Addressstore = dataStore(Addressfields, basePath + Addressaction + "?method=selQuery");// 定义Addressstore
	var Addresssm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Addresscm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Addresssm, {// 改
			header : '我的地址ID',
			dataIndex : 'addressid',
			hidden : true
		}
		, {
			header : '客户ID',
			dataIndex : 'addresscustomer',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '联系人',
			dataIndex : 'addressconnect',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '手机',
			dataIndex : 'addressphone',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '地址',
			dataIndex : 'addressaddress',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '是否默认',
			dataIndex : 'addressture',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var AddressdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'AddressdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Addressaddressid',
				name : 'addressid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户ID',
				id : 'Addressaddresscustomer',
				name : 'addresscustomer',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系人',
				id : 'Addressaddressconnect',
				name : 'addressconnect',
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
				id : 'Addressaddressphone',
				name : 'addressphone',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '地址',
				id : 'Addressaddressaddress',
				name : 'addressaddress',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '是否默认',
				id : 'Addressaddressture',
				name : 'addressture',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Addressbbar = pagesizebar(Addressstore);//定义分页
	var Addressgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Addresstitle,
		store : Addressstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Addresscm,
		sm : Addresssm,
		bbar : Addressbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					AddressdataForm.form.reset();
					createWindow(basePath + Addressaction + "?method=insAll", "新增", AddressdataForm, Addressstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Addressgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Addressaction + "?method=updAll", "修改", AddressdataForm, Addressstore);
					AddressdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Addressgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Addressaction + "?method=delAll",selections,Addressstore,Addresskeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Addressaction + "?method=impAll","导入",Addressstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Addressaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Addressgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Addressgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Addresskeycolumn.length;i++){
						fid += selections[0].data[Addresskeycolumn[i]] + ","
					}
					commonAttach(fid, Addressclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Addressaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Addressaction).getValue()) {
								Addressstore.load();
							} else {
								Addressstore.load({
									params : {
										query : Ext.getCmp("query"+Addressaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Addressgrid.region = 'center';
	Addressstore.load();//加载数据
	Addressstore.on("beforeload",function(){ 
		Addressstore.baseParams = {
				query : Ext.getCmp("query"+Addressaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Addressgrid ]
	});
})
