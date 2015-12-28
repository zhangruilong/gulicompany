var Om_dutyclassify = "职务定义表";
var Om_dutytitle = "当前位置:系统管理》" + Om_dutyclassify;
var Om_dutyaction = "Om_dutyAction.do";
var Om_dutyfields = ['dutyid'
        			    ,'dutycode' 
        			    ,'dutyname' 
        			    ,'parentduty' 
        			    ,'dutylevel' 
        			    ,'dutyseq' 
        			    ,'dutytype' 
        			    ,'isleaf' 
        			    ,'subcount' 
        			    ,'remark' 
        			      ];// 全部字段
var Om_dutykeycolumn = [ 'dutyid' ];// 主键
var Om_dutystore = dataStore(Om_dutyfields, basePath + Om_dutyaction + "?method=selQuery");// 定义Om_dutystore
var Om_dutysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_dutycm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_dutysm, {// 改
		header : '职务编号',
		dataIndex : 'dutyid',
		hidden : true
	}
	, {
		header : '职务代码',
		dataIndex : 'dutycode',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '职务名称',
		dataIndex : 'dutyname',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '上级职务编号',
		dataIndex : 'parentduty',
		align : 'center',
		width : 80,
		hidden : true
	}
	, {
		header : '职务层次',
		dataIndex : 'dutylevel',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '职务序列号',
		dataIndex : 'dutyseq',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '职务套别',
		dataIndex : 'dutytype',
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
var Om_dutydataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_dutydataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_dutydutyid',
			name : 'dutyid',
			hidden : true
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '职务代码',
			id : 'Om_dutydutycode',
			name : 'dutycode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '职务名称',
			id : 'Om_dutydutyname',
			name : 'dutyname',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '上级职务编号',
			id : 'Om_dutyparentduty',
			name : 'parentduty',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '职务层次',
			id : 'Om_dutydutylevel',
			name : 'dutylevel',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '职务序列号',
			id : 'Om_dutydutyseq',
			name : 'dutyseq',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '职务套别',
			id : 'Om_dutydutytype',
			name : 'dutytype',
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
			id : 'Om_dutyremark',
			name : 'remark',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	]
});

var Om_dutybbar = pagesizebar(Om_dutystore);//定义分页
var Om_dutygrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	title : Om_dutytitle,
	store : Om_dutystore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_dutycm,
	sm : Om_dutysm,
	bbar : Om_dutybbar,
	tbar : [{
			text : "新增",
			iconCls : 'add',
			handler : function() {
				Om_dutydataForm.form.reset();
				createWindow(basePath + Om_dutyaction + "?method=insAll", "新增", Om_dutydataForm, Om_dutystore);
			}
		},'-',{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_dutygrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_dutyaction + "?method=updAll", "修改", Om_dutydataForm, Om_dutystore);
				Om_dutydataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = Om_dutygrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + Om_dutyaction + "?method=delAll",selections,Om_dutystore,Om_dutykeycolumn);
			}
		},'-',{
			text : "导入",
			iconCls : 'imp',
			handler : function() {
				commonImp(basePath + Om_dutyaction + "?method=impAll","导入",Om_dutystore);
			}
		},'-',{
			text : "后台导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						window.location.href = basePath + Om_dutyaction + "?method=expAll"; 
					}
				});
			}
		},'-',{
			text : "前台导出",
			iconCls : 'exp',
			handler : function() {
				commonExp(Om_dutygrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_dutygrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
					});
					return;
				}
				var fid = '';
				for (var i=0;i<Om_dutykeycolumn.length;i++){
					fid += selections[0].data[Om_dutykeycolumn[i]] + ","
				}
				commonAttach(fid, Om_dutyclassify);
			}
		},'->',{
			xtype : 'textfield',
			id : 'query'+Om_dutyaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_dutyaction).getValue()) {
							Om_dutystore.load();
						} else {
							Om_dutystore.load({
								params : {
									query : Ext.getCmp("query"+Om_dutyaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_dutygrid.region = 'center';
Om_dutystore.load();//加载数据
Om_dutystore.on("beforeload",function(){ 
	Om_dutystore.baseParams = {
			query : Ext.getCmp("query"+Om_dutyaction).getValue()
	}; 
});
