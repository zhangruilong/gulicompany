var startDate = Ext.util.Format.date(new Date(),'Y-m-d')+' 00:00:00';			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d H:i:s');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';

var quBrand = [];			//查询的品牌
var quEmp = [];				//查询的业务员
var quCus = [];				//查询的客户店名

var numtotal = 0;				//合计数量
var weighttotal = 0;				//合计重量
var moneytotal = 0;				//合计金额
var rightmoneytotal = 0;				//合计实际金额

var Orderdgrid = new Object();
var Orderdstatbbar = new Object();
Ext.onReady(function() {
	var Orderdstatclassify = "订单商品统计";
	var Orderdstattitle = "当前位置:订单管理》" + Orderdstatclassify;
	var Orderdstataction = "CPOrderdAction.do";
	var Orderdstatfields = ['orderdcode'
	        			    ,'orderdname' 
	        			    ,'orderdunits' 
	        			    ,'orderdunit' 
	        			    ,'orderdprice' 
	        			    ,'sumorderdmoney' 
	        			    ,'sumorderdrightmoney' 
	        			    ,'sumorderdnum' 
	        			    ,'orderdweight' 
	        			      ];// 订单的全部字段
	var Orderdstatkeycolumn = [ 'ordermid' ];// 主键
	var Orderdstatstore = dataStore(Orderdstatfields, basePath + Orderdstataction + "?method=orderdStatistics");// 定义Orderdstatstore
	
	Orderdstatstore.on('beforeload',function(store,options){					//数据加载前
		
		var start;
		var query = Ext.getCmp("queryOrderdstataction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				comid : comid,
				startDate : startDate,
				endDate : endDate,
				empNames : Ext.getCmp("quEmptextfield").getValue(),
				brandNames : Ext.getCmp("quBrandtextfield").getValue(),
				cusNames : Ext.getCmp("quCustextfield").getValue(),
				query : query,
				limit : Orderdstatbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			store.loadPage(1);
		}
		Ext.apply(Orderdstatstore.proxy.extraParams, new_params);    //ext 4.0
		
	});
	Orderdstatstore.on('load',function(store){						//数据加载后
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			numtotal = total.get('sumorderdnum');
			weighttotal = total.get('orderdweight');
			moneytotal = total.get('sumorderdmoney');
			rightmoneytotal = total.get('sumorderdrightmoney');
		}
		Orderdstatstore.remove(total);
	});
	Orderdstatbbar = pagesizebar(Orderdstatstore);		//定义分页
	
	/*   定义 orderm(订单总表) 的 表格  开始    */
	var Orderdstatgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Orderdstattitle,
		store : Orderdstatstore,
		bbar : Orderdstatbbar,
		features: [{							//要添加到网格中的网格特性数组。也可以只是一个单一的功能，而不是数组。
	        ftype: 'summary',
	        dock : 'bottom'
	    }],
		selModel: {
	        type: 'checkboxmodel'								//复选框
	    },
	    plugins: {												//这里设置插件
	         ptype: 'cellediting',									//类型为:单元格编辑
	         clicksToEdit: 1										//点几下可以编辑
	    },
	    /*设置表格*/
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		},
		{// 改
			header : '商品编码',
			dataIndex : 'orderdcode',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '商品名称',				//表头
			dataIndex : 'orderdname',		//对应字段
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '规格',
			dataIndex : 'orderdunits',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '单位',
			dataIndex : 'orderdunit',
			sortable : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '单价',
			dataIndex : 'orderdprice',
			sortable : true, 
			summaryRenderer: function(value, summaryData, dataIndex) {
                return '合计:';
            },
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '数量',
			dataIndex : 'sumorderdnum',
			xtype: 'numbercolumn',
			sortable : true, 
//			summaryType: 'sum',
			renderer : function(value, metaData, record, rowIdx, colIdx, store, view){	//数据显示时的函数
				return parseInt(value);
			},
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(numtotal);
            },
			editor: {
                xtype: 'numberfield',
                readOnly: true
            }
		}
		, {
			header : '重量',
			dataIndex : 'orderdweight',
			sortable : true, 
//			summaryType: 'sum',
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(weighttotal);
            },
			editor: {
                xtype: 'numberfield',
                readOnly: true
            }
		}
		, {
			header : '商品总价',
			dataIndex : 'sumorderdmoney',
			sortable : true, 
//			summaryType: 'sum',
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(moneytotal);
            },
			editor: {
                xtype: 'numberfield',
                readOnly: true
            }
		}
		, {
			header : '实际金额',
			dataIndex : 'sumorderdrightmoney',
			sortable : true, 
//			summaryType: 'sum',
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(rightmoneytotal);
            },
			editor: {
                xtype: 'numberfield',
                readOnly: true
            }
		}
		],
		/*工具栏*/
		tbar : [{
					text : "导出",
    				iconCls : 'exp',
    				handler : function() {
    					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
    						if (btn == 'yes') {
    							//
    							window.location.href = basePath + Orderdstataction + "?method=exprderdSta&query="+
    							Ext.getCmp("queryOrderdstataction").getValue()+"&startDate="+startDate+"&endDate="+endDate+
    							"&cusNames="+Ext.getCmp("quCustextfield").getValue()+"&empNames="+Ext.getCmp("quEmptextfield").getValue()+
    							"&brandNames="+Ext.getCmp("quBrandtextfield").getValue(); 
    						}
    					});
    				}
				},'-',{
					xtype: 'datetimefield',
					fieldLabel : '订单日期',
					labelWidth:60,				//标签宽度
					id:"startDate",
					name:"startDate",
					editable:false, //不允许对日期进行编辑
					width:220,
					format:"Y-m-d H:i:s",
					emptyText:"请选择日期",		//默认显示的日期
					value: startDate
				},{
					xtype: 'datetimefield',
					fieldLabel : '到',
					labelWidth:20,
					id:"endDate",
					name:"endDate",
					editable:false, //不允许对日期进行编辑
					width:180,
					format:"Y-m-d H:i:s",
					emptyText:"请选择日期",		//默认显示的日期
					value: endDate
				},{
					text : "查询",
					xtype: 'button',
    				handler : function() {
    					startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d H:i:s');		//得到时间选择框中的开始时间
    					endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d H:i:s');			//结束时间
    					Orderdstatstore.load();
    				}
				},'-',{
					xtype : 'textfield',
					fieldLabel : '筛选:业务员',
					id : 'quEmptextfield',
					name : 'quEmp',
					width : 195,
					labelWidth : 80,
					enableKeyEvents : true,
					listeners : {
						focus : function(){
							showEmp();
						}
					}
				},{
					xtype : 'textfield',
					fieldLabel : '品牌',
					id : 'quBrandtextfield',
					name : 'quEmp',
					width : 150,
					labelWidth : 35,
					enableKeyEvents : true,
					listeners : {
						focus : function(){
							showBrand();
						}
					}
				},{
					xtype : 'textfield',
					fieldLabel : '客户',
					id : 'quCustextfield',
					name : 'quEmp',
					width : 150,
					labelWidth : 35,
					listeners : {
						focus : function(){
							showCusNames();
						},
						
					}
				},'->',{
				xtype : 'textfield',														//文本类型
				id : 'queryOrderdstataction',
				name : 'query',															//传到后台时这个就是 参数名称
				emptyText : '模糊匹配',												//为空的时候的字符
				width : 100,																//宽度
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {				//这句话等于:e.getKey() == 13
							startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d H:i:s');		//得到时间选择框中的开始时间
							endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d H:i:s');			//结束时间
							Orderdstatstore.load();
						}
					}
				}
			}
		]
	});
	
	/*   定义 orderm(订单总表) 的 表格  结束    */
	
	Orderdstatgrid.region = 'center';
	Orderdstatstore.load();			//加载数据
	var win = new Ext.Viewport({	//只能有一个viewport
		resizable : false,			//是否可改变大小
		layout : 'border',				//布局
		renderTo : 'datagriddiv',
		bodyStyle : 'padding:0px;',
		items : [ Orderdstatgrid ]
	});
})
