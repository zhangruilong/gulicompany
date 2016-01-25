function selectGoodsclass() {	
	var Goodsclassclassify = "大小类";
	var Goodsclasstitle = "当前位置:业务管理》" + Goodsclassclassify;
	var Goodsclassaction = "GoodsclassAction.do";
	var Goodsclassfields = ['goodsclassid'
	        			    ,'goodsclasscode' 
	        			    ,'goodsclassname' 
	        			    ,'goodsclassparent' 
	        			    ,'goodsclassdetail' 
	        			    ,'goodsclassstatue' 
	        			      ];// 全部字段
	var Goodsclasskeycolumn = [ 'goodsclassid' ];// 主键
	var Goodsclassstore = dataStore(Goodsclassfields, basePath + Goodsclassaction + "?method=selQuery");// 定义Goodsclassstore
	var Goodsclasssm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Goodsclasscm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Goodsclasssm, {// 改
			header : '大小类ID',
			dataIndex : 'goodsclassid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'goodsclasscode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'goodsclassname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '父类',
			dataIndex : 'goodsclassparent',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '城市',
			dataIndex : 'goodsclassdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'goodsclassstatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	
	var Goodsclassbbar = pagesizebar(Goodsclassstore);//定义分页
	var Goodsclassgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		
		store : Goodsclassstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Goodsclasscm,
		sm : Goodsclasssm,
		bbar : Goodsclassbbar,
		tbar : [{
				xtype : 'textfield',
				id : 'query'+Goodsclassaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Goodsclassaction).getValue()) {
								Goodsclassstore.load();
							} else {
								Goodsclassstore.load({
									params : {
										query : Ext.getCmp("query"+Goodsclassaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Goodsclassgrid.region = 'center';
	Goodsclassstore.load();//加载数据
	Goodsclassstore.on("beforeload",function(){ 
		Goodsclassstore.baseParams = {
				query : Ext.getCmp("query"+Goodsclassaction).getValue()
		}; 
	});
	var selectgridWindow = new Ext.Window({
		title : Goodsclasstitle,
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
		items : Goodsclassgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Goodsclassgrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Scantscantclassname').setValue(selectRows[0].get("goodsclassname"));
							Ext.getCmp('Scantscantclass').setValue(selectRows[0].get("goodsclassid"));
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
}