var startDate = Ext.util.Format.date(new Date(),'Y-m-d');			//查询的开始时间
var endDate = Ext.util.Format.date(new Date(),'Y-m-d');				//查询结束时间
Ext.onReady(function() {
	var Warrantordermclassify = "出库管理";
	var Warrantordermtitle = "当前位置:库存管理》" + Warrantordermclassify;
	var Warrantordermaction = "MWarrantordermAction.do";
	var Warrantordermfields = ['ordermid'
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
	        			    ,'ordermprinttimes' 
	        			      ];// 全部字段
	var Warrantordermkeycolumn = [ 'ordermid' ];// 主键
	var Warrantordermstore = dataStore(Warrantordermfields, basePath + Warrantordermaction + "?method=selLimit&order=ordermtime desc");// 定义Warrantordermstore
	
//	var Warrantordermbbar = pagesizebar(Warrantordermstore);//定义分页
	var Warrantordermgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Warrantordermtitle,
		store : Warrantordermstore,
//		bbar : Warrantordermbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [{header : '序号',xtype: 'rownumberer',width:60}, 
		{// 改
			header : '订单ID',
			dataIndex : 'ordermid',
			hidden : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '客户ID',
			dataIndex : 'ordermcustomer',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '经销商ID',
			dataIndex : 'ordermcompany',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '单据编号',
			dataIndex : 'ordermcode',
			sortable : true,  
			width : 180,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '客户名称',
			dataIndex : 'ordermcusshop',
			sortable : true, 
			width : 180,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '种类数',
			dataIndex : 'ordermnum',
			sortable : true, 
			width : 80,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '状态',
			dataIndex : 'ordermstatue',
			sortable : true, 
			width : 80,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '下单金额',
			dataIndex : 'ordermmoney',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '实际金额',
			dataIndex : 'ordermrightmoney',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '支付方式',
			dataIndex : 'ordermway',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '领货人',
			dataIndex : 'ordermdetail',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '联系人',
			dataIndex : 'ordermconnect',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '手机',
			dataIndex : 'ordermphone',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '地址',
			dataIndex : 'ordermaddress',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '来源',
			dataIndex : 'ordermemp',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '出库时间',
			dataIndex : 'updtime',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}

		, {
			header : '生成时间',
			dataIndex : 'ordermtime',
			sortable : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '修改人',
			dataIndex : 'updor',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '客户等级',
			dataIndex : 'ordermcuslevel',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '客户类型',
			dataIndex : 'ordermcustype',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '打印次数',
			dataIndex : 'ordermprinttimes',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'queryWarrantordermaction',
			name : 'query',
			emptyText : '模糊匹配',
			width : 100,
			enableKeyEvents : true,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						Warrantordermstore.load({
							params : {
								json : queryjson,
								query : Ext.getCmp("queryWarrantordermaction").getValue()
							}
						});
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
						Warrantordermstore.load({
							params : {
								query : Ext.getCmp("queryWarrantordermaction").getValue(),
								wheresql : "ordermtime >= '"+startDate+" 00:00:00' and ordermtime <= '"+endDate+" 23:59:59'"
							}
						});
				}
			},'-',{
				text : Ext.os.deviceType === 'Phone' ? null : "出库",
						iconCls : 'select',
						handler : function() {
							var selections = Warrantordermgrid.getSelection();
							warrantout(selections);
						}
			},'-',{
				text : "打印",
				xtype: 'button',
				handler : function() {
					var selections = Warrantordermgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择一条数据！');
						return;
					}			//返回当前选定的记录的数组
					window.open("chukudanPrint.jsp?ordermid="+selections[0].get('ordermid')
							+"&ordermcode="+selections[0].get('ordermcode')
							+"&customershop="+selections[0].get('ordermcusshop')
							+"&ordermdetail="+selections[0].get('ordermdetail')
							);
				}
			}
		]
	});
	Warrantordermgrid.region = 'center';
	Warrantordermstore.load({
		params : {
			wheresql : "ordermtime >= '"+startDate+" 00:00:00' and ordermtime <= '"+endDate+" 23:59:59'"
		}
	});//加载数据
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Warrantordermgrid ]
	});
})
