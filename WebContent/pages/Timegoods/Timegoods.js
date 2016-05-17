Ext.onReady(function() {
	var Timegoodsclassify = "秒杀";
	var Timegoodstitle = "当前位置:业务管理》" + Timegoodsclassify;
	var Timegoodsaction = "TimegoodsAction.do";
	var Timegoodsfields = ['timegoodsid'
	        			    ,'timegoodscompany' 
	        			    ,'timegoodscode' 
	        			    ,'timegoodsname' 
	        			    ,'timegoodsdetail' 
	        			    ,'timegoodsunits' 
	        			    ,'timegoodsunit' 
	        			    ,'timegoodsprice' 
	        			    ,'timegoodsorgprice' 
	        			    ,'timegoodsnum' 
	        			    ,'timegoodsclass' 
	        			    ,'timegoodsimage' 
	        			    ,'timegoodsstatue' 
	        			    ,'createtime' 
	        			    ,'creator' 
	        			    ,'allnum' 
	        			    ,'surplusnum' 
	        			    ,'timegoodsseq' 
	        			    ,'timegoodsscope' 
	        			      ];// 全部字段
	var Timegoodskeycolumn = [ 'timegoodsid' ];// 主键
	var Timegoodsstore = dataStore(Timegoodsfields, basePath + Timegoodsaction + "?method=selQuery");// 定义Timegoodsstore
	var Timegoodssm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Timegoodscm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Timegoodssm, {// 改
			header : '促销品ID',
			dataIndex : 'timegoodsid',
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'timegoodscompany',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '编码',
			dataIndex : 'timegoodscode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'timegoodsname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'timegoodsdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '规格',
			dataIndex : 'timegoodsunits',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '单位',
			dataIndex : 'timegoodsunit',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '原价',
			dataIndex : 'timegoodsprice',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '现价',
			dataIndex : 'timegoodsorgprice',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '限量',
			dataIndex : 'timegoodsnum',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '小类名称',
			dataIndex : 'timegoodsclass',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '图片',
			dataIndex : 'timegoodsimage',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'timegoodsstatue',
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
			header : '创建人',
			dataIndex : 'creator',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '全部限量',
			dataIndex : 'allnum',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '剩余数量',
			dataIndex : 'surplusnum',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '顺序',
			dataIndex : 'timegoodsseq',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '客户范围',
			dataIndex : 'timegoodsscope',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var TimegoodsdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'TimegoodsdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Timegoodstimegoodsid',
				name : 'timegoodsid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商ID',
				id : 'Timegoodstimegoodscompany',
				name : 'timegoodscompany',
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
				id : 'Timegoodstimegoodscode',
				name : 'timegoodscode',
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
				id : 'Timegoodstimegoodsname',
				name : 'timegoodsname',
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
				id : 'Timegoodstimegoodsdetail',
				name : 'timegoodsdetail',
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
				id : 'Timegoodstimegoodsunits',
				name : 'timegoodsunits',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '单位',
				id : 'Timegoodstimegoodsunit',
				name : 'timegoodsunit',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '原价',
				id : 'Timegoodstimegoodsprice',
				name : 'timegoodsprice',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '现价',
				id : 'Timegoodstimegoodsorgprice',
				name : 'timegoodsorgprice',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '限量',
				id : 'Timegoodstimegoodsnum',
				name : 'timegoodsnum',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '小类名称',
				id : 'Timegoodstimegoodsclass',
				name : 'timegoodsclass',
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
				id : 'Timegoodstimegoodsimage',
				name : 'timegoodsimage',
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
				id : 'Timegoodstimegoodsstatue',
				name : 'timegoodsstatue',
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
				id : 'Timegoodscreatetime',
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
				fieldLabel : '创建人',
				id : 'Timegoodscreator',
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
				fieldLabel : '全部限量',
				id : 'Timegoodsallnum',
				name : 'allnum',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '剩余数量',
				id : 'Timegoodssurplusnum',
				name : 'surplusnum',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '顺序',
				id : 'Timegoodstimegoodsseq',
				name : 'timegoodsseq',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户范围',
				id : 'Timegoodstimegoodsscope',
				name : 'timegoodsscope',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Timegoodsbbar = pagesizebar(Timegoodsstore);//定义分页
	var Timegoodsgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Timegoodstitle,
		store : Timegoodsstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Timegoodscm,
		sm : Timegoodssm,
		bbar : Timegoodsbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					TimegoodsdataForm.form.reset();
					createWindow(basePath + Timegoodsaction + "?method=insAll", "新增", TimegoodsdataForm, Timegoodsstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Timegoodsgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					createWindow(basePath + Timegoodsaction + "?method=updAll", "修改", TimegoodsdataForm, Timegoodsstore);
					TimegoodsdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Timegoodsgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Timegoodsaction + "?method=delAll",selections,Timegoodsstore,Timegoodskeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Timegoodsaction + "?method=impAll","导入",Timegoodsstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Timegoodsaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Timegoodsgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Timegoodsgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Timegoodskeycolumn.length;i++){
						fid += selections[0].data[Timegoodskeycolumn[i]] + ","
					}
					commonAttach(fid, Timegoodsclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Timegoodsaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Timegoodsaction).getValue()) {
								Timegoodsstore.load();
							} else {
								Timegoodsstore.load({
									params : {
										query : Ext.getCmp("query"+Timegoodsaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Timegoodsgrid.region = 'center';
	Timegoodsstore.on("beforeload",function(){ 
		Timegoodsstore.baseParams = {
				query : Ext.getCmp("query"+Timegoodsaction).getValue()
		}; 
	});
	Timegoodsstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Timegoodsgrid ]
	});
})
