var startDate = Ext.util.Format.date(new Date(),'Y-m-d')+' 00:00:00';			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d H:i:s');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odStorehouseid = '';
var Inventoryviewbbar = new Object();
/* 合计信息 */
var sumQuondamnum = 0;
var sumInnum = 0;
var sumOutnum = 0;
var sumOutback = 0;
var sumInback = 0;
var sumGoodsnumnum = 0;
Ext.onReady(function() {
	var Inventoryviewclassify = "进销存变动表";
	var Inventoryviewtitle = "当前位置:业务管理》" + Inventoryviewclassify;
	var Inventoryviewaction = "CPGoodsnumAction.do";
	var Inventoryviewfields = ['goodsname'
	        			    ,'goodsunits' 
	        			    ,'storehousename' 
	        			    ,'innum' 
	        			    ,'outnum' 
	        			    ,'outback' 
	        			    ,'inback' 
	        			    ,'goodsnumnum' 
	        			    ,'quondamnum' 
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
	var Storehousestore = dataStore(Storehousefields, basePath + "CPStorehouseAction.do?method=selAll&wheresql=storehousecompany='"+comid+"' and storehousestatue='启用'");// 定义Storehousestore
	Storehousestore.load();
	var Inventoryviewstore = dataStore(Inventoryviewfields, basePath + Inventoryviewaction + "?method=goodsnumTable");// 定义Inventoryviewstore
	//数据加载前
	Inventoryviewstore.on('beforeload',function(store,options){
		var size = Inventoryviewbbar.pageSize;
		var storehouseid = Ext.getCmp("queryStorehouseid").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				startDate : startDate,
				endDate : endDate,
				storehouseid : storehouseid,
				limit : size
		};
		if(startDate!=odStartDate || endDate!=odEndDate || storehouseid!=odStorehouseid ){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odStorehouseid = storehouseid;
			store.loadPage(1);
		}
		Ext.apply(store.proxy.extraParams, new_params);    //ext 4.0
	});
	//数据加载后
	Inventoryviewstore.on('load',function(store){
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			sumQuondamnum = total.get('quondamnum');
			sumInnum = total.get('innum');
			sumOutnum = total.get('outnum');
			sumOutback = total.get('outback');
			sumInback = total.get('inback');
			sumGoodsnumnum = total.get('goodsnumnum');
		}
		store.remove(total);
	});
	Inventoryviewbbar = pagesizebar(Inventoryviewstore);//定义分页
	var Inventoryviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Inventoryviewtitle,
		store : Inventoryviewstore,
		bbar : Inventoryviewbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
	    features: {							//要添加到网格中的网格特性数组。也可以只是一个单一的功能，而不是数组。
	        ftype: 'summary',
	        dock : 'bottom'
	    },
		columns : [{xtype: 'rownumberer',width:50}, 
		{// 改
			header : '商品名称',
			dataIndex : 'goodsname',
			sortable : true, 
		}
		, {
			header : '规格',
			dataIndex : 'goodsunits',
			sortable : true,  
		}
		, {
			header : '仓库',
			dataIndex : 'storehousename',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return '合计:';
            },
		}
		, {
			header : '上期存',
			dataIndex : 'quondamnum',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumQuondamnum);
            },
		}
		, {
			header : '进货数量',
			dataIndex : 'innum',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumInnum);
            },
		}
		, {
			header : '销售数量',
			dataIndex : 'outnum',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumOutnum);
            },
		}
		, {
			header : '销售退货',
			dataIndex : 'outback',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumOutback);
            },
		}
		, {
			header : '进货退货',
			dataIndex : 'inback',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumInback);
            },
		}
		, {
			header : '本期存',
			dataIndex : 'goodsnumnum',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(sumGoodsnumnum);
            },
		}
		],
		tbar : [{
			xtype: 'datetimefield',
			fieldLabel : '起始时间',
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
			fieldLabel : '终止时间',
			labelWidth:60,
			id:"endDate",
			name:"endDate",
			editable:false, //不允许对日期进行编辑
			width:220,
			format:"Y-m-d H:i:s",
			emptyText:"请选择日期",		//默认显示的日期
			value: endDate
		},{
			text : "查询",
			xtype: 'button',
			handler : function() {
				startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d H:i:s');		//得到时间选择框中的开始时间
				endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d H:i:s');			//结束时间
				Inventoryviewstore.load();
			}
		},'-',{
			xtype : 'combo',
			fieldLabel : '仓库',
			labelWidth : 33,
			id : 'queryStorehouseid',
			name : 'storehouseid',
			width : 173,
			store : Storehousestore,
			mode : 'local',					//local是取本地数据的也就是javascirpt(内存)中的数据。
											//'remote'指的是要动态去服务器端拿数据，这样就不能加Goodsclassstore.load()。
			displayField : 'storehousename',		//显示的字段
			valueField : 'storehouseid',		//作为值的字段
			hiddenName : 'storehouseid',
			triggerAction : 'all',
			value : "",
			editable : false,
			maxLength : 100,
			anchor : '95%',
		},'-',{
        	text : "导出",
			iconCls : 'exp',
			handler : function() {
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
					if (btn == 'yes') {
						var storehouseid = Ext.getCmp("queryStorehouseid").getValue();
						storehousename = storehousename==null?'':storehousename;
						storehouseid = storehouseid==null?'':storehouseid;
						var storehousename = Ext.getCmp("queryStorehouseid").getRawValue();
						window.location.href = basePath + Inventoryviewaction + "?method=expJinXiaoCun&startDate="+startDate+
								"&endDate="+endDate+
								"&storehousename="+storehousename+
								"&storehouseid="+storehouseid; 
					}
				});
			}
        }
		]
	});
	Inventoryviewgrid.region = 'center';
	Inventoryviewstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Inventoryviewgrid ]
	});
})
