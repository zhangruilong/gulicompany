var Om_emppositionclassify = "人员岗位对应关系";
var Om_emppositiontitle = "当前位置:系统管理》" + Om_emppositionclassify;
var Om_emppositionaction = "Om_emppositionAction.do";
var Om_emppositionfields = ['posempid'
	        			    ,'positionid'
                            ,'empid' 
	        			    ,'ismain' 
	        			    ,'empcode' 
	        			    ,'empname' 
	        			    ,'gender' 
	        			    ,'empstatus' 
	        			    ,'orgname' 
	        			    ,'posiname' 
	        			    ,'loginname'  
        			      ];// 全部字段
var Om_emppositionkeycolumn = [ 'positionid' ];// 主键
var Om_emppositionstore = dataStore(Om_emppositionfields, basePath + "Om_empposorgviewAction.do?method=selQuery");// 定义Om_emppositionstore
var Om_emppositionsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_emppositioncm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_emppositionsm, {// 改
		header : 'posempid',
		dataIndex : 'posempid',
		hidden : true
	}
	, {
		header : '岗位编号',
		dataIndex : 'positionid',
		hidden : true
	}
	, {
		header : '人员编号',
		dataIndex : 'empid',
		align : 'center',
		width : 80,
		hidden : true
	}
	, {
		header : '编号',
		dataIndex : 'empcode',
		sortable : true
	}
	, {
		header : '姓名',
		dataIndex : 'empname',
		sortable : true
	}
	, {
		header : '性别',
		dataIndex : 'gender',
		sortable : true
	}
	, {
		header : '状态',
		dataIndex : 'empstatus',
		sortable : true
	}
	, {
		header : '机构名称',
		dataIndex : 'orgname',
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
		header : '是否主岗位',
		dataIndex : 'ismain',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '登录名',
		dataIndex : 'loginname',
		sortable : true
	}
	]
});
var ismainStore = new Ext.data.ArrayStore({//
	fields:["ismain"],
	data:[["是"],["否"]]
});
var Om_emppositiondataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_emppositiondataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_emppositionposempid',
			name : 'posempid',
			hidden : true
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '岗位编号',
			id : 'Om_emppositionpositionid',
			name : 'positionid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .9,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '岗位名称',
			id : 'Om_emppositionpositionname',
			name : 'posiname',
			maxLength : 100,
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
			handler : selectOm_position.createCallback(),
			scope : this,
			anchor : '25%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '人员编号',
			id : 'Om_emppositionempid',
			name : 'empid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .9,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '人员',
			id : 'Om_emppositionempname',
			name : 'empname',
			maxLength : 100,
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
			handler : selectOm_employee.createCallback(),
			scope : this,
			anchor : '25%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '是否主岗位',
			id : 'Om_emppositionismain',
			name : 'ismain',
			emptyText : '请选择',
			store : ismainStore,
			mode : 'local',
			displayField : 'ismain',
			valueField : 'ismain',
			hiddenName : 'ismain',
			triggerAction : 'all',
			editable : false,
			maxLength : 100,
			allowBlank : false,
			anchor : '95%'
		} ]
	}
	]
});

var Om_emppositionbbar = pagesizebar(Om_emppositionstore);//定义分页
var Om_emppositiongrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 36,
	width : '100%',
	store : Om_emppositionstore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_emppositioncm,
	sm : Om_emppositionsm,
	bbar : Om_emppositionbbar,
	tbar : [{
			text : "新增",
			iconCls : 'add',
			handler : function() {
				Om_emppositiondataForm.form.reset();
				createWindow(basePath + Om_emppositionaction + "?method=insAll", "新增", Om_emppositiondataForm, Om_emppositionstore);
				Om_emppositiondataForm.getForm().setValues({Om_emppositionempid:treepanel.getSelectionModel().getSelectedNode().id,
					Om_emppositionempname:treepanel.getSelectionModel().getSelectedNode().text});
			}
		},'-',{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_emppositiongrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_emppositionaction + "?method=updAll", "修改", Om_emppositiondataForm, Om_emppositionstore);
				Om_emppositiondataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = Om_emppositiongrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_emppositionaction + "?method=delAll",selections,Om_emppositionstore,Om_emppositionkeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_emppositionaction + "?method=impAll","导入",Om_emppositionstore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_emppositionaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(Om_emppositiongrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_emppositiongrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_emppositionkeycolumn.length;i++){
					fid += selections[0].data[Om_emppositionkeycolumn[i]] + ","
				}
				commonAttach(fid, Om_emppositionclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'query'+Om_emppositionaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_emppositionaction).getValue()) {
							Om_emppositionstore.load();
						} else {
							Om_emppositionstore.load({
								params : {
									query : Ext.getCmp("query"+Om_emppositionaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_emppositiongrid.region = 'center';
Om_emppositionstore.load();//加载数据
Om_emppositionstore.on("beforeload",function(){ 
	Om_emppositionstore.baseParams = {
			query : Ext.getCmp("query"+Om_emppositionaction).getValue()
	}; 
});
var empposition = new Ext.TabPanel({
	activeTab : 0,
	items : [ {
		title : '人员查询',
		items : Om_employeegrid
	}, {
		title : '人岗查询',
		items : Om_emppositiongrid
	} ]
})
