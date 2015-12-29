	var Goodsclassclassify = "大小类";
	var Goodsclasstitle = "当前位置:业务管理》" + Goodsclassclassify;
	var Goodsclassaction = "GoodsclassAction.do";
	var Goodsclassfields = ['goodsclassid'
	        			    ,'goodsclasscode' 
	        			    ,'goodsclassname' 
	        			    ,'goodsclassparent' 
	        			    ,'goodsclassdetail' 
	        			    ,'goodsclassstatue' 
	        			      ];// 全部字段
	var Goodsclasskeycolumn = [ 'goodsclassid' ];// 主键
	var Goodsclassstore = dataStore(Goodsclassfields, basePath + Goodsclassaction + "?method=selQuery");// 定义Goodsclassstore
	var Goodsclasssm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Goodsclasscm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Goodsclasssm, {// 改
			header : '大小类ID',
			dataIndex : 'goodsclassid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'goodsclasscode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'goodsclassname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '父类',
			dataIndex : 'goodsclassparent',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '城市',
			dataIndex : 'goodsclassdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'goodsclassstatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var GoodsclassdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'GoodsclassdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Goodsclassgoodsclassid',
				name : 'goodsclassid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Goodsclassgoodsclasscode',
				name : 'goodsclasscode',
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
				id : 'Goodsclassgoodsclassname',
				name : 'goodsclassname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '父类',
				id : 'Goodsclassgoodsclassparent',
				name : 'goodsclassparent',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '父类',
				id : 'Goodsclassgoodsclassparentname',
				name : 'goodsclassparentname',
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
				id : 'Goodsclassgoodsclassdetail',
				name : 'goodsclassdetail',
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
				hiddenName : 'goodsclassstatue',
				fieldLabel : '状态',
				id : 'Goodsclassgoodsclassstatue',
				name : 'goodsclassstatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Goodsclassbbar = pagesizebar(Goodsclassstore);//定义分页
	var Goodsclassgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Goodsclasstitle,
		store : Goodsclassstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Goodsclasscm,
		sm : Goodsclasssm,
		bbar : Goodsclassbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					GoodsclassdataForm.form.reset();
					createWindow(basePath + Goodsclassaction + "?method=insAll", "新增", GoodsclassdataForm, Goodsclassstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Goodsclassgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Goodsclassaction + "?method=updAll", "修改", GoodsclassdataForm, Goodsclassstore);
					GoodsclassdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Goodsclassgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Goodsclassaction + "?method=delAll",selections,Goodsclassstore,Goodsclasskeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Goodsclassaction + "?method=impAll","导入",Goodsclassstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Goodsclassaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Goodsclassgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Goodsclassgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Goodsclasskeycolumn.length;i++){
						fid += selections[0].data[Goodsclasskeycolumn[i]] + ","
					}
					commonAttach(fid, Goodsclassclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Goodsclassaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Goodsclassaction).getValue()) {
								Goodsclassstore.load();
							} else {
								Goodsclassstore.load({
									params : {
										query : Ext.getCmp("query"+Goodsclassaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Goodsclassgrid.region = 'center';
	Goodsclassstore.load();//加载数据
	Goodsclassstore.on("beforeload",function(){ 
		Goodsclassstore.baseParams = {
				query : Ext.getCmp("query"+Goodsclassaction).getValue()
		}; 
	});
