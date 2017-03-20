var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';

var Orderdgrid = new Object();
var Ordermbbar = new Object();

var storehouseid;
Ext.onReady(function() {
	var Ordermclassify = "出库单";
	var Ordermtitle = "当前位置:库存管理》" + Ordermclassify;
	var Ordermaction = "CPOrdermAction.do";
	var Ordermfields = ['idwarrantout'
	                    	,'warrantoutodm' 
	        			    ,'ordermcode' 
	        			    ,'ordermnum' 
	        			    ,'customershop' 
	        			    ,'customername' 
	        			    ,'customerphone' 
	        			    ,'warrantoutupdwhen' ];// 全部字段
	var Ordermstore = dataStore(Ordermfields, basePath + Ordermaction + "?method=chukudan");// 定义Ordermstore
	Ordermstore.on('beforeload',function(store,options){					//数据加载时的事件
		var query = Ext.getCmp("queryOrdermaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				companyid : comid,
				startDate : startDate+" 00:00:00",
				endDate : endDate+" 23:59:59",
				query : query,
				limit : Ordermbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			store.loadPage(1);
		}
		Ext.apply(store.proxy.extraParams, new_params);    //ext 4.0
	});
	var Ordermbbar = pagesizebar(Ordermstore);//定义分页
	var Ordermgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Ordermtitle,
		store : Ordermstore,
		bbar : Ordermbbar,
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
		}
		, {// 改
			header : '出库台账ID',
			dataIndex : 'idwarrantout',
			sortable : true, 
			hidden : true
		}
		, {
			header : '订单ID',
			dataIndex : 'warrantoutodm',
			sortable : true, 
			hidden : true
		}
		, {
			header : '订单编号',
			dataIndex : 'ordermcode',
			sortable : true,  
			width : 158,
		}
		, {
			header : '种类数',
			dataIndex : 'ordermnum',
			sortable : true,  
			width:60,
		}
		, {
			header : '客户名称',
			dataIndex : 'customershop',
			sortable : true,  
			width:150,
		}
		, {
			header : '联系人',
			dataIndex : 'customername',
			sortable : true,  
			width:72,
		}
		, {
			header : '手机',
			dataIndex : 'customerphone',
			sortable : true,  
			width:109,
		}
		, {
			header : '出库时间',
			dataIndex : 'warrantoutupdwhen',		//“打印次数”这里当做“出库时间”使用
			sortable : true,  
			width:138,
		}
		],
		tbar : [{
				xtype : 'textfield',														//文本类型
				id : 'queryOrdermaction',
				name : 'query',															//传到后台时这个就是 参数名称
				emptyText : '模糊匹配',												//为空的时候的字符
				width : 100,																//宽度
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {				//这句话等于:e.getKey() == 13
							Ordermstore.load();
						}
					}
				}
			},'-',{
				xtype: 'datefield',
				fieldLabel : '订单日期',
				labelWidth:60,				//标签宽度
				id:"startDate",
				name:"startDate",
				editable:false, //不允许对日期进行编辑
				width:165,
				format:"Y-m-d",
				emptyText:"请选择日期",		//默认显示的日期
				value: startDate
			},{
				xtype: 'datefield',
				fieldLabel : '到',
				labelWidth:20,
				id:"endDate",
				name:"endDate",
				editable:false, //不允许对日期进行编辑
				width:125,
				format:"Y-m-d",
				emptyText:"请选择日期",		//默认显示的日期
				value: endDate
			},{
				text : "查询",
				xtype: 'button',
				handler : function() {
					startDate = Ext.util.Format.date(Ext.getCmp("startDate").getValue(),'Y-m-d');		//得到时间选择框中的开始时间
					endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(),'Y-m-d');			//结束时间
					Ordermstore.load();
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "详细",
				iconCls : 'select',
				handler : function() {
					var ordermSelections = Ordermgrid.getSelection();
					if (ordermSelections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条数据！', function() {
						});
						return;
					}
					detailWindow(ordermSelections[0].data['idwarrantout'],ordermSelections[0].data['warrantoutodm']);
				}
			},'-',{
				text : '<span style="color:#FFFFFF;">完成</span>',
				xtype: 'button',
				style: {											//自定义单个元素的样式
					//color:'#FFFFFF',
					background:'#1D6BE9'
				},
				handler : function() {
					var selections = Ordermgrid.getSelection();				//得到被选定的行
					var ordermid = '';
					if(typeof(selections)=='undefined' || !selections){
						Ext.Msg.alert('提示', '请选择要修改状态的订单。');
						return;
					} else if(selections.length>1){
						Ext.Msg.alert('提示', '只能选择一个订单。');
						return;
					} else {
						ordermid = selections[0].data['warrantoutodm'];
					}
					$.ajax({
						url:"CPOrderAction.do",
						type:"post",
						data:{
							method:"updateOrdermStatue",
							statue:"已完成",
							ordermid:ordermid
						},
						success : function(resp){
							var data = eval('('+resp+')');
							if(data.msg=='操作成功'){
								Ext.Msg.alert('提示', '操作成功，订单已完成。');
								Ordermviewstore.load();
							} else {
								Ext.Msg.alert('提示', data.msg);
							}
						},
						error : function(resp){
							var data = eval('('+resp+')');
							Ext.Msg.alert('提示', data.msg);
						}
					});
				}
			},'-',{
				text : "打印",
				xtype: 'button',
				handler : function() {
					var selections = Ordermgrid.getSelection();				//返回当前选定的记录的数组
					var ordermid = '';
					var ordermcode = '';
					var customershop = '';
					var idwarrantouts = '';
					var subUrl = '';
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择要打印的订单！');
						return;
					} else {
						for (var i = 0; i < selections.length; i++) {
							if(selections[i].data['ordermcode']){
								ordermcode =selections[i].data['ordermcode'];
							}
							if(selections[i].data['customershop']){
								customershop =selections[i].data['customershop'];
							}
							if(ordermid == ''){
								ordermid = selections[i].data['warrantoutodm'];
							} else if(selections[i].data['warrantoutodm'] && ordermid != selections[i].data['warrantoutodm']){
								Ext.Msg.alert('提示', '操作失败。');		//这里提示信息需要修改
								return;
							} else if(!selections[i].data['warrantoutodm']){
								idwarrantouts += selections[i].data['idwarrantout']+',';
							}
						}
					}
					if(idwarrantouts != ''){
						subUrl = '&idwarrantouts='+idwarrantouts;
					}
					window.open("chukudanPrint.jsp?ordermid="+ordermid+subUrl+
							"&ordermcode="+ordermcode+
							"&customershop="+customershop);
				}
			},'-',{
            	text : "导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Ordermaction + "?method=expAll&json="+queryjson+"&query="+Ext.getCmp("queryOrdermaction").getValue(); 
						}
					});
				}
            }
		]
	});
	Ordermgrid.region = 'center';
	Ordermstore.load();//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Ordermgrid ]
	});
})
