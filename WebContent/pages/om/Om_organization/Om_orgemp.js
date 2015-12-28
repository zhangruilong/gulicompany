var Om_organizationsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var orgbbar = pagesizebar(Om_organizationstore);//定义分页
var orggrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 36,
	width : '100%',
	store : Om_organizationstore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_organizationcm,
	sm : Om_organizationsm,
	bbar : orgbbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = orggrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_organizationaction + "?method=updAll", "修改", Om_organizationdataForm, Om_organizationstore);
				Om_organizationdataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = orggrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_organizationaction + "?method=delAll",selections,Om_organizationstore,Om_organizationkeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_organizationaction + "?method=impAll","导入",Om_organizationstore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_organizationaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(orggrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = orggrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_organizationkeycolumn.length;i++){
					fid += selections[0].data[Om_organizationkeycolumn[i]] + ","
				}
				commonAttach(fid, Om_organizationclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'orgquery',
			name : 'orgquery',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("orgquery").getValue()) {
							Om_organizationstore.load();
						} else {
							Om_organizationstore.load({
								params : {
									query : Ext.getCmp("orgquery").getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
orggrid.region = 'center';
Om_organizationstore.load();//加载数据
Om_organizationstore.on("beforeload",function(){ 
	Om_organizationstore.baseParams = {
			query : Ext.getCmp("orgquery").getValue()
	}; 
});
var Om_employeesm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var orgbbar = pagesizebar(Om_employeestore);//定义分页
var empgrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 36,
	width : '100%',
	store : Om_employeestore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_employeecm,
	sm : Om_employeesm,
	bbar : orgbbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = empgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				Om_employeedataForm.form.loadRecord(selections[0]);
				createWindow(basePath + Om_employeeaction + "?method=updAll", "修改", Om_employeedataForm, Om_employeestore);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = empgrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_employeeaction + "?method=delAll",selections,Om_employeestore,Om_employeekeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_employeeaction + "?method=impAll","导入",Om_employeestore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_employeeaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(empgrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = empgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_employeekeycolumn.length;i++){
					fid += selections[0].data[Om_employeekeycolumn[i]] + ","
				}
				commonAttach(fid, Om_employeeclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'empquery',
			name : 'empquery',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("empquery").getValue()) {
							Om_employeestore.load();
						} else {
							Om_employeestore.load({
								params : {
									query : Ext.getCmp("empquery").getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
empgrid.region = 'center';
Om_employeestore.load();//加载数据
Om_employeestore.on("beforeload",function(){ 
	Om_employeestore.baseParams = {
			query : Ext.getCmp("empquery").getValue()
	}; 
});
var orgemp = new Ext.TabPanel({
	activeTab : 0,
	items : [ {
		title : '机构查询',
		items : orggrid
	}, {
		title : '人员查询',
		items : empgrid
	} ]
})