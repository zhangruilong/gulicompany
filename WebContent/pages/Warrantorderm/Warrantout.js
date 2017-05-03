function warrantout(ordermselections,Warrantordermstore) {
	var isfahuo = false;
	var selectgridWindow;
	var Warrantoutclassify = "warrantout";
	var Warrantouttitle = "当前位置:业务管理》" + Warrantoutclassify;
	var Warrantoutaction = "MWarrantoutAction.do";
	var Warrantoutfields = ['idwarrantout'
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
	        			    ,'warrantoutcusid' 
	        			    ,'warrantoutcusname' 
	        			    ,'warrantoutodm' 
	        			    ,'warrantoutprint' 
	        			    ,'storehousename' 
	        			    ,'idgoodsnum' 
	        			    ,'goodsnumnum'
	        			      ];// 全部字段
	var Warrantoutkeycolumn = [ 'idwarrantout' ];// 主键
	
	var ordermid = "";
	var storeurl = basePath + "CPWarrantoutviewAction.do?method=selAll&wheresql=warrantoutodm='"+ordermid+"'";
	if (!Ext.isEmpty(ordermselections)) {
		ordermid = ordermselections[0].get("ordermid");
		var storeurl = basePath + "CPWarrantoutviewAction.do?method=selAll&wheresql=warrantoutodm='"+ordermid+"'";
		if(ordermselections[0].get("ordermstatue")=="已发货") {
			isfahuo = true;
			storeurl = basePath + "CPWarrantoutviewAction.do?method=selAll&wheresql=warrantoutodm='"+ordermid+"' and warrantoutstatue='已发货'";
		}
	}
	var Warrantoutstore = dataStore(Warrantoutfields, storeurl);// 定义Warrantoutstore
	var Empfields = ['empid'
		    ,'empcode' 
		    ,'empdetail' 
		      ];// 全部字段
	var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empcode!='隐藏'");// 定义Empstore
	Empstore.load();
	var OrdermviewdataForm = Ext.create('Ext.form.Panel', {
		id:'OrdermviewdataForm',
		labelAlign : 'right',
		frame : true,
		region : 'north',					//在容器的上方
		layout : 'column',	//横向布局
		//scrollable : "y",	//使用y轴的滚动条
		items : [{
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '客户ID',
				id : 'Warrantordermordermcustomer',
				name : 'ordermcustomer'
			} ]
		}
		, {
			columnWidth : .9,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客户名称',
				allowBlank : false,
				id : 'Warrantordermordermcusshop',
				name : 'ordermcusshop'
			} ]
		}
		, {
			columnWidth : .1,
			layout : 'form',
			items : [ {
				xtype : 'button',
				iconCls : 'select',
				handler : function() {
					showCustomer();
				}
			} ]
		}
