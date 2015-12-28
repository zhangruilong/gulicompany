var Om_organizationclassify = "机构信息表";
var Om_organizationtitle = "当前位置:系统管理》" + Om_organizationclassify;
var Om_organizationaction = "Om_organizationAction.do";
var Om_organizationfields = ['orgid'
        			    ,'orgcode' 
        			    ,'orgname' 
        			    ,'orglevel' 
        			    ,'orgdegree' 
        			    ,'parentorgid' 
        			    ,'orgseq' 
        			    ,'orgtype' 
        			    ,'orgaddr' 
        			    ,'zipcode' 
        			    ,'manaposition' 
        			    ,'managerid' 
        			    ,'orgmanager' 
        			    ,'linkman' 
        			    ,'linktel' 
        			    ,'email' 
        			    ,'weburl' 
        			    ,'startdate' 
        			    ,'enddate' 
        			    ,'status' 
        			    ,'area' 
        			    ,'createtime' 
        			    ,'lastupdate' 
        			    ,'updator' 
        			    ,'sortno' 
        			    ,'isleaf' 
        			    ,'subcount' 
        			    ,'remark' 
        			      ];// 全部字段
var Om_organizationkeycolumn = [ 'orgid' ];// 主键
var Om_organizationstore = dataStore(Om_organizationfields, basePath + Om_organizationaction + "?method=selQuery");// 定义Om_organizationstore
var Om_organizationsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_organizationcm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_organizationsm, {// 改
		header : '机构编号',
		dataIndex : 'orgid',
		hidden : true
	}
	, {
		header : '机构代码',
		dataIndex : 'orgcode',
		align : 'center',
		width : 80,
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
		header : '机构等级',
		dataIndex : 'orgdegree',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '机构序列',
		dataIndex : 'orgseq',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '机构状态',
		dataIndex : 'status',
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
var statusStore = new Ext.data.ArrayStore({//
	fields:["status"],
	data:[["启用"],["禁用"]]
});
var Om_organizationdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_organizationdataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_organizationorgid',
			name : 'orgid',
			hidden : true
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '机构代码',
			id : 'Om_organizationorgcode',
			name : 'orgcode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '机构名称',
			id : 'Om_organizationorgname',
			name : 'orgname',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '机构等级',
			id : 'Om_organizationorgdegree',
			name : 'orgdegree',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '父机构编号',
			id : 'Om_organizationparentorgid',
			name : 'parentorgid',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '机构序列',
			id : 'Om_organizationorgseq',
			name : 'orgseq',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '机构状态',
			id : 'Om_organizationstatus',
			name : 'status',
			emptyText : '请选择',
			store : statusStore,
			mode : 'local',
			displayField : 'status',
			valueField : 'status',
			hiddenName : 'status',
			triggerAction : 'all',
			editable : false,
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
			id : 'Om_organizationremark',
			name : 'remark',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	]
});

var Om_organizationbbar = pagesizebar(Om_organizationstore);//定义分页
var Om_organizationgrid = new Ext.grid.GridPanel({
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	title : Om_organizationtitle,
	store : Om_organizationstore,
	stripeRows : true,
	frame : true,
	loadMask : {
		msg : '正在加载表格数据,请稍等...'
	},
	cm : Om_organizationcm,
	sm : Om_organizationsm,
	bbar : Om_organizationbbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_organizationgrid.getSelectionModel().getSelections();
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
				var selections = Om_organizationgrid.getSelectionModel().getSelections();
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
				commonExp(Om_organizationgrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_organizationgrid.getSelectionModel().getSelections();
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
			id : 'query'+Om_organizationaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_organizationaction).getValue()) {
							Om_organizationstore.load();
						} else {
							Om_organizationstore.load({
								params : {
									query : Ext.getCmp("query"+Om_organizationaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_organizationgrid.region = 'center';
Om_organizationstore.load();//加载数据
Om_organizationstore.on("beforeload",function(){ 
	Om_organizationstore.baseParams = {
			query : Ext.getCmp("query"+Om_organizationaction).getValue()
	}; 
});
