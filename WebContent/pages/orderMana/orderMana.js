var Orderdfields = ['orderdid'
        			    ,'orderdorderm' 
        			    ,'orderdcode' 
        			    ,'orderdtype' 
        			    ,'orderdname' 
        			    ,'orderddetail' 
        			    ,'orderdunits' 
        			    ,'orderdprice' 
        			    ,'orderdunit' 
        			    ,'orderdclass' 
        			    ,'orderdnum' 
        			    ,'orderdmoney' 
        			    ,'orderdrightmoney' 
        			    ,'orderdweight' 
        			    ,'orderdgoods' 
        			    ,'orderdnote' 
        			    ,'orderdbrand' 
        			      ];// 订单详细全部字段
var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
/*之前的查询条件*/
var odStartDate=startDate;								
var odEndDate=endDate;
var odQuery='';

var Orderdgrid = new Object();
var Ordermviewbbar = new Object();

Ext.onReady(function() {
	var Ordermviewclassify = "订单管理";
	var Ordermviewtitle = "当前位置:业务管理》" + Ordermviewclassify;
	var Ordermviewaction = "CPOrderAction.do";
	var Ordermviewfields = ['ordermid'
	        			    ,'ordermcustomer' 
	        			    ,'ordermcompany' 
	        			    ,'ordermcode' 
	        			    ,'ordermnum' 
	        			    ,'ordermmoney' 
	        			    ,'ordermrightmoney' 
	        			    ,'ordermway' 
	        			    ,'ordermstatue' 
	        			    ,'ordermdetail' 
	        			    ,'ordermtime' 
	        			    ,'ordermconnect' 
	        			    ,'ordermphone' 
	        			    ,'ordermaddress' 
	        			    ,'updtime' 
	        			    ,'updor' 
	        			    ,'ordermemp' 
	        			    ,'ordermcusshop' 
	        			    ,'ordermcuslevel' 
	        			    ,'ordermcustype' 
	        			    ,'companyshop' 
	        			    ,'companyphone' 
	        			    ,'companydetail' 
	        			    ,'openid' 
	        			      ];// 订单的全部字段
	
	var Ordermviewkeycolumn = [ 'ordermid' ];// 主键
	var Ordermviewstore = dataStore(Ordermviewfields, basePath + Ordermviewaction + "?method=queryOrder&comid="+comid);// 定义Ordermviewstore
	
	Ordermviewstore.on('beforeload',function(store,options){					//数据加载时的事件
		//var start;
		var query = Ext.getCmp("queryOrdermviewaction").getValue();
		var new_params = {		//每次数据加载的时候传递的参数
				json : queryjson,
				//method : 'queryOrder',
				comid : comid,
				startDate : startDate+" 00:00:00",
				endDate : endDate+" 23:59:59",
				query : query,
				limit : Ordermviewbbar.pageSize
		};
		if(startDate!=odStartDate || endDate!=odEndDate || query!=odQuery){		//如果查询条件变化了就变成第一页
			odStartDate = startDate;
			odEndDate = endDate;
			odQuery = query;
			store.loadPage(1);
		}
		Ext.apply(Ordermviewstore.proxy.extraParams, new_params);    //ext 4.0
		
	});
	/*   修改 orderm(订单总表) 的 form 表单  开始    */
	var OrdermviewdataForm = Ext.create('Ext.form.Panel', {
		id:'OrdermviewdataForm',
		labelAlign : 'right',
		frame : true,
		region : 'north',					//在容器的上方
		layout : 'column',	//横向布局
		//scrollable : "y",	//使用y轴的滚动条
		items : [ {
			columnWidth : .3,	//列宽,1表示百分之百
			layout : 'form',	//布局方式:form是纵向布局
			items : [ {
				xtype : 'textfield',
				fieldLabel : '订单编号',
				id : 'Ordermviewordermcode',
				name : 'ordermcode',
				readOnly : true
			} ]
		}
		, {
			columnWidth : .2,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '订单状态',
				id : 'Ordermviewordermstatue',
				name : 'ordermstatue',
				readOnly : true
			} ]
		}
		, {
			columnWidth : .4,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '店铺名称',
				id : 'Ordermviewcustomershop',
				name : 'customershop',
				readOnly : true
			} ]
		}
		, {
			columnWidth : .3,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : ' 联系人',
				id : 'Ordermviewordermconnect',
				name : 'ordermconnect',
//				width : 195,
//				labelWidth : 80,		//此处'fieldLabel'的宽度设置无效,原因未知
				readOnly : true
			} ]
		}
		, {
			columnWidth : .2,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '联系电话',
				id : 'Ordermviewordermphone',
				name : 'ordermphone',
				readOnly : true
			} ]
		}
		, {
			columnWidth : .4,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '送货地址',
				id : 'Ordermviewordermaddress',
				name : 'ordermaddress',
				readOnly : true
			} ]
		}
		]
	});
	/*   修改 orderm(订单总表) 的 form 表单  结束    */
	
	
	
	Ordermviewbbar = pagesizebar(Ordermviewstore);//定义分页
	
	/*   定义 orderm(订单总表) 的 表格  开始    */
	var Ordermviewgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Ordermviewtitle,
		store : Ordermviewstore,
		bbar : Ordermviewbbar,
		
		selModel: {
	        type: 'checkboxmodel'								//复选框
	    },
	    plugins: {												//这里设置插件
	         ptype: 'cellediting',									//类型为:单元格编辑
	         clicksToEdit: 1										//点几下可以编辑
	    },
	    /*设置表格*/
		columns : [{xtype: 'rownumberer',width:25}, 			//行号
		{// 改
			header : 'id',
			dataIndex : 'ordermid',
			sortable : true,		//如果是false则禁用此列的排序功能,默认为 true
			hidden : true
		}
		, {
			header : '订单编号',				//表头
			dataIndex : 'ordermcode',		//对应字段
			sortable : true,  
			width:80,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '支付方式',
			dataIndex : 'ordermway',
			sortable : true,  
			width:80,
			editor: {										//这里是编辑选项。设置后变为可编辑，不设置则不可编辑。(要有可编辑单元格的插件才能生效)
                xtype: 'textfield',							//编辑类型
                editable:false								//是否可编辑
            }
		}
		, {
			header : '种类数',
			dataIndex : 'ordermnum',
			sortable : true,
			width:50,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '下单金额',
			dataIndex : 'ordermmoney',
			sortable : true,
			width:60,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '实际金额',
			dataIndex : 'ordermrightmoney',
			sortable : true,
			width:60,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		
		, {
			header : '状态',
			dataIndex : 'ordermstatue',
			sortable : true,
			width:60,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		
		, {
			header : '下单时间',
			dataIndex : 'ordermtime',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'updtime',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '客户名称',
			dataIndex : 'ordermcusshop',
			sortable : true,  
			editor: {
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '联系人',
			dataIndex : 'ordermconnect',
			sortable : true,
			width:70,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '手机号',
			dataIndex : 'ordermphone',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '地址',
			dataIndex : 'ordermaddress',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '订单源',
			dataIndex : 'ordermemp',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			sortable : true,
			editor: {	
                xtype: 'textfield',
                editable:false
            }
		}
		],
		/*工具栏*/
		tbar : [{
			text : Ext.os.deviceType === 'Phone' ? null : "删除",					//按钮上的字
					iconCls : 'delete',												//按钮样式(删除按钮)
					handler : function() {											//点击按钮执行的函数
						var selections = Ordermviewgrid.getSelection();				//返回当前选定的记录的数组
						var ordermid = '';
    					if (Ext.isEmpty(selections)) {
    						Ext.Msg.alert('提示', '请选择要删除的订单！');
    						return;
    					} else if (selections.length >1){
    						Ext.Msg.alert('提示', '一次只能删除一个订单！');
    						return;
    					} else {
    						ordermid = selections[0].data['ordermid'];
    					}
    					$.ajax({
    						url:"CPOrderAction.do",
    						type:"post",
    						data:{
    							method:"updateOrdermStatue",
    							statue:"已删除",
    							ordermid:ordermid
    						},
    						success : function(resp){
    							var data = eval('('+resp+')');
    							if(data.msg=='操作成功'){
    								Ext.Msg.alert('提示', '操作成功，订单已删除。');
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
					text : Ext.os.deviceType === 'Phone' ? null : "详细",
							iconCls : 'select',
							handler : function() {
								var ordermSelections = Ordermviewgrid.getSelection();
								if (ordermSelections.length != 1) {
									Ext.Msg.alert('提示', '请选择一条数据！', function() {
									});
									return;
								}
								OrdermviewdataForm.form.reset();
								//Ext.getCmp("Ordermviewordermid").setEditable(false);
								var ordermData = ordermSelections[0].data;
								var ordermid = ordermData['ordermid'];
								var Orderdstore = dataStore(Orderdfields, basePath + "CPOrderdAction.do?method=selAll&wheresql=orderdorderm='"
										+ordermid+"'");// 定义Orderdstore
								/*   定义 orderd(订单商品) 的 表格  开始    */
								Orderdgrid = Ext.create('Ext.grid.Panel', {
									height : document.documentElement.clientHeight - 148,
									width : '100%',
									store : Orderdstore,
									region : 'north',					//显示在容器的上方
									//bbar : Orderdbbar,
								    selModel: {
								        type: 'checkboxmodel'
								    },
									columns : [{xtype: 'rownumberer',width:25}, 
									{
										header : '商品编码',
										dataIndex : 'orderdcode',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}, {
										header : '名称',
										dataIndex : 'orderdname',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '商品类型',
										dataIndex : 'orderdtype',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '规格',
										dataIndex : 'orderdunits',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '小类',
										dataIndex : 'orderdclass',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '价格',
										dataIndex : 'orderdprice',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '单位',
										dataIndex : 'orderdunit',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '数量',
										dataIndex : 'orderdnum',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '下单金额',
										dataIndex : 'orderdmoney',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '实际金额',
										dataIndex : 'orderdrightmoney',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '重量',
										dataIndex : 'orderdweight',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									, {
										header : '订单备注',
										dataIndex : 'orderdnote',
										sortable : true,  
										editor: {
							                xtype: 'textfield',
							                editable:false
							            }
									}
									],
									tbar : [{	
										//修改订单详情的按钮
										text : Ext.os.deviceType === 'Phone' ? null : "修改",
											iconCls : 'edit',
											handler : function() {
												var orderdSelections = Orderdgrid.getSelection();
												if (orderdSelections.length != 1) {
													Ext.Msg.alert('提示', '请选择一条数据！', function() {
													});
													return;
												}
												OrderddataForm.form.reset();
												createEditOrderdWindow(basePath + "CPOrderdAction.do?method=orderdEdit", 
														"修改", OrderddataForm, Orderdstore, Ordermviewstore);
												OrderddataForm.form.loadRecord(orderdSelections[0]);
											}
									}]
								});
								/*   定义 orderd(订单商品) 的 表格  结束    */
								Orderdstore.load();
								//创建一个窗口
								createOrderdWindow("", "订单详情", OrdermviewdataForm, Orderdgrid, Ordermviewstore);
								OrdermviewdataForm.form.loadRecord(ordermSelections[0]);
							}
				},'-',{
					text : "导出",
    				iconCls : 'exp',
    				handler : function() {
    					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
    						if (btn == 'yes') {
    							//
    							window.location.href = basePath + Ordermviewaction + "?method=expOrder&json="+queryjson+"&query="+
    							Ext.getCmp("queryOrdermviewaction").getValue()+"&startDate="+startDate+" 00:00:00&endDate="+endDate+" 23:59:59"; 
    						}
    					});
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
    					Ordermviewstore.load();
    				}
				},'-',{
					text : "打印",
					xtype: 'button',
    				handler : function() {
    					var selections = Ordermviewgrid.getSelection();				//返回当前选定的记录的数组
    					var ordermids = '';
    					var ordermCusId = '';									//客户id
    					var ordermAddress = '';
    					if (Ext.isEmpty(selections)) {
    						Ext.Msg.alert('提示', '请选择要打印的订单！');
    						return;
    					} else {
    						for (var i = 0; i < selections.length; i++) {
    							if(ordermCusId == ''){
    								ordermCusId = selections[i].data['ordermcustomer'];
    							} else if(ordermCusId != selections[i].data['ordermcustomer']){
    								Ext.Msg.alert('提示', '操作失败！多个订单合并打印时，须客户和收货信息完全一致。');
    								return;
    							}
    							if(ordermAddress == ''){
    								ordermAddress = selections[i].data['ordermaddress'];
    							} else if(ordermAddress != selections[i].data['ordermaddress']){
    								Ext.Msg.alert('提示', '操作失败！多个订单合并打印时，须客户和收货信息完全一致。');
    								return;
    							}
        						ordermids += selections[i].data['ordermid']+',';
    						}
    					}
    					
    					window.open("../../print.jsp?ordermids="+ordermids);
    				}
				},'-',{
					text : '<span style="color:#FFFFFF;">确认</span>',
					xtype: 'button',
					style: {											//自定义单个元素的样式
						
						background:'#83CD1F'
					},
    				handler : function() {
    					var selections = Ordermviewgrid.getSelection();				//得到被选定的行
    					var ordermid = '';
    					if(typeof(selections)=='undefined' || !selections){
    						Ext.Msg.alert('提示', '请选择要修改状态的订单。');
    						return;
    					} else if(selections.length>1){
    						Ext.Msg.alert('提示', '只能选择一个订单。');
    						return;
    					} else {
    						ordermid = selections[0].data['ordermid'];
    					}
    					$.ajax({
    						url:"CPOrderAction.do",
    						type:"post",
    						data:{
    							method:"updateOrdermStatue",
    							statue:"已确认",
    							ordermid:ordermid
    						},
    						success : function(resp){
    							var data = eval('('+resp+')');
    							if(data.msg=='操作成功'){
    								Ext.Msg.alert('提示', '操作成功，订单已确认。');
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
					text : '<span style="color:#FFFFFF;">发货</span>',
					xtype: 'button',
					style: {											//自定义单个元素的样式
						background:'#DAD52B'
					},
    				handler : function() {
    					var selections = Ordermviewgrid.getSelection();				//得到被选定的行
    					var ordermid = '';
    					if(typeof(selections)=='undefined' || !selections){
    						Ext.Msg.alert('提示', '请选择要修改状态的订单。');
    						return;
    					} else if(selections.length>1){
    						Ext.Msg.alert('提示', '只能选择一个订单。');
    						return;
    					} else {
    						ordermid = selections[0].data['ordermid'];
    					}
    					$.ajax({
    						url:"CPOrderAction.do",
    						type:"post",
    						data:{
    							method:"updateOrdermStatue",
    							statue:"已发货",
    							ordermid:ordermid
    						},
    						success : function(resp){
    							var data = eval('('+resp+')');
    							if(data.msg=='操作成功'){
    								Ext.Msg.alert('提示', '操作成功，订单已发货。');
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
					text : '<span style="color:#FFFFFF;">完成</span>',
					xtype: 'button',
					style: {											//自定义单个元素的样式
						//color:'#FFFFFF',
						background:'#1D6BE9'
					},
    				handler : function() {
    					var selections = Ordermviewgrid.getSelection();				//得到被选定的行
    					var ordermid = '';
    					if(typeof(selections)=='undefined' || !selections){
    						Ext.Msg.alert('提示', '请选择要修改状态的订单。');
    						return;
    					} else if(selections.length>1){
    						Ext.Msg.alert('提示', '只能选择一个订单。');
    						return;
    					} else {
    						ordermid = selections[0].data['ordermid'];
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
				},'->',{
				xtype : 'textfield',														//文本类型
				id : 'queryOrdermviewaction',
				name : 'query',															//传到后台时这个就是 参数名称
				emptyText : '模糊匹配',												//为空的时候的字符
				width : 100,																//宽度
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {				//这句话等于:e.getKey() == 13
	    					Ordermviewstore.load();
						}
					}
				}
			}
		]
	});
	/*   定义 orderm(订单总表) 的 表格  结束    */
	
	Ordermviewgrid.region = 'center';
	Ordermviewstore.load();			//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : false,			//是否可改变大小
		layout : 'border',				//布局
		bodyStyle : 'padding:0px;',
		items : [ Ordermviewgrid ]
	});
})