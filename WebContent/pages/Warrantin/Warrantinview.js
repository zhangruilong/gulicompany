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
	var wheresql = "goodscompany='"+comid+"'";
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
				id : 'Warrantinviewwarrantinstore',
				allowBlank : false,
				name : 'warrantinstore',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '供货单位',
				id : 'Warrantinviewwarrantinfrom',
				allowBlank : false,
				name : 'warrantinfrom',
				maxLength : 100
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
				fieldLabel : '状态',
				id : 'Warrantinviewwarrantinstatue',
				name : 'warrantinstatue',
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
	    plugins: {
	         ptype: 'cellediting',
	         clicksToEdit: 1
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
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品ID',
			dataIndex : 'warrantingoods',
			hidden : true,
			sortable : true,  
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品编号',
			dataIndex : 'goodscode',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品名称',
			dataIndex : 'goodsname',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '规格',
			dataIndex : 'goodsunits',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '仓库',
			dataIndex : 'warrantinstore',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '供货单位',
			dataIndex : 'warrantinfrom',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '进货价',
			dataIndex : 'warrantinprice',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '数量',
			dataIndex : 'warrantinnum',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '检验员',
			dataIndex : 'warrantinwho',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '状态',
			dataIndex : 'warrantinstatue',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '备注',
			dataIndex : 'warrantindetail',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantininswhen',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建人',
			dataIndex : 'warrantininswho',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantinupdwhen',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改人',
			dataIndex : 'warrantinupdwho',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
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
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					WarrantinviewdataForm.form.reset();
					Ext.getCmp("Warrantinviewidwarrantin").setEditable (true);
					addWarrantinWindow(basePath + "CPWarrantinAction.do?method=addWarrantin", "新增", WarrantinviewdataForm, Warrantinviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Warrantinviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					WarrantinviewdataForm.form.reset();
					Ext.getCmp("Warrantinviewidwarrantin").setEditable (false);
					editWarrantinWindow(basePath + "CPWarrantinAction.do?method=updWarrantin", "修改", WarrantinviewdataForm, Warrantinviewstore);
					WarrantinviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
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
