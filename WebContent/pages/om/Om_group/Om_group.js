var Om_groupclassify = "工作组";
var Om_grouptitle = "当前位置:系统管理》" + Om_groupclassify;
var Om_groupaction = "Om_groupAction.do";
var Om_groupfields = ['groupid'
        			    ,'groupcode' 
        			    ,'groupname' 
        			    ,'grouplevel' 
        			    ,'groupdesc' 
        			    ,'grouptype' 
        			    ,'groupseq' 
        			    ,'startdate' 
        			    ,'enddate' 
        			    ,'groupstatus' 
        			    ,'manager' 
        			    ,'orgid' 
        			    ,'parentgroupid' 
        			    ,'createtime' 
        			    ,'lastupdate' 
        			    ,'updator' 
        			    ,'isleaf' 
        			    ,'subcount' 
        			      ];// 全部字段
var Om_groupkeycolumn = [ 'groupid' ];// 主键
var Om_groupstore = dataStore(Om_groupfields, basePath + Om_groupaction + "?method=selQuery");// 定义Om_groupstore
var Om_groupsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_groupcm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_groupsm, {// 改
		header : '工作组编号',
		dataIndex : 'groupid',
		hidden : true
	}
	, {
		header : '工作组代码',
		dataIndex : 'groupcode',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组名称',
		dataIndex : 'groupname',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组级别',
		dataIndex : 'grouplevel',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组描述',
		dataIndex : 'groupdesc',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组类型',
		dataIndex : 'grouptype',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组路径序列',
		dataIndex : 'groupseq',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '开始日期',
		dataIndex : 'startdate',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '截止日期',
		dataIndex : 'enddate',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '工作组状态',
		dataIndex : 'groupstatus',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '负责人',
		dataIndex : 'manager',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '隶属机构',
		dataIndex : 'orgid',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '父工作组编号',
		dataIndex : 'parentgroupid',
		align : 'center',
		width : 80,
		hidden : true
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
	]
});
var Om_groupdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_groupdataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_groupgroupid',
			name : 'groupid',
			hidden : true
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组代码',
			id : 'Om_groupgroupcode',
			name : 'groupcode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组名称',
			id : 'Om_groupgroupname',
			name : 'groupname',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'numberfield',
			fieldLabel : '工作组级别',
			id : 'Om_groupgrouplevel',
			name : 'grouplevel',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组描述',
			id : 'Om_groupgroupdesc',
			name : 'groupdesc',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组类型',
			id : 'Om_groupgrouptype',
			name : 'grouptype',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组序列',
			id : 'Om_groupgroupseq',
			name : 'groupseq',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'datefield',
			fieldLabel : '开始日期',
			id : 'Om_groupstartdate',
			name : 'startdate',
			format : 'Y-m-d',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'datefield',
			fieldLabel : '截止日期',
			id : 'Om_groupenddate',
			name : 'enddate',
			format : 'Y-m-d',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '工作组状态',
			id : 'Om_groupgroupstatus',
			name : 'groupstatus',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '负责人',
			id : 'Om_groupmanager',
			name : 'manager',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '隶属机构',
			id : 'Om_grouporgid',
			name : 'orgid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '父工作组编号',
			id : 'Om_groupparentgroupid',
			name : 'parentgroupid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	]
});

var Om_groupbbar = pagesizebar(Om_groupstore);//定义分页
var Om_groupgrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	store : Om_groupstore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_groupcm,
	sm : Om_groupsm,
	bbar : Om_groupbbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_groupgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_groupaction + "?method=updAll", "修改", Om_groupdataForm, Om_groupstore);
				Om_groupdataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = Om_groupgrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_groupaction + "?method=delAll",selections,Om_groupstore,Om_groupkeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_groupaction + "?method=impAll","导入",Om_groupstore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_groupaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(Om_groupgrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_groupgrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_groupkeycolumn.length;i++){
					fid += selections[0].data[Om_groupkeycolumn[i]] + ","
				}
				commonAttach(fid, Om_groupclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'query'+Om_groupaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_groupaction).getValue()) {
							Om_groupstore.load();
						} else {
							Om_groupstore.load({
								params : {
									query : Ext.getCmp("query"+Om_groupaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_groupgrid.region = 'center';
Om_groupstore.load();//加载数据
Om_groupstore.on("beforeload",function(){ 
	Om_groupstore.baseParams = {
			query : Ext.getCmp("query"+Om_groupaction).getValue()
	}; 
});
