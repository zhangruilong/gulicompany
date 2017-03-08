var Warrantcheckviewbbar;
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';
var odQueryjson='';
Ext.onReady(function() {
	var Warrantcheckviewclassify = "库存盘点";
	var Warrantcheckviewtitle = "当前位置:库存管理》" + Warrantcheckviewclassify;
	var Warrantcheckviewaction = "CPWarrantcheckviewAction.do";
	var Warrantcheckviewfields = ['idwarrantcheck'
	  	        			    ,'warrantcheckstore' 
	  	        			    ,'warrantcheckgoods' 
	  	        			    ,'warrantchecknumorg' 
	  	        			    ,'warrantchecknumnow' 
	  	        			    ,'warrantcheckstatue' 
	  	        			    ,'warrantcheckdetail' 
	  	        			    ,'warrantcheckinswho' 
	  	        			    ,'warrantcheckinswhen' 
	  	        			    ,'warrantcheckupdwho' 
	  	        			    ,'warrantcheckupdwhen' 
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
     			    ,'empcode' 
     			      ];// 全部字段
	var Warrantcheckviewkeycolumn = [ 'idwarrantcheck' ];// 主键
	var wheresql = "goodscompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"' and storehousestatue='启用'");// 定义Storehousestore
	Storehousestore.load();
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	var Warrantcheckviewstore = dataStore(Warrantcheckviewfields, basePath + Warrantcheckviewaction + "?method=selQueryCP");// 定义Warrantcheckviewstore
	Warrantcheckviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryWarrantcheckviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				startDate : startDate+' 00:00:00',
				endDate : endDate+' 23:59:59',
				json : queryjson,
				query : query,
				limit : Warrantcheckviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
		Ext.apply(Warrantcheckviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	var WarrantcheckviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增和修改的FormPanel
		id:'WarrantcheckviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '盘点记录ID',
				id : 'Warrantcheckviewidwarrantcheck',
				name : 'idwarrantcheck',
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
				id : 'Warrantcheckviewwarrantcheckgoods',
				allowBlank : false,
				name : 'warrantcheckgoods',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编号',
				id : 'Warrantcheckviewgoodscode',
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
				id : 'Warrantcheckviewgoodsname',
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
				id : 'Warrantcheckviewgoodsunits',
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
				id : 'Warrantcheckviewwarrantcheckstore',
				name : 'warrantcheckstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'warrantcheckstore',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				anchor : '95%',
				listeners: {
					change: function(obj, newValue, oldValue, eOpts){
						var goodsid = Ext.getCmp('Warrantcheckviewwarrantcheckgoods').getValue();
						if(typeof(goodsid)!='undefined' && goodsid){
							$.ajax({
								url: 'CPGoodsnumAction.do',
								type: 'post',
								data: {
									method: 'selAll',
									wheresql: "goodsnumgoods='"+goodsid+"' and goodsnumstore='"+newValue+"'"
								},
								success: function(resp){
									var data = eval('('+resp+')');
									if(data.root.length >0){
										Ext.getCmp('Warrantcheckviewwarrantchecknumorg').setValue(data.root[0].goodsnumnum);
									} else {
//										Ext.Msg.alert('提示','没有查询到商品数量。');
									}
								},
								error: function(resp){
									
								}
							});
						}
					}
				}
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '现有数量',
				id : 'Warrantcheckviewwarrantchecknumnow',
				allowBlank : false,
				name : 'warrantchecknumnow',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '应有数量',
				id : 'Warrantcheckviewwarrantchecknumorg',
				allowBlank : false,
				name : 'warrantchecknumorg',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '盘点人',
				id : 'Warrantcheckwarrantcheckor',
				name : 'warrantcheckor',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Empstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'empcode',		//显示的字段
				valueField : 'empcode',		//作为值的字段
				hiddenName : 'warrantcheckor',
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
				fieldLabel : '描述',
				id : 'Warrantcheckviewwarrantcheckdetail',
				name : 'warrantcheckdetail',
				maxLength : 100
			} ]
		}
		]
	});
	
	Warrantcheckviewbbar = pagesizebar(Warrantcheckviewstore);//定义分页
	var Warrantcheckviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantcheckviewtitle,
		store : Warrantcheckviewstore,
		bbar : Warrantcheckviewbbar,
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
			header : '盘点记录ID',
			dataIndex : 'idwarrantcheck',
			hidden : true,
			sortable : true, 
		}
		, {
			header : '商品ID',
			dataIndex : 'warrantcheckgoods',
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
			dataIndex : 'warrantcheckstore',
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
		, {
			header : '现有数量',
			dataIndex : 'warrantchecknumnow',
			sortable : true, 
			width : 73,
		}
		, {
			header : '应有数量',
			dataIndex : 'warrantchecknumorg',
			sortable : true, 
			width : 73,
		}
		, {
			header : '盘点人',
			dataIndex : 'warrantcheckor',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '描述',
			dataIndex : 'warrantcheckdetail',
			sortable : true, 
			width : 137,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantcheckinswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantcheckinswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'warrantcheckupdwho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantcheckupdwhen',
			sortable : true, 
			width:138,
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantcheckviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantcheckviewaction").getValue()) {
							Warrantcheckviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantcheckviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantcheckviewaction").getValue()
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
				Warrantcheckviewstore.load();
			}
		},'-',{
				text : "盘点",
				iconCls : 'add',
				handler : function() {
					Ext.getCmp("Warrantcheckviewgoodscode").setReadOnly (true);
					Ext.getCmp("Warrantcheckviewgoodsname").setReadOnly (true);
					Ext.getCmp("Warrantcheckviewgoodsunits").setReadOnly (true);
					goodsWindow(WarrantcheckviewdataForm,Storehousestore,Warrantcheckviewstore);
					
				}
		},'-',{
			text: '更多操作',
            menu: {
            	xtype: 'menu',
                items: {
                	xtype: 'buttongroup',
                    columns: 3,
                    items: [{
            			text : "筛选",
            			iconCls : 'select',
            			handler : function() {
            				WarrantcheckviewdataForm.form.reset();
            				Ext.getCmp("Warrantcheckviewidwarrantcheck").setEditable (true);
            				Ext.getCmp("Warrantcheckviewgoodscode").setReadOnly (false);
        					Ext.getCmp("Warrantcheckviewgoodsname").setReadOnly (false);
        					Ext.getCmp("Warrantcheckviewgoodsunits").setReadOnly (false);
            				checkQueryWindow("筛选", WarrantcheckviewdataForm, Warrantcheckviewstore,Ext.getCmp("queryWarrantcheckviewaction").getValue());
            			}
            		},{
                    	text : "导出",
            			iconCls : 'exp',
            			handler : function() {
            				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
            					if (btn == 'yes') {
            						window.location.href = basePath + Warrantcheckviewaction + "?method=expAllCP&json="+queryjson+
            						"&query="+Ext.getCmp("queryWarrantcheckviewaction").getValue()+"&wheresql="+wheresql+
    								"&startDate="+startDate+" 00:00:00&endDate="+endDate+" 23:59:59"; 
            					}
            				});
            			}
                    },{
                    	text : "导入检查",
            			iconCls : 'imp',
            			handler : function() {
            				commonImp(basePath + "CPWarrantcheckAction.do?method=impCheck","导入",Warrantcheckviewstore);
            			}
                    },{
                    	text : "导入",
            			iconCls : 'imp',
            			handler : function() {
            				commonImp(basePath + "CPWarrantcheckAction.do?method=impWarrantcheck","导入",Warrantcheckviewstore);
            			}
                    },{
            			text : "回滚",
    					iconCls : 'delete',
    					handler : function() {
    						var selections = Warrantcheckviewgrid.getSelection();
    						if (selections.length != 1) {
    							Ext.Msg.alert('提示', '请选择一条数据！');
    							return;
    						}
    						if(selections[0].data['warrantcheckstatue'] =='已回滚'){
        						Ext.Msg.alert('提示', '不能回滚已回滚的数据。');
        						return;
        					}
    						checkRollBACK(basePath + "CPWarrantcheckAction.do?method=checkRollBACK",selections,Warrantcheckviewstore);
    					}
            		},{
                    	text : "删除",
            			iconCls : 'delete',
            			handler : function() {
            				var selections = Warrantcheckviewgrid.getSelection();
            				if (selections.length != 1) {
            					Ext.Msg.alert('提示', '请选择一条数据！');
            					return;
            				}
            				commonDelete(basePath + "CPWarrantcheckAction.do?method=delAll",selections,Warrantcheckviewstore,Warrantcheckviewkeycolumn);
            			}
                    }]
                }
            }
		}
		]
	});
	Warrantcheckviewgrid.region = 'center';
	Warrantcheckviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantcheckviewgrid ]
	});
})
