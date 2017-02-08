var Warrantcheckviewbbar;
Ext.onReady(function() {
	var Warrantcheckviewclassify = "盘点记录";
	var Warrantcheckviewtitle = "当前位置:库存管理》" + Warrantcheckviewclassify;
	var Warrantcheckviewaction = "CPWarrantcheckviewAction.do";
	var Warrantcheckviewfields = ['idwarrantcheck'
	        			    ,'warrantcheckstore' 
	        			    ,'warrantcheckgoods' 
	        			    ,'warrantchecknumorg' 
	        			    ,'warrantchecknumnow' 
	        			    ,'warrantcheckstatue' 
	        			    ,'warrantcheckdetail' 
	        			    ,'warrantcheckinswho' 
	        			    ,'warrantcheckinswhen' 
	        			    ,'warrantcheckupdwho' 
	        			    ,'warrantcheckupdwhen' 
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
	var Warrantcheckviewkeycolumn = [ 'idwarrantcheck' ];// 主键
	var wheresql = "goodscompany='"+comid+"'";
	var Warrantcheckviewstore = dataStore(Warrantcheckviewfields, basePath + Warrantcheckviewaction + "?method=selAll");// 定义Warrantcheckviewstore
	Warrantcheckviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				json : queryjson,
				query : Ext.getCmp("queryWarrantcheckviewaction").getValue(),
				limit : Warrantcheckviewbbar.pageSize
		};
		Ext.apply(Warrantcheckviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var WarrantcheckviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantcheckviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '盘点记录ID',
				id : 'Warrantcheckviewidwarrantcheck',
				name : 'idwarrantcheck',
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
				id : 'Warrantcheckviewwarrantcheckgoods',
				allowBlank : false,
				name : 'warrantcheckgoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantcheckviewgoodscode',
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
				id : 'Warrantcheckviewgoodsname',
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
				id : 'Warrantcheckviewgoodsunits',
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
				id : 'Warrantcheckviewwarrantcheckstore',
				allowBlank : false,
				name : 'warrantcheckstore',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '应有数量',
				id : 'Warrantcheckviewwarrantchecknumorg',
				allowBlank : false,
				name : 'warrantchecknumorg',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '现有数量',
				id : 'Warrantcheckviewwarrantchecknumnow',
				allowBlank : false,
				name : 'warrantchecknumnow',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '状态',
				id : 'Warrantcheckviewwarrantcheckstatue',
				name : 'warrantcheckstatue',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Warrantcheckviewwarrantcheckdetail',
				name : 'warrantcheckdetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantcheckviewbbar = pagesizebar(Warrantcheckviewstore);//定义分页
	var Warrantcheckviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantcheckviewtitle,
		store : Warrantcheckviewstore,
		bbar : Warrantcheckviewbbar,
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
			header : '盘点记录ID',
			dataIndex : 'idwarrantcheck',
			hidden : true,
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品ID',
			dataIndex : 'warrantcheckgoods',
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
			dataIndex : 'warrantcheckstore',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '应有数量',
			dataIndex : 'warrantchecknumorg',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '现有数量',
			dataIndex : 'warrantchecknumnow',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '状态',
			dataIndex : 'warrantcheckstatue',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '描述',
			dataIndex : 'warrantcheckdetail',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建人',
			dataIndex : 'warrantcheckinswho',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantcheckinswhen',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '更新人',
			dataIndex : 'warrantcheckupdwho',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '更新时间',
			dataIndex : 'warrantcheckupdwhen',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantcheckviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantcheckviewaction").getValue()) {
							Warrantcheckviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantcheckviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantcheckviewaction").getValue()
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
					WarrantcheckviewdataForm.form.reset();
					Ext.getCmp("Warrantcheckviewidwarrantcheck").setEditable (true);
					addWarrantcheckWindow(basePath + "CPWarrantcheckAction.do?method=insWarrantcheck", "新增", WarrantcheckviewdataForm, Warrantcheckviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Warrantcheckviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					WarrantcheckviewdataForm.form.reset();
					Ext.getCmp("Warrantcheckviewidwarrantcheck").setEditable (false);
					editWarrantcheckWindow(basePath + "CPWarrantcheckAction.do?method=updWarrantcheck", "修改", WarrantcheckviewdataForm, Warrantcheckviewstore);
					WarrantcheckviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Warrantcheckviewgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + "CPWarrantcheckAction.do?method=delAll",selections,Warrantcheckviewstore,Warrantcheckviewkeycolumn);
				}
            }
		]
	});
	Warrantcheckviewgrid.region = 'center';
	Warrantcheckviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantcheckviewgrid ]
	});
})
