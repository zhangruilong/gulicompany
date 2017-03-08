var statueStore = new Ext.data.ArrayStore({//状态下拉
	fields:["name"],
	data:[["发货请求"],["已发货"]]
});
var Warrantoutviewbbar;
var sumNum = 0;
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';
var odQueryjson='';
Ext.onReady(function() {
	var Warrantoutviewclassify = "出库管理";
	var Warrantoutviewtitle = "当前位置:库存管理》" + Warrantoutviewclassify;
	var Warrantoutviewaction = "CPWarrantoutviewAction.do";
	var Warrantoutviewfields = ['idwarrantout'
		        			    ,'warrantoutcompany' 
		        			    ,'warrantoutstore' 
		        			    ,'warrantoutgoods' 
		        			    ,'warrantoutnum' 
		        			    ,'warrantoutstatue' 
		        			    ,'warrantoutdetail' 
		        			    ,'warrantoutwho' 
		        			    ,'warrantoutinswhen' 
		        			    ,'warrantoutinswho' 
		        			    ,'warrantoutupdwhen' 
		        			    ,'warrantoutupdwho' 
		        			    ,'warrantoutgcode' 
		        			    ,'warrantoutgname' 
		        			    ,'warrantoutgunits' 
		        			    ,'warrantoutgtype' 
		        			    ,'warrantoutggclass' 
		        			    ,'warrantoutgunit' 
		        			    ,'warrantoutgweight' 
		        			    ,'warrantoutordnote' 
		        			    ,'warrantoutprice'
		        			    ,'warrantoutmoney'
		        			    ,'goodsid' 
		        			    ,'goodscompany' 
		        			    ,'goodscode' 
		        			    ,'goodsname' 
		        			    ,'goodsunits' 
		        			    ,'storehousename' 
		        			      ];// 全部字段
	var Warrantoutviewkeycolumn = [ 'idwarrantout' ];// 主键
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
	var wheresql = "warrantoutcompany='"+comid+"'";
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"' and storehousestatue='启用'");// 定义Storehousestore
	Storehousestore.load();
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	var Warrantoutviewstore = dataStore(Warrantoutviewfields, basePath + Warrantoutviewaction + "?method=selQueryCP");// 定义Warrantoutviewstore
	Warrantoutviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryWarrantoutviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				wheresql : wheresql,
				comid : comid,
				startDate : startDate+' 00:00:00',
				endDate : endDate+' 23:59:59',
				json : queryjson,
				query : query,
				limit : Warrantoutviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery || queryjson!=odQueryjson){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			odQueryjson = queryjson;
			store.loadPage(1);
		}
		Ext.apply(Warrantoutviewstore.proxy.extraParams, new_params);    //ext 4.0
	});
	Warrantoutviewstore.on('load',function(store){						//数据加载后
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			sumNum = total.get('warrantoutnum');
		}
		Warrantoutviewstore.remove(total);
	});
	var WarrantoutviewdataForm = Ext.create('Ext.form.Panel', {// 定义新增的FormPanel
		id:'WarrantoutviewdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '出库台账ID',
				id : 'Warrantoutviewidwarrantout',
				name : 'idwarrantout',
				maxLength : 100,
				readOnly : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品',
				id : 'Warrantoutviewwarrantoutgoods',
				name : 'warrantoutgoods',
				maxLength : 100,
				readOnly : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品编码',
				id : 'Warrantoutviewwarrantoutgcode',
				name : 'warrantoutgcode',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '商品名称',
				id : 'Warrantoutviewwarrantoutgname',
				name : 'warrantoutgname',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Warrantoutviewwarrantoutgunits',
				name : 'warrantoutgunits',
				allowBlank : false,
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '仓库',
				id : 'Warrantoutviewwarrantoutstore',
				name : 'warrantoutstore',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Storehousestore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'storehousename',		//显示的字段
				valueField : 'storehouseid',		//作为值的字段
				hiddenName : 'warrantoutstore',
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
				fieldLabel : '数量',
				id : 'Warrantoutviewwarrantoutnum',
				allowBlank : false,
				name : 'warrantoutnum',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '销售单价',
				id : 'Warrantoutwarrantoutprice',
				name : 'warrantoutprice',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '销售金额',
				id : 'Warrantoutwarrantoutmoney',
				name : 'warrantoutmoney',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Warrantoutviewwarrantoutstatue',
				name : 'warrantoutstatue',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : statueStore,
				value : '已发货',
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'name',		//显示的字段
				valueField : 'name',		//作为值的字段
				hiddenName : 'warrantoutstatue',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '领货人',
				id : 'Warrantoutviewwarrantoutwho',
				name : 'warrantoutwho',			//小类名称
				//loadingText: 'loading...',			//正在加载时的显示
				//editable : false,						//是否可编辑
				emptyText : '请选择',
				store : Empstore,
				mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
												//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
				displayField : 'empcode',		//显示的字段
				valueField : 'empcode',		//作为值的字段
				hiddenName : 'warrantoutwho',
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '备注',
				id : 'Warrantoutviewwarrantoutdetail',
				name : 'warrantoutdetail',
				maxLength : 100
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			hidden : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '其他类别',
				id : 'Warrantoutviewwarrantoutgtype',
				name : 'warrantoutgtype',
				maxLength : 100
			} ]
		}
		]
	});
	Warrantoutviewbbar = pagesizebar(Warrantoutviewstore);//定义分页
	var Warrantoutviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantoutviewtitle,
		store : Warrantoutviewstore,
		bbar : Warrantoutviewbbar,
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
			header : 'ID',
			dataIndex : 'idwarrantout',
			sortable : true, 
			hidden : true,
		}
		, {
			header : '商品',
			dataIndex : 'warrantoutgoods',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '商品编码',
			dataIndex : 'warrantoutgcode',
			sortable : true,  
			width : 158,
		}
		, {
			header : '商品名称',
			dataIndex : 'warrantoutgname',
			sortable : true,  
			width : 137,
		}
		, {
			header : '规格',
			dataIndex : 'warrantoutgunits',
			sortable : true,  
			width : 105,
		}
		, {
			header : '仓库',
			dataIndex : 'warrantoutstore',
			sortable : true, 
			width : 137,
			renderer : function(value){
				var md = Storehousestore.find('storehouseid',value);
				if(md!=-1){
					return Storehousestore.getAt(md).get('storehousename');
				} else {
					return '';
				}
			},
			summaryRenderer: function(value, summaryData, dataIndex) {
                return "合计:";
            },
		}
		, {
			header : '数量',
			dataIndex : 'warrantoutnum',
			sortable : true, 
			width : 48,
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumNum);
            },
		}
		, {
			header : '销售单价',
			dataIndex : 'warrantoutprice',
			sortable : true,  
			width : 73,
		}
		, {
			header : '销售金额',
			dataIndex : 'warrantoutmoney',
			sortable : true,  
			width : 73,
		}
		, {
			header : '状态',
			dataIndex : 'warrantoutstatue',
			sortable : true, 
			width : 73,
		}
		, {
			header : '备注',
			dataIndex : 'warrantoutdetail',
			sortable : true, 
			width : 73,
		}
		, {
			header : '领货人',
			dataIndex : 'warrantoutwho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantoutinswhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '创建人',
			dataIndex : 'warrantoutinswho',
			sortable : true, 
			width : 73,
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantoutupdwhen',
			sortable : true, 
			width:138,
		}
		, {
			header : '修改人',
			dataIndex : 'warrantoutupdwho',
			sortable : true, 
			width : 73,
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantoutviewaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("queryWarrantoutviewaction").getValue()) {
							Warrantoutviewstore.load({
								params : {
									json : queryjson
								}
							});
						} else {
							Warrantoutviewstore.load({
								params : {
									json : queryjson,
									query : Ext.getCmp("queryWarrantoutviewaction").getValue()
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
				Warrantoutviewstore.load();
			}
		},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "出库",
				iconCls : 'add',
				handler : function() {
					WarrantoutviewdataForm.form.reset();
					Ext.getCmp("Warrantoutviewidwarrantout").setEditable (true);
					Ext.getCmp('Warrantoutviewwarrantoutstatue').setVisible(false);	//隐藏
					Ext.getCmp('Warrantoutviewwarrantoutgcode').setReadOnly(true);
					Ext.getCmp('Warrantoutviewwarrantoutgname').setReadOnly(true);
					Ext.getCmp('Warrantoutviewwarrantoutgunits').setReadOnly(true);
					addWarrantoutWindow(basePath + "CPWarrantoutAction.do?method=insWarrantout", "新增出库台账", WarrantoutviewdataForm, Warrantoutviewstore);
				}
		},'-',{
			text : Ext.os.deviceType === 'Phone' ? null : "修改",
			iconCls : 'edit',
			handler : function() {
				var selections = Warrantoutviewgrid.getSelection();
				if (selections.length != 1) {
					Ext.Msg.alert('提示', '请选择一条数据！', function() {
					});
					return;
				}
				if(selections[0].data['warrantoutstatue']=='已发货'){
					Ext.Msg.alert('提示','不能修改已发货的台账记录。');
					return;
				}
				WarrantoutviewdataForm.form.reset();
				Ext.getCmp("Warrantoutviewidwarrantout").setEditable (false);
				Ext.getCmp('Warrantoutviewwarrantoutstatue').setVisible(true);	//显示
				Ext.getCmp('Warrantoutviewwarrantoutgcode').setReadOnly(true);
				Ext.getCmp('Warrantoutviewwarrantoutgname').setReadOnly(true);
				Ext.getCmp('Warrantoutviewwarrantoutgunits').setReadOnly(true);
				editWarrantoutWindow(basePath + "CPWarrantoutAction.do?method=updWarrantout", "修改", WarrantoutviewdataForm, Warrantoutviewstore);
				WarrantoutviewdataForm.form.loadRecord(selections[0]);
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
            				WarrantoutviewdataForm.form.reset();
            				Ext.getCmp("Warrantoutviewidwarrantout").setEditable (true);
            				Ext.getCmp('Warrantoutviewwarrantoutgcode').setReadOnly(false);
            				Ext.getCmp('Warrantoutviewwarrantoutgname').setReadOnly(false);
            				Ext.getCmp('Warrantoutviewwarrantoutgunits').setReadOnly(false);
            				outQueryWindow("筛选", WarrantoutviewdataForm, Warrantoutviewstore,Ext.getCmp("queryWarrantoutviewaction").getValue());
            			}
            		},{
                    	text : "导出",
            			iconCls : 'exp',
            			handler : function() {
            				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
            					if (btn == 'yes') {
            						window.location.href = basePath + Warrantoutviewaction + "?method=expAllCP&json="+queryjson+
            						"&query="+Ext.getCmp("queryWarrantoutviewaction").getValue()+"&wheresql="+wheresql+
    								"&startDate="+startDate+" 00:00:00&endDate="+endDate+" 23:59:59"; 
            					}
            				});
            			}
                    },{
                    	text : "回滚",
        				iconCls : 'delete',
        				handler : function() {
        					var selections = Warrantoutviewgrid.getSelection();
        					if (Ext.isEmpty(selections)) {
        						Ext.Msg.alert('提示', '请至少选择一条数据！');
        						return;
        					}
        					delWarrantout(basePath + "CPWarrantoutAction.do?method=delWarrantout",selections,Warrantoutviewstore,Warrantoutviewkeycolumn);
        				}
                    },{
                    	text : "删除",
        				iconCls : 'delete',
        				handler : function() {
        					var selections = Warrantoutviewgrid.getSelection();
        					if (Ext.isEmpty(selections)) {
        						Ext.Msg.alert('提示', '请至少选择一条数据！');
        						return;
        					}
        					commonDelete(basePath + "CPWarrantoutAction.do?method=delAll",selections,Warrantoutviewstore,Warrantoutviewkeycolumn);
        				}
                    }]
                }
            }
		}
		]
	});
	Warrantoutviewgrid.region = 'center';
	Warrantoutviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantoutviewgrid ]
	});
})
