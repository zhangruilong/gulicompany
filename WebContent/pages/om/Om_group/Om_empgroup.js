var Om_empgroupclassify = "人员工作组对应关系";
var Om_empgrouptitle = "当前位置:系统管理》" + Om_empgroupclassify;
var Om_empgroupaction = "Om_empgroupAction.do";
var Om_empgroupfields = ['groempid'
        			    ,'groupid' 
        			    ,'empid' 
        			    ,'groupduty' 
        			    ,'remark'
        			    ,'empcode' 
        			    ,'empname' 
        			    ,'gender' 
        			    ,'empstatus' 
        			      ];// 全部字段
var Om_empgroupkeycolumn = [ 'groempid' ];// 主键
var Om_empgroupstore = dataStore(Om_empgroupfields, basePath + "Om_groupempviewAction.do?method=selQuery");// 定义Om_empgroupstore
var Om_empgroupsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_empgroupcm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_empgroupsm, {// 改
		header : '工作组人员ID',
		dataIndex : 'groempid',
		hidden : true
	}
	, {
		header : '工作组编号',
		dataIndex : 'groupid',
		align : 'center',
		width : 80,
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
		header : '工作组职务',
		dataIndex : 'groupduty',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '备注',
		dataIndex : 'remark',
		align : 'center',
		width : 80,
		sortable : true
	}
	]
});
var Om_empgroupdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_empgroupdataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_empgroupgroempid',
			name : 'groempid',
			hidden : true
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '工作组编号',
			id : 'Om_empgroupgroupid',
			name : 'groupid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '人员编号',
			id : 'Om_empgroupempid',
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
			fieldLabel : '人员姓名',
			id : 'Om_empgroupempname',
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
			xtype : 'textfield',
			fieldLabel : '工作组职务',
			id : 'Om_empgroupgroupduty',
			name : 'groupduty',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '备注',
			id : 'Om_empgroupremark',
			name : 'remark',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	]
});

var Om_empgroupbbar = pagesizebar(Om_empgroupstore);//定义分页
var Om_empgroupgrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	store : Om_empgroupstore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_empgroupcm,
	sm : Om_empgroupsm,
	bbar : Om_empgroupbbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_empgroupgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_empgroupaction + "?method=updAll", "修改", Om_empgroupdataForm, Om_empgroupstore);
				Om_empgroupdataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = Om_empgroupgrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_empgroupaction + "?method=delAll",selections,Om_empgroupstore,Om_empgroupkeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_empgroupaction + "?method=impAll","导入",Om_empgroupstore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_empgroupaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(Om_empgroupgrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_empgroupgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_empgroupkeycolumn.length;i++){
					fid += selections[0].data[Om_empgroupkeycolumn[i]] + ","
				}
				commonAttach(fid, Om_empgroupclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'query'+Om_empgroupaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_empgroupaction).getValue()) {
							Om_empgroupstore.load();
						} else {
							Om_empgroupstore.load({
								params : {
									query : Ext.getCmp("query"+Om_empgroupaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_empgroupgrid.region = 'center';
Om_empgroupstore.load();//加载数据
Om_empgroupstore.on("beforeload",function(){ 
	Om_empgroupstore.baseParams = {
			query : Ext.getCmp("query"+Om_empgroupaction).getValue()
	}; 
});
