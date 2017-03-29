var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';

var numtotal = 0;		//总订单合计
var moneytotal = 0;		//订单金额合计

var Orderdgrid = new Object();
var customerStatbbar = new Object();
Ext.onReady(function() {
	var customerStatclassify = "订单业务统计";
	var customerStattitle = "当前位置:订单管理》" + customerStatclassify;
	var customerStataction = "CPOrderdAction.do";
	var customerStatfields = ['customerid'
	        			    ,'customercode' 
	        			    ,'customername' 
	        			    ,'customerphone' 
	        			    ,'customerpsw' 
	        			    ,'customershop' 
	        			    ,'customercity' 
	        			    ,'customerxian' 
	        			    ,'customeraddress' 
	        			    ,'customertype' 
	        			    ,'customerlevel' 
	        			    ,'openid' 
	        			    ,'customerdetail' 
	        			    ,'customerstatue' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			    ,'odm_num'
	        			    ,'odm_money' 
	        			    ,'cccreatetime' 
	        			      ];// 订单的全部字段
	var customerStatkeycolumn = [ 'ordermid' ];// 主键
	var customerStatstore = dataStore(customerStatfields, basePath + customerStataction + "?method=customerStatistics");// 定义customerStatstore
	
	customerStatstore.on('beforeload',function(store,options){					//数据加载时的事件
		var start;
		var query = Ext.getCmp("querycustomerStataction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				comid : comid,
				startDate : startDate+" 00:00:00",
				endDate : endDate+" 23:59:59",
				query : query,
				limit : customerStatbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			store.loadPage(1);
		}
		Ext.apply(customerStatstore.proxy.extraParams, new_params);    //ext 4.0
		
	});
	customerStatstore.on('load',function(store){						//数据加载后
		var total = store.getAt(store.getCount()-1);
		if(typeof(total)!='undefined' && total){
			numtotal = total.get('odm_num');
			moneytotal = total.get('odm_money');
		}
		customerStatstore.remove(total);
	});
	customerStatbbar = pagesizebar(customerStatstore);//定义分页
	
	/*   定义 orderm(订单总表) 的 表格  开始    */
	var customerStatgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : customerStattitle,
		store : customerStatstore,
		bbar : customerStatbbar,
		features: [{							//要添加到网格中的网格特性数组。也可以只是一个单一的功能，而不是数组。
	        ftype: 'summary',
	        dock : 'bottom'
	    }],
		selModel: {
	        type: 'checkboxmodel'								//复选框
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
	    /*设置表格*/
		columns : [{
			header : '序号',
			xtype: 'rownumberer',		//行号
			width:60
		},
		{// 改
			header : '客户名称',
			dataIndex : 'customershop',
			sortable : true,  
			width:150,
		}
		, {
			header : '联系人',				//表头
			dataIndex : 'customername',		//对应字段
			sortable : true,  
			width:72,
		}
		, {
			header : '联系电话',
			dataIndex : 'customerphone',
			sortable : true,  
			width:109,
		}
		, {
			header : '地址',
			dataIndex : 'customeraddress',
			sortable : true,  
			width:160,
		}
		, {
			header : '客户经理',
			dataIndex : 'cccreatetime',
			sortable : true,  
			summaryRenderer: function(value, summaryData, dataIndex) {
                return '合计:';
            },
		}
		, {
			header : '订单数量',
			dataIndex : 'odm_num',
			sortable : true,  
//			summaryType: 'sum',
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(numtotal);
            },
		}
		, {
			header : '订单总额',
			dataIndex : 'odm_money',
			sortable : true,  
//			summaryType: 'sum',
			summaryRenderer: function(value, summaryData, dataIndex) {
                return parseInt(moneytotal);
            },
		}
		],
		/*工具栏*/
		tbar : [{
			xtype : 'textfield',														//文本类型
			id : 'querycustomerStataction',
			name : 'query',															//传到后台时这个就是 参数名称
			emptyText : '模糊匹配',												//为空的时候的字符
			width : 100,																//宽度
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {				//这句话等于:e.getKey() == 13
						startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d H:i:s');		//得到时间选择框中的开始时间
						endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d H:i:s');			//结束时间
						customerStatstore.load();
					}
				}
			}
		},'-',{
			xtype: 'datefield',
			fieldLabel : '',
//			labelWidth:60,				//标签宽度
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
			labelWidth:10,
			id:"endDate",
			name:"endDate",
			editable:false, //不允许对日期进行编辑
			width:115,
			format:"Y-m-d",
			emptyText:"请选择日期",		//默认显示的日期
			value: endDate
		},{
				text : "查询",
				xtype: 'button',
				handler : function() {
					startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d');		//得到时间选择框中的开始时间
					endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d');			//结束时间
					customerStatstore.load();
				}
			},'-',{
				text : "导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							//
							window.location.href = basePath + customerStataction + "?method=expCusOrder&query="+
							Ext.getCmp("querycustomerStataction").getValue()+"&startDate="+startDate+"&endDate="+endDate+""; 
						}
					});
				}
			}
		]
	});
	/*   定义 orderm(订单总表) 的 表格  结束    */
	
	customerStatgrid.region = 'center';
	customerStatstore.load();			//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,			//是否可改变大小
		layout : 'border',				//布局
		bodyStyle : 'padding:0px;',
		items : [ customerStatgrid ]
	});
})
