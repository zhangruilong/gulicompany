var statueStore = new Ext.data.ArrayStore({//状态下拉
    	fields:["name"],
    	data:[["启用"],["禁用"]]
    });
var goodsStatueStore = new Ext.data.ArrayStore({//商品状态下拉
	fields:["name"],
	data:[["上架"],["下架"]]
});
//定义store
function dataStore(fields,url) {
	var store = Ext.create('Ext.data.Store', {
		 pageSize : 20,
		 fields: fields,
	     proxy: {
	         type: 'ajax',
	         url: url,
	         reader: {
	             type: 'json',
	             rootProperty: 'root',
	             totalProperty : 'total',
	         }
	     }
	 });
	return store;
}
//将单元格内容加上超链接
function domUrl(value) {
	return "<a href='" + basePath + value + "' target='_blank'>" + value
			+ "</a>";
}
//让单元格内容自动换行
function wrapCell(value, meta, record) {
	meta.attr = 'style="white-space:normal;word-wrap:break-word;"';
	return value;
}

function pagesizebar(store) {
	/** *********************分页工具条 开始**************************** */
	var PAGESIZEStore = new Ext.data.SimpleStore({
		fields : [ 'value', 'text' ],
		data : [ [ 20, '20/页' ], [ 40, '40/页' ], [ 80, '80/页' ] , [ 0, '全部' ] ]
	});
	
	var pagesize_combo = new Ext.form.ComboBox({
		name : 'pagesize',
		hiddenName : 'pagesize',
		triggerAction : 'all',
		lazyRender : true,
		mode : 'local',
		store : PAGESIZEStore,
		valueField : 'value',
		displayField : 'text',
		value : '20',
		editable : false,
		width : 65
	});
	
	var number = parseInt(pagesize_combo.getValue());
	pagesize_combo.on("select", function(comboBox) {
		bbar.pageSize = parseInt(comboBox.getValue());
		number = parseInt(comboBox.getValue());
		store.reload({
			params : {
				start : 0,
				limit : bbar.pageSize
			}
		});
	});
	
	var bbar = new Ext.PagingToolbar({
		pageSize : number,
		store : store,
		dock: 'bottom',
		displayInfo : true,
		displayMsg : '显示{0}条到{1}条,共{2}条',
		// plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
		emptyMsg : "没有符合条件的记录",
		items : [ '-', pagesize_combo ]
	})
	/** *********************分页工具条 结束**************************** */
	return bbar;
}
/**
 * 创建window
 * 
 * @param {}
 *            url
 * @param {}
 *            title
 * @param {}
 *            _form
 * @param {}
 *            store
 */
function createBigWindow(url,title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : 880, // 窗口宽度
		height : document.body.clientHeight / 1, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 880 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var json = "[" + Ext.encode(_form.form.getFieldValues(false)) + "]";
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
	dataWindow.show();
}
function createWindow(url,title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		height : document.body.clientHeight / 1, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : _form, // 嵌入的表单面板
		buttons : [
				{
					text : '提交',
					iconCls : 'ok',
					handler : function() {
						if (_form.form.isValid()) {
							var json = "[" + Ext.encode(_form.form.getFieldValues(false)) + "]";
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
	dataWindow.show();
}
function createTextWindow(url,title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
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
	dataWindow.removeAll(false);
	dataWindow.items.add(_form);
	dataWindow.show();
}
function createQueryWindow(title,_form,store) {
	var dataWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : Ext.os.deviceType === 'Phone' ? '100%' : 650, // 窗口宽度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
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
//						json = json.replace(/""/g,null);
						store.load({
							params : {
								json : queryjson
							}
						});
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
	dataWindow.show();
}
function selectWindow(title,selectgrid) {
	var selectgridWindow = new Ext.Window({
		title : title, // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : 420, // 窗口宽度
		height : document.body.clientHeight / 1.5, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : Ext.os.deviceType === 'Phone' ? 0 : document.body.clientWidth / 2 - 420 / 2, // 页面定位X坐标
		items : selectgrid, // 嵌入的表单面板
		buttons : [
					{
						text : '确定',
						iconCls : 'ok',
						handler : function() {
							var selectRows = selectgrid.getSelectionModel()
									.getSelections();
							if (selectRows.length != 1) {
								Ext.Msg.alert('提示', '请选择一条！', function() {
								});
								return;
							}
							Ext.getCmp('System_powerparentname').setValue(selectRows[0].get("name"));
							Ext.getCmp('System_powerparentid').setValue(selectRows[0].get("id"));
							win.close();
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							win.close();
						}
					}]
	});
	dataWindow.show();
}