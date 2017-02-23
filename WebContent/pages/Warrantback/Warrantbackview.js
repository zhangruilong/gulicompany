var Warrantbackviewbbar;
var statueStore = new Ext.data.ArrayStore({//状态下拉
	fields:["name","value"],
	data:[["完好退货","good"],["报废退货","waste"],["采购退货","purchase"]]
});
var sumNum = 0;
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';
var odQueryjson='';
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
	 	        			    ,'goodscode' 
	 	        			    ,'goodsname' 
	 	        			    ,'goodsunits' 
	 	        			    ,'storehousename' 
	 	        			      ];// 全部字段
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
	var Empfields = ['empid'
	     			    ,'empcompany' 
	     			    ,'empcode' 
	     			    ,'loginname' 
	     			    ,'password' 
	     			    ,'empdetail' 
	     			    ,'empstatue' 
	     			    ,'createtime' 
	     			    ,'updtime' 
	     			      ];// 全部字段
	var Warrantbackviewkeycolumn = [ 'idwarrantback' ];// 主键
	var wheresql = "goodscompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"'");// 定义Storehousestore
	Storehousestore.load();
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	var Warrantbackviewstore = dataStore(Warrantbackviewfields, basePath + Warrantbackviewaction + "?method=selQueryCP");// 定义Warrantbackviewstore
	Warrantbackviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryWarrantbackviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				startDate : startDate+' 00:00:00',
				endDate : endDate+' 23:59:59',
				json : queryjson,
				query : query,
				limit : Warrantbackviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
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
				xtype : 'combo',
				fieldLabel : '仓库',
				id : 'Warrantbackviewwarrantbackstore',
				name : 'warrantbackstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'warrantbackstore',
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
				xtype : 'combo',
				fieldLabel : '退货人',
				id : 'Warrantbackviewwarrantbackwho',
				name : 'warrantbackwho',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Empstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'empcode',		//显示的字段
				valueField : 'empcode',		//作为值的字段
				hiddenName : 'warrantbackwho',
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
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Warrantbackviewwarrantbackstatue',
				name : 'warrantbackstatue',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'value',		//作为值的字段
				hiddenName : 'warrantbackstatue',
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				maxLength : 100,
				anchor : '95%',
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
			renderer : function(value){
				if(value=='回滚'){
					return value;
				} else {
					var md = Storehousestore.find('storehouseid',value);
					if(md!=-1){
						return Storehousestore.getAt(md).get('storehousename');
					} else {
						return '';
					}
				}
			},
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
			width : 73,
			renderer : function(value){
				var md = statueStore.find('value',value);
				if(md!=-1){
					return statueStore.getAt(md).get('name');
				} else {
					return '';
				}
			},
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
			xtype: 'datefield',
			fieldLabel : '',
			labelWidth:8,				//标签宽度
			id:"startDate",
			name:"startDate",
			editable:false, //不允许对日期进行编辑
			width:100,
			format:"Y-m-d",
			emptyText:"请选择日期",		//默认显示的日期
			value: startDate
		},{
			xtype: 'datefield',
			fieldLabel : '-',
			labelSeparator : '',
			labelWidth:8,
			id:"endDate",
			name:"endDate",
			editable:false, //不允许对日期进行编辑
			width:113,
			format:"Y-m-d",
			emptyText:"请选择日期",		//默认显示的日期
			value: endDate
		},{
			text : "查询",
			xtype: 'button',
			handler : function() {
				startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d');		//得到时间选择框中的开始时间
				endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d');			//结束时间
				Warrantbackviewstore.load();
			}
		},'-',{
			text : Ext.os.deviceType === 'Phone' ? null : "退货",
			iconCls : 'add',
			handler : function() {
				WarrantbackviewdataForm.form.reset();
				Ext.getCmp("Warrantbackviewidwarrantback").setEditable (true);
				addWarrantbackWindow(basePath + "CPWarrantbackAction.do?method=addWarrantback", "新增退货台账", WarrantbackviewdataForm, Warrantbackviewstore);
			}
		},'-',{
			text: '操作',
            menu: {
            	xtype: 'menu',
                items: {
                	xtype: 'buttongroup',
                    columns: 3,
                    items: [{
            			text : "筛选",
            			iconCls : 'select',
            			handler : function() {
            				Ext.getCmp("Warrantbackviewidwarrantback").setEditable (true);
            				createQueryWindow("筛选", WarrantbackviewdataForm, Warrantbackviewstore,Ext.getCmp("queryWarrantbackviewaction").getValue());
            			}
            		},{
                    	text : "导出",
            			iconCls : 'exp',
            			handler : function() {
            				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
            					if (btn == 'yes') {
            						window.location.href = basePath + Warrantbackviewaction + "?method=expAllCP&json="+queryjson+
            								"&query="+Ext.getCmp("queryWarrantbackviewaction").getValue()+"&wheresql="+wheresql+
            								"&startDate="+startDate+" 00:00:00&endDate="+endDate+" 23:59:59"; 
            					}
            				});
            			}
                    },{
                    	text : "回滚",
        				iconCls : 'delete',
        				handler : function() {
        					var selections = Warrantbackviewgrid.getSelection();
        					if (Ext.isEmpty(selections)) {
        						Ext.Msg.alert('提示', '请至少选择一条数据！');
        						return;
        					}
        					backRollBACK(basePath + "CPWarrantbackAction.do?method=backRollBack",selections,Warrantbackviewstore);
        				}
                    },{
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
                    }]
                }
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
