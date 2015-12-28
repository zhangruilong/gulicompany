Ext.onReady(function() {
	var Om_busiorgclassify = "业务机构";
	var Om_busiorgtitle = "当前位置:系统管理》" + Om_busiorgclassify;
	var Om_busiorgaction = "Om_busiorgAction.do";
	var Om_busiorgfields = ['busidomain'
	        			    ,'busiorgid' 
	        			    ,'orgname' 
	        			    ,'parentid' 
	        			    ,'orgid' 
	        			    ,'orglevel' 
	        			    ,'nodetype' 
	        			    ,'orgcode' 
	        			    ,'seqno' 
	        			    ,'manapos' 
	        			    ,'sortno' 
	        			    ,'isleaf' 
	        			    ,'subcount' 
	        			      ];// 全部字段
	var Om_busiorgkeycolumn = [ 'busidomain' ];// 主键
	var Om_busiorgstore = dataStore(Om_busiorgfields, basePath + Om_busiorgaction + "?method=selQuery");// 定义Om_busiorgstore
	var Om_busiorgsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Om_busiorgcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Om_busiorgsm, {// 改
			header : '业务条线',
			dataIndex : 'busidomain',
			hidden : true
		}
		, {
			header : '业务机构编号',
			dataIndex : 'busiorgid',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '业务机构名称',
			dataIndex : 'orgname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '上级业务机构',
			dataIndex : 'parentid',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '机构编号',
			dataIndex : 'orgid',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '业务机构层次',
			dataIndex : 'orglevel',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '节点类型',
			dataIndex : 'nodetype',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '机构代号',
			dataIndex : 'orgcode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '序列号',
			dataIndex : 'seqno',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '主管岗位',
			dataIndex : 'manapos',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '排列顺序编号',
			dataIndex : 'sortno',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '是否叶子节点',
			dataIndex : 'isleaf',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '子节点数',
			dataIndex : 'subcount',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var Om_busiorgdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'Om_busiorgdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Om_busiorgbusidomain',
				name : 'busidomain',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '业务机构编号',
				id : 'Om_busiorgbusiorgid',
				name : 'busiorgid',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '业务机构名称',
				id : 'Om_busiorgorgname',
				name : 'orgname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '上级业务机构',
				id : 'Om_busiorgparentid',
				name : 'parentid',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '机构编号',
				id : 'Om_busiorgorgid',
				name : 'orgid',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '业务机构层次',
				id : 'Om_busiorgorglevel',
				name : 'orglevel',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '节点类型',
				id : 'Om_busiorgnodetype',
				name : 'nodetype',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '机构代号',
				id : 'Om_busiorgorgcode',
				name : 'orgcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '序列号',
				id : 'Om_busiorgseqno',
				name : 'seqno',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '主管岗位',
				id : 'Om_busiorgmanapos',
				name : 'manapos',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '排列顺序编号',
				id : 'Om_busiorgsortno',
				name : 'sortno',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '是否叶子节点',
				id : 'Om_busiorgisleaf',
				name : 'isleaf',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '子节点数',
				id : 'Om_busiorgsubcount',
				name : 'subcount',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Om_busiorgbbar = pagesizebar(Om_busiorgstore);//定义分页
	var Om_busiorggrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Om_busiorgtitle,
		store : Om_busiorgstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Om_busiorgcm,
		sm : Om_busiorgsm,
		bbar : Om_busiorgbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					Om_busiorgdataForm.form.reset();
					createWindow(basePath + Om_busiorgaction + "?method=insAll", "新增", Om_busiorgdataForm, Om_busiorgstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Om_busiorggrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Om_busiorgaction + "?method=updAll", "修改", Om_busiorgdataForm, Om_busiorgstore);
					Om_busiorgdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Om_busiorggrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Om_busiorgaction + "?method=delAll",selections,Om_busiorgstore,Om_busiorgkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Om_busiorgaction + "?method=impAll","导入",Om_busiorgstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Om_busiorgaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Om_busiorggrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Om_busiorggrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Om_busiorgkeycolumn.length;i++){
						fid += selections[0].data[Om_busiorgkeycolumn[i]] + ","
					}
					commonAttach(fid, Om_busiorgclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Om_busiorgaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Om_busiorgaction).getValue()) {
								Om_busiorgstore.load();
							} else {
								Om_busiorgstore.load({
									params : {
										query : Ext.getCmp("query"+Om_busiorgaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Om_busiorggrid.region = 'center';
	Om_busiorgstore.load();//加载数据
	Om_busiorgstore.on("beforeload",function(){ 
		Om_busiorgstore.baseParams = {
				query : Ext.getCmp("query"+Om_busiorgaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Om_busiorggrid ]
	});
})
