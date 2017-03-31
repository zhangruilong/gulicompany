var Bkgoodsbbar;
var Goodsclassstore;
var goodsCusTypeStore = new Ext.data.ArrayStore({//客户类型下拉
	fields:["name","value"],
	data:[["全部商品",""],["餐饮商品","3"],["商超商品","2"],["组织单位商品","1"]]
});
var odType = '';
var odQuery = '';
var odQueryjson = '';
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
		var type = Ext.getCmp('queryGoodsCusType').getValue();
		var query = Ext.getCmp("queryBkgoodsaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				wheresql : where,
				type : type,
				query : query,
				limit : Bkgoodsbbar.pageSize
		};
		if(type!=odType || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odType = type;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
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
				labelWidth: 70,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'Bkgoodsbkgoodsname',
				name : 'bkgoodsname',
				maxLength : 100,
				allowBlank : false,
				labelWidth: 70,
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
				fieldLabel : '图片路径',
				id : 'Bkgoodsbkgoodsimage',
				name : 'bkgoodsimage',
				maxLength : 100,
				labelWidth: 70,
				width : 724,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '促销描述',
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

	var screenDataForm = Ext.create('Ext.form.Panel', {// 筛选的FormPanel
		id:'scrBkgoodsdataForm',
		labelAlign : 'right',
		frame : true,
//		width : document.documentElement.clientWidth - 30,
		//scrollable : true,	//使用y轴的滚动条
		layout : 'column',
		//collapsed : true,		//折叠
		items : [ {
			id: 'scrselectScope',
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
			        id        : 'scrscopeCheckbox3'
			    }, {
			        boxLabel  : '商超客户',
			        name      : 'selectScope',
			        originalValue: '2',
			        margin    : '0 10 0 0',
//			        checked   : true,
			        id        : 'scrscopeCheckbox2'
			    }, {
			        boxLabel  : '组织单位客户',
			        name      : 'selectScope',
			        originalValue: '1',
			        margin    : '0 10 0 0',
			        id        : 'scrscopeCheckbox1'
			    }
		]},
		{
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '促销品ID',
				id : 'scrBkgoodsbkgoodsid',
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
				id : 'scrBkgoodsbkgoodscompany',
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
				id : 'scrBkgoodsbkgoodscode',
				name : 'bkgoodscode',
				maxLength : 100,
				labelWidth: 70,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'scrBkgoodsbkgoodsname',
				name : 'bkgoodsname',
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
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'scrBkgoodsbkgoodsunits',
				name : 'bkgoodsunits',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '单位',
				id : 'scrBkgoodsbkgoodsunit',
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
				id : 'scrBkgoodsbkgoodsbrand',
				name : 'bkgoodsbrand',
				maxLength : 100,
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '重量(kg)',
				id : 'scrBkgoodsbkgoodsweight',
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
				id : 'scrBkgoodsbkgoodsstatue',
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
				anchor : '95%',
				labelWidth: 40,
				width : 352,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '顺序',
				id : 'scrBkgoodsbkgoodsseq',
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
				fieldLabel : '个人限量',
				id : 'scrBkgoodsbkgoodsnum',
				name : 'bkgoodsnum',
				maxLength : 100,
				labelWidth: 70,
				width : 228,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '全部限量',
				id : 'scrBkgoodsbkgoodsallnum',
				name : 'bkgoodsallnum',
				maxLength : 100,
				labelWidth: 70,
				width : 228,
				margin : '5 10 5 10'
			},{
				xtype : 'textfield',
				fieldLabel : '剩余数量',
				id : 'scrBkgoodsbkgoodssurplus',
				name : 'bkgoodssurplus',
				maxLength : 100,
				labelWidth: 70,
				width : 228,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片路径',
				id : 'scrBkgoodsbkgoodsimage',
				name : 'bkgoodsimage',
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
				fieldLabel : '客户范围',
				id : 'scrBkgoodsbkgoodsscope',
				name : 'bkgoodsscope',
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
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
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
			width : 126,
		}
		, {
			header : '商品名称',
			dataIndex : 'bkgoodsname',
			sortable : true, 
			width : 137,
		}
		, {
			header : '规格',
			dataIndex : 'bkgoodsunits',
			sortable : true, 
			width : 105,
		}
		, {
			header : '单位',
			dataIndex : 'bkgoodsunit',
			sortable : true, 
			width : 47,
		}
		, {
			header : '类别',
			dataIndex : 'bkgoodsclass',
			sortable : true, 
			width:72,
		}
		, {
			header : '品牌',
			dataIndex : 'bkgoodsbrand',
			sortable : true, 
			width:72,
		}
		, {
			header : '原价',
			dataIndex : 'bkgoodsprice',
			sortable : true, 
			width : 47,
		}
		, {
			header : '现价',
			dataIndex : 'bkgoodsorgprice',
			sortable : true, 
			width : 47,
		}
		, {
			header : '重量',
			dataIndex : 'bkgoodsweight',
			sortable : true, 
			width : 47,
		}
		, {
			header : '促销描述',
			dataIndex : 'bkgoodsdetail',
			sortable : true, 
			width : 300,
		}
		, {
			header : '图片路径',
			dataIndex : 'bkgoodsimage',
			sortable : true,
			hidden : true
		}
		, {
			header : '状态',
			dataIndex : 'bkgoodsstatue',
			sortable : true, 
			width : 47,
		}
		, {
			header : '顺序',
			dataIndex : 'bkgoodsseq',
			sortable : true, 
			width : 47,
		}
		, {
			header : '客户范围',
			dataIndex : 'bkgoodsscope',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '修改时间',
			dataIndex : 'bkgoodsupdtime',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'bkgoodsupdor',
			sortable : true, 
			width:72,
		}
		, {
			header : '创建时间',
			dataIndex : 'bkcreatetime',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'bkcreator',
			sortable : true, 
			width:72,
		}
		],
		tbar : [{
			xtype : 'combo',
			fieldLabel : '商品类型',
			labelWidth : 63,
			id : 'queryGoodsCusType',
			name : 'cusType',
			width : 173,
			store : goodsCusTypeStore,
			mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
											//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
			displayField : 'name',		//显示的字段
			valueField : 'value',		//作为值的字段
			hiddenName : 'cusType',
			triggerAction : 'all',
			value : "",
			editable : false,
			maxLength : 100,
			anchor : '95%',
		},'-',{
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
			text : "查询",
			xtype: 'button',
			handler : function() {
				Bkgoodsstore.load();
			}
		},'-',{
			text : "筛选",
			iconCls : 'select',
			handler : function() {
				BkgoodsdataForm.form.reset();
				Ext.getCmp("Bkgoodsbkgoodsid").setEditable (true);
				bkgoodsQueryWindow("筛选", screenDataForm, Bkgoodsstore,Ext.getCmp("queryBkgoodsaction").getValue());
			}
		},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					BkgoodsdataForm.form.reset();
					Ext.getCmp("Bkgoodsbkgoodsid").setEditable (true);
					Ext.getCmp("Bkgoodsbkgoodscode").setDisabled(false);
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
					Ext.getCmp("Bkgoodsbkgoodscode").setDisabled(true);
					editBKgoodsWindow(basePath + Bkgoodsaction + "?method=updAll", "修改", BkgoodsdataForm, Bkgoodsstore,selections[0].data['bkgoodsscope'],Bkgoodsfields);
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