//		, {
//			columnWidth : 1,
//			layout : 'form',
//			items : [ {
//				xtype : 'textfield',
//				fieldLabel : '编码',
//				editable : false,
//				id : 'Warrantordermordermcode',
//				name : 'ordermcode'
//			} ]
//		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '领货人',
				id : 'Warrantoutviewwarrantoutwho',
				name : 'warrantoutwho',			
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
		]
	});
	//var Warrantoutbbar = pagesizebar(Warrantoutstore);//定义分页
	var Warrantoutgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		
		store : Warrantoutstore,
		//bbar : Warrantoutbbar,
	    selModel: {
	        type: 'checkboxmodel'
	    },
	    plugins: {
	         ptype: 'cellediting',
	         clicksToEdit: 1
	    },
	    viewConfig : {
	    	enableTextSelection : true	//文本可以被选中
	    },
		columns : [{xtype: 'rownumberer',width:50}, 
		{// 改
			header : 'ID',
			dataIndex : 'idwarrantout',
			hidden : true, 
			editor: {
                xtype: 'textfield',
                editable: false
            }
		}
		, {
			header : '库存数量',
			dataIndex : 'goodsnumnum',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '库存id',
			dataIndex : 'idgoodsnum',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '经销商ID',
			dataIndex : 'warrantoutcompany',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '商品',
			dataIndex : 'warrantoutgoods',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '状态',
			dataIndex : 'warrantoutstatue',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '描述',
			dataIndex : 'warrantoutdetail',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '领货人',
			dataIndex : 'warrantoutwho',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '创建时间',
			dataIndex : 'warrantoutinswhen',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '创建人',
			dataIndex : 'warrantoutinswho',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '修改时间',
			dataIndex : 'warrantoutupdwhen',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '修改人',
			dataIndex : 'warrantoutupdwho',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '商品编码',
			dataIndex : 'warrantoutgcode',
			sortable : true,
			width : 120
		}
		, {
			header : '商品名称',
			dataIndex : 'warrantoutgname',
			sortable : true,
			width : 180
		}
		, {
			header : '规格',
			dataIndex : 'warrantoutgunits',
			sortable : true
		}
		, {
			header : '其他类别',
			dataIndex : 'warrantoutgtype',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '小类',
			dataIndex : 'warrantoutggclass',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '单位',
			dataIndex : 'warrantoutgunit',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '重量',
			dataIndex : 'warrantoutgweight',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '销售单价',
			dataIndex : 'warrantoutprice',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '销售金额',
			dataIndex : 'warrantoutmoney',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '客户ID',
			dataIndex : 'warrantoutcusid',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '客户名称',
			dataIndex : 'warrantoutcusname',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '订单总表ID',
			dataIndex : 'warrantoutodm',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '订单的打印次数',
			dataIndex : 'warrantoutprint',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '数量',
			dataIndex : 'warrantoutnum',
			sortable : true,  
			width : 50,
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '仓库',
			dataIndex : 'warrantoutstore',
			hidden : true,  
			editor: {
                xtype: 'textfield'
            }
		}
		, {
			header : '仓库',
			dataIndex : 'warrantoutordnote',
			hidden : true
		}
		, {
			header : '仓库',
			dataIndex : 'storehousename',
			width : 70,
			sortable : true
		}
		],
		tbar : [{
				text : Ext.os.deviceType === 'Phone' ? null : "新增",
				iconCls : 'add',
				handler : function() {
					if (isfahuo) return;
					goodsnumview(Warrantoutstore,ordermselections);
				}
//			},'-',{
//				text : Ext.os.deviceType === 'Phone' ? null : "保存",
//				iconCls : 'ok',
//				handler : function() {
//					if (isfahuo) return;
//					var selections = Warrantoutgrid.getSelection();
//					if (Ext.isEmpty(selections)) {
//						Ext.Msg.alert('提示', '请至少选择一条数据！');
//						return;
//					}
//					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要保存当前选择的条目？', function(btn, text) {
//						if (btn == 'yes') {
//							var json = '[';
//							for (var i = 0; i < selections.length; i++) {
//								if(!strUtil.isNull(selections[i].get('idwarrantout')))
//								json += Ext.encode(selections[i].getData())+",";
//							};
//							if(json == '[') return;//无正确数据取消操作
//							Ext.Ajax.request({
//								url : basePath + Warrantoutaction + "?method=updAll",
//								method : 'POST',
//								params : {
//									json : json.substr(0, json.length - 1) + "]"
//								},
//								success : function(response) {
//									var resp = Ext.decode(response.responseText); 
//									Ext.Msg.alert('提示', resp.msg, function(){
//									});
//								},
//								failure : function(response) {
//									Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
//								}
//							});
//						}
//					});
////					commonSave(basePath + Warrantoutaction + "?method=updAll",selections);
//				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					if (isfahuo) return;
					var selections = Warrantoutgrid.getSelection();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择一条数据！');
						return;
					}
					Warrantoutstore.remove(selections);
//					commonDelete(basePath + Warrantoutaction + "?method=delAll",selections,Warrantoutstore,Warrantoutkeycolumn);
				}
			},'-',{
				text : "出库过账",
				iconCls : 'exp',
				handler : function() {
					if (isfahuo) return;
					if (!OrdermviewdataForm.form.isValid()||Warrantoutstore.getCount()==0) {
				        Ext.Msg.alert('提示', '请正确填写表单!');
				        return;
				    }
					var linghuoren = Ext.getCmp("Warrantoutviewwarrantoutwho").getValue();
					if(strUtil.isNull(linghuoren)) {
						Ext.Msg.alert('提示', "领货人不能为空", function(){
							return;
						});
					}
					var msg = '';
					var goodsnumjson = "[";
					for(var i =0;i<Warrantoutstore.getCount();i++){
						if(strUtil.isNull(Warrantoutstore.getAt(i).get('goodsnumnum'))) Warrantoutstore.getAt(i).set('goodsnumnum',0);
						
						var goodsnumnum = Warrantoutstore.getAt(i).get('goodsnumnum') - Warrantoutstore.getAt(i).get('warrantoutnum');
						if(goodsnumnum<0) {
							msg += Warrantoutstore.getAt(i).get('warrantoutgname')+",";
						}
						goodsnumjson += "{'idgoodsnum':'"+Warrantoutstore.getAt(i).get('idgoodsnum')
						+"','goodsnumnum':'"+goodsnumnum
						+"','goodsnumgoods':'"+Warrantoutstore.getAt(i).get('warrantoutgoods')
						+"','idwarrantout':'"+Warrantoutstore.getAt(i).get('idwarrantout')
						+"','warrantoutgoods':'"+Warrantoutstore.getAt(i).get('warrantoutgoods')
						+"','warrantoutnum':'"+Warrantoutstore.getAt(i).get('warrantoutnum')
						+"','warrantoutstore':'"+Warrantoutstore.getAt(i).get('warrantoutstore')
						+"','warrantoutgcode':'"+Warrantoutstore.getAt(i).get('warrantoutgcode')
						+"','warrantoutgname':'"+Warrantoutstore.getAt(i).get('warrantoutgname')
						+"','warrantoutgunits':'"+Warrantoutstore.getAt(i).get('warrantoutgunits')
						+"','warrantoutcompany':'"+1
						+"'},";
					}
					if(!strUtil.isNull(msg)) msg += '</b>数量不足，是否继续？';
					else msg = '确定出库过账吗？'
					Ext.Msg.confirm('请确认', '<b>提示:'+msg, function(btn2, text2) {
						if (btn2 == 'yes') {
							var json = "[{'ordermid':'"+ordermid
							+"','ordermdetail':'"+linghuoren
							+"','ordermcustomer':'"+Ext.getCmp("Warrantordermordermcustomer").getValue()
							+"','ordermcusshop':'"+Ext.getCmp("Warrantordermordermcusshop").getValue()
							+"','ordermnum':'"+Warrantoutstore.getCount()
							+"','ordermcompany':'"+1
							+"','ordermstatue':'"+"已发货"
							+"'}]";
							//出库更新库存表(商品，数量)、出库单主表（主表id，领货人）
							Ext.Ajax.request({
								url : basePath + "MWarrantordermAction.do?method=guozhan",
								method : 'POST',
								params : {
									json : json,
									goodsnumjson: goodsnumjson.substr(0, goodsnumjson.length - 1) + "]",
								},
								success : function(response) {
									var resp = Ext.decode(response.responseText); 
									isfahuo = true;
									alert( resp.msg)
									if (!Ext.isEmpty(ordermselections)) {
										ordermselections[0].set("ordermnum",Warrantoutstore.getCount());
										ordermselections[0].set("ordermstatue","已发货");
										ordermselections[0].set("ordermdetail",linghuoren);
										ordermselections[0].set("updtime",new Date().dateFormat("yyyy-MM-dd hh:mm:ss"));
									}else Warrantordermstore.reload();
								},
								failure : function(response) {
									alert( '网络出现问题，请稍后再试');
								}
							});	
						}
					})
				}
			}
		]
	});
	Warrantoutgrid.region = 'north';	
	Warrantoutstore.load();//加载数据
	if (!Ext.isEmpty(ordermselections)) {
		Ext.getCmp("Warrantoutviewwarrantoutwho").setValue(ordermselections[0].get("ordermdetail"));
		Ext.getCmp("Warrantordermordermcustomer").setValue(ordermselections[0].get("ordermcustomer"));
		Ext.getCmp("Warrantordermordermcusshop").setValue(ordermselections[0].get("ordermcusshop"));
//		Ext.getCmp("Warrantordermordermcode").setValue(ordermselections[0].get("ordermcode"));
	}
	selectgridWindow = new Ext.Window({
		title : "出库详情",
		layout : 'border', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		//closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items :[OrdermviewdataForm, Warrantoutgrid]
	});
	selectgridWindow.show();
}
