var Om_employeeclassify = "人员";
var Om_employeetitle = "当前位置:系统管理》" + Om_employeeclassify;
var Om_employeeaction = "Om_employeeAction.do";
var Om_employeefields = ['empid'
        			    ,'empcode' 
        			    ,'operatorid' 
        			    ,'loginname' 
        			    ,'empname' 
        			    ,'realname' 
        			    ,'gender' 
        			    ,'birthdate' 
        			    ,'position' 
        			    ,'empstatus' 
        			    ,'cardtype' 
        			    ,'cardno' 
        			    ,'indate' 
        			    ,'outdate' 
        			    ,'otel' 
        			    ,'oaddress' 
        			    ,'ozipcode' 
        			    ,'oemail' 
        			    ,'faxno' 
        			    ,'mobileno' 
        			    ,'msn' 
        			    ,'htel' 
        			    ,'haddress' 
        			    ,'hzipcode' 
        			    ,'pemail' 
        			    ,'party' 
        			    ,'degree' 
        			    ,'major' 
        			    ,'specialty' 
        			    ,'workexp' 
        			    ,'regdate' 
        			    ,'createtime' 
        			    ,'lastmodytime' 
        			    ,'orgidlist' 
        			    ,'orgid' 
        			    ,'remark' 
        			      ];// 全部字段
