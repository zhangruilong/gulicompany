var Bkgoodsbbar;
var Goodsclassstore;
Ext.onReady(function() {
	var Bkgoodsclassify = '年货专区';
	var Bkgoodstitle = "当前位置:业务管理》" + Bkgoodsclassify;
	var Bkgoodsaction = "CPBkgoodsAction.do";
	var Bkgoodsfields = ['bkgoodsid'
	        			    ,'bkgoodscompany' 
	        			    ,'bkgoodscode' 
	        			    ,'bkgoodsname' 
	        			    ,'bkgoodsdetail' 
	        			    ,'bkgoodsunits' 
	        			    ,'bkgoodsunit' 
	        			    ,'bkgoodsprice' 
	        			    ,'bkgoodsorgprice' 
	        			    ,'bkgoodsnum' 
	        			    ,'bkgoodsclass' 
	        			    ,'bkgoodsimage' 
	        			    ,'bkgoodsstatue' 
	        			    ,'bkcreatetime' 
	        			    ,'bkcreator' 
	        			    ,'bkgoodsseq' 
	        			    ,'bkgoodsscope' 
	        			    ,'bkgoodsbrand' 
	        			    ,'bkgoodsallnum' 
	        			    ,'bkgoodssurplus' 
	        			    ,'bkgoodsweight' 
	        			    ,'bkgoodstype' 
	        			      ];// 全部字段
	var Goodsclassfields = ['goodsclassid'
	        			    ,'goodsclassname' 
	        			      ];// 小类字段
	var Bkgoodskeycolumn = [ 'bkgoodsid' ];// 主键
	var goodsStoreURL = basePath + Bkgoodsaction + "?method=queryCompanyBKgoods";
	var where = "bkgoodscompany='"+comid+"' and bkgoodsclass='年货商品'";
	var Bkgoodsstore = dataStore(Bkgoodsfields, goodsStoreURL);	//定义Bkgoodsstore
	Goodsclassstore = dataStore(Goodsclassfields, "CPGoodsclassAction.do?method=queryCompanyGoodsclass&wheresql=goodsclasscompany='"+comid+"'");	//定义小类store
	
	Goodsclassstore.load();	//加载供应商小类
	Bkgoodsstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				wheresql : where,
				query : Ext.getCmp("queryBkgoodsaction").getValue(),
				limit : Bkgoodsbbar.pageSize
		};
		Ext.apply(Bkgoodsstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var BkgoodsdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'BkgoodsdataForm',
		labelAlign : 'right',
		frame : true,
		//scrollable : true,	//使用y轴的滚动条
		layout : 'column',
		//collapsed : true,		//折叠
		items : [ {
			id: 'selectScope',
			name: 'selectScopecontainer',
			xtype: 'fieldcontainer',
			fieldLabel: '客户范围',
			labelWidth: 75,
			defaultType: 'checkboxfield',
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			        boxLabel  : '餐饮客户',
			        name      : 'selectScope',
			        originalValue: '3',
			        margin    : '0 10 0 0',
			        id        : 'scopeCheckbox3'
			    }, {
			        boxLabel  : '商超客户',
			        name      : 'selectScope',
			        originalValue: '2',
			        margin    : '0 10 0 0',
//			        checked   : true,
			        id        : 'scopeCheckbox2'
			    }, {
			        boxLabel  : '组织单位客户',
			        name      : 'selectScope',
			        originalValue: '1',
			        margin    : '0 10 0 0',
			        id        : 'scopeCheckbox1'
			    }
		]},
		{
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '促销品ID',
				id : 'Bkgoodsbkgoodsid',
				name : 'bkgoodsid',
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
				id : 'Bkgoodsbkgoodscompany',
				name : 'bkgoodscompany',
				value : comid,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Bkgoodsbkgoodscode',
				name : 'bkgoodscode',
				maxLength : 100,
				allowBlank : false,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'Bkgoodsbkgoodsname',
				name : 'bkgoodsname',
				maxLength : 100,
				allowBlank : false,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Bkgoodsbkgoodsunits',
				allowBlank : false,
				name : 'bkgoodsunits',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '单位',
				id : 'Bkgoodsbkgoodsunit',
				allowBlank : false,
				name : 'bkgoodsunit',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '品牌',
				id : 'Bkgoodsbkgoodsbrand',
				name : 'bkgoodsbrand',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '原价',
				id : 'Bkgoodsbkgoodsprice',
				name : 'bkgoodsprice',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '现价',
				id : 'Bkgoodsbkgoodsorgprice',
				allowBlank : false,
				name : 'bkgoodsorgprice',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '重量(kg)',
				id : 'Bkgoodsbkgoodsweight',
				name : 'bkgoodsweight',
				maxLength : 100,
				labelWidth: 70,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Bkgoodsbkgoodsstatue',
				name : 'bkgoodsstatue',
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'name',		//作为值的字段
				hiddenName : 'bkgoodsstatue',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				anchor : '95%',
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '顺序',
				id : 'Bkgoodsbkgoodsseq',
				name : 'bkgoodsseq',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片',
				id : 'Bkgoodsbkgoodsimage',
				name : 'bkgoodsimage',
				maxLength : 100,
				labelWidth: 40,
				width : 724,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '买赠信息',
				id : 'Bkgoodsbkgoodsdetail',
				name : 'bkgoodsdetail',
				maxLength : 100,
				labelWidth: 70,
				width : 724,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '类名',
				value : '年货商品',
				id : 'Bkgoodsbkgoodsclass',
				name : 'bkgoodsclass',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户范围',
				id : 'Bkgoodsbkgoodsscope',
				name : 'bkgoodsscope',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '分类',
				value : "年货",
				id : 'Bkgoodsbkgoodstype',
				name : 'bkgoodstype',
				maxLength : 100
			} ]
		}
		]
	});
	
	Bkgoodsbbar = pagesizebar(Bkgoodsstore);//定义分页
	var Bkgoodsgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Bkgoodstitle,
		store : Bkgoodsstore,
		bbar : Bkgoodsbbar,
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
			header : '促销品ID',
			dataIndex : 'bkgoodsid',
			sortable : true, 
			hidden : true
		}
		, {
			header : '商品编号',
			dataIndex : 'bkgoodscode',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品名称',
			dataIndex : 'bkgoodsname',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '规格',
			dataIndex : 'bkgoodsunits',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '单位',
			dataIndex : 'bkgoodsunit',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '类别',
			dataIndex : 'bkgoodsclass',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '品牌',
			dataIndex : 'bkgoodsbrand',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '现价',
			dataIndex : 'bkgoodsorgprice',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '重量',
			dataIndex : 'bkgoodsweight',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '图片',
			dataIndex : 'bkgoodsimage',
			sortable : true,
			hidden : true
		}
		, {
			header : '状态',
			dataIndex : 'bkgoodsstatue',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '顺序',
			dataIndex : 'bkgoodsseq',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '客户范围',
			dataIndex : 'bkgoodsscope',
			sortable : true,  
			hidden : true,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'bKGOODSUPDTIME',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改人',
			dataIndex : 'bKGOODSUPDOR',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'bkcreatetime',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建人',
			dataIndex : 'bkcreator',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryBkgoodsaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryBkgoodsaction").getValue()) {
							Bkgoodsstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Bkgoodsstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryBkgoodsaction").getValue()
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
					BkgoodsdataForm.form.reset();
					Ext.getCmp("Bkgoodsbkgoodsid").setEditable (true);
					addBKgoodsWindow(basePath + Bkgoodsaction + "?method=insAll", "新增", BkgoodsdataForm, Bkgoodsstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Bkgoodsgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					BkgoodsdataForm.form.reset();
					Ext.getCmp("Bkgoodsbkgoodsid").setEditable (false);
					editBKgoodsWindow(basePath + Bkgoodsaction + "?method=updAll", "修改", BkgoodsdataForm, Bkgoodsstore,selections[0].data['bkgoodsscope']);
					BkgoodsdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Bkgoodsgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Bkgoodsaction + "?method=delAll",selections,Bkgoodsstore,Bkgoodskeycolumn);
				}
            }
		]
	});
	Bkgoodsgrid.region = 'center';
	Bkgoodsstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Bkgoodsgrid ]
	});
})
