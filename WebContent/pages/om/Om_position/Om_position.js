Ext.onReady(function() {
	var Om_positionclassify = "岗位/职位";
	var Om_positiontitle = "当前位置:系统管理》" + Om_positionclassify;
	var Om_positionaction = "Om_positionAction.do";
	var Om_positionfields = ['positionid'
	        			    ,'posicode' 
	        			    ,'posiname' 
	        			    ,'posilevel' 
	        			    ,'manaposi' 
	        			    ,'dutyid' 
	        			    ,'orgid' 
	        			    ,'positionseq' 
	        			    ,'positype' 
	        			    ,'createtime' 
	        			    ,'lastupdate' 
	        			    ,'updator' 
	        			    ,'startdate' 
	        			    ,'enddate' 
	        			    ,'status' 
	        			    ,'isleaf' 
	        			    ,'subcount' 
	        			      ];// 全部字段
	var Om_positionkeycolumn = [ 'positionid' ];// 主键
	var Om_positionstore = dataStore(Om_positionfields, basePath + Om_positionaction + "?method=selQuery");// 定义Om_positionstore
	var Om_positionsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Om_positioncm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Om_positionsm, {// 改
			header : '岗位编号',
			dataIndex : 'positionid',
			hidden : true
		}
		, {
			header : '岗位代码',
			dataIndex : 'posicode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位名称',
			dataIndex : 'posiname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位层次',
			dataIndex : 'posilevel',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '上级岗位',
			dataIndex : 'manaposi',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '职务编号',
			dataIndex : 'dutyid',
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
			header : '岗位序列',
			dataIndex : 'positionseq',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位类别',
			dataIndex : 'positype',
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
			header : '最近更新时间',
			dataIndex : 'lastupdate',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '最近更新人员',
			dataIndex : 'updator',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位有效开始日期',
			dataIndex : 'startdate',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位有效截止日期',
			dataIndex : 'enddate',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '岗位状态',
			dataIndex : 'status',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '是否叶子岗位',
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
	var Om_positiondataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'Om_positiondataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Om_positionpositionid',
				name : 'positionid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位代码',
				id : 'Om_positionposicode',
				name : 'posicode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位名称',
				id : 'Om_positionposiname',
				name : 'posiname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位层次',
				id : 'Om_positionposilevel',
				name : 'posilevel',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '上级岗位',
				id : 'Om_positionmanaposi',
				name : 'manaposi',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '职务编号',
				id : 'Om_positiondutyid',
				name : 'dutyid',
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
				id : 'Om_positionorgid',
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
				fieldLabel : '岗位序列',
				id : 'Om_positionpositionseq',
				name : 'positionseq',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位类别',
				id : 'Om_positionpositype',
				name : 'positype',
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
				id : 'Om_positioncreatetime',
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
				fieldLabel : '最近更新时间',
				id : 'Om_positionlastupdate',
				name : 'lastupdate',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '最近更新人员',
				id : 'Om_positionupdator',
				name : 'updator',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位有效开始日期',
				id : 'Om_positionstartdate',
				name : 'startdate',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位有效截止日期',
				id : 'Om_positionenddate',
				name : 'enddate',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '岗位状态',
				id : 'Om_positionstatus',
				name : 'status',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '是否叶子岗位',
				id : 'Om_positionisleaf',
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
				id : 'Om_positionsubcount',
				name : 'subcount',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Om_positionbbar = pagesizebar(Om_positionstore);//定义分页
	var Om_positiongrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Om_positiontitle,
		store : Om_positionstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Om_positioncm,
		sm : Om_positionsm,
		bbar : Om_positionbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					Om_positiondataForm.form.reset();
					createWindow(basePath + Om_positionaction + "?method=insAll", "新增", Om_positiondataForm, Om_positionstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Om_positiongrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					Om_positiondataForm.form.loadRecord(selections[0]);
					createWindow(basePath + Om_positionaction + "?method=updAll", "修改", Om_positiondataForm, Om_positionstore);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Om_positiongrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Om_positionaction + "?method=delAll",selections,Om_positionstore,Om_positionkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Om_positionaction + "?method=impAll","导入",Om_positionstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Om_positionaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Om_positiongrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Om_positiongrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Om_positionkeycolumn.length;i++){
						fid += selections[0].data[Om_positionkeycolumn[i]] + ","
					}
					commonAttach(fid, Om_positionclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Om_positionaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Om_positionaction).getValue()) {
								Om_positionstore.load();
							} else {
								Om_positionstore.load({
									params : {
										query : Ext.getCmp("query"+Om_positionaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Om_positiongrid.region = 'center';
	Om_positionstore.load();//加载数据
	Om_positionstore.on("beforeload",function(){ 
		Om_positionstore.baseParams = {
				query : Ext.getCmp("query"+Om_positionaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Om_positiongrid ]
	});
})
