function selectCompany() {
var Companyclassify = "经销商";
	var Companytitle = "当前位置:业务管理》" + Companyclassify;
	var Companyaction = "CompanyAction.do";
	var Companyfields = ['companyid'
	        			    ,'companycode' 
	        			    ,'username' 
	        			    ,'companyphone' 
	        			    ,'companyshop' 
	        			    ,'companycity' 
	        			    ,'companyaddress' 
	        			    ,'companydetail' 
	        			    ,'companystatue' 
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			    ,'cityid' 
	        			    ,'citycode' 
	        			    ,'cityname' 
	        			    ,'cityparent' 
	        			    ,'citydetail' 
	        			    ,'citystatue' 
	        			      ];// 全部字段
	var Companykeycolumn = [ 'companyid' ];// 主键
	var Companystore = dataStore(Companyfields, basePath + "CompanyviewAction.do" + "?method=selQuery");// 定义Companystore
	var Companysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Companycm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Companysm, {// 改
			header : '经销商ID',
			dataIndex : 'companyid',
			hidden : true
		}
		, {
			header : '城市',
			dataIndex : 'cityname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '编码',
			dataIndex : 'companycode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '姓名',
			dataIndex : 'username',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '手机',
			dataIndex : 'companyphone',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '店铺',
			dataIndex : 'companyshop',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '城市和县ID',
			dataIndex : 'companycity',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '街道地址',
			dataIndex : 'companyaddress',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'companydetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'companystatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '账号',
			dataIndex : 'loginname',
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
			header : '修改时间',
			dataIndex : 'updtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	
	var Companybbar = pagesizebar(Companystore);//定义分页
	var Companygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Companystore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Companycm,
		sm : Companysm,
		bbar : Companybbar,
		tbar : [{
				xtype : 'textfield',
				id : 'query'+Companyaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Companyaction).getValue()) {
								Companystore.load();
							} else {
								Companystore.load({
									params : {
										query : Ext.getCmp("query"+Companyaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Companygrid.region = 'center';
	Companystore.load();//加载数据
	Companystore.on("beforeload",function(){ 
		Companystore.baseParams = {
				query : Ext.getCmp("query"+Companyaction).getValue()
		}; 
	});
	
	var selectgridWindow = new Ext.Window({
		title : Companytitle,
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
		items : Companygrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Companygrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Empempcompanyname').setValue(selectRows[0].get("companyname"));
							Ext.getCmp('Empempcompany').setValue(selectRows[0].get("companyid"));
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