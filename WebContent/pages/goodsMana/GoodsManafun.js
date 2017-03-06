
//筛选商品
function screenWindow(title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						queryjson = "[" + Ext.encode(_form.form.getValues(false)) + "]";
						queryjson = queryjson.replace(/""/g,null);
						store.load();
						dataWindow.hide();
					}
				}, {
					text : '关闭',
					iconCls : 'close',
					handler : function() {
						dataWindow.hide();
					}
				}]
	});
	dataWindow.removeAll(false);	//这一行和下面一行如果没有则第二次选择修改时窗口中的选择框没有选项。
	dataWindow.items.add(_form);
	dataWindow.show();
}
/////----------商品价格(开始)----------/////
var isAdd = true;	//是否新增
var isAct = 'n';
var GoodsPricesForm = Ext.create('Ext.form.Panel', {
		id:'GoodsPricesForm',
		labelAlign : 'left',
		frame : true,
		region : 'north',					//在容器的上方
		layout : 'column',	//横向布局
		items : [ 
		{
			xtype: 'fieldcontainer',
			labelWidth : 120,
			labelAlign : 'left',
			margin : 10,
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
					xtype : 'textfield',
					fieldLabel : '商品编号',
			        margin    : '0 15 0 0',			//外边距
					columnWidth : .3,				//宽度
					labelWidth : 60,				//标题宽度
					editable : false,				//不可编辑
					id : 'setPricesGoodscode',
					name : 'goodscode',
					maxLength : 100,					//输入的最大长度
					triggerWrapCls:'kb-textField-not-border-trigger-wrap',
					inputWrapCls:'kb-textField-not-border-wrap',
					fieldStyle : 'background:none; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: 0px solid',
				}
				, {
					xtype : 'textfield',
					fieldLabel : '商品名称',
			        margin    : '0 15 0 0',
					columnWidth : .5,
					labelWidth : 60,
					editable : false,
					id : 'setPricesGoodsname',
					name : 'goodsname',
					maxLength : 100,
					triggerWrapCls:'kb-textField-not-border-trigger-wrap',
					inputWrapCls:'kb-textField-not-border-wrap',
					fieldStyle : 'background:none; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: 0px solid',
				}
				, {
					xtype : 'textfield',
					fieldLabel : '规格',
			        margin    : '0 15 0 0',
					columnWidth : .2,
					labelWidth : 35,
					editable : false,
					id : 'setPricesGoodsunits',
					name : 'goodsunits',
					maxLength : 100,
					triggerWrapCls:'kb-textField-not-border-trigger-wrap',
					inputWrapCls:'kb-textField-not-border-wrap',
					fieldStyle : 'background:none; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: 0px solid',
				}
		]},
		{
			xtype: 'fieldcontainer',
			labelWidth : 120,
			labelAlign : 'left',
			margin : 10,
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			    	xtype : 'textfield',
					fieldLabel : '单位',
					labelWidth : 35,
					width : 100,
					allowBlank : false,
					id : 'pricesunit',
					name : 'pricesunit'
				}
		]},{
			xtype: 'checkboxgroup',
			fieldLabel: '客户范围',
			margin : 10,
			defaultType: 'checkboxfield',
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			        boxLabel  : '餐饮客户',
			        name      : 'priceScope',
			        margin    : '0 15 0 0',
			        originalValue: '3',
			        id        : 'scopeCheckbox3'
			    }, {
			        boxLabel  : '商超客户',
			        name      : 'priceScope',
			        margin    : '0 15 0 0',
			        originalValue: '2',
	//		        checked   : true,
			        id        : 'scopeCheckbox2'
			    }, {
			        boxLabel  : '组织单位客户',
			        name      : 'priceScope',
			        margin    : '0 15 0 0',
			        originalValue: '1',
			        id        : 'scopeCheckbox1'
			    }
		]},{
			xtype: 'fieldcontainer',
			fieldLabel: '餐饮客户价格',
			labelWidth : 120,
			labelAlign : 'left',
			margin : 10,
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			    	xtype : 'textfield',
					id : 'canyinPricesidLv3',	//价格ID 3
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级3',
					columnWidth : .33,
					labelWidth : 40,
					id : 'canyinPriceLv3',
					name : 'pricesprice',
					allowBlank : false,
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'canyinPricesidLv2',	//价格ID 2
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级2',
					columnWidth : .33,
					labelWidth : 40,
					id : 'canyinPriceLv2',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'canyinPricesidLv1',	//价格ID 1
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级1',
					columnWidth : .33,
					labelWidth : 40,
					id : 'canyinPriceLv1',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }
		]},{
			xtype: 'fieldcontainer',
			fieldLabel: '商超客户价格',
			labelWidth : 120,
			labelAlign : 'left',
			margin : 10,
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			    	xtype : 'textfield',
					id : 'shangchaoPricesidLv3',	//价格ID 3
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级3',
					columnWidth : .33,
					labelWidth : 40,
					id : 'shangchaoPriceLv3',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'shangchaoPricesidLv2',	//价格ID 2
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级2',
					columnWidth : .33,
					labelWidth : 40,
					id : 'shangchaoPriceLv2',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'shangchaoPricesidLv1',	//价格ID 1
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级1',
					columnWidth : .33,
					labelWidth : 40,
					id : 'shangchaoPriceLv1',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }
		]},{
			xtype: 'fieldcontainer',
			fieldLabel: '组织单位客户价格',
			labelWidth : 120,
			labelAlign : 'left',
			margin : 10,
			columnWidth : 1,
			layout : 'column',
			items: [
			    {
			    	xtype : 'textfield',
					id : 'zuzhiPricesidLv3',	//价格ID 3
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级3',
					columnWidth : .33,
					labelWidth : 40,
					id : 'zuzhiPriceLv3',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'zuzhiPricesidLv2',	//价格ID 2
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级2',
					columnWidth : .33,
					labelWidth : 40,
					id : 'zuzhiPriceLv2',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }, {
			    	xtype : 'textfield',
					id : 'zuzhiPricesidLv1',	//价格ID 1
					hidden : true,
					name : 'pricesid'
			    }, {
			    	xtype : 'textfield',
					fieldLabel : '等级1',
					columnWidth : .33,
					labelWidth : 40,
					id : 'zuzhiPriceLv1',
					name : 'pricesprice',
			        margin    : '0 15 0 0'
			    }
		]},
		]
	
});
/////----------商品价格(结束)----------/////
//添加或修改价格(参数 isAct:y则保存成功后上架)
function addPricesData(isAct,pricegoodsid,goodsPricesWindow,store){
	if(GoodsPricesForm.form.isValid()){
		var prevPri;		//上一个价格
		var priIDs = $('[name="pricesid"]');
		var priceScope = '';
		var json = '[';
		$('[name="pricesprice"]').each(function(i,item){
			var pricesprice=item.value;
			if(typeof(pricesprice)=='undefined' || !pricesprice){
				pricesprice = prevPri;
				$(item).val(pricesprice);
			} else {
				prevPri = pricesprice;
			}
			var ids = '';
			if(typeof(priIDs[i].value)!='undefined' && priIDs[i].value){
				ids = priIDs[i].value;
			}
			json += '{"pricesid":"'+ids+'","pricesprice":"'+pricesprice+'","pricesgoods":"'+pricegoodsid+'","pricesunit":"'+
					Ext.getCmp('pricesunit').getValue()+'"},';
		});
		json = json.substring(0,json.length-1)+']';
		priceScope = Ext.getCmp("scopeCheckbox3").getValue()+','+Ext.getCmp("scopeCheckbox2").getValue()+','+Ext.getCmp("scopeCheckbox1").getValue();
		$.ajax({
				url:"CPPricesAction.do?method=setGoodsPrices",
				data:{
					json : json,
					isAct : isAct,		//是否上架
					isAdd : isAdd,		//是否是新增
					priceScope : priceScope		//客户范围
				},
				success: function(resp){
					var data = eval('('+resp+')');
					if(data.code==202){
						Ext.Msg.alert('提示', '价格已更新！',function(){
							goodsPricesWindow.hide();
							store.load();
						});
					} else {
						Ext.Msg.alert('提示', data.msg);
					}
				},
				error:function(resp){
					var data = eval('('+resp+')');
					Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
				}
		});
	}
}
//设置商品价格窗口
function goodsPriceWindow(url,store,pricegoodsid){
	var goodsPricesTile = "商品价格设置";
	var goodsPricesWindow = new Ext.Window({
		title : goodsPricesTile, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 750, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : GoodsPricesForm, // 嵌入的表单面板
		buttons : [
				{
					text : '保存',
					iconCls : 'ok',
					handler : function() {
						addPricesData('n',pricegoodsid,goodsPricesWindow,store);
					}
				}, {
					text : '保存并上架',
					iconCls : 'ok',
					handler : function() {
						addPricesData('y',pricegoodsid,goodsPricesWindow,store);
					}
				}, {
					text : '关闭',
					iconCls : 'close',
					handler : function() {
						goodsPricesWindow.hide();
					}
				}]
	});
	goodsPricesWindow.show();
	$.ajax({
		url : 'CPPricesAction.do?method=selAll',
		type : 'post',
		data : {
			wheresql : "pricesgoods='"+pricegoodsid+"'",
			order : "PRICESCLASS desc , PRICESLEVEL desc"
		},
		success : function(resp){
			var data = eval('('+resp+')');
			if(data.root.length>0){						//如果有价格
				isAdd = false;
				$('[name="pricesprice"]').each(function(i,item){
					$(item).val(data.root[i].pricesprice);			//初始化商品价格
				});
				$('[name="pricesid"]').each(function(i,item){
					$(item).val(data.root[i].pricesid);			//初始化商品价格
				});
				if(data.root[0].creator=='启用'){
					Ext.getCmp('scopeCheckbox3').setValue(true);
				}
				if(data.root[3].creator=='启用'){
					Ext.getCmp('scopeCheckbox2').setValue(true);
				}
				if(data.root[6].creator=='启用'){
					Ext.getCmp('scopeCheckbox1').setValue(true);
				}
				$('[name="pricesunit"]').val(data.root[0].pricesunit);	//价格单位
			} else {
				isAdd = true;
			}
		},
		error : function(resp){
			var data = eval('('+resp+')');
			if(data && data.msg){
				Ext.Msg.alert('提示', data.msg);
			} else {
				Ext.Msg.alert('提示', '操作失败');
			}
			goodsPricesWindow.hide();
		}
	});
}
/////----------商品价格模块(结束)----------/////


