Ext.onReady(function() {
	var Collectclassify = "收藏";
	var Collecttitle = "当前位置:业务管理》" + Collectclassify;
	var Collectaction = "CollectAction.do";
	var Collectfields = ['collectid'
	        			    ,'collectgoods' 
	        			    ,'collectcustomer' 
	        			    ,'collectdetail' 
	        			    ,'createtime' 
	        			      ];// 全部字段
	var Collectkeycolumn = [ 'collectid' ];// 主键
	var Collectstore = dataStore(Collectfields, basePath + Collectaction + "?method=selQuery");// 定义Collectstore
	var Collectsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Collectcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Collectsm, {// 改
			header : '收藏ID',
			dataIndex : 'collectid',
			hidden : true
		}
		, {
			header : '商品ID',
			dataIndex : 'collectgoods',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '客户ID',
			dataIndex : 'collectcustomer',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'collectdetail',
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
	var CollectdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'CollectdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Collectcollectid',
				name : 'collectid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品ID',
				id : 'Collectcollectgoods',
				name : 'collectgoods',
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
				id : 'Collectcollectcustomer',
				name : 'collectcustomer',
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
				id : 'Collectcollectdetail',
				name : 'collectdetail',
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
				id : 'Collectcreatetime',
				name : 'createtime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Collectbbar = pagesizebar(Collectstore);//定义分页
	var Collectgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Collecttitle,
		store : Collectstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Collectcm,
		sm : Collectsm,
		bbar : Collectbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					CollectdataForm.form.reset();
					createWindow(basePath + Collectaction + "?method=insAll", "新增", CollectdataForm, Collectstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Collectgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Collectaction + "?method=updAll", "修改", CollectdataForm, Collectstore);
					CollectdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Collectgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Collectaction + "?method=delAll",selections,Collectstore,Collectkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Collectaction + "?method=impAll","导入",Collectstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Collectaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Collectgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Collectgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Collectkeycolumn.length;i++){
						fid += selections[0].data[Collectkeycolumn[i]] + ","
					}
					commonAttach(fid, Collectclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Collectaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Collectaction).getValue()) {
								Collectstore.load();
							} else {
								Collectstore.load({
									params : {
										query : Ext.getCmp("query"+Collectaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Collectgrid.region = 'center';
	Collectstore.load();//加载数据
	Collectstore.on("beforeload",function(){ 
		Collectstore.baseParams = {
				query : Ext.getCmp("query"+Collectaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Collectgrid ]
	});
})
