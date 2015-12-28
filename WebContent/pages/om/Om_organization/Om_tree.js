var treepanel;
Ext.onReady(function() {
	var root = new Ext.tree.AsyncTreeNode({
		text : '机构人员树',
		expanded : true,
		icon : '../../../sysimages/home.gif',
		id : 'root'
	});
	treepanel = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
			baseAttrs : {},
			dataUrl : basePath + Om_organizationaction + "?method=selOmtree"
		}),
		root : root,
		lines : true,
		title : '机构管理',
		autoScroll : true,
		height : document.documentElement.clientHeight - 4,
		width : 180,
		containerScroll : true,
		animate : false,
		useArrows : false,
		enableDD : true, // 拖拽节点 
	    listeners: {
            'click': function(node, checked){
            	node.getUI().getAnchor().href = "javascript:void(0);";
            	var queryValue = node.attributes['code'];
            	if(node.id=='root'){
        			Om_organizationstore.load();
        			Om_employeestore.load();
        			panel.getLayout().setActiveItem(0);  
        		}else if(node.attributes['qtip']=='org'){
        			Om_organizationstore.load({
                		params : {
                			query : queryValue
                		}
                	});
        			panel.getLayout().setActiveItem(1);  
        		}else if(node.attributes['qtip']=='position'){
        			Om_positionstore.load({
                		params : {
                			query : queryValue
                		}
                	});
        			panel.getLayout().setActiveItem(2);  
        		}else{
        			Om_employeestore.load({
                		params : {
                			query : queryValue
                		}
                	});
        			Om_emppositionstore.load({
                		params : {
                			query : queryValue
                		}
                	});
        			panel.getLayout().setActiveItem(3);  
        		}
            }
        }
	}); 
	// 拖拽后
	treepanel.on('beforemovenode',function(tree, node, oldParent, newParent, index) {
		if(node.attributes['qtip']=='org'&&newParent.attributes['qtip']=='org')
			return true;
		else if(node.attributes['qtip']=='position'&&newParent.attributes['qtip']=='org')
			return true;
		else if(node.attributes['qtip']!='org'&&node.attributes['qtip']!='position'&&newParent.attributes['qtip']=='position')
			return true;
		else
			return false;
	
	});
	// 在拖动的时候，主要通过发送ajax请求，到后台，进行数据的同步修改．
	treepanel.on('movenode',function(tree,node,oldParent,newParent,index){  
		var url = basePath;
		var json = "[{";
		if(node.attributes['qtip']=='org'){
			url += Om_organizationaction + "?method=updAll";
			json += "orgid:" + node.id + ",parentorgid:" + newParent.id + "}]";
		}else if(node.attributes['qtip']=='position'){
			url += Om_positionaction + "?method=updAll";
			json += "positionid:" + node.id + ",orgid:" + newParent.id + "}]";
		}else {
			url += Om_emppositionaction + "?method=updAll";
			json += "posempid:" + node.attributes['leaf'] + ",positionid:" + newParent.id + "}]";
		}
		Ext.Ajax.request({  
			url : url,
			waitMsg : '正在处理数据,请稍候...',
			params:{ 
				json : json
			}  
		});  
	}); 
	// 点击右键出现tree菜单
	treepanel.on('contextmenu', function(node, event) { 
		node.select();// 点击右键同时选中该项
		event.preventDefault(); 
		// 定义右键菜单
		var contextMenu;
		if(node.attributes['qtip']=='org'){
			contextMenu = new Ext.menu.Menu( {  
				items : [ {  
					text : '增加下级机构',  
					handler : function() {
						Om_organizationdataForm.form.reset();
						createWindow(basePath + Om_organizationaction + "?method=insAll", "新增", Om_organizationdataForm, node);
						Om_organizationdataForm.getForm().setValues({Om_organizationparentorgid:node.id});
					}  
				}, {  
					text : '增加下级岗位',  
					handler : function() {
						Om_positiondataForm.form.reset();
						createWindow(basePath + Om_positionaction + "?method=insAll", "新增", Om_positiondataForm, node);
						Om_positiondataForm.getForm().setValues({Om_positionorgid:node.id});
					}   
				}, {  
					text : '删除本机构',  
					handler : function() {
						Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要删除当前选择的条目？', function(btn, text) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : basePath + Om_organizationaction + "?method=delAll",
									method : 'POST',
									params : {
										json : '[{orgid:' + node.id + '}]'
									},
									success : function(response) {
										var resp = Ext.decode(response.responseText); 
										Ext.Msg.alert('提示', resp.msg, function(){
											node.parentNode.reload(); 
										});
									},
									failure : function(response) {
										Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
									}
								});
							}
						});
					}   
				}, {
					 text : "刷新",
					 handler : function()
					 { 
						node.reload();
					 }
				}]  
			});
		}else if(node.attributes['qtip']=='position'){
			contextMenu = new Ext.menu.Menu( {  
				items : [{  
					text : '增加新进人员',  
					handler : function() {
						Om_employeedataForm.form.reset();
						createWindow(basePath + Om_employeeaction + "?method=insAll", "新增", Om_employeedataForm, node);
						Om_employeedataForm.getForm().setValues({Om_employeeposition:node.id,Om_employeeorgid:node.parentNode.id});
					}   
				}, {  
					text : '增加已有人员',  
					handler : function() {
						Om_emppositiondataForm.form.reset();
						createWindow(basePath + Om_emppositionaction + "?method=insAll", "新增", Om_emppositiondataForm, node);
						Om_emppositiondataForm.getForm().setValues({Om_emppositionpositionid:node.id,Om_emppositionpositionname:node.text});
					}   
				}, {  
					text : '删除本岗位',  
					handler : function() {
						Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要删除当前选择的条目？', function(btn, text) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : basePath + Om_positionaction + "?method=delAll",
									method : 'POST',
									params : {
										json : '[{positionid:' + node.id + '}]'
									},
									success : function(response) {
										var resp = Ext.decode(response.responseText); 
										Ext.Msg.alert('提示', resp.msg, function(){
											node.parentNode.reload(); 
										});
									},
									failure : function(response) {
										Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
									}
								});
							}
						});
					}   
				}, {
					 text : "刷新",
					 handler : function()
					 { 
						node.reload();
					 }
				}]  
			});
		}else if(node.id=='root'){
			contextMenu = new Ext.menu.Menu( {  
				items : [{  
					text : '增加下级机构',  
					handler : function() {
						Om_organizationdataForm.form.reset();
						createWindow(basePath + Om_organizationaction + "?method=insAll", "新增", Om_organizationdataForm, node);
						Om_organizationdataForm.getForm().setValues({Om_organizationparentorgid:node.id});
					}  
				}, {
					 text : "刷新",
					 handler : function()
					 { 
						node.reload();
					 }
				}]  
			});
		}
		contextMenu.showAt(event.getPoint());  
	});
	var panel = new Ext.Panel({ 
		layout: 'card', 
		activeItem: 0, //默认显示第一个子面板 
		layoutConfig: { 
			animate: true 
		}, 
		items: [orgemp,Om_organizationgrid,Om_positiongrid,empposition]
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ {
	        region: 'west',
	        floatable: false,
	        width: 180,
	        items: treepanel
	    },{
	    	id : 'rightviewport',
	    	region : 'center',
	    	items : panel
	    } ]
	});
})