var Om_employeekeycolumn = [ 'empid' ];// 主键
var Om_employeestore = dataStore(Om_employeefields, basePath + Om_employeeaction + "?method=selQuery");// 定义Om_employeestore
var Om_employeesm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
var Om_employeecm = new Ext.grid.ColumnModel({// 定义columnModel
	columns : [ new Ext.grid.RowNumberer(), Om_employeesm, {// 改
		header : '人员编号',
		dataIndex : 'empid',
		hidden : true
	}
	, {
		header : '人员代码',
		dataIndex : 'empcode',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '操作员登录号',
		dataIndex : 'loginname',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '人员姓名',
		dataIndex : 'empname',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '性别',
		dataIndex : 'gender',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '出生日期',
		dataIndex : 'birthdate',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '状态',
		dataIndex : 'empstatus',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '证件类型',
		dataIndex : 'cardtype',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '证件号码',
		dataIndex : 'cardno',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '入职日期',
		dataIndex : 'indate',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '离职日期',
		dataIndex : 'outdate',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '办公电话',
		dataIndex : 'otel',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '办公地址',
		dataIndex : 'oaddress',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '办公邮编',
		dataIndex : 'ozipcode',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '办公邮件',
		dataIndex : 'oemail',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '传真号码',
		dataIndex : 'faxno',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '手机号码',
		dataIndex : 'mobileno',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : 'MSN号码',
		dataIndex : 'msn',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '家庭电话',
		dataIndex : 'htel',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '家庭地址',
		dataIndex : 'haddress',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '家庭邮编',
		dataIndex : 'hzipcode',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '私人电子邮箱',
		dataIndex : 'pemail',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '政治面貌',
		dataIndex : 'party',
		align : 'center',
		width : 80,
		sortable : true
	}
	, {
		header : '职级',
		dataIndex : 'degree',
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
		header : '最新更新时间',
		dataIndex : 'lastmodytime',
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
var empstatusStore = new Ext.data.ArrayStore({//
	fields:["empstatus"],
	data:[["在岗"],["离职"],["待岗"],["退休"]]
});
var genderStore = new Ext.data.ArrayStore({//
	fields:["gender"],
	data:[["男"],["女"]]
});
var cardtypeStore = new Ext.data.ArrayStore({//
	fields:["cardtype"],
	data:[["身份证"],["护照"],["暂住证"],["军官证"],["学生证"]]
});
var partyStore = new Ext.data.ArrayStore({//
	fields:["party"],
	data:[["团员"],["党员"],["群众"]]
});
var Om_employeedataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
	id:'Om_employeedataForm',
	labelAlign : 'right',
	frame : true,
	layout : 'column',
	items : [ {
		items : [ {
			xtype : 'textfield',
			id : 'Om_employeeempid',
			name : 'empid',
			hidden : true
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '人员代码',
			id : 'Om_employeeempcode',
			name : 'empcode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '人员姓名',
			id : 'Om_employeeempname',
			name : 'empname',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '操作员登录号',
			id : 'Om_employeeloginname',
			name : 'loginname',
			emptyText  : '无需登录系统则为空',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '性别',
			id : 'Om_employeegender',
			name : 'gender',
			emptyText : '请选择',
			store : genderStore,
			mode : 'local',
			displayField : 'gender',
			valueField : 'gender',
			hiddenName : 'gender',
			triggerAction : 'all',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'datefield',
			fieldLabel : '出生日期',
			id : 'Om_employeebirthdate',
			name : 'birthdate',
			maxLength : 100,
			format : 'Y-m-d',
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '状态',
			id : 'Om_employeeempstatus',
			name : 'empstatus',
			emptyText : '请选择',
			store : empstatusStore,
			mode : 'local',
			displayField : 'empstatus',
			valueField : 'empstatus',
			hiddenName : 'empstatus',
			triggerAction : 'all',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '证件类型',
			id : 'Om_employeecardtype',
			name : 'cardtype',
			emptyText : '请选择',
			store : cardtypeStore,
			mode : 'local',
			displayField : 'cardtype',
			valueField : 'cardtype',
			hiddenName : 'cardtype',
			triggerAction : 'all',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '证件号码',
			id : 'Om_employeecardno',
			name : 'cardno',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'datefield',
			fieldLabel : '入职日期',
			id : 'Om_employeeindate',
			name : 'indate',
			maxLength : 100,
			format : 'Y-m-d',
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'datefield',
			fieldLabel : '离职日期',
			id : 'Om_employeeoutdate',
			name : 'outdate',
			maxLength : 100,
			format : 'Y-m-d',
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '办公电话',
			id : 'Om_employeeotel',
			name : 'otel',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '办公地址',
			id : 'Om_employeeoaddress',
			name : 'oaddress',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '办公邮编',
			id : 'Om_employeeozipcode',
			name : 'ozipcode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '办公邮件',
			id : 'Om_employeeoemail',
			name : 'oemail',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '传真号码',
			id : 'Om_employeefaxno',
			name : 'faxno',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '手机号码',
			id : 'Om_employeemobileno',
			name : 'mobileno',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'MSN号码',
			id : 'Om_employeemsn',
			name : 'msn',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '家庭电话',
			id : 'Om_employeehtel',
			name : 'htel',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : 1,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '家庭地址',
			id : 'Om_employeehaddress',
			name : 'haddress',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '家庭邮编',
			id : 'Om_employeehzipcode',
			name : 'hzipcode',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'textfield',
			fieldLabel : '私人电子邮箱',
			id : 'Om_employeepemail',
			name : 'pemail',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'combo',
			fieldLabel : '政治面貌',
			id : 'Om_employeeparty',
			name : 'party',
			emptyText : '请选择',
			store : partyStore,
			mode : 'local',
			displayField : 'party',
			valueField : 'party',
			hiddenName : 'party',
			triggerAction : 'all',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'numberfield',
			fieldLabel : '职级',
			id : 'Om_employeedegree',
			name : 'degree',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '基本岗位',
			id : 'Om_employeeposition',
			name : 'position',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	, {
		columnWidth : .5,
		layout : 'form',
		items : [ {
			xtype : 'hidden',
			fieldLabel : '主机构编号',
			id : 'Om_employeeorgid',
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
			fieldLabel : '备注',
			id : 'Om_employeeremark',
			name : 'remark',
			maxLength : 100,
			anchor : '95%'
		} ]
	}
	]
});

var Om_employeebbar = pagesizebar(Om_employeestore);//定义分页
var Om_employeegrid = new Ext.grid.GridPanel({
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
	bbar : Om_employeebbar,
	tbar : [{
			text : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Om_employeegrid.getSelectionModel().getSelections();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
					});
					return;
				}
				createWindow(basePath + Om_employeeaction + "?method=updAll", "修改", Om_employeedataForm, Om_employeestore);
				Om_employeedataForm.form.loadRecord(selections[0]);
			}
		},'-',{
			text : "删除",
			iconCls : 'delete',
			handler : function() {
				var selections = Om_employeegrid.getSelectionModel().getSelections();
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
				commonExp(Om_employeegrid);
			}
		},'-',{
			text : "附件",
			iconCls : 'attach',
			handler : function() {
				var selections = Om_employeegrid.getSelectionModel().getSelections();
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
			id : 'query'+Om_employeeaction,
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query"+Om_employeeaction).getValue()) {
							Om_employeestore.load();
						} else {
							Om_employeestore.load({
								params : {
									query : Ext.getCmp("query"+Om_employeeaction).getValue()
								}
							});
						}
					}
				}
			}
		}
	]
});
Om_employeegrid.region = 'center';
Om_employeestore.load();//加载数据
Om_employeestore.on("beforeload",function(){ 
	Om_employeestore.baseParams = {
			query : Ext.getCmp("query"+Om_employeeaction).getValue()
	}; 
});
