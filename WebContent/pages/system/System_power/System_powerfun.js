var menulevelStore = new Ext.data.ArrayStore({//menulevel下拉
    	fields:["name"],
    	data:[["顶级菜单"],["普通菜单"],["叶子菜单"],["按钮权限"],["数据权限"]]
    });
var hreftargetStore = new Ext.data.ArrayStore({//hreftarget下拉
	fields:["name"],
	data:[["main"],["_blank"]]
});
function selectSystem_power() {
	var System_powerclassify = "权限";
	var System_powertitle = "当前位置:系统管理》" + System_powerclassify;
	var System_poweraction = "System_powerviewAction.do";
	var System_powerviewaction = "System_powerviewAction.do";
	var System_powerfields = ['id'
	        			    ,'code' 
	        			    ,'name' 
	        			    ,'detail' 
	        			    ,'parentid' 
	        			    ,'menulevel' 
	        			    ,'entrance' 
	        			    ,'menuorder' 
	        			      ];// 全部字段
	var System_powerkeycolumn = [ 'id' ];// 主键
	var System_powerstore = dataStore(System_powerfields, basePath + System_powerviewaction + "?method=selQuery");// 定义System_powerstore
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
		]
	});
	var System_powerbbar = pagesizebar(System_powerstore);//定义分页
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
		bbar : System_powerbbar,
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
		items : System_powergrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = System_powergrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('System_powerparentname').setValue(selectRows[0].get("name"));
							Ext.getCmp('System_powerparentid').setValue(selectRows[0].get("id"));
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