//标品的窗口
function scantWindow(){
	var Scanttitle = "标品库";
	var Scantaction = "CPScantAction.do";
	var Scantfields = ['scantid'
       			    ,'scantcode' 
    			    ,'scantname' 
    			    ,'scantdetail' 
    			    ,'scantunits' 
    			    ,'scantclass' 
    			    ,'scantimage' 
    			    ,'scantbrand' 
    			    ,'scanttype' 
    			    ,'scantstatue' 
    			    ,'goodsclassid' 
    			    ,'goodsclasscode' 
    			    ,'goodsclassname' 
    			    ,'goodsclassparent' 
    			    ,'goodsclassdetail' 
    			    ,'goodsclassstatue' 
    			    ,'goodsclasscity' 
    			    ,'goodsclassorder' 
    			    ,'goodsclasscompany' 
	        			      ];// 全部字段
	var Scantkeycolumn = [ 'scantid' ];// 主键
	var Scantstore = dataStore(Scantfields, basePath + Scantaction + "?method=selQuery");// 定义Scantstore
	var Scantbbar = pagesizebar(Scantstore);
	var Scantgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Scantstore,
		bbar : Scantbbar,
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
			header : '标品ID',
			dataIndex : 'scantid',
			hidden : true,
			sortable : true, 
		}
		, {
			header : '编码',
			dataIndex : 'scantcode',
			sortable : true, 
		}
		, {
			header : '名称',
			dataIndex : 'scantname',
			sortable : true, 
			width : 137,
		}
		, {
			header : '规格',
			dataIndex : 'scantunits',
			sortable : true, 
		}
		, {
			header : '小类ID',
			dataIndex : 'scantclass',
			sortable : true,  
			hidden : true,
		}
		, {
			header : '小类',
			dataIndex : 'goodsclassname',
			sortable : true,  
		}
		, {
			header : '图片路径',
			dataIndex : 'scantimage',
			sortable : true,
			hidden : true, 
		}
		, {
			header : '品牌',
			dataIndex : 'scantbrand',
			sortable : true, 
		}
		],
		tbar : [{
			xtype : 'textfield',
			id : 'query',
			name : 'query',
			emptyText : '模糊匹配',
			enableKeyEvents : true,
			width : 100,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if ("" == Ext.getCmp("query").getValue()) {
							Scantstore.load();
						} else {
							Scantstore.load({
								params : {
									query : Ext.getCmp("query").getValue()
								}
							});
						}
					}
				}
			}
		}]
	});
	Scantstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : Scanttitle,
		layout : 'fit', // 设置窗口布局模式
		width : 720, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		//closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 720 / 2, // 页面定位X坐标
		items : Scantgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = Scantgrid.getSelection();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('Goodsgoodscode').setValue(comcode+selectRows[0].get("scantcode"));
							Ext.getCmp('Goodsgoodsname').setValue(selectRows[0].get("scantname"));
							Ext.getCmp('Goodsgoodsunits').setValue(selectRows[0].get("scantunits"));
							Ext.getCmp('Goodsgoodsimage').setValue(nullStr(selectRows[0].get("scantimage")));
							Ext.getCmp('Goodsgoodsbrand').setValue(nullStr(selectRows[0].get("scantbrand")));
							Ext.getCmp('Goodsgoodsclass').setValue(nullStr(selectRows[0].get("scantclass")));
							selectgridWindow.close();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							selectgridWindow.close();
						}
					}]
	});
	selectgridWindow.show();
}

