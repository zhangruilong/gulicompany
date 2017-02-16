Ext.onReady(function() {
	var Storehouseclassify = "仓库管理";
	var Storehousetitle = "当前位置:库存管理》" + Storehouseclassify;
	var Storehouseaction = "CPStorehouseAction.do";
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
	var Storehousekeycolumn = [ 'storehouseid' ];// 主键
	var Storehousestore = dataStore(Storehousefields, basePath + Storehouseaction + "?method=selAll");// 定义Storehousestore
	var StorehousedataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'StorehousedataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '仓库ID',
				id : 'Storehousestorehouseid',
				name : 'storehouseid',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '经销商ID',
				id : 'Storehousestorehousecompany',
				name : 'storehousecompany',
				value : comid,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Storehousestorehousecode',
				name : 'storehousecode',
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
				id : 'Storehousestorehousename',
				name : 'storehousename',
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
				id : 'Storehousestorehousedetail',
				name : 'storehousedetail',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Storehousestorehousestatue',
				name : 'storehousestatue',
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'name',		//作为值的字段
				hiddenName : 'storehousestatue',
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
			items : [ {
				xtype : 'textfield',
				fieldLabel : '仓库地址',
				id : 'Storehousestorehouseaddress',
				name : 'storehouseaddress',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		]
	});
	
	var Storehousebbar = pagesizebar(Storehousestore);//定义分页
	var Storehousegrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Storehousetitle,
		store : Storehousestore,
		bbar : Storehousebbar,
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
			header : '仓库ID',
			dataIndex : 'storehouseid',
			sortable : true,
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'storehousecompany',
			sortable : true,
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'storehousecode',
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'storehousename',
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'storehousedetail',
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'storehousestatue',
			sortable : true
		}
		, {
			header : '仓库地址',
			dataIndex : 'storehouseaddress',
			sortable : true
		}
		, {
			header : '修改时间',
			dataIndex : 'storehouseupdtime',
			sortable : true
		}
		, {
			header : '修改人',
			dataIndex : 'storehouseupdor',
			sortable : true
		}
		, {
			header : '创建时间',
			dataIndex : 'storehousecretime',
			sortable : true
		}
		, {
			header : '创建人',
			dataIndex : 'storehousecreor',
			sortable : true
		}
		],
		tbar : [{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					StorehousedataForm.form.reset();
					Ext.getCmp("Storehousestorehouseid").setEditable (true);
					createTextWindow(basePath + Storehouseaction + "?method=addStorehouse", "新增", StorehousedataForm, Storehousestore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Storehousegrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					StorehousedataForm.form.reset();
					Ext.getCmp("Storehousestorehouseid").setEditable (false);
					createTextWindow(basePath + Storehouseaction + "?method=updStorehouse", "修改", StorehousedataForm, Storehousestore);
					StorehousedataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Storehousegrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Storehouseaction + "?method=delAll",selections,Storehousestore,Storehousekeycolumn);
				}
            },'->',{
				xtype : 'textfield',
				id : 'queryStorehouseaction',
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("queryStorehouseaction").getValue()) {
								Storehousestore.load({
									params : {
										json : queryjson
									}
								});
							} else {
								Storehousestore.load({
									params : {
										json : queryjson,
										query : Ext.getCmp("queryStorehouseaction").getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Storehousegrid.region = 'center';
	Storehousestore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Storehousegrid ]
	});
})
