var goodsStatueStore = new Ext.data.ArrayStore({//状态下拉
    	fields:["name"],
    	data:[["上架"],["下架"]]
    });
var Goodsbbar;
var Goodsclassstore;
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
	
	/*之前的查询条件*/
	var odQuery = '';
	Goodsclassstore.load();	//加载供应商小类
	Goodsstore.on('beforeload',function(store,options){					//数据加载时的事件
		var new_params = {		//每次数据加载的时候传递的参数
				comid : comid,
				json : queryjson,
				goodsstatue : goodsstatue,
				query : Ext.getCmp("queryGoodsaction").getValue(),
				limit : Goodsbbar.pageSize
		};
		Ext.apply(Goodsstore.proxy.extraParams, new_params);    //ext 4.0
	});
	
	var GoodsdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'GoodsdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品ID',
				id : 'Goodsgoodsid',
				name : 'goodsid'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Goodsgoodscode',
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
				fieldLabel : '名称',
				id : 'Goodsgoodsname',
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
				id : 'Goodsgoodsunits',
				allowBlank : false,
				name : 'goodsunits',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '类型',
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
				anchor : '95%'
			} ]
		}
		
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '其他类别',
				id : 'Goodsgoodstype',
				name : 'goodstype',
				maxLength : 100,
				allowBlank : false
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片',
				id : 'Goodsgoodsimage',
				name : 'goodsimage',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '品牌',
				id : 'Goodsgoodsbrand',
				name : 'goodsbrand',
				maxLength : 100
			} ]
		}
		
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '顺序',
				id : 'Goodsgoodsorder',
				name : 'goodsorder',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '重量',
				id : 'Goodsgoodsweight',
				name : 'goodsweight',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品数量',
				id : 'Goodsgoodsnumnum',
				name : 'Goodsnumnum',
				maxLength : 100,
				allowBlank : false
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品仓库',
				id : 'Goodsgoodsnumstore',
				name : 'Goodsnumstore',
				maxLength : 100,
				allowBlank : false
			} ]
		}
		]
	});
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
			header : '编号',
			dataIndex : 'goodscode',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '名称',
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
			header : '描述',
			dataIndex : 'goodsdetail',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '小类',
			dataIndex : 'goodsclassname',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '其他类别',
			dataIndex : 'goodstype',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '状态',
			dataIndex : 'goodsstatue',
			sortable : true,
			editor : goodsStaCombo,
			renderer : getOneDisplay
		}
		, {
			header : '品牌',
			dataIndex : 'goodsbrand',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '顺序',
			dataIndex : 'goodsorder',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '重量',
			dataIndex : 'goodsweight',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'updtime',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'createtime',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '创建人',
			dataIndex : 'creator',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		],
		tbar : [{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					GoodsdataForm.form.reset();
					Ext.getCmp("Goodsgoodsid").setEditable (true);
					addGoodsWindow(basePath + Goodsaction + "?method=insGoods", "新增", GoodsdataForm, Goodsstore);
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
					editGoodsWindow(basePath + "CPGoodsAction.do?method=updAll", "修改", GoodsdataForm, Goodsstore);
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
            },'-',{
            	text : "商品价格",
				iconCls : 'edit',
				handler : function() {
					var selections = Goodsgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择一条数据！');
						return;
					}
					GoodsPricesForm.form.reset();
					goodsPriceWindow("CPPricesAction.do?method=setGoodsPrices",Goodsstore,selections[0].data['goodsid']+'');
					/*window.parent.main.location.href = '../Prices/setPrices.jsp?goodsid='+selections[0].data['goodsid']+
										'&goodscode='+selections[0].data['goodscode']+
										'&goodsname='+selections[0].data['goodsname']+
										'&goodsunits='+selections[0].data['goodsunits'];*/
					GoodsPricesForm.form.loadRecord(selections[0]);
				}
            },'->',{
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
