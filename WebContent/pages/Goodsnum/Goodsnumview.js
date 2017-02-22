var Goodsnumviewbbar;
var sumNum=0;
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';
var odQueryjson='';

Ext.onReady(function() {
	var Goodsnumviewclassify = "库存总账";
	var Goodsnumviewtitle = "当前位置:业务管理》" + Goodsnumviewclassify;
	var Goodsnumviewaction = "CPGoodsnumviewAction.do";
	var Goodsnumviewfields = ['idgoodsnum'
		        			    ,'goodsnumgoods' 
		        			    ,'goodsnumnum' 
		        			    ,'goodsnumstore' 
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
		        			    ,'goodsclassid' 
		        			    ,'goodsclassname'  
	        			      ];// 全部字段
	var Goodsnumviewkeycolumn = [ 'idgoodsnum' ];// 主键
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
	var wheresql = "goodscompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"'");// 定义Storehousestore
	Storehousestore.load();
	var Goodsnumviewstore = dataStore(Goodsnumviewfields, basePath + Goodsnumviewaction + "?method=selQueryCP");// 定义Goodsnumviewstore
	Goodsnumviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryGoodsnumviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				startDate : startDate+' 00:00:00',
				endDate : endDate+' 23:59:59',
				json : queryjson,
				query : query,
				limit : Goodsnumviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
		Ext.apply(Goodsnumviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	Goodsnumviewstore.on('load',function(store){						//数据加载后
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			sumNum = total.get('goodsnumnum');
		}
		Goodsnumviewstore.remove(total);
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
				fieldLabel : '商品编号',
				readOnly : true,
				id : 'Goodsnumviewgoodscode',
//				allowBlank : false,
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
				id : 'Goodsnumviewgoodsname',
//				allowBlank : false,
				name : 'goodsname',
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
				id : 'Goodsnumviewgoodsunits',
//				allowBlank : false,
				name : 'goodsunits',
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
				xtype : 'combo',
				fieldLabel : '仓库',
				id : 'Goodsnumviewgoodsnumstore',
				name : 'goodsnumstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'goodsnumstore',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				anchor : '95%',
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
	    features: [{							//要添加到网格中的网格特性数组。也可以只是一个单一的功能，而不是数组。
	        ftype: 'summary',
	        dock : 'bottom'
	    }],
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60,
		},
		{// 改
			header : 'ID',
			dataIndex : 'idgoodsnum',
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
			header : '商品规格',
			dataIndex : 'goodsunits',
			sortable : true, 
			width : 105,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return '合计:';
            },
		}
		, {
			header : '数量',
			dataIndex : 'goodsnumnum',
			sortable : true, 
			width : 48,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumNum);
            },
		}
		, {
			header : '仓库',
			dataIndex : 'goodsnumstore',
			sortable : true, 
			renderer : function(value){
				var md = Storehousestore.find('storehouseid',value);
				if(md!=-1){
					return Storehousestore.getAt(md).get('storehousename');
				} else {
					return '';
				}
			},
			width : 137,
		}
		],
		tbar : [{
				xtype : 'textfield',
				id : 'queryGoodsnumviewaction',
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
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
					Goodsnumviewstore.load();
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
	        					Ext.getCmp("Goodsnumviewidgoodsnum").setEditable (true);
	        					Ext.getCmp("Goodsnumviewgoodscode").setReadOnly (false);
	        					Ext.getCmp("Goodsnumviewgoodsname").setReadOnly (false);
	        					Ext.getCmp("Goodsnumviewgoodsunits").setReadOnly (false);
	        					Ext.getCmp("Goodsnumviewgoodsnumnum").allowBlank = true;
	        					createQueryWindow("筛选", GoodsnumviewdataForm, Goodsnumviewstore,Ext.getCmp("queryGoodsnumviewaction").getValue());
	        				}
	        			},{
	                    	text : "导出",
	        				iconCls : 'exp',
	        				handler : function() {
	        					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
	        						if (btn == 'yes') {
	        							window.location.href = basePath + Goodsnumviewaction + "?method=expAll&json="+queryjson+"&query="+Ext.getCmp("queryGoodsnumviewaction").getValue(); 
	        						}
	        					});
	        				}
	                    },{
	                    	text : "删除",
	        				iconCls : 'delete',
	        				handler : function() {
	        					var selections = Goodsnumviewgrid.getSelection();
	        					if (Ext.isEmpty(selections)) {
	        						Ext.Msg.alert('提示', '请至少选择一条数据！');
	        						return;
	        					}
	        					commonDelete(basePath + "CPGoodsnumAction.do?method=delAll",selections,Goodsnumviewstore,Goodsnumviewkeycolumn);
	        				}
	                    }]
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
