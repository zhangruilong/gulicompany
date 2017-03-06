var goodsStatueStore = new Ext.data.ArrayStore({//状态下拉
    	fields:["name"],
    	data:[["上架"],["下架"]]
    });
var goodsCusTypeStore = new Ext.data.ArrayStore({//客户类型下拉
	fields:["name","value"],
	data:[["全部商品",""],["餐饮商品","3"],["商超商品","2"],["组织单位商品","1"]]
});
var Goodsbbar;
var Goodsclassstore;
var GoodsdataForm;
//上下架的combox的renderer函数
getOneDisplay = function(value, meta, record) { 
    var rowIndex = goodsStatueStore.find("name", "" + value); 
    
    if (rowIndex < 0) {
    	
    	return '';
    }

    var record = goodsStatueStore.getAt(rowIndex); 

    return record ? record.get('name') : '';

}



Ext.onReady(function() {
	var Goodsclassify = classify;
	var Goodstitle = "当前位置:商品管理》" + Goodsclassify;
	var Goodsaction = "CPGoodsviewAction.do";
	var Goodsfields = ['goodsid'
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
	        			    ,'goodsclassid' 
	        			    ,'goodsclasscode' 
	        			    ,'goodsclassname' 
	        			    ,'goodsclassparent' 
	        			    ,'goodsclassdetail' 
	        			    ,'goodsclassstatue' 
	        			    ,'goodsclasscity' 
	        			    ,'goodsclassorder' 
	        			    ,'goodsclasscompany' 
	        			    ,'companyshop' 
	        			    ,'companycity' 
	        			    ,'companyaddress' 
	        			    ,'companydetail' 
	        			    ,'companystatue' 
	        			    ,'pricesid' 
	        			    ,'pricesclass' 
	        			    ,'priceslevel' 
	        			    ,'pricesprice' 
	        			    ,'pricesunit' 
	        			    ,'pricesprice2' 
	        			    ,'pricesunit2' 
	        			      ];// 全部字段
	var Goodsclassfields = ['goodsclassid'
	        			    ,'goodsclassname' 
	        			      ];// 小类字段
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
	var Goodskeycolumn = [ 'goodsid' ];// 主键
	var goodsStoreURL = basePath + Goodsaction + "?method=queryCompanyGoods&wheresql=goodscompany='"+comid+"'";
	if(goodsstatue != ''){
		goodsStoreURL += " and goodsstatue='"+goodsstatue+"'";		//上架/下架 商品
	}
	if(goodstype != ''){
		goodsStoreURL += " and goodstype='"+goodstype+"'";		//裸价商品
	}
	var Goodsstore = dataStore(Goodsfields, goodsStoreURL);// 定义Goodsstore
	Goodsclassstore = dataStore(Goodsclassfields, "CPGoodsclassAction.do?method=queryCompanyGoodsclass&wheresql=goodsclasscompany='"+comid+"'");//定义小类store
	/*Goodsclassstore.on('load',function(store){
		store.insert(0,{"goodsclassid": "", "goodsclassname": "不限制"}); //只添加一行用这个比较方便
	});*/
	Goodsclassstore.load();	//加载供应商小类
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"' and storehousestatue='启用'");// 定义Storehousestore
	Storehousestore.load();
	/*之前的查询条件*/
	var odQuery = '';
	Goodsstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				comid : comid,
				json : queryjson,
				goodsstatue : goodsstatue,
				cusType : Ext.getCmp('queryGoodsCusType').getValue(),
				query : Ext.getCmp("queryGoodsaction").getValue(),
				limit : Goodsbbar.pageSize
		};
		Ext.apply(Goodsstore.proxy.extraParams, new_params);    //ext 4.0
	});
	
	GoodsdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'GoodsdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'column',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品ID',
				id : 'Goodsgoodsid',
				name : 'goodsid',
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编码',
				id : 'Goodsgoodscode',
				allowBlank : false,
				name : 'goodscode',
				maxLength : 100,
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10',
				disabled : true
			}, {
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'Goodsgoodsname',
				allowBlank : false,
				name : 'goodsname',
				maxLength : 100,
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Goodsgoodsunits',
				allowBlank : false,
				name : 'goodsunits',
				maxLength : 100,
				labelWidth: 40,
				width : 302,
				margin : '5 10 5 10'
			}, {
				xtype : 'combo',
				fieldLabel : '小类',
				id : 'Goodsgoodsclass',
				name : 'goodsclass',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Goodsclassstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'goodsclassname',		//显示的字段
				valueField : 'goodsclassid',		//作为值的字段
				hiddenName : 'menulevel',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,			//不允许空白值
				anchor : '95%',
				labelWidth: 40,
				width : 302,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '其他类别',
				id : 'Goodsgoodstype',
				name : 'goodstype',
				maxLength : 100,
				allowBlank : false,
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10'
			}, {
				xtype : 'textfield',
				fieldLabel : '品牌',
				id : 'Goodsgoodsbrand',
				name : 'goodsbrand',
				maxLength : 100,
				labelWidth: 40,
				width : 302,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '重量(kg)',
				id : 'Goodsgoodsweight',
				name : 'goodsweight',
				maxLength : 100,
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10'
			}, {
				xtype : 'textfield',
				fieldLabel : '顺序',
				id : 'Goodsgoodsorder',
				name : 'goodsorder',
				maxLength : 100,
				labelWidth: 40,
				width : 302,
				margin : '5 10 5 10'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片路径',
				id : 'Goodsgoodsimage',
				name : 'goodsimage',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'column',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品数量',
				id : 'Goodsgoodsnumnum',
				name : 'Goodsnumnum',
				maxLength : 100,
				allowBlank : false,
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10'
			}, {
				xtype : 'combo',
				fieldLabel : '商品仓库',
				id : 'Goodsgoodsnumstore',
				name : 'Goodsnumstore',			//小类名称
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'Goodsnumstore',
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				maxLength : 100,
				anchor : '95%',
				labelWidth: 70,
				width : 302,
				margin : '5 10 5 10'
			} ]
		}
		]
	});
