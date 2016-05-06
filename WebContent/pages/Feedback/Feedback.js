Ext.onReady(function() {
	var Feedbackclassify = "客户反馈意见";
	var Feedbacktitle = "当前位置:业务管理》" + Feedbackclassify;
	var Feedbackaction = "FeedbackAction.do";
	var Feedbackfields = ['feedbackid'
	        			    ,'feedbackdetail' 
	        			    ,'feedbackcustomer' 
	        			    ,'feedbacktime' 
	        			    ,'feedbackstate' 
	        			      ];// 全部字段
	var Feedbackkeycolumn = [ 'feedbackid' ];// 主键
	var Feedbackstore = dataStore(Feedbackfields, basePath + Feedbackaction + "?method=selQuery");// 定义Feedbackstore
	var Feedbacksm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Feedbackcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Feedbacksm, {// 改
			header : '客户反馈id',
			dataIndex : 'feedbackid',
			hidden : true
		}
		, {
			header : '内容',
			dataIndex : 'feedbackdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '客户id',
			dataIndex : 'feedbackcustomer',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '反馈时间',
			dataIndex : 'feedbacktime',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '反馈状态',
			dataIndex : 'feedbackstate',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var FeedbackdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'FeedbackdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Feedbackfeedbackid',
				name : 'feedbackid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '内容',
				id : 'Feedbackfeedbackdetail',
				name : 'feedbackdetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户id',
				id : 'Feedbackfeedbackcustomer',
				name : 'feedbackcustomer',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '反馈时间',
				id : 'Feedbackfeedbacktime',
				name : 'feedbacktime',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '反馈状态',
				id : 'Feedbackfeedbackstate',
				name : 'feedbackstate',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Feedbackbbar = pagesizebar(Feedbackstore);//定义分页
	var Feedbackgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Feedbacktitle,
		store : Feedbackstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Feedbackcm,
		sm : Feedbacksm,
		bbar : Feedbackbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					FeedbackdataForm.form.reset();
					createWindow(basePath + Feedbackaction + "?method=insAll", "新增", FeedbackdataForm, Feedbackstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Feedbackgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					createWindow(basePath + Feedbackaction + "?method=updAll", "修改", FeedbackdataForm, Feedbackstore);
					FeedbackdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Feedbackgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Feedbackaction + "?method=delAll",selections,Feedbackstore,Feedbackkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Feedbackaction + "?method=impAll","导入",Feedbackstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Feedbackaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Feedbackgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Feedbackgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Feedbackkeycolumn.length;i++){
						fid += selections[0].data[Feedbackkeycolumn[i]] + ","
					}
					commonAttach(fid, Feedbackclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Feedbackaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Feedbackaction).getValue()) {
								Feedbackstore.load();
							} else {
								Feedbackstore.load({
									params : {
										query : Ext.getCmp("query"+Feedbackaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Feedbackgrid.region = 'center';
	Feedbackstore.on("beforeload",function(){ 
		Feedbackstore.baseParams = {
				query : Ext.getCmp("query"+Feedbackaction).getValue()
		}; 
	});
	Feedbackstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Feedbackgrid ]
	});
})