//修改商品的窗口
function editGoodsWindow(url,title,_form,store) {
//	Ext.getCmp('Goodsgoodscode').setDisabled(true);
	Ext.getCmp('Goodsgoodsnumnum').setVisible(false);
	Ext.getCmp('Goodsgoodsnumstore').setVisible(false);
	Ext.getCmp('Goodsgoodsnumnum').setDisabled(true);
	Ext.getCmp('Goodsgoodsnumstore').setDisabled(true);
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									json : json
								},
								success : function(form, action) {
									Ext.Msg.alert('提示', action.result.msg,function(){
										dataWindow.hide();
										store.reload();
									});
								},
								failure : function(form, action) {
									Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
								},
								waitMsg : '正在处理数据,请稍候...'
							});
						} else {
					        Ext.Msg.alert('提示', '请正确填写表单!');
					    }
					}
				}, {
					text : '关闭',
					iconCls : 'close',
					handler : function() {
						dataWindow.hide();
					}
				}]
	});
	dataWindow.removeAll(false);	//这一行和下面一行如果没有则第二次选择修改时窗口中的选择框没有选项。
	dataWindow.items.add(_form);
	dataWindow.show();
}
//新增商品的窗口
function addGoodsWindow(url,title,_form,store) {
//	Ext.getCmp('Goodsgoodscode').setDisabled(false);
	Ext.getCmp('Goodsgoodsnumnum').setDisabled(false);
	Ext.getCmp('Goodsgoodsnumstore').setDisabled(false);
	Ext.getCmp('Goodsgoodsnumnum').setVisible(true);
	Ext.getCmp('Goodsgoodsnumstore').setVisible(true);
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : false, // 是否可收缩
		maximizable : false, // 设置是否可以最大化
		border : false, // 边框线设置
		animateTarget : Ext.getBody(),
		pageY : 0, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '查找标品库',
					iconCls : 'select',
					handler : function() {
						scantWindow();
					}
				},
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var json = "[" + Ext.encode(_form.form.getValues(false)) + "]";
							_form.form.submit({
								url : url,
								waitTitle : '提示',
								params : {//改
									json : json,
									goodsnum : Ext.getCmp("Goodsgoodsnumnum").getValue(),
									goodsnumstore : Ext.getCmp("Goodsgoodsnumstore").getValue()
								},
								success : function(form, action) {
									Ext.Msg.alert('提示', action.result.msg,function(){
										if(action.result.code==202){
											GoodsPricesForm.form.reset();
											Ext.getCmp('setPricesGoodscode').setValue(Ext.getCmp('Goodsgoodscode').getValue());
											Ext.getCmp('setPricesGoodsname').setValue(Ext.getCmp('Goodsgoodsname').getValue());
											Ext.getCmp('setPricesGoodsunits').setValue(Ext.getCmp('Goodsgoodsunits').getValue());
											goodsPriceWindow("CPPricesAction.do?method=setGoodsPrices",store,action.result.goodsid+'');
											dataWindow.hide();
										} else {
											dataWindow.hide();
											store.reload();
										}
									});
								},
								failure : function(form, action) {
									Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
								},
								waitMsg : '正在处理数据,请稍候...'
							});
						} else {
					        Ext.Msg.alert('提示', '请正确填写表单!');
					    }
					}
				}, {
					text : '关闭',
					iconCls : 'close',
					handler : function() {
						dataWindow.hide();
					}
				}]
	});
	dataWindow.removeAll(false);	//这一行和下面一行如果没有则第二次选择修改时窗口中的选择框没有选项。
	dataWindow.items.add(_form);
	dataWindow.show();
}





