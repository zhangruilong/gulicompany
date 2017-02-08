Ext.onReady(function() {
	var Largecuspriceviewclassify = "特殊商品";
	var Largecuspriceviewtitle = "当前位置：客户管理》录单客户》" + Largecuspriceviewclassify;
	var Largecuspriceviewaction = "CPLargecuspriceviewAction.do";
	var Largecuspriceviewfields = ['largecuspriceid'
	        			    ,'largecuspricecompany' 
	        			    ,'largecuspriceprice' 
	        			    ,'largecuspriceunit' 
	        			    ,'largecuspricedetail' 
	        			    ,'largecuspricecreatetime' 
	        			    ,'largecuspricecreator' 
	        			    ,'largecusupdtime' 
	        			    ,'largecusupdor' 
	        			    ,'largecuspricecustomer' 
	        			    ,'goodsid' 
	        			    ,'goodscompany' 
	        			    ,'goodscode' 
	        			    ,'goodsname' 
	        			    ,'goodsunits' 
	        			    ,'goodsclassname' 
	        			      ];// 全部字段
	var Largecuspriceviewkeycolumn = [ 'largecuspriceid' ];// 主键
	var LargecuspriceviewstoreURL = basePath + Largecuspriceviewaction + "?method=selAll";
	var where = "&wheresql=largecuspricecompany='"+comid+"' and goodscompany='"+comid+"' and largecuspricecustomer='"+cusid+"'";
	var Largecuspriceviewstore = dataStore(Largecuspriceviewfields,LargecuspriceviewstoreURL+where );// 定义Largecuspriceviewstore
	var LargecuspriceviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'LargecuspriceviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '价格ID',
				id : 'Largecuspriceviewlargecuspriceid',
				name : 'largecuspriceid',
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
				id : 'Largecuspriceviewgoodsid',
				name : 'goodsid',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户ID',
				id : 'Largecuspriceviewlargecuspricecustomer',
				name : 'largecuspricecustomer',
				maxLength : 100,
				value : cusid
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				readOnly : true,
				id : 'Largecuspriceviewgoodscode',
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
				readOnly : true,
				id : 'Largecuspriceviewgoodsname',
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
				readOnly : true,
				id : 'Largecuspriceviewgoodsunits',
				name : 'goodsunits',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '单位',
				id : 'Largecuspriceviewlargecuspriceunit',
				name : 'largecuspriceunit',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '价格',
				id : 'Largecuspriceviewlargecuspriceprice',
				name : 'largecuspriceprice',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Largecuspriceviewlargecuspricedetail',
				name : 'largecuspricedetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	//var Largecuspriceviewbbar = pagesizebar(Largecuspriceviewstore);//定义分页
	var Largecuspriceviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Largecuspriceviewtitle,
		store : Largecuspriceviewstore,
		//bbar : Largecuspriceviewbbar,
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
			header : '价格ID',
			dataIndex : 'largecuspriceid',
			sortable : true,
			hidden : true
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
			header : '类别',
			dataIndex : 'goodsclassname',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '价格',
			dataIndex : 'largecuspriceprice',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '单位',
			dataIndex : 'largecuspriceunit',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '描述',
			dataIndex : 'largecuspricedetail',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'largecusupdtime',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改人',
			dataIndex : 'largecusupdor',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'largecuspricecreatetime',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建人',
			dataIndex : 'largecuspricecreator',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryLargecuspriceviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryLargecuspriceviewaction").getValue()) {
							Largecuspriceviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Largecuspriceviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryLargecuspriceviewaction").getValue()
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
					LargecuspriceviewdataForm.form.reset();
					Ext.getCmp("Largecuspriceviewlargecuspriceid").setEditable (true);
					addLCPWindow(basePath + Largecuspriceviewaction + "?method=insLCP", "新增特殊商品", LargecuspriceviewdataForm, Largecuspriceviewstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Largecuspriceviewgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					LargecuspriceviewdataForm.form.reset();
					Ext.getCmp("Largecuspriceviewlargecuspriceid").setEditable (false);
					createTextWindow(basePath + Largecuspriceviewaction + "?method=updateLCP", "修改", LargecuspriceviewdataForm, Largecuspriceviewstore);
					LargecuspriceviewdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : Ext.os.deviceType === 'Phone' ? null : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Largecuspriceviewgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Largecuspriceviewaction + "?method=deleteLCP",selections,Largecuspriceviewstore,Largecuspriceviewkeycolumn);
				}
			}
		]
	});
	Largecuspriceviewgrid.region = 'center';
	Largecuspriceviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Largecuspriceviewgrid ]
	});
})
