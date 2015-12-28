function selectSystem_power(store) {
	var System_powerclassify = "菜单";
	var System_powertitle = "当前位置:系统管理》" + System_powerclassify;
	var System_poweraction = "System_menuAction.do";
	var System_powerfields = ['id'
	        			    ,'code' 
	        			    ,'name' 
	        			    ,'detail' 
	        			    ,'parentname' 
	        			    ,'menulevel' 
	        			    ,'entrance' 
	        			    ,'hreftarget' 
	        			      ];// 全部字段
	var System_powerkeycolumn = [ 'id' ];// 主键
	var System_powerstore = dataStore(System_powerfields, basePath + System_poweraction + "?method=selMenuRemoveQuick");// 定义System_powerstore
	var System_powersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_powercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_powersm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'code',
			width : 130,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'name',
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'detail',
			sortable : true
		}
		, {
			header : '父节点',
			dataIndex : 'parentname',
			sortable : true
		}
		, {
			header : '类型',
			dataIndex : 'menulevel',
			sortable : true
		}
		, {
			header : '入口',
			dataIndex : 'entrance',
			sortable : true
		}
		, {
			header : '打开方式',
			dataIndex : 'hreftarget',
			sortable : true
		}
		]
	});
	
	var System_powergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : System_powerstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_powercm,
		sm : System_powersm,
		tbar : [{
			xtype : 'textfield',
			id : 'query',
			name : 'query',
			emptyText : '模糊匹配',
			enableKeyEvents : true,
			width : 100,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query").getValue()) {
							System_powerstore.load();
						} else {
							System_powerstore.load({
								params : {
									query : Ext.getCmp("query").getValue()
								}
							});
						}
					}
				}
			}
		}]
	});
	System_powerstore.load();//加载数据
	System_powerstore.on("beforeload",function(){ 
		System_powerstore.baseParams = {
				query : Ext.getCmp("query").getValue()
		}; 
	});
	var selectgridWindow = new Ext.Window({
		title : System_powertitle,
		layout : 'fit', // 设置窗口布局模式
		width : 820, // 窗口宽度
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
		pageX : document.body.clientWidth / 2 - 820 / 2, // 页面定位X坐标
		items : System_powergrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selections = System_powergrid.getSelectionModel().getSelections();
							if (Ext.isEmpty(selections)) {
								Ext.Msg.alert('提示', '未选中任何记录！');
								return;
							}
							Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要将当前选择的条目加入快捷菜单？', function(btn, text) {
								if (btn == 'yes') {
									var ids = '[';
									for (var i = 0; i < selections.length; i++) {
										ids += "{'powerid':'" + selections[i].data['id'] + "'},";
									};
									Ext.Ajax.request({
										url : basePath + "System_power_quickAction.do?method=insAll",
										method : 'POST',
										params : {
											json : ids.substr(0, ids.length - 1) + "]"
										},
										success : function(response) {
											var resp = Ext.decode(response.responseText); 
											Ext.Msg.alert('提示', resp.msg, function(){
												store.reload();
												selectgridWindow.close();
											});
										},
										failure : function(response) {
											Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
										}
									});
								}
							})
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