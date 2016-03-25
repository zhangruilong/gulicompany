Ext.onReady(function() {
	var Goodsclassify = "商品";									//标题栏
	var Goodstitle = "当前位置:业务管理》" + Goodsclassify;		//标题
	var Goodsaction = "GoodsAction.do";							//action
	var Goodsfields = ['goodsid'								//字段
	        			    ,'goodscompany' 
	        			    ,'goodscode' 
	        			    ,'goodsname' 
	        			    ,'goodsdetail' 
	        			    ,'goodsunits' 
	        			    ,'goodsclass' 
	        			    ,'goodsimage' 
	        			    ,'goodsstatue' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			    ,'creator' 
	        			    ,'updor' 
	        			      ];// 全部字段
	var Goodskeycolumn = [ 'goodsid' ];// 主键
	//alert(basePath);
	var Goodsstore = dataStore(Goodsfields, basePath + Goodsaction + "?method=selQuery");// 定义Goodsstore
	var Goodssm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Goodscm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Goodssm, {// 改
			header : '商品ID',
			dataIndex : 'goodsid',
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'goodscompany',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '编码',
			dataIndex : 'goodscode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'goodsname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'goodsdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '规格',
			dataIndex : 'goodsunits',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '小类ID',
			dataIndex : 'goodsclass',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '图片',
			dataIndex : 'goodsimage',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'goodsstatue',
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
		, {
			header : '创建人',
			dataIndex : 'creator',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var GoodsdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'GoodsdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Goodsgoodsid',
				name : 'goodsid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商ID',
				id : 'Goodsgoodscompany',
				name : 'goodscompany',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Goodsgoodscode',
				name : 'goodscode',
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
				id : 'Goodsgoodsname',
				name : 'goodsname',
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
				id : 'Goodsgoodsdetail',
				name : 'goodsdetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Goodsgoodsunits',
				name : 'goodsunits',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '小类ID',
				id : 'Goodsgoodsclass',
				name : 'goodsclass',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片',
				id : 'Goodsgoodsimage',
				name : 'goodsimage',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '状态',
				id : 'Goodsgoodsstatue',
				name : 'goodsstatue',
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
				id : 'Goodscreatetime',
				name : 'createtime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '修改时间',
				id : 'Goodsupdtime',
				name : 'updtime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '创建人',
				id : 'Goodscreator',
				name : 'creator',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '修改人',
				id : 'Goodsupdor',
				name : 'updor',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Goodsbbar = pagesizebar(Goodsstore);//定义分页
	var Goodsgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Goodstitle,
		store : Goodsstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Goodscm,
		sm : Goodssm,
		bbar : Goodsbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					GoodsdataForm.form.reset();
					createWindow(basePath + Goodsaction + "?method=insAll", "新增", GoodsdataForm, Goodsstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Goodsgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Goodsaction + "?method=updAll", "修改", GoodsdataForm, Goodsstore);
					GoodsdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Goodsgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Goodsaction + "?method=delAll",selections,Goodsstore,Goodskeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Goodsaction + "?method=impAll","导入",Goodsstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Goodsaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Goodsgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Goodsgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Goodskeycolumn.length;i++){
						fid += selections[0].data[Goodskeycolumn[i]] + ","
					}
					commonAttach(fid, Goodsclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Goodsaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Goodsaction).getValue()) {
								Goodsstore.load();
							} else {
								Goodsstore.load({
									params : {
										query : Ext.getCmp("query"+Goodsaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Goodsgrid.region = 'center';
	Goodsstore.load();//加载数据
	Goodsstore.on("beforeload",function(){ 
		Goodsstore.baseParams = {
				query : Ext.getCmp("query"+Goodsaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Goodsgrid ]
	});
})
