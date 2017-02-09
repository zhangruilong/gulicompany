var Warrantoutviewbbar;
Ext.onReady(function() {
	var Warrantoutviewclassify = "出库台账";
	var Warrantoutviewtitle = "当前位置:库存管理》" + Warrantoutviewclassify;
	var Warrantoutviewaction = "CPWarrantoutviewAction.do";
	var Warrantoutviewfields = ['idwarrantout'
	        			    ,'warrantoutstore' 
	        			    ,'warrantoutgoods' 
	        			    ,'warrantoutnum' 
	        			    ,'warrantoutstatue' 
	        			    ,'warrantoutdetail' 
	        			    ,'warrantoutwho' 
	        			    ,'warrantoutinswhen' 
	        			    ,'warrantoutinswho' 
	        			    ,'warrantoutupdwhen' 
	        			    ,'warrantoutupdwho' 
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
	var Warrantoutviewkeycolumn = [ 'idwarrantout' ];// 主键
	var wheresql = "goodscompany='"+comid+"'";
	var Warrantoutviewstore = dataStore(Warrantoutviewfields, basePath + Warrantoutviewaction + "?method=selAll");// 定义Warrantoutviewstore
	Warrantoutviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				json : queryjson,
				query : Ext.getCmp("queryWarrantoutviewaction").getValue(),
				limit : Warrantoutviewbbar.pageSize
		};
		Ext.apply(Warrantoutviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var WarrantoutviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantoutviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '出库台账ID',
				id : 'Warrantoutviewidwarrantout',
				name : 'idwarrantout',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品',
				id : 'Warrantoutviewwarrantoutgoods',
				allowBlank : false,
				name : 'warrantoutgoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantoutviewgoodscode',
				allowBlank : false,
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
				id : 'Warrantoutviewgoodsname',
				allowBlank : false,
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
				id : 'Warrantoutviewgoodsunits',
				allowBlank : false,
				name : 'goodsunits',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '仓库',
				id : 'Warrantoutviewwarrantoutstore',
				allowBlank : false,
				name : 'warrantoutstore',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数量',
				id : 'Warrantoutviewwarrantoutnum',
				allowBlank : false,
				name : 'warrantoutnum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '状态',
				id : 'Warrantoutviewwarrantoutstatue',
				name : 'warrantoutstatue',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '备注',
				id : 'Warrantoutviewwarrantoutdetail',
				name : 'warrantoutdetail',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '领货人',
				id : 'Warrantoutviewwarrantoutwho',
				allowBlank : false,
				name : 'warrantoutwho',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantoutviewbbar = pagesizebar(Warrantoutviewstore);//定义分页
	var Warrantoutviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantoutviewtitle,
		store : Warrantoutviewstore,
		bbar : Warrantoutviewbbar,
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
			header : 'ID',
			dataIndex : 'idwarrantout',
			sortable : true, 
			hidden : true,
		}
		, {
			header : '商品',
			dataIndex : 'warrantoutgoods',
			sortable : true,  
			hidden : true,
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
			dataIndex : 'warrantoutstore',
			sortable : true, 
			width : 137,
		}
		, {
			header : '数量',
			dataIndex : 'warrantoutnum',
			sortable : true, 
			width : 48,
		}
		, {
			header : '状态',
			dataIndex : 'warrantoutstatue',
			sortable : true, 
			width : 48,
		}
		, {
			header : '备注',
			dataIndex : 'warrantoutdetail',
			sortable : true, 
			width : 73,
		}
		, {
			header : '领货人',
			dataIndex : 'warrantoutwho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantoutinswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantoutinswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantoutupdwhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'warrantoutupdwho',
			sortable : true, 
			width : 73,
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantoutviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantoutviewaction").getValue()) {
							Warrantoutviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantoutviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantoutviewaction").getValue()
								}
							});
						}
					}
				}
			}
		},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					WarrantoutviewdataForm.form.reset();
					Ext.getCmp("Warrantoutviewidwarrantout").setEditable (true);
					addWarrantoutWindow(basePath + "CPCPWarrantoutAction.do?method=insWarrantout", "新增出库台账", WarrantoutviewdataForm, Warrantoutviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Warrantoutviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					WarrantoutviewdataForm.form.reset();
					Ext.getCmp("Warrantoutviewidwarrantout").setEditable (false);
					editWarrantoutWindow(basePath + "CPCPWarrantoutAction.do?method=updWarrantout", "修改出库台账", WarrantoutviewdataForm, Warrantoutviewstore);
					WarrantoutviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Warrantoutviewgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					delWarrantout(basePath + "CPCPWarrantoutAction.do?method=delWarrantout",selections,Warrantoutviewstore,Warrantoutviewkeycolumn);
				}
            }
		]
	});
	Warrantoutviewgrid.region = 'center';
	Warrantoutviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantoutviewgrid ]
	});
})
