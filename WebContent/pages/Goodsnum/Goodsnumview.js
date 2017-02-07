var Goodsnumviewbbar;
Ext.onReady(function() {
	var Goodsnumviewclassify = "库存总账";
	var Goodsnumviewtitle = "当前位置:业务管理》" + Goodsnumviewclassify;
	var Goodsnumviewaction = "CPGoodsnumviewAction.do";
	var Goodsnumviewfields = ['idgoodsnum'
	        			    ,'goodsnumgoods' 
	        			    ,'goodsnumnum' 
	        			    ,'goodsnumstore' 
	        			    ,'gOODSID' 
	        			    ,'gOODSCOMPANY' 
	        			    ,'gOODSCODE' 
	        			    ,'gOODSNAME' 
	        			    ,'gOODSDETAIL' 
	        			    ,'gOODSUNITS' 
	        			    ,'gOODSCLASS' 
	        			    ,'gOODSIMAGE' 
	        			    ,'gOODSSTATUE' 
	        			    ,'cREATETIME' 
	        			    ,'uPDTIME' 
	        			    ,'cREATOR' 
	        			    ,'uPDOR' 
	        			    ,'gOODSBRAND' 
	        			    ,'gOODSTYPE' 
	        			    ,'gOODSORDER' 
	        			    ,'gOODSCLASSID' 
	        			    ,'gOODSCLASSNAME' 
	        			      ];// 全部字段
	var Goodsnumviewkeycolumn = [ 'idgoodsnum' ];// 主键
	var wheresql = "gOODSCOMPANY='"+comid+"'";
	var Goodsnumviewstore = dataStore(Goodsnumviewfields, basePath + Goodsnumviewaction + "?method=selQuery");// 定义Goodsnumviewstore
	Goodsnumviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				json : queryjson,
				query : Ext.getCmp("queryGoodsnumviewaction").getValue(),
				limit : Goodsnumviewbbar.pageSize
		};
		Ext.apply(Goodsnumviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var GoodsnumviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'GoodsnumviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : 'ID',
				id : 'Goodsnumviewidgoodsnum',
				name : 'idgoodsnum',
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
				id : 'Goodsnumgoodsnumgoods',
				allowBlank : false,
				name : 'goodsnumgoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编码',
				readOnly : true,
				id : 'GoodsnumviewgOODSCODE',
				allowBlank : false,
				name : 'gOODSCODE',
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
				id : 'GoodsnumviewgOODSNAME',
				allowBlank : false,
				name : 'gOODSNAME',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品规格',
				readOnly : true,
				id : 'GoodsnumviewgOODSUNITS',
				allowBlank : false,
				name : 'gOODSUNITS',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数量',
				id : 'Goodsnumviewgoodsnumnum',
				allowBlank : false,
				name : 'goodsnumnum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '仓库',
				id : 'Goodsnumviewgoodsnumstore',
				allowBlank : false,
				name : 'goodsnumstore',
				maxLength : 100
			} ]
		}
		]
	});
	
	Goodsnumviewbbar = pagesizebar(Goodsnumviewstore);//定义分页
	var Goodsnumviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Goodsnumviewtitle,
		store : Goodsnumviewstore,
		bbar : Goodsnumviewbbar,
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
			header : 'ID',
			dataIndex : 'idgoodsnum',
			sortable : true, 
			hidden : true,
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品编码',
			dataIndex : 'gOODSCODE',
			sortable : true
		}
		, {
			header : '商品名称',
			dataIndex : 'gOODSNAME',
			sortable : true
		}
		, {
			header : '商品规格',
			dataIndex : 'gOODSUNITS',
			sortable : true
		}
		, {
			header : '数量',
			dataIndex : 'goodsnumnum',
			sortable : true
		}
		, {
			header : '仓库',
			dataIndex : 'goodsnumstore',
			sortable : true
		}
		],
		tbar : [{
				xtype : 'textfield',
				id : 'queryGoodsnumviewaction',
				name : 'query',
				emptyText : '模糊匹配',
				width : 200,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("queryGoodsnumviewaction").getValue()) {
								Goodsnumviewstore.load({
									params : {
										json : queryjson
									}
								});
							} else {
								Goodsnumviewstore.load({
									params : {
										json : queryjson,
										query : Ext.getCmp("queryGoodsnumviewaction").getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Goodsnumviewgrid.region = 'center';
	Goodsnumviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Goodsnumviewgrid ]
	});
})
