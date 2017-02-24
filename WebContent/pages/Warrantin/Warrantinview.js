var Warrantinviewbbar;
var sumPrice = 0;
var sumNum = 0;
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';
var odQueryjson='';
Ext.onReady(function() {
	var Warrantinviewclassify = "入库台账";
	var Warrantinviewtitle = "当前位置:库存管理》" + Warrantinviewclassify;
	var Warrantinviewaction = "CPWarrantinviewAction.do";
	var Warrantinviewfields = ['idwarrantin'
		        			    ,'warrantinstore' 
		        			    ,'warrantinfrom' 
		        			    ,'warrantingoods' 
		        			    ,'warrantinprice' 
		        			    ,'warrantinnum' 
		        			    ,'warrantinwho' 
		        			    ,'warrantinstatue' 
		        			    ,'warrantindetail' 
		        			    ,'warrantininswhen' 
		        			    ,'warrantininswho' 
		        			    ,'warrantinupdwhen' 
		        			    ,'warrantinupdwho' 
		        			    ,'goodsid' 
		        			    ,'goodscode' 
		        			    ,'goodsname' 
		        			    ,'goodsunits' 
		        			    ,'storehousename' 
		        			    ,'suppliername' 
		        			      ];// 全部字段
	var Warrantinviewkeycolumn = [ 'idwarrantin' ];// 主键
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
	var Supplierfields = ['supplierid'
	        			    ,'suppliercode' 
	        			    ,'suppliername' 
	        			    ,'suppliercontact' 
	        			    ,'supplierphone' 
	        			    ,'supplieraddress' 
	        			    ,'supplierdetail' 
	        			    ,'supplierstatue' 
	        			    ,'suppliercompany' 
	        			    ,'supplierupdtime' 
	        			    ,'supplierupdor' 
	        			    ,'suppliercretime' 
	        			    ,'suppliercreor' 
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
	var wheresql = "goodscompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"'");// 定义Storehousestore
	Storehousestore.load();
	var Supplierstore = dataStore(Supplierfields, basePath + "CPSupplierAction.do?method=selAll&wheresql=suppliercompany='"+comid+"'");// 定义Supplierstore
	Supplierstore.load();
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	var Warrantinviewstore = dataStore(Warrantinviewfields, basePath + Warrantinviewaction + "?method=selQueryCP");// 定义Warrantinviewstore
	Warrantinviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryWarrantinviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				startDate : startDate+' 00:00:00',
				endDate : endDate+' 23:59:59',
				json : queryjson,
				query : query,
				limit : Warrantinviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
		Ext.apply(Warrantinviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	Warrantinviewstore.on('load',function(store){						//数据加载后
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			sumPrice = total.get('warrantinprice');
			sumNum = total.get('warrantinnum');
		}
		Warrantinviewstore.remove(total);
	});
	var WarrantinviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantinviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '入库台账ID',
				id : 'Warrantinviewidwarrantin',
				name : 'idwarrantin',
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
				id : 'Warrantinviewwarrantingoods',
				allowBlank : false,
				readOnly : true,
				name : 'warrantingoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantinviewgoodscode',
				readOnly : true,
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
				id : 'Warrantinviewgoodsname',
				readOnly : true,
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
				id : 'Warrantinviewgoodsunits',
				readOnly : true,
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
				id : 'Warrantinviewwarrantinstore',
				name : 'warrantinstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'warrantinstore',
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
				fieldLabel : '供货单位',
				id : 'Warrantinviewwarrantinfrom',
				name : 'warrantinfrom',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Supplierstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'suppliername',		//显示的字段
				valueField : 'supplierid',		//作为值的字段
				hiddenName : 'warrantinfrom',
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
				fieldLabel : '进货价',
				id : 'Warrantinviewwarrantinprice',
				allowBlank : false,
				name : 'warrantinprice',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '数量',
				id : 'Warrantinviewwarrantinnum',
				allowBlank : false,
				name : 'warrantinnum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '检验员',
				id : 'Warrantinviewwarrantinwho',
				name : 'warrantinwho',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Empstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'empcode',		//显示的字段
				valueField : 'empcode',		//作为值的字段
				hiddenName : 'warrantinwho',
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
				fieldLabel : '备注',
				id : 'Warrantinviewwarrantindetail',
				name : 'warrantindetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantinviewbbar = pagesizebar(Warrantinviewstore);//定义分页
	var Warrantinviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantinviewtitle,
		store : Warrantinviewstore,
		bbar : Warrantinviewbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    features: {							//要添加到网格中的网格特性数组。也可以只是一个单一的功能，而不是数组。
	        ftype: 'summary',
	        dock : 'bottom'
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
			header : '入库台账ID',
			dataIndex : 'idwarrantin',
			hidden : true,
			sortable : true, 
		}
		, {
			header : '商品ID',
			dataIndex : 'warrantingoods',
			hidden : true,
			sortable : true,  
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
			dataIndex : 'storehousename',
			sortable : true, 
			width : 137,
		}
		, {
			header : '供货单位',
			dataIndex : 'suppliername',
			sortable : true, 
			width:150,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return "合计:";
            },
		}
		, {
			header : '进货价',
			dataIndex : 'warrantinprice',
			sortable : true, 
			width:60,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumPrice);
            },
		}
		, {
			header : '数量',
			dataIndex : 'warrantinnum',
			sortable : true, 
			width : 48,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumNum);
            },
		}
		, {
			header : '检验员',
			dataIndex : 'warrantinwho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '状态',
			dataIndex : 'warrantinstatue',
			sortable : true, 
			width : 47,
		}
		, {
			header : '备注',
			dataIndex : 'warrantindetail',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantininswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantininswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantinupdwhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'warrantinupdwho',
			sortable : true, 
			width : 73,
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantinviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantinviewaction").getValue()) {
							Warrantinviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantinviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantinviewaction").getValue()
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
				Warrantinviewstore.load();
			}
		},'-',{
			text : Ext.os.deviceType === 'Phone' ? null : "入库",
			iconCls : 'add',
			handler : function() {
				WarrantinviewdataForm.form.reset();
				Ext.getCmp("Warrantinviewidwarrantin").setEditable (true);
				Ext.getCmp("Warrantinviewgoodscode").setReadOnly (true);
				Ext.getCmp("Warrantinviewgoodsname").setReadOnly (true);
				Ext.getCmp("Warrantinviewgoodsunits").setReadOnly (true);
				Ext.getCmp("Warrantinviewwarrantinnum").allowBlank = false;
				addWarrantinWindow(basePath + "CPWarrantinAction.do?method=addWarrantin", "新增入库台账", WarrantinviewdataForm, Warrantinviewstore);
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
            				Ext.getCmp("Warrantinviewidwarrantin").setEditable (true);
            				Ext.getCmp("Warrantinviewgoodscode").setReadOnly (false);
            				Ext.getCmp("Warrantinviewgoodsname").setReadOnly (false);
            				Ext.getCmp("Warrantinviewgoodsunits").setReadOnly (false);
            				Ext.getCmp("Warrantinviewwarrantinnum").allowBlank = true;
            				createQueryWindow("筛选", WarrantinviewdataForm, Warrantinviewstore,Ext.getCmp("queryWarrantinviewaction").getValue());
            			}
            		},{
                    	text : "导出",
            			iconCls : 'exp',
            			handler : function() {
            				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
            					if (btn == 'yes') {
            						window.location.href = basePath + Warrantinviewaction + "?method=expAllCP&json="+queryjson+
            						"&query="+Ext.getCmp("queryWarrantinviewaction").getValue()+"&wheresql="+wheresql+
    								"&startDate="+startDate+" 00:00:00&endDate="+endDate+" 23:59:59"; 
            					}
            				});
            			}
                    },{
        				text : Ext.os.deviceType === 'Phone' ? null : "回滚",
						iconCls : 'delete',
						handler : function() {
							var selections = Warrantinviewgrid.getSelection();
							if (Ext.isEmpty(selections)) {
								Ext.Msg.alert('提示', '请至少选择一条数据！');
								return;
							}
							warrantinRollBACK(basePath + "CPWarrantinAction.do?method=warrantinRollBACK",selections,Warrantinviewstore);
						}
        		    },{
                    	text : "删除",
        				iconCls : 'delete',
        				handler : function() {
        					var selections = Warrantinviewgrid.getSelection();
        					if (Ext.isEmpty(selections)) {
        						Ext.Msg.alert('提示', '请至少选择一条数据！');
        						return;
        					}
        					commonDelete(basePath + "CPWarrantinAction.do?method=delAll",selections,Warrantinviewstore,Warrantinviewkeycolumn);
        				}
                    }]
                }
            }
		}
		]
	});
	Warrantinviewgrid.region = 'center';
	Warrantinviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantinviewgrid ]
	});
})