/////----------筛选商品(开始)----------/////
	var screenGoodsForm = Ext.create('Ext.form.Panel', {
		id:'screenGoodsForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'scrGoodsgoodscode',
				name : 'goodscode',
				maxLength : 100,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'scrGoodsgoodsname',
				name : 'goodsname',
				maxLength : 100,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'scrGoodsgoodsunits',
				name : 'goodsunits',
				maxLength : 100,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '小类',
				id : 'scrGoodsgoodsclass',
				name : 'goodsclass',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Goodsclassstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'goodsclassname',		//显示的字段
				valueField : 'goodsclassid',			//作为值的字段
				hiddenName : 'menulevel',
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
				fieldLabel : '其他类别',
				id : 'scrGoodsgoodstype',
				name : 'goodstype',
				maxLength : 100,
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '品牌',
				id : 'scrGoodsgoodsbrand',
				name : 'goodsbrand',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '重量(kg)',
				id : 'scrGoodsgoodsweight',
				name : 'goodsweight',
				maxLength : 100
			} ]
		}
		]
	});
/////----------筛选商品(结束)----------/////
	var goodsStaCombo = Ext.create('Ext.form.ComboBox', {

	    store : goodsStatueStore, 

	    allowBlank : true, 

	    displayField : 'name', 

	    valueField : 'name', 

	    emptyText : '状态', 

	    editable : false,  // 不可编辑 

	    listeners : {
	    	change : function(bzss , newValue , oldValue , eOpts){
		    	var selections = Goodsgrid.getSelection();
	        	$.ajax({
	        		url : 'CPGoodsAction.do?method=updAll',
	        		type : 'post',
	        		//dataType : 'json',
	        		data : {
	        			json : '[{"goodsid":"'+selections[0].data['goodsid']+'","goodsstatue":"'+newValue+'"}]'
	        		},
	        		success : function(resp){
	        			var data = eval('('+resp+')');
	        			if(data.code==202){
	        				alert('商品状态已修改');
	        			} else {
	        				alert('操作失败，商品可能已被删除。');
	        			}
	        		},
	        		error : function(resp){
	        			var data = eval('('+resp+')');
	        			alert('未知问题，操作失败。');
	        		}
	        	});
	        }
		 }
	});
	Goodsbbar = pagesizebar(Goodsstore);//定义分页
	var Goodsgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Goodstitle,
		store : Goodsstore,
		bbar : Goodsbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
	    plugins: {												//这里设置插件
	    	ptype: 'cellediting',									//类型为:单元格编辑
	    	clicksToEdit: 1										//点几下可以编辑
	    },
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		},
		{// 改
			header : '商品ID',
			dataIndex : 'goodsid',
			sortable : true,
			hidden : true
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
			header : '小类',
			dataIndex : 'goodsclassname',
			sortable : true, 
			width : 85,
		}
		, {
			header : '其他类别',
			dataIndex : 'goodstype',
			sortable : true, 
			width : 85,
		}
		, {
			header : '状态',
			dataIndex : 'goodsstatue',
			sortable : true,
			editor : goodsStaCombo,
			renderer : getOneDisplay,
			width : 70,
		}
		, {
			header : '品牌',
			dataIndex : 'goodsbrand',
			sortable : true, 
			width : 73,
		}
		, {
			header : '顺序',
			dataIndex : 'goodsorder',
			sortable : true, 
			width : 47,
		}
		, {
			header : '重量',
			dataIndex : 'goodsweight',
			sortable : true, 
			width : 47,
		}
		, {
			header : '修改时间',
			dataIndex : 'updtime',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'createtime',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'creator',
			sortable : true, 
			width : 73,
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
			id : 'queryGoodsaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryGoodsaction").getValue()) {
							Goodsstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Goodsstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryGoodsaction").getValue()
								}
							});
						}
					}
				}
			}
		},'-',{
			text : "筛选",
			iconCls : 'select',
			handler : function() {
				screenWindow("筛选", screenGoodsForm, Goodsstore);
			}
		},'-',{
        	text : "价格设置",
			iconCls : 'edit',
			handler : function() {
				var selections = Goodsgrid.getSelection();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择一条数据！');
					return;
				}
				GoodsPricesForm.form.reset();
				goodsPriceWindow("CPPricesAction.do?method=setGoodsPrices",Goodsstore,selections[0].data['goodsid']+'');
				GoodsPricesForm.form.loadRecord(selections[0]);
			}
        },'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "新增商品",
				iconCls : 'add',
				handler : function() {
					GoodsdataForm.form.reset();
					Ext.getCmp("Goodsgoodsid").setEditable (true);
					addGoodsWindow(basePath + Goodsaction + "?method=insGoods", "新增商品", GoodsdataForm, Goodsstore);
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Goodsgrid.getSelection();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					GoodsdataForm.form.reset();
					Ext.getCmp("Goodsgoodsid").setEditable (false);
					editGoodsWindow(basePath + "CPGoodsAction.do?method=updAll", "修改商品信息", GoodsdataForm, Goodsstore);
					GoodsdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
            	text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Goodsgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请至少选择一条数据！');
						return;
					}
					commonDelete(basePath + Goodsaction + "?method=deleteGoods",selections,Goodsstore,Goodskeycolumn);
				}
            }
		]
	});
	Goodsgrid.region = 'center';
	Goodsstore.load();//加载数据
	
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : false,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Goodsgrid ]
	});
})
