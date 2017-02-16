Ext.onReady(function() {
	var Supplierclassify = "供货商管理";
	var Suppliertitle = "当前位置:库存管理》" + Supplierclassify;
	var Supplieraction = "CPSupplierAction.do";
	var Supplierfields = ['supplierid'
	        			    ,'suppliercode' 
	        			    ,'suppliername' 
	        			    ,'suppliercontact' 
	        			    ,'supplierphone' 
	        			    ,'supplieraddress' 
	        			    ,'supplierdetail' 
	        			    ,'supplierstatue' 
	        			    ,'suppliercompany' 
	        			    ,'supplierupdtime' 
	        			    ,'supplierupdor' 
	        			    ,'suppliercretime' 
	        			    ,'suppliercreor' 
	        			      ];// 全部字段
	var Supplierkeycolumn = [ 'supplierid' ];// 主键
	var Supplierstore = dataStore(Supplierfields, basePath + Supplieraction + "?method=selAll");// 定义Supplierstore
	var SupplierdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'SupplierdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '供货商ID',
				id : 'Suppliersupplierid',
				name : 'supplierid',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Suppliersuppliercode',
				name : 'suppliercode',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '名称',
				id : 'Suppliersuppliername',
				name : 'suppliername',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系人',
				id : 'Suppliersuppliercontact',
				name : 'suppliercontact',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系电话',
				id : 'Suppliersupplierphone',
				name : 'supplierphone',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '地址',
				id : 'Suppliersupplieraddress',
				name : 'supplieraddress',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Suppliersupplierdetail',
				name : 'supplierdetail',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Suppliersupplierstatue',
				name : 'supplierstatue',
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'name',		//作为值的字段
				hiddenName : 'supplierstatue',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%',
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商ID',
				id : 'Suppliersuppliercompany',
				name : 'suppliercompany',
				value : comid,
				maxLength : 100
			} ]
		}
		]
	});
	
	var Supplierbbar = pagesizebar(Supplierstore);//定义分页
	var Suppliergrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Suppliertitle,
		store : Supplierstore,
		bbar : Supplierbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [
		{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		}
		, {// 改
			header : '供货商ID',
			dataIndex : 'supplierid',
			sortable : true, 
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'suppliercode',
			sortable : true,  
		}
		, {
			header : '名称',
			dataIndex : 'suppliername',
			sortable : true,  
		}
		, {
			header : '联系人',
			dataIndex : 'suppliercontact',
			sortable : true,  
		}
		, {
			header : '联系电话',
			dataIndex : 'supplierphone',
			sortable : true,  
		}
		, {
			header : '地址',
			dataIndex : 'supplieraddress',
			sortable : true,  
		}
		, {
			header : '描述',
			dataIndex : 'supplierdetail',
			sortable : true,  
		}
		, {
			header : '状态',
			dataIndex : 'supplierstatue',
			sortable : true,  
		}
		, {
			header : '经销商ID',
			dataIndex : 'suppliercompany',
			sortable : true,  
			hidden : true
		}
		, {
			header : '修改时间',
			dataIndex : 'supplierupdtime',
			sortable : true,  
		}
		, {
			header : '修改人',
			dataIndex : 'supplierupdor',
			sortable : true,  
		}
		, {
			header : '创建时间',
			dataIndex : 'suppliercretime',
			sortable : true,  
		}
		, {
			header : '创建人',
			dataIndex : 'suppliercreor',
			sortable : true,  
		}
		],
		tbar : [{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					SupplierdataForm.form.reset();
					Ext.getCmp("Suppliersupplierid").setEditable (true);
					createTextWindow(basePath + Supplieraction + "?method=addSupplier", "新增", SupplierdataForm, Supplierstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Suppliergrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					SupplierdataForm.form.reset();
					Ext.getCmp("Suppliersupplierid").setEditable (false);
					createTextWindow(basePath + Supplieraction + "?method=updSupplier", "修改", SupplierdataForm, Supplierstore);
					SupplierdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Suppliergrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Supplieraction + "?method=delAll",selections,Supplierstore,Supplierkeycolumn);
				}
            },'->',{
				xtype : 'textfield',
				id : 'querySupplieraction',
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("querySupplieraction").getValue()) {
								Supplierstore.load({
									params : {
										json : queryjson
									}
								});
							} else {
								Supplierstore.load({
									params : {
										json : queryjson,
										query : Ext.getCmp("querySupplieraction").getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Suppliergrid.region = 'center';
	Supplierstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Suppliergrid ]
	});
})
