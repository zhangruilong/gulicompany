var Warrantinviewbbar;
Ext.onReady(function() {
	var Warrantinviewclassify = "入库台账";
	var Warrantinviewtitle = "当前位置:库存管理》" + Warrantinviewclassify;
	var Warrantinviewaction = "CPWarrantinviewAction.do";
	var Warrantinviewfields = ['idwarrantin'
	        			    ,'warrantinstore' 
	        			    ,'warrantinfrom' 
	        			    ,'warrantingoods' 
	        			    ,'warrantinprice' 
	        			    ,'warrantinnum' 
	        			    ,'warrantinwho' 
	        			    ,'warrantinstatue' 
	        			    ,'warrantindetail' 
	        			    ,'warrantininswhen' 
	        			    ,'warrantininswho' 
	        			    ,'warrantinupdwhen' 
	        			    ,'warrantinupdwho' 
	        			    ,'goodsid' 
	        			    ,'goodscompany' 
	        			    ,'goodscode' 
	        			    ,'goodsname' 
	        			    ,'goodsdetail' 
	        			    ,'goodsunits' 
	        			    ,'goodsclass' 
	        			    ,'goodsimage' 
	        			    ,'goodsstatue' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			    ,'creator' 
	        			    ,'updor' 
	        			    ,'goodsbrand' 
	        			    ,'goodstype' 
	        			    ,'goodsorder' 
	        			    ,'goodsweight' 
	        			    ,'goodsclassname' 
	        			      ];// 全部字段
	var Warrantinviewkeycolumn = [ 'idwarrantin' ];// 主键
	var Storehousefields = ['storehouseid'
	        			    ,'storehousecode' 
	        			    ,'storehousename' 
	        			    ,'storehousedetail' 
	        			    ,'storehousestatue' 
	        			    ,'storehousecompany' 
	        			    ,'storehouseupdtime' 
	        			    ,'storehouseupdor' 
	        			    ,'storehousecretime' 
	        			    ,'storehousecreor' 
	        			    ,'storehouseaddress' 
	        			      ];// 全部字段
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
	var wheresql = "goodscompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"'");// 定义Storehousestore
	Storehousestore.load();
	var Supplierstore = dataStore(Supplierfields, basePath + "CPSupplierAction.do?method=selAll&wheresql=suppliercompany='"+comid+"'");// 定义Supplierstore
	Supplierstore.load();
	var Warrantinviewstore = dataStore(Warrantinviewfields, basePath + Warrantinviewaction + "?method=selAll");// 定义Warrantinviewstore
	Warrantinviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				json : queryjson,
				query : Ext.getCmp("queryWarrantinviewaction").getValue(),
				limit : Warrantinviewbbar.pageSize
		};
		Ext.apply(Warrantinviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var WarrantinviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantinviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '入库台账ID',
				id : 'Warrantinviewidwarrantin',
				name : 'idwarrantin',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品ID',
				id : 'Warrantinviewwarrantingoods',
				allowBlank : false,
				readOnly : true,
				name : 'warrantingoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantinviewgoodscode',
				allowBlank : false,
				readOnly : true,
				name : 'goodscode',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'Warrantinviewgoodsname',
				allowBlank : false,
				readOnly : true,
				name : 'goodsname',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Warrantinviewgoodsunits',
				allowBlank : false,
				readOnly : true,
				name : 'goodsunits',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '仓库',
				id : 'Warrantinviewwarrantinstore',
				name : 'warrantinstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'warrantinstore',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				anchor : '95%',
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '供货单位',
				id : 'Warrantinviewwarrantinfrom',
				name : 'warrantinfrom',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Supplierstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'suppliername',		//显示的字段
				valueField : 'supplierid',		//作为值的字段
				hiddenName : 'warrantinfrom',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				anchor : '95%',
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '进货价',
				id : 'Warrantinviewwarrantinprice',
				allowBlank : false,
				name : 'warrantinprice',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数量',
				id : 'Warrantinviewwarrantinnum',
				allowBlank : false,
				name : 'warrantinnum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '检验员',
				id : 'Warrantinviewwarrantinwho',
				name : 'warrantinwho',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '备注',
				id : 'Warrantinviewwarrantindetail',
				name : 'warrantindetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantinviewbbar = pagesizebar(Warrantinviewstore);//定义分页
	var Warrantinviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantinviewtitle,
		store : Warrantinviewstore,
		bbar : Warrantinviewbbar,
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
			header : '入库台账ID',
			dataIndex : 'idwarrantin',
			hidden : true,
			sortable : true, 
		}
		, {
			header : '商品ID',
			dataIndex : 'warrantingoods',
			hidden : true,
			sortable : true,  
		}
		, {
			header : '商品编号',
			dataIndex : 'goodscode',
			sortable : true, 
			width : 122,
		}
		, {
			header : '商品名称',
			dataIndex : 'goodsname',
			sortable : true, 
			width : 137,
		}
		, {
			header : '规格',
			dataIndex : 'goodsunits',
			sortable : true, 
			width : 105,
		}
		, {
			header : '仓库',
			dataIndex : 'warrantinstore',
			sortable : true, 
			width : 137,
			renderer : function(value){
				var md = Storehousestore.find('storehouseid',value);
				if(md!=-1){
					return Storehousestore.getAt(md).get('storehousename');
				} else {
					return '';
				}
			},
		}
		, {
			header : '供货单位',
			dataIndex : 'warrantinfrom',
			sortable : true, 
			width:150,
		}
		, {
			header : '进货价',
			dataIndex : 'warrantinprice',
			sortable : true, 
			width:60,
		}
		, {
			header : '数量',
			dataIndex : 'warrantinnum',
			sortable : true, 
			width : 48,
		}
		, {
			header : '检验员',
			dataIndex : 'warrantinwho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '状态',
			dataIndex : 'warrantinstatue',
			sortable : true, 
			width : 47,
		}
		, {
			header : '备注',
			dataIndex : 'warrantindetail',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantininswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantininswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantinupdwhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'warrantinupdwho',
			sortable : true, 
			width : 73,
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantinviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantinviewaction").getValue()) {
							Warrantinviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantinviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantinviewaction").getValue()
								}
							});
						}
					}
				}
			}
		},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "入库",
				iconCls : 'add',
				handler : function() {
					WarrantinviewdataForm.form.reset();
					Ext.getCmp("Warrantinviewidwarrantin").setEditable (true);
					addWarrantinWindow(basePath + "CPWarrantinAction.do?method=addWarrantin", "新增入库台账", WarrantinviewdataForm, Warrantinviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "回滚",
				iconCls : 'delete',
				handler : function() {
					var selections = Warrantinviewgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					delWarrantin(basePath + "CPWarrantinAction.do?method=delWarrantin",selections,Warrantinviewstore);
				}
            }
		]
	});
	Warrantinviewgrid.region = 'center';
	Warrantinviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantinviewgrid ]
	});
})
