function selectCity() {
	var Cityclassify = "城市和县及街道";
	var Citytitle = "当前位置:业务管理》" + Cityclassify;
	var Cityaction = "CityAction.do";
	var Cityfields = ['cityid'
	        			    ,'citycode' 
	        			    ,'cityname' 
	        			    ,'cityparent' 
	        			    ,'citydetail' 
	        			    ,'citystatue' 
	        			      ];// 全部字段
	var Citykeycolumn = [ 'cityid' ];// 主键
	var Citystore = dataStore(Cityfields, basePath + Cityaction + "?method=selQuery");// 定义Citystore
	var Citysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Citycm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Citysm, {// 改
			header : '城市ID',
			dataIndex : 'cityid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'citycode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'cityname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '父类',
			dataIndex : 'cityparent',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '描述',
			dataIndex : 'citydetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'citystatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var Citybbar = pagesizebar(Citystore);//定义分页
	var Citygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Citystore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Citycm,
		sm : Citysm,
		bbar : Citybbar,
		tbar : [{
				xtype : 'textfield',
				id : 'query'+Cityaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Cityaction).getValue()) {
								Citystore.load();
							} else {
								Citystore.load({
									params : {
										query : Ext.getCmp("query"+Cityaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Citygrid.region = 'center';
	Citystore.load();//加载数据
	Citystore.on("beforeload",function(){ 
		Citystore.baseParams = {
				query : Ext.getCmp("query"+Cityaction).getValue()
		}; 
	});
	var selectgridWindow = new Ext.Window({
		title : Citytitle,
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
		items : Citygrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Citygrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Companycompanycityname').setValue(selectRows[0].get("cityname"));
							Ext.getCmp('Companycompanycity').setValue(selectRows[0].get("cityid"));
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