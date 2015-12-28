Ext.onReady(function() {
	var System_power_quickclassify = "快捷菜单";
	var System_power_quicktitle = "当前位置:系统管理》" + System_power_quickclassify;
	var System_power_quickaction = "System_power_quickAction.do";
	var System_power_quickviewaction = "System_power_quickviewAction.do";
	var System_power_quickfields = ['id'
	        			    ,'userid' 
	        			    ,'powerid' 
	        			    ,'code' 
	        			    ,'name' 
	        			    ,'detail' 
	        			    ,'menulevel' 
	        			    ,'entrance' 
	        			    ,'parentname'
	        			 	,'hreftarget'
	        			      ];// 全部字段
	var System_power_quickkeycolumn = [ 'id' ];// 主键
	var System_power_quickstore = dataStore(System_power_quickfields, basePath + System_power_quickviewaction+ "?method=selQuery");// 定义System_power_quickstore
	var System_power_quicksm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_power_quickcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_power_quicksm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : 'USERID',
			dataIndex : 'userid',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : 'POWERID',
			dataIndex : 'powerid',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'code',
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
			width : 200,
			sortable : true
		}
		, {
			header : '打开方式',
			dataIndex : 'hreftarget',
			sortable : true
		}
		]
	});
	
	var System_power_quickbbar = pagesizebar(System_power_quickstore);//定义分页
	var System_power_quickgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_power_quicktitle,
		store : System_power_quickstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_power_quickcm,
		sm : System_power_quicksm,
		bbar : System_power_quickbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					selectSystem_power(System_power_quickstore);
				}
			},'-',{
				text : "移除",
				iconCls : 'delete',
				handler : function() {
					var selections = System_power_quickgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + System_power_quickaction+ "?method=delAll",selections,System_power_quickstore,System_power_quickkeycolumn);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+System_power_quickaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_power_quickaction).getValue()) {
								System_power_quickstore.load();
							} else {
								System_power_quickstore.load({
									params : {
										query : Ext.getCmp("query"+System_power_quickaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_power_quickgrid.region = 'center';
	System_power_quickstore.load();//加载数据
	System_power_quickstore.on("beforeload",function(){ 
		System_power_quickstore.baseParams = {
				query : Ext.getCmp("query"+System_power_quickaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ System_power_quickgrid ]
	});
})
