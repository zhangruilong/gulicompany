var customertypeStore = new Ext.data.ArrayStore({//状态下拉
    	fields:["name","value"],
    	data:[["餐饮客户","3"],["商超客户","2"],["组织单位客户","1"]]
    });
var customerlevelStore = new Ext.data.ArrayStore({//状态下拉
	fields:["value"],
	data:[["3"],["2"],["1"]]
});
var Ccustomerviewbbar;
Ext.onReady(function() {
	var Ccustomerviewclassify = classify;
	var Ccustomerviewtitle = "当前位置:客户管理》" + Ccustomerviewclassify;
	var Ccustomerviewaction = "CPCcustomerviewAction.do";
	var Ccustomerviewfields = ['ccustomerid'
	        			    ,'ccustomercompany' 
	        			    ,'ccustomerdetail' 
	        			    ,'createtime' 
	        			    ,'creator' 
	        			    ,'customerid' 
	        			    ,'customercode' 
	        			    ,'customername' 
	        			    ,'customerphone' 
	        			    ,'customerpsw' 
	        			    ,'customershop' 
	        			    ,'customercity' 
	        			    ,'customerxian' 
	        			    ,'customeraddress' 
	        			    ,'customertype' 
	        			    ,'customerlevel' 
	        			    ,'openid' 
	        			    ,'customerdetail' 
	        			    ,'customerstatue' 
	        			    ,'cuscreatetime' 
	        			    ,'updtime' 
	        			      ];// 全部字段
	var Empfields = ['empid','empcompany','empcode','empdetail'];// 全部字段
	var Cityfields = ['cityid'
      			    ,'citycode' 
      			    ,'cityname' 
      			    ,'cityparent' 
      			    ,'citydetail' 
      			    ,'citystatue' 
      			      ];// 全部字段
	var Ccustomerviewkeycolumn = [ 'ccustomerid' ];// 主键
	var CcustomerviewstoreURL = basePath + Ccustomerviewaction + "?method=queryCCustomerwiew";
	var where = "&wheresql=ccustomercompany='"+comid+"'";
	if(customertype != ''){
		where += " and customertype='"+customertype+"'";		//客户类型
	}
	if(creator != ''){
		where += " and creator='"+creator+"'";		//特殊客户
	}
	var Ccustomerviewstore = dataStore(Ccustomerviewfields, CcustomerviewstoreURL+where);// 定义Ccustomerviewstore
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empstatue='启用' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	
	Ccustomerviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				wheresql : where,
				query : Ext.getCmp("queryCcustomerviewaction").getValue(),
				limit : Ccustomerviewbbar.pageSize
		};
		Ext.apply(Ccustomerviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var CcustomerviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'CcustomerviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ 
 		{
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '客户类型',
				id : 'Ccustomerviewcustomertype',
				name : 'customertype',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : customertypeStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'value',		//作为值的字段
				hiddenName : 'customertype',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
 		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '客户等级',
				id : 'Ccustomerviewccustomerdetail',
				name : 'ccustomerdetail',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : customerlevelStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'value',		//显示的字段
				valueField : 'value',		//作为值的字段
				hiddenName : 'ccustomerdetail',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
 		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户关系ID',
				id : 'Ccustomerviewccustomerid',
				name : 'ccustomerid',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '客户经理',
				id : 'Ccustomerviewcreatetime',
				name : 'createtime',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Empstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'empcode',		//显示的字段
				valueField : 'empcode',		//作为值的字段
				hiddenName : 'createtime',
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
				fieldLabel : '客户名称',
				id : 'Ccustomerviewcustomershop',
				name : 'customershop',
				maxLength : 100,
				allowBlank : false,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户ID',
				id : 'Ccustomerviewcustomerid',
				name : 'customerid',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系人',
				id : 'Ccustomerviewcustomername',
				name : 'customername',
				maxLength : 100,
				allowBlank : false,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系电话',
				id : 'Ccustomerviewcustomerphone',
				name : 'customerphone',
				maxLength : 100,
				allowBlank : false,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系地址',
				id : 'Ccustomerviewcustomeraddress',
				name : 'customeraddress',
				maxLength : 100,
				allowBlank : false,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '客户状态',
				id : 'Ccustomerviewcustomerstatue',
				name : 'customerstatue',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'name',		//作为值的字段
				hiddenName : 'customerstatue',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ /*{
				xtype : 'textfield',
				fieldLabel : '所在城市',
				id : 'Ccustomerviewcustomercity',
				name : 'customercity',
				maxLength : 100,
			}*/{
				xtype : 'combo',
				fieldLabel : '所在城市',
				id : 'Ccustomerviewcustomercity',
				name : 'customercity',			//小类名称
				emptyText : '请选择',
				store : [city],
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				value : city,
				hiddenName : 'customercity',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ /*{
				xtype : 'textfield',
				fieldLabel : '所在地区',
				id : 'Ccustomerviewcustomerxian',
				name : 'customerxian',
				maxLength : 100,
			}*/{
				xtype : 'combo',
				fieldLabel : '所在地区',
				id : 'Ccustomerviewcustomerxian',
				name : 'customerxian',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : xian,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
//				displayField : 'empcode',		//显示的字段
//				valueField : 'empcode',		//作为值的字段
				hiddenName : 'customerxian',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%'
			} ]
		}
		]
	});
	
	Ccustomerviewbbar = pagesizebar(Ccustomerviewstore);//定义分页
	var Ccustomerviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Ccustomerviewtitle,
		store : Ccustomerviewstore,
		bbar : Ccustomerviewbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		},
		{// 改
			header : '客户关系ID',
			dataIndex : 'ccustomerid',
			sortable : true,
			hidden : true
		}
		, {
			header : '客户编码',
			dataIndex : 'customercode',
			sortable : true,
			width:75,
		}
		, {
			header : '客户名称',
			dataIndex : 'customershop',
			sortable : true,
			width:150,
		}
		, {
			header : '地址',
			dataIndex : 'customeraddress',
			sortable : true, 
			width:150,
		}
		, {
			header : '联系人',
			dataIndex : 'customername',
			sortable : true, 
			width:72,
		}
		, {
			header : '联系电话',
			dataIndex : 'customerphone',
			sortable : true,
			width:109,
		}
		, {
			header : '客户类型',
			dataIndex : 'customertype',
			sortable : true,
			width : 73,
			renderer : function(value, metaData, record, rowIdx, colIdx, store, view){
				var type = value=='3'?'餐饮客户':(value=='2'?'商超客户':(value=='1'?'组织单位客户':''));
				return type;
			}, 
		}
		, {
			header : '价格层级',
			dataIndex : 'ccustomerdetail',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'ccustomerupdtime',
			sortable : true,
			width : 139, 
		}
		, {
			header : '修改人',
			dataIndex : 'ccustomerupdor',
			sortable : true, 
			width : 73,
		}
		, {
			header : '客户经理',
			dataIndex : 'createtime',
			sortable : true, 
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryCcustomerviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryCcustomerviewaction").getValue()) {
							Ccustomerviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Ccustomerviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryCcustomerviewaction").getValue()
								}
							});
						}
					}
				}
			}
		},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Ccustomerviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					CcustomerviewdataForm.form.reset();
					Ext.getCmp("Ccustomerviewccustomerid").setEditable (false);
					createTextWindow(basePath + Ccustomerviewaction + "?method=updateCustomerInfo", "修改", CcustomerviewdataForm, Ccustomerviewstore);
					CcustomerviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					CcustomerviewdataForm.form.reset();
					Ext.getCmp("Ccustomerviewccustomerid").setEditable (true);
					Ext.getCmp("Ccustomerviewcustomerstatue").setValue('启用');
					createTextWindow(basePath + Ccustomerviewaction + "?method=insSpecialCustomer", "新增", CcustomerviewdataForm, Ccustomerviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "录单",
				iconCls : 'query',
				handler : function() {
					var selections = Ccustomerviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					window.location.href="../../largeCusXiaDan.jsp?customerid="+selections[0].data["customerid"];
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "特殊商品",
				iconCls : 'select',
				handler : function() {
					var selections = Ccustomerviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					window.location.href = "../../pages/LargecuspriceGoods/LargecuspriceGoods.jsp?cusid="+
							selections[0].data["customerid"];
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "导出",
				iconCls : 'exp',
				handler : function() {
					window.location.href = basePath + Ccustomerviewaction + "?method=expCCustomerwiew&json="+queryjson+"&query="
					+Ext.getCmp("queryCcustomerviewaction").getValue()+where; 
				}
			}
		]
	});
	Ccustomerviewgrid.region = 'center';
	Ccustomerviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Ccustomerviewgrid ]
	});
})
