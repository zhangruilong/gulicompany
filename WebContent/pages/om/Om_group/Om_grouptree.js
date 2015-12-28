var treepanel;
Ext.onReady(function() {
	var root = new Ext.tree.AsyncTreeNode({
		text : '工作组管理',
		expanded : true,
		icon : '../../../sysimages/home.gif',
		id : 'root'
	});
	treepanel = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
			baseAttrs : {},
			dataUrl : basePath + Om_groupaction + "?method=selTree"
		}),
		root : root,
		lines : true,
		title : '工作组管理',
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
            	var Om_groupqueryValue = node.attributes['code'];
            	var Om_empgroupqueryValue = node.attributes['code'];
            	if(node.id!='root'&&node.attributes['qtip']!='org'){
            		Om_groupqueryValue = node.parentNode.id;
            	}
            	Om_groupstore.load({
            		params : {
            			query : Om_groupqueryValue
            		}
            	});
            	Om_empgroupstore.load({
            		params : {
            			query : Om_empgroupqueryValue
            		}
            	});
            }
        }
	}); 
	// 拖拽后
	treepanel.on('beforemovenode',function(tree, node, oldParent, newParent, index) {
		if(newParent.attributes['qtip']=='org')
			return true;
		else
			return false;
	
	});
	// 在拖动的时候，主要通过发送ajax请求，到后台，进行数据的同步修改．
	treepanel.on('movenode',function(tree,node,oldParent,newParent,index){  
		var url = basePath;
		var json = "[{";
		if(node.attributes['qtip']=='org'){
			url += Om_groupaction + "?method=updAll";
			json += "groupid:" + node.id + ",parentgroupid:" + newParent.id + "}]";
		}else {
			url += Om_empgroupaction + "?method=updAll";
			json += "groempid:" + node.attributes['leaf'] + ",groupid:" + newParent.id + "}]";
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
		if(node.id=='root'){
			contextMenu = new Ext.menu.Menu( {  
				items : [{  
					text : '增加下级工作组',  
					handler : function() {
						Om_groupdataForm.form.reset();
						createWindow(basePath + Om_groupaction + "?method=insAll", "新增", Om_groupdataForm, node);
						Om_groupdataForm.getForm().setValues({Om_groupparentgroupid:node.id});
					}  
				}, {
					 text : "刷新",
					 handler : function()
					 { 
						node.reload();
					 }
				}]  
			});
		}else if(node.attributes['qtip']=='org'){
			contextMenu = new Ext.menu.Menu( {  
				items : [ {  
					text : '增加下级工作组',  
					handler : function() {
						Om_groupdataForm.form.reset();
						createWindow(basePath + Om_groupaction + "?method=insAll", "新增", Om_groupdataForm, node);
						Om_groupdataForm.getForm().setValues({Om_groupparentgroupid:node.id});
					}  
				}, {  
					text : '增加工作组人员',  
					handler : function() {
						Om_empgroupdataForm.form.reset();
						createWindow(basePath + Om_empgroupaction + "?method=insAll", "新增", Om_empgroupdataForm, node);
						Om_empgroupdataForm.getForm().setValues({Om_empgroupgroupid:node.id});
					} 
				}, {  
					text : '删除本工作组',  
					handler : function() {
						Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要删除当前选择的条目？', function(btn, text) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : basePath + Om_groupaction + "?method=delAll",
									method : 'POST',
									params : {
										json : '[{groupid:' + node.id + '}]'
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
		} 
		contextMenu.showAt(event.getPoint());  
	});
	var groupemp = new Ext.TabPanel({
		activeTab : 0,
		items : [ {
			title : '工作组查询',
			items : Om_groupgrid
		}, {
			title : '人员查询',
			items : Om_empgroupgrid
		} ]
	})
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
	    	items : groupemp
	    } ]
	});
})
