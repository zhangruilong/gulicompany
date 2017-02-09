var Warrantbackviewbbar;
Ext.onReady(function() {
	var Warrantbackviewclassify = "退货台账";
	var Warrantbackviewtitle = "当前位置:库存管理》" + Warrantbackviewclassify;
	var Warrantbackviewaction = "CPWarrantbackviewAction.do";
	var Warrantbackviewfields = ['idwarrantback'
	                             ,'warrantbackstore' 
	 	        			    ,'warrantbackgoods' 
	 	        			    ,'warrantbacknum' 
	 	        			    ,'warrantbackwho' 
	 	        			    ,'warrantbackstatue' 
	 	        			    ,'warrantbackdetail' 
	 	        			    ,'warrantbackinswho' 
	 	        			    ,'warrantbackinswhen' 
	 	        			    ,'warrantbackupdwho' 
	 	        			    ,'warrantbackupdwhen' 
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
	var Warrantbackviewkeycolumn = [ 'idwarrantback' ];// 主键
	var wheresql = "goodscompany='"+comid+"'";
	var Warrantbackviewstore = dataStore(Warrantbackviewfields, basePath + Warrantbackviewaction + "?method=selQuery");// 定义Warrantbackviewstore
	Warrantbackviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				json : queryjson,
				query : Ext.getCmp("queryWarrantbackviewaction").getValue(),
				limit : Warrantbackviewbbar.pageSize
		};
		Ext.apply(Warrantbackviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var WarrantbackviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantbackviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '退货台账ID',
				id : 'Warrantbackviewidwarrantback',
				name : 'idwarrantback',
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
				id : 'Warrantbackviewwarrantbackgoods',
				allowBlank : false,
				name : 'warrantbackgoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantbackviewgoodscode',
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
				id : 'Warrantbackviewgoodsname',
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
				id : 'Warrantbackviewgoodsunits',
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
				id : 'Warrantbackviewwarrantbackstore',
				allowBlank : false,
				name : 'warrantbackstore',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数量',
				id : 'Warrantbackviewwarrantbacknum',
				allowBlank : false,
				name : 'warrantbacknum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '退货人',
				id : 'Warrantbackviewwarrantbackwho',
				allowBlank : false,
				name : 'warrantbackwho',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '状态',
				id : 'Warrantbackviewwarrantbackstatue',
				name : 'warrantbackstatue',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Warrantbackviewwarrantbackdetail',
				name : 'warrantbackdetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantbackviewbbar = pagesizebar(Warrantbackviewstore);//定义分页
	var Warrantbackviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantbackviewtitle,
		store : Warrantbackviewstore,
		bbar : Warrantbackviewbbar,
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
			dataIndex : 'idwarrantback',
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
			dataIndex : 'warrantbackstore',
			sortable : true, 
			width : 137,
		}
		, {
			header : '商品',
			dataIndex : 'warrantbackgoods',
			sortable : true, 
			hidden : true,
		}
		, {
			header : '数量',
			dataIndex : 'warrantbacknum',
			sortable : true, 
			width : 47,
		}
		, {
			header : '退货人',
			dataIndex : 'warrantbackwho',
			sortable : true, 
			width:150,
		}
		, {
			header : '状态',
			dataIndex : 'warrantbackstatue',
			sortable : true, 
			width : 47,
		}
		, {
			header : '描述',
			dataIndex : 'warrantbackdetail',
			sortable : true, 
			width:150,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantbackinswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantbackinswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantbackupdwhen',
			sortable : true, 
		}
		, {
			header : '修改人',
			dataIndex : 'warrantbackupdwho',
			sortable : true, 
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantbackviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantbackviewaction").getValue()) {
							Warrantbackviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantbackviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantbackviewaction").getValue()
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
					WarrantbackviewdataForm.form.reset();
					Ext.getCmp("Warrantbackviewidwarrantback").setEditable (true);
					addWarrantbackWindow(basePath + "CPWarrantbackAction.do?method=addWarrantback", "新增退货台账", WarrantbackviewdataForm, Warrantbackviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Warrantbackviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					WarrantbackviewdataForm.form.reset();
					Ext.getCmp("Warrantbackviewidwarrantback").setEditable (false);
					editWarrantbackWindow(basePath + "CPWarrantbackAction.do?method=updWarrantback", "修改退货台账", WarrantbackviewdataForm, Warrantbackviewstore);
					WarrantbackviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Warrantbackviewgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + "CPWarrantbackAction.do?method=delAll",selections,Warrantbackviewstore,Warrantbackviewkeycolumn);
				}
            }
		]
	});
	Warrantbackviewgrid.region = 'center';
	Warrantbackviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantbackviewgrid ]
	});
})
