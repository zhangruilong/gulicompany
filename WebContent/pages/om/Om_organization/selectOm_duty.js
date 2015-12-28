function selectOm_duty() {
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

	var Om_dutybbar = pagesizebar(Om_dutystore);//定义分页
	var Om_dutygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
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
				xtype : 'textfield',
				id : 'queryOm_duty',
				name : 'queryOm_duty',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("queryOm_duty").getValue()) {
								Om_dutystore.load();
							} else {
								Om_dutystore.load({
									params : {
										query : Ext.getCmp("queryOm_duty").getValue()
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
				query : Ext.getCmp("queryOm_duty").getValue()
		}; 
	});
	var selectgridWindow = new Ext.Window({
		title : Om_dutytitle,
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
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
		items : Om_dutygrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Om_dutygrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Om_positiondutyname').setValue(selectRows[0].get("dutyname"));
							Ext.getCmp('Om_positiondutyid').setValue(selectRows[0].get("dutyid"));
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
