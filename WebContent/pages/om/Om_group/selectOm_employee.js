function selectOm_employee() {
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
				xtype : 'textfield',
				id : 'queryOm_employee',
				name : 'queryOm_employee',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("queryOm_employee").getValue()) {
								Om_employeestore.load();
							} else {
								Om_employeestore.load({
									params : {
										query : Ext.getCmp("queryOm_employee").getValue()
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
				query : Ext.getCmp("queryOm_employee").getValue()
		}; 
	});
	var selectgridWindow = new Ext.Window({
		title : Om_employeetitle,
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight - 4, // 窗口高度
		modal : true,
		//closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : Om_employeegrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Om_employeegrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Om_empgroupempname').setValue(selectRows[0].get("empname"));
							Ext.getCmp('Om_empgroupempid').setValue(selectRows[0].get("empid"));
							selectgridWindow.close();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							selectgridWindow.close();
						}
					}]
	});
	selectgridWindow.show();
};