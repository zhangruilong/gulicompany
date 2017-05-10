var Goodsnumviewaction = "GoodsnumviewAction.do";
var Goodsnumviewfields = ['idgoodsnum'
        			    ,'goodsnumgoods' 
        			    ,'goodsnumnum' 
        			    ,'goodsnumstore' 
        			    ,'goodsid' 
        			    ,'goodscompany' 
        			    ,'createtime' 
        			    ,'goodscode' 
        			    ,'goodsname' 
        			    ,'goodsunits' 
        			    ,'storehousename' 
        			      ];// 全部字段
var Goodsnumviewkeycolumn = [ 'idgoodsnum' ];// 主键
var Goodsnumviewstore = dataStore(Goodsnumviewfields, basePath + Goodsnumviewaction + "?method=selAll");// 定义Goodsnumviewstore
Goodsnumviewstore.load();//加载数据
var Goodsnumviewgrid =  Ext.create('Ext.grid.Panel', {
	height : document.documentElement.clientHeight - 4,
	width : '100%',
	//title : Goodsnumviewtitle,
	store : Goodsnumviewstore,
    selModel: {
        type: 'checkboxmodel'
    },
	columns : [{xtype: 'rownumberer',width:50}, 
	{// 改
		header : 'ID',
		dataIndex : 'idgoodsnum',
		hidden : true, 
		editor: {
            xtype: 'textfield',
            editable: false
        }
	}
	, {
		header : '商品ID',
		dataIndex : 'goodsnumgoods',
		hidden : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '仓库',
		dataIndex : 'goodsnumstore',
		hidden : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '商品ID',
		dataIndex : 'goodsid',
		hidden : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '经销商ID',
		dataIndex : 'goodscompany',
		hidden : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '创建时间',
		dataIndex : 'createtime',
		hidden : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '商品编号',
		dataIndex : 'goodscode',
		sortable : true,  
		width : 120,
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '商品名称',
		dataIndex : 'goodsname',
		sortable : true,  
		width : 180,
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '规格',
		dataIndex : 'goodsunits',
		sortable : true,  
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '仓库',
		dataIndex : 'storehousename',
		sortable : true,  
		width : 70,
		editor: {
            xtype: 'textfield'
        }
	}
	, {
		header : '数量',
		dataIndex : 'goodsnumnum',
		sortable : true, 
		width : 50,
		editor: {
            xtype: 'textfield'
        }
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
						Goodsnumviewstore.load({
							params : {
								start : 0,
								limit : PAGESIZE,
								json : queryjson,
								query : Ext.getCmp("queryGoodsnumviewaction").getValue()
							}
						});
					}
				}
			}
		},{
			text : "查询",
			xtype: 'button',
			handler : function() {
				Goodsnumviewstore.load({
					params : {
						start : 0,
						limit : PAGESIZE,
						json : queryjson,
						query : Ext.getCmp("queryGoodsnumviewaction").getValue()
					}
				});
			}
		}
	]
});
Goodsnumviewgrid.region = 'center';
var selectgridWindow2;
function goodsnumview(Warrantoutstore1,ordermselections) {
	if(!selectgridWindow2)
	selectgridWindow2 = new Ext.Window({
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : Goodsnumviewgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Goodsnumviewgrid.getSelection();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							var p = new Ext.data.Model({	
								'warrantoutstore' : selectRows[0].get("goodsnumstore")
		        			    ,'warrantoutgoods' : selectRows[0].get("goodsnumgoods")
		        			    ,'warrantoutnum' : '1'
		        			    ,'idgoodsnum' : selectRows[0].get("idgoodsnum")
		        			    ,'goodsnumnum' : selectRows[0].get("goodsnumnum")
		        			    ,'warrantoutgcode' : selectRows[0].get("goodscode")
		        			    ,'warrantoutgname' : selectRows[0].get("goodsname")
		        			    ,'warrantoutgunits' : selectRows[0].get("goodsunits")
		        			    ,'storehousename' : selectRows[0].get("storehousename")
				                }); 
							Warrantoutstore.insert(Warrantoutstore.getCount(), p);
							selectgridWindow2.close();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							selectgridWindow2.close();
						}
					}]
	});
	selectgridWindow2.show();
}
