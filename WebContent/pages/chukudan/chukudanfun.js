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
	var Warrantouttitle = "出库单详情";
	var Warrantoutaction = "CPWarrantoutAction.do";
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
	        			      ];// 全部字段
	var Warrantoutstore = dataStore(Warrantoutfields, basePath + Warrantoutaction + "?method=selQuery");// 定义Warrantoutstore
	var Warrantoutbbar = pagesizebar(Warrantoutstore);//定义分页
	var Warrantoutgrid =  Ext.create('Ext.grid.Panel', {
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Warrantoutstore,
		bbar : Warrantoutbbar,
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
			header : 'ID',
			dataIndex : 'idwarrantout',
			sortable : true, 
			hidden: true
		}
		, {
			header : '经销商ID',
			dataIndex : 'warrantoutcompany',
			sortable : true,  
			hidden: true
		}
		, {
			header : '商品',
			dataIndex : 'warrantoutgoods',
			sortable : true,  
			hidden: true,
		}
		, {
			header : '商品编码',
			dataIndex : 'warrantoutgcode',
			sortable : true,  
		}
		, {
			header : '商品名称',
			dataIndex : 'warrantoutgname',
			sortable : true,  
		}
		, {
			header : '规格',
			dataIndex : 'warrantoutgunits',
			sortable : true,  
		}
		, {
			header : '单位',
			dataIndex : 'warrantoutgunit',
			sortable : true,  
		}
		, {
			header : '数量',
			dataIndex : 'warrantoutnum',
			sortable : true,  
		}
		, {
			header : '仓库',
			dataIndex : 'warrantoutstore',
			sortable : true,  
			renderer: function(value, metaData, record, rowIdx, colIdx, store, view){
				var v = Storehousestore.query('storehouseid',value);
				return v.get('storehousename');
			}
		}
		]
	});

//出库单详情窗口
function detailWindow(idwarrantout,warrantoutodm){
	var wheresql = "warrantoutcompany='"+comid+"'";
	if(typeof(warrantoutodm)!='undefined' && warrantoutodm){
		wheresql += " and warrantoutodm='"+warrantoutodm+"'";
	}
	if(typeof(idwarrantout)!='undefined' && idwarrantout){
		wheresql += " and idwarrantout='"+idwarrantout+"'";
	}
	Warrantoutstore.load({
		params : {
			wheresql: wheresql
		}
	});//加载数据
	var selectgridWindow = new Ext.Window({
		title : Warrantouttitle,
		layout : 'fit', // 设置窗口布局模式
		width : 720, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : Warrantoutgrid // 嵌入的表单面板
	});
	selectgridWindow.show();
}





